package observers.cards;

import observers.CardViewObserver;

public interface BonusPostOfficeObservable {
    void register(CardViewObserver observer);
    void notifyAllObservers();
}
