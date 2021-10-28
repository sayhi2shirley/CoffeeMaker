import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * Starts the console UI for the CoffeeMaker
 */
public class Main {
	private static CoffeeMaker coffeeMaker;

	/**
	 * Passes a prompt to the user and returns the user specified 
	 * string.
	 * @param message
	 * @return int
	 */
	private static int inputOutput(String message) {
		System.out.println(message);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int returnValue = 0;
		try {
			returnValue = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Error reading in integer value");
			mainMenu();
		}
		catch (IOException e){
			System.out.println("Error reading in value");
			mainMenu();
		}
		return returnValue;
	}


	/**
	 * Add inventory user interface that processes input.
	 */
	public static void addIngredients() {
		//Read in amt coffee
		int coffee = inputOutput("\nPlease enter the units of coffee to add: ");

		//Read in amt milk
		int milk = inputOutput("\nPlease enter the units of milk to add: ");

		//Read in amt chocolate
		int chocolate = inputOutput("\nPlease enter the units of chocolate to add: ");

		try {
			coffeeMaker.addIngredients(coffee, milk, chocolate);
			System.out.println("Ingredients successfully added");
		} catch (Exception e) {
			System.out.println("Inventory was not added");
		} finally {
			mainMenu();
		}
	}

	/**
	 * Check ingredients user interface that processes input.
	 */
	public static void checkIngredients() {
		System.out.println(coffeeMaker.checkIngredients());
		mainMenu();
	}

	/**
	 * Make coffee user interface the processes input.
	 */
	public static void makeCoffee() {

		System.out.println("1. Espresso");
		System.out.println("2. Latte");

		int item = inputOutput("Please enter the item you wish to purchase");

		int amtPaid = inputOutput("Please enter the amount you are paying");

		if (item >= 1 && item <=2) {
			int change = 0;
			switch (item) {
			case 1:
				change = coffeeMaker.makeEspresso(amtPaid);
				break;
			case 2:
				change = coffeeMaker.makeLatte(amtPaid);
				break;
			}

			if (change == amtPaid) {
				System.out.println("Insufficient funds to purchase.");
			} else {
				System.out.println("Thanks for purchasing");
			}
			System.out.println("Your change is: " + change + "\n");
			mainMenu();
		} else {
			System.out.println("Please enter a number from 1 - 2");
			mainMenu();
		}
	}

	/**
	 * Prints the main menu and handles user input for 
	 * main menu commands.
	 */
	public static void cleanup() {
		coffeeMaker.cleanup();
		System.out.println("Thanks for cleaning up the machine");
		mainMenu();
	}

	/**
	 * Prints the main menu and handles user input for 
	 * main menu commands.
	 */
	public static void mainMenu() {
		System.out.println("1. Make Coffee");
		System.out.println("2. Cleanup");
		System.out.println("3. Add ingredients");
		System.out.println("4. Display ingredients");
		System.out.println("0. Exit\n");

		//Get user input
		int userInput = inputOutput("Please press the number that corresponds to what you would like the coffee maker to do.");

		if (userInput >= 0 && userInput <=4) {
			if (userInput == 1) makeCoffee();
			if (userInput == 2) cleanup();
			if (userInput == 3) addIngredients();
			if (userInput == 4) checkIngredients();
			if (userInput == 0) System.exit(0);
		} else {
			System.out.println("Please enter a number from 0 - 4");
			mainMenu();
		}
	}

	/**
	 * Starts the coffee maker program.
	 * @param args
	 */
	public static void main(String[] args) {
		coffeeMaker = new CoffeeMaker();
		System.out.println("Welcome to the CoffeeMaker!\n");
		mainMenu();
	}
}
