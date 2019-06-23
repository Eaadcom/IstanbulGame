package models.locations;

import models.Player;
import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.PostOfficeObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostOffice implements Location, PostOfficeObservable {

    // Variables
    private static PostOffice postOffice;
    private List<LocationViewObserver> observers = new ArrayList<>();
    public boolean redAs = false;
    public boolean blueAs = false;
    public boolean greenAs = false;
    public boolean yellowAs = false;
    public boolean whiteAs = false;

    // Firebase
    public Map<String, Object> getVariableMap(){
        Map<String, Object> Data = new HashMap<>();

        return Data;
    }

    public void setData(Map variables){
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



    private int jewel  = 1;
    private int fabric = 1;
    private int spice  = 0;
    private int fruit  = 0;
    private int lira   = 4;

    private int STATE = 0;

    public void confirmPurchase(Player player){
        player.setLira(player.getLira()+lira);
        player.setFabrics(player.getFabrics()+fabric);
        player.setSpices(player.getSpices()+spice);
        player.setFruits(player.getFruits()+fruit);
        player.setJewels(player.getJewels()+jewel);
        stateHandler();
    }

    /**
     *  Handles the state of the post office based on the STATE int.
     *  @version 20-6-2019
     *  @Author Thomas van Velzen
     */
    private void setStateValue() {
        if (STATE == 0) {
            this.jewel = 1;
            this.fabric = 1;
            this.spice = 0;
            this.fruit = 0;
            this.lira = 4;
        } else if (STATE == 1) {
            this.jewel = 1;
            this.fabric = 0;
            this.spice = 1;
            this.fruit = 0;
            this.lira = 4;
        } else if (STATE == 2) {
            this.jewel = 1;
            this.fabric = 0;
            this.spice = 1;
            this.fruit = 0;
            this.lira = 3;
        } else if (STATE == 3) {
            this.jewel = 0;
            this.fabric = 0;
            this.spice = 1;
            this.fruit = 1;
            this.lira = 3;
        } else if (STATE == 4) {
            this.jewel = 0;
            this.fabric = 0;
            this.spice = 1;
            this.fruit = 1;
            this.lira = 2;
        }
    }

    /**
     *  updates the STATE int to handle the post office state.
     *  @version 20-6-2019
     *  @Author Thomas van Velzen
     */
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
            int dup =0;
        }
    }

    public int PostOfficeGetJewel() {
        return jewel;
    }

    public int PostOfficeGetFabric() {
        return fabric;
    }

    public int PostOfficeGetSpice() {
        return spice;
    }

    public int PostOfficeGetFruit() {
        return fruit;
    }

    public int PostOfficeGetLira() {
        return lira;
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
    public static PostOffice getInstance() {
        if (postOffice == null) {
            postOffice = new PostOffice();
        }
        return postOffice;
    }
}
