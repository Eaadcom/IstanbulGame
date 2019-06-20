package views;

import controllers.GameController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.util.*;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Difficulty;
import models.FamilyMember;
import models.Game;
import models.Player;
import models.cards.BonusCard;
import models.locations.GemstoneDealer;
import models.locations.SultanPalace;
import observers.*;

import java.io.IOException;
import java.net.URL;

import observers.locations.GemstoneDealerObservable;
import observers.locations.SultanPalaceObservable;
import views.tiles.*;
import views.tiles.sultansPalace.SultansPalaceView;

import static models.Difficulty.EASY;
import static models.Difficulty.MEDIUM;

public class GameView implements GameViewObserver, Initializable {

    // Variables
    private static GameView gameView;
    private LocationView locationView = LocationView.getInstance();
    private PopUpView popUpView = PopUpView.getInstance();
    private GameController gameController = GameController.getInstance();
    List<Button> tiles = new ArrayList<>();
    private Stage stage;

    // Locatie views
    private SmallMarketView smallMarketView = SmallMarketView.getInstance();
    private GreatMarketView greatMarketView = GreatMarketView.getInstance();
    private GemstoneDealerView gemstoneDealerView = GemstoneDealerView.getInstance();
    private SmallMosqueView smallMosqueView = SmallMosqueView.getInstance();
    private GreatMosqueView greatMosqueView = GreatMosqueView.getInstance();
    private SultansPalaceView sultansPalaceView = SultansPalaceView.getInstance();
    private PostOfficeView postOfficeView = PostOfficeView.getInstance();

    // FXML variables
    @FXML
    public Pane playerblue, playerred, playergreen, playerYellow, playerwhite; // aanmaken fx:id
    @FXML
    public Pane famblue, famred, famgreen, famyellow, famwhite; // aanmaken fx:id
    @FXML
    public Pane redAs, blueAs, greenAs, yellowAs, whiteAss;
    @FXML
    public GridPane grid; // aanmaken fx:id
    @FXML
    public Button tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11, tile12, tile13, tile14, tile15, tile16; // aanmaken fx:id
    @FXML
    public Text gemPrice;
    @FXML
    public Text playerLira, playerRubies, playerFabrics, playerFruits, playerSpices, playerJewels;
    @FXML
    public Text SultanRed, SultanBlue, SultanYellow, SultanGreen, SultanChoice;
    @FXML
    public Text maxFruit, maxSpice, maxJewel, maxFabric;

    private List<BonusCard> bonusCardsHuidigeSpeler = new ArrayList<>();
    private boolean endTurn = false;

    private Button[][] easyMap;
    private Button[][] hardMap;
    private Button[][] mediumMap;
    private List<Button> randomMap;
    private Pane[] players;
    private Pane[] family;
    private Pane[] assistants;

    public GameView() {
        GameView.gameView = this;
    }

