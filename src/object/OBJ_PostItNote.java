package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_PostItNote extends SuperObject {
    public OBJ_PostItNote() {
        name = "Post It Note";
        description = "The post-it note says : 'Buy Oliver a toy horse.'";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/clues/post-it-note.png")));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
