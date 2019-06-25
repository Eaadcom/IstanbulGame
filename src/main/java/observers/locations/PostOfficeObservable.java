package observers.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import views.GameView;

public interface PostOfficeObservable {
    void register(GameViewObserver observer);
    void notifyAllObservers();
    public int PostOfficeGetJewel();

    public int PostOfficeGetFabric();

    public int PostOfficeGetSpice();

    public int PostOfficeGetFruit();

    public int PostOfficeGetLira();
}
