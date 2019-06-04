package observers.locations;

import observers.GameViewObserver;

public interface CaravansaryObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
    public String getScore();
}
