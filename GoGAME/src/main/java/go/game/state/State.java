package go.game.state;

import go.game.controller.GameControler;

import java.io.IOException;

public interface State {
    public void handle() throws IOException;
}
