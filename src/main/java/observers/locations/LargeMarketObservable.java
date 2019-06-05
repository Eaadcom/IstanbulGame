package observers.locations;

import observers.GameViewObserver;

public interface LargeMarketObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
    public String getScore();
}