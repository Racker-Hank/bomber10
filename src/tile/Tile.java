package src.tile;

import javafx.scene.image.Image;

public class Tile {

    public Image image;
    public boolean collision = false;
    public boolean explode = false;
    public boolean isExploded = false;

    public int x;
    public int y;

    public Tile() {
    }

    public Tile(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public void update() {
    }

}
