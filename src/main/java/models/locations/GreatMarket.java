package models.locations;

import models.Player;
import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.GreatMarketObservable;
import views.GameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GreatMarket implements Location, GreatMarketObservable {



    private int jewel  = 2;
    private int fabric = 2;
    private int spice  = 0;
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

    //1: 2, 2, 0, 1
    //2: 3, 1, 0, 1
    //3: 2, 2, 1, 0
    //4: 2, 1, 1, 1
    //5: 3, 1, 1, 0

    /**
     *  Handles the state of the large market based on the STATE int.
     *  @version 20-6-2019
     *  @Author Thomas van Velzen
     */
    private void setStateValue() {
        if (STATE == 0) {
            this.jewel = 2;
            this.fabric = 2;
            this.spice = 0;
            this.fruit = 1;
        } else if (STATE == 1) {
            this.jewel = 3;
            this.fabric = 1;
            this.spice = 0;
            this.fruit = 1;
        } else if (STATE == 2) {
            this.jewel = 2;
            this.fabric = 2;
            this.spice = 1;
            this.fruit = 0;
        } else if (STATE == 3) {
            this.jewel = 2;
            this.fabric = 1;
            this.spice = 1;
            this.fruit = 1;
        } else if (STATE == 4) {
            this.jewel = 3;
            this.fabric = 1;
            this.spice = 1;
            this.fruit = 0;
        }
    }

    /**
     *  updates the STATE int to handle the large market state.
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
     *  Confirms the purchase of the large market. It checks if the player has enough resources
     *  and updates the price if so. It also adds lira and removes the goods from the player.
     *  @version 20-6-2019
     *  @Author Thomas van Velzen
     * @param fabric
     * @param fruit
     * @param spice
     * @param jewel
     * @param player
     */
    public void GMconfirmPurchase(int fabric, int fruit, int spice, int jewel, Player player){
        if( player.getFabrics() >= fabric && fabric <= this.fabric &&
            player.getFruits()  >= fruit  && fruit  <= this.fruit  &&
            player.getSpices()  >= spice  && spice  <= this.spice  &&
            player.getJewels()  >= jewel  && jewel  <= this.jewel  ){

            player.setFabrics(player.getFabrics() - fabric);
            System.out.println("GM player fabric: " + player.getFabrics());
            player.setFruits (player.getFruits()  - fruit);
            System.out.println("GM player fruit: " + player.getFruits());
            player.setSpices (player.getSpices()  - spice);
            System.out.println("GM player spices: " + player.getSpices());
            player.setJewels (player.getJewels()  - jewel);
            System.out.println("GM player jewel: " + player.getJewels());

            if(fabric + fruit + spice + jewel == 1){
                player.setLira(player.getLira()+3);
                System.out.println("3 lira toegevoegd");
                stateHandler();
            }else if(fabric + fruit + spice + jewel == 2){
                player.setLira(player.getLira()+7);
                System.out.println("7 lira toegevoegd");
                stateHandler();
            }else if(fabric + fruit + spice + jewel == 3){
                player.setLira(player.getLira()+12);
                System.out.println("12 lira toegevoegd");
                stateHandler();
            }else if(fabric + fruit + spice + jewel == 4){
                player.setLira(player.getLira()+18);
                System.out.println("18 lira toegevoegd");
                stateHandler();
            }else if(fabric + fruit + spice + jewel == 5){
                player.setLira(player.getLira()+25);
                System.out.println("25 lira toegevoegd");
                stateHandler();
            }
        }
    }

    // GETTERS
    public int GMgetJewel() {
        return this.jewel;
    }
    public int GMgetFabric() {
        return this.fabric;
    }
    public int GMgetSpice() {
        return this.spice;
    }
    public int GMgetFruit() { return this.fruit; }

    // Variables
    private static GreatMarket greatMarket;
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
    public static GreatMarket getInstance() {
        if (greatMarket == null) {
            greatMarket = new GreatMarket();
        }
        return greatMarket;
    }
}
