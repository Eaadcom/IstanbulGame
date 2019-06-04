package observers.locations;

import observers.GameViewObserver;

public interface WainwrightObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
}
