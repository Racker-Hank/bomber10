package src.entities;

import src.graphics.Sprite;
import src.gui.game.GamePane;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Entity {
    public GamePane gp;
    public int worldX, worldY;
    public int speed;
    public Image up1, up2, down1, down2, left1, left2, right1, right2, dead1, dead2, dead3;

    String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0, 0, 32, 32);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public boolean collisionOn = false;
    int actionLockAction = 0;
    public boolean isDead = false;

    // Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    // Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    // Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity(GamePane gp) {
        this.gp = gp;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public void update() {
    }

}