    // Starts the game
    public void start() throws Exception {
        Platform.runLater(
                () -> {
                    try {
                        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/game.fxml"));
                        Parent root1 = fxmlloader.load();
                        if (stage == null) {
                            stage = new Stage();
                        }
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.setTitle("Istanbul");
                        stage.setScene(new Scene(root1));
                        stage.setMaximized(true);

                        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                        stage.setX(primaryScreenBounds.getMinX());
                        stage.setY(primaryScreenBounds.getMinY());
                        stage.setWidth(primaryScreenBounds.getWidth());
                        stage.setHeight(primaryScreenBounds.getHeight());
                        stage.show();


                        //Sultans palace
                        SultanRed = (Text) root1.lookup("#SultanRed");
                        SultanBlue = (Text) root1.lookup("#SultanBlue");
                        SultanYellow = (Text) root1.lookup("#SultanYellow");
                        SultanGreen = (Text) root1.lookup("#SultanGreen");
                        SultanChoice = (Text) root1.lookup("#SultanChoice");

                        //player values
                        playerLira = (Text) root1.lookup("#playerLira");
                        playerRubies = (Text) root1.lookup("#playerRubies");
                        playerFabrics = (Text) root1.lookup("#playerFabrics");
                        playerFruits = (Text) root1.lookup("#playerFruits");
                        playerSpices = (Text) root1.lookup("#playerSpices");
                        playerJewels = (Text) root1.lookup("#playerJewels");
                        maxFabric = (Text) root1.lookup("#maxFabric");
                        maxFruit = (Text) root1.lookup("#maxFruit");
                        maxSpice = (Text) root1.lookup("#maxSpice");
                        maxJewel = (Text) root1.lookup("#maxJewel");

                        gemPrice = (Text) root1.lookup("#gemPrice");

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException ie) {
                        //helemaal niks
                    }
                }
        );
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeMaps();
        initializePlayers();
        initializeFamily();
        updatePlayerResources(GameController.getInstance().getPlayer());
        initializeAssistants();

        checkDifficulty();
        if (!gameController.getDifficulty().equals(MEDIUM)) {
            setPlayersEnFamily();
        }

        checkAmountOfPlayers();

        tiles.addAll(Arrays.asList(tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11,
                tile12, tile13, tile14, tile15, tile16
        ));

        if(isMyTurn()) {
            enableLocationsForMyPlayer();
        } else {
            disableTiles(true);
        }

//        turnManager();

        gameController.registerGameOrLobbyObserverToGame(this);
        gameController.registerGameViewObserverToPlayer(this);
    }

    private Pane enableLocationsForMyPlayer() {
        Pane myPane = null;
        List<Player> players = gameController.getGame().getPlayers();
        String myPlayerName = gameController.getMyPlayer().getName();
        int playersSize = players.size();
        if (playersSize > 4){ playersSize = 4;}
        for (int i = 0; i < playersSize; i++) {
            if (players.get(i).getName().equals(myPlayerName)) {
                myPane = this.players[i];
                possibleMoves(myPane);
            }
        }
        return myPane;
    }

    private boolean isMyTurn() {
        return gameController.getMyPlayer().getName().equals(gameController.getPlayerCurrentTurn().getName());
    }

    private void checkAmountOfPlayers() {
        for (Pane playerPane : players) {
            playerPane.setVisible(false);
        }
        for (Pane familyPane : family) {
            familyPane.setVisible(false);
        }
        int amountOfPlayers = gameController.getGame().getPlayers().size();
        if (amountOfPlayers > 4){amountOfPlayers = 4;}
        for (int i = 0; i < amountOfPlayers; i++) {
            players[i].setVisible(true);
            family[i].setVisible(true);
        }
    }

    private void initializeFamily() {
        family = new Pane[]{famred, famyellow, famgreen, famblue, famwhite};
    }

    private void initializePlayers() {
        players = new Pane[]{playerred, playerYellow, playergreen, playerblue, playerwhite};
    }

    private void initializeAssistants(){
        assistants = new Pane[] {redAs, blueAs, greenAs, yellowAs, whiteAss};
    }
    private void initializeMaps() {
        easyMap = new Button[][]{{tile15, tile4, tile8, tile13},
                {tile5, tile12, tile6, tile10},
                {tile2, tile7, tile11, tile1},
                {tile14, tile3, tile9, tile16}};

        hardMap = new Button[][]{{tile16, tile15, tile3, tile10},
                {tile2, tile7, tile5, tile9},
                {tile8, tile6, tile12, tile14},
                {tile11, tile4, tile1, tile13}};

//        mediumMap = new Button[][]{{tile1, tile2, tile3, tile4},
//                {tile5, tile6, tile7, tile8},
//                {tile9, tile10, tile11, tile12},
//                {tile13, tile14, tile15, tile16}};
    }

    private void disableAllTiles() {
        tiles.forEach(button -> button.setDisable(false));
    }

    /**
     * Hier moet code komen zoals in {@link GameView#turnManager()} waarin de buttons
     * van de grid enabled of disabled moeten worden
     * @param currentPlayerTurn Player
     */
    private void manageGameFieldIconen(Player currentPlayerTurn) {

    }

