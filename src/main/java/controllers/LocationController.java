package controllers;


import java.util.Random;
import models.Dice;
import models.locations.BlackMarket;
import models.locations.TeaHouse;
import java.util.Scanner;

public class LocationController {
    int location;

    BlackMarket blackMarket = new BlackMarket();
    TeaHouse teaHouse = new TeaHouse();
    Dice dice = new Dice();


    Scanner scanner = new Scanner(System.in);

    public void onUse(){

        switch(location){
            case 1: {

            }
            case 15:
                teaHouse.numberChoice = setNumberChoice();

                teaHouse.diceOne = setDiceValue();
                teaHouse.diceTwo = setDiceValue();

                int DiceResult = teaHouse.diceOne + teaHouse.diceTwo;

                if(teaHouse.numberChoice > DiceResult){

                }

            }

        }



    public int setNumberChoice(){

        int Choice = scanner.nextInt();
        return Choice;
    }

    public int setDiceValue(){

        int DiceValue = (int)(Math.random() * 12 + 1);

        return DiceValue;
    }



}
