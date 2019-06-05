package views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import observers.*;
import observers.cards.*;
import observers.locations.*;
import views.tiles.BlackMarketView;

import java.io.IOException;

public class GameView implements GameViewObserver {

    BlackMarketView blackMarketView = new BlackMarketView();

    @FXML
    public Button cac; // aanmaken fx:id

    @FXML
    public Button cpp; // aanmaken fx:id

    @FXML
    public Pane playerblue, playerred, playergreen, playeryellow, playerwhite; // aanmaken fx:id

    @FXML
    public Pane famblue, famred, famgreen, famyellow, famwhite; // aanmaken fx:id

    @FXML
    public GridPane grid; // aanmaken fx:id



    public void start() throws IOException {
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

    public void askClose() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/askClose.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void playerProgression() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/playerProgression.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void askConfirmMovement() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/confirmMovement.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


    public void close(){
        System.exit(0);
    }


    public void closeAskClose() throws IOException {
        Stage stage = (Stage) cac.getScene().getWindow();
        stage.close();
    }

    public void closePlayerProg() throws IOException {
        Stage stage = (Stage) cpp.getScene().getWindow();
        stage.close();
    }

    //BLACK MARKET POP UP
    public void blackMarket() throws IOException {
        blackMarketView.blackMarket();

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
            switch(player){
                case("red"):
                    grid.setColumnIndex(playerred, column);
                    grid.setRowIndex(playerred, row); break;
                case("blue"):
                    grid.setColumnIndex(playerblue, column);
                    grid.setRowIndex(playerblue, row); break;
                case("green"):
                    grid.setColumnIndex(playergreen, column);
                    grid.setRowIndex(playergreen, row); break;
                case("yellow"):
                    grid.setColumnIndex(playeryellow, column);
                    grid.setRowIndex(playeryellow, row); break;
                case("white"):
                    grid.setColumnIndex(playerwhite, column);
                    grid.setRowIndex(playerwhite, row); break;
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
        switch(familyMember){
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

    // Dit is de hel, altijd onderaan deze klasse.
    // Blijf weg voor behoud van breincel
    @Override
    public void update(BonusFourMovesObservable bfmo) {

    }

    @Override
    public void update(BonusGainGoodObservable bggo) {

    }

    @Override
    public void update(BonusGemstoneDealerObservable bgdo) {

    }

    @Override
    public void update(BonusGetLiraObservable bglo) {

    }

    @Override
    public void update(BonusPostOfficeObservable bpoo) {

    }

    @Override
    public void update(BonusReturnAssistantObservable brao) {

    }

    @Override
    public void update(BonusReturnMemberObservable brmo) {

    }

    @Override
    public void update(BonusSmallMarketObservable bsmo) {

    }

    @Override
    public void update(BonusSultansPalaceObservable bspo) {

    }

    @Override
    public void update(BonusZeroMovesObservable bzmo) {

    }

    @Override
    public void update(BlackMarketObservable bmo) {

    }

    @Override
    public void update(CaravansaryObservable co) {

    }

    @Override
    public void update(FabricWarehouseObservable fwo) {

    }

    @Override
    public void update(FountainObservable fo) {

    }

    @Override
    public void update(FruitWarehouseObservable fwo) {

    }

    @Override
    public void update(GemstoneDealerObservable gdo) {

    }

    @Override
    public void update(GreatMosqueObservable gmo) {

    }

    @Override
    public void update(LargeMarketObservable lmo) {

    }

    @Override
    public void update(PoliceStationObservable pso) {

    }

    @Override
    public void update(PostOfficeObservable poo) {

    }

    @Override
    public void update(SmallMarketObservable smo) {

    }

    @Override
    public void update(SmallMosqueObservable smo) {

    }

    @Override
    public void update(SpiceWarehouseObservable swo) {

    }

    @Override
    public void update(SultanPalaceObservable spo) {

    }

    @Override
    public void update(TeaHouseObservable tho) {

    }

    @Override
    public void update(WainwrightObservable wo) {

    }

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


