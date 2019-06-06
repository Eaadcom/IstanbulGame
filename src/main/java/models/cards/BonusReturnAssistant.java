package models.cards;

import observers.CardViewObserver;
import observers.GameViewObserver;
import observers.cards.BonusReturnAssistantObservable;

import java.util.ArrayList;
import java.util.List;

public class BonusReturnAssistant implements BonusReturnAssistantObservable {
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
