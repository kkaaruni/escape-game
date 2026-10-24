package main;

import object.*;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.object[0] = new OBJ_diary();
        gamePanel.object[0].worldX = 6 * gamePanel.tileSize;
        gamePanel.object[0].worldY = 5 * gamePanel.tileSize;

        gamePanel.object[1] = new OBJ_toy_box();
        gamePanel.object[1].worldX = 14 * gamePanel.tileSize;
        gamePanel.object[1].worldY = 5 * gamePanel.tileSize;

        gamePanel.object[2] = new OBJ_PostItNote();
        gamePanel.object[2].worldX = 12 * gamePanel.tileSize;
        gamePanel.object[2].worldY = 0 * gamePanel.tileSize;

        gamePanel.object[3] = new OBJ_barrel();
        gamePanel.object[3].worldX = 1 * gamePanel.tileSize;
        gamePanel.object[3].worldY = 1 * gamePanel.tileSize;

        gamePanel.object[4] = new OBJ_first_aid_kit();
        gamePanel.object[4].worldX = 1 * gamePanel.tileSize;
        gamePanel.object[4].worldY = 10 * gamePanel.tileSize;


    }
}
