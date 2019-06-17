package observers;

public interface GameObservable {
    public void register(GameViewLobbyViewObserver observer);
    public void notifyAllObservers();
}
