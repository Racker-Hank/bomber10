package src.gui.game;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import src.entities.Bomber;
import src.entities.Entity;
import src.entities.enemy.EnemyManager;
// import src.graphics.Sprite;
import src.object.ObjectManager;
import src.tile.TileManager;

public class GamePane {
    public AnchorPane gamePane;
    public Scene gameScene;
    public Stage gameStage;

    // * SCREEN SETTING
    public final int originalTilesSize = 16; // 16x16 tile
    public final int scale = 2;

    public int tileSize = originalTilesSize * scale; // 32
    public int maxScreenCol = 31;
    public int maxScreenRow = 13;
    public int screenWidth = 1072;
    public int screenHeight = 540;

    // * WORLD SETTINGS
    public final int maxWorldCol = 31;
    public final int maxWorldRow = 13;
    public final int worldWidth = tileSize * maxWorldCol; // 992
    public final int worldHeight = tileSize * maxWorldRow; // 416

    // canvas
    public GraphicsContext gc;
    public Canvas canvas;
    public String levelMapPath = "./res/levels/level1Map.txt";

    // key handler
    public KeyHandler keyHandler;

    public GetImage getImage = new GetImage();

    // map
    public TileManager tileManager;
    // objects
    public List<ObjectManager> obj = new ArrayList<ObjectManager>();
    // asset
    public AssetSetter assetSetter;
    // entities
    public Bomber player;
    // enemy
    public List<Entity> enemy = new ArrayList<Entity>();
    // collision checker
    public CollisionChecker collisionChecker;

    // game thread
    public AnimationTimer gameThread;

    // game state
    public int gameState;
    public final int PLAY_STATE = 1;
    public final int PAUSE_STATE = 2;

    public GamePane() {
        // create game pane
        initGamePane();
        initGame();
    }

    public void initGamePane() {
        gamePane = new AnchorPane();
        // gamePane.setStyle(PANE_STYLE);
        gamePane.getStyleClass().add("game-background");

        // create canvas
        canvas = new Canvas(worldWidth, worldHeight);
        AnchorPane.setTopAnchor(canvas, 100.0);
        AnchorPane.setLeftAnchor(canvas, 40.0);
        gc = canvas.getGraphicsContext2D();
        gamePane.getChildren().add(canvas);
        gameScene = new Scene(gamePane, screenWidth, screenHeight);
        gameScene.getStylesheets().add("/src/gui/scenes/scenes.css");
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.setTitle("Game");
    }

    public void initGame() {
        keyHandler = new KeyHandler(this);
        player = new Bomber(this, keyHandler);
        tileManager = new TileManager(this);
        assetSetter = new AssetSetter(this);
        collisionChecker = new CollisionChecker(this);
    }

    public void setupGame(Stage primaryStage) {
        primaryStage.hide();
        gameState = PLAY_STATE;

        // set objects (power ups)
        assetSetter.setObject();
        // set enemies
        assetSetter.setEnemy();

        // game loop
        createGameLoop();

        gameStage.show();
    }

    public void createGameLoop() {
        gameThread = new AnimationTimer() {
            @Override
            public void handle(long now) {
                render();
                update();
            }
        };
        gameThread.start();
    }

    public void update() {
        if (gameState == PLAY_STATE) {
            // player
            player.update();
            // enemy
            for (int i = 0; i < enemy.size(); i++) {
                if (enemy.get(i) != null) {
                    enemy.get(i).update();
                }
            }
        }
        if (gameState == PAUSE_STATE) {
            // nothing
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // draw map
        tileManager.render(gc);

        // draw objects
        for (int i = 0; i < obj.size(); i++) {
            if (obj.get(i) != null) {
                obj.get(i).render(gc);
            }
        }

        // draw enemies
        for (int i = 0; i < enemy.size(); i++) {
            if (enemy.get(i) != null) {
                enemy.get(i).render(gc);
            }
        }

        // draw player
        player.render(gc);
    }

}
