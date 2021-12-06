package src.entities.enemy;

import src.entities.Entity;
import src.gui.game.GamePane;

public class EnemyManager extends Entity {
    public String name;

    public EnemyManager(GamePane gp) {
        super(gp);
    }

    @Override
    public void update() {
        setAction();

        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkEntity(this, gp.enemy);
        if (gp.collisionChecker.checkPlayer(this, true)) {
            gp.player.hitEnemy(this);
        }
        // if collision is false , entity can move
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
    }

    // public void update() {
    // setAction();

    // collisionOn = false;
    // gp.collisionChecker.checkTile(this);

    // // if collision is false , player can move
    // if (!collisionOn) {
    // switch (direction) {
    // case "up":
    // System.out.println("up");
    // y -= speed;
    // break;

    // case "down":
    // System.out.println("down");
    // y += speed;
    // break;

    // case "left":
    // System.out.println("left");
    // x -= speed;
    // break;

    // case "right":
    // System.out.println("right");
    // x += speed;
    // break;
    // }
    // }

    // spriteCounter++;
    // if (spriteCounter > 10) {
    // if (spriteNum != 2) {
    // spriteNum = 2;
    // } else if (spriteNum != 1) {
    // spriteNum = 1;
    // }
    // spriteCounter = 0;
    // }
    // }
}
