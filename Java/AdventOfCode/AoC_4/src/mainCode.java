import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class mainCode {

	public static void main(String[] args) {
		List<List<String>> passportInfo = importInformation();
		analyzePassport(passportInfo);
	}
	
	//Import the information
	public static List<List<String>> importInformation() {
		List<List<String>> passports = new ArrayList<>();
		try {
			Scanner scnr = new Scanner(new File(".\\CSV\\passports.csv"));
			scnr.useDelimiter("\\n\\s\\n");
			while (scnr.hasNext()) {
				String line = scnr.next();
				passports.add(Arrays.asList(line));
			}
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return passports;
	}
	
	//Analyze passport
	public static void analyzePassport(List<List<String>> passports) {
		int validPass = 0;
		for (int i = 0; i < passports.size(); i++) {
			String singlePassport = passports.get(i).get(0);
			String[] values = singlePassport.split(":|\\n|\\s");
			List<String> expectedValues = new LinkedList<String>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"));
			
			for (int j = 0; j < values.length; j++) {
				String check = values[j];
				if (expectedValues.contains(check)) {
					switch(check) {
					case "byr":
						if ((Integer.parseInt(values[j + 1]) >= 1920) && (Integer.parseInt(values[j + 1]) <= 2002)) {
							expectedValues.remove(check);
						}
						
						break;
					
					case "iyr":
						if ((Integer.parseInt(values[j + 1]) >= 2010) && (Integer.parseInt(values[j + 1]) <= 2020)) {
							expectedValues.remove(check);
						}
						
						break;
						
					case "eyr":
						if ((Integer.parseInt(values[j + 1]) >= 2020) && (Integer.parseInt(values[j + 1]) <= 2030)) {
							expectedValues.remove(check);
						}
						
						break;
						
					case "hgt":
						String height = values[j + 1];
						if (height.charAt(height.length() - 1) == 'm') {
							String[] heightNum = height.split("cm");
							if (Integer.parseInt(heightNum[0]) >= 150 && Integer.parseInt(heightNum[0]) <= 193) {
								expectedValues.remove(check);
							}
						}
						
						if (height.charAt(height.length() - 1) == 'n') {
							String[] heightNum = height.split("in");
							if (Integer.parseInt(heightNum[0]) >= 59 && Integer.parseInt(heightNum[0]) <= 76) {
								expectedValues.remove(check);
							}
						}
						
						break;
						
					case "hcl":
						String hair = values[j + 1];
						List<Character> characters = new LinkedList<Character>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'));
						if ((hair.charAt(0) == '#') && (hair.length() == 7)) {
							boolean validColor = true;
							for (int h = 1; h < hair.length(); h++) {
								if (!(characters.contains(hair.charAt(h)))){
									validColor = false;
								}
							}
							
							if (validColor) {
								expectedValues.remove(check);
							}
						}
						
						break;
						
					case "ecl":
						List<String> eyeColors = new LinkedList<String>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
						if (eyeColors.contains(values[j + 1])) {
							expectedValues.remove(check);
						}
						
						break;
						
					case "pid":
						String pidNumber = values[j + 1];
						if (pidNumber.length() == 9) {
							expectedValues.remove(check);
						}
						
						break;
					}
				}
			}
			
			if (expectedValues.size() == 0) {
				validPass++;
				System.out.println("Passport entry " + i + " is a valid passport.");
			}
			
			else if ((expectedValues.size() == 1) && (expectedValues.get(0) == "cid")) {
				validPass++;
				System.out.println("Passport entry " + i + " is a valid passport.");
			}
			
		}
		
		System.out.println("There are " + validPass + " valid passports.");
	}

}
