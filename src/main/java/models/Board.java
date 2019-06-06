package models;

import models.cards.BonusCard;
import observers.BoardObservable;
import observers.GameViewObserver;

import java.util.ArrayList;
import java.util.List;

public class Board implements BoardObservable {

    // Variables
    private List<GameViewObserver> observers = new ArrayList<>();
    public ArrayList<BonusCard> BonusCards =  new ArrayList<>();
    public ArrayList<BonusCard> PlayerCardChoice = new ArrayList<>();
    private ArrayList<Player> Players = new ArrayList<>();
    private ArrayList<Dice> dice = new ArrayList<>();
    private Governor governor = new Governor();
    private Smuggler smuggler = new Smuggler();

    // Constructor
    public Board(){

    }

    // Observer Pattern
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
