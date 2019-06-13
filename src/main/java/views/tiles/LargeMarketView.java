package views.tiles;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LargeMarketView implements Initializable {

    //Singleton
    private static LargeMarketView largeMarketView;
    public static LargeMarketView getInstance() {
        if (largeMarketView == null) {
            largeMarketView = new LargeMarketView();
        }
        return largeMarketView;
    }
    //

    public void largeMarket() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/largeMarket.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set green good price
        //set red good price
        //set yellow good price
        //set blue good price
    }
}