    private void setPlayersEnFamily() {
        //Familieleden op de juiste plek zetten
        addToGrid(famred, tile12);
        addToGrid(famyellow, tile12);
        addToGrid(famgreen, tile12);
        addToGrid(famwhite, tile12);
        addToGrid(famblue, tile12);

        //spelers op de juiste plek zetten
        addToGrid(playerred, tile7);
        addToGrid(playerYellow, tile7);
        addToGrid(playergreen, tile7);
        addToGrid(playerblue, tile7);
        addToGrid(playerwhite, tile7);
    }

    private void setAssistants(int tile, String color){
        switch(tile){
            case 1:
                if(color == "red") {
                    addToGrid(redAs, tile8);
                } else if (color == "blue") {
                    addToGrid(blueAs, tile8);
                } else if (color == "green"){
                    addToGrid(greenAs, tile8);
                } else if (color == "yellow"){
                    addToGrid(yellowAs, tile8);
                } else if (color == "white"){
                    addToGrid(whiteAss, tile8);
                }

        }
    }

    private void addToGrid(Node node, Node ofNodeIndex) {
        grid.add(node, GridPane.getColumnIndex(ofNodeIndex), GridPane.getRowIndex(ofNodeIndex));
    }


    // Builds the map based on difficulty
    public void checkDifficulty() {
        Difficulty difficulty = gameController.getDifficulty();

        if (difficulty.equals(EASY)) {
            buildMapForDifficulty(easyMap);
        }
        if (difficulty.equals(Difficulty.HARD)) {
            buildMapForDifficulty(hardMap);
        }
        if (difficulty.equals(Difficulty.RANDOM)) {
            buildRandomMap();
        }
//        if (difficulty.equals(Difficulty.MEDIUM)){
//            buildMapForDifficulty(mediumMap);
//        }
    }

    private void buildMapForDifficulty(final Button[][] buttonMap) {
        grid.getChildren().clear();
        for (int y = 0; y < buttonMap.length; y++) {
            for (int x = 0; x < buttonMap[y].length; x++) {
                grid.add(buttonMap[y][x], y, x);
            }
        }
        GameController.getInstance().tilesToModel(buttonMap);
    }

    public void buildRandomMap() {
        grid.getChildren().clear();
        List<Button> buttons = Arrays.asList(tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11, tile12, tile13, tile14, tile15, tile16);
        Collections.shuffle(buttons);
        randomMap = buttons;

        Button[] buttonArray = buttons.toArray(new Button[buttons.size()]);

        int[][] locatiesCoordinaten = new int[][]{
                {0, 0}, {1, 0}, {2, 0}, {3, 0},
                {0, 1}, {1, 1}, {2, 1}, {3, 1},
                {0, 2}, {1, 2}, {2, 2}, {3, 2},
                {0, 3}, {1, 3}, {2, 3}, {3, 3}};

        for (int i = 0; i < 16; i++) {
            grid.add(buttonArray[i], locatiesCoordinaten[i][0], locatiesCoordinaten[i][1]);
        }
        GameController.getInstance().tilesToModel(randomMap);
    }

    // Closes popups
    public void askClose() throws IOException {
        popUpView.askClose();
    }

    // Popup to show the progression of an enemy player
    public void playerProgression() throws IOException {
        popUpView.playerProgression();
    }

    // Popup to show the progression of an enemy player
    public void endTurn() throws IOException {
        if (popUpView.endTurn()) {
            gameController.endTurn();
        }
    }

    public void bonusCards() throws IOException {
        String gekozenKaart = popUpView.bonusCards();
        gameController.addGekozenKaart(gekozenKaart);
    }

