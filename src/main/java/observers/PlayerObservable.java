package observers;

public interface PlayerObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
    public String getScore();
}
