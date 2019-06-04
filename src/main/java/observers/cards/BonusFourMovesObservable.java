package observers.cards;

import observers.GameViewObserver;

public interface BonusFourMovesObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
}
