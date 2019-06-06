package observers;

import observers.cards.*;
import observers.locations.*;

public interface GameViewObserver {

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
