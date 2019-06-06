package models.cards;

import observers.CardViewObserver;;
import observers.cards.BonusFourMovesObservable;

import java.util.ArrayList;
import java.util.List;

public class BonusFourMoves implements BonusFourMovesObservable {

    // Variables
    private List<CardViewObserver> observers = new ArrayList<>();

    // Observer pattern
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
