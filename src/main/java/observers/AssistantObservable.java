package observers;

public interface AssistantObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
}
