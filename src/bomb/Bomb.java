package src.bomb;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import src.entities.Entity;
import src.entities.enemy.EnemyManager;
import src.gui.game.GamePane;

public class Bomb {
    public GamePane gp;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public static int bomb_radius = 1;
    public static int toExplodeTime = 120;
    public static int explodeTime = 150;

    public int x;
    public int y;

    public Rectangle solidArea = new Rectangle(0, 0, 30, 30);

    // public Image image;

    // bomb
    public Image bomb, bomb1, bomb2;
    // bomb explode
    public Image bomb_exploded, bomb_exploded1, bomb_exploded2;

    // flame
    public Image flame_mid_ver, flame_mid_ver1, flame_mid_ver2;
    public Image flame_mid_hor, flame_mid_hor1, flame_mid_hor2;
    public Image flame_left_hor, flame_left_hor1, flame_left_hor2;
    public Image flame_right_hor, flame_right_hor1, flame_right_hor2;
    public Image flame_top_ver, flame_top_ver1, flame_top_ver2;
    public Image flame_bot_ver, flame_bot_ver1, flame_bot_ver2;

    public Bomb(GamePane gp) {
        this.gp = gp;
        setImage();
    }

    public Bomb(GamePane gp, int x, int y) {
        this(gp);
        this.x = x * gp.tileSize;
        this.y = y * gp.tileSize;
    }

    public void setImage() {
        bomb = gp.getImage.bomb;
        bomb1 = gp.getImage.bomb1;
        bomb2 = gp.getImage.bomb2;

        bomb_exploded = gp.getImage.bomb_exploded;
        bomb_exploded1 = gp.getImage.bomb_exploded1;
        bomb_exploded2 = gp.getImage.bomb_exploded2;

        flame_mid_ver = gp.getImage.flame_mid_ver;
        flame_mid_ver1 = gp.getImage.flame_mid_ver1;
        flame_mid_ver2 = gp.getImage.flame_mid_ver2;
        flame_mid_hor = gp.getImage.flame_mid_hor;
        flame_mid_hor1 = gp.getImage.flame_mid_hor1;
        flame_mid_hor2 = gp.getImage.flame_mid_hor2;
        flame_left_hor = gp.getImage.flame_left_hor;
        flame_left_hor1 = gp.getImage.flame_left_hor1;
        flame_left_hor2 = gp.getImage.flame_left_hor2;
        flame_right_hor = gp.getImage.flame_right_hor;
        flame_right_hor1 = gp.getImage.flame_right_hor1;
        flame_right_hor2 = gp.getImage.flame_right_hor2;
        flame_top_ver = gp.getImage.flame_top_ver;
        flame_top_ver1 = gp.getImage.flame_top_ver1;
        flame_top_ver2 = gp.getImage.flame_top_ver2;
        flame_bot_ver = gp.getImage.flame_bot_ver;
        flame_bot_ver1 = gp.getImage.flame_bot_ver1;
        flame_bot_ver2 = gp.getImage.flame_bot_ver2;
    }

    public void update() {
        spriteCounter++;
        // System.out.println(spriteCounter);
        if (spriteCounter > 0 && spriteCounter < toExplodeTime) {
            int loop = 3;
            for (int i = 0; i < loop; i++) {
                if (spriteCounter > (toExplodeTime * i / loop) + 0
                        && spriteCounter < (toExplodeTime * i / loop) + (toExplodeTime * 1) / (3 * loop)) {
                    spriteNum = 1;
                } else if (spriteCounter > (toExplodeTime * i / loop) + (toExplodeTime * 1) / (3 * loop)
                        && spriteCounter < (toExplodeTime * i / loop) + (toExplodeTime * 2) / (3 * loop)) {
                    spriteNum = 2;
                } else if (spriteCounter > (toExplodeTime * i / loop) + (toExplodeTime * 2) / (3 * loop)
                        && spriteCounter < (toExplodeTime * i / loop) + (toExplodeTime * 3) / (3 * loop)) {
                    spriteNum = 3;
                }
            }
        } else if (spriteCounter >= toExplodeTime
                && spriteCounter < ((explodeTime - toExplodeTime) * 1) / 3 + toExplodeTime) {
            spriteNum = 4;
        } else if (spriteCounter > ((explodeTime - toExplodeTime) * 1) / 3 + toExplodeTime
                && spriteCounter < ((explodeTime - toExplodeTime) * 2) / 3 + toExplodeTime) {
            spriteNum = 5;
        } else if (spriteCounter > ((explodeTime - toExplodeTime) * 2) / 3 + toExplodeTime
                && spriteCounter < ((explodeTime - toExplodeTime) * 3) / 3 + toExplodeTime) {
            spriteNum = 6;
            // spriteCounter = 0; // light show
        }

    }

