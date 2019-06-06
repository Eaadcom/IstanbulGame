package models;

import observers.DiceObservable;
import observers.GameViewObserver;

import java.util.ArrayList;
import java.util.List;

public class Dice implements DiceObservable{

    // Variables
    private List<GameViewObserver> observers = new ArrayList<>();
    public int DiceValue;

    // Constructor
    public Dice(){
        this.DiceValue = DiceValue;
    }

    // Observer Pattern
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
