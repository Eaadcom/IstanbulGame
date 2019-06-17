/**
 * Controller for everything that happens while actually playing the game.
 * @author Stan, Thomas
 * @version June 4, 2019
 */

package controllers;

import models.Difficulty;
import models.Game;
import models.MainMenu;
import models.Player;
import models.cards.BonusCard;
import views.GameView;

import java.util.List;

public class GameController {

    // Variables
    private MenuViewController menuViewController = MenuViewController.getInstance();
    private static GameController gameController;
    private static CardController cardController = CardController.getInstance();
    private static FirebaseController firebaseController = FirebaseController.getInstance();
    public Game game;

    // Get data from other controllers
    public Difficulty getDifficulty() {
        return game.getDifficulty();
//        return menuViewController.getGameDifficulty();
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

    public void setGameName(String gameName) {
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer(){
        return game.getPlayer();
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


    public Player getPlayerCurrentTurn() {
        int playerTurn = TurnManager();
        return game.getPlayers().get(playerTurn);
    }

    public int TurnManager() {
            if (game.turnCounter % game.getPlayerTotal() == 0 && !game.gameEnd) {
                return 1;
            } else if (game.turnCounter % game.getPlayerTotal() == 1 && !game.gameEnd) {
                return 2;
            } else if (game.turnCounter % game.getPlayerTotal() == 2 && !game.gameEnd) {
                return 3;
            } else if (game.turnCounter % game.getPlayerTotal() == 3 && !game.gameEnd) {
                return 4;
            } else if (game.turnCounter % game.getPlayerTotal() == 4 && !game.gameEnd) {
                return 5;
            } else if (game.gameEnd){
                //
            }
        return 6;
    }

    public void setNextTurn() {
        game.turnCounter++; // beter met methode aanroep in game (game.nextTurn() ofzo)
    }

    public Player getCurrentPlayerTurn() {
        return game.getCurrentPlayerTurn();
    }

    public void setNextPlayer() {

    }

    public List<BonusCard> getBonusKaartenVanHuidigeSpeler() {
        return getCurrentPlayerTurn().getBonusKaartenInBezit();
    }



    public void addGekozenKaart(String gekozenKaartId) {
        BonusCard gekozenKaart = cardController.getGekozenKaart(gekozenKaartId);
        gekozenKaart.onUse(game.getPlayer());

    }

    public void endTurn() {
        game.increaseTurnCounter();
    }

    public void registerObservers(GameView gameView) {
        game.register(gameView);
        game.getPlayer().register(gameView);

    }

    public void addPlayer(Player player) {
        game.addPlayer(player);
    }

    public void initializeGameData() {
        MainMenu mainMenu = menuViewController.getMainMenu();
        game = new Game(mainMenu.getGameName(), mainMenu.getPlayerTotal(), Difficulty.fromString(mainMenu.getDifficulty()));
        Player player = new Player(mainMenu.getUsername());
        // nemen aan dat joeri speler 1 is
        player.setLira(2);
        gameController.addPlayer(player);
        firebaseController.createOnlineGame(game);
    }
}


