package main;

import object.OBJ_diary;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI {
    GamePanel gp;
    public int toyCommandNum = 0;
    public int selectedToy = 0;
    public String currentMessage = "";
    public String codeInput = "";
    public String codeMessage = "";
    public OBJ_diary currentDiary;
    public int diaryPage = 0;
    public String passwordInput = "";
    public String passwordMessage = "";
    public int computerCommandNum = 0;   // 0 = email, 1 = calendar
    public int computerScreen = 0;

    BufferedImage[] toyImages = new BufferedImage[3];
    String[] toyNames = {"Bear", "Duck", "Horse"};
    String[] toyText = {
            "A cute, preloved teddy bear :)",
            "Just an ordinary duck that likes lemonade...",
            "A toy horse with the names Brian and Maria engraved\n" + "on its hooves."
    };

    String[] computerNames = {"Email", "Calendar"};
    String[] computerText = {
            "To: brian7@yahoo.com\n\nHi dad,\nI miss you so much. I wish you could come back home ;)\n\nLove,\nOli",
            "Clara's birthday: March 9\nBea's birthday: June 25\nGary's birthday: July 10\n" +
                    "Blu's birthday: September 26\nChloe's birthday: October 12\nMaria's birthday: October 28\n" +
                    "Harriet's birthday: November 12\nCharlotte's birthday: November 30\nJohn's birthday: December 7"
    };

    public UI(GamePanel gp) {
        this.gp = gp;
        String[] files = {"bear-toy", "duck-toy", "horse-toy"};
        try {
            for (int i = 0; i < 3; i++) {
                toyImages[i] = ImageIO.read(Objects.requireNonNull(
                        getClass().getResourceAsStream("/clues/" + files[i] + ".png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        if (gp.gameState == gp.messageState) {
            int w = 450, h = 160;
            int x = (gp.screenWidth - w) / 2, y = (gp.screenHeight - h) / 2;
            g2.setColor(new Color(0, 0, 0, 200));
            g2.fillRoundRect(x, y, w, h, 25, 25);

            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(16F));
            int ty = y + 50;
            for (String line : currentMessage.split("\n")) {
                g2.drawString(line, x + 30, ty);
                ty += 28;
            }
            g2.setFont(g2.getFont().deriveFont(14F));
            g2.drawString("[Esc] Close", x + 30, y + h - 15);
        }

        if (gp.gameState == gp.codeEntryState) {
            int w = 400, h = 180;
            int x = (gp.screenWidth - w) / 2, y = (gp.screenHeight - h) / 2;
            g2.setColor(new Color(0, 0, 0, 200));
            g2.fillRoundRect(x, y, w, h, 25, 25);

            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(20F));
            g2.drawString("The box is locked. Enter the code:", x + 30, y + 45);

            // show 4 slots, filled with typed digits
            g2.setFont(g2.getFont().deriveFont(36F));
            for (int i = 0; i < 4; i++) {
                String c = i < codeInput.length() ? String.valueOf(codeInput.charAt(i)) : "_";
                g2.drawString(c, x + 110 + i * 50, y + 100);
            }

            g2.setFont(g2.getFont().deriveFont(16F));
            g2.setColor(Color.RED);
            g2.drawString(codeMessage, x + 30, y + 130);
            g2.setColor(Color.WHITE);
            g2.drawString("[Enter] Confirm   [Backspace] Delete   [Esc] Cancel", x + 30, y + h - 15);
        }

        if (gp.gameState == gp.toyChoiceState) {
            int w = 400, h = 220;
            int x = (gp.screenWidth - w) / 2, y = (gp.screenHeight - h) / 2;
            g2.setColor(new Color(0, 0, 0, 200));
            g2.fillRoundRect(x, y, w, h, 25, 25);

            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(24F));
            g2.drawString("Which toy do you look at?", x + 30, y + 40);
            for (int i = 0; i < 3; i++) {
                g2.drawString(toyNames[i], x + 90, y + 90 + i * 40);
                if (toyCommandNum == i) g2.drawString(">", x + 60, y + 90 + i * 40);
            }
            g2.setFont(g2.getFont().deriveFont(16F));
            g2.drawString("[W/S] Move   [Enter] Open   [Esc] Leave", x + 30, y + h - 15);
        }

        if (gp.gameState == gp.toyViewState) {
            int w = 500, h = 400;
            int x = (gp.screenWidth - w) / 2, y = (gp.screenHeight - h) / 2;
            g2.setColor(new Color(0, 0, 0, 200));
            g2.fillRoundRect(x, y, w, h, 25, 25);

            g2.drawImage(toyImages[selectedToy], x + (w - 200) / 2, y + 20, 200, 200, null);

            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(18F));
            int ty = y + 250;
            for (String line : toyText[selectedToy].split("\n")) {
                g2.drawString(line, x + 30, ty);
                ty += 26;
            }
            g2.drawString("[Esc] Back", x + 30, y + h - 20);
        }

        if (gp.gameState == gp.diaryState && currentDiary != null) {
            int w = 560, h = 220;
            int x = (gp.screenWidth - w) / 2, y = (gp.screenHeight - h) / 2;
            g2.setColor(new Color(0, 0, 0, 200));
            g2.fillRoundRect(x, y, w, h, 25, 25);

            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(18F));
            int ty = y + 45;
            for (String line : currentDiary.diaryPages[diaryPage].split("\n")) {
                g2.drawString(line, x + 30, ty);
                ty += 26;
            }

            g2.setFont(g2.getFont().deriveFont(14F));
            g2.drawString("Page " + (diaryPage + 1) + " / " + currentDiary.diaryPages.length,
                    x + 30, y + h - 40);
            g2.drawString("[A/D] Turn page   [Esc] Close", x + 30, y + h - 15);
        }

        if (gp.gameState == gp.passwordState) {
            int w = 400, h = 170;
            int x = (gp.screenWidth - w) / 2, y = (gp.screenHeight - h) / 2;
            g2.setColor(new Color(0, 0, 0, 200));
            g2.fillRoundRect(x, y, w, h, 25, 25);

            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(22F));
            g2.drawString("Enter the password:", x + 30, y + 45);
            g2.drawString("*".repeat(passwordInput.length()) + "_", x + 30, y + 85);

            g2.setFont(g2.getFont().deriveFont(16F));
            g2.setColor(Color.RED);
            g2.drawString(passwordMessage, x + 30, y + 112);
            g2.setColor(Color.WHITE);
            g2.drawString("[Enter] Confirm   [Backspace] Delete   [Esc] Cancel", x + 30, y + h - 15);
        }

        if (gp.gameState == gp.computerMenuState) {
            int w = 400, h = 190;
            int x = (gp.screenWidth - w) / 2, y = (gp.screenHeight - h) / 2;
            g2.setColor(new Color(0, 0, 0, 200));
            g2.fillRoundRect(x, y, w, h, 25, 25);

            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(24F));
            g2.drawString("What do you open?", x + 30, y + 45);
            for (int i = 0; i < 2; i++) {
                g2.drawString(computerNames[i], x + 90, y + 95 + i * 40);
                if (computerCommandNum == i) g2.drawString(">", x + 60, y + 95 + i * 40);
            }
            g2.setFont(g2.getFont().deriveFont(14F));
            g2.drawString("[W/S] Move   [Enter] Open   [Esc] Leave", x + 30, y + h - 15);
        }

        if (gp.gameState == gp.computerViewState) {
            int w = 560, h = 330;
            int x = (gp.screenWidth - w) / 2, y = (gp.screenHeight - h) / 2;
            g2.setColor(new Color(0, 0, 0, 200));
            g2.fillRoundRect(x, y, w, h, 25, 25);

            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(18F));
            int ty = y + 40;
            for (String line : computerText[computerScreen].split("\n")) {
                g2.drawString(line, x + 30, ty);
                ty += 26;
            }
            g2.setFont(g2.getFont().deriveFont(14F));
            g2.drawString("[Esc] Back", x + 30, y + h - 15);
        }
    }
}
