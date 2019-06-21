package models;

import observers.FamilyMemberObservable;
import observers.GameViewObserver;

import java.util.ArrayList;
import java.util.List;

public class FamilyMember implements FamilyMemberObservable {

    // Variables
    private List<GameViewObserver> observers = new ArrayList<>();

//    public String location = "tile12";
//
//    public void setLocation(String newLocation) {
//        location = newLocation;
//    }

    public int location = 12;

    public void setLocation(int newLocation) {
        location = newLocation;
    }


    // Observer pattern
    @Override
    public void register(GameViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (GameViewObserver gvo : observers){
            gvo.update(this);
        }
    }
}
