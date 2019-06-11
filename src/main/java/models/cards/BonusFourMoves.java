package models.cards;

import models.locations.Location;
import observers.CardViewObserver;;
import observers.cards.BonusFourMovesObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the BonusFourMoves bonus card. When the player uses this card he or she can make 1 or 2 steps.
 * @author Joeri van Duijkeren
 * @version 6 june 2019
 */
public class BonusFourMoves implements BonusFourMovesObservable {

    // Variables
    private List<CardViewObserver> observers = new ArrayList<>();





    // Observer pattern
    @Override
    public void register(CardViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (CardViewObserver cvo : observers){
            cvo.update(this);
        }
    }
}
