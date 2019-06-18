package controllers;


import models.Player;
import models.locations.*;

import java.io.IOException;
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

    private BlackMarket blackMarket;
    private TeaHouse teaHouse =  new TeaHouse();
    private SultanPalace sultansPalaceModel = SultanPalace.getInstance();
    public FruitWarehouse fruitWarehouse;
    public Caravansary caravansary = new Caravansary();
    private CardController cardController;
    public PlayerController playerController = new PlayerController();
    private Player myPlayer = playerController.getMyPlayer();
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

    public void SpiceWarehouse() {
        playerController.MaxCargoUpdater();
        playerController.MaxGoods("spice");
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

    }}
        // SULTANS PALACE CODE
        public int  getBluePrice()   { return SultanPalace.getInstance().getJewelPrice();   }
        public int  getRedPrice()    { return SultanPalace.getInstance().getFabricPrice();  }
        public int  getGreenPrice()  { return SultanPalace.getInstance().getSpicePrice();   }
        public int  getYellowPrice() { return SultanPalace.getInstance().getFruitPrice();   }
        public int  getChoiceAmount(){ return SultanPalace.getInstance().getChoiceAmount(); }
        public void confirmPurchase() throws IOException { SultanPalace.getInstance().confirmPurchase(GameController.getInstance().getPlayer()); }
        public void handleChoice(String choice){ SultanPalace.getInstance().handleChoice(choice, GameController.getInstance().getPlayer()); }


        //SMALL MARKET CODE
        public int  SMgetJewel()   { return SmallMarket.getInstance().getJewel();   }
        public int  SMgetFabric()    { return SmallMarket.getInstance().getFabric();  }
        public int  SMgetSpice()  { return SmallMarket.getInstance().getSpice();   }
        public int  SMgetFruit() { return SmallMarket.getInstance().getFruit();   }
        public void SMconfirmPurchase(int fabric, int fruit, int spice, int jewel) throws IOException {
        SmallMarket.getInstance().confirmPurchase(fabric, fruit, spice, jewel, GameController.getInstance().getPlayer()); }

        //GREAT MARKET CODE
        public int  GMgetJewel()   { return GreatMarket.getInstance().GMgetJewel();   }
        public int  GMgetFabric()    { return GreatMarket.getInstance().GMgetFabric();  }
        public int  GMgetSpice()  { return GreatMarket.getInstance().GMgetSpice();   }
        public int  GMgetFruit() { return GreatMarket.getInstance().GMgetFruit();   }
        public void GMconfirmPurchase(int fabric, int fruit, int spice, int jewel) throws IOException {
        GreatMarket.getInstance().GMconfirmPurchase(fabric, fruit, spice, jewel, GameController.getInstance().getPlayer()); }





    // Singleton Pattern
    public static LocationController getInstance() {
        if (locationController == null) {
            locationController = new LocationController();
        }
        return locationController;
    }

}
