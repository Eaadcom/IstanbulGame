package observers.cards;

import observers.CardViewObserver;

public interface BonusGemstoneDealerObservable {
    void register(CardViewObserver observer);
    void notifyAllObservers();
}
