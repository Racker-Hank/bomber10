package src.gui.game;

import javafx.scene.canvas.GraphicsContext;
import src.gui.model.GameButton;
import src.gui.model.GameLabel;
import src.gui.model.GameSubScene;

public class UI {
    GamePane gp;
    GraphicsContext gc;

    public GameSubScene gameSettings;
    public GameSubScene menuSettings;
    public GameSubScene gameLevel;
    public GameSubScene gameOver;
    public GameLabel scoreLabel;
    public GameLabel timeLabel;

    public UI(GamePane gp) {
        this.gp = gp;
        this.gc = gp.gc;
        createSubScenes();
    }

    // * Buttons
    public void createButtons() {

    }

    private void createPauseButton() {
        GameButton button = new GameButton("||");
        button.setLayoutX(1032 - button.getWidth());
        button.setLayoutY(20);
        gp.gamePane.getChildren().add(button);

        // button.setOnAction(value);
    }

    // * SubScenes
    private void createSubScenes() {
        gameSettings = new GameSubScene();
        gp.gamePane.getChildren().add(gameSettings);

    }
}
