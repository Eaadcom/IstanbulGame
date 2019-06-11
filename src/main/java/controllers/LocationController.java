package controllers;


import java.util.Random;
import models.Dice;
import models.Player;
import models.locations.BlackMarket;
import models.locations.Caravansary;
import models.locations.FruitWarehouse;
import models.locations.TeaHouse;
import java.util.Scanner;

public class LocationController {
    int location;
    int DiceResult;
    public int cardNumber1;
    public int cardNumber2;

    public BlackMarket blackMarket = new BlackMarket();
    public TeaHouse teaHouse;
    public FruitWarehouse fruitWarehouse;
    public CardController cardController;
    public PlayerController playerController = new PlayerController();
    public models.Player player = new models.Player("Name");
    public models.Board board = new models.Board();


    Scanner scanner = new Scanner(System.in);


    public void onUse() {


    }


    public int setNumberChoice() {

        int Choice = scanner.nextInt();
        return Choice;
    }

    public int setDiceValue() {

        int DiceValue = (int) (Math.random() * 6 + 1);

        return DiceValue;
    }

    public void BlackMarketDice() {
        blackMarket.diceOne.DiceValue = setDiceValue();
        blackMarket.diceTwo.DiceValue = setDiceValue();

        DiceResult = blackMarket.diceOne.DiceValue + blackMarket.diceTwo.DiceValue;

        System.out.println("Je hebt " + DiceResult + " gegooit");

        if (DiceResult < 7) {
            player.jewels = player.jewels;
        } else if (DiceResult == 7 || DiceResult == 8) {
            if (playerController.CargoCheckJewels(1) == true) {
                player.jewels += 1;
            }

        } else if (DiceResult == 9 || DiceResult == 10) {
            if (playerController.CargoCheckJewels(2)) {
                player.jewels += 2;
            } else if (playerController.CargoCheckJewels(1)) {
                player.jewels += 1;
            }

        } else if (DiceResult == 11 || DiceResult == 12) {
            if (playerController.CargoCheckJewels(3)) {
                player.jewels += 3;
            } else if (playerController.CargoCheckJewels(2)) {
                player.jewels += 2;
            } else if (playerController.CargoCheckJewels(1)) {
                player.jewels += 1;
            }
        }
        System.out.println("er zijn " + player.jewels + " Jewels toegevoegd!");

    }

    public void BlackMarketChoice(int BlackMarketChoice) {

        if (BlackMarketChoice == 1) {
            if (playerController.CargoCheckSpices(1) == true) {
                player.spices += 1;

            }
        } else if (BlackMarketChoice == 2) {
            if (playerController.CargoCheckFruits(1) == true) {
                player.fruits += 1;

            }
        } else if (BlackMarketChoice == 3) {
            if (playerController.CargoCheckFabrics(1)) {
                player.fabrics += 1;
                System.out.println("er zijn " + player.fabrics + " Fabrics");

            } else {
            }

        } else {
        }
    }

    public void CarravansaryCardSelector() {
        // moet nog aff
        cardNumber1 = cardController.getRandomCard() - 1;
        cardNumber2 = cardController.getRandomCard() - 1;

        Boolean SameCard = cardController.CardChecker();

        while (SameCard = true) {
            cardNumber2 = cardController.getRandomCard() - 1;
        }

        board.PlayerCardChoice.add(board.BonusCards.get(cardNumber1));
        board.PlayerCardChoice.add(board.BonusCards.get(cardNumber2));


    }

    public void FruitWarehouse() {

        playerController.MaxCargoUpdater();
        playerController.MaxGoods("fruit");


    }

    public void FabricWarehouse() {

        playerController.MaxCargoUpdater();
        playerController.MaxGoods("fabric");


    }

    public void spiceWarehouse() {

        playerController.MaxCargoUpdater();
        playerController.MaxGoods("spice");


    }

    public void TeaHouse() {
        teaHouse.numberChoice = setNumberChoice();

        teaHouse.diceOne = setDiceValue();
        teaHouse.diceTwo = setDiceValue();

        DiceResult = teaHouse.diceOne + teaHouse.diceTwo;

        System.out.println("Je hebt " + DiceResult + " gegooit");

        if (teaHouse.numberChoice > DiceResult) {
        }
    }
}
