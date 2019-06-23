package models;

import observers.FamilyMemberObservable;
import observers.GameViewObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * This model stores information about the family member.
 * The most important information is the position of the familymember on the board.
 * @author Stan Hogenboom
 * @version 20-6-2019
 */
public class FamilyMember implements FamilyMemberObservable {

    // Variables
    private List<GameViewObserver> observers = new ArrayList<>();
    
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
