package go.game.model;
import java.util.List;

public class ActionsCollection {
    private List<Action> actionList;

    public ActionsCollection(List<Action> actionLis){
        this.actionList=actionLis;
    }

    public int getHowManyActions(){return actionList.size();}

    public List<Action> getActionList() {
        return actionList;
    }
}
