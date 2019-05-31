package game.controller;

import game.model.Game;
import javafx.fxml.FXML;

import java.io.IOException;

public class GameController {

    Game game = new Game();

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

