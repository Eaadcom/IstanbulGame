package observers;

public interface MainMenuObservable {
    public void register(MenuViewObserver observer);
    public void notifyAllObservers();
    public String getUsername();
}
