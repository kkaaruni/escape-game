package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_barrel extends SuperObject
{
    public OBJ_barrel()
    {
        name = "barrel";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/clues/barrel.png"));
        } catch (IOException e) {}
    }
}
