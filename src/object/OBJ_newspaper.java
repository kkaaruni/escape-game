package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_newspaper extends SuperObject{
    public OBJ_newspaper() {
        name = "Newspaper";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/clues/newspaper.png")));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
