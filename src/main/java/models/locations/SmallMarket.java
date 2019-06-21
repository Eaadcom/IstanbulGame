package models.locations;

import models.Player;
import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.SmallMarketObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmallMarket implements Location, SmallMarketObservable {



    private int jewel  = 0;
    private int fabric = 1;
    private int spice  = 3;
    private int fruit  = 1;

    private int STATE = 0;

    // Firebase
    public Map<String, Object> getVariableMap(){
        Map<String, Object> Data = new HashMap<>();

        Data.put("jewel", jewel); Data.put("fabric", fabric);
        Data.put("spice", spice); Data.put("fruit", fruit);
        Data.put("STATE", STATE);

        return Data;
    }

    public void setData(Map variables){
        this.jewel = Math.toIntExact((long) variables.get("jewel")); this.fabric = Math.toIntExact((long) variables.get("fabric"));
        this.spice = Math.toIntExact((long) variables.get("spice")); this.fruit = Math.toIntExact((long) variables.get("fruit"));
        this.STATE = Math.toIntExact((long) variables.get("STATE"));
    }

    private void setStateValue() {
        if (STATE == 0) {
            this.jewel = 0;
            this.fabric = 1;
            this.spice = 3;
            this.fruit = 1;
        } else if (STATE == 1) {
            this.jewel = 1;
            this.fabric = 1;
            this.spice = 1;
            this.fruit = 2;
        } else if (STATE == 2) {
            this.jewel = 1;
            this.fabric = 1;
            this.spice = 2;
            this.fruit = 1;
        } else if (STATE == 3) {
            this.jewel = 0;
            this.fabric = 1;
            this.spice = 2;
            this.fruit = 2;
        } else if (STATE == 4) {
            this.jewel = 1;
            this.fabric = 0;
            this.spice = 2;
            this.fruit = 2;
        }
    }

    private void stateHandler() {
        if (STATE == 0) {
            STATE++;
            setStateValue();
        } else if (STATE == 1) {
            STATE++;
            setStateValue();
        } else if (STATE == 2) {
            STATE++;
            setStateValue();
        } else if (STATE == 3) {
            STATE++;
            setStateValue();
        } else if (STATE == 4) {
            STATE = 0;
            setStateValue();
        }
    }

    public void confirmPurchase(int fabric, int fruit, int spice, int jewel, Player player){
        if( player.getFabrics() >= fabric && fabric <= this.fabric &&
            player.getFruits()  >= fruit  && fruit  <= this.fruit  &&
            player.getSpices()  >= spice  && spice  <= this.spice  &&
            player.getJewels()  >= jewel  && jewel  <= this.jewel  ){

            player.setFabrics(player.getFabrics() - fabric);
            System.out.println("player fabric: " + player.getFabrics());
            player.setFruits (player.getFruits()  - fruit);
            System.out.println("player fruit: " + player.getFruits());
            player.setSpices (player.getSpices()  - spice);
            System.out.println("player spices: " + player.getSpices());
            player.setJewels (player.getJewels()  - jewel);
            System.out.println("player jewel: " + player.getJewels());

            if(fabric + fruit + spice + jewel == 1){
                //voeg 2 lira toe
                System.out.println("2 lira toegevoegd");
                stateHandler();
            }else if(fabric + fruit + spice + jewel == 2){
                //voeg 5 lira toe
                System.out.println("5 lira toegevoegd");
                stateHandler();
            }else if(fabric + fruit + spice + jewel == 3){
                //voeg 9 lira toe
                System.out.println("9 lira toegevoegd");
                stateHandler();
            }else if(fabric + fruit + spice + jewel == 4){
                //voeg 14 lira toe
                System.out.println("14 lira toegevoegd");
                stateHandler();
            }else if(fabric + fruit + spice + jewel == 5){
                //voeg 20 lira toe
                System.out.println("20 lira toegevoegd");
                stateHandler();
            }
        }
    }

    // GETTERS
    public int getJewel() {
        return jewel;
    }
    public int getFabric() {
        return fabric;
    }
    public int getSpice() {
        return spice;
    }
    public int getFruit() {
        return fruit;
    }

    // Variables
    private static SmallMarket smallMarket;
    private ArrayList<String> demandTiles = new ArrayList<>();
    private List<LocationViewObserver> observers = new ArrayList<>();
    public boolean redAs = false;
    public boolean blueAs = false;
    public boolean greenAs = false;
    public boolean yellowAs = false;
    public boolean whiteAs = false;

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
    public void register(LocationViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (LocationViewObserver gvo : observers){
            gvo.update(this);
        }
    }

    // Singleton Pattern
    public static SmallMarket getInstance() {
        if (smallMarket == null) {
            smallMarket = new SmallMarket();
        }
        return smallMarket;
    }
}
