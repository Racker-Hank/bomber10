package src.tile;

import javafx.scene.image.Image;

public class Brick extends Tile {
    public Brick(int x, int y, Image image) {
        super(x, y, image);
        explode = true;
        collision = true;
    }

    public Brick() {
        explode = true;
        collision = true;
    }

    // @Override
    // public void update() {

    // }
}
