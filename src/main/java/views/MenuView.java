package views;

import controllers.FirebaseController;
import controllers.GameController;
import controllers.MenuViewController;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import models.Game;
import observers.MainMenuObservable;
import observers.MenuViewObserver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class MenuView implements Initializable, MenuViewObserver {

    // variabelen
    private static MenuView menuView;
    private MenuViewController menuViewController = MenuViewController.getInstance();
    private GameController gameController = GameController.getInstance();
    private static Stage stage;

    // FXML variabelen
    @FXML
    private ChoiceBox<String> cb = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> cb2 = new ChoiceBox<>();
    @FXML private VBox rootPane;
    @FXML private Button createRoom;
    @FXML private TextField usernamefield;
    @FXML private TextField roomName;

    public void start(Stage stager) throws Exception{
        stage = stager;
        FirebaseController.getInstance().initialize();
        stage.setTitle("Istanbul");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        rootPane = (VBox) root.lookup("#rootPane");
        root.setId("pane");
        Scene scene = new Scene(root, 1920, 1080);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

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

    // Create Lobby
    @FXML
    private void createLobby() throws IOException {
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
        Pattern loginPattern = Pattern.compile("[$&+,:;=\\\\?@#|/'{}<>.^~_`*()%!-]");
        String usernamefieldText = usernamefield.getText();

        if (!usernamefieldText.equals("") && !usernamefieldText.contains(" ") && !loginPattern.matcher(usernamefieldText).find()) {
            VBox pane3 = FXMLLoader.load(getClass().getResource("../fxml/mainmenu.fxml"));
            rootPane.getScene().setRoot(pane3);
            //createMainMenu();
            menuViewController.throwUsername(usernamefieldText);
        }
    }

    @FXML
    public void createMainMenu(){
        try{
            VBox pane3 = FXMLLoader.load(getClass().getResource("../fxml/mainmenu.fxml"));
            stage.setScene(new Scene(pane3));
            stage.setFullScreen(true);
            stage.show();
        } catch ( Exception e){
            e.printStackTrace();
        }
    }

    //Start game
    @FXML
    private void createRoom() {
        try{
            if (roomName.getText() != null && cb2.getSelectionModel().getSelectedItem() != null && cb.getSelectionModel().getSelectedItem() != null){
                menuViewController = MenuViewController.getInstance();
                menuViewController.throwGameData(roomName.getText(), cb2.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(cb.getSelectionModel().getSelectedItem()));

                stage = (Stage) createRoom.getScene().getWindow();
                stage.close();
                gameController.initializeGameData();
                LobbyView.getInstance().showLobbyView();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Stage getStage2(){
        return stage;
    }

//    private void showGameView() {
//        try {
//            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/game.fxml"));
//            Parent root1 = fxmlloader.load();
//            Stage stage = new Stage();
//            stage.initStyle(StageStyle.UNDECORATED);
//            stage.setTitle("Istanbul");
//            stage.setScene(new Scene(root1));
//            stage.setMaximized(true);
//
//            GameView gameView = fxmlloader.getController();
//
//            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//            stage.setX(primaryScreenBounds.getMinX());
//            stage.setY(primaryScreenBounds.getMinY());
//            stage.setWidth(primaryScreenBounds.getWidth());
//            stage.setHeight(primaryScreenBounds.getHeight());
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

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
