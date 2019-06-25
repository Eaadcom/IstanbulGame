package views.tiles;

import controllers.LocationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.locations.SmallMarket;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SmallMarketView implements Initializable {

    @FXML
    private Text jewel, fabric, fruit, spice;
    @FXML
    private TextField choiceFabric, choiceJewel, choiceFruit, choiceSpice;
    @FXML
    private Button none;

    //Singleton
    private static SmallMarketView smallMarketView;
    public static SmallMarketView getInstance() {
        if (smallMarketView == null) {
            smallMarketView = new SmallMarketView();
        }
        return smallMarketView;
    }
    //

    /**
     * Starts the small market popup.
     * @author Thomas van Velzen
     * @version 24-6-2019
     */
    public void smallMarket() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/smallMarket.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /**
     * Updates the price smallmarket price on the board.
     * @author Thomas van Velzen
     * @version 24-6-2019
     */
    public void updatePrice(){
        jewel.setText (Integer.toString(LocationController.getInstance().SMgetJewel()));
        spice.setText (Integer.toString(LocationController.getInstance().SMgetSpice()));
        fabric.setText(Integer.toString(LocationController.getInstance().SMgetFabric()));
        fruit.setText (Integer.toString(LocationController.getInstance().SMgetFruit()));
    }

    /**
     * Updates the price of the small market and triggers the function to confirm the purchase
     * @author Thomas van Velzen
     * @version 24-6-2019
     */
    public void confirmPurchase() throws IOException {
        if(choiceFruit.getText() != null && choiceFabric.getText() != null &&choiceSpice.getText() != null &&choiceJewel.getText() != null ) {
            int fabricvalue = Integer.parseInt(choiceFabric.getText());
            int fruitvalue = Integer.parseInt(choiceFruit.getText());
            int spicevalue = Integer.parseInt(choiceSpice.getText());
            int jewelvalue = Integer.parseInt(choiceJewel.getText());
            LocationController.getInstance().SMconfirmPurchase(fabricvalue, fruitvalue, spicevalue, jewelvalue);
            close();
        }else{
            //doe niks
        }
    }

    /**
     * Closes the small market
     * @author Thomas van Velzen
     * @version 24-6-2019
     */
    public void close(){
        Stage stage = (Stage) none.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatePrice();
    }
}