    public void render(GraphicsContext gc) {
        // System.out.println(spriteNum);
        if (spriteNum == 1) {
            gc.drawImage(bomb, x, y);
        } else if (spriteNum == 2) {
            gc.drawImage(bomb1, x, y);
        } else if (spriteNum == 3) {
            gc.drawImage(bomb2, x, y);
        } else if (spriteNum == 4) {
            gc.drawImage(bomb_exploded, x, y);
            boolean up = true, down = true, left = true, right = true;
            for (int i = 1; i < bomb_radius; i++) {
                int mid = i * gp.tileSize;
                if (up && checkTile(x, y - mid)) {
                    gc.drawImage(flame_mid_ver, x, y - mid);
                } else {
                    up = false;
                }
                if (down && checkTile(x, y + mid)) {
                    gc.drawImage(flame_mid_ver, x, y + mid);
                } else {
                    down = false;
                }
                if (left && checkTile(x - mid, y)) {
                    gc.drawImage(flame_mid_hor, x - mid, y);
                } else {
                    left = false;
                }
                if (right && checkTile(x + mid, y)) {
                    gc.drawImage(flame_mid_hor, x + mid, y);
                } else {
                    right = false;
                }
            }
            int last = bomb_radius * gp.tileSize;
            if (up && checkTile(x, y - last)) {
                gc.drawImage(flame_top_ver, x, y - last);
            }
            if (down && checkTile(x, y + last)) {
                gc.drawImage(flame_bot_ver, x, y + last);
            }
            if (left && checkTile(x - last, y)) {
                gc.drawImage(flame_left_hor, x - last, y);
            }
            if (right && checkTile(x + last, y)) {
                gc.drawImage(flame_right_hor, x + last, y);
            }
        } else if (spriteNum == 5) {
            gc.drawImage(bomb_exploded1, x, y);
            boolean up = true, down = true, left = true, right = true;
            for (int i = 1; i < bomb_radius; i++) {
                int mid = i * gp.tileSize;
                if (up && checkTile(x, y - mid)) {
                    gc.drawImage(flame_mid_ver1, x, y - mid);
                } else {
                    up = false;
                }
                if (down && checkTile(x, y + mid)) {
                    gc.drawImage(flame_mid_ver1, x, y + mid);
                } else {
                    down = false;
                }
                if (left && checkTile(x - mid, y)) {
                    gc.drawImage(flame_mid_hor1, x - mid, y);
                } else {
                    left = false;
                }
                if (right && checkTile(x + mid, y)) {
                    gc.drawImage(flame_mid_hor1, x + mid, y);
                } else {
                    right = false;
                }
            }
            int last = bomb_radius * gp.tileSize;
            if (up && checkTile(x, y - last)) {
                gc.drawImage(flame_top_ver1, x, y - last);
            }
            if (down && checkTile(x, y + last)) {
                gc.drawImage(flame_bot_ver1, x, y + last);
            }
            if (left && checkTile(x - last, y)) {
                gc.drawImage(flame_left_hor1, x - last, y);
            }
            if (right && checkTile(x + last, y)) {
                gc.drawImage(flame_right_hor1, x + last, y);
            }
        } else if (spriteNum == 6) {
            gc.drawImage(bomb_exploded2, x, y);
            boolean up = true, down = true, left = true, right = true;
            for (int i = 1; i < bomb_radius; i++) {
                int mid = i * gp.tileSize;
                if (up && checkTile(x, y - mid)) {
                    gc.drawImage(flame_mid_ver2, x, y - mid);
                } else {
                    up = false;
                }
                if (down && checkTile(x, y + mid)) {
                    gc.drawImage(flame_mid_ver2, x, y + mid);
                } else {
                    down = false;
                }
                if (left && checkTile(x - mid, y)) {
                    gc.drawImage(flame_mid_hor2, x - mid, y);
                } else {
                    left = false;
                }
                if (right && checkTile(x + mid, y)) {
                    gc.drawImage(flame_mid_hor2, x + mid, y);
                } else {
                    right = false;
                }
            }
            int last = bomb_radius * gp.tileSize;
            if (up && checkTile(x, y - last)) {
                gc.drawImage(flame_top_ver2, x, y - last);
            }
            if (down && checkTile(x, y + last)) {
                gc.drawImage(flame_bot_ver2, x, y + last);
            }
            if (left && checkTile(x - last, y)) {
                gc.drawImage(flame_left_hor2, x - last, y);
            }
            if (right && checkTile(x + last, y)) {
                gc.drawImage(flame_right_hor2, x + last, y);
            }
        }
    }

