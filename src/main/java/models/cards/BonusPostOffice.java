package models.cards;

import observers.GameViewObserver;
import observers.cards.BonusPostOfficeObservable;

import java.util.ArrayList;
import java.util.List;

public class BonusPostOffice implements BonusPostOfficeObservable {
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
