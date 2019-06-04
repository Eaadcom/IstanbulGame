package observers;

public interface DiceObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
}
