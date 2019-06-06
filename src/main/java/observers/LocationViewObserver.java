package observers;

import observers.locations.*;

public interface LocationViewObserver {
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
}
