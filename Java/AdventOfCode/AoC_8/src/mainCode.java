import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainCode {

	public static void main(String[] args) {
		List<String> instructions = importInstr();
		int accumulator = part1(instructions);
		System.out.println("The number in the accumulator when repeated was " + accumulator + "\n");
		accumulator = part2(instructions);
		System.out.println("The number in the accumulator when the loop terminated was " + accumulator);
	}
	
	public static int part1(List<String> instructions) {
		List<Integer> ran = new ArrayList<Integer>();
		int i = 0;
		int accumulator = 0;
		while (!ran.contains(i)) {
			ran.add(i);
			String[] current = (instructions.get(i)).split(" ");
			String currentInstr = current[0];
			
			switch(currentInstr) {
			case "acc":
				accumulator += Integer.parseInt(current[1]);
				i++;
				break;
			case "jmp":
				i += Integer.parseInt(current[1]);
				break;
			case "nop":
				i++;
				break;
			}
		}
		
		System.out.println("The pattern repeated line " + i);
		
		return accumulator;
	}
	
	public static int part2(List<String> instructions) {
		List<Integer> ran2 = new ArrayList<Integer>();
		int j = 0;
		int accumulator2 = 0;
		while (!(ran2.contains(j))) {
			ran2.add(j);
			String[] current = (instructions.get(j)).split(" ");
			String currentInstr = current[0];
			
			switch(currentInstr) {
			case "acc":
				accumulator2 += Integer.parseInt(current[1]);
				j++;
				break;
			case "jmp":
				//Break it out to try the loop with the jmp changed
				int result = tryFix(instructions, accumulator2, j, ran2);
				
				if (result > 0) {
					System.out.println("Changing the jmp at section " + j + " fixed the loop");
					return result;
				}
				
				
				j += Integer.parseInt(current[1]);
				break;
			case "nop":
				j++;
				break;
			}
		}
		
		System.out.println("The pattern repeated line " + j);
		
		return accumulator2;
	}
	
	public static int tryFix(List<String> instructions, int accumulatorTry, int jTry, List<Integer> ranTry) {
		List<Integer> ranHere = new ArrayList<Integer>();
		for (Integer element: ranTry) {
			ranHere.add(element);
		}
		
		
		jTry++;
		while (!ranHere.contains(jTry)) {
			if (jTry >= instructions.size()) {
				return accumulatorTry;
			}
			
			else if (jTry < 0) {
				break;
			}
			
			ranHere.add(jTry);
			String[] currentTry = ((instructions.get(jTry)).split(" "));
			String currentInstrTry = currentTry[0];
			
			switch(currentInstrTry) {
			case "acc":
				accumulatorTry += Integer.parseInt(currentTry[1]);
				jTry++;
				break;
			case "jmp":
				jTry += Integer.parseInt(currentTry[1]);
				break;
			case "nop":
				jTry++;
				break;
			}
		}
		
		return 0;
	}
	
	public static List<String> importInstr() {
		List<String> answers = new ArrayList<>();
		try {
			Scanner scnr = new Scanner(new File(".\\CSV\\infinity.csv"));
			scnr.useDelimiter(",");
			while (scnr.hasNext()) {
				String line = scnr.nextLine();
				answers.add(line);
			}
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return answers;
	}

}
