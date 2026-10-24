package object;

import java.util.Objects;

public class OBJ_binary extends SuperObject {
    public OBJ_binary() {
        name = "Binary";
        try {
            image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResource("/clues/binary.png")));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
