package observers;

public interface GovernorObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
}
