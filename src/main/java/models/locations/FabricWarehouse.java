package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.FabricWarehouseObservable;

import java.util.ArrayList;
import java.util.List;

public class FabricWarehouse implements Location, FabricWarehouseObservable {

    // Variables
    private static FabricWarehouse fabricWarehouse;
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
    public static FabricWarehouse getInstance() {
        if (fabricWarehouse == null) {
            fabricWarehouse = new FabricWarehouse();
        }
        return fabricWarehouse;
    }
}
