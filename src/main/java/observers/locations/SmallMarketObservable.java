package observers.locations;

import observers.GameViewObserver;

public interface SmallMarketObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
}
