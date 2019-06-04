package models;

import javafx.scene.Node;
import views.GameView;
import controllers.GameController;

import java.io.IOException;

/**
 * This class is the player. An object of this class is created for every player. All statistics of a player, including it's position on the board, are stored here.
 * @author Stan
 * @version 2 juni 2019
 */
public class Player {

    public String name;

    public int rubies = 0;
    public int lira = 0;
    public int carUpgrades = 2;
    public String teamColor;

    public int spices = 0;
    public int fruits = 0;
    public int jewels = 0;
    public int fabrics = 0;

    public int assistants = 4;
    public int position = 7;

    public boolean greenTile = false;
    public boolean redTile = false;
    public boolean blueTile = false;
    public boolean yellowTile = false;

    public boolean hasMoved = false;

    ////

    public Player(String name){
        this.name = name;
    }

    ////
}


