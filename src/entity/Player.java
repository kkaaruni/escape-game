package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        solidArea = new Rectangle(8, 16, 32, 32);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/man_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/man_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/man_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/man_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/man_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/man_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/man_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/man_right_2.png")));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void interactWithObject(int i) {
        if (i != 999) {
            // constant first, so it won't crash if an object has no name set
            if ("Toy box".equals(gamePanel.object[i].name)) {
                keyHandler.upPressed = false;
                keyHandler.downPressed = false;
                keyHandler.leftPressed = false;
                keyHandler.rightPressed = false;

                gamePanel.ui.toyCommandNum = 0;
                gamePanel.gameState = gamePanel.toyChoiceState;
            }
        }
    }

    public void update() {

        if(keyHandler.upPressed == true || keyHandler.downPressed == true ||
            keyHandler.leftPressed == true || keyHandler.rightPressed == true) {
            if(keyHandler.upPressed){
                direction = "up";
            }else if(keyHandler.downPressed){
                direction = "down";
            } else if(keyHandler.leftPressed){
                direction = "left";
            } else if(keyHandler.rightPressed){
                direction = "right";
            }

            // Check tile and object collision
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);
            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            interactWithObject(objIndex);

            // If collision is false, player can move
            if (collisionOn == false) {
                switch(direction) {
                    case "up":
                        y -= speed;
                        break;
                    case "down":
                        y += speed;
                        break;
                    case "left":
                        x -= speed;
                        break;
                    case "right":
                        x += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
