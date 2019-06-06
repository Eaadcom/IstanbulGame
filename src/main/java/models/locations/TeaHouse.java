package models.locations;

import observers.GameViewObserver;
import observers.locations.TeaHouseObservable;

import java.util.ArrayList;
import java.util.List;

public class   TeaHouse implements Location, TeaHouseObservable {
    public int numberChoice;
    public int diceOne;
    public int diceTwo;
    public int Location = 15;

    public TeaHouse(int numberChoice, int diceOne, int diceTwo, int Location){
        this.numberChoice = numberChoice;
        this.diceOne = diceOne;
        this.diceTwo = diceTwo;
        this.Location = Location;
    }


    private List<GameViewObserver> observers = new ArrayList<>();


    @Override
    public void register(GameViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (GameViewObserver gvo : observers){
            gvo.update(this);
        }
    }
}


