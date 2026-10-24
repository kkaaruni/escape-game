package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 32;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    //FPS



    public GamePanel() {
        this.setPreferredSize( new Dimension( screenWidth, screenHeight ) );
        this.setBackground( Color.black );
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable( true );


    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

    while(gameThread != null){
        long currentTime = System.currentTimeMillis();


        //Update information such as character position
        update();

        //Draw the screen with the update information
        repaint();

    }

    }

    public void update(){

        if(keyHandler.upPressed){
            playerY -= playerSpeed;
        }else if(keyHandler.downPressed){
            playerY += playerSpeed;
        } else if(keyHandler.leftPressed){
            playerX -= playerSpeed;
        } else if(keyHandler.rightPressed){
            playerX += playerSpeed;
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor( Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();

    }
}
