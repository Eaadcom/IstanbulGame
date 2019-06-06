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
    private Game game = new Game();

    // Get data from other controllers
    public String getDifficulty(){
        return menuViewController.getGameDifficulty();
    }

    // Singleton Pattern
    public static GameController getInstance() {
        if (gameController == null) {
            gameController = new GameController();
        }
        return gameController;
    }
}
