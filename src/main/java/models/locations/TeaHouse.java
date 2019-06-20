package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.TeaHouseObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeaHouse implements Location, TeaHouseObservable {

    // Variables
    private static TeaHouse teaHouse;
    public int Location = 15;
    public boolean redAs = false;
    public boolean blueAs = false;
    public boolean greenAs = false;
    public boolean yellowAs = false;
    public boolean whiteAs = false;

    // Firebase
    public Map<String, Object> getVariableMap(){
        Map<String, Object> Data = new HashMap<>();

        Data.put("Location", Location);

        return Data;
    }

    public void setData(Map variables){
        this.Location = Math.toIntExact((long) variables.get("Location"));
    }


    public int teahouseNumberChoice;
    public int teahouseDiceNumber;
    private List<LocationViewObserver> observers = new ArrayList<>();
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

    // Constructor
    public TeaHouse(){
    }

    //setter
    public void setTeahouseNumberChoice(int teahouseNumberChoice) {
        this.teahouseNumberChoice = teahouseNumberChoice;
    }

    //getter
    public int getTeahouseNumberChoice() {
        return teahouseNumberChoice;
    }

    public void setTeahouseDiceNumber(int teahouseDiceNumber) {
        this.teahouseDiceNumber = teahouseDiceNumber;
    }

    public int getTeahouseDiceNumber(){
        return teahouseDiceNumber;
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
    public static TeaHouse getInstance() {
        if (teaHouse == null) {
            teaHouse = new TeaHouse();
        }
        return teaHouse;
    }
}


