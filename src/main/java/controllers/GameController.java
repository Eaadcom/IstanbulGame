/**
 * Controller for everything that happens while actually playing the game.
 * @author Stan, Thomas
 * @version June 4, 2019
 */

package controllers;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import models.Difficulty;
import models.Game;
import models.MainMenu;
import models.Player;
import models.cards.BonusCard;
import observers.GameViewLobbyViewObserver;
import observers.GameViewObserver;
import views.GameView;

import java.util.List;
import java.util.Map;

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

    public boolean isGameEnded() {
        return game.isGameEnded();
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
            if (game.getTurnCounter() % game.getPlayerTotal() == 0 && !game.isGameEnded()) {
                return 1;
            } else if (game.getTurnCounter() % game.getPlayerTotal() == 1 && !game.isGameEnded()) {
                return 2;
            } else if (game.getTurnCounter() % game.getPlayerTotal() == 2 && !game.isGameEnded()) {
                return 3;
            } else if (game.getTurnCounter() % game.getPlayerTotal() == 3 && !game.isGameEnded()) {
                return 4;
            } else if (game.getTurnCounter() % game.getPlayerTotal() == 4 && !game.isGameEnded()) {
                return 5;
            } else if (game.isGameEnded()){
                //
            }
        return 6;
    }

    public void setNextTurn() {
        game.increaseTurnCounter();
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

    public void registerGameOrLobbyObserverToGame(GameViewLobbyViewObserver gameView) {
        game.register(gameView);
    }

    public void registerGameViewObserverToPlayer(GameViewObserver gameViewObserver) {
        game.getPlayer().register(gameViewObserver);
    }

    public void addPlayer(Player player) {
        game.addPlayer(player);
    }

    public void initializeGameData() {
        MainMenu mainMenu = menuViewController.getMainMenu();
        game = new Game(mainMenu.getGameName(), mainMenu.getPlayerTotal(), Difficulty.fromString(mainMenu.getDifficulty()));
        Player player = new Player(mainMenu.getUsername());
        gameController.addPlayer(player);
        firebaseController.createOnlineGame(game);
    }

    public void joinGame(QueryDocumentSnapshot document) {
        MainMenu mainMenu = menuViewController.getMainMenu();
        this.game = new Game(document);
        Player player = new Player(mainMenu.getUsername());
        gameController.addPlayer(player);
        firebaseController.updateGame(game);
    }

    public void startWatchForChanges() {
        firebaseController.startWatchForChangesForGame(this.game);
    }

    public void updateGameData(DocumentSnapshot documentSnapshot) {
        game.updateFromSnapShot(documentSnapshot);
    }


}


