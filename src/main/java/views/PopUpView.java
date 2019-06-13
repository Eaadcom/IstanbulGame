package views;

import javafx.application.Platform;
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

    // Variables
    private static PopUpView popUpView;

    // FXML variables
    @FXML
    public Button cac; // aanmaken fx:id
    @FXML
    public Button cpp; // aanmaken fx:id
    @FXML
    public Button cr; // aanmaken fx:id
    @FXML
    public Button closeConfirmMovement; // aanmaken fx:id
    public boolean move = true;

    // Function to close the popup
    public void askClose() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/askClose.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    // Function to show the playerprogression popup
    public void playerProgression() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/playerProgression.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    // Function to show the confirmmovement popup
    public boolean confirmMovement() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/confirmMovement.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        PopUpView controller = fxmlloader.getController();
        stage.showAndWait();
        return controller.move;
    }

    public void move() {
        this.move = true;
        closeConfirmMovement();
    }

    public void dontMove() {
        this.move = false;
        closeConfirmMovement();
    }

    // Function to close the confirmmovement popup
    public void closeConfirmMovement() {
        Stage stage = (Stage) closeConfirmMovement.getScene().getWindow();
        stage.close();
    }

    // Function to show the game rules
    public void rulesButton() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/rules.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    // Function to close the rules popup
    public void closeRulesPage(){
        try {
            Stage stage = (Stage) cr.getScene().getWindow();
            stage.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    // ??????
    public void closeAskClose() throws IOException {
        Stage stage = (Stage) cac.getScene().getWindow();
        stage.close();
    }

    // Function to close the player progression window
    public void closePlayerProg() throws IOException {
        Stage stage = (Stage) cpp.getScene().getWindow();
        stage.close();
    }

    public void winnerScreen() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/winnerScreen.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void loserScreen() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/loserScreen.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    // Function to close the game
    public void close(){
        System.exit(0);
    }

    // Singleton Pattern
    public static PopUpView getInstance() {
        if (popUpView == null) {
            popUpView = new PopUpView();
        }
        return popUpView;
    }


}
