package views.tiles;

import controllers.GameController;
import controllers.LocationController;
import controllers.PlayerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.locations.BlackMarket;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the view for the black market
 * @author Floris Dekker, Thomas van Velzen
 * @version 24-6-2019
 */
public class BlackMarketView implements Initializable {
    private LocationController locationController = LocationController.getInstance();
    @FXML
    public Text diceBlackMarket;
    @FXML
    public Text jewelsBlackMarket;

    private static BlackMarketView blackMarketView;


    public void BlackMarketResult() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/blackMarket/blackMarket3.fxml"));
        Parent root = fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void close(){
        Stage stage = (Stage) diceBlackMarket.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int number = BlackMarket.getInstance().rollDice();
        diceBlackMarket.setText(String.valueOf(number));

        if (number<7){
            jewelsBlackMarket.setText(String.valueOf(0));

        } else if(number == 7 || number == 8){
            jewelsBlackMarket.setText(String.valueOf(1));
            BlackMarket.getInstance().addJewel(1, GameController.getInstance().getGame().board.players.get(
                    GameController.getInstance().game.turnCounter));
            //PlayerController.getInstance().CargoCheckJewels(1);
        } else if(number == 9 || number == 10){
            jewelsBlackMarket.setText(String.valueOf(2));
            BlackMarket.getInstance().addJewel(2, GameController.getInstance().getGame().board.players.get(
                    GameController.getInstance().game.turnCounter));
            //PlayerController.getInstance().CargoCheckJewels(2);
        } else if(number == 11 || number == 12){
            jewelsBlackMarket.setText(String.valueOf(3));
            BlackMarket.getInstance().addJewel(3, GameController.getInstance().getGame().board.players.get(
                    GameController.getInstance().game.turnCounter));
            //PlayerController.getInstance().CargoCheckJewels(3);
        }

    }

    // Singleton Pattern
    public static BlackMarketView getInstance() {
        if (blackMarketView == null) {
            blackMarketView = new BlackMarketView();
        }
        return blackMarketView;
    }
}
