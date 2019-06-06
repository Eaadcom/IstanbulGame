package observers.locations;

import observers.LocationViewObserver;

public interface CaravansaryObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
