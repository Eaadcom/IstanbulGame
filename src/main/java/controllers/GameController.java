/**
 * Controller for everything that happens while actually playing the game.
 * @author Stan, Thomas
 * @version June 4, 2019
 */

package controllers;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import views.GameView;
import javafx.fxml.FXML;
import models.Player;
import java.io.IOException;

public class GameController {

    PlayerController playerController = new PlayerController();

    Player playerOne = new Player("yes");

    GameView game = new GameView();

    public void start() throws IOException {
        game.start();
    }

    public void askClose() throws IOException {
        game.askClose();
    }

    public void playerProgression() throws IOException {
        game.playerProgression();
    }

    public void moveToPosition(int tileID) throws IOException {
        playerController.changePosition(tileID);
    }

    public void confirmMovement() throws IOException {
        game.askConfirmMovement();
    }

    //The functions are called when the player clicks on a tile
    public void tileOne() throws IOException { moveToPosition(1); }

    public void tileTwo() throws IOException { moveToPosition(2); }

    public void tileThree() throws IOException { moveToPosition(3); }

    public void tileFour() throws IOException { moveToPosition(4); }

    public void tileFive() throws IOException { moveToPosition(5); }

    public void tileSix() throws IOException { moveToPosition(6); }

    public void tileSeven() throws IOException { moveToPosition(7); }

    public void tileEight() throws IOException { moveToPosition(8); }

    public void tileNine() throws IOException { moveToPosition(9); }

    public void tileTen() throws IOException { moveToPosition(10); }

    public void tileEleven() throws IOException { moveToPosition(11); }

    public void tileTwelve() throws IOException { moveToPosition(12); }

    public void tileThirteen() throws IOException { moveToPosition(13); }

    public void tileFourteen() throws IOException { moveToPosition(14); }

    public void tileFifteen() throws IOException { moveToPosition(15); }

    public void tileSixteen() throws IOException { moveToPosition(16); }
}
