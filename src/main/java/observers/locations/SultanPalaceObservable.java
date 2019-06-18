package observers.locations;

import observers.GameViewObserver;

public interface SultanPalaceObservable {
    void register(GameViewObserver observer);
    void notifyAllObservers();
    int getJewelPrice();
    int getFabricPrice();
    int getSpicePrice();
    int getFruitPrice();
    int getChoiceAmount();
}
