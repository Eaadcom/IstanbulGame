package controllers;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import views.GameView;
import javafx.fxml.FXML;

import java.io.IOException;

public class GameController {

    GameView game = new GameView();

    public void start() throws IOException {
        game.start();
    }

    @FXML
    public void askClose() throws IOException {
        game.askClose();
    }

    @FXML
    public void playerProgression() throws IOException {
        game.playerProgression();
    }



    //movement
    @FXML
    public HBox HT1, HT2, HT3, HT4, HT5, HT6, HT7, HT8, HT9, HT10, HT11, HT12, HT13, HT14, HT15, HT16 = new HBox();
    public Pane niffored, niffogreen, niffowhite, niffoyellow, niffoblue = new Pane();


    public void switchButtonPressed() {
        if(HT1.getChildren().contains(niffored)) {
            HT1.getChildren().remove(niffored);
            HT2.getChildren().add(niffored);
        } else {
            HT2.getChildren().remove(niffored);
            HT1.getChildren().add(niffored);
        }

    }
    }


