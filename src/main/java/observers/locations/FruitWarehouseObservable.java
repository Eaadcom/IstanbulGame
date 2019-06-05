package observers.locations;

import observers.GameViewObserver;

public interface FruitWarehouseObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
}
