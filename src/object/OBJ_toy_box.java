package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_toy_box extends SuperObject{
    public OBJ_toy_box() {
        name = "Toy box";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/clues/toy-box.png")));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
