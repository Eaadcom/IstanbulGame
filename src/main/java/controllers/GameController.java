/**
 * Controller for everything that happens while actually playing the game.
 * @author Stan, Thomas
 * @version June 4, 2019
 */

package controllers;

import models.Game;

public class GameController {

    // Variables
    private MenuViewController menuViewController = MenuViewController.getInstance();
    private static GameController gameController;
    public Game game = new Game();

    // Get data from other controllers
    public String getDifficulty() {
        return menuViewController.getGameDifficulty();
    }

    // Get game end
    public boolean getGameEnd(){
        return game.getGameEnd();
    }
    public int getMyPlayerID(){
        return game.getMyPlayerID();
    }

    // Singleton Pattern
    public static GameController getInstance() {
        if (gameController == null) {
            gameController = new GameController();
        }
        return gameController;
    }

    /**
     *Check in the game model if player has moves already
     * @author Stan Hogenboom
     * @return
     */
    public boolean movementDone() {
        if (!game.hasMoved) {
            return false;
        }
        else {
            return true;
        }
    }

    public void setMoved(Boolean b) {
        game.setHasMoved(b);
    }


    public int TurnManager() {
            if (game.TURNCOUNTER % game.getPlayerTotal() == 0 && !game.gameEnd) {
                return 1;
            } else if (game.TURNCOUNTER % game.getPlayerTotal() == 1 && !game.gameEnd) {
                return 2;
            } else if (game.TURNCOUNTER % game.getPlayerTotal() == 2 && !game.gameEnd) {
                return 3;
            } else if (game.TURNCOUNTER % game.getPlayerTotal() == 3 && !game.gameEnd) {
                return 4;
            } else if (game.TURNCOUNTER % game.getPlayerTotal() == 4 && !game.gameEnd) {
                return 5;
            } else if (game.gameEnd){
                //
            }
        return 6;
    }
}


