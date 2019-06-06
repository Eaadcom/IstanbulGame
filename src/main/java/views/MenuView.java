package views;

import controllers.MenuViewController;
import observers.MainMenuObservable;
import observers.MenuViewObserver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuView implements Initializable, MenuViewObserver {

    // variabelen
    private static MenuView menuView;
    private MenuViewController menuViewController;
    private GameView gv;

    // FXML variabelen
    @FXML
    ChoiceBox cb;
    @FXML
    ChoiceBox cb2;
    @FXML
    private VBox rootPane; // aanmaken fx:id
    @FXML
    private Button startGame; // aanmaken fx:id
    @FXML
    private TextField usernamefield; // aanmaken fx:id

    // Constructor
    public MenuView(){
        cb = new ChoiceBox();
        cb2 = new ChoiceBox();
        gv = new GameView();
    }

    // Start het login deel van de MenuView
    public void start(Stage stage) throws Exception{

        stage.setTitle("Istanbul");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        root.setId("pane");
        Scene scene = new Scene(root, 1920, 1080);
        stage.setFullScreen(true);


        stage.setScene(scene);
        stage.show();
    }

    // Start het game aanmaak deel van de MenuView
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

    // Available rooms
    @FXML
    private void availablerooms() throws IOException {
        VBox pane4 = FXMLLoader.load(getClass().getResource("../fxml/availablerooms.fxml"));
        rootPane.getChildren().setAll(pane4);
    }

    // Handelt de input van de Login
    @FXML
    private void login() throws IOException {
        if (usernamefield.getText().equals("") || usernamefield.getText().contains(" ") || usernamefield.getText().contains("`") || usernamefield.getText().contains("+") || usernamefield.getText().contains("-") || usernamefield.getText().contains("]") || usernamefield.getText().contains("=") || usernamefield.getText().contains("/") || usernamefield.getText().contains("\\") || usernamefield.getText().contains("~") || usernamefield.getText().contains("'") || usernamefield.getText().contains(";") || usernamefield.getText().contains(":") || usernamefield.getText().contains(",") || usernamefield.getText().contains(".") || usernamefield.getText().contains("?") || usernamefield.getText().contains("!") || usernamefield.getText().contains("@") || usernamefield.getText().contains("#") || usernamefield.getText().contains("$") || usernamefield.getText().contains("%") || usernamefield.getText().contains("^") || usernamefield.getText().contains("&") || usernamefield.getText().contains("*") || usernamefield.getText().contains("(") || usernamefield.getText().contains(")") || usernamefield.getText().contains("''") || usernamefield.getText().contains("_") || usernamefield.getText().contains("{") || usernamefield.getText().contains("}") || usernamefield.getText().contains("|") || usernamefield.getText().contains("\"")) { // doe niks
        } else {
            VBox pane3 = FXMLLoader.load(getClass().getResource("../fxml/mainmenu.fxml"));
            rootPane.getChildren().setAll(pane3);
            String username = usernamefield.getText();

            menuViewController = MenuViewController.getInstance();
            menuViewController.throwUsername(username);
        }
    }

    //Start game
    @FXML
    private void startGame() throws IOException {
        Stage stage = (Stage) startGame.getScene().getWindow();
        stage.close();
        gv.start();
    }

    // Exit game
    @FXML
    public void close(){
        System.exit(0);
    }

    // Observer Pattern
    @Override
    public void update(MainMenuObservable mmo) {
        mmo.getUsername();
        System.out.println(mmo.getUsername());
    }

    // Singleton Pattern
    public static MenuView getInstance() {
        if (menuView == null) {
            menuView = new MenuView();
        }
        return menuView;
    }
}

