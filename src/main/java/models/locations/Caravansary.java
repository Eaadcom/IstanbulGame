package models.locations;

import observers.GameViewObserver;
import observers.locations.CaravansaryObservable;

import java.util.ArrayList;
import java.util.List;

public class Caravansary implements Location, CaravansaryObservable {
    private List<GameViewObserver> observers = new ArrayList<>();

    int location = 2;

    int PlayerCardChoice;

    ArrayList usedBonusCards = new ArrayList();


    public Caravansary(){
        this.usedBonusCards = usedBonusCards;
        this.location = location;
        this.PlayerCardChoice = PlayerCardChoice;

    }

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
