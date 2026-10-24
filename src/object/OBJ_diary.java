package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_diary extends SuperObject{

    public OBJ_diary() {
        name = "Closed Diary";
        description = "Dear diary, \n" +
                "My wife and I dropped off Brian to his first day of school today. \n" +
                "He looked so cute in his blue uniform. He’s grown so much, \n" +
                "I’m so proud of my little boy. He carried his big backpack on his own too.";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/clues/closed-diary.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
