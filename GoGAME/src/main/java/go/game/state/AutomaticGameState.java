package go.game.state;

import go.game.controller.GameControler;

import java.io.IOException;

public class AutomaticGameState implements State{

    private GameControler controler;

    public AutomaticGameState(GameControler controler){
        this.controler=controler;
    }

    @Override
    public void handle() throws IOException {
        controler.startGame();
    }

}
