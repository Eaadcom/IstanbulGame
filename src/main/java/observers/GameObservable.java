package observers;

public interface GameObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
}
