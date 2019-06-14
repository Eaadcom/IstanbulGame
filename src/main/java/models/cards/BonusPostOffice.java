package models.cards;

import models.Player;
import observers.CardViewObserver;
import observers.cards.BonusPostOfficeObservable;

import java.util.ArrayList;
import java.util.List;

public class BonusPostOffice implements BonusCard, BonusPostOfficeObservable {
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

    @Override
    public void onUse(Player player) {

    }
}
