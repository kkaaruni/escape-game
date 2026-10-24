package main;

import entity.Player;
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

    // FPS
    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    KeyHandler keyHandler = new KeyHandler();
    public Player player = new Player(this, keyHandler);

    public SuperObject[] object =  new SuperObject[10];
    public AssetSetter assetSetter = new AssetSetter(this);
    MouseHandler mouseHandler = new MouseHandler(this);

    public GamePanel() {
        this.setPreferredSize( new Dimension( screenWidth, screenHeight ) );
        this.setBackground( Color.black );
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
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
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

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

        g2.dispose();
    }
}
