package views;

import controllers.GameController;
import controllers.LocationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import observers.LocationViewObserver;
import observers.locations.*;
import views.GameView;
import views.tiles.BlackMarketView;
import views.tiles.GemstoneDealerView;
import views.tiles.TeaHouseView;
import views.tiles.WainwrightView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LocationView implements LocationViewObserver, Initializable {

    // Variables
    private static LocationView locationView;
    private TeaHouseView thv = new TeaHouseView();
    private WainwrightView wwv = new WainwrightView();

    // FXML variables
    @FXML public Button fabric, fruit, spice, dices, sweet, closetn; // aanmaken fx:id
    @FXML private AnchorPane rootPane, rootPane2; // aanmaken fx:id
    @FXML private TextField TeaHouseChoice;
    @FXML private TextField policeStationChoice;
    @FXML private TextField TeaHouseDice;


    // Creates blackmarket popup
    public void blackMarket() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/tiles/blackMarket/blackMarket.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    // Code voor +1 fabric
    public void blackMarketFabric() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/tiles/blackMarket/blackMarket2.fxml"));
        rootPane.getChildren().setAll(pane);
        LocationController.getInstance().BlackMarketChoice(3);
    }

    // Stuk code voor +1 fruit
    public void blackMarketFruit() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/tiles/blackMarket/blackMarket2.fxml"));
        rootPane.getChildren().setAll(pane);
        LocationController.getInstance().BlackMarketChoice(2);

    }

    // Stuk code voor +1 spice
    public void blackMarketSpice() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/tiles/blackMarket/blackMarket2.fxml"));
        rootPane.getChildren().setAll(pane);
        LocationController.getInstance().BlackMarketChoice(1);

    }

    // Function for rolling the dice
    public void blackMarketRollDice() throws IOException {
        Stage stage = (Stage) dices.getScene().getWindow();
        stage.close();
        BlackMarketView.getInstance().BlackMarketResult();
    }

    // Function to close the popup
    public void close(){
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    public void fabricWarehouse() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/tiles/fabricWarehouse.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        LocationController.getInstance().FabricWarehouse();

        //TODO hiervoor observer pattern implementeren
        GameController.getInstance().getGame().board.players.get(
                GameController.getInstance().game.turnCounter).setMaxFabrics();
    }
    public void fruitWarehouse() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/tiles/fruitWarehouse.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        LocationController.getInstance().FruitWarehouse();

        //TODO hiervoor observer pattern implementeren
        GameController.getInstance().getGame().board.players.get(
                GameController.getInstance().game.turnCounter).setMaxFruits();
    }
    public void spiceWarehouse() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/tiles/spiceWarehouse.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        LocationController.getInstance().SpiceWarehouse();


        //TODO hiervoor observer pattern implementeren
        GameController.getInstance().getGame().board.players.get(
                GameController.getInstance().game.turnCounter).setMaxSpices();
    }
    public void teaHouse() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/tiles/teaHouse/teaHouse.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    public void teaHouseChooseNumber() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/tiles/teaHouse/teaHouse2.fxml"));
        rootPane2.getChildren().setAll(pane);

        LocationController.getInstance().setTeaHouseNumber(Integer.parseInt(TeaHouseChoice.getText()));
    }

    public void teaHouseRollDice() throws IOException {
        Stage stage = (Stage) dices.getScene().getWindow();
        stage.close();
        LocationController.getInstance().TeaHouseResult();
        LocationController.getInstance().setTeaHouseDice(Integer.parseInt(LocationController.getInstance().diceResultStr));
        thv.teaHouseResult();}



    public void wainwright() throws IOException {
        wwv.wainwright();
    }

    public void policeStation() throws IOException {

        if (GameController.getInstance().getMyPlayer().familyMember.location == 12) {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/tiles/policeStation.fxml"));
            Parent root = (Parent) fxmlloader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        else {

        }
    }

    public void policeStationAction() throws IOException {
        String pschoice = policeStationChoice.getText();
        close();
        LocationController.getInstance().policeStation(pschoice); //aanroepen functie die familymember verplaatst en daarna de actie uitvoert
    }

    public void policeStationTileNumbers() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/tileNumbers.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    public void ClosePoliceStationTileNumbers(){
        Stage stage = (Stage) closetn.getScene().getWindow();
        stage.close();
    }

    public void gemstoneDealerAction() {
        GemstoneDealerView.getInstance().gemstoneDealerYes();
        close();
    }

    public void fountain() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/tiles/fountain.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    // Singleton Pattern
    public static LocationView getInstance() {
        if (locationView == null) {
            locationView = new LocationView();
        }
        return locationView;
    }

    // Observer Pattern
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
    public void update(GreatMarketObservable lmo) {

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
    public void initialize(URL location, ResourceBundle resources) {

    }
}
