import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainCode {

	public static void main(String[] args) {
		List<Long> encryptInfo = importInfo();
		//System.out.println(encryptInfo);
		long answer = part1Answer(encryptInfo);
		part2Answer(encryptInfo, answer);
	}
	
	public static long part1Answer(List<Long> encryption) {
		int minCheck = 0;
		int maxCheck = 24;
		
		for (int i = 25; i < encryption.size(); i++) {
			long currentNum = encryption.get(i);
			boolean sums = false;
			
			while (!sums) {
				for (int j = minCheck; j <= maxCheck; j++) {
					long first = encryption.get(j);
					
					for (int k = minCheck; k <= maxCheck; k++) {
						long second = encryption.get(k);
						
						if ((j != k) && (first + second == currentNum)) {
							sums = true;
						}
					}
				}
				
				if (!sums) {
					System.out.println(currentNum + " does not follow the pattern.");
					return currentNum;
				}
			}
			
			minCheck++;
			maxCheck++;
			
		}
		return 0;
	}
	
	public static void part2Answer(List<Long> encryption, long answer) {
		for (int i = 0; i < encryption.size(); i++) {
			long currentSum = 0;
			currentSum += encryption.get(i);
			
			for (int j = (i + 1); j < encryption.size(); j++) {
				currentSum += encryption.get(j);
				
				if (currentSum == answer) {
					long min = 1000000000;
					long max = 0;
					
					for (int k = i; k < (j + 1); k++) {
						if (encryption.get(k) < min) {
							min = encryption.get(k);
						}
						
						if (encryption.get(k) > max) {
							max = encryption.get(k);
						}
					}
					
					System.out.println("The encryption weakness is " + (min + max));
					return;
				}
				
				else if (currentSum > answer){
					break;
				}
			}
		}
	}
	
	public static List<Long> importInfo(){
		List<String> information = new ArrayList<>();
		List<Long> encryption = new ArrayList<>();
		try {
			Scanner scnr = new Scanner(new File(".\\CSV\\encryption.csv"));
			scnr.useDelimiter(",");
			while (scnr.hasNext()) {
				String line = scnr.nextLine();
				information.add(line);
			}
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (String element: information) {
			encryption.add(Long.parseLong(element));
		}
		
		return encryption;
	}
	
}
