package observers;

public interface BoardObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
}
