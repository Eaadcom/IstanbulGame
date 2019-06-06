package controllers;

import javax.smartcardio.Card;

public class CardController {

    LocationController locationController;

    CardController(LocationController locationController){
        this.locationController = locationController;
    }



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
}
