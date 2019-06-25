package views.cards;



        import controllers.CardController;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.stage.Modality;
        import javafx.stage.Stage;
        import javafx.stage.StageStyle;
        import models.cards.BonusGainGood;


        import java.io.IOException;

public class BonusGainGoodView {

    @FXML
    public Button red, green, yellow, blue;

    private static BonusGainGoodView bonusGainGoodView;
    public static BonusGainGoodView getInstance() {
        if (bonusGainGoodView == null) {
            bonusGainGoodView = new BonusGainGoodView();
        }
        return bonusGainGoodView;
    }

    public void redOne(){
        BonusGainGood.getInstance().GainGood("fabric");
        close();
    }
    public void blueOne(){
        BonusGainGood.getInstance().GainGood("jewel");
        close();
    }
    public void yellowOne(){
        BonusGainGood.getInstance().GainGood("fruit");
        close();
    }
    public void greenOne(){
        BonusGainGood.getInstance().GainGood("spice");
        close();
    }

    public void close(){
        Stage stage = (Stage) red.getScene().getWindow();
        stage.close();
    }


    public void bonusOneGood() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/bonusOneGood.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}

