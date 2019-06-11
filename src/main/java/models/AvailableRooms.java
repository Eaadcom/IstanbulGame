package models;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import controllers.MenuViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
            System.out.println(document.getData().get("playerTotal"));
            ObservableList<GameInformation> gi = FXCollections.observableArrayList(new GameInformation(document.getId(), document.getData().get("playerTotal")  , "a"));
            roomname.setCellValueFactory(new PropertyValueFactory<GameInformation, String>("roomname"));
            totalPlayers.setCellValueFactory(new PropertyValueFactory<GameInformation, Object>("totalPlayers"));
            joinButtons.setCellValueFactory(new PropertyValueFactory<GameInformation, String>("button"));
            lobbyTable.getItems().addAll(gi);
        }
    }
}
