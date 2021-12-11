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
    public GameLabel livesLabel;
    public GameLabel scoreLabel;
    public GameLabel timeLabel;
    public GameLabel levelLabel;

    public UI(GamePane gp) {
        this.gp = gp;
        this.gc = gp.gc;
        // createSubScenes();
        createButtons();
        createGameInfoLabels();
    }

    // * Buttons
    public void createButtons() {
        createPauseButton();
    }

    private void createPauseButton() {
        // GameButton button = new GameButton("||");

        String BUTTON_STYLE = "-fx-padding: 13 15 7 15;	-fx-font-family: 'Press Start 2P';	-fx-font-size: 20;	-fx-background-color: #0b2364;	-fx-background-radius: 15;-fx-text-fill: #6fe4f9;";

        String BUTTON_HOVER_STYLE = "-fx-padding: 13 15 7 15;	-fx-font-family: 'Press Start 2P';	-fx-font-size: 20;	-fx-background-color: #66addf;	-fx-background-radius: 15;-fx-text-fill: #fff;";

        GameLabel button = new GameLabel("||");
        button.setStyle(BUTTON_STYLE);
        button.setLayoutX(960);
        button.setLayoutY(30);
        // button.getStyleClass().add("game-btn");
        gp.gamePane.getChildren().add(button);
        // button.setOnAction(value);

        button.setOnMouseEntered(e -> button.setStyle(BUTTON_HOVER_STYLE));
        button.setOnMouseExited(e -> button.setStyle(BUTTON_STYLE));
        button.setOnMouseClicked(e -> {
            if (gp.gameState == gp.PLAY_STATE) {
                gp.gameState = gp.PAUSE_STATE;
            } else if (gp.gameState == gp.PAUSE_STATE) {
                gp.gameState = gp.PLAY_STATE;
            }
        });
    }

    // * SubScenes
    private void createSubScenes() {
        gameSettings = new GameSubScene();
        gp.gamePane.getChildren().add(gameSettings);

    }

    // * game info label
    public void createGameInfoLabels() {
        createLivesLabel();
        createScoreLabel();
        createTimeLabel();
        createLevelLabel();
    }

    private void createLivesLabel() {
        livesLabel = new GameLabel("Lives: " + gp.player.lives);
        livesLabel.setLayoutX(40);
        livesLabel.setLayoutY(30);
        gp.gamePane.getChildren().add(livesLabel);
    }

    private void createScoreLabel() {
        scoreLabel = new GameLabel("Score: " + gp.player.score);
        scoreLabel.setLayoutX(300);
        scoreLabel.setLayoutY(30);
        gp.gamePane.getChildren().add(scoreLabel);
    }

    private void createTimeLabel() {
        double levelTimeLeft = gp.levelTimeLimit - gp.levelTimePassed;
        timeLabel = new GameLabel("Time: " + String.format("%.0f", levelTimeLeft));
        timeLabel.setLayoutX(700);
        timeLabel.setLayoutY(30);
        gp.gamePane.getChildren().add(timeLabel);
    }

    private void createLevelLabel() {
        levelLabel = new GameLabel("LEVEL " + gp.Level.getLevel());

        String LEVEL_LABEL_STYLE = "-fx-font-size: 30;-fx-text-fill: #fff;-fx-font-family: 'Press Start 2P';-fx-background-color: transparent;-fx-background-radius: 15;-fx-padding: 13 20 7 20";

        levelLabel.setStyle(LEVEL_LABEL_STYLE);
        levelLabel.setLayoutX(430);
        levelLabel.setLayoutY(530);
        gp.gamePane.getChildren().add(levelLabel);
    }
}
