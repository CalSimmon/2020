import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class mainCode {

	public static void main(String[] args) {
		//Need to fix expense report
		
		//Find two entries in an expense report that multiply to 2020 and then multiply
		List<List<String>> expenses = importExpenses();
		checkEntries(expenses);		
	}
	
	//Import all entries from expenses.csv into program
	public static List<List<String>> importExpenses() {
		List<List<String>> expenses = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(".\\CSV\\expenses.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				expenses.add(Arrays.asList(values));
			}
		}
		catch(Exception e) {
			
		}
		
		
		return expenses;
	}
	
	//Run through entries to find those that add to 2020
	public static void checkEntries(List<List<String>> expenseList) {
		List<List<String>> expenses = expenseList;
		for (int i = 0; i < expenses.size(); i++) {
			int x = Integer.parseInt(expenses.get(i).get(0));			
			for (int j = 0; j < expenses.size(); j++) {
				if (i != j) {
					int y = Integer.parseInt(expenses.get(j).get(0));
					for (int k = 0; k < expenses.size(); k++) {
						if ((i != k) && (j != k)) {
							int z = Integer.parseInt(expenses.get(k).get(0));
							if ((x + y + z) == 2020) {
								System.out.println(x * y * z);
							}
						}
					}
				} 
			}
		}
	}
}
