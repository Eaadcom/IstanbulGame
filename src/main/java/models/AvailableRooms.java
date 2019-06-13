package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AvailableRooms implements Initializable {


    @FXML private TableView<GameInformation> lobbyTable;
    @FXML private TableColumn<GameInformation,String> roomname;
    @FXML private TableColumn<GameInformation,Integer> totalPlayers;
    @FXML TableColumn joinButtons = new TableColumn("Action");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<GameInformation> gi = FXCollections.observableArrayList(new GameInformation("game", 2));
        roomname.setCellValueFactory(new PropertyValueFactory<GameInformation, String>("roomname"));
        totalPlayers.setCellValueFactory(new PropertyValueFactory<GameInformation, Integer>("totalPlayers"));
        joinButtons.setCellValueFactory(new PropertyValueFactory<GameInformation, String>("button"));
        lobbyTable.getItems().addAll(gi);
    }
}
