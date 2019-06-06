package observers.locations;

import observers.LocationViewObserver;

public interface WainwrightObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
