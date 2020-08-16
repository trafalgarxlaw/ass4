package go.game.Interface;

import java.io.IOException;

public interface userInterface {

    public void printMessage();

    public void outofBoardErrorMSG();

    public void isKOMSG();

    public String readUserInput() throws IOException;



}
