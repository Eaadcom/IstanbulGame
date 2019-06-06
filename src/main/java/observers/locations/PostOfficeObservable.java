package observers.locations;

import observers.LocationViewObserver;

public interface PostOfficeObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
