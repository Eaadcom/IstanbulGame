package models.locations;

import observers.GameViewObserver;
import observers.locations.BlackMarketObservable;

import java.util.ArrayList;
import java.util.List;

public class BlackMarket implements Location, BlackMarketObservable {

   public int location = 1;
   public models.Dice diceOne = new models.Dice();
   public models.Dice diceTwo = new models.Dice();



    //BlackMarket blackMarket = new BlackMarket();

    private List<GameViewObserver> observers = new ArrayList<>();


    @Override
    public void register(GameViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (GameViewObserver gvo : observers){
            gvo.update(this);
        }
    }
}
