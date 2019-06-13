package views;

import controllers.LocationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.locations.*;
import observers.LocationViewObserver;
import observers.locations.*;
import views.GameView;
import views.tiles.TeaHouseView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LocationView implements LocationViewObserver, Initializable {

    // Variables
    private static LocationView locationView;
    private LocationController locationController = LocationController.getInstance();
    private TeaHouseView thv = new TeaHouseView();

    // FXML variables
    @FXML
    public Button fabric, fruit, spice, dices, sweet, closetn; // aanmaken fx:id
    @FXML
    private AnchorPane rootPane, rootPane2; // aanmaken fx:id

    @FXML
    private TextField TeaHouseChoice;

    @FXML
    private TextField TeaHouseDice;


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
        locationController.BlackMarketChoice(3);
    }

    // Stuk code voor +1 fruit
    public void blackMarketFruit() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/tiles/blackMarket/blackMarket2.fxml"));
        rootPane.getChildren().setAll(pane);
        locationController.BlackMarketChoice(2);

    }

    // Stuk code voor +1 spice
    public void blackMarketSpice() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/tiles/blackMarket/blackMarket2.fxml"));
        rootPane.getChildren().setAll(pane);
        locationController.BlackMarketChoice(1);

    }

    // Function for rolling the dice
    public void blackMarketRollDice() throws IOException {
        //locationController.BlackMarketDice();
        //als je geen moskee tegel hebt
        // OF wel een moskee tegel hebt en een reroll hebt gedaan
        // OF wel een moskee tegel hebt en de laagste dice naar een 4 hebt veranderd (HIER MOET LOGICA)
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/tiles/blackMarket/blackMarket3.fxml"));
        rootPane.getChildren().setAll(pane);

        //als je wel een moskee tegel hebt (HIER MOET LOGICA)
        AnchorPane pane2 = FXMLLoader.load(getClass().getResource("../fxml/tiles/blackMarket/blackMarket4.fxml"));
        rootPane.getChildren().setAll(pane2);

        locationController.BlackMarketDice();
    }

    // Function to do a reroll
    public void acceptRerollBlackMarket() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/tiles/blackMarket/blackMarket3.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    // Function to change one of your dice rolls to 4
    public void turnToFourBlackMarket() {
        //stuk code om de laagste dice naar een 4 te veranderen
    }

    // Function to do a reroll
    public void blackMarketRerollDices() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/tiles/blackMarket/blackMarket2.fxml"));
        rootPane.getChildren().setAll(pane);
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
        locationController.FabricWarehouse();
    }
    public void fruitWarehouse() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/tiles/fruitWarehouse.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        locationController.FruitWarehouse();
    }
    public void spiceWarehouse() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/tiles/spiceWarehouse.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        locationController.SpiceWarehouse();
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

        locationController.setTeaHouseNumber(Integer.parseInt(TeaHouseChoice.getText()));
    }

    public void teaHouseRollDice() throws IOException {
        // als je geen moskee tegel hebt
        // OF wel een moskee tegel hebt en een reroll hebt gedaan
        // OF wel een moskee tegel hebt en de laagste dice naar een 4 hebt veranderd (HIER MOET LOGICA)
        locationController.TeaHouseResult();
        locationController.setTeaHouseDice(Integer.parseInt(locationController.diceResultStr));
        thv.teaHouseResult();}


        //System.out.println((locationController.getTeaHouseNumber()));


        //als je wel een moskee tegel hebt (HIER MOET LOGICA)
        /*
        AnchorPane pane2 = FXMLLoader.load(getClass().getResource("../fxml/tiles/teaHouse/teaHouse4.fxml"));
        rootPane2.getChildren().setAll(pane2);*/

    public void wainwright() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/tiles/wainwright.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void policeStation() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/tiles/policeStation.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
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
    public void initialize(URL location, ResourceBundle resources) {

    }
}
