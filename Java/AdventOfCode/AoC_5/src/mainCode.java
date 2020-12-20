import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class mainCode {

	public static void main(String[] args) {
		List<List<String>> boardingInfo = importBP();
		List<Integer> seatIDArr = findLocation(boardingInfo);
		findSeat(seatIDArr);
	}
	
	public static List<List<String>> importBP() {
		List<List<String>> boardingPasses = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(".\\CSV\\boardingpasses.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				boardingPasses.add(Arrays.asList(values));
			}
		}
		catch(Exception e) {
			
		}
		
		return boardingPasses;
	}
	
	public static List<Integer> findLocation(List<List<String>> boardingPasses) {
		List<Integer> seatIDArr = new ArrayList<Integer>();
		int highestID = 0;
		
		for (int i = 0; i < boardingPasses.size(); i++) {
			String assignment = boardingPasses.get(i).get(0);
			int rowMin = 0;
			int rowMax = 127;
			int columnMin = 0;
			int columnMax = 7;
			
			for (int j = 0; j < assignment.length(); j++) {
				char analyze = assignment.charAt(j);
				int rowDifference = ((rowMax - rowMin) / 2) + 1;
				int columnDifference = ((columnMax - columnMin) / 2) + 1;
				
				switch (analyze) {
				case 'B':
					rowMin = rowMin + rowDifference;
					break;
				case 'F':
					rowMax = rowMax - rowDifference;
					break;
				case 'R':
					columnMin = columnMin + columnDifference;
					break;
				case 'L':
					columnMax = columnMax - columnDifference;
					break;
				}
			}
			
			int seatID = (rowMax * 8) + columnMax;
			System.out.println("Seat " + i + " ID is " + seatID);
			seatIDArr.add(seatID);
		}
		
		for (int p = 0; p < seatIDArr.size(); p++) {
			if (seatIDArr.get(p) > highestID) {
				highestID = seatIDArr.get(p);
			}
		}
		
		System.out.println("The highest ID is: " + highestID);
		return seatIDArr;
	}
	
	public static void findSeat(List<Integer> seatIDArr) {
		for (int k = 21; k < 996; k++) {
			if (!(seatIDArr.contains(k))) {
				System.out.println("Seat ID " + k + " is not in the list.");
			}
		}
	}
}
