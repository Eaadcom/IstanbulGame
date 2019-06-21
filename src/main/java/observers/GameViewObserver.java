package observers;

import observers.locations.GemstoneDealerObservable;
import observers.locations.SultanPalaceObservable;

public interface GameViewObserver extends GameViewLobbyViewObserver {

    // Rest
    void update(AssistantObservable ao);

    void update(BoardObservable bo);

    void update(DiceObservable dO);

    void update(FamilyMemberObservable fmo);

    void update(GovernorObservable go);

    void update(PlayerObservable po);

    void update(SmugglerObservable so);

    void update(SultanPalaceObservable spo);

    void update(GemstoneDealerObservable gdo);
}
