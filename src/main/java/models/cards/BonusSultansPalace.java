package models.cards;

import models.Player;
import observers.CardViewObserver;
import observers.cards.BonusSultansPalaceObservable;

import java.util.ArrayList;
import java.util.List;

public class BonusSultansPalace implements BonusCard, BonusSultansPalaceObservable {
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
