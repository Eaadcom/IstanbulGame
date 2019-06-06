package observers.cards;

import observers.CardViewObserver;

public interface BonusGainGoodObservable {
    void register(CardViewObserver observer);
    void notifyAllObservers();
}
