package observers.locations;

import observers.GameViewObserver;

public interface GreatMosqueObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
}
