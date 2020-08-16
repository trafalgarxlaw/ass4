package go.game.state;

import go.game.controller.GameControler;

import java.io.IOException;

public class InteractiveGameState implements State{
    GameControler controler;

    public InteractiveGameState(GameControler controler){
        this.controler=controler;

    }
    @Override
    public void handle() throws IOException {
        controler.activateInterface();
    }
}
