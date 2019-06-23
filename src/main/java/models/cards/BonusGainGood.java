package models.cards;

import controllers.GameController;
import models.Player;
import observers.CardViewObserver;
import observers.cards.BonusGainGoodObservable;
import views.GameView;
import views.cards.BonusGainGoodView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BonusGainGood implements BonusCard, BonusGainGoodObservable {

    // Variables
    private List<CardViewObserver> observers = new ArrayList<>();


    // Observer Pattern
    @Override
    public void register(CardViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (CardViewObserver cvo : observers){
            cvo.update(this);
        }
    }

    @Override
    public void onUse(Player player) {
        try {
            BonusGainGoodView.getInstance().bonusOneGood();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void GainGood(String choice){
        Player player = GameController.getInstance().getPlayer();
        int newValue;
        if(choice == "fabric"){
            newValue = player.getFabrics()+1;
            if(newValue < player.getMaxFabrics()) {
                player.setFabrics(newValue);
            }
        } else if(choice == "fruit"){
            newValue = player.getFruits()+1;
            if(newValue < player.getMaxFruits()) {
                player.setFruits(newValue);
            }
        } else if (choice  == "jewel"){
            newValue = player.getJewels()+1;
            if(newValue < player.getMaxJewels()) {
                player.setJewels(newValue);
            }
        } else if(choice == "spice"){
            newValue = player.getSpices()+1;
            if(newValue < player.getMaxSpices()) {
                player.setSpices(newValue);
            }
        }
    }


    private static BonusGainGood bonusGainGood;
    public static BonusGainGood getInstance() {
        if (bonusGainGood == null) {
            bonusGainGood = new BonusGainGood();
        }
        return bonusGainGood;
    }

}
