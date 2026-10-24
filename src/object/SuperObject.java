package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX;
    public int worldY;

    public void draw(Graphics2D g2, GamePanel gamePanel) {

        int screenX = worldX - gamePanel.player.x + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.y + gamePanel.player.screenY;

        if(worldX + gamePanel.tileSize > gamePanel.player.x - gamePanel.player.screenX &&
           worldX - gamePanel.tileSize < gamePanel.player.x + gamePanel.player.screenX &&
           worldY + gamePanel.tileSize > gamePanel.player.y - gamePanel.player.screenY &&
           worldY - gamePanel.tileSize < gamePanel.player.y + gamePanel.player.screenY) {

            g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

        }
    }
}
