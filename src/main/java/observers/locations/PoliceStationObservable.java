package observers.locations;

import observers.LocationViewObserver;

public interface PoliceStationObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