    public boolean checkTile(int col, int row) {
        // if (col < 0 || col > gp.maxWorldCol || row < 0 || row > gp.maxWorldRow) {
        // return false;
        // }
        // if (gp.tileManager.mapTile[col][row] == '#') {
        // return false;
        // }
        // System.out.println(gp.tileManager.mapTile[col / gp.tileSize][row /
        // gp.tileSize] + "yup");
        col = col / gp.tileSize;
        row = row / gp.tileSize;
        if (col > 0 && col < gp.maxWorldCol && row > 0 & row < gp.maxWorldRow) {
            // if (gp.tileManager.mapTile[col / gp.tileSize][row / gp.tileSize] == ' ') {
            if (gp.tileManager.mapTile[col][row] == ' ' || gp.tileManager.mapTile[col][row] == 'p') {
                return true;
            }
            for (int i = 0; i < gp.bricks.size(); i++) {
                if (col == gp.bricks.get(i).x / gp.tileSize && row == gp.bricks.get(i).y / gp.tileSize) {
                    // if (col == gp.bricks.get(i).x && row == gp.bricks.get(i).y) {
                    gp.bricks.get(i).isExploded = true;
                }
            }
        }
        // return true;
        return false;
    }

    public void checkEntity(Entity entity) {
        entity.solidArea.setX(entity.x + entity.solidArea.getX());
        entity.solidArea.setY(entity.y + entity.solidArea.getY());
        if (entity.solidArea.getBoundsInParent().intersects(this.solidArea.getBoundsInParent())) {
            if (entity instanceof EnemyManager) {
                gp.player.score += 200;
            }
            entity.isExploded = true;
        }
        entity.solidArea.setX(entity.solidAreaDefaultX);
        entity.solidArea.setY(entity.solidAreaDefaultY);
    }

    public void checkEnemy() {
        for (int i = 0; i < gp.enemy.size(); i++) {
            checkEntity(gp.enemy.get(i));
        }
    }

    public void checkPlayer() {
        checkEntity(gp.player);
    }

    // check xem da co bom o vi tri day chua
    public boolean checkBomb(Bomb newBomb) {
        for (int i = 0; i < gp.bombs.size(); i++) {
            if (newBomb.x == gp.bombs.get(i).x && newBomb.y == gp.bombs.get(i).y) {
                return false;
            }
        }
        return true;
    }
}
