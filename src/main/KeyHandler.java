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
            if (code == KeyEvent.VK_BACK_SPACE && !gp.ui.codeInput.isEmpty()) {
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
        else if (gp.gameState == gp.diaryState) {
            int pages = gp.ui.currentDiary.diaryPages.length;

            if ((code == KeyEvent.VK_D) && gp.ui.diaryPage < pages - 1) {
                gp.ui.diaryPage++;
            }
            if ((code == KeyEvent.VK_A) && gp.ui.diaryPage > 0) {
                gp.ui.diaryPage--;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.currentDiary.image = gp.ui.currentDiary.closedImage;   // back to closed
                gp.gameState = gp.playState;
            }
        }

        else if (gp.gameState == gp.passwordState) {
            char c = e.getKeyChar();

            if (Character.isLetterOrDigit(c) && gp.ui.passwordInput.length() < 20) {
                gp.ui.passwordInput += c;
                gp.ui.passwordMessage = "";
            }
            if (code == KeyEvent.VK_BACK_SPACE && !gp.ui.passwordInput.isEmpty()) {
                gp.ui.passwordInput = gp.ui.passwordInput.substring(0, gp.ui.passwordInput.length() - 1);
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.passwordInput.equalsIgnoreCase("deadlock")) {
                    gp.computerUnlocked = true;
                    gp.ui.computerCommandNum = 0;
                    gp.gameState = gp.computerMenuState;
                } else {
                    gp.ui.passwordMessage = "Incorrect password.";
                    gp.ui.passwordInput = "";
                }
            }
            if (code == KeyEvent.VK_ESCAPE) gp.gameState = gp.playState;
        }
        else if (gp.gameState == gp.computerMenuState) {
            if (code == KeyEvent.VK_W) gp.ui.computerCommandNum = (gp.ui.computerCommandNum + 1) % 2;
            if (code == KeyEvent.VK_S) gp.ui.computerCommandNum = (gp.ui.computerCommandNum + 1) % 2;
            if (code == KeyEvent.VK_ENTER) {
                gp.ui.computerScreen = gp.ui.computerCommandNum;
                gp.gameState = gp.computerViewState;
            }
            if (code == KeyEvent.VK_ESCAPE) gp.gameState = gp.playState;
        }
        else if (gp.gameState == gp.computerViewState) {
            if (code == KeyEvent.VK_ESCAPE) gp.gameState = gp.computerMenuState;
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