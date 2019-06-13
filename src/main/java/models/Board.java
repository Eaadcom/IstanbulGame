package models;

import models.cards.BonusCard;
import observers.BoardObservable;
import observers.GameViewObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Board implements BoardObservable {

    // Variables
    private List<GameViewObserver> observers = new ArrayList<>();
    public ArrayList<BonusCard> bonusCards =  new ArrayList<>();
    public ArrayList<BonusCard> playerBonusCards = new ArrayList<>();
    public List<Player> players = new LinkedList<>();
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

    public Player getCurrentPlayerTurn() {
        return null;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
