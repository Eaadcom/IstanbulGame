package views;

import controllers.GameController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.cards.BonusCard;

import java.io.IOException;

/**
 * This view class contains the views of the less complicated pop-up windows.
 * The more complicated ones are stored in their own respective view classes.
 * @author Stan Hogenboom, Thomas van Velzen, Edward Deen, Joeri van Duijkeren, Floris Dekker
 * @version 21-6-2019
 */
public class PopUpView {

    // Variables
    private static PopUpView popUpView;

    // FXML variables
    @FXML
    public Button cac, endturn, closebc; // aanmaken fx:id
    @FXML
    public Button cpp; // aanmaken fx:id
    @FXML
    public Button cr; // aanmaken fx:id
    @FXML
    public Button closeConfirmMovement; // aanmaken fx:id

    public boolean move = true;
    public BonusCard gekozenBonusKaart;
    private boolean bonusKaartGebruiken = false;
    private boolean endTurn = false;

    private String gekozenKaart = "";

    // Function to close the popup
    public void askClose() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/askClose.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    // Function to show the playerprogression popup
    public void playerProgression() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/playerProgression.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    // Function to show the confirmmovement popup
    public boolean confirmMovement() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/confirmMovement.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        PopUpView controller = fxmlloader.getController();
        stage.showAndWait();
        return controller.move;
    }

    public boolean bonusKaartGebruiken() {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("")); // fxml van popup met bonuskaart gebruiken ja/nee
        Parent root2 = null;
        try {
            root2 = fxmlloader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        PopUpView controller = fxmlloader.getController();
        stage.showAndWait();
//        return controller.bonusKaartGebruiken;
        return true;
    }

    /**
     * Methoden om de beurt te beeindigen
     * @author Joeri
     * @version 12 june 2019
     * @throws IOException
     */
    public boolean endTurn() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/endTurn.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        PopUpView controller = fxmlloader.getController();
        stage.showAndWait();
        return controller.endTurn;
    }

    public void confirmEndTurn() {
        this.endTurn = true;
        Stage stage = (Stage) endturn.getScene().getWindow();
        stage.close();
    }

    public void closeEndTurn() {
        Stage stage = (Stage) endturn.getScene().getWindow();
        stage.close();
    }

    /**
     * Fxml bekijken van je bonuskaarten
     * @author Joeri
     * @version 12 june
     * @throws IOException
     */

    public String bonusCards() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/bonusCards.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        PopUpView controller = fxmlloader.getController();

        stage.showAndWait();
        return controller.gekozenKaart;
    }

    @FXML
    public void chooseBonusCard(ActionEvent event) {
        Button source = (Button) event.getSource();
        this.gekozenKaart = source.getId();
        closeBonusCards();
    }

    /**
     * Popup van beschikbare bonuskaarten
     * @author Joeri
     * @version 12 june
     */
    public void closeBonusCards() {
        Stage stage = (Stage) closebc.getScene().getWindow();
        stage.close();
    }

    /**
     * Popup of je wil bewegen naar de tile die je aanklikt
     * @Author Joeri
     * @Version 11 june
     */
    public void move() {
        this.move = true;
        closeConfirmMovement();
    }

    public void dontMove() {
        this.move = false;
        closeConfirmMovement();
    }

    // Function to close the confirmmovement popup
    public void closeConfirmMovement() {
        Stage stage = (Stage) closeConfirmMovement.getScene().getWindow();
        stage.close();
    }

    // Function to show the game rules
    public void rulesButton() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/rules.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    // Function to close the rules popup
    public void closeRulesPage(){
        try {
            Stage stage = (Stage) cr.getScene().getWindow();
            stage.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    // Asks if you want to close the game
    public void closeAskClose() throws IOException {
        Stage stage = (Stage) cac.getScene().getWindow();
        stage.close();
    }

    // Function to close the player progression window
    public void closePlayerProg() throws IOException {
        Stage stage = (Stage) cpp.getScene().getWindow();
        stage.close();
    }

    public void winnerScreen() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/youWon.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void loserScreen() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/youLost.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    // Function to close the game
    public void close(){
        GameController.getInstance().pauseGame();
        GameController.getInstance().updateGame();
        System.exit(0);
    }

    // Singleton Pattern
    public static PopUpView getInstance() {
        if (popUpView == null) {
            popUpView = new PopUpView();
        }
        return popUpView;
    }

}
