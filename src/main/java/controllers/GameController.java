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

import java.util.List;

public class GameController {

    // Variables
    private MenuViewController menuViewController = MenuViewController.getInstance();
    private static GameController gameController;
    private static CardController cardController = CardController.getInstance();
    private static FirebaseController firebaseController = FirebaseController.getInstance();
    private static PlayerController playerController = PlayerController.getInstance();
    public Game game;

    private boolean myGameStarted = false;

    // Get data from other controllers
    public Difficulty getDifficulty() {
        return game.getDifficulty();
//        return menuViewController.getGameDifficulty();
    }

    //CONSTRUCTOR
    public GameController(){

    }

    /**
     * Methode om te kijken of lokaal de game gestart is
     * @author Joeri
     * @version 12 june
     */
    public boolean isMyGameStarted() {
        return myGameStarted;
    }

    /**
     * Nethode om lokaal de game te starten
     * @author Joeri
     * @param myGameStarted of de game gestart is ja of nee
     * @version 12 june
     */
    public void setMyGameStarted(boolean myGameStarted) {
        this.myGameStarted = myGameStarted;
    }

    public boolean isGameEnded() {
        return game.isGameEnded();
    }
    
    public int getMyPlayerID(){
        return playerController.getMyPlayer().getPlayerID();
    }

    // Singleton Pattern
    public static GameController getInstance() {
        if (gameController == null) {
            gameController = new GameController();
        }
        return gameController;
    }

    /**
     * Methode om de naam van de game op te halen
     * @author Joeri
     * @version 12 june
     */
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

    /**
     * Methode om aan te geven dat de huidige speler bewogen heeft
     * @param hasMoved of de speler bewogen heeft of niet
     * @author Joeri
     * @version 12 june
     */
    public void setHasMoved(boolean hasMoved) {
        game.setHasMoved(hasMoved);
    }

    public boolean hasMoved() {
        return game.isHasMoved();
    }

    public Player getPlayerCurrentTurn() {
        int playerTurn = TurnManager();
        return game.getPlayers().get(playerTurn);
    }


    /**
     * Manages the turns based on the amount of players.
     * @author Thomas van Velzen, Joeri van Duijkeren
     * @version 20-6-2019
     */
    public int TurnManager() {
        int turn = (game.getTurnCounter() + game.getPlayerTotal()) % game.getPlayerTotal();
        return turn;
    }

    public void setNextPlayer() {

    }

    public void addGekozenKaart(String gekozenKaartId) {
        BonusCard gekozenKaart = cardController.getGekozenKaart(gekozenKaartId);
        gekozenKaart.onUse(game.getPlayer());

    }

    public void endTurn() {
        game.increaseTurnCounter();
        game.setHasMoved(false);
        updateGame();
    }

    public void registerGameOrLobbyObserverToGame(GameViewLobbyViewObserver gameView) {
        game.register(gameView);
    }

    public void registerGameViewObserverToPlayer(GameViewObserver gameViewObserver) {
        game.getPlayer().register(gameViewObserver);
    }

    /**
     * Methode om de game data te initialiseren wanneer er een nieuwe game aangemaakt wordt
     * @author Joeri
     * @version 12 june
     */
    public void initializeGameData() {
        MainMenu mainMenu = menuViewController.getMainMenu();
        game = new Game(mainMenu.getGameName(), mainMenu.getPlayerTotal(), Difficulty.fromString(mainMenu.getDifficulty()));
        int myPlayerID = game.getPlayers().size() + 1;
        Player player = playerController.createNewPlayer(mainMenu.getUsername(), myPlayerID);
        game.addInitialPlayer(player);
        firebaseController.createNewGame(game);
    }

    public void updateGame(){
        FirebaseController.getInstance().updateGame(game);
    }

    /**
     * Methode om een game te joinen geselecteerd uit de lijst in de lobbby view
     * @author Joeri
     * @param document het document uit de firebase die geklikt is in de lobby view
     * @version 12 june
     */
    public void joinGame(QueryDocumentSnapshot document) {
        //Map<String, Object> newData =  firebaseController.getGameDataFromFirebase();
        DocumentSnapshot documentSnapshot = firebaseController.getGameDataFromFirebase();
        MainMenu mainMenu = menuViewController.getMainMenu();
        this.game = new Game();
        game.updateFromSnapShot(documentSnapshot);
        int playersJoined = GameController.getInstance().getGame().board.players.size();
        int myPlayerID = playersJoined + 1;

        Player newPlayer = playerController.createNewPlayer(mainMenu.getUsername(), myPlayerID);
        game.addPlayer(newPlayer);
        System.out.println(document.getData().get("Players"));
        System.out.println(GameController.getInstance().getGame().board.players.get(0).name);
        updateGame();
    }

    /**
     * Methode om de eigen speler op te halen
     * @author Joeri
     * @version 12 june
     */
    public Player getMyPlayer() {
        return playerController.getMyPlayer();

    }

    /**
     * Methode om te starten met kijken naar veranderingen in het game document in firebase
     * @author Joeri
     * @version 12 june
     */
    public void startWatchForChanges() {
        firebaseController.startWatchForChangesForGame(this.game);
    }

    /**
     * Methode om game te updaten met informatie uit firebase
     * @author Joeri
     * @param documentSnapshot het document met geupdate data uit firebase
     * @version 12 june
     */
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

    public void updatePlayerTile(String tileString) {
        game.updatePlayerTile(tileString, playerController.getMyPlayer().getPlayerID());
    }
}


