import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class mainCode {

	public static void main(String[] args) {
		/*Need to find all the invalid passwords.  
		 * ** The numbers, #-#, define the range of a certain character allowed
		 * **** First number, is the minimum amount
		 * **** Second number is the maximum amount
		 * ** The letter in the string defines the character for the requirement
		 * ** The following string defines the password
		 * */
		
		List<List<String>> passwordReq = importInformation();
		checkPassword(passwordReq);
	}
	
	//Import the lists from a csv
	public static List<List<String>> importInformation() {
		List<List<String>> passwordReq = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(".\\CSV\\passwords.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				passwordReq.add(Arrays.asList(values));
			}
			
			//Separate each value into individual parts
			for (int i = 0; i < passwordReq.size(); i++) {
				String information = passwordReq.get(i).get(0);
				String[] separated = information.split(" ");
				passwordReq.set(i, Arrays.asList(separated));
			}
		}
		catch(Exception e) {
			
		}
		
		
		return passwordReq;
	}
	
	//Check the password
	public static void checkPassword(List<List<String>> passwords) {
		int validPass = 0;
		for (int k = 0; k < passwords.size(); k++) {
			List<String> currentPassword = passwords.get(k);
			String numbers = currentPassword.get(0);
			String letterInfo = currentPassword.get(1);
			String password = currentPassword.get(2);
			String[] values = numbers.split("-");
			int is = (Integer.parseInt(values[0]) - 1);
			int isnot = (Integer.parseInt(values[1]) - 1);
			char letter = letterInfo.charAt(0);
			char first = password.charAt(is);
			char second = password.charAt(isnot);
			
			if (first != second) {
				if ((first == letter) || (second == letter)) {
					System.out.println("The password " + password + " which is entry " + (k + 1) + " is a valid password.");
					validPass++;
				}
			}
		}
		
		System.out.println("There are " + validPass + " valid passwords.");
	}
}
