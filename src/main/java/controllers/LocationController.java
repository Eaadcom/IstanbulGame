package controllers;


import java.util.Random;
import models.Dice;
import models.locations.BlackMarket;
import models.locations.TeaHouse;
import java.util.Scanner;

public class LocationController {
    int location;
    int DiceResult;

    BlackMarket blackMarket = new BlackMarket();
    TeaHouse teaHouse = new TeaHouse();
    Dice dice = new Dice();


    Scanner scanner = new Scanner(System.in);

    public void onUse(){

        switch(location){
            case 1: {

                blackMarket.diceOne = setDiceValue();
                blackMarket.diceTwo = setDiceValue();

                DiceResult = blackMarket.diceOne + blackMarket.diceTwo;


                if(DiceResult < 7 ){

                }else if(DiceResult == 7|| DiceResult == 8){


                }else if(DiceResult == 9 || DiceResult == 10){

                }else if(DiceResult == 11 || DiceResult == 12){

                }



            }
            case 2: {

            }
            case 15:
                teaHouse.   numberChoice = setNumberChoice();

                teaHouse.diceOne = setDiceValue();
                teaHouse.diceTwo = setDiceValue();

                DiceResult = teaHouse.diceOne + teaHouse.diceTwo;

                if(teaHouse.numberChoice > DiceResult){

                }

            }

        }



    public int setNumberChoice(){

        int Choice = scanner.nextInt();
        return Choice;
    }

    public int setDiceValue(){

        int DiceValue = (int)(Math.random() * 6 + 1);

        return DiceValue;
    }



}
