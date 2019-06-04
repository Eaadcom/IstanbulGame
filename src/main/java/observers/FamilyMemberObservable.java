package observers;

public interface FamilyMemberObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
}
