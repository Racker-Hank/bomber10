package src.gui.scenes.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import src.gui.game.GamePane;
import src.gui.scenes.loading.loadingController;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
import javafx.scene.control.Button;

public class menuController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane root;

    @FXML
    private Button quit;

    @FXML
    private Button showHighscore;

    @FXML
    private Button showInstruction;

    @FXML
    private Button showSettings;

    @FXML
    private Button startGame;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // load loading scene
        if (!loadingController.isSplashLoaded) {
            loadSplashScene();
        }
    }

    public void loadSplashScene() {
        try {
            AnchorPane loadingPane = FXMLLoader.load(getClass().getResource("/src/gui/scenes/loading/loading.fxml"));
            root.getChildren().setAll(loadingPane);

            // fade in
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(5.5), loadingPane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), loadingPane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();
            loadingController.isSplashLoaded = true;

            // fade out
            fadeIn.setOnFinished(e -> {
                fadeOut.play();
            });

            // load menu scene
            fadeOut.setOnFinished(e -> {
                try {
                    AnchorPane parentContent = FXMLLoader
                            .load(getClass().getResource("/src/gui/scenes/menu/menu.fxml"));
                    root.getChildren().setAll(parentContent);
                } catch (IOException e1) {
                    System.out.println(e);
                }
            });

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void startGame() {
        // try {
        // // AnchorPane parentContent = FXMLLoader
        // // .load(getClass().getResource("/src/gui/scenes/level1/level1_map.fxml"));
        // // // root.getChildren().setAll(parentContent);
        // // Stage stage;
        // // stage = (Stage) root.getScene().getWindow();
        // // stage.setScene(new Scene(parentContent));
        // } catch (IOException e) {
        // System.out.println(e);
        // }
        Stage primaryStage;
        primaryStage = (Stage) root.getScene().getWindow();
        GamePane gamePane = new GamePane();
        // primaryStage.hide();
        // gamePane.setupGame();
        primaryStage.setScene(gamePane.gameScene);
    }

    public void quit() {
        System.exit(0);
    }
}