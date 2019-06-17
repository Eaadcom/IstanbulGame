package views;

import controllers.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.*;

import javafx.scene.layout.*;
import javafx.scene.text.Text;
import models.Difficulty;
import models.Game;
import models.Player;
import models.cards.BonusCard;
import observers.*;

import java.io.IOException;
import java.net.URL;

import views.tiles.*;

import static models.Difficulty.EASY;
import static models.Difficulty.MEDIUM;

public class GameView implements GameViewObserver, Initializable {

    // Variables
    private static GameView gameView;
    private LocationView locationView = LocationView.getInstance();
    private PopUpView popUpView = PopUpView.getInstance();
    private GameController gameController = GameController.getInstance();
    List<Button> tiles = new ArrayList<>();


    // Locatie views
    private SmallMarketView smallMarketView = SmallMarketView.getInstance();
    private LargeMarketView largeMarketView = LargeMarketView.getInstance();
    private GemstoneDealerView gemstoneDealerView = GemstoneDealerView.getInstance();
    private SmallMosqueView smallMosqueView = SmallMosqueView.getInstance();
    private GreatMosqueView greatMosqueView = GreatMosqueView.getInstance();
    private SultansPalaceView sultansPalaceView = SultansPalaceView.getInstance();
    private PostOfficeView postOfficeView = PostOfficeView.getInstance();

    // Waarom wordt hier een tweede gamecontroller aangemaakt ???
    private GameController gameController1 = new GameController();

    // FXML variables
    @FXML
    public Pane playerblue, playerred, playergreen, playerYellow, playerwhite; // aanmaken fx:id
    @FXML
    public Pane famblue, famred, famgreen, famyellow, famwhite; // aanmaken fx:id
    @FXML
    public GridPane grid; // aanmaken fx:id
    @FXML
    public Button tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11, tile12, tile13, tile14, tile15, tile16; // aanmaken fx:id
    @FXML
    public Text gemprice;
    @FXML
    public Text playerLira;

    private List<BonusCard> bonusCardsHuidigeSpeler = new ArrayList<>();
    private boolean endTurn = false;

    private Button[][] easyMap;
    private Button[][] hardMap;

    // Starts the game
    public void start() throws Exception {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeMaps();

        gameController.initializeGameData();
        checkDifficulty();
        if (!gameController.getDifficulty().equals(MEDIUM)) {
            setPlayersEnFamily();
        }
        turnManager();
        enableAllTiles();

        tiles.addAll(Arrays.asList(tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11,
                tile12, tile13, tile14, tile15, tile16
        ));

        possibleMoves(playerred);


        gameController.registerObservers(this);
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
    }

    private void enableAllTiles() {
        tiles.forEach(button -> button.setDisable(false));
    }

    /**
     * Hier moet code komen zoals in {@link GameView#turnManager()} waarin de buttons
     * van de grid enabled of disabled moeten worden
     *
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

    private void addToGrid(Node node, Node ofNodeIndex) {
        grid.add(node, GridPane.getColumnIndex(ofNodeIndex), GridPane.getRowIndex(ofNodeIndex));
    }

    // Builds the map based on difficulty
    public void checkDifficulty() {
        Difficulty difficulty = gameController.getDifficulty();

        if (difficulty.equals(EASY)) {
            buildEasyMap();
        }
        if (difficulty.equals(Difficulty.HARD)) {
            buildHardMap();
        }
        if (difficulty.equals(Difficulty.RANDOM)) {
            buildRandomMap();
        }
    }

    public void buildEasyMap() {
        buildMapForDifficulty(easyMap);
    }

    public void buildHardMap() {
        buildMapForDifficulty(hardMap);
    }

    private void buildMapForDifficulty(final Button[][] buttonMap) {
        grid.getChildren().clear();
        for (int y = 0; y < buttonMap.length; y++) {
            for (int x = 0; x < buttonMap[y].length; x++) {
                grid.add(buttonMap[y][x], y, x);
            }
        }
    }

    public void buildRandomMap() {
        grid.getChildren().clear();
        List<Button> buttons = Arrays.asList(tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11, tile12, tile13, tile14, tile15, tile16);
        Collections.shuffle(buttons);

        Button[] buttonArray = buttons.toArray(new Button[buttons.size()]);

        int[][] locatiesCoordinaten = new int[][]{
                {0, 0}, {1, 0}, {2, 0}, {3, 0},
                {0, 1}, {1, 1}, {2, 1}, {3, 1},
                {0, 2}, {1, 2}, {2, 2}, {3, 2},
                {0, 3}, {1, 3}, {2, 3}, {3, 3}};

        for (int i = 0; i < 16; i++) {
            grid.add(buttonArray[i], locatiesCoordinaten[i][0], locatiesCoordinaten[i][1]);
        }
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

    // Popup to confirm if the player wants to move to the selected location
    @FXML
    public void confirmMovement(ActionEvent event) throws IOException {
        if (popUpView.confirmMovement()) {
            Button source = (Button) event.getSource();
            int rowIndex = 0;
            int columnIndex = 0;
            rowIndex = GridPane.getRowIndex(source);
            columnIndex = GridPane.getColumnIndex(source);
            moveTile(playerred, columnIndex, rowIndex);

            //showPopupBonusKaarten();

            // moveTile(getCurrentPlayer, columnIndex, rowIndex);
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

            } else if (source.getId().equals("tile7")) {
                fountain();
            } else if (source.getId().equals("tile8")) {
                blackMarket();
            } else if (source.getId().equals("tile9")) {
                teaHouse();
            } else if (source.getId().equals("tile10")) {
                largeMarket();
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
            possibleMoves(playerred);
        }
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

    public void largeMarket() throws IOException {
        largeMarketView.largeMarket();
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
    public void close() {
        System.exit(0);
    }

    /**
     * This opens the rules so the player can take a look at them.
     *
     * @throws IOException
     * @author Stan
     * @version June 5th, 2019
     */
    public void rulesPage() throws Exception {
        popUpView.rulesButton();
    }

