package go.game.model;

public class Action {
    private boolean pass;
    private Intersection actionIntersection;

    public Action(int positionX, int positionY){
        this.pass=false;
        this.actionIntersection= new Intersection(positionX,positionY);
        this.actionIntersection.setStatus(IntersectionStatus.FREE);
    }

    public Action(boolean pass){
       if (pass){
           this.pass = true;
       }
    }

    public go.game.model.Intersection getActionIntersection() {
        return actionIntersection;
    }

    public boolean isPass(){
        return this.pass;
    }
      
}
