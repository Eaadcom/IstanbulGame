package controllers;

import javax.smartcardio.Card;

public class CardController {

    //Variables
    private static CardController cardController;
    LocationController locationController = LocationController.getInstance();

    public int getRandomCard(){
        int RandCardInt = (int) (Math.random() * 20 + 1);

        return RandCardInt;
    }

    public boolean CardChecker(){
        if(locationController.cardNumber1 == locationController.cardNumber2){
           return true;

        }else {
            return false;
        }
    }

    // Singleton Pattern
    public static CardController getInstance() {
        if (cardController == null) {
            cardController = new CardController();
        }
        return cardController;
    }
}
