package views.tiles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.locations.*;
import views.GameView;

import java.io.IOException;

public class BlackMarketView {

    //BlackMarket blackMarket = new BlackMarket();

    @FXML
    public Button fabric, fruit, spice, dices, sweet; // aanmaken fx:id

    @FXML
    private AnchorPane rootPane; // aanmaken fx:id

    public void blackMarket() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/blackMarket/blackMarket.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void fabric() throws IOException {
        //stuk code voor +1 fabric
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../../fxml/tiles/blackMarket/blackMarket2.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void fruit() throws IOException {
        //stuk code voor +1 fabric
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../../fxml/tiles/blackMarket/blackMarket2.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void spice() throws IOException {
        //stuk code voor +1 fabric
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../../fxml/tiles/blackMarket/blackMarket2.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void rollDice() throws IOException {
        //als je geen moskee tegel hebt
        // OF wel een moskee tegel hebt en een reroll hebt gedaan
        // OF wel een moskee tegel hebt en de laagste dice naar een 4 hebt veranderd (HIER MOET LOGICA)
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../../fxml/tiles/blackMarket/blackMarket3.fxml"));
        rootPane.getChildren().setAll(pane);

        //als je wel een moskee tegel hebt (HIER MOET LOGICA)
        AnchorPane pane2 = FXMLLoader.load(getClass().getResource("../../fxml/tiles/blackMarket/blackMarket4.fxml"));
        rootPane.getChildren().setAll(pane2);
    }

    public void acceptReroll() throws IOException {
        //stuk code voor +1 fabric
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../../fxml/tiles/blackMarket/blackMarket3.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void turnToFour() {
        //stuk code om de laagste dice naar een 4 te veranderen
    }

    public void rerollDices() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../../fxml/tiles/blackMarket/blackMarket2.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void close(){
        Stage stage = (Stage) sweet.getScene().getWindow();
        stage.close();

    }



}
