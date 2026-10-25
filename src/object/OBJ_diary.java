package object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class OBJ_diary extends SuperObject{

    public BufferedImage openedImage;
    public BufferedImage closedImage;
    public String[] diaryPages = {
            "Dear diary,\n" +
                    "Today my wife and I dropped off Oliver for his first day of school.\n" +
                    "He looked so cute in his blue uniform. He carried his big\n" +
                    "backpack on his own too.",

            "Dear diary,\n" +
                    "Today I went to see my mum. It's hard watching her struggle.\n" +
                    "Regardless, Oliver said that my mum has beautiful red hair and\n" +
                    "wants to look after his mum like how I look after mine.",

            "Dear diary,\n" +
                    "Ronnie keeps asking for my research.\n" +
                    "I want to help him but he's being so frustrating.\n" +
                    "My wife passed away only 6 weeks ago and all he cares about\n" +
                    "is my research on semaphores..."
    };

    public OBJ_diary() {
        name = "Closed Diary";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/clues/closed-diary.png")));
            closedImage = image;
            openedImage = ImageIO.read(Objects.requireNonNull(
                    getClass().getResource("/clues/opened-diary.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
