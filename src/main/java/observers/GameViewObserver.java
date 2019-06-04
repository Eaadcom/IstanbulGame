package observers;

import observers.cards.*;
import observers.locations.*;

public interface GameViewObserver {

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


    // Locations

    public void update(BlackMarketObservable bmo);

    public void update(CaravansaryObservable co);

    public void update(FabricWarehouseObservable fwo);

    public void update(FountainObservable fo);

    public void update(FruitWarehouseObservable fwo);

    public void update(GemstoneDealerObservable gdo);

    public void update(GreatMosqueObservable gmo);

    public void update(LargeMarketObservable lmo);

    public void update(PoliceStationObservable pso);

    public void update(PostOfficeObservable  poo);

    public void update(SmallMarketObservable smo);

    public void update(SmallMosqueObservable smo);

    public void update(SpiceWarehouseObservable swo);

    public void update(SultanPalaceObservable spo);

    public void update(TeaHouseObservable tho);

    public void update(WainwrightObservable wo);


    // Rest

    public void update(AssistantObservable ao);

    public void update(BoardObservable bo);

    public void update(DiceObservable dO);

    public void update(FamilyMemberObservable fmo);

    public void update(GameObservable go);

    public void update(GovernorObservable go);

    public void update(PlayerObservable po);

    public void update(SmugglerObservable so);
}
