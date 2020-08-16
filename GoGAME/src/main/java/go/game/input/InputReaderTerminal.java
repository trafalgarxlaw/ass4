package go.game.input;

import go.game.model.Action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReaderTerminal implements Input{
    @Override
    public String ReadInput(String pathname) throws IOException {
        //Enter data using BufferReader
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        // Reading data using readLine
        String name = reader.readLine();

        // Printing the read line
       // System.out.println(name);

        return name;
    }

    public Action GenerateInputAction(String input){

        Action GeneratedAction;

        if("pass".equalsIgnoreCase(input)){
            Action newAction = new Action(true);
            GeneratedAction=newAction;

        }else {
            int posi= convertCharToInt (input.charAt(0));
            int posj=convertCharToInt(input.charAt(1));

            Action newAction = new Action(posi,posj);
            GeneratedAction=newAction;

        }

        return GeneratedAction;
    }

    private int convertCharToInt(char c) {
        int pos = 0;
        String alphabetes = "ABCDEFGHJKLMNOPQRSTUVWXYZ";

        if (Character.isDigit(c)){
            pos=Integer.parseInt(String.valueOf(c))-1;

        }else {

            pos = alphabetes.indexOf(c);

        }

        return pos;
    }
}
