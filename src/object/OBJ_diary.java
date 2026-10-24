package object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_diary extends SuperObject{
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public OBJ_diary() {
        name = "Closed Diary";
        try{
            image  = ImageIO.read(getClass().getResource("/object/closed-diary.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
