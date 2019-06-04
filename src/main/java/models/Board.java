package models;

import models.cards.BonusCard;

import java.util.ArrayList;

public class Board {

    public ArrayList<Board> BonusCards =  new ArrayList<Board>();
    public ArrayList<Board> PlayerCardChoice = new ArrayList<Board>();


    public Board(){
        this.BonusCards = BonusCards;
        this.PlayerCardChoice = PlayerCardChoice;

    }

    Board board = new Board();
}
