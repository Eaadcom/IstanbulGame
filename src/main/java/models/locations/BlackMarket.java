package models.locations;

import observers.GameViewObserver;
import observers.locations.BlackMarketObservable;

import java.util.ArrayList;
import java.util.List;

public class BlackMarket implements Location, BlackMarketObservable {

   public int Location = 1;
   public models.Dice diceOne;
   public models.Dice diceTwo;

    public BlackMarket(models.Dice diceOne, models.Dice diceTwo) {
        this.diceOne = diceOne;
        this.diceTwo = diceTwo;

    }

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
