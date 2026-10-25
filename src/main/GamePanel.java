package main;

import entity.Player;
import object.OBJ_diary;
import object.SuperObject;
import tile.TileManager;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int messageState = 5;
    public final int codeEntryState = 6;

    // FPS
    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    KeyHandler keyHandler = new KeyHandler(this);
    public Player player = new Player(this, keyHandler);
    public UI ui = new UI(this);

    public SuperObject[] object =  new SuperObject[10];
    public AssetSetter assetSetter = new AssetSetter(this);


    public boolean dialogueOn = false;
    public String dialogueText = "";
    public boolean diaryOpen = false;

    public int diaryPage = 1;

    public String[] diaryPages = {
            "Dear diary,\n" +
             "Today my wife and I dropped off Oliver for his first day of school .\n" +
             "He looked so cute in his blue uniform.",

            "Dear diary,\n" +
                    "Today I went to see my mum. It's hard watching her struggle.\n" +
                    "Regardless, Oliver said that my mum has beautiful red hair and wants to\n" +
                    "look after his mum like how I look after mine.",

            "Dear diary,\n" +
            "Ronnie keeps asking for my research.\n" +
                    "I want to help him but he's being so frustrating.\n" +
                    "My wife passed away only 6 weeks ago and all he cares about\n" +
                    "is my research on semaphores..."
    };

    public final int diaryState = 4;
    public final int playState = 1;
    public final int toyChoiceState = 2;
    public final int toyViewState = 3;

    public int gameState = playState;

    public GamePanel() {
        this.setPreferredSize( new Dimension( screenWidth, screenHeight ) );
        this.setBackground( Color.black );
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable( true );
    }

    public void setupGame(){
        assetSetter.setObject();
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }

    }

    public void update(){
        if (gameState == playState) {
            player.update();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        System.out.println("Drawing objects...");

        Graphics2D g2 = (Graphics2D) g;

        // tile
        tileManager.draw(g2);

        // object
        for(int i = 0; i < object.length; i++) {
            if(object[i] != null) {
                object[i].draw(g2, this);
            }
        }

        // player
        player.draw(g2);

        if (diaryOpen) {
            for (SuperObject obj : object) {
                if (obj instanceof OBJ_diary) {
                    OBJ_diary diary = (OBJ_diary) obj;

                    g2.drawImage(diary.openedImage,
                            diary.worldX, diary.worldY,
                            tileSize, tileSize, null);
                }
            }
        }

        if (dialogueOn) {
            drawDialogueScreen(g2);
        }

        if (gameState == messageState|| gameState == toyChoiceState || gameState == toyViewState){
                ui.draw(g2);
            }

        g2.dispose();
    }

    private void drawDialogueScreen(Graphics2D g2) {
        System.out.println("DRAWING DIALOGUE");

            int x = tileSize;
            int y = tileSize * 8;
            int width = screenWidth - tileSize * 2;
            int height = tileSize * 3;

            // Dialogue box background
            g2.setColor(Color.DARK_GRAY);
            g2.fillRoundRect(x, y, width, height, 25, 25);


            // Border
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(x, y, width, height, 25, 25);

            // Dialogue text
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.WHITE);


            int textX = x + 20;
            int textY = y + 35;

        String textToShow = dialogueText;

        if (diaryOpen) {
            textToShow = diaryPages[diaryPage - 1];
        }

        for (String line : textToShow.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 25;
        }

        // UI
        ui.draw(g2);

    }
}
