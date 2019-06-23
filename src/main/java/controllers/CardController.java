package controllers;

import models.Player;
import models.cards.*;

import java.util.HashMap;
import java.util.Map;

public class CardController {

    //Variables
    private static CardController cardController;


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


    public void GainGood(String choice){
        int newValue;
        if(choice == "fabric"){

            newValue = player.fabrics++;
            if(newValue < player.maxFabrics) {
                player.setFabrics(newValue);
            }
        } else if(choice == "fruit"){
            newValue = player.fruits++;
            if(newValue < player.maxFruits) {
                player.setFruits(newValue);
            }
        } else if (choice  == "jewel"){
            newValue = player.jewels++;
            if(newValue < player.maxFabrics) {
                player.setJewels(newValue);
            }
        } else if(choice == "spice"){
            newValue = player.spices++;
            if(newValue < player.maxSpices) {
                player.setSpices(newValue);
            }
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
