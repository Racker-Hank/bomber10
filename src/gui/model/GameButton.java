package src.gui.model;

import javafx.scene.control.Button;

public class GameButton extends Button {
    private final String BUTTON_STYLE = "-fx-padding: 13 20 7 20;	-fx-font-family: 'Press Start 2P';	-fx-font-size: 16;	-fx-background-color: #0b2364;	-fx-background-radius: 15;";

    public GameButton(String text) {
        super(text);
        this.setStyle(BUTTON_STYLE);
    }
}
