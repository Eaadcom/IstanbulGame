package observers.cards;

import observers.CardViewObserver;

public interface BonusZeroMovesObservable {
    void register(CardViewObserver observer);
    void notifyAllObservers();
}
