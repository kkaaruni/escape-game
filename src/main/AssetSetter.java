package main;

import object.*;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.object[0] = new OBJ_diary();
        gamePanel.object[0].worldX = (int) (13.1 * gamePanel.tileSize);
        gamePanel.object[0].worldY = (int) (2.6 * gamePanel.tileSize);
        gamePanel.object[0].collision = true;

        gamePanel.object[1] = new OBJ_toy_box();
        gamePanel.object[1].worldX = 14 * gamePanel.tileSize;
        gamePanel.object[1].worldY = 5 * gamePanel.tileSize;
        gamePanel.object[1].collision = true;

        gamePanel.object[2] = new OBJ_PostItNote("Buy Oliver a toy horse.");
        gamePanel.object[2].worldX = 12 * gamePanel.tileSize;
        gamePanel.object[2].worldY = (int) (0.2 * gamePanel.tileSize);
        gamePanel.object[2].collision = true;

        gamePanel.object[3] = new OBJ_table();
        gamePanel.object[3].worldX = 13 * gamePanel.tileSize;
        gamePanel.object[3].worldY = 3 * gamePanel.tileSize;
        gamePanel.object[3].collision = true;

        gamePanel.object[4] = new OBJ_binary();
        gamePanel.object[4].worldX = 6 * gamePanel.tileSize;
        gamePanel.object[4].worldY = 11 * gamePanel.tileSize;
        gamePanel.object[4].collision = true;

        gamePanel.object[5] = new OBJ_hex();
        gamePanel.object[5].worldX = 15 * gamePanel.tileSize;
        gamePanel.object[5].worldY = 8 * gamePanel.tileSize;
        gamePanel.object[5].collision = true;

        gamePanel.object[6] = new OBJ_PostItNote("Ronnie waits for the research held by Brian, while Brian\n" +
                                                 "waits for the research held by Ronnie.");
        gamePanel.object[6].worldX = (int) (0.1 * gamePanel.tileSize);
        gamePanel.object[6].worldY = 6 * gamePanel.tileSize;
        gamePanel.object[6].collision = true;

        gamePanel.object[7] = new OBJ_computer();
        gamePanel.object[7].worldX = 4 * gamePanel.tileSize;
        gamePanel.object[7].worldY = 8 * gamePanel.tileSize;
        gamePanel.object[7].collision = true;

        gamePanel.object[8] = new OBJ_first_aid_kit();
        gamePanel.object[8].worldX = 7 * gamePanel.tileSize;
        gamePanel.object[8].worldY = 4 * gamePanel.tileSize;
        gamePanel.object[8].collision = true;

        gamePanel.object[9] = new OBJ_barrel();
        gamePanel.object[9].worldX = 10 * gamePanel.tileSize;
        gamePanel.object[9].worldY = 7 * gamePanel.tileSize;
        gamePanel.object[9].collision = true;

        gamePanel.object[10] = new OBJ_newspaper();
        gamePanel.object[10].worldX = 1 * gamePanel.tileSize;
        gamePanel.object[10].worldY = 1 * gamePanel.tileSize;
        gamePanel.object[10].collision = true;


    }
}
