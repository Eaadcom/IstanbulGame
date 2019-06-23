package observers.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;

public interface SmallMarketObservable {
    void register(GameViewObserver observer);
    void notifyAllObservers();
    public int getJewel();
    public int getFabric();
    public int getSpice();
    public int getFruit();
}
