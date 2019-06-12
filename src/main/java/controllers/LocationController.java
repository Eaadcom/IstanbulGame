package controllers;


import models.locations.BlackMarket;
import models.locations.Caravansary;
import models.locations.FruitWarehouse;
import models.locations.TeaHouse;
import java.util.Scanner;

public class LocationController{

    // Variables
    private static LocationController locationController;
    private int location;
    private int diceResult;
    public int cardNumber1;
    public int cardNumber2;

    private BlackMarket blackMarket;
    private TeaHouse teaHouse;
    public FruitWarehouse fruitWarehouse;
    public Caravansary caravansary = new Caravansary();
    private CardController cardController;
    private PlayerController playerController;
    private models.Player player = new models.Player("Name");
    private models.Board board = new models.Board();

    Scanner scanner = new Scanner(System.in);

    // Functie die wordt aangeroepen wanneer een locatie tile wordt gebruikt
    public void onUse() {
        switch (location) {
            // BlackMarket Functie
            case 1: {

            }
            // Caravansary Functie
            case 2: {

            }
            // Teahouse Functie
            case 15: {
                teaHouse.numberChoice = setNumberChoice();

                teaHouse.diceOne = setDiceValue();
                teaHouse.diceTwo = setDiceValue();

                diceResult = teaHouse.diceOne + teaHouse.diceTwo;

                System.out.println("Je hebt " + diceResult + " gegooit");

                if (teaHouse.numberChoice > diceResult) {
                }
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

    public void BlackMarketDice() {
        playerController = PlayerController.getInstance();
blackMarket = BlackMarket.getInstance();
        blackMarket.diceOne.DiceValue = setDiceValue();
        blackMarket.diceTwo.DiceValue = setDiceValue();

        diceResult = blackMarket.diceOne.DiceValue + blackMarket.diceTwo.DiceValue;
        diceResult = 8;
        System.out.println("Je hebt " + diceResult + " gegooit");

        if (diceResult < 7) {
            player.jewels = player.jewels;
        } else if (diceResult == 7 || diceResult == 8) {
            if (playerController.CargoCheckJewels(1) == true) {
                player.jewels += 1;
            }

        } else if (diceResult == 9 || diceResult == 10) {
            if (playerController.CargoCheckJewels(2)) {
                player.jewels += 2;
            } else if (playerController.CargoCheckJewels(1)) {
                player.jewels += 1;
            }

        } else if (diceResult == 11 || diceResult == 12) {
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
playerController = PlayerController.getInstance();
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

        board.playerBonusCards.add(board.bonusCards.get(cardNumber1));
        board.playerBonusCards.add(board.bonusCards.get(cardNumber2));


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

        diceResult = teaHouse.diceOne + teaHouse.diceTwo;

        System.out.println("Je hebt " + diceResult + " gegooit");

        if (teaHouse.numberChoice > diceResult) {
        }
    }

    // Singleton Pattern
    public static LocationController getInstance() {
        if (locationController == null) {
            locationController = new LocationController();
        }
        return locationController;
    }

}
