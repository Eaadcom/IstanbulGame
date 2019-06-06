package observers.cards;

import observers.CardViewObserver;

public interface BonusReturnAssistantObservable {
    void register(CardViewObserver observer);
    void notifyAllObservers();
}
