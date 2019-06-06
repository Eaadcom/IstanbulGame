package models.locations;

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
    public models.Dice diceOne;
    public models.Dice diceTwo;

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

    // Singleton Pattern
    public static BlackMarket getInstance() {
        if (blackMarket == null) {
            blackMarket = new BlackMarket();
        }
        return blackMarket;
    }
}
