package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.x + entity.solidArea.x;
        int entityRightWorldX = entity.x + entity.solidArea.width;
        int entityTopWorldY = entity.y + entity.solidArea.y;
        int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }

    }


    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gamePanel.object.length; i++) {

            if (gamePanel.object[i] != null) {

                // Get the entity's current solid area in world coordinates
                int entityLeftX = entity.x + entity.solidArea.x;
                int entityRightX = entityLeftX + entity.solidArea.width;
                int entityTopY = entity.y + entity.solidArea.y;
                int entityBottomY = entityTopY + entity.solidArea.height;

                // Predict where the entity will move
                switch (entity.direction) {
                    case "up":
                        entityTopY -= entity.speed;
                        break;
                    case "down":
                        entityBottomY += entity.speed;
                        break;
                    case "left":
                        entityLeftX -= entity.speed;
                        break;
                    case "right":
                        entityRightX += entity.speed;
                        break;
                }

                // Object's solid area in world coordinates
                int objectLeftX = gamePanel.object[i].worldX
                        + gamePanel.object[i].solidArea.x;
                int objectRightX = objectLeftX
                        + gamePanel.object[i].solidArea.width;
                int objectTopY = gamePanel.object[i].worldY
                        + gamePanel.object[i].solidArea.y;
                int objectBottomY = objectTopY
                        + gamePanel.object[i].solidArea.height;

                // Check whether the two rectangles overlap
                if (entityLeftX < objectRightX
                        && entityRightX > objectLeftX
                        && entityTopY < objectBottomY
                        && entityBottomY > objectTopY) {

                    if (gamePanel.object[i].collision) {
                        entity.collisionOn = true;
                    }

                    if (player) {
                        index = i;
                    }
                }
            }
        }

        return index;
    }

}
