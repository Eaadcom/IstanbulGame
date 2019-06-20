package observers.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;

public interface GemstoneDealerObservable {
    void register(GameViewObserver observer);
    void notifyAllObservers();
    int  getGemstonePrice();
}
