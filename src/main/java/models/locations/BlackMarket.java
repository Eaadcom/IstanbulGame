package models.locations;

import models.Dice;
import models.Player;
import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.BlackMarketObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackMarket implements Location, BlackMarketObservable {

    // Variables
    private static BlackMarket blackMarket;
    private List<LocationViewObserver> observers = new ArrayList<>();
    public int Location = 1;
    public models.Dice diceOne = new Dice();
    public models.Dice diceTwo = new Dice();



    // Firebase
    public Map<String, Object> getVariableMap(){
        Map<String, Object> BlackMarketData = new HashMap<>();

        BlackMarketData.put("Location", Location);
        //TODO make dice work with firebase ?
        //BlackMarketData.put("diceOne", diceOne); BlackMarketData.put("diceTwo", diceTwo);

        return BlackMarketData;
    }

    public void setData(Map variables){
        this.Location = Math.toIntExact((long) variables.get("Location"));
    }

    // Constructor
    public BlackMarket() {
    }

    public int rollDice(){
        int dice1=(int)(Math.random()*6+1);
        int dice2=(int)(Math.random()*6+1);
        int sum= dice1 + dice2;
        return sum;
    }

    public void addJewel(int number, Player player){
        player.setJewels(player.getJewels() + number);
    }

    // Observer Pattern
    @Override
    public void register(LocationViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (LocationViewObserver gvo : observers){
            gvo.update(this);
        }
    }



    public void onUse(Player player) {

    }

                      // Singleton Pattern
    public static BlackMarket getInstance() {
        if (blackMarket == null) {
            blackMarket = new BlackMarket();
        }
        return blackMarket;
    }
}
