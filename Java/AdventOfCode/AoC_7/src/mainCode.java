import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class mainCode {
	static List<String> allContains = new LinkedList<String>();
	
	public static void main(String[] args) {
		List<List<String>> rulesInfo = importRules();
		part1Answer(rulesInfo);
		System.out.println("\n");
		part2Answer(rulesInfo);
	}
	
	public static List<List<String>> importRules() {
		List<List<String>> luggageRules = new ArrayList<>();
		try {
			Scanner scnr = new Scanner(new File(".\\CSV\\luggage.csv"));
			scnr.useDelimiter("\\n");
			while (scnr.hasNext()) {
				String line = scnr.next();
				luggageRules.add(Arrays.asList(line));
			}
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return luggageRules;
	}
	
	//Solve part 1
	public static void part1Answer(List<List<String>> luggage) {
		String[] yourBag = {"shiny gold bags"};
		List<String> canContain = Arrays.asList(yourBag);
		recursive(canContain, luggage);
		
		System.out.println("There are " + (allContains.size()) + " bags that can contain your bag.");
	}
	
	public static void recursive(List<String> containsBag, List<List<String>> luggage) {
		if (containsBag.size() > 0) {
			List<String> newContains = new LinkedList<String>();
			for (int i = 0; i < luggage.size(); i++) {
				boolean contains = false;
				String rule = luggage.get(i).get(0);
				String[] ruleSep = rule.split(" contain |, |\\.");
				
				for (int j = 1; j < ruleSep.length; j++) {
					String currentReq = ruleSep[j];
					String[] temp = currentReq.split(" ", 2);
					List<String> currentReqSplit = Arrays.asList(temp);
					
					for (String element: containsBag) {
						String elementTrunc = element.substring(0, element.length() - 1);
						if (currentReqSplit.contains(element)) {
							contains = true;
						}
						
						else if (currentReqSplit.contains(elementTrunc)) {
							contains = true;
						}
					}
				}
				
				if (contains) {
					if (!(allContains.contains(ruleSep[0]))) {
						System.out.println("The " + ruleSep[0] + " can contain your bag.");
						newContains.add(ruleSep[0]);
						allContains.add(ruleSep[0]);
					}
				}
			}
			
			recursive(newContains, luggage);
		}
	}
	
	public static void part2Answer(List<List<String>> luggage) {
		String yourBag = "shiny gold bags";
		int currentCount = recursiveContain(yourBag, luggage);
		//You have to remove 1 from the currentCount because the function adds one at the end of the program.
		System.out.println("Your shiny gold bag would contain " + (currentCount - 1) + " other bags.");
	}
	
	public static int recursiveContain(String yourBag, List<List<String>> luggage) {
		int totalBranch = 0;
		for (int i = 0; i < luggage.size(); i++) { 
			String rule = luggage.get(i).get(0);
			String noPeriod = rule.substring(0, rule.length() - 2);
			String[] ruleSep = noPeriod.split(" contain |, ");
			
			if (ruleSep[0].contains(yourBag)) {
				for (int k = 1; k < ruleSep.length; k++) {
					String currentReq = ruleSep[k];
					String[] current = currentReq.split(" ", 2);
					String currentBag = current[1];
					
					if (currentBag.contains("other bags")) {
						return 1;
					}
					
					int numBags = recursiveContain(currentBag, luggage);
					totalBranch = totalBranch + (numBags * Integer.parseInt(current[0]));
				}
				
				//Add 1 to include the bag itself
				return totalBranch + 1;
			}
		}
		
		return totalBranch;
	}
}
