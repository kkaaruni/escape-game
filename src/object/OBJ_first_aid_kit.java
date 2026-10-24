package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_first_aid_kit extends SuperObject {
    public OBJ_first_aid_kit() {
        name = "first_aid_kit";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/clues/first-aid-kit.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
