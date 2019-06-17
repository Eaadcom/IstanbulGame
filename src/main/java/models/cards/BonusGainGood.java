package models.cards;

import models.Player;
import observers.CardViewObserver;
import observers.cards.BonusGainGoodObservable;

import java.util.ArrayList;
import java.util.List;

public class BonusGainGood implements BonusCard, BonusGainGoodObservable {

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

    @Override
    public void onUse(Player player) {

    }
}
