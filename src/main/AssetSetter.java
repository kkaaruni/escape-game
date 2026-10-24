package main;

import object.OBJ_diary;
import object.SuperObject;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.object[0] = new OBJ_diary();
        gamePanel.object[0].worldX = 10 * gamePanel.tileSize;
        gamePanel.object[0].worldY = 2 * gamePanel.tileSize;

        gamePanel.object[1] = new OBJ_diary();
        gamePanel.object[1].worldX = 9 * gamePanel.tileSize;
        gamePanel.object[1].worldY = 5 * gamePanel.tileSize;
    }
}
