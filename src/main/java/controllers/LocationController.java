package controllers;


import models.Player;
import models.locations.*;

import java.util.Scanner;

public class LocationController{

    // Variables
    private static LocationController locationController;
    private int location;
    public int diceResult;
    public String diceResultStr;
    models.Dice diceOne = new models.Dice();
    models.Dice diceTwo = new models.Dice();
    public int cardNumber1;
    public int cardNumber2;

    public int teahouseNumberChoice;

    private BlackMarket blackMarket = BlackMarket.getInstance();
    private TeaHouse teaHouse = TeaHouse.getInstance();
    private FruitWarehouse fruitWarehouse = FruitWarehouse.getInstance();
    private Caravansary caravansary = Caravansary.getInstance();
    private CardController cardController = CardController.getInstance();
    public PlayerController playerController = PlayerController.getInstance();
    private Player myPlayer = playerController.getMyPlayer();
    private models.Board board = new models.Board();
    private Wainwright wainwright = Wainwright.getInstance();

    Scanner scanner = new Scanner(System.in);

    // Functie die wordt aangeroepen wanneer een locatie tile wordt gebruikt

    public void wainrightBuyer(){
        if(myPlayer.lira > 7 || myPlayer.lira == 7) {
            if (myPlayer.carUpgrades < 3) {
                playerController.addRubysLiras("lira", -7);
                playerController.CarUpgrader();

                System.out.println("Je hebt nu " + myPlayer.carUpgrades + " CarUpgrades en " + myPlayer.lira + " Lira");
            }
            System.out.println("Je hebt nu " + myPlayer.carUpgrades + " CarUpgrades en " + myPlayer.lira + " Lira");

        }
        System.out.println("Je hebt nu " + myPlayer.carUpgrades + " CarUpgrades en " + myPlayer.lira + " Lira");


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
            myPlayer.jewels = myPlayer.jewels;
        } else if (diceResult == 7 || diceResult == 8) {
            if (playerController.CargoCheckJewels(1) == true) {
                myPlayer.jewels += 1;
            }

        } else if (diceResult == 9 || diceResult == 10) {
            if (playerController.CargoCheckJewels(2)) {
                myPlayer.jewels += 2;
            } else if (playerController.CargoCheckJewels(1)) {
                myPlayer.jewels += 1;
            }

        } else if (diceResult == 11 || diceResult == 12) {
            if (playerController.CargoCheckJewels(3)) {
                myPlayer.jewels += 3;
            } else if (playerController.CargoCheckJewels(2)) {
                myPlayer.jewels += 2;
            } else if (playerController.CargoCheckJewels(1)) {
                myPlayer.jewels += 1;
            }
        }
        System.out.println("er zijn " + myPlayer.jewels + " Jewels toegevoegd!");

    }

        public void BlackMarketChoice(int BlackMarketChoice) {
playerController = PlayerController.getInstance();
        if (BlackMarketChoice == 1) {
            if (playerController.CargoCheckSpices(1) == true) {
                myPlayer.spices += 1;

            }
        } else if (BlackMarketChoice == 2) {
            if (playerController.CargoCheckFruits(1) == true) {
                myPlayer.fruits += 1;

            }
        } else if (BlackMarketChoice == 3) {
            if (playerController.CargoCheckFabrics(1)) {
                myPlayer.fabrics += 1;
                System.out.println("er zijn " + myPlayer.fabrics + " Fabrics");

            } else {
            }

        } else {
        }
    }

    public void CarravansaryCardSelector() {

        // hier nog even naar kijken jongens, ik weet niet hoe jullie die bonuskaarten willen hebben.


    }

    public void FruitWarehouse() {
        playerController.MaxCargoUpdater();
        playerController.MaxGoods("fruit");
    }

    public void FabricWarehouse() {
        playerController.MaxCargoUpdater();
        playerController.MaxGoods("fabric");
    }

    public void SpiceWarehouse() {
        playerController.MaxCargoUpdater();
        playerController.MaxGoods("spice");
        System.out.println("Er zijn " + myPlayer.spices + " toegevoegd.");
    }



    public void setTeaHouseNumber(int number) {
        teaHouse.setTeahouseNumberChoice(number);
    }
    public int getTeaHouseNumber() {
        return teaHouse.getTeahouseNumberChoice();
    }

    public void setTeaHouseDice(int number){
        teaHouse.setTeahouseDiceNumber(number);
    }
    public int getTeaHouseDiceNumber(){
        return teaHouse.getTeahouseDiceNumber();
    }

    public void TeaHouseResult(){
        diceOne.DiceValue = setDiceValue();
        diceTwo.DiceValue = setDiceValue();
        diceResult = diceOne.DiceValue + diceTwo.DiceValue;
        diceResultStr = Integer.toString(diceResult);
        teahouseNumberChoice = teaHouse.teahouseNumberChoice;

        if (teaHouse.teahouseNumberChoice < diceResult || teaHouse.teahouseNumberChoice == diceResult) {
            playerController.addRubysLiras("lira", teaHouse.teahouseNumberChoice);
            System.out.println("Er is " + teaHouse.teahouseNumberChoice + " Lira toegevoegd!" );
        } else if(teaHouse.teahouseNumberChoice < diceResult){
            playerController.addRubysLiras("lira", 2);
                System.out.println("Helaas! je ligt eronder!");

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
