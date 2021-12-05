package src.tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.scene.canvas.GraphicsContext;
import src.gui.game.GamePane;

public class TileManager {
    GamePane gp;
    public Tile[] tile;
    public char[][] mapTile;
    public String levelMapPath;

    public TileManager(GamePane gp) {
        this.gp = gp;
        this.levelMapPath = gp.levelMapPath;

        tile = new Tile[10];

        mapTile = new char[gp.maxWorldCol][gp.maxScreenRow];
        getImage();
        loadMap();
    }

    public void getImage() {
        // grass
        tile[0] = new Tile();
        tile[0].image = gp.getImage.grass;

        // wall
        tile[1] = new Tile();
        tile[1].image = gp.getImage.wall;
        tile[1].collision = true;
        tile[2] = new Tile();
        tile[2].image = gp.getImage.wall1;
        tile[2].collision = true;

        // brick
        tile[3] = new Tile();
        tile[3].image = gp.getImage.brick;
        tile[3].collision = true;
    }

    public void loadMap() {
        try {
            File f = new File(levelMapPath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                    mapTile[col][row] = line.charAt(col);
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void render(GraphicsContext gc) {
        for (int i = 0; i < gp.maxWorldCol; i++) {
            for (int j = 0; j < gp.maxWorldRow; j++) {
                if (j == 0 || j == gp.maxWorldRow - 1 || i == 0 || i == gp.maxWorldCol - 1) {
                    gc.drawImage(tile[1].image, i * gp.tileSize, j * gp.tileSize);
                } else if (mapTile[i][j] == '#') {
                    gc.drawImage(tile[2].image, i * gp.tileSize, j * gp.tileSize);
                } else if (mapTile[i][j] == '*') {
                    gc.drawImage(tile[3].image, i * gp.tileSize, j * gp.tileSize);
                } else {
                    gc.drawImage(tile[0].image, i * gp.tileSize, j * gp.tileSize);
                }
            }
        }
    }
}
