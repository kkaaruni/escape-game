package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_table extends SuperObject{
    public OBJ_table() {
        name = "Table";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/clues/table.png")));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}