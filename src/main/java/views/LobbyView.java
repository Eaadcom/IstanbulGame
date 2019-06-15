package views;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import controllers.GameController;
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

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class LobbyView implements LobbyViewObserver, Initializable {

    // Variables
    private static LobbyView lobbyView;

    private static final GameController gameController = GameController.getInstance();

    // FXML Variables
    @FXML
    Text roomName;
    @FXML
    Text p1;
    @FXML
    Text p2;
    @FXML
    Text p3;
    @FXML
    Text p4;
    @FXML
    Text p5;

    private QueryDocumentSnapshot document;

    private List<Text> playerTexts;
    private Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerTexts = Arrays.asList(p1, p2, p3, p4, p5);

//        if (document != null) {
//            setFromDocumentData(document);
//        } else if (game != null) {
        gameController.registerGameOrLobbyObserverToGame(this);

        setFromGameData(gameController.getGame());
        disablePlayerTexts(gameController.getGame().getPlayerTotal());
//        } else {
//            throw new IllegalStateException("geen game of document, kan game niet starten");
//        }
        gameController.startWatchForChanges();
    }

    public void startGame() {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/game.fxml"));
            Parent root1 = fxmlloader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Istanbul");
            stage.setScene(new Scene(root1));
            stage.setMaximized(true);


            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX(primaryScreenBounds.getMinX());
            stage.setY(primaryScreenBounds.getMinY());
            stage.setWidth(primaryScreenBounds.getWidth());
            stage.setHeight(primaryScreenBounds.getHeight());
            stage.show();
        } catch (IOException e) {
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

    private void setFromDocumentData(QueryDocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        setPlayerNames((List<String>) data.get("playerNames"));
        setRoomName(document.getId());
        disablePlayerTexts(Integer.parseInt(data.get("playerTotal").toString()));
        gameController.joinGame(document);
    }

    private void setPlayerNames(List<String> playersNames) {
        for (int i = 0; i < playersNames.size(); i++) {
            playerTexts.get(i).setText(playersNames.get(i));
        }
    }

    private void setRoomName(String roomName) {
        this.roomName.setText(roomName);
    }
//
//    @FXML
//    public void start(String gameName, String playerTotal, int gameNum){
//        try{
//            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/lobby.fxml"));
//            Parent root8 = (Parent) fxmlloader.load();
//
//            roomName = (Text) root8.lookup("#roomName");
//            roomName.setText(gameName);
//
//            disablePlayerTexts(root8, Integer.parseInt(playerTotal));
//            setNameByFirebase(root8, gameNum);
//
//            Stage stage = new Stage();
//            stage.initStyle(StageStyle.UNDECORATED);
//            stage.setTitle("Istanbul");
//            stage.setScene(new Scene(root8));
//            stage.setMaximized(true);
//            stage.show();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    private void setNameByFirebase(Parent root8, int gameNum){
//        List namesList = (ArrayList) Firebase.getInstance().getLobbyInfo().get(gameNum).getData().get("playerNames");
//        p1 = (Text) root8.lookup("#p1");
//        p2 = (Text) root8.lookup("#p2");
//        p3 = (Text) root8.lookup("#p3");
//        p4 = (Text) root8.lookup("#p4");
//        p5 = (Text) root8.lookup("#p5");
//
////        if (namesList.get(0) == "") {
////            p1.setText(MenuViewController.getInstance().getUserName());
////        } else if (namesList.get(1) == ""){
////
////        } else if (namesList.get(2) == ""){
////
////        } else if (namesList.get(3) == ""){
////
////        } else if (namesList.get(4) == ""){
////
////        }
//    }

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

    // Singleton Pattern
    public static LobbyView getInstance() {
        if (lobbyView == null) {
            lobbyView = new LobbyView();
        }
        return lobbyView;
    }

    public void setDocument(QueryDocumentSnapshot document) {
        this.document = document;
    }

    public void setGame(Game game) {
        this.game = game;
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
        }
    }
}
