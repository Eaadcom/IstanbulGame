package observers.locations;

import observers.GameViewObserver;

public interface FabricWarehouseObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
    public String getScore();
}
