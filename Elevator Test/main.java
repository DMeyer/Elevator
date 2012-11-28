import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BufferedReader readConsole = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		
		System.out.println("Enter the number of elevators.");
		
		try {
			input = readConsole.readLine();
		} catch (IOException e) {
			System.out.println("Error getting console input: " + e);
		}
		int numElevators = Integer.parseInt(input);
		
		
		Controller controller = new Controller();
		//controller.start();
		controller.createElevators(numElevators);
		controller.start();
		
		while(true){
			try {
				Thread.sleep(1010);
				System.out.println("--------");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

	}
}
