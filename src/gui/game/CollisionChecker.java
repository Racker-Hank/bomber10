package src.gui.game;

import src.entities.Entity;
import src.object.ObjectManager;

public class CollisionChecker {
    GamePane gp;

    public CollisionChecker(GamePane gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = (int) (entity.x + entity.solidArea.getX());
        int entityRightWorldX = (int) (entity.x + entity.solidArea.getX() + entity.solidArea.getWidth());
        int entityTopWorldY = (int) (entity.y + entity.solidArea.getY());
        int entityBottomWorldY = (int) (entity.y + entity.solidArea.getY() + entity.solidArea.getHeight());

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;

                if (gp.tileManager.mapTile[entityLeftCol][entityTopRow] == '#') {
                    tileNum1 = 1;
                } else if (gp.tileManager.mapTile[entityLeftCol][entityTopRow] == '*') {
                    tileNum1 = 3;
                } else {
                    tileNum1 = 0;
                }

                if (gp.tileManager.mapTile[entityRightCol][entityTopRow] == '#') {
                    tileNum2 = 1;
                } else if (gp.tileManager.mapTile[entityRightCol][entityTopRow] == '*') {
                    tileNum2 = 3;
                } else {
                    tileNum2 = 0;
                }

                if (gp.tileManager.tile[tileNum1].collision == true
                        || gp.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;

                if (gp.tileManager.mapTile[entityLeftCol][entityBottomRow] == '#') {
                    tileNum1 = 1;
                } else if (gp.tileManager.mapTile[entityLeftCol][entityBottomRow] == '*') {
                    tileNum1 = 3;
                } else {
                    tileNum1 = 0;
                }

                if (gp.tileManager.mapTile[entityRightCol][entityBottomRow] == '#') {
                    tileNum2 = 1;
                } else if (gp.tileManager.mapTile[entityRightCol][entityBottomRow] == '*') {
                    tileNum2 = 3;
                } else {
                    tileNum2 = 0;
                }

                if (gp.tileManager.tile[tileNum1].collision == true
                        || gp.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;

                if (gp.tileManager.mapTile[entityLeftCol][entityTopRow] == '#') {
                    tileNum1 = 1;
                } else if (gp.tileManager.mapTile[entityLeftCol][entityTopRow] == '*') {
                    tileNum1 = 3;
                } else {
                    tileNum1 = 0;
                }

                if (gp.tileManager.mapTile[entityLeftCol][entityBottomRow] == '#') {
                    tileNum2 = 1;
                } else if (gp.tileManager.mapTile[entityLeftCol][entityBottomRow] == '*') {
                    tileNum2 = 3;
                } else {
                    tileNum2 = 0;
                }

                if (gp.tileManager.tile[tileNum1].collision == true
                        || gp.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;

                if (gp.tileManager.mapTile[entityRightCol][entityTopRow] == '#') {
                    tileNum1 = 1;
                } else if (gp.tileManager.mapTile[entityRightCol][entityTopRow] == '*') {
                    tileNum1 = 3;
                } else {
                    tileNum1 = 0;
                }

                if (gp.tileManager.mapTile[entityRightCol][entityBottomRow] == '#') {
                    tileNum2 = 1;
                } else if (gp.tileManager.mapTile[entityRightCol][entityBottomRow] == '*') {
                    tileNum2 = 3;
                } else {
                    tileNum2 = 0;
                }

                if (gp.tileManager.tile[tileNum1].collision == true
                        || gp.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean isPlayer) {
        int index = 999;

        for (int i = 0; i < gp.obj.size(); i++) {
            if (gp.obj.get(i) != null) {
                // get entity's solid area position
                entity.solidArea.setX(entity.x + entity.solidArea.getX());
                entity.solidArea.setY(entity.y + entity.solidArea.getY());

                // get object's solid area position
                gp.obj.get(i).solidArea.setX(gp.obj.get(i).x + gp.obj.get(i).solidArea.getX());
                gp.obj.get(i).solidArea.setY(gp.obj.get(i).y + gp.obj.get(i).solidArea.getY());

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.setY(entity.solidArea.getY() - entity.speed);
                        if (entity.solidArea.getBoundsInParent()
                                .intersects(gp.obj.get(i).solidArea.getBoundsInParent())) {
                            if (gp.obj.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;

                    case "down":
                        entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                        if (entity.solidArea.getBoundsInParent()
                                .intersects(gp.obj.get(i).solidArea.getBoundsInParent())) {
                            if (gp.obj.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;

                    case "left":
                        entity.solidArea.setX(entity.solidArea.getX() - entity.speed);
                        if (entity.solidArea.getBoundsInParent()
                                .intersects(gp.obj.get(i).solidArea.getBoundsInParent())) {
                            if (gp.obj.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;

                    case "right":
                        entity.solidArea.setX(entity.solidArea.getX() + entity.speed);
                        if (entity.solidArea.getBoundsInParent()
                                .intersects(gp.obj.get(i).solidArea.getBoundsInParent())) {
                            if (gp.obj.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.setX(entity.solidAreaDefaultX);
                entity.solidArea.setY(entity.solidAreaDefaultY);
                gp.obj.get(i).solidArea.setX(gp.obj.get(i).solidAreaDefaultX);
                gp.obj.get(i).solidArea.setY(gp.obj.get(i).solidAreaDefaultY);
            }
        }
        return index;
    }
}
