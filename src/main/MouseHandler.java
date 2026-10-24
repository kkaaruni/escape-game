package main;

import object.SuperObject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class MouseHandler implements MouseListener {

    GamePanel gamePanel;

    public MouseHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        // Dialogue box dimensions (must match GamePanel)
        int x = gamePanel.tileSize;
        int y = gamePanel.tileSize * 8;
        int width = gamePanel.screenWidth - gamePanel.tileSize * 2;

        // Close button dimensions
        int closeSize = 30;
        int closeX = x + width - closeSize - 10;
        int closeY = y + 10;

        // Check if close button was clicked
        if (gamePanel.dialogueOn &&
                mouseX >= closeX && mouseX < closeX + closeSize &&
                mouseY >= closeY && mouseY < closeY + closeSize) {

            gamePanel.dialogueOn = false;
            gamePanel.repaint();
            return;
        }

        // Check if an object was clicked
        for (SuperObject obj : gamePanel.object) {
            if (obj != null &&
                    mouseX >= obj.worldX &&
                    mouseX < obj.worldX + gamePanel.tileSize &&
                    mouseY >= obj.worldY &&
                    mouseY < obj.worldY + gamePanel.tileSize) {

                gamePanel.dialogueText = obj.description;
                gamePanel.dialogueOn = true;
                gamePanel.repaint();
                break;
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

