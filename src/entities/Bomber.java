package src.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import src.entities.enemy.EnemyManager;
import src.gui.game.GamePane;
import src.gui.game.KeyHandler;

public class Bomber extends Entity {
    // GamePane gp;
    KeyHandler keyHandler;

    public int hasBomb = 1;
    public int lives = 1;

    int standCounter = 0;

    // public Bomber(int x, int y, Image image) {
    // super(x, y, image);
    // }

    // public Bomber(GamePane gp) {
    // super(gp);
    // }

    public Bomber(GamePane gp, KeyHandler keyHandler) {
        super(gp);
        // this.gp = gamePane;
        this.keyHandler = keyHandler;

        solidArea = new Rectangle(0, 13, 16, 16);

        solidAreaDefaultX = (int) solidArea.getX();
        solidAreaDefaultY = (int) solidArea.getY();

        setDefaultValues();
        getImage();
    }

    public void setDefaultValues() {
        x = gp.tileSize;
        y = gp.tileSize;
        speed = 3;
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
        stand_up = gp.getImage.player_stand_up;
        stand_down = gp.getImage.player_stand_down;
        stand_left = gp.getImage.player_stand_left;
        stand_right = gp.getImage.player_stand_right;
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

            collisionOn = false;
            // check tile collision
            gp.collisionChecker.checkTile(this);
            // check object collision
            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);
            // check enemy collision
            int enemyIndex = gp.collisionChecker.checkEntity(this, gp.enemy);
            if (enemyIndex != 999) {
                Entity enemy = gp.enemy.get(enemyIndex);
                hitEnemy(enemy);
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
                if (spriteNum != 2) {
                    spriteNum = 2;
                } else if (spriteNum != 1) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            standCounter++;
            if (standCounter == 20) {
                spriteNum = 0;
                standCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objName = gp.obj.get(i).name;
            switch (objName) {
                case "speed":
                    speed = 5;
                    gp.obj.remove(i);
                    break;
                case "flames":
                    // speed = 5;
                    System.out.println("flames");
                    gp.obj.remove(i);
                    break;
            }
        }
    }

    public void hitEnemy(Entity entity) {
        if (entity instanceof EnemyManager) {
            EnemyManager enemy = (EnemyManager) entity;
            String enemyName = enemy.name;
            System.out.println(enemyName);
            // switch (enemyName) {
            // case "enemy":
            gp.enemy.remove(enemy);
            lives--;
            System.out.println(lives);
            // break;
            // }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        // Image image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 0) {
                    image = stand_up;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 0) {
                    image = stand_down;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 0) {
                    image = stand_left;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 0) {
                    image = stand_right;
                }
                break;

        }
        gc.drawImage(image, x, y);
    }
}