    /**
     * Handles the movement of the player.
     * @author Stan Hogenboom
     * @version 18-6-2019
     * @param event
     * @throws IOException
     */
    @FXML
    public void confirmMovement(ActionEvent event) throws IOException {
        if (popUpView.confirmMovement()) {
            Button source = (Button) event.getSource();
            Pane pane = findPlayerColor(gameController.getMyPlayerID(), true); //finds out what the color of the player is and tells it's a player, not a familymember

            int rowIndex = 0; // gives a default value
            int columnIndex = 0;
            rowIndex = GridPane.getRowIndex(source);
            columnIndex = GridPane.getColumnIndex(source);
            moveTile(pane, columnIndex, rowIndex);

            if (source.getId().equals("tile1")) {
                wainwright();
            } else if (source.getId().equals("tile2")) {
                fabricWarehouse();
            } else if (source.getId().equals("tile3")) {
                spiceWarehouse();
            } else if (source.getId().equals("tile4")) {
                fruitWarehouse();
            } else if (source.getId().equals("tile5")) {
                postOffice();
            } else if (source.getId().equals("tile6")) {
                //Geen functie omdat dit de eigen tegel is
            } else if (source.getId().equals("tile7")) {
                fountain();
            } else if (source.getId().equals("tile8")) {
                blackMarket();
            } else if (source.getId().equals("tile9")) {
                teaHouse();
            } else if (source.getId().equals("tile10")) {
                greatMarket();
            } else if (source.getId().equals("tile11")) {
                smallMarket();
            } else if (source.getId().equals("tile12")) {
                policeStation();
            } else if (source.getId().equals("tile13")) {
                sultansPalace();
            } else if (source.getId().equals("tile14")) {
                smallMosque();
            } else if (source.getId().equals("tile15")) {
                greatMosque();
            } else if (source.getId().equals("tile16")) {
                gemstoneDealer();
            }

            gameController.setNextPlayer();
            possibleMoves(pane);
            disableTiles(true);
        }
    }

    public void setColumnRow(Button button) {
        int column = GridPane.getColumnIndex(button);
        int row = GridPane.getRowIndex(button);
    }

