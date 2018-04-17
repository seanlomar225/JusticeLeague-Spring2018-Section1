import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Scanner;
import java.util.Set;

import javax.swing.DefaultListModel;


//RoomID—-PuzzleID—-Riddle—-Choice—-Solution—-Hint

public class Puzzle extends Observable {
	Scanner keyboard = new Scanner (System.in );
	private static FileReader fR;
	private static Scanner sR;
	
	private Map<String, ArrayList<String>> puzzleMap = new HashMap<String, ArrayList<String>>();
	public List<String[]> puzzleInRoom = new ArrayList<String[]>();
	public String hint;
	public String choice;
	public String riddle;
	public String answer;
	public String question;

	// this hashmap splits the puzzle data for the system to know what can be used and what cannot be used. 
	//If puzzle is not in this list the system cannot use it.
	public Map<String,String[]> puzzleCanUse = new HashMap<String,String[]>();
	
	
	
	public Puzzle() {
		
		fetchPuzzle();
}	
	
	public void fetchPuzzle() {
		
		try {
			fR = new FileReader("Puzzle.txt");
			sR = new Scanner(fR);

		    while(sR.hasNextLine()) {
		    		String line = sR.nextLine();
		    		String[] splitLine = line.split("- - "); //splitLine = [RoomID—-PuzzleID—-Riddle—-Choice—-Solution—-Hint]
		    		String e = splitLine[0];  //string e = roomID
		       
		       if(puzzleMap.containsKey(e)) { // if 
					ArrayList<String> localArray1 = new ArrayList<String>();
					for(int i = 0; i < puzzleMap.get(e).size();i++) {
						localArray1.add(puzzleMap.get(e).get(i)); // 
					}
					localArray1.add(line);
					puzzleMap.put(e, localArray1);
					
				}else {
					ArrayList<String> localArray2 = new ArrayList<String>();
					localArray2.add(line);
					puzzleMap.put(e, localArray2);
				}

		    }
		} catch (IOException e) {		   
		    e.printStackTrace();
		    
		} finally {
		    try {
		        //in.close();
		        fR.close();
				sR.close();
		    } catch (Exception e) {
		    }
		}
}
	
	
	public void getPuzzle(String s) {
		puzzleInRoom.clear();
			if(puzzleMap.containsKey(s)) { // 
				for(int i = 0; i < puzzleMap.get(s).size();i++) {
					String puzzle = (puzzleMap.get(s).get(i));
					String[] z = puzzle.split(",");
					puzzleInRoom.add(z);
					String[] t = {z[2],z[3],z[4],z[5]};
					puzzleCanUse.put(z[1],t);
				}	
			}else {
			System.out.println("Sorry No Puzzle Here");
			}
			}

	public void examine(String s) {

		if(puzzleCanUse.containsKey(s)) {
			String[] arrayHold = puzzleCanUse.get(s);
			this.question = arrayHold[1];
			this.choice = arrayHold[2];
			this.answer = arrayHold[3];
			this.hint = arrayHold[4];
		}
		}


	public String getQuestion() {
	//	String q = question;	
		return question;
	}


	public String getChoice() {
	//	String c = choice;
		return choice;
	}

	public String getAnswer(String a) { // String a is the answer from user 
		if(answer.contains(a)) {
			String r= "That's right!";
			return r;
		} else {
			String r= "That's wrong!";
			return r;
		} 
	}

	public String getHint() {
		return hint;
	}

	public String toString() {
	//	String print = q + "\n" + c;
		String print = question + "\n" + choice;
		return print;
	}
	
	public String quit() {
		    String quit = "You quit the puzzle.";
		    return quit;
		    } 	

}

