/**
 * Controller for everything that happens while actually playing the game.
 * @author Stan, Thomas
 * @version June 4, 2019
 */

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

    //The functions are called when the player clicks on a tile
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
    @FXML
    public void tileSeven() throws IOException { moveToPosition(7); }
    @FXML
    public void tileEight() throws IOException { moveToPosition(8); }
    @FXML
    public void tileNine() throws IOException { moveToPosition(9); }
    @FXML
    public void tileTen() throws IOException { moveToPosition(10); }
    @FXML
    public void tileEleven() throws IOException { moveToPosition(11); }
    @FXML
    public void tileTwelve() throws IOException { moveToPosition(12); }
    @FXML
    public void tileThirteen() throws IOException { moveToPosition(13); }
    @FXML
    public void tileFourteen() throws IOException { moveToPosition(14); }
    @FXML
    public void tileFifteen() throws IOException { moveToPosition(15); }
    @FXML
    public void tileSixteen() throws IOException { moveToPosition(16); }
}

