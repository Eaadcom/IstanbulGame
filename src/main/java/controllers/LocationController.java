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

/**
 * This is the controller for all 16 tiles.
 * The methods in this class are called when they player visits the respective tile.
 * @author Stan Hogenboom, Thomas van Velzen, Edward Deen, Joeri van Duijkeren, Floris Dekker
 * @version 21-6-2019
 */
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



    public void Fountain(){

    }
    public void wainrightBuyer(){
        Player player = GameController.getInstance().getPlayer();
        if(player.getLira() >= 7) {
            if (player.getCarUpgrades() < 3) {
                player.setLira(player.getLira() - 7);
                player.setCarUpgrades(player.getCarUpgrades() + 1);
            }
        }

    }

    public int setDiceValue() {

        int DiceValue = (int) (Math.random() * 6 + 1);

        return DiceValue;
    }

        public void BlackMarketChoice(int BlackMarketChoice) {
        Player player = GameController.getInstance().getPlayer();
        if (BlackMarketChoice == 1) {
            if (playerController.CargoCheckSpices(1) == true) {
                player.setSpices(player.getSpices() +1);

            }
        } else if (BlackMarketChoice == 2) {
            if (playerController.CargoCheckFruits(1) == true) {
                player.setFruits(player.getFruits() +1);

            }
        } else if (BlackMarketChoice == 3) {
            if (playerController.CargoCheckFabrics(1)) {
                player.setFabrics(player.getFabrics() +1);

            } else {

            }

        } else {

        }
    }


    public void SmallMosque(int Bought, String choice){
        int newValue;
        switch (choice) {
            case "fabric":
                if (player.fabrics > Bought || player.fabrics == Bought) {
                    newValue = player.fabrics - Bought;
                    player.setFabrics(newValue);
                    player.setFabricMosque(true);
                    MosqueChecker("small");

                }
            case "spice":
                if (player.spices > Bought || player.spices == Bought){
                    newValue = player.spices - Bought;
                    player.setSpices(newValue);
                    player.setSpiceMosque(true);
                    MosqueChecker("small");
                }
        }





    }

    public void BigMosque(int Bought, String choice){
        int newValue;

        switch(choice){
            case "jewel":
                if(player.jewels > Bought || player.jewels == Bought){
                    System.out.println("Je betaald nu" + Bought);
                    newValue = player.jewels - Bought;
                    player.setJewels(newValue);
                    System.out.println(player.jewels);
                    player.setJewelMosque(true);
                    MosqueChecker("big");
                }
            case "fruit":
                if(player.fruits > Bought || player.fruits == Bought){
                    newValue = player.fruits - Bought;
                    player.setFruits((newValue));
                    player.setFruitMosque(true);
                    MosqueChecker("big");
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
                        System.out.println("Je hebt nu beide de jewels en fruit op true staan");

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
        Player player = GameController.getInstance().getPlayer();
        diceOne.DiceValue = setDiceValue();
        diceTwo.DiceValue = setDiceValue();
        diceResult = diceOne.DiceValue + diceTwo.DiceValue;
        diceResultStr = Integer.toString(diceResult);
        teahouseNumberChoice = teaHouse.teahouseNumberChoice;

        if (teaHouse.teahouseNumberChoice < diceResult || teaHouse.teahouseNumberChoice == diceResult) {
            player.setLira(player.getLira() + teahouseNumberChoice);
        } else if(teaHouse.teahouseNumberChoice < diceResult){
            player.setLira(player.getLira() + 2);

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
        Player player = GameController.getInstance().getPlayer();
        GemstoneDealer gsd = GemstoneDealer.getInstance();
        int price = gsd.getGemstonePrice();

        if(player.getLira() > price){
            player.setRubies(player.getRubies()+1);
            player.setLira(player.getLira()-price);
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
