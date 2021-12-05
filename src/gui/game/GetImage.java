package src.gui.game;

import javafx.scene.image.Image;
import src.graphics.Sprite;

public class GetImage {
    // player
    public Image player_up1, player_up2, player_down1, player_down2, player_left1, player_left2, player_right1,
            player_right2, player_dead1, player_dead2, player_dead3, player_stand_up, player_stand_down,
            player_stand_left, player_stand_right;

    // bolloom
    public Image balloom_up1, balloom_up2, balloom_down1, balloom_down2, balloom_left1, balloom_left2, balloom_right1,
            balloom_right2, balloom_dead1;

    // oneal
    public Image oneal_up1, oneal_up2, oneal_down1, oneal_down2, oneal_left1, oneal_left2, oneal_right1, oneal_right2,
            oneal_dead1;

    // tiles
    public Image grass, wall, wall1;
    public Image brick, brick_exploded, brick_exploded1, brick_exploded2;

    // objects
    public Image portal, powerup_bombs, powerup_flames, powerup_speed, powerup_wallpass, powerup_detonator,
            powerup_bombpass,
            powerup_flamepass;

    public GetImage() {
        getImageFromSprite();
    }

    public void getImageFromSprite() {

        // player
        player_up1 = Sprite.player_up_1.getFxImage();
        player_up2 = Sprite.player_up_2.getFxImage();
        player_down1 = Sprite.player_down_1.getFxImage();
        player_down2 = Sprite.player_down_2.getFxImage();
        player_left1 = Sprite.player_left_1.getFxImage();
        player_left2 = Sprite.player_left_2.getFxImage();
        player_right1 = Sprite.player_right_1.getFxImage();
        player_right2 = Sprite.player_right_2.getFxImage();
        player_dead1 = Sprite.player_dead1.getFxImage();
        player_dead2 = Sprite.player_dead2.getFxImage();
        player_dead3 = Sprite.player_dead3.getFxImage();
        player_stand_up = Sprite.player_up.getFxImage();
        player_stand_down = Sprite.player_down.getFxImage();
        player_stand_left = Sprite.player_left.getFxImage();
        player_stand_right = Sprite.player_right.getFxImage();

        // balloom
        balloom_up1 = Sprite.balloom_left1.getFxImage();
        balloom_up2 = Sprite.balloom_right1.getFxImage();
        balloom_down1 = Sprite.balloom_right1.getFxImage();
        balloom_down2 = Sprite.balloom_left1.getFxImage();
        balloom_left1 = Sprite.balloom_left2.getFxImage();
        balloom_left2 = Sprite.balloom_left3.getFxImage();
        balloom_right1 = Sprite.balloom_right2.getFxImage();
        balloom_right2 = Sprite.balloom_right3.getFxImage();
        balloom_dead1 = Sprite.balloom_dead.getFxImage();

        // oneal
        oneal_up1 = Sprite.oneal_left1.getFxImage();
        oneal_up2 = Sprite.oneal_right1.getFxImage();
        oneal_down1 = Sprite.oneal_right1.getFxImage();
        oneal_down2 = Sprite.oneal_left1.getFxImage();
        oneal_left1 = Sprite.oneal_left2.getFxImage();
        oneal_left2 = Sprite.oneal_left3.getFxImage();
        oneal_right1 = Sprite.oneal_right2.getFxImage();
        oneal_right2 = Sprite.oneal_right3.getFxImage();
        oneal_dead1 = Sprite.oneal_dead.getFxImage();

        // tiles
        grass = Sprite.grass.getFxImage();
        wall = Sprite.wall.getFxImage();
        wall1 = Sprite.wall1.getFxImage();
        brick = Sprite.brick.getFxImage();
        brick_exploded = Sprite.brick_exploded.getFxImage();
        brick_exploded1 = Sprite.brick_exploded1.getFxImage();
        brick_exploded2 = Sprite.brick_exploded2.getFxImage();

        // objects
        portal = Sprite.portal.getFxImage();

    }
}
