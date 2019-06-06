package observers.cards;

import observers.CardViewObserver;

public interface BonusSmallMarketObservable {
    void register(CardViewObserver observer);
    void notifyAllObservers();
}
