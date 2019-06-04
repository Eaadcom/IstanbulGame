package observers.cards;

import observers.GameViewObserver;

public interface BonusSmallMarketObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
    public String getScore();
}
