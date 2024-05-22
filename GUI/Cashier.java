package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author Vicky Sekhon 
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-09-06
 */
public class Cashier {

    // Attributes
    private Menu menu = null;
    
    private static final String LINE = "-".repeat(40);

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }

    /**
     * Prints the menu.
     */
    private void printCommands() {
	System.out.println("\nMenu:");
	System.out.println(menu.toString());
	System.out.println("Press 0 when done.");
	System.out.println("Press any other key to see the menu again.\n");
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
	System.out.println("Welcome to WLU Foodorama!");
	
	// your code here
	
	printCommands();
	String userInStri;
	int userInInt;
	
	Scanner scanner = new Scanner(System.in);
	// customer order
	Order order = new Order(); 
	while (true) { 
		System.out.print("Command: ");
		// numeric value entered 
		if (scanner.hasNextInt()) {
			userInInt = scanner.nextInt();
			// valid entry entered
			if (userInInt >= 1 && userInInt <= 7) {
				// food 
				MenuItem food = this.menu.getItem(userInInt);
				System.out.print("How many do you want: ");
				// valid quantity 
				if (scanner.hasNextInt()) {
					userInInt = scanner.nextInt();
					order.add(food, userInInt);
				// invalid quantity
				} else {
					System.out.println("Not a valid number.");
					scanner.next();
				}
			// order is complete
			} else if (userInInt == 0) {
				System.out.println();
				System.out.println(LINE);
				System.out.println(order.toString());
				break; 
			// input is anything other than 0 (close the program) and 1-7 (valid menu indexes)
			} else {
				// show the user the menu 
				printCommands();
			}
		// invalid entry entered
		} else if (scanner.hasNext()) {
			userInStri = scanner.next();
			if (userInStri instanceof String) {
				System.out.println("Not a valid number.");
				printCommands();				
			}
		}
	}
	scanner.close();
	return null;
    }
}