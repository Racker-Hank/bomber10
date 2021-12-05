package src.object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import src.gui.game.GamePane;

public class ObjectManager {
    public GamePane gp;
    public Image image;
    public String name;
    public boolean collision = false;
    // public int worldX, worldY;
    public int x, y;
    public Rectangle solidArea = new Rectangle(0, 0, 32, 32);

    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public ObjectManager(GamePane gp) {
        this.gp = gp;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, x, y);
    }
}
