package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    // FPS
    int FPS = 60;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


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
