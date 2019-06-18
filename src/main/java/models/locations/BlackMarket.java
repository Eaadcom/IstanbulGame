package models.locations;

import models.Dice;
import models.Player;
import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.BlackMarketObservable;

import java.util.ArrayList;
import java.util.List;

public class BlackMarket implements Location, BlackMarketObservable {

    // Variables
    private static BlackMarket blackMarket;
    private List<LocationViewObserver> observers = new ArrayList<>();
    public int Location = 1;
    public models.Dice diceOne = new Dice();
    public models.Dice diceTwo = new Dice();
    public boolean redAs = false;
    public boolean blueAs = false;
    public boolean greenAs = false;
    public boolean yellowAs = false;
    public boolean whiteAs = false;



    // Constructor
    public BlackMarket() {
    }

    // Observer Pattern
    @Override
    public void register(LocationViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (LocationViewObserver gvo : observers){
            gvo.update(this);
        }
    }

    public boolean color(String color) {
        boolean myColor;
        if (color == "red") {
            myColor = redAs;
        } else if( color == "blue"){
            myColor = blueAs;
        } else if(color == "green" ){
            myColor = greenAs;
        } else if ( color == "yellow"){
            myColor = yellowAs;
        } else if (color == "white"){
            myColor = whiteAs;
        }
        else{
            myColor = false;
        }
        return myColor;
    }

    public void setColor(String color, boolean set){
        if (color == "red"){
            redAs = set;
        } else if(color == "blue"){
            blueAs = set;
        } else if (color == "green"){
            greenAs = set;
        } else if (color == "yellow"){
            yellowAs = set;
        } else if (color == "white"){
            whiteAs = set;
        }
    }

    public void onUse(Player player) {

    }

                      // Singleton Pattern
    public static BlackMarket getInstance() {
        if (blackMarket == null) {
            blackMarket = new BlackMarket();
        }
        return blackMarket;
    }
}
