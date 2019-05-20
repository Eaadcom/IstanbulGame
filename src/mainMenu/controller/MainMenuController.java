package mainMenu.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {


    @FXML
    private VBox rootPane; // aanmaken fx:id
    @Override
    public void initialize(URL location, ResourceBundle resources){ }


    // Create Room
    @FXML
    private void createRoom() throws IOException {
        VBox pane = FXMLLoader.load(getClass().getResource("../view/makingroom.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    // Back button
    @FXML
    private void goBack() throws IOException {
        VBox pane2 = FXMLLoader.load(getClass().getResource("../view/mainmenu.fxml"));
        rootPane.getChildren().setAll(pane2);
    }

    // Settings
    @FXML
    private void settings() throws IOException {
        VBox pane3 = FXMLLoader.load(getClass().getResource("../view/settings.fxml"));
        rootPane.getChildren().setAll(pane3);
    }

    // Login
    @FXML
    private TextField usernamefield; // aanmaken fx:id

    @FXML
    private void login() throws IOException {
        if (usernamefield.getText().equals("") || usernamefield.getText().contains(" ") || usernamefield.getText().contains("`") || usernamefield.getText().contains("+") || usernamefield.getText().contains("-") || usernamefield.getText().contains("]") || usernamefield.getText().contains("=") || usernamefield.getText().contains("/") || usernamefield.getText().contains("\\") || usernamefield.getText().contains("~") || usernamefield.getText().contains("'") || usernamefield.getText().contains(";") || usernamefield.getText().contains(":") || usernamefield.getText().contains(",") || usernamefield.getText().contains(".") || usernamefield.getText().contains("?") || usernamefield.getText().contains("!") || usernamefield.getText().contains("@") || usernamefield.getText().contains("#") || usernamefield.getText().contains("$") || usernamefield.getText().contains("%") || usernamefield.getText().contains("^") || usernamefield.getText().contains("&") || usernamefield.getText().contains("*") || usernamefield.getText().contains("(") || usernamefield.getText().contains(")") || usernamefield.getText().contains("''")|| usernamefield.getText().contains("_") || usernamefield.getText().contains("{") || usernamefield.getText().contains("}") || usernamefield.getText().contains("|"))
        { // doe niks
            }else{
            VBox pane3 = FXMLLoader.load(getClass().getResource("../view/mainmenu.fxml"));
            rootPane.getChildren().setAll(pane3);
            String username = usernamefield.getText();
            System.out.println(username);
    }}


    /*
    //Start game
    @FXML
    private void startGame() throws IOException {
        VBox pane4 = FXMLLoader.load(getClass().getResource("../board.fxml"));
        rootPane.getChildren().setAll(pane4);
    }
    */



    //Exit game
    @FXML
    public void close(){
        System.exit(0);
    }

}
