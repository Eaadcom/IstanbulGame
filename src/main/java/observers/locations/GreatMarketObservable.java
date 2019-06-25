package observers.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;

public interface GreatMarketObservable {
    void register(GameViewObserver observer);
    void notifyAllObservers();
    public int GMgetJewel();
    public int GMgetFabric();
    public int GMgetSpice();
    public int GMgetFruit();
}
