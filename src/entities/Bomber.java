package src.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import src.gui.game.GamePane;
import src.gui.game.KeyHandler;

public class Bomber extends Entity {
    // GamePane gp;
    KeyHandler keyHandler;

    public int hasBomb = 1;

    int standCounter = 0;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    // public Bomber(GamePane gp) {
    // super(gp);
    // }

    public Bomber(GamePane gp, KeyHandler keyHandler) {
        super(gp);
        // this.gp = gamePane;
        this.keyHandler = keyHandler;

        solidArea = new Rectangle();
        solidArea.setLayoutX(3);
        solidArea.setLayoutY(8);
        solidArea.setHeight(20);
        solidArea.setWidth(20);

        solidAreaDefaultX = (int) solidArea.getLayoutX();
        solidAreaDefaultY = (int) solidArea.getLayoutY();

        setDefaultValues();
        getImage();
    }

    public void setDefaultValues() {
        x = gp.tileSize;
        y = gp.tileSize;
        speed = 4;
        direction = "down";
    }

    public void getImage() {
        up1 = gp.getImage.player_up1;
        up2 = gp.getImage.player_up2;
        down1 = gp.getImage.player_down1;
        down2 = gp.getImage.player_down2;
        left1 = gp.getImage.player_left1;
        left2 = gp.getImage.player_left2;
        right1 = gp.getImage.player_right1;
        right2 = gp.getImage.player_right2;
        dead1 = gp.getImage.player_dead1;
        dead2 = gp.getImage.player_dead2;
        dead3 = gp.getImage.player_dead3;
    }

    @Override
    public void update() {
        if (keyHandler.up || keyHandler.down || keyHandler.left || keyHandler.right) {
            if (keyHandler.up) {
                direction = "up";
            } else if (keyHandler.down) {
                direction = "down";
            } else if (keyHandler.left) {
                direction = "left";
            } else if (keyHandler.right) {
                direction = "right";
            }
            // if collision is false , player can move
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        y -= speed;
                        break;

                    case "down":
                        y += speed;
                        break;

                    case "left":
                        x -= speed;
                        break;

                    case "right":
                        x += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            standCounter++;
            if (standCounter == 12) {
                spriteNum = 1;
                standCounter = 0;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        // Image image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    img = up1;
                }
                if (spriteNum == 2) {
                    img = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    img = down1;
                }
                if (spriteNum == 2) {
                    img = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    img = left1;
                }
                if (spriteNum == 2) {
                    img = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    img = right1;
                }
                if (spriteNum == 2) {
                    img = right2;
                }
                break;

        }
        gc.drawImage(img, x, y);
    }
}
