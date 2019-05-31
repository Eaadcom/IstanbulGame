package controllers;

import views.GameView;
import javafx.fxml.FXML;

import java.io.IOException;

public class GameController {

    GameView game = new GameView();

    public void start() throws IOException {
        game.start();
    }

    @FXML
    public void askClose() throws IOException {
        game.askClose();
    }

    @FXML
    public void playerProgression() throws IOException {
        game.playerProgression();
    }
}

