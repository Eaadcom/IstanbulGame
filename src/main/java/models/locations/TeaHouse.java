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

    public TeaHouse(){
        this.teahouse = teahouse;
        this.numberChoice = numberChoice;
        this.diceOne = diceOne;
        this.diceTwo = diceTwo;
        this.Location = Location;
    }

    TeaHouse teahouse = new TeaHouse();

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


