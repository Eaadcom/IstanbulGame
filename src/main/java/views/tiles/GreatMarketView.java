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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GreatMarketView implements Initializable {

    @FXML
    private Text jewel, fabric, fruit, spice;
    @FXML
    private TextField GMchoiceFabric, GMchoiceJewel, GMchoiceFruit, GMchoiceSpice;
    @FXML
    private Button none;

    //Singleton
    private static GreatMarketView greatMarketView;
    public static GreatMarketView getInstance() {
        if (greatMarketView == null) {
            greatMarketView = new GreatMarketView();
        }
        return greatMarketView;
    }
    //

    public void greatMarket() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/greatMarket.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void updatePrice(){
        jewel.setText (Integer.toString(LocationController.getInstance().GMgetJewel()));
        spice.setText (Integer.toString(LocationController.getInstance().GMgetSpice()));
        fabric.setText(Integer.toString(LocationController.getInstance().GMgetFabric()));
        fruit.setText (Integer.toString(LocationController.getInstance().GMgetFruit()));
    }

    public void confirmPurchase() throws IOException {
        if(GMchoiceFruit.getText() != null && GMchoiceFabric.getText() != null &&GMchoiceSpice.getText() != null &&GMchoiceJewel.getText() != null ) {
            int GMfabricvalue = Integer.parseInt(GMchoiceFabric.getText());
            int GMfruitvalue = Integer.parseInt(GMchoiceFruit.getText());
            int GMspicevalue = Integer.parseInt(GMchoiceSpice.getText());
            int GMjewelvalue = Integer.parseInt(GMchoiceJewel.getText());
            LocationController.getInstance().GMconfirmPurchase(GMfabricvalue, GMfruitvalue, GMspicevalue, GMjewelvalue);
            close();
        }else{
            //doe niks
        }
    }

    public void close(){
        Stage stage = (Stage) none.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatePrice();
    }
}
