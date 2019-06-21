package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.GemstoneDealerObservable;
import views.GameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GemstoneDealer implements Location, GemstoneDealerObservable {

    // Variables
    private static GemstoneDealer gemstoneDealer;
    private List<GameViewObserver> observers = new ArrayList<>();
    public boolean redAs = false;
    public boolean blueAs = false;
    public boolean greenAs = false;
    public boolean yellowAs = false;
    public boolean whiteAs = false;
    private int gemstonePrice = 12;

    /**
     *  Adds the value of the gemstone dealer to the firebase server.
     *  @version 20-6-2019
     *  @Author Thomas van Velzen
     */
    // Firebase
    public Map<String, Object> getVariableMap(){
        Map<String, Object> Data = new HashMap<>();

        Data.put("gemstonePrice", gemstonePrice);

        return Data;
    }

    /**
     *  Sets the value of the gemstone dealer from the firebase server.
     *  @version 20-6-2019
     *  @Author Thomas van Velzen
     *  @param variables
     *  @param variables
     */
    public void setData(Map variables){
        this.gemstonePrice = Math.toIntExact((long) variables.get("gemstonePrice"));
    }

    public boolean color(String color) {
        boolean myColor;
        if (color == "red") {
            myColor = redAs;
        } else if( color == "blue"){
            myColor = blueAs;
        } else if(color == "green" ){
            myColor = greenAs;
        } else if ( color == "yellow"){
            myColor = yellowAs;
        } else if (color == "white"){
            myColor = whiteAs;
        }
        else{
            myColor = false;
        }
        return myColor;
    }

    public void setColor(String color, boolean set){
        if (color == "red"){
            redAs = set;
        } else if(color == "blue"){
            blueAs = set;
        } else if (color == "green"){
            greenAs = set;
        } else if (color == "yellow"){
            yellowAs = set;
        } else if (color == "white"){
            whiteAs = set;
        }
    }



    // Observer Pattern
    @Override
    public void register(GameViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        if (!observers.contains(GameView.getInstance())){
            register(GameView.getInstance());
        }
        for (GameViewObserver gvo : observers){
            gvo.update(this);
        }
    }

    public int getGemstonePrice() {
        return gemstonePrice;
    }

    public void updatePrice(int newPrice) {
        gemstonePrice = newPrice;
        notifyAllObservers();
    }

    // Singleton Pattern
    public static GemstoneDealer getInstance() {
        if (gemstoneDealer == null) {
            gemstoneDealer = new GemstoneDealer();
        }
        return gemstoneDealer;
    }
}
