package views.tiles;

import controllers.LocationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.locations.*;
import views.GameView;

import java.io.IOException;

public class BlackMarketView {

    //BlackMarket blackMarket = new BlackMarket();

    LocationController locationController;


    @FXML
    public Button fabric, fruit, spice, dices, sweet; // aanmaken fx:id

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
        System.out.println("test4");
        Stage stage = (Stage) fabric.getScene().getWindow();
        stage.close();
        System.out.println("test3");

        //stuk code voor +1 fabric
        locationController.BlackMarketChoice(3);

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/blackMarket/blackMarket2.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage2 = new Stage();
        stage2.initStyle(StageStyle.UNDECORATED);
        stage2.setScene(new Scene(root));
        stage2.initModality(Modality.APPLICATION_MODAL);
        stage2.show();
    }

    public void fruit() throws IOException {
        Stage stage = (Stage) fruit.getScene().getWindow();
        stage.close();
        System.out.println("test4");


        //stuk code voor +1 fruit
        locationController.BlackMarketChoice(2);
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/blackMarket/blackMarket2.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage2 = new Stage();
        stage2.initStyle(StageStyle.UNDECORATED);
        stage2.setScene(new Scene(root));
        stage2.initModality(Modality.APPLICATION_MODAL);
        stage2.show();
    }

    public void spice() throws IOException {
        Stage stage = (Stage) spice.getScene().getWindow();
        stage.close();
        System.out.println("test4");


        //stuk code voor +1 spice
        locationController.BlackMarketChoice(1);

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/blackMarket/blackMarket2.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage2 = new Stage();
        stage2.initStyle(StageStyle.UNDECORATED);
        stage2.setScene(new Scene(root));
        stage2.initModality(Modality.APPLICATION_MODAL);
        stage2.show();
    }

    public void rollDice() throws IOException {
        Stage stage = (Stage) dices.getScene().getWindow();
        stage.close();

            //volgens mij moet hier ook nog wat logica
        locationController.BlackMarketDice();

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/blackMarket/blackMarket3.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage2 = new Stage();
        stage2.initStyle(StageStyle.UNDECORATED);
        stage2.setScene(new Scene(root));
        stage2.initModality(Modality.APPLICATION_MODAL);
        stage2.show();
    }

    public void close() throws IOException {
        Stage stage = (Stage) sweet.getScene().getWindow();
        stage.close();

    }



}
