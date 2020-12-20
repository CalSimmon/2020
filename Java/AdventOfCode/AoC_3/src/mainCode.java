import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class mainCode {

	public static void main(String[] args) {
		List<List<String>> mapMap = importInformation();
		analyzePath(mapMap);
	}
	
	//Import csv information into a List List
	public static List<List<String>> importInformation() {
		List<List<String>> map = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(".\\CSV\\map.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				map.add(Arrays.asList(values));
			}
		}
		catch(Exception e) {
			
		}
		
		
		return map;
	}
	
	public static void analyzePath(List<List<String>> map) {
		//Create the list for special movement values
		List<List<Integer>> values = new ArrayList<>();
		List<Integer> addList = Arrays.asList(1,1);
		values.add(addList);
		addList = Arrays.asList(3,1);
		values.add(addList);
		addList = Arrays.asList(5,1);
		values.add(addList);
		addList = Arrays.asList(7,1);
		values.add(addList);
		addList = Arrays.asList(1,2);
		values.add(addList);
		
		//Set base variables
		int totalCount = 1;
		for (int j = 0; j < values.size(); j++) {
			List<Integer> currentValues = values.get(j);
			int right = 0;
			int treeCount = 0;
			
			//Get the char at the expected location
			for (int i = 0; i < map.size(); i = i + currentValues.get(1)) {
				String square = map.get(i).get(0);
				char analyze = square.charAt(right);
				
				//If it is a #, add to treeCount
				if (analyze == '#') {
					treeCount++;
				}
				
				//Move to the right per the value instructions
				right = right + currentValues.get(0);
				
				//If the right value is longer than the array, go back
				if (right >= square.length()) {
					right = right - square.length();
				}
			}
			
			//Calculate how many trees hit per wave
			System.out.println("For Right " + currentValues.get(0) + " and Down " + currentValues.get(1) + ", you ran into " + treeCount + " trees.");
			totalCount = totalCount * treeCount;
		}
		
		//Print answer
		System.out.println("The answer is " + totalCount);
	}

}
