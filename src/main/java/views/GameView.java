package views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GameView {

    @FXML
    public Button cac; // aanmaken fx:id

    @FXML
    public Button cpp; // aanmaken fx:id


    public void start() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/game.fxml"));
        Parent root1 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Istanbul");
        stage.setScene(new Scene(root1));
        //stage.setMaximized(true);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());

        stage.show();
    }

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


    public void close(){
        System.exit(0);
    }


    public void closeAskClose() throws IOException {
        Stage stage = (Stage) cac.getScene().getWindow();
        stage.close();
    }

    public void closePlayerProg() throws IOException {
        Stage stage = (Stage) cpp.getScene().getWindow();
        stage.close();
    }}

