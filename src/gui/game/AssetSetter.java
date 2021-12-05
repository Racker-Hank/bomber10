package src.gui.game;

import src.object.Flames;
import src.object.Portal;
import src.object.Speed;

public class AssetSetter {
    GamePane gp;

    public AssetSetter(GamePane gp) {
        this.gp = gp;
    }

    public void setObject() {
        for (int col = 0; col < gp.maxWorldCol; col++) {
            for (int row = 0; row < gp.maxWorldRow; row++) {
                if (gp.tileManager.mapTile[col][row] == 'f') {
                    gp.obj.add(new Flames(gp, col, row));
                } else if (gp.tileManager.mapTile[col][row] == 's') {
                    gp.obj.add(new Speed(gp, col, row));
                } else if (gp.tileManager.mapTile[col][row] == 'x') {
                    gp.obj.add(new Portal(gp, col, row));
                }
            }
        }
    }
}
