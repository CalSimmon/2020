import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class mainCode {
	
	//Main function
	public static void main(String[] args) {
		List<List<String>> surveyAnswers = getAnswers();
		part1Answer(surveyAnswers);
		System.out.println("\n");
		part2Answer(surveyAnswers);
	}
	
	//Parse .csv to get all sections of answers
	public static List<List<String>> getAnswers() {
		List<List<String>> answers = new ArrayList<>();
		try {
			Scanner scnr = new Scanner(new File(".\\CSV\\answers.csv"));
			scnr.useDelimiter("\\n\\s\\n");
			while (scnr.hasNext()) {
				String line = scnr.next();
				answers.add(Arrays.asList(line));
			}
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return answers;
	}
	
	//Get answer to part 1
	public static void part1Answer(List<List<String>> answers) {
		int sum = 0;
		for (int i = 0; i < answers.size(); i++) {
			List<Character> currentValues = new LinkedList<Character>();
			String currentGroup = answers.get(i).get(0);
			String[] ansArr = currentGroup.split("\\n");
			
			for (String element: ansArr) {
				for (char c: element.toCharArray()) {
					if (!(currentValues.contains(c))) {
						currentValues.add(c);
					}
				}
			}
			
			sum = sum + (currentValues.size() - 1);
			
			System.out.println("Group " + (i + 1) + " had " + (currentValues.size() - 1) + " unique answers.");
		}
		
		System.out.println("The answer is " + sum + ".");
	}
	
	//Get answer to part 2
	public static void part2Answer(List<List<String>> answers) {
		int sum = 0;
		for (int j = 0; j < answers.size(); j++) {
			List<Character> currentValues = new LinkedList<Character>();
			String currentGroup = answers.get(j).get(0);
			String[] ansArr = currentGroup.split("\\n");
			
			for (String element: ansArr) {
				for (char c: element.toCharArray()) {
					boolean addOr = true;
					for (String check: ansArr) {
						int presentin = check.indexOf(c);
						
						if (!(presentin >= 0)) {
							addOr = false;
						}
					}
					
					if (addOr) {
						if (!(currentValues.contains(c))) {
							currentValues.add(c);
						}
					}
				}
			}
			
			sum = sum + (currentValues.size() - 1);
			
			System.out.println("Group " + (j + 1) + " all said yes to " + (currentValues.size() - 1) + " answers.");
		}
		
		System.out.println("The answer is " + sum + ".");
	}
}
