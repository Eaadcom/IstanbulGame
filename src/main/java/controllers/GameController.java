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

import java.awt.*;
import java.util.List;
import java.util.Map;

public class GameController {

    // Variables
    private MenuViewController menuViewController = MenuViewController.getInstance();
    private static GameController gameController;
    private static CardController cardController = CardController.getInstance();
    private static FirebaseController firebaseController = FirebaseController.getInstance();
    private static PlayerController playerController = PlayerController.getInstance();
    public Game game;

    // Get data from other controllers
    public Difficulty getDifficulty() {
        return game.getDifficulty();
//        return menuViewController.getGameDifficulty();
    }

    //CONSTRUCTOR
    public GameController(){

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

    public Player getPlayer(){
        return game.getPlayer();
    }

    public boolean getGamePhase(){
        return game.gameStarted;
    }

    public void pauseGame(){
        game.pauseGame();
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
        int turn = (game.getTurnCounter() + game.getPlayerTotal()) % game.getPlayerTotal();
        return turn;
//        if (game.getTurnCounter() % game.getPlayerTotal() == 0 && !game.isGameEnded()) {
//                return 1;
//            } else if (game.getTurnCounter() % game.getPlayerTotal() == 1 && !game.isGameEnded()) {
//                return 2;
//            } else if (game.getTurnCounter() % game.getPlayerTotal() == 2 && !game.isGameEnded()) {
//                return 3;
//            } else if (game.getTurnCounter() % game.getPlayerTotal() == 3 && !game.isGameEnded()) {
//                return 4;
//            } else if (game.getTurnCounter() % game.getPlayerTotal() == 4 && !game.isGameEnded()) {
//                return 5;
//            } else if (game.isGameEnded()){
//                //
//            }
//        return 6;
    }

    public void increaseTurn(){
        game.increaseTurnCounter();
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
        updateGame();
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
        Player player = playerController.createNewPlayer(mainMenu.getUsername());
        game.addInitialPlayer(player);
        firebaseController.createNewGame(game);
    }

    public void updateGame(){
        FirebaseController.getInstance().updateGame(game);
    }

    public void joinGame(QueryDocumentSnapshot document) {
        //Map<String, Object> newData =  firebaseController.getGameDataFromFirebase();
        DocumentSnapshot documentSnapshot = firebaseController.getGameDataFromFirebase();
        MainMenu mainMenu = menuViewController.getMainMenu();
        this.game = new Game(document);
        game.updateFromSnapShot(documentSnapshot);
        int playersJoined = GameController.getInstance().getGame().board.players.size();

        if (playersJoined == 1){ GameController.getInstance().getGame().myPlayerID = 2; }
        if (playersJoined == 2){ GameController.getInstance().getGame().myPlayerID = 3; }
        if (playersJoined == 3){ GameController.getInstance().getGame().myPlayerID = 4; }
        if (playersJoined == 4){ GameController.getInstance().getGame().myPlayerID = 5; }

        Player newPlayer = playerController.createNewPlayer(mainMenu.getUsername());
        game.addPlayer(newPlayer);
        System.out.println(document.getData().get("Players"));
        System.out.println(GameController.getInstance().getGame().board.players.get(0).name);
        updateGame();
    }

    public Player getMyPlayer() {
        return playerController.getMyPlayer();

    }

    public void startWatchForChanges() {
        firebaseController.startWatchForChangesForGame(this.game);
    }

    public void updateGameData(DocumentSnapshot documentSnapshot) {
        game.updateFromSnapShot(documentSnapshot);
    }

    public void startGame() {
        game.startGame();
        firebaseController.updateGame(game);
    }

    public void tilesToModel(List<javafx.scene.control.Button> randomMap) {

    }

    public void tilesToModel(javafx.scene.control.Button[][] buttonMap) {

    }
}


