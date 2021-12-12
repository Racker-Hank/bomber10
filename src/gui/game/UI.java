package src.gui.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import src.gui.model.GameButton;
import src.gui.model.GameLabel;
import src.gui.model.GameLabelButton;
import src.gui.model.GameSubScene;

public class UI {
    GamePane gp;
    GraphicsContext gc;
    AnchorPane ap;

    public GameSubScene sceneToHide;

    public GameSubScene gameSettings;
    public GameSubScene menuSettings;
    public GameSubScene gameLevel;
    public GameSubScene gameOver;
    public GameLabel livesLabel;
    public GameLabel scoreLabel;
    public GameLabel timeLabel;
    public GameLabel levelLabel;

    public Font gameFont = Font.font("Poppins", FontWeight.BOLD, 16);
    public List<Message> messages = new ArrayList<Message>();

    public UI(GamePane gp) {
        this.gp = gp;
        this.gc = gp.gc;
        this.ap = gp.gamePane;
        createSubScenes();
        createButtons();
        createGameInfoLabels();
    }

    public UI(AnchorPane ap) {
        this.ap = ap;
    }

    // * InGame Messages
    public class Message {
        public String message = "";
        public int x;
        public int y;
        public Color color;
        public int messageCounter = 0;
        public boolean messageOn = false;

        public Message(String message, int x, int y, Color color) {
            this.message = message;
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    public void showGameMessage(String message, int x, int y, Color color) {
        Message newMessage = new Message(message, x, y, color);
        newMessage.messageOn = true;
        messages.add(newMessage);
    }

    public void render(GraphicsContext gc) {
        for (int i = 0; i < messages.size(); i++) {
            Message m = messages.get(i);
            if (m.messageOn) {
                gc.setFill(m.color);
                gc.setFont(gameFont);
                gc.fillText(m.message, gp.player.x - 30, gp.player.y - (5 + i * 16));
            }
            m.messageCounter++;
            if (m.messageCounter > 90) {
                m.messageOn = false;
                m.messageCounter = 0;
                messages.remove(m);
            }
        }
    }

    // * Buttons
    public void createButtons() {
        createPauseButton();
    }

    public void createPauseButton() {
        GameLabelButton button = new GameLabelButton("||");
        // button.setStyle(BUTTON_STYLE);
        button.setLayoutX(960);
        button.setLayoutY(30);
        button.setPrefWidth(72);
        // button.getStyleClass().add("game-btn");
        ap.getChildren().add(button);
        // button.setOnAction(value);

        button.setOnMouseClicked(e -> {
            if (gp.gameState == gp.PLAY_STATE) {
                gp.gameState = gp.PAUSE_STATE;
                button.setText("▶");
            } else if (gp.gameState == gp.PAUSE_STATE) {
                gp.gameState = gp.PLAY_STATE;
                button.setText("||");
            }
            showSubScene(gameSettings);
        });
    }

    // * SubScenes
    private void createSubScenes() {
        createPauseSubScene();
    }

    public void showSubScene(GameSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        } else {
            subScene.moveSubScene();
            sceneToHide = subScene;
        }
    }

    public void createPauseSubScene() {
        gameSettings = new GameSubScene();
        // gameSettings.setLayoutX((1072 - gameSettings.getWidth()) / 2);
        GameLabelButton exitToMenuButton = new GameLabelButton("Quit");
        GameLabelButton backToGameButton = new GameLabelButton("Back");
        backToGameButton.setOnMouseClicked(e -> {
            gp.gameState = gp.PLAY_STATE;
            showSubScene(gameSettings);
        });
        exitToMenuButton.setOnMouseClicked(e -> {
            try {
                gp.gameThread.stop();
                AnchorPane menu = FXMLLoader.load(getClass().getResource("/src/gui/scenes/menu/menu.fxml"));
                ap.getChildren().setAll(menu);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setSpacing(80);
        AnchorPane.setTopAnchor(buttonContainer, (double) (160));
        AnchorPane.setLeftAnchor(buttonContainer, (double) 0);
        AnchorPane.setRightAnchor(buttonContainer, (double) 0);
        buttonContainer.getChildren().add(exitToMenuButton);
        buttonContainer.getChildren().add(backToGameButton);
        gameSettings.getPane().getChildren().add(buttonContainer);
        ap.getChildren().add(gameSettings);
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
        ap.getChildren().add(livesLabel);
    }

    private void createScoreLabel() {
        scoreLabel = new GameLabel("Score: " + gp.player.score);
        scoreLabel.setLayoutX(300);
        scoreLabel.setLayoutY(30);
        ap.getChildren().add(scoreLabel);
    }

    private void createTimeLabel() {
        double levelTimeLeft = gp.levelTimeLimit - gp.levelTimePassed;
        timeLabel = new GameLabel("Time: " + String.format("%.0f", levelTimeLeft));
        timeLabel.setLayoutX(700);
        timeLabel.setLayoutY(30);
        ap.getChildren().add(timeLabel);
    }

    private void createLevelLabel() {
        levelLabel = new GameLabel("LEVEL " + gp.Level.getLevel());

        String LEVEL_LABEL_STYLE = "-fx-font-size: 30;-fx-text-fill: #fff;-fx-font-family: 'Press Start 2P';-fx-background-color: transparent;-fx-background-radius: 15;-fx-padding: 13 20 7 20";

        levelLabel.setStyle(LEVEL_LABEL_STYLE);
        // levelLabel.setLayoutX(430);
        // levelLabel.setLayoutY(530);
        levelLabel.setEffect(new DropShadow(10, Color.web("#6fe4f9")));

        HBox levelLabelContainer = new HBox();
        levelLabelContainer.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(levelLabelContainer, (double) 530);
        AnchorPane.setLeftAnchor(levelLabelContainer, (double) 40);
        AnchorPane.setRightAnchor(levelLabelContainer, (double) 40);
        levelLabelContainer.getChildren().add(levelLabel);

        ap.getChildren().add(levelLabelContainer);
    }
}
