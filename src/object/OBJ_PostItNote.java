package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_PostItNote extends SuperObject {
    public OBJ_PostItNote(String message) {
        name = "Post It Note";
        this.message = message;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/clues/post-it-note.png")));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
