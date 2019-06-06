package observers.cards;

import observers.CardViewObserver;

public interface BonusSultansPalaceObservable {
    void register(CardViewObserver observer);
    void notifyAllObservers();
}
