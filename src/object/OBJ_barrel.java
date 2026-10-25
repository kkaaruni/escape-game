package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_barrel extends SuperObject{
    public OBJ_barrel() {
        name = "Barrel";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/clues/barrel.png")));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
