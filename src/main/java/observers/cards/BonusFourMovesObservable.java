package observers.cards;

import observers.CardViewObserver;

public interface BonusFourMovesObservable {
    void register(CardViewObserver observer);
    void notifyAllObservers();
}
