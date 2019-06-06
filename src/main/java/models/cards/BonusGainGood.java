package models.cards;

import controllers.PlayerController;
import observers.CardViewObserver;
import observers.GameViewObserver;
import observers.cards.BonusGainGoodObservable;

import java.util.ArrayList;
import java.util.List;

public class BonusGainGood implements BonusGainGoodObservable {

    // Variables
    private List<CardViewObserver> observers = new ArrayList<>();

    // Observer Pattern
    @Override
    public void register(CardViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (CardViewObserver cvo : observers){
            cvo.update(this);
        }
    }
}
