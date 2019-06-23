package controllers;


import models.Player;
import javafx.stage.Stage;
import models.locations.*;
import javafx.scene.control.TextField;
import views.GameView;
import views.LocationView;

import java.awt.*;
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
    private Player player = GameController.getInstance().getPlayer();

    private GameView gameView;

    Scanner scanner = new Scanner(System.in);

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
        if(myPlayer.lira >= 7) {
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


    public int SmallMosque(int Bought, String choice){
        int payement;

        switch (choice) {
            case "fabric":
                payement = 1 + Bought;
                if (player.fabrics > payement || player.fabrics == payement) {
                    player.setFabrics(-payement);
                    player.setFabricMosque(true);
                    Bought++;
                    MosqueChecker("small");

                }
            case "spice":
                payement = 1 + Bought;
                if (player.spices > payement || player.spices == payement){
                    player.setSpices(-payement);
                    player.setSpiceMosque(true);
                    MosqueChecker("small");
                    Bought++;
                }
        }




        return Bought;
    }

    public void BigMosque(String choice){
        int payement;
        int BoughtJewels = greatMosque.boughtJewels;
        int BoughtFruit = greatMosque.boughtFruit;
        switch(choice){
            case "jewel":
                payement = 1 + BoughtJewels;
                if(player.jewels > payement || player.jewels == payement){
                    player.setJewels(-payement);
                    player.setJewelMosque(true);
                    MosqueChecker("big");
                    BoughtJewels++;
                    greatMosque.boughtJewels = BoughtJewels;
                }
            case "fruit":
                payement = 1 + BoughtFruit;
                if(player.fruits > payement || player.fruits == payement){
                    player.setFruits((-payement));
                    player.setFruitMosque(true);
                    MosqueChecker("big");
                    BoughtFruit++;
                    greatMosque.boughtFruit = BoughtFruit;
                }
        }


    }

    public void MosqueChecker(String mosque){

        switch (mosque){
            case "small":
                if(player.SmallMosqueRuby == false) {
                    if (myPlayer.fabricMosque && myPlayer.spiceMosque) {
                        playerController.addRubysLiras("ruby", 1);
                        player.setSmallMosqueRuby(true);

                    }
                }
            case "big":
                if(player.BigMosqueRuby == false) {
                    if (myPlayer.jewelMosque && myPlayer.fruitMosque) {
                        playerController.addRubysLiras("ruby", 1);
                        player.setBigMosqueRuby(true);
                    }
                }

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

    }}
        // SULTANS PALACE CODE
        public int  getBluePrice()   { return SultanPalace.getInstance().getJewelPrice();   }
        public int  getRedPrice()    { return SultanPalace.getInstance().getFabricPrice();  }
        public int  getGreenPrice()  { return SultanPalace.getInstance().getSpicePrice();   }
        public int  getYellowPrice() { return SultanPalace.getInstance().getFruitPrice();   }
        public int  getChoiceAmount(){ return SultanPalace.getInstance().getChoiceAmount(); }

        /**
        *  Confirms the purchase of the sultans palace. Triggers the confirmPurchase(Player) function in the SultanPalace model.
        *  @author Thomas van Velzen
        *  @version 20-6-2019
        */
        public void confirmPurchase() throws IOException { SultanPalace.getInstance().confirmPurchase(GameController.getInstance().getPlayer()); }
        /**
        *  Handles the choice of the sultans palace. Triggers the handleChoice(choice, Player) function in the SultanPalace model.
        *  @author Thomas van Velzen
        *  @version 20-6-2019
        *  @param choice
        */
        public void handleChoice(String choice){ SultanPalace.getInstance().handleChoice(choice, GameController.getInstance().getPlayer()); }


        //SMALL MARKET CODE
        public int  SMgetJewel()   { return SmallMarket.getInstance().getJewel();   }
        public int  SMgetFabric()    { return SmallMarket.getInstance().getFabric();  }
        public int  SMgetSpice()  { return SmallMarket.getInstance().getSpice();   }
        public int  SMgetFruit() { return SmallMarket.getInstance().getFruit();   }

        /**
        *  Confirms the purchase of the small market. Triggers the confirmPurchase(fabric, fruit, spice, jewel Player) function in the SmallMarket model.
        *  @author Thomas van Velzen
        *  @version 20-6-2019
        *  @param fabric
        *  @param fruit
        *  @param spice
        *  @param jewel
        */
        public void SMconfirmPurchase(int fabric, int fruit, int spice, int jewel) throws IOException {
        SmallMarket.getInstance().confirmPurchase(fabric, fruit, spice, jewel, GameController.getInstance().getPlayer()); }

        //GREAT MARKET CODE
        public int  GMgetJewel()   { return GreatMarket.getInstance().GMgetJewel();   }
        public int  GMgetFabric()    { return GreatMarket.getInstance().GMgetFabric();  }
        public int  GMgetSpice()  { return GreatMarket.getInstance().GMgetSpice();   }
        public int  GMgetFruit() { return GreatMarket.getInstance().GMgetFruit();   }
        /**
        *  Confirms the purchase of the large market. Triggers the confirmPurchase(fabric, fruit, spice, jewel Player) function in the LargeMarket model.
        *  @author Thomas van Velzen
        *  @version 20-6-2019
        *  @param fabric
        *  @param fruit
        *  @param spice
        *  @param jewel
        */
        public void GMconfirmPurchase(int fabric, int fruit, int spice, int jewel) throws IOException {
        GreatMarket.getInstance().GMconfirmPurchase(fabric, fruit, spice, jewel, GameController.getInstance().getPlayer()); }


    /**
     * Checks if the player has entered a valid number at the police station and acts accoringly
     * @author Stan Hogenboom
     * @param usernamefield
     */
    public void policeStation(String usernamefield) throws IOException {
        if (usernamefield.equals("1")) {
            GameView.getInstance().movePoliceStation(1);
        }
        else if (usernamefield.equals("2")) {
            GameView.getInstance().movePoliceStation(2);
        }
        else if (usernamefield.equals("3")) {
            GameView.getInstance().movePoliceStation(3);
        }
        else if (usernamefield.equals("4")) {
            GameView.getInstance().movePoliceStation(4);
        }
        else if (usernamefield.equals("5")) {
            GameView.getInstance().movePoliceStation(5);
        }
        else if (usernamefield.equals("6")) {
            GameView.getInstance().movePoliceStation(6);
        }
        else if (usernamefield.equals("7")) {
            GameView.getInstance().movePoliceStation(7);
        }
        else if (usernamefield.equals("8")) {
            GameView.getInstance().movePoliceStation(8);
        }
        else if (usernamefield.equals("9")) {
            GameView.getInstance().movePoliceStation(9);
        }
        else if (usernamefield.equals("10")) {
            GameView.getInstance().movePoliceStation(10);
        }
        else if (usernamefield.equals("11")) {
            GameView.getInstance().movePoliceStation(11);
        }
        else if (usernamefield.equals("12")) {
            // This is the policesttion itself, probably shouldn't be included
        }
        else if (usernamefield.equals("13")) {
            GameView.getInstance().movePoliceStation(13);
        }
        else if (usernamefield.equals("14")) {
            GameView.getInstance().movePoliceStation(14);
        }
        else if (usernamefield.equals("15")) {
            GameView.getInstance().movePoliceStation(15);
        }
        else if (usernamefield.equals("16")) {
            GameView.getInstance().movePoliceStation(16);
        }
        else {

        }
    }


    /**
     * This function is used when the player lands on the gemstone dealer tile and clicks "yes".
     * It checks if the player has enough Lira to purchase a ruby.
     * If that's the case, the player recieves a ruby and a number of lira is withdrawn.
     * The price of a ruby goes up by one every time someone buys a ruby.
     * @author Stan Hogenboom
     * @version 17-6-2019
     */
    public void gemstoneDealerAction() {
        GemstoneDealer gsd = GemstoneDealer.getInstance();
        int price = gsd.getGemstonePrice();

        if(myPlayer.lira > price){
            playerController.addRubysLiras("ruby", 1);
            playerController.addRubysLiras("lira", -price);
            gsd.updatePrice(price + 1);
        }
        else {
            System.out.println("Niet genoeg Lira");
            //hier moet een scherm met de tekst [je hebt niet genoeg lira]
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
