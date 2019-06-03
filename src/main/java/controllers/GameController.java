package controllers;

import views.GameView;
import javafx.fxml.FXML;
import models.Player;
import java.io.IOException;

public class GameController {

    Player playerOne = new Player("yes");

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

    @FXML
    public void moveToPosition(int tileID) throws IOException {
        playerOne.changePosition(tileID);
    }

    @FXML
    public void confirmMovement() throws IOException {
        game.askConfirmMovement();
    }

    //Activated when pressed on a tile
    @FXML
    public void tileOne() throws IOException { moveToPosition(1); }
    @FXML
    public void tileTwo() throws IOException { moveToPosition(2); }
    @FXML
    public void tileThree() throws IOException { moveToPosition(3); }
    @FXML
    public void tileFour() throws IOException { moveToPosition(4); }
    @FXML
    public void tileFive() throws IOException { moveToPosition(5); }
    @FXML
    public void tileSix() throws IOException { moveToPosition(6); }
}

