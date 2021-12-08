package src.entities;

import src.entities.enemy.EnemyManager;
import src.gui.game.GamePane;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Entity {
    public GamePane gp;
    // public int worldX, worldY;
    public int speed;
    public Image up1, up2, down1, down2, left1, left2, right1, right2, dead1, dead2, dead3, stand_up, stand_down,
            stand_left, stand_right;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int explodeCounter = 0;

    public Rectangle solidArea = new Rectangle(0, 0, 30, 30);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public boolean collisionOn = false;
    public boolean explode = true; // can explode?
    public boolean isExploded = false;
    public boolean isDead = false;

    public int actionLockCounter = 0;

    // Tọa độ X tính từ góc trái trên trong Canvas
    public int x;

    // Tọa độ Y tính từ góc trái trên trong Canvas
    public int y;

    public Image image;

    // Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    // public Entity(int xUnit, int yUnit, Image image) {
    // this.x = xUnit * Sprite.SCALED_SIZE;
    // this.y = yUnit * Sprite.SCALED_SIZE;
    // this.image = image;
    // }

    public Entity(GamePane gp) {
        this.gp = gp;
    }

    public void render(GraphicsContext gc) {
        if (!isExploded) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;

            }
        } else {
            explodeCounter++;
            if (explodeCounter >= 0 && explodeCounter <= 15) {
                image = dead1;
            } else if (explodeCounter > 15 && explodeCounter <= 30) {
                image = dead2;
            } else if (explodeCounter > 30 && explodeCounter <= 45) {
                image = dead3;
            } else {
                image = null;
                if (this instanceof EnemyManager) {
                    gp.tileManager.liveMapTile[this.x / gp.tileSize][this.y / gp.tileSize] = ' ';
                    gp.enemy.remove(this);
                }
            }
        }
        gc.drawImage(image, x, y);
    }

    public void setAction() {
    }

    public void update() {
    }

}
