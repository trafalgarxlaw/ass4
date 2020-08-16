package go.test;

import go.game.controller.GameInterface;
import go.game.model.Action;
import go.game.model.Board;
import go.game.controller.GameControler;
import java.util.List;
import go.game.model.Intersection;
import go.game.model.Player;

public class GameMock implements GameInterface {
    private Player player2;
    private Player player1;
    private GameControler gameControler =  new GameControler(player1, player2);
    boolean isIntersection;


    public boolean setIntersection(Board board, Action userAction) {

        return gameControler.intersectionUsed(board, userAction);
    }

    @Override
    public boolean isConsecutivePass() {
        return gameControler.isConsecutivePass();
    }
    public void setConsecutivePass(Action currentaction,Action previousaction) {
        gameControler.isConsecutivePass(currentaction , previousaction);
    }
    public Action getCurrentaction(List<Action> actionList){
        return actionList.get(actionList.size()-1);
    }

    public Action getPreviousCurrentaction(List<Action> actionList){
        return actionList.get(actionList.size()-2);
    }
}
