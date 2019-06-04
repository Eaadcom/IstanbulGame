package observers.cards;

import observers.GameViewObserver;

public interface BonusPostOfficeObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
    public String getScore();
}
