package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_computer extends SuperObject{
    public OBJ_computer() {
        name = "Computer";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/clues/computer.png")));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
