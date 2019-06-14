package controllers;

import models.cards.*;

import java.util.HashMap;
import java.util.Map;

public class CardController {

    //Variables
    private static CardController cardController;
    LocationController locationController = LocationController.getInstance();

    Map<String, BonusCard> bonusCardMap = new HashMap<>();


    public CardController() {
        createBonusCardsReferentie();
    }

    private void createBonusCardsReferentie() {
        bonusCardMap.put("card1", new BonusSmallMarket());
        bonusCardMap.put("card2", new BonusZeroMoves());
        bonusCardMap.put("card3", new BonusReturnMember());
        bonusCardMap.put("card4", new BonusGemstoneDealer());
        bonusCardMap.put("card5", new BonusPostOffice());
        bonusCardMap.put("card6", new BonusSultansPalace());
        bonusCardMap.put("card7", new BonusGetLira());
        bonusCardMap.put("card8", new BonusGainGood());
        bonusCardMap.put("card9", new BonusReturnAssistant());
        bonusCardMap.put("card10", new BonusFourMoves());
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

    // Singleton Pattern
    public static CardController getInstance() {
        if (cardController == null) {
            cardController = new CardController();
        }
        return cardController;
    }

    public BonusCard getGekozenKaart(String gekozenKaart) {
        return bonusCardMap.get(gekozenKaart);
    }
}
