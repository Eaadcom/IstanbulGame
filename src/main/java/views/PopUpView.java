package views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PopUpView {

    @FXML
    public Button cac; // aanmaken fx:id

    @FXML
    public Button cpp; // aanmaken fx:id

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

    public void closeAskClose() throws IOException {
        Stage stage = (Stage) cac.getScene().getWindow();
        stage.close();
    }

    public void closePlayerProg() throws IOException {
        Stage stage = (Stage) cpp.getScene().getWindow();
        stage.close();
    }

    public void close(){
        System.exit(0);
    }
}
