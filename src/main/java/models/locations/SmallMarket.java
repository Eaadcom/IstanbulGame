package models.locations;

import models.Player;
import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.SmallMarketObservable;
import views.GameView;

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

    /**
     *  Handles the state of the small market based on the STATE int.
     *  @version 20-6-2019
     *  @Author Thomas van Velzen
     */

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

    /**
     *  updates the STATE int to handle the small market state.
     *  @version 20-6-2019
     *  @Author Thomas van Velzen
     */
    private void stateHandler() {
        if (STATE == 0) {
            STATE++;
            setStateValue();
            notifyAllObservers();
        } else if (STATE == 1) {
            STATE++;
            setStateValue();
            notifyAllObservers();
        } else if (STATE == 2) {
            STATE++;
            setStateValue();
            notifyAllObservers();
        } else if (STATE == 3) {
            STATE++;
            setStateValue();
            notifyAllObservers();
        } else if (STATE == 4) {
            STATE = 0;
            setStateValue();
            notifyAllObservers();
        }
    }

    /**
     *  Confirms the purchase of the small market. It checks if the player has enough resources
     *  and updates the price if so. It also adds lira and removes the goods from the player.
     *  @version 20-6-2019
     *  @Author Thomas van Velzen
     * @param fabric
     * @param fruit
     * @param spice
     * @param jewel
     * @param player
     */
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
                player.setLira(player.getLira()+2);
                System.out.println("2 lira toegevoegd");
                stateHandler();
            }else if(fabric + fruit + spice + jewel == 2){
                player.setLira(player.getLira()+5);
                System.out.println("5 lira toegevoegd");
                stateHandler();
            }else if(fabric + fruit + spice + jewel == 3){
                player.setLira(player.getLira()+9);
                System.out.println("9 lira toegevoegd");
                stateHandler();
            }else if(fabric + fruit + spice + jewel == 4){
                player.setLira(player.getLira()+14);
                System.out.println("14 lira toegevoegd");
                stateHandler();
            }else if(fabric + fruit + spice + jewel == 5){
                player.setLira(player.getLira()+20);
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
    private List<GameViewObserver> observers = new ArrayList<>();





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

    // Singleton Pattern
    public static SmallMarket getInstance() {
        if (smallMarket == null) {
            smallMarket = new SmallMarket();
        }
        return smallMarket;
    }
}
