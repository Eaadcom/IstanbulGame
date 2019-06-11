package models;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import controllers.MenuViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AvailableRooms implements Initializable {

    // Variables
    MenuViewController menuViewController;

    // FXML Variables
    @FXML private TableView<GameInformation> lobbyTable;
    @FXML private TableColumn<GameInformation,String> roomname;
    @FXML private TableColumn<GameInformation,java.lang.Object> totalPlayers;
    @FXML private TableColumn joinButtons = new TableColumn("Action");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        menuViewController = MenuViewController.getInstance();
        List<QueryDocumentSnapshot> documents = menuViewController.getLobbies();

        for (QueryDocumentSnapshot document : documents) {
            ObservableList<GameInformation> gi = FXCollections.observableArrayList(new GameInformation(document.getId(), document.getData().get("playerTotal")  , "a"));
            roomname.setCellValueFactory(new PropertyValueFactory<GameInformation, String>("roomname"));
            totalPlayers.setCellValueFactory(new PropertyValueFactory<GameInformation, Object>("totalPlayers"));
            joinButtons.setCellValueFactory(new PropertyValueFactory<GameInformation, String>("button"));
            lobbyTable.getItems().addAll(gi);
        }
    }

    public void goBack() throws IOException {
        Stage stage2 = (Stage) lobbyTable.getScene().getWindow();
        stage2.close();

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/mainmenu.fxml"));
        Parent root1 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Istanbul");
        stage.setScene(new Scene(root1));
        stage.setMaximized(true);
        stage.show();
    }
}
