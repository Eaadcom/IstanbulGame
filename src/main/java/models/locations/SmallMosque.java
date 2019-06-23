package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.SmallMosqueObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmallMosque implements Location, SmallMosqueObservable {

    // Variables
    public static SmallMosque smallMosque;
    private List<LocationViewObserver> observers = new ArrayList<>();
    public boolean redAs = false;
    public boolean blueAs = false;
    public boolean greenAs = false;
    public boolean yellowAs = false;
    public boolean whiteAs = false;

<<<<<<< Updated upstream
    public int bought;
=======
    public int boughtGreen = 2;
    public int boughtRed = 2;



>>>>>>> Stashed changes

    // Firebase
    public Map<String, Object> getVariableMap(){
        Map<String, Object> Data = new HashMap<>();

        return Data;
    }

    public void setData(Map variables){
    }
<<<<<<< Updated upstream
=======
    
>>>>>>> Stashed changes

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
    public static SmallMosque getInstance() {
        if (smallMosque == null) {
            smallMosque = new SmallMosque();
        }
        return smallMosque;
    }
}
