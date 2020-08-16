package go.game.input;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 
import go.game.model.Action;


public class FileInputReader implements Input {

	private String input; 
	private List<Action> actionList;

	//return the content of file in String variable
	public String ReadInput(String pathname) {
		try {
			File myObj = new File(pathname);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				this.input=data;
				System.out.println(data + "\n");
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred when reading the file Input.");
			e.printStackTrace();
		}

		return input;
	}

	//this method will convert the readed input into a list of positions
	//
	//
	public List<Action> GenerateInputActions() {
		actionList= new ArrayList<>();
		String [] words = input.trim().split(" ", input.length());
		for (int i = 0; i <words.length ; i++) {
			if("pass".equalsIgnoreCase(words[i])){
				Action newAction = new Action(true);

				this.actionList.add(newAction);

			}else {
				int posi= convertCharToInt (words[i].charAt(0));
				int posj=convertCharToInt(words[i].charAt(1));

				Action newAction = new Action(posi,posj);
				this.actionList.add(newAction);

			}  
		}
		return this.actionList;
	}



	public List<Action> getActionist() {
		return this.actionList;
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
