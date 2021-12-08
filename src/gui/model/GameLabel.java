package src.gui.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class GameLabel extends Label {
    public final String LABEL_STYLE = "-fx-text-fill: #6fe4f9;-fx-font-family: 'Press Start 2P';-fx-background-color: #0b2364;	-fx-background-radius: 15;";

    public GameLabel(String text) {
        setText(text);
        setWrapText(true);
        setStyle(LABEL_STYLE);
        setAlignment(Pos.CENTER);
    }
}
