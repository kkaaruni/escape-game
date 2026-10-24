package object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class OBJ_diary extends SuperObject{

    public Image openedImage;

    public OBJ_diary() {
        name = "Closed Diary";
        description = "You opened the diary and start reading.";
        
        try {
            image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResource("/clues/closed-diary.png")));

             openedImage = ImageIO.read(Objects.requireNonNull(
                    getClass().getResource("/clues/opened-diary.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
