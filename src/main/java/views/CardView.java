package views;

import observers.CardViewObserver;
import observers.cards.*;

public class CardView implements CardViewObserver {

    // Variables
    private static CardView cardView;

    // FXML Variables

    // Singleton Pattern
    public static CardView getInstance() {
        if (cardView == null) {
            cardView = new CardView();
        }
        return cardView;
    }

    // Observer Pattern
    @Override
    public void update(BonusFourMovesObservable bfmo) {

    }

    @Override
    public void update(BonusGainGoodObservable bggo) {

    }

    @Override
    public void update(BonusGemstoneDealerObservable bgdo) {

    }

    @Override
    public void update(BonusGetLiraObservable bglo) {

    }

    @Override
    public void update(BonusPostOfficeObservable bpoo) {

    }

    @Override
    public void update(BonusReturnAssistantObservable brao) {

    }

    @Override
    public void update(BonusReturnMemberObservable brmo) {

    }

    @Override
    public void update(BonusSmallMarketObservable bsmo) {

    }

    @Override
    public void update(BonusSultansPalaceObservable bspo) {

    }

    @Override
    public void update(BonusZeroMovesObservable bzmo) {

    }
}
