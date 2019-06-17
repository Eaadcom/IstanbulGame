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
    public boolean hasAssistants;

    public int teahouseNumberChoice;

    private BlackMarket blackMarket = BlackMarket.getInstance();
    private TeaHouse teaHouse = TeaHouse.getInstance();
    private FabricWarehouse fabricWarehouse = FabricWarehouse.getInstance();
    private Fountain fountain = Fountain.getInstance();
    private GemstoneDealer gemstoneDealer = GemstoneDealer.getInstance();
    private GreatMosque greatMosque = GreatMosque.getInstance();
    private LargeMarket largeMarket = LargeMarket.getInstance();
    private PoliceStation policeStation = PoliceStation.getInstance();
    private PostOffice postOffice = PostOffice.getInstance();
    private SmallMarket smallMarket = SmallMarket.getInstance();
    private SmallMosque smallMosque = SmallMosque.getInstance();
    private SpiceWarehouse spiceWarehouse = SpiceWarehouse.getInstance();
    private SultanPalace sultanPalace = SultanPalace.getInstance();
    private FruitWarehouse fruitWarehouse = FruitWarehouse.getInstance();
    private Caravansary caravansary = Caravansary.getInstance();
    private CardController cardController = CardController.getInstance();
    public PlayerController playerController = PlayerController.getInstance();
    private Player myPlayer = playerController.getMyPlayer();
    private models.Board board = new models.Board();
    private Wainwright wainwright = Wainwright.getInstance();

    // Functie die wordt aangeroepen wanneer een locatie tile wordt gebruikt

    public void AssistantLocation(int location){
        if (myPlayer.assistants > 0) {
            hasAssistants = true;



            switch (location) {

                case 1:
                    if (blackMarket.color(myPlayer.color)) {
                        blackMarket.setColor(myPlayer.color, false);
                        myPlayer.assistants += 1;
                    } else if (blackMarket.color(myPlayer.color) == false) {
                        blackMarket.setColor(myPlayer.color, true);
                        myPlayer.assistants -= 1;
                    }
                    break;
                case 2:
                    if (caravansary.color(myPlayer.color)) {
                        caravansary.setColor(myPlayer.color, false);
                        myPlayer.assistants += 1;
                    } else if (caravansary.color(myPlayer.color) == false) {
                        caravansary.setColor(myPlayer.color, true);
                        myPlayer.assistants -= 1;
                    }
                    break;
                case 3:
                    if (fabricWarehouse.color(myPlayer.color)) {
                        fabricWarehouse.setColor(myPlayer.color, false);
                        myPlayer.assistants += 1;
                    } else if (fabricWarehouse.color(myPlayer.color) == false) {
                        fabricWarehouse.setColor(myPlayer.color, true);
                        myPlayer.assistants -= 1;
                    }
                    break;

                case 4:
                    Fountain();
                    break;

                case 5:
                    if (fruitWarehouse.color(myPlayer.color)) {
                        fabricWarehouse.setColor(myPlayer.color, false);
                        myPlayer.assistants += 1;
                    } else if (fruitWarehouse.color(myPlayer.color) == false) {
                        fabricWarehouse.setColor(myPlayer.color, true);
                        myPlayer.assistants -= 1;
                    }
                    break;
                case 6:
                    if (gemstoneDealer.color(myPlayer.color)) {
                        gemstoneDealer.setColor(myPlayer.color, false);
                        myPlayer.assistants += 1;
                    } else if (gemstoneDealer.color(myPlayer.color) == false) {
                        gemstoneDealer.setColor(myPlayer.color, true);
                        myPlayer.assistants -= 1;
                    }
                    break;
                case 7:
                    if (greatMosque.color(myPlayer.color)) {
                        greatMosque.setColor(myPlayer.color, false);
                        myPlayer.assistants += 1;
                    } else if (greatMosque.color(myPlayer.color) == false) {
                        greatMosque.setColor(myPlayer.color, true);
                        myPlayer.assistants -= 1;
                    }
                    break;

                case 8:
                    if (largeMarket.color(myPlayer.color)) {
                        largeMarket.setColor(myPlayer.color, false);
                        myPlayer.assistants += 1;
                    } else if (largeMarket.color(myPlayer.color) == false) {
                        largeMarket.setColor(myPlayer.color, true);
                        myPlayer.assistants -= 1;
                    }
                    break;

                case 9:
                    if (policeStation.color(myPlayer.color)) {
                        policeStation.setColor(myPlayer.color, false);
                        myPlayer.assistants += 1;
                    } else if (policeStation.color(myPlayer.color) == false) {
                        policeStation.setColor(myPlayer.color, true);
                        myPlayer.assistants -= 1;
                    }
                    break;

                case 10:
                    if (postOffice.color(myPlayer.color)) {
                        postOffice.setColor(myPlayer.color, false);
                        myPlayer.assistants += 1;
                    } else if (postOffice.color(myPlayer.color) == false) {
                        postOffice.setColor(myPlayer.color, true);
                        myPlayer.assistants -= 1;
                    }
                    break;

                case 11:
                    if (smallMarket.color(myPlayer.color)) {
                        smallMarket.setColor(myPlayer.color, false);
                        myPlayer.assistants += 1;
                    } else if (smallMarket.color(myPlayer.color) == false) {
                        smallMarket.setColor(myPlayer.color, true);
                        myPlayer.assistants -= 1;
                    }
                    break;
                case 12:
                    if (smallMosque.color(myPlayer.color)) {
                        smallMosque.setColor(myPlayer.color, false);
                        myPlayer.assistants += 1;
                    } else if (smallMosque.color(myPlayer.color) == false) {
                        smallMosque.setColor(myPlayer.color, true);
                        myPlayer.assistants -= 1;
                    }
                    break;
                case 13:
                    if (spiceWarehouse.color(myPlayer.color)) {
                        spiceWarehouse.setColor(myPlayer.color, false);
                        myPlayer.assistants += 1;
                    } else if (spiceWarehouse.color(myPlayer.color) == false) {
                        spiceWarehouse.setColor(myPlayer.color, true);
                        myPlayer.assistants -= 1;
                    }
                    break;

                case 14:
                    if (sultanPalace.color(myPlayer.color)) {
                        sultanPalace.setColor(myPlayer.color, false);
                        myPlayer.assistants += 1;
                    } else if (sultanPalace.color(myPlayer.color) == false) {
                        sultanPalace.setColor(myPlayer.color, true);
                        myPlayer.assistants -= 1;
                    }
                    break;
                case 15:
                    if (teaHouse.color(myPlayer.color)){
                        teaHouse.setColor(myPlayer.color, false);
                        myPlayer.assistants += 1;
                    } else if (blackMarket.color(myPlayer.color) == false){
                        teaHouse.setColor(myPlayer.color, true);
                        myPlayer.assistants -=1;
                    }

                    break;
                case 16:
                    if (wainwright.color(myPlayer.color)){
                        wainwright.setColor(myPlayer.color, false);
                        myPlayer.assistants += 1;
                    } else if(wainwright.color(myPlayer.color) == false){
                        wainwright.setColor(myPlayer.color, true);
                        myPlayer.assistants -= 1;
                    }
                    System.out.println("Er zijn " + myPlayer.assistants + " assistants.");
                    break;


            }
        } else{
             hasAssistants = false;
        }


    }
    public void Fountain(){
        blackMarket.setColor(myPlayer.color, false);
        caravansary.setColor(myPlayer.color, false);
        fabricWarehouse.setColor(myPlayer.color, false);
        fruitWarehouse.setColor(myPlayer.color, false);
        teaHouse.setColor(myPlayer.color, false);
        gemstoneDealer.setColor(myPlayer.color, false);
        greatMosque.setColor(myPlayer.color, false);
        largeMarket.setColor(myPlayer.color, false);
        policeStation.setColor(myPlayer.color, false);
        postOffice.setColor(myPlayer.color, false);
        smallMarket.setColor(myPlayer.color, false);
        smallMosque.setColor(myPlayer.color, false);
        spiceWarehouse.setColor(myPlayer.color, false);
        sultanPalace.setColor(myPlayer.color, false);
        wainwright.setColor(myPlayer.color, false);

        myPlayer.assistants = myPlayer.maxAssistants;
    }
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
