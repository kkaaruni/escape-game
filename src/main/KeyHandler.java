package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) upPressed = true;
            if (code == KeyEvent.VK_S) downPressed = true;
            if (code == KeyEvent.VK_A) leftPressed = true;
            if (code == KeyEvent.VK_D) rightPressed = true;
        }
        else if (gp.gameState == gp.toyChoiceState) {
            if (code == KeyEvent.VK_W) gp.ui.toyCommandNum = (gp.ui.toyCommandNum + 2) % 3;
            if (code == KeyEvent.VK_S) gp.ui.toyCommandNum = (gp.ui.toyCommandNum + 1) % 3;
            if (code == KeyEvent.VK_ENTER) {
                gp.ui.selectedToy = gp.ui.toyCommandNum;
                gp.gameState = gp.toyViewState;
            }
            if (code == KeyEvent.VK_ESCAPE) gp.gameState = gp.playState;
        }
        else if (gp.gameState == gp.toyViewState) {
            if (code == KeyEvent.VK_ESCAPE) gp.gameState = gp.toyChoiceState;
        }
        else if (gp.gameState == gp.messageState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
        }
        else if (gp.gameState == gp.codeEntryState) {
            char c = e.getKeyChar();

            if (c >= '0' && c <= '9' && gp.ui.codeInput.length() < 4) {
                gp.ui.codeInput += c;
                gp.ui.codeMessage = "";
            }
            if (code == KeyEvent.VK_BACK_SPACE && gp.ui.codeInput.length() > 0) {
                gp.ui.codeInput = gp.ui.codeInput.substring(0, gp.ui.codeInput.length() - 1);
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.codeInput.equals("1209")) {
                    gp.toyBoxUnlocked = true;
                    gp.ui.toyCommandNum = 0;
                    gp.gameState = gp.toyChoiceState;   // "Which toy do you look at?"
                } else {
                    gp.ui.codeMessage = "Wrong code.";
                    gp.ui.codeInput = "";
                }
            }
            if (code == KeyEvent.VK_ESCAPE) {
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
    }
}