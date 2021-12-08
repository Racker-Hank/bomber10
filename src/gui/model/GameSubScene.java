package src.gui.model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class GameSubScene extends SubScene {
    private final static String BACKGROUND_IMAGE = "/res/img/UI/blue_panel.png";

    private boolean isHidden;

    public GameSubScene() {
        super(new AnchorPane(), 600, 350);
        prefWidth(600);
        prefHeight(350);
        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 350, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane root = (AnchorPane) this.getRoot();
        root.setBackground(new Background(image));

        setLayoutY(95);
        // isHidden = true;
    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));

        // decide what to move
        transition.setNode(this);

        if (isHidden) {
            transition.setToX(-676);
        } else {
            transition.setToX(0);
        }
        isHidden = !isHidden;

        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }
}
