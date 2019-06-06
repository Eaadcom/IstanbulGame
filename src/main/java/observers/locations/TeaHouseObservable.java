package observers.locations;

import observers.LocationViewObserver;

public interface TeaHouseObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