    public void movePoliceStation(int tileNumber) throws IOException {

        Pane familyMember = findPlayerColor(gameController.getMyPlayerID(), false);

        if (tileNumber == 1) {
            wainwright();
            GridPane.setColumnIndex(familyMember, GridPane.getColumnIndex(tile1));
            GridPane.setRowIndex(familyMember, GridPane.getRowIndex(tile1));
        }
        else if (tileNumber == 2) {
            fabricWarehouse();
            GridPane.setColumnIndex(familyMember, GridPane.getColumnIndex(tile2));
            GridPane.setRowIndex(familyMember, GridPane.getRowIndex(tile2));
        }
        else if (tileNumber == 3) {
            spiceWarehouse();
            GridPane.setColumnIndex(familyMember, GridPane.getColumnIndex(tile3));
            GridPane.setRowIndex(familyMember, GridPane.getRowIndex(tile3));
        }
        else if (tileNumber == 4) {
            fruitWarehouse();
            GridPane.setColumnIndex(familyMember, GridPane.getColumnIndex(tile4));
            GridPane.setRowIndex(familyMember, GridPane.getRowIndex(tile4));
        }
        else if (tileNumber == 5) {
            postOffice();
            GridPane.setColumnIndex(familyMember, GridPane.getColumnIndex(tile5));
            GridPane.setRowIndex(familyMember, GridPane.getRowIndex(tile5));
        }
        else if (tileNumber == 6) {
            //PUT CARAVANSERY HERE WHEN READY
            GridPane.setColumnIndex(familyMember, GridPane.getColumnIndex(tile6));
            GridPane.setRowIndex(familyMember, GridPane.getRowIndex(tile6));
        }
        else if (tileNumber == 7) {
            fountain();
            GridPane.setColumnIndex(familyMember, GridPane.getColumnIndex(tile7));
            GridPane.setRowIndex(familyMember, GridPane.getRowIndex(tile7));
        }
        else if (tileNumber == 8) {
            blackMarket();
            GridPane.setColumnIndex(familyMember, GridPane.getColumnIndex(tile8));
            GridPane.setRowIndex(familyMember, GridPane.getRowIndex(tile8));
        }
        else if (tileNumber == 9) {
            teaHouse();
            GridPane.setColumnIndex(familyMember, GridPane.getColumnIndex(tile9));
            GridPane.setRowIndex(familyMember, GridPane.getRowIndex(tile9));
        }
        else if (tileNumber == 10) {
            greatMarket();
            GridPane.setColumnIndex(familyMember, GridPane.getColumnIndex(tile10));
            GridPane.setRowIndex(familyMember, GridPane.getRowIndex(tile10));
        }
        else if (tileNumber == 11) {
            smallMarket();
            GridPane.setColumnIndex(familyMember, GridPane.getColumnIndex(tile1));
            GridPane.setRowIndex(familyMember, GridPane.getRowIndex(tile11));
        }
        else if (tileNumber == 12) {
            /* This is the policesttion itself, probably shouldn't be included*/
        }
        else if (tileNumber == 13) {
            sultansPalace();
            GridPane.setColumnIndex(familyMember, GridPane.getColumnIndex(tile13));
            GridPane.setRowIndex(familyMember, GridPane.getRowIndex(tile13));
        }
        else if (tileNumber == 14) {
            smallMosque();
            GridPane.setColumnIndex(familyMember, GridPane.getColumnIndex(tile14));
            GridPane.setRowIndex(familyMember, GridPane.getRowIndex(tile14));
        }
        else if (tileNumber == 15) {
            greatMosque();
            GridPane.setColumnIndex(familyMember, GridPane.getColumnIndex(tile15));
            GridPane.setRowIndex(familyMember, GridPane.getRowIndex(tile15));
        }
        else if (tileNumber == 16) {
            gemstoneDealer();
            GridPane.setColumnIndex(familyMember, GridPane.getColumnIndex(tile16));
            GridPane.setRowIndex(familyMember, GridPane.getRowIndex(tile16));
        }
        
    }

    /**
     * finds out the color of the player so other methods know which character to move
     * @author Stan Hogenboom
     * @version 18-6-2019
     * @param playerID
     * @return
     */
    private Pane findPlayerColor(int playerID, boolean player) {
        Pane pane = playerred; //makes sure it's initialized
            switch (playerID) {
                case 1:
                    if (player) { pane = playerred; }
                    else { pane = famred; }
                    break;
                case 2:
                    if (player) { pane = playerYellow; }
                    else { pane = famyellow; }
                    break;
                case 3:
                    if (player) { pane = playergreen; }
                    else { pane = famgreen; }
                    break;
                case 4:
                    if (player) { pane = playerblue; }
                    else { pane = famblue; }
                    break;
                case 5:
                    if (player) { pane = playerwhite; }
                    else { pane = famwhite; }
                    break;
            }
        return pane;
    }

    //TILE POP UPS
    public void blackMarket() throws IOException {
        locationView.blackMarket();
    }

    public void fabricWarehouse() throws IOException {
        locationView.fabricWarehouse();
    }

    public void fruitWarehouse() throws IOException {
        locationView.fruitWarehouse();
    }

    public void spiceWarehouse() throws IOException {
        locationView.spiceWarehouse();
    }

    public void teaHouse() throws IOException {
        locationView.teaHouse();
    }

    public void wainwright() throws IOException {
        locationView.wainwright();
    }

    public void policeStation() throws IOException {
        locationView.policeStation();
    }

    public void fountain() throws IOException {
        locationView.fountain();
    }
    public void greatMosque() throws IOException {
        greatMosqueView.greatMosque();
    }

    public void smallMosque() throws IOException {
        smallMosqueView.smallMosque();
    }

    public void gemstoneDealer() throws IOException {
        gemstoneDealerView.gemstoneDealer();
    }

    public void greatMarket() throws IOException {
        greatMarketView.greatMarket();
    }

