package observers;

public interface SmugglerObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
}
