package models.cards;

import observers.CardViewObserver;
import observers.GameViewObserver;
import observers.cards.BonusPostOfficeObservable;

import java.util.ArrayList;
import java.util.List;

public class BonusPostOffice implements BonusPostOfficeObservable {
    private List<CardViewObserver> observers = new ArrayList<>();


    @Override
    public void register(CardViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (CardViewObserver gvo : observers){
            gvo.update(this);
        }
    }
}
