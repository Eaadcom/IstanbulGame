package observers;

import observers.cards.*;

public interface CardViewObserver {

    // Bonus Cards
    public void update(BonusFourMovesObservable bfmo);

    public void update(BonusGainGoodObservable bggo);

    public void update(BonusGemstoneDealerObservable bgdo);

    public void update(BonusGetLiraObservable bglo);

    public void update(BonusPostOfficeObservable bpoo);

    public void update(BonusReturnAssistantObservable brao);

    public void update(BonusReturnMemberObservable brmo);

    public void update(BonusSmallMarketObservable bsmo);

    public void update(BonusSultansPalaceObservable bspo);

    public void update(BonusZeroMovesObservable bzmo);
}
