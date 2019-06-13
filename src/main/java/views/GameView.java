package views;

import controllers.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.util.Random;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import observers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameView implements GameViewObserver, Initializable {

    // Variables
    private static GameView gameView;
    private LocationView locationView = LocationView.getInstance();
    private PopUpView popUpView = PopUpView.getInstance();
    private GameController gameController = GameController.getInstance();

    private GameController gameController1 = new GameController();

    // FXML variables
    @FXML public Pane playerblue, playerred, playergreen, playeryellow, playerwhite; // aanmaken fx:id
    @FXML public Pane famblue, famred, famgreen, famyellow, famwhite; // aanmaken fx:id
    @FXML public GridPane grid; // aanmaken fx:id
    @FXML public Button tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11, tile12, tile13, tile14, tile15, tile16; // aanmaken fx:id

    // Starts the game
    public void start() throws Exception {


        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/game.fxml"));
        Parent root1 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
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

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            checkDifficulty();
            TurnManager();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Familieleden op de juiste plek zetten
        grid.setColumnIndex(famred, grid.getColumnIndex(tile12));      grid.setRowIndex(famred, grid.getRowIndex(tile12));
        grid.setColumnIndex(famyellow, grid.getColumnIndex(tile12));   grid.setRowIndex(famyellow, grid.getRowIndex(tile12));
        grid.setColumnIndex(famgreen, grid.getColumnIndex(tile12));    grid.setRowIndex(famgreen, grid.getRowIndex(tile12));
        grid.setColumnIndex(famblue, grid.getColumnIndex(tile12));     grid.setRowIndex(famblue, grid.getRowIndex(tile12));
        grid.setColumnIndex(famwhite, grid.getColumnIndex(tile12));    grid.setRowIndex(famwhite, grid.getRowIndex(tile12));

        //spelers op de juiste plek zetten
        grid.setColumnIndex(playerred, grid.getColumnIndex(tile7));    grid.setRowIndex(playerred, grid.getRowIndex(tile7));
        grid.setColumnIndex(playeryellow, grid.getColumnIndex(tile7)); grid.setRowIndex(playeryellow, grid.getRowIndex(tile7));
        grid.setColumnIndex(playergreen, grid.getColumnIndex(tile7));  grid.setRowIndex(playergreen, grid.getRowIndex(tile7));
        grid.setColumnIndex(playerblue, grid.getColumnIndex(tile7));   grid.setRowIndex(playerblue, grid.getRowIndex(tile7));
        grid.setColumnIndex(playerwhite, grid.getColumnIndex(tile7));  grid.setRowIndex(playerwhite, grid.getRowIndex(tile7));

    }

    // Builds the map based on difficulty
    public void checkDifficulty() throws Exception{
        String diff = gameController.getDifficulty();

        if (diff == "easy") {
            buildEasyMap();
        }if (diff == "hard"){
            buildHardMap();
        }if (diff == "random"){
            buildRandomMap();
        }
    }

    public void buildEasyMap(){
        grid.setColumnIndex(tile1, 2);grid.setRowIndex(tile1, 3);
        grid.setColumnIndex(tile2, 2);grid.setRowIndex(tile2, 0);
        grid.setColumnIndex(tile3, 3);grid.setRowIndex(tile3, 1);
        grid.setColumnIndex(tile4, 0);grid.setRowIndex(tile4, 1);
        grid.setColumnIndex(tile5, 1);grid.setRowIndex(tile5, 0);
        grid.setColumnIndex(tile6, 1);grid.setRowIndex(tile6, 2);
        grid.setColumnIndex(tile7, 2);grid.setRowIndex(tile7, 1);
        grid.setColumnIndex(tile8, 0);grid.setRowIndex(tile8, 2);
        grid.setColumnIndex(tile9, 3);grid.setRowIndex(tile9, 2);
        grid.setColumnIndex(tile10, 1);grid.setRowIndex(tile10, 3);
        grid.setColumnIndex(tile11, 2);grid.setRowIndex(tile11, 2);
        grid.setColumnIndex(tile12, 1);grid.setRowIndex(tile12, 1);
        grid.setColumnIndex(tile13, 0);grid.setRowIndex(tile13, 3);
        grid.setColumnIndex(tile14, 3);grid.setRowIndex(tile14, 0);
        grid.setColumnIndex(tile15, 0);grid.setRowIndex(tile15, 0);
        grid.setColumnIndex(tile16, 3);grid.setRowIndex(tile16, 3);
    }

    public void buildHardMap(){
        grid.setColumnIndex(tile1, 3);grid.setRowIndex(tile1, 2);
        grid.setColumnIndex(tile2, 1);grid.setRowIndex(tile2, 0);
        grid.setColumnIndex(tile3, 0);grid.setRowIndex(tile3, 2);
        grid.setColumnIndex(tile4, 3);grid.setRowIndex(tile4, 1);
        grid.setColumnIndex(tile5, 1);grid.setRowIndex(tile5, 2);
        grid.setColumnIndex(tile6, 2);grid.setRowIndex(tile6, 1);
        grid.setColumnIndex(tile7, 1);grid.setRowIndex(tile7, 1);
        grid.setColumnIndex(tile8, 2);grid.setRowIndex(tile8, 0);
        grid.setColumnIndex(tile9, 1);grid.setRowIndex(tile9, 3);
        grid.setColumnIndex(tile10, 0);grid.setRowIndex(tile10, 3);
        grid.setColumnIndex(tile11, 3);grid.setRowIndex(tile11, 0);
        grid.setColumnIndex(tile12, 2);grid.setRowIndex(tile12, 2);
        grid.setColumnIndex(tile13, 3);grid.setRowIndex(tile13, 3);
        grid.setColumnIndex(tile14, 2);grid.setRowIndex(tile14, 3);
        grid.setColumnIndex(tile15, 0);grid.setRowIndex(tile15, 1);
        grid.setColumnIndex(tile16, 0);grid.setRowIndex(tile16, 0);
    }

    public void buildRandomMap(){
        int[][] locatiesCoordinaten = new int[][]{
                {0, 0}, {1, 0}, {2, 0}, {3, 0},
                {0, 1}, {1, 1}, {2, 1}, {3, 1},
                {0, 2}, {1, 2}, {2, 2}, {3, 2},
                {0, 3}, {1, 3}, {2, 3}, {3, 3}};
        Button[] locatieVariabelen = new Button[]{
                tile1, tile2, tile3, tile4,
                tile5, tile6, tile7, tile8,
                tile9, tile10, tile11, tile12,
                tile13, tile14, tile15, tile16};
        ArrayList<Integer> hadLocations = new ArrayList<>();

        Random rand = new Random();
        for (int i = 0; i < 15; i++){
            int loc = rand.nextInt(15);
            if (!hadLocations.contains(loc)){
                grid.setColumnIndex(locatieVariabelen[i], locatiesCoordinaten[loc][0]);grid.setRowIndex(locatieVariabelen[i], locatiesCoordinaten[loc][1]);
                hadLocations.add(loc);
            } else{
                i = i - 1;
            }
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

    // Popup to confirm if the player wants to move to the selected location
    @FXML
    public void confirmMovement(ActionEvent event) throws IOException {
        if(popUpView.confirmMovement()) {
            Button source = (Button) event.getSource();
            int rowIndex = 0;
            int columnIndex = 0;
            rowIndex = GridPane.getRowIndex(source);
            columnIndex = GridPane.getColumnIndex(source);
            moveTile(playerred, columnIndex, rowIndex);
            if(source.getId().equals("tile8")) {
                blackMarket();
            }
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
    public void greatMosque() throws IOException {
        locationView.greatMosque();
    }
    public void smallMosque() throws IOException {
        locationView.smallMosque();
    }
    public void gemstoneDealer() throws IOException {
        locationView.gemstoneDealer();
    }

    // Closes the game
    public void close(){
        System.exit(0);
    }

    /**
     * This opens the rules so the player can take a look at them.
     * @author Stan
     * @version June 5th, 2019
     * @throws IOException
     */
    public void rulesPage() throws Exception{
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
         * @author       Thomas van Velzen, Stan Hogenboom
         * @version      4 juni 2019
         */
    public void movePlayer(String player, int column, int row) throws IOException {
        if (gameController.movementDone()){
            move(player, column, row);
        }
        else {
            popUpView.winnerScreen();
        }
    }

    /**
     * This is a method that visualizes the movement of the player.
     *
     * @param familyMember Specify the team color of the player who moved. ex: "red" or "green".
     *                     Since the node is only visible and reachable via this class
     *                     I made the parameter a String and used a switch case to reach the right node.
     * @param column       This specifies the column the user moved to.
     * @param row          This specifies the row the user moved to.
     * @author             Thomas van Velzen
     * @version            4 juni 2019
     */
    public void moveFamilyMember(String familyMember, int column, int row) {
        move(familyMember, column, row);
    }

    private void moveTile(Pane pane, int columnm, int row) throws IOException {
        if (!gameController.movementDone()){
            GridPane.setColumnIndex(pane, columnm);
            GridPane.setRowIndex(pane, row);
            gameController.setMoved(true);
        }
        else {
            popUpView.winnerScreen();
        }
    }

    // Move code used by movePlayer() and moveFamilyMember()
    // Put here to remove duplicate code
    private void move(String target, int column, int row){
        switch(target){
            case("red"):
                grid.setColumnIndex(famred, column);
                grid.setRowIndex(famred, row); break;
            case("blue"):
                grid.setColumnIndex(famblue, column);
                grid.setRowIndex(famblue, row); break;
            case("green"):
                grid.setColumnIndex(famgreen, column);
                grid.setRowIndex(famgreen, row); break;
            case("yellow"):
                grid.setColumnIndex(famyellow, column);
                grid.setRowIndex(famyellow, row); break;
            case("white"):
                grid.setColumnIndex(famwhite, column);
                grid.setRowIndex(famwhite, row); break;
        }
    }

    /**
     * This is a method returns the location value of the player.
     *
     * @param player       Specify the team color of the player you want to know the location of. ex: "red" or "green".
     *                     The method returns a location integer -1 which you can convert back into coordinates.
     *                     Example with return value 14: '13 % 4 = 3r1' results in a row/column index of: 3,1 .
     * @author             Thomas van Velzen
     * @version            4 juni 2019
     */
    public int getPlayerPosition(String player) {
        switch(player){
            case("red"):
                int red1 = grid.getColumnIndex(playerred);
                int red2 = grid.getRowIndex(playerred); return red1+red2-1;
            case("yellow"):
                int yel1 = grid.getColumnIndex(playeryellow);
                int yel2 = grid.getRowIndex(playeryellow); return yel1+yel2-1;
            case("green"):
                int gre1 = grid.getColumnIndex(playergreen);
                int gre2 = grid.getRowIndex(playergreen); return gre1+gre2-1;
            case("blue"):
                int blu1 = grid.getColumnIndex(playerblue);
                int blu2 = grid.getRowIndex(playerblue); return blu1+blu2-1;
            case("white"):
                int whi1 = grid.getColumnIndex(playerwhite);
                int whi2 = grid.getRowIndex(playerwhite); return whi1+whi2-1;
            default:break;
        }
        return 17;
    }


    //DISABELEN VAN KNOPPEN ALS HET NIET JE BEURJE IS!!!!!!!!!!!!!!!!!!!!!!!!!
    public void TurnManager() {
            if (gameController.getMyPlayerID() != gameController.TurnManager() && !gameController.getGameEnd()){
                tile1.setDisable(true);  tile2.setDisable(true);  tile3.setDisable(true);  tile4.setDisable(true);
                tile5.setDisable(true);  tile6.setDisable(true);  tile7.setDisable(true);  tile8.setDisable(true);
                tile9.setDisable(true);  tile10.setDisable(true); tile11.setDisable(true); tile12.setDisable(true);
                tile13.setDisable(true); tile14.setDisable(true); tile15.setDisable(true); tile16.setDisable(true);
               // TURNCOUNTER++;

        }
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

    }

    @Override
    public void update(GovernorObservable go) {

    }

    @Override
    public void update(PlayerObservable po) {

    }

    @Override
    public void update(SmugglerObservable so) {

    }


}
