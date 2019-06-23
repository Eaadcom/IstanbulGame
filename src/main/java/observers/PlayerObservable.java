package observers;

public interface PlayerObservable {
    public void register(GameViewObserver observer);
    public void notifyAllObservers();
    public int getSpices();

    public int getFruits();

    public int getJewels();

    public int getFabrics();

    public int getRubies() ;

    public int getMaxJewels();

    public int getMaxFruits();

    public int getMaxSpices();

    public int getMaxFabrics();

    public int getCarUpgrades();

    public int getPlayerID();
    public int getLira();
}
