package observers.cards;

import observers.GameViewObserver;

public interface BonusGemstoneDealerObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
}