    public void smallMarket() throws IOException {
        smallMarketView.smallMarket();
    }

    public void sultansPalace() throws IOException {
        sultansPalaceView.sultansPalace();
    }
    public void postOffice() throws IOException {
        postOfficeView.postOffice();
    }



    // Closes the game
    //TODO this function is not used anywhere
    public void close() {
        System.exit(0);
    }

    /**
     * This opens the rules so the player can take a look at them.
     *
     * @throws IOException
     * @author Stan Hogenboom
     * @version June 5th, 2019
     */
    public void rulesPage() throws Exception {
        popUpView.rulesButton();
    }

    /**
     * This functions changes the X and Y position of the player on the board.
     * It also checks if the player has moved already
     * @author Stan Hogenboom
     * @param pane
     * @param column
     * @param row
     * @throws IOException
     */
    private void moveTile(Pane pane, int column, int row) throws IOException {


        if(!gameController.movementDone()) {
            GridPane.setColumnIndex(pane, column);
            GridPane.setRowIndex(pane, row);

            //gameController.setMoved(true);
            //disableAllTiles();
        }
        else {
            popUpView.dontMove();
        }
    }

    /**
     * Checks if it's your turn and disbales tiles acordingly.
     */
    public void turnManager() {
        if (gameController.getMyPlayerID() != gameController.TurnManager() && !gameController.isGameEnded()) {
            disableTiles(false);
            // TURNCOUNTER++;
            //GameController.getInstance().increaseTurn();
        }
    }

    /**
     * Disables all tiles so you can't click on them anymore.
     *
     * @author: Stan Hogenboom
     */
    public void disableTiles(boolean disabled) {
        for (Button button : tiles) {
            button.setDisable(disabled);
        }
    }

    private int getDifferenceBetweenNumbers(int value1, int value2) {
        return Math.abs(value1 - value2);
    }


    public void possibleMoves(Node node) {
        int playerrow = GridPane.getRowIndex(node);
        int playercol = GridPane.getColumnIndex(node);

        // [1,0] [-1,0] [0,1] [0,-1]

        final int moves = 4;

        for (int i = 0; i < tiles.size(); i++) {
            Button button = tiles.get(i);
            int tilecol = GridPane.getColumnIndex(button);
            int tilerow = GridPane.getRowIndex(button);

            int colDifference = getDifferenceBetweenNumbers(tilecol, playercol);
            int rowDifference = getDifferenceBetweenNumbers(tilerow, playerrow);

            int totalDifference = colDifference + rowDifference;
            if (totalDifference == 0) {
                button.setDisable(true);
            } else if (totalDifference <= moves) {
                button.setDisable(false);
            } else {
                button.setDisable(true);
            }

        }
    }

//        for(int i = 0; i<tiles.size(); i++){
//            int tilecol = grid.getColumnIndex(tiles.get(i));
//            int tilerow = grid.getRowIndex(tiles.get(i));
//
//            int deltaC = tilecol - playercol;
//            int deltaR = tilerow - playerrow;
//
//            if(tilecol == playercol && tilerow == playerrow){
//                    tiles.get(i).setDisable(true);
//            }
//
//            else if((-1 <= deltaC && deltaC <=1) && (-1 <= deltaR && deltaR <= 1)) {
//                tiles.get(i).setDisable(false);
//            }
//
//            else if((tilecol == playercol && deltaR <= 1) || (tilecol == playercol && -1 <=  deltaR)) {
//                tiles.get(i).setDisable(false);
//            }
//
//            else if((tilerow == playerrow && deltaC <= 1) || (tilerow == playerrow && -1 <=  deltaC)) {
//                tiles.get(i).setDisable(false);
//            }
//
//            else{
//                tiles.get(i).setDisable(true);
//            }
//        }


    /**
     * Gives an int based on the node coordinarions.
     *
     * @author: Thomas van Velzen
     */
    public int getNodeLocation(Node node) {
        return ((grid.getRowIndex(node) * 4) + grid.getColumnIndex(node)) + 1;
    }

