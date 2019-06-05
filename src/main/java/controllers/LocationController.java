package controllers;


import java.util.Random;
import models.Dice;
import models.Player;
import models.locations.BlackMarket;
import models.locations.Caravansary;
import models.locations.TeaHouse;
import java.util.Scanner;

public class LocationController {
    int location;
    int DiceResult;
    public int CardNumber1;
    public int CardNumber2;

    BlackMarket blackMarket = new BlackMarket();
    TeaHouse teaHouse = new TeaHouse();
    Caravansary caravansary = new Caravansary();
    CardController cardController = new CardController();
    models.Player player = new models.Player("Name");
    models.Board board = new models.Board();


    Scanner scanner = new Scanner(System.in);

    public void onUse() {

        switch (location) {
            // BlackMarket Functie
            case 1: {

            }
            // Caravansary Functie
            case 2: {

                CardNumber1 = cardController.getRandomCard() - 1;
                CardNumber2 = cardController.getRandomCard() - 1;

                Boolean SameCard = cardController.CardChecker();

                while(SameCard = true){
                    CardNumber2 = cardController.getRandomCard() - 1;
                }

                board.PlayerCardChoice.add(board.BonusCards.get(CardNumber1));
                board.PlayerCardChoice.add(board.BonusCards.get(CardNumber2));





            }
            case 15:
                teaHouse.numberChoice = setNumberChoice();

                teaHouse.diceOne = setDiceValue();
                teaHouse.diceTwo = setDiceValue();

                DiceResult = teaHouse.diceOne + teaHouse.diceTwo;

                if (teaHouse.numberChoice > DiceResult) {

                }

        }

    }


    public int setNumberChoice() {

        int Choice = scanner.nextInt();
        return Choice;
    }

    public int setDiceValue() {

        int DiceValue = (int) (Math.random() * 6 + 1);

        return DiceValue;
    }

    public void BlackMarketDice(){
        blackMarket.diceOne.DiceValue = setDiceValue();
        blackMarket.diceTwo.DiceValue = setDiceValue();

        DiceResult = blackMarket.diceOne.DiceValue + blackMarket.diceTwo.DiceValue;


        if (DiceResult < 7) {

            player.jewels = player.jewels;

        } else if (DiceResult == 7 || DiceResult == 8) {
            player.jewels += 1;

        } else if (DiceResult == 9 || DiceResult == 10) {
            player.jewels += 2;

        } else if (DiceResult == 11 || DiceResult == 12) {
            player.jewels+= 3;

        }


    }
    public void BlackMarketChoice(int BlackMarketChoice){

        if(BlackMarketChoice == 1){
            player.spices += 1;
        }else if(BlackMarketChoice == 2){
            player.fruits += 1;
        }else if(BlackMarketChoice == 3){
            player.fabrics += 1;

        }

    }
    }

