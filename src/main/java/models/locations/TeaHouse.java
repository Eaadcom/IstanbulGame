package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.TeaHouseObservable;

import java.util.ArrayList;
import java.util.List;

public class TeaHouse implements Location, TeaHouseObservable {

    // Variables
    private static TeaHouse teaHouse;
    public int numberChoice;
    public int diceOne;
    public int diceTwo;
    public int Location = 15;
    private List<LocationViewObserver> observers = new ArrayList<>();

    // Constructor
    public TeaHouse(){
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
    public static TeaHouse getInstance() {
        if (teaHouse == null) {
            teaHouse = new TeaHouse();
        }
        return teaHouse;
    }
}


