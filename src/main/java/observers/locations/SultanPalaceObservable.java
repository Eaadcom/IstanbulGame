package observers.locations;

import observers.LocationViewObserver;

public interface SultanPalaceObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