    /**
     * This is a method that visualizes the movement of the player.
     *
     * @param player Specify the team color of the player who moved. ex: "red" or "green".
     *               Since the node is only visible and reachable via this class
     *               I made the parameter a String and used a switch case to reach the right node.
     *                   This function also checks if the player has moved already.
     *                   If that's the case the player is not able to move again.
     * @param column This specifies the column the user moved to.
     * @param row    This specifies the row the user moved to.
     * @author Thomas van Velzen, Stan Hogenboom
     * @version 4 juni 2019
     */


    /**
     * This is a method that visualizes the movement of the player.
     *
     * @param familyMember Specify the team color of the player who moved. ex: "red" or "green".
     *                     Since the node is only visible and reachable via this class
     *                     I made the parameter a String and used a switch case to reach the right node.
     * @param column       This specifies the column the user moved to.
     * @param row          This specifies the row the user moved to.
     * @author Thomas van Velzen
     * @version 4 juni 2019
     */
    public void moveFamilyMember(String familyMember, int column, int row) {
        move(familyMember, column, row);
    }

    /**
     * This functions changes the X and Y position of the player on the board.
     * It also checks if the player has moved already
     *
     * @param pane
     * @param columnm
     * @param row
     * @throws IOException
     * @author Stan Hogenboom
     */
    private void moveTile(Pane pane, int columnm, int row) throws IOException {
        if (!gameController.movementDone()) {
            GridPane.setColumnIndex(pane, columnm);
            GridPane.setRowIndex(pane, row);
//            gameController.setMoved(true);
//            disableAllTiles();
        } else {
            popUpView.dontMove();
        }
    }

    // Move code used by movePlayer() and moveFamilyMember()
    // Put here to remove duplicate code
    private void move(String target, int column, int row) {
        switch (target) {
            case ("red"):
                grid.setColumnIndex(famred, column);
                grid.setRowIndex(famred, row);
                break;
            case ("blue"):
                grid.setColumnIndex(famblue, column);
                grid.setRowIndex(famblue, row);
                break;
            case ("green"):
                grid.setColumnIndex(famgreen, column);
                grid.setRowIndex(famgreen, row);
                break;
            case ("yellow"):
                grid.setColumnIndex(famyellow, column);
                grid.setRowIndex(famyellow, row);
                break;
            case ("white"):
                grid.setColumnIndex(famwhite, column);
                grid.setRowIndex(famwhite, row);
                break;
        }
    }

    /**
     * This is a method returns the location value of the player.
     *
     * @param player Specify the team color of the player you want to know the location of. ex: "red" or "green".
     *               The method returns a location integer -1 which you can convert back into coordinates.
     *               Example with return value 14: '13 % 4 = 3r1' results in a row/column index of: 3,1 .
     * @author Thomas van Velzen
     * @version 4 juni 2019
     */
    public int getPlayerPosition(String player) {
        switch (player) {
            case ("red"):
                int red1 = grid.getColumnIndex(playerred);
                int red2 = grid.getRowIndex(playerred);
                return red1 + red2 - 1;
            case ("yellow"):
                int yel1 = grid.getColumnIndex(playerYellow);
                int yel2 = grid.getRowIndex(playerYellow);
                return yel1 + yel2 - 1;
            case ("green"):
                int gre1 = grid.getColumnIndex(playergreen);
                int gre2 = grid.getRowIndex(playergreen);
                return gre1 + gre2 - 1;
            case ("blue"):
                int blu1 = grid.getColumnIndex(playerblue);
                int blu2 = grid.getRowIndex(playerblue);
                return blu1 + blu2 - 1;
            case ("white"):
                int whi1 = grid.getColumnIndex(playerwhite);
                int whi2 = grid.getRowIndex(playerwhite);
                return whi1 + whi2 - 1;
            default:
                break;
        }
        return 17;
    }


    /**
     * Checks if it's your turn and disbales tiles acordingly.
     */
    public void turnManager() {
        if (gameController.getMyPlayerID() != gameController.TurnManager() && !gameController.getGameEnd()) {
            disableAllTiles();
            // TURNCOUNTER++;

        }
    }

    /**
     * Disables all tiles so you can't click on them anymore.
     *
     * @author: Stan Hogenboom
     */
    public void disableAllTiles() {
        for (Button button : tiles) {
            button.setDisable(true);
        }
    }

    private int getDifferenceBetweenNumbers(int value1, int value2) {
        return Math.abs(value1 - value2);
    }

    public void possibleMoves(Node node) {
        int playerrow = grid.getRowIndex(node);
        int playercol = grid.getColumnIndex(node);

        // [1,0] [-1,0] [0,1] [0,-1]

        final int moves = 2;

        for (int i = 0; i < tiles.size(); i++) {
            Button button = tiles.get(i);
            int tilecol = grid.getColumnIndex(button);
            int tilerow = grid.getRowIndex(button);

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
    }

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
        }
    }

    private void updateGameView(Game game) {
//        if (game.isMyTurn()) {
//            // update view (enable disable knoppen)
//        }
    }

    @Override
    public void update(GovernorObservable go) {

    }

    @Override
    public void update(PlayerObservable po) {
        if (po instanceof Player) {
            Player player = (Player) po;
            updatePlayerResources(player);
        }
    }

    private void updatePlayerResources(Player player) {
        playerLira.setText(String.valueOf(player.getLira()));
    }

    @Override
    public void update(SmugglerObservable so) {

    }


    public void useTile(ActionEvent actionEvent) {

    }
}
