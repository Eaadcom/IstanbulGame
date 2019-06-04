package models;

import models.cards.BonusCard;
import observers.BoardObservable;
import observers.GameViewObserver;

import java.util.ArrayList;
import java.util.List;

public class Board implements BoardObservable {
    private List<GameViewObserver> observers = new ArrayList<>();
    public ArrayList<Board> BonusCards =  new ArrayList<Board>();
    public ArrayList<Board> PlayerCardChoice = new ArrayList<Board>();


    public Board(){
        this.BonusCards = BonusCards;
        this.PlayerCardChoice = PlayerCardChoice;

    }

    Board board = new Board();

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
