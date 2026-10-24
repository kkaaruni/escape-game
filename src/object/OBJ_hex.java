package object;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_hex extends SuperObject {
    public OBJ_hex() {
        name = "Hex";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/clues/hex.png")));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

}
