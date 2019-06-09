package models.cards;

import observers.CardViewObserver;
import observers.GameViewObserver;
import observers.cards.BonusGetLiraObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the bonuscard GetLira, when a player uses this card he or she gets +5 lira on her or she lira balance
 * @author Joeri van Duijkeren
 * @version 4 june 2019
 */

public class BonusGetLira implements BonusGetLiraObservable {

    private List<CardViewObserver> observers = new ArrayList<>();

    int lira = 5;
    int cardSupply = 2;


    public BonusGetLira(int lira) {
        this.lira = lira;

    }


    @Override
    public void register(CardViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (CardViewObserver gvo : observers){
            gvo.update(this);
        }
    }
}
