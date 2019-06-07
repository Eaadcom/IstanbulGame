package views;

import controllers.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import observers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameView implements GameViewObserver, Initializable {

    // Variables
    private static GameView gameView;
    private LocationView locationView = LocationView.getInstance();
    private PopUpView popUpView = PopUpView.getInstance();
    private GameController gameController = GameController.getInstance();

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
    }

    // Work in progress
    public String checkDifficulty() throws Exception{
        String diff = gameController.getDifficulty();

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/game.fxml"));
        System.out.println(tile1);
        //tile1.relocate(2,1);
        //tile2.relocate(1,1);
        grid.setColumnIndex(tile1, 1);
        grid.setColumnIndex(tile2, 0);


        return "";
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
    public void confirmMovement() throws IOException {
        popUpView.confirmMovement();
    }

    //BLACK MARKET POP UP
    public void blackMarket() throws IOException {
        locationView.blackMarket();
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
         * @param column This specifies the column the user moved to.
         * @param row    This specifies the row the user moved to.
         * @author       Thomas van Velzen
         * @version      4 juni 2019
         */
        public void movePlayer(String player, int column, int row) {
            move(player, column, row);
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
                //TURNCOUNTER++

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


