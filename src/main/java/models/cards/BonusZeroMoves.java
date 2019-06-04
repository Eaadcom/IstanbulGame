package models.cards;

import observers.GameViewObserver;
import observers.cards.BonusZeroMovesObservable;

import java.util.ArrayList;
import java.util.List;

public class BonusZeroMoves implements BonusZeroMovesObservable {
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
