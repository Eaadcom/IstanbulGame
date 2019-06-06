package observers.cards;

import observers.CardViewObserver;

public interface BonusGetLiraObservable {
    void register(CardViewObserver observer);
    void notifyAllObservers();
}
