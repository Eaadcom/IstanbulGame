package views;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import controllers.MenuViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import observers.MainMenuObservable;
import observers.MenuViewObserver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.GameInformation;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class MenuView implements Initializable, MenuViewObserver {

    // variabelen
    private static MenuView menuView;
    private MenuViewController menuViewController;

    // FXML variabelen
    @FXML ChoiceBox cb = new ChoiceBox();
    @FXML ChoiceBox cb2 = new ChoiceBox();
    @FXML private VBox rootPane;
    @FXML private Button startGame;
    @FXML private TextField usernamefield;
    @FXML private TextField roomName;

    // Start het login deel van de MenuView
//    public void start(Stage stage) throws Exception{
//
//        stage.setTitle("Istanbul");
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
//        root.setId("pane");
//        Scene scene = new Scene(root, 1920, 1080);
//        stage.setFullScreen(true);
//
//        stage.setScene(scene);
//        stage.show();
//    }

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
    private void goBack() {
        try{
            VBox pane2 = FXMLLoader.load(getClass().getResource("../fxml/mainmenu.fxml"));
            rootPane.getChildren().setAll(pane2);
        } catch (Exception e){
            e.printStackTrace();
        }
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


        //GameInformation g = new GameInformation("game", 2);
        //lobbyTable.getItems().add(g);
        //roomname.getColumns().add("test");

        //System.out.println(roomname.getColumns());

        //lobbyTable.getItems().add(0, "test");

        //System.out.println(lobbyTable);

        //for (QueryDocumentSnapshot document : documents) {
        //    //roomname.setText(document.getId());
        //    System.out.println(lobbyTable);
        //    System.out.println(document.getId());
        //}

        VBox pane4 = FXMLLoader.load(getClass().getResource("../fxml/availablerooms.fxml"));
        rootPane.getChildren().setAll(pane4);
    }

    // Handelt de input van de Login
    @FXML
    private void login() throws IOException {
        Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^~`*()%!-]");
        boolean result = regex.matcher(usernamefield.getText()).matches();

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
    private void startGame() {
        try{
            if (roomName.getText() != null && cb2.getSelectionModel().getSelectedItem() != null && cb.getSelectionModel().getSelectedItem() != null){
                menuViewController = MenuViewController.getInstance();
                menuViewController.throwGameData(roomName.getText(), cb2.getSelectionModel().getSelectedItem().toString(),
                        Integer.parseInt(cb.getSelectionModel().getSelectedItem().toString()));

                menuViewController.createOnlineGame();

                Stage stage = (Stage) startGame.getScene().getWindow();
                stage.close();
                GameView gv = GameView.getInstance();
                gv.start();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
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
    }

    // Singleton Pattern
    public static MenuView getInstance() {
        if (menuView == null) {
            menuView = new MenuView();
        }
        return menuView;
    }
}

