package controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuViewController implements Initializable {

    GameController gc = new GameController();

    @FXML
    ChoiceBox cb = new ChoiceBox();
    @FXML
    ChoiceBox cb2 = new ChoiceBox();




    @FXML
    private VBox rootPane; // aanmaken fx:id
    @FXML
    private FlowPane flowPane; // aanmaken fx:id
    @FXML
    private Button startGame; // aanmaken fx:id

    @Override
    public void initialize(URL location, ResourceBundle resources){
        cb.getItems().add("2");
        cb.getItems().add("3");
        cb.getItems().add("4");
        cb.getItems().add("5");

        cb2.getItems().add("easy");
        cb2.getItems().add("medium");
        cb2.getItems().add("hard");
        cb2.getItems().add("random");
    }

    // Create Room
    @FXML
    private void createRoom() throws IOException {
        VBox pane = FXMLLoader.load(getClass().getResource("../fxml/makingroom.fxml"));
        rootPane.getChildren().setAll(pane);

    }

    // Back button
    @FXML
    private void goBack() throws IOException {
        VBox pane2 = FXMLLoader.load(getClass().getResource("../fxml/mainmenu.fxml"));
        rootPane.getChildren().setAll(pane2);
    }

    // Settings
    @FXML
    private void settings() throws IOException {
        VBox pane3 = FXMLLoader.load(getClass().getResource("../fxml/settings.fxml"));
        rootPane.getChildren().setAll(pane3);
    }

    //available rooms
    @FXML
    private void availablerooms() throws IOException {
        VBox pane4 = FXMLLoader.load(getClass().getResource("../fxml/availablerooms.fxml"));
        rootPane.getChildren().setAll(pane4);
    }

    // Login
    @FXML
    private TextField usernamefield; // aanmaken fx:id

    @FXML
    private void login() throws IOException {
        if (usernamefield.getText().equals("") || usernamefield.getText().contains(" ") || usernamefield.getText().contains("`") || usernamefield.getText().contains("+") || usernamefield.getText().contains("-") || usernamefield.getText().contains("]") || usernamefield.getText().contains("=") || usernamefield.getText().contains("/") || usernamefield.getText().contains("\\") || usernamefield.getText().contains("~") || usernamefield.getText().contains("'") || usernamefield.getText().contains(";") || usernamefield.getText().contains(":") || usernamefield.getText().contains(",") || usernamefield.getText().contains(".") || usernamefield.getText().contains("?") || usernamefield.getText().contains("!") || usernamefield.getText().contains("@") || usernamefield.getText().contains("#") || usernamefield.getText().contains("$") || usernamefield.getText().contains("%") || usernamefield.getText().contains("^") || usernamefield.getText().contains("&") || usernamefield.getText().contains("*") || usernamefield.getText().contains("(") || usernamefield.getText().contains(")") || usernamefield.getText().contains("''")|| usernamefield.getText().contains("_") || usernamefield.getText().contains("{") || usernamefield.getText().contains("}") || usernamefield.getText().contains("|") || usernamefield.getText().contains("\""))
        { // doe niks
            }else{
            VBox pane3 = FXMLLoader.load(getClass().getResource("../fxml/mainmenu.fxml"));
            rootPane.getChildren().setAll(pane3);
            String username = usernamefield.getText();
            System.out.println(username);
    }}

    //Start game
    @FXML
    private void startGame() throws IOException {
        Stage stage = (Stage) startGame.getScene().getWindow();
        stage.close();
        gc.start();
    }




    //Exit game
    @FXML
    public void close(){
        System.exit(0);
    }

}
