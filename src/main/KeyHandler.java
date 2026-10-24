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