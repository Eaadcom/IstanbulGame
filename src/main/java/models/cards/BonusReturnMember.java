package models.cards;

import observers.CardViewObserver;
import observers.GameViewObserver;
import observers.cards.BonusReturnMemberObservable;

import java.util.ArrayList;
import java.util.List;

public class BonusReturnMember implements BonusReturnMemberObservable {
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
