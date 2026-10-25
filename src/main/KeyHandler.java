package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean interactPressed = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Normal player movement
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) upPressed = true;
            if (code == KeyEvent.VK_S) downPressed = true;
            if (code == KeyEvent.VK_A) leftPressed = true;
            if (code == KeyEvent.VK_D) rightPressed = true;
            if (code == KeyEvent.VK_E) interactPressed = true;
        }

        // Toy selection menu
        else if (gp.gameState == gp.toyChoiceState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.toyCommandNum = (gp.ui.toyCommandNum + 2) % 3;
            }

            if (code == KeyEvent.VK_S) {
                gp.ui.toyCommandNum = (gp.ui.toyCommandNum + 1) % 3;
            }

            if (code == KeyEvent.VK_ENTER) {
                gp.ui.selectedToy = gp.ui.toyCommandNum;
                gp.gameState = gp.toyViewState;
            }

            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
        }

        // Viewing the selected toy
        else if (gp.gameState == gp.toyViewState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.toyChoiceState;
            }
        }
        // Reading the diary
        else if (gp.gameState == gp.diaryState) {
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                if (gp.diaryPage < gp.diaryPages.length) {
                    gp.diaryPage++;
                }
            }

            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                if (gp.diaryPage > 1) {
                    gp.diaryPage--;
                }
            }

            if (code == KeyEvent.VK_ESCAPE) {
                gp.diaryOpen = false;
                gp.dialogueOn = false;
                gp.gameState = gp.playState;
            }
        }
        else if (gp.gameState == gp.messageState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.currentMessage = "";
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) upPressed = false;
        if (code == KeyEvent.VK_S) downPressed = false;
        if (code == KeyEvent.VK_A) leftPressed = false;
        if (code == KeyEvent.VK_D) rightPressed = false;
        if (code == KeyEvent.VK_E) interactPressed = false;
    }
}
