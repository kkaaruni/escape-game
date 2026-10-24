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
        gamePanel.object[0].worldX = 6 * gamePanel.tileSize;
        gamePanel.object[0].worldY = 5 * gamePanel.tileSize;

    }
}
