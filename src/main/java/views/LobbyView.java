package views;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import controllers.GameController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Game;
import models.Player;
import observers.GameObservable;
import observers.LobbyViewObserver;
import util.GameInformation;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * This view is shown when the player enters a lobby, which is after the main menu but before entering the actual game.
 * @author Thomas vabn Velzen, Edward Deen
 * @version 20-6-2019
 */
public class LobbyView implements LobbyViewObserver, Initializable {

    // Variables
    private GameController gameController = GameController.getInstance();
    private static LobbyView lobbyView;
    private static Stage stage;

    // FXML Variables
    @FXML Text roomName;
    @FXML Text p1;
    @FXML Text p2;
    @FXML Text p3;
    @FXML Text p4;
    @FXML Text p5;

    private List<Text> playerTexts;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerTexts = Arrays.asList(p1, p2, p3, p4, p5);
        gameController.registerGameOrLobbyObserverToGame(this);
        setFromGameData(gameController.getGame());
        disablePlayerTexts(gameController.getGame().getPlayerTotal());
        gameController.startWatchForChanges();
    }

    public void startGame() {
        gameController.startGame();
//        loadGameScreen();
    }

    private void loadGameScreen() {
        try {
            //if (GameController.getInstance().getGamePhase()){
                GameView.getInstance().start();//}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLobbyView() {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/lobby.fxml"));
            Parent root8 = fxmlloader.load();
            root8.setId("pane");
            stage = new Stage();
            Scene scene = new Scene(root8);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Istanbul");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFromGameData(Game game) {
        setRoomName(game.getName());
        List<String> playerNames = new ArrayList<>();
        for (Player player : game.getPlayers()) {
            playerNames.add(player.getName());
        }
        setPlayerNames(playerNames);
    }

    private void setPlayerNames(List<String> playersNames) {
        int count = 0;
        for (int i = 0; i < GameController.getInstance().getGame().board.getPlayers().size(); i++) {
            if (count == 5){
                break;
            }
            playerTexts.get(i).setText(GameController.getInstance().getGame().board.getPlayers().get(i).getName());
            count++;
        }
    }

    private void setRoomName(String roomName) {
        this.roomName.setText(roomName);
    }

    private void disablePlayerTexts(int playerTotal) {
        if (playerTotal == 2) {
            p3.setText("-----");
            p4.setText("-----");
            p5.setText("-----");
        }
        if (playerTotal == 3) {
            p4.setText("-----");
            p5.setText("-----");
        }
        if (playerTotal == 4) {
            p5.setText("-----");
        }
    }

    public void closeStage(){
        stage.close();
    }

    @FXML
    private void goBack(){
        MenuView.getInstance().createMainMenu();
        closeStage();
    }

    @Override
    public void update(GameObservable go) {
        if (go instanceof Game) {
            System.out.println("received update as observer");
            Game game = (Game) go;
            List<String> names = new ArrayList<>();
            for (Player player : game.getPlayers()) {
                names.add(player.getName());
            }
            setRoomName(game.getName());
            setPlayerNames(names);
            disablePlayerTexts(game.getPlayerTotal());
            System.out.println(game.isGameStarted());
            if (game.isGameStarted() && !gameController.isMyGameStarted()) {
                gameController.setMyGameStarted(true);
                loadGameScreen();
            }

//            Platform.runLater(new Runnable(){
//                @Override public void run() {
//
//                }
//            });
        }
    }

    // Singleton Pattern
    public static LobbyView getInstance() {
        if (lobbyView == null) {
            lobbyView = new LobbyView();
        }
        return lobbyView;
    }
}
