package observers.cards;

import observers.CardViewObserver;

public interface BonusReturnMemberObservable {
    void register(CardViewObserver observer);
    void notifyAllObservers();
}
