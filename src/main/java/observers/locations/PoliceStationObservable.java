package observers.locations;

import observers.GameViewObserver;

public interface PoliceStationObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
}
