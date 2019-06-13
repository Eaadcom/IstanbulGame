package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.TeaHouseObservable;

import java.util.ArrayList;
import java.util.List;

public class TeaHouse implements Location, TeaHouseObservable {

    // Variables
    private static TeaHouse teaHouse;
    public int Location = 15;




    public int teahouseNumberChoice;
    public int teahouseDiceNumber;
    private List<LocationViewObserver> observers = new ArrayList<>();

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


