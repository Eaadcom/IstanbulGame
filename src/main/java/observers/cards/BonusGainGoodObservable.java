package observers.cards;

import observers.GameViewObserver;

public interface BonusGainGoodObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
}
