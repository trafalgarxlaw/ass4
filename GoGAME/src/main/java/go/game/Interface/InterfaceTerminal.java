package go.game.Interface;

import go.game.input.InputReaderTerminal;
import go.game.model.Action;

import java.io.IOException;

public class InterfaceTerminal implements userInterface {

    InputReaderTerminal reader;

    public InterfaceTerminal(){
        initialiseInterface();
    }

    private void initialiseInterface(){
        System.out.println("-----------------------------------");
        System.out.println("Activation of the user interface...");
        System.out.println("-----------------------------------");
        reader = new InputReaderTerminal();
    }

    @Override
    public String readUserInput() throws IOException {
        return reader.ReadInput("");
    }


    @Override
    public void printMessage() {
        System.out.print("Please enter the position you want to capture : ");

    }
    @Override
    public void outofBoardErrorMSG() {
        System.out.println("\n\u26A0 Warning !");
        System.out.println("the entered position is out of board, please retry.");
        System.out.println("---------------------------------------------------\n");

    }
    @Override
    public void isKOMSG() {
        System.out.println();


      
        System.out.println("\n" + "This shot is forbidden because of the KO rule !" + '\n' +
                  "---------------------------------------------------" + '\n');


    }


    public Action createAction(String input){
        return reader.GenerateInputAction(input);
    }
}