    // Singleton Pattern
    public static GameView getInstance() {
        if (gameView == null) {
            gameView = new GameView();
        }
        return gameView;
    }

    // Observer Pattern
    @Override
    public void update(AssistantObservable ao) {

    }

    @Override
    public void update(BoardObservable bo) {

    }

    @Override
    public void update(DiceObservable dO) {

    }

    @Override
    public void update(FamilyMemberObservable fmo) {

    }

    @Override
    public void update(GameObservable go) {
        if (go instanceof Game) {
            Game game = (Game) go;
            updateGameView(game);

            Platform.runLater(new Runnable(){
                @Override public void run() {

                }
            });
        }
    }

    private void updateGameView(Game game) {
        if (isMyTurn()) {
            enableLocationsForMyPlayer();
        }
    }


    @Override
    public void update(GovernorObservable go) {
    }

    @Override
    public void update(SultanPalaceObservable spo){
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                System.out.println(SultanBlue);
                System.out.println(spo.getJewelPrice());
                SultanBlue.setText(String.valueOf(spo.getJewelPrice()));
                SultanRed.setText(String.valueOf(spo.getFabricPrice()));
                SultanGreen.setText(String.valueOf(spo.getSpicePrice()));
                SultanYellow.setText(String.valueOf(spo.getFruitPrice()));
                SultanChoice.setText(String.valueOf(spo.getChoiceAmount()));
            }
        });
    }

    @Override
    public void update(GemstoneDealerObservable gdo) {
        Platform.runLater( () -> {
                System.out.println("vis");
                gemPrice.setText(String.valueOf(gdo.getGemstonePrice()));
            });}




    @Override
    public void update(PlayerObservable po) {
        if (po instanceof Player) {
            Player player = (Player) po;
            updatePlayerResources(player);
        }
    }

    public void updatePlayerResources(Player player) {
        playerLira.setText(String.valueOf(player.getLira()));
        playerRubies.setText(String.valueOf(player.getRubies()));
        playerJewels.setText(String.valueOf(player.getJewels()));
        playerFabrics.setText(String.valueOf(player.getFabrics()));
        playerSpices.setText(String.valueOf(player.getSpices()));
        playerFruits.setText(String.valueOf(player.getFruits()));

        //maxFabric.setText(String.valueOf(player.getMaxFabrics()));
        //maxFruit.setText(String.valueOf(player.getMaxFruits()));
        //maxJewel.setText(String.valueOf(player.getMaxJewels()));
        //maxSpice.setText(String.valueOf(player.getMaxSpices()));

    }

    public void updateBoard(Player player){
        playerLira.setText(String.valueOf(player.getLira()));
        playerRubies.setText(String.valueOf(player.getRubies()));
        playerJewels.setText(String.valueOf(player.getJewels()));
        playerFabrics.setText(String.valueOf(player.getFabrics()));
        playerSpices.setText(String.valueOf(player.getSpices()));
        playerFruits.setText(String.valueOf(player.getFruits()));

        maxFabric.setText(String.valueOf(player.getMaxFabrics()));
        maxFruit.setText(String.valueOf(player.getMaxFruits()));
        maxJewel.setText(String.valueOf(player.getMaxJewels()));
        maxSpice.setText(String.valueOf(player.getMaxSpices()));

        SultanBlue.setText(String.valueOf(SultanPalace.getInstance().getJewelPrice()));
        SultanRed.setText(String.valueOf(SultanPalace.getInstance().getFabricPrice()));
        SultanGreen.setText(String.valueOf(SultanPalace.getInstance().getSpicePrice()));
        SultanYellow.setText(String.valueOf(SultanPalace.getInstance().getFruitPrice()));
        SultanChoice.setText(String.valueOf(SultanPalace.getInstance().getChoiceAmount()));
    }

    @Override
    public void update(SmugglerObservable so) {

    }


    public void useTile(ActionEvent actionEvent) {

    }
}
