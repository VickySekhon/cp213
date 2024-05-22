	package cp213;
	
	import java.util.Collection;
	import java.util.Scanner;
	import java.util.ArrayList;
	
	/**
	 * Stores a List of MenuItems and provides a method return these items in a
	 * formatted String. May be constructed from an existing List or from a file
	 * with lines in the format:
	 *
	 * <pre>
	1.25 hot dog
	10.00 pizza
	...
	 * </pre>
	 *
	 * @author Vicky Sekhon 
	 * @author Abdul-Rahman Mawlood-Yunis
	 * @author David Brown
	 * @version 2023-09-06
	 */
	public class Menu {
		
	// Attributes.
    // your code here
	// define a List of MenuItem objects
	ArrayList<MenuItem> items = new ArrayList<>();

    /**
     * Creates a new Menu from an existing Collection of MenuItems. MenuItems are
     * copied into the Menu List.
     *
     * @param items an existing Collection of MenuItems.
     */
    public Menu(Collection<MenuItem> items) {

	// your code here
    this.items = new ArrayList<>(items);
	}

    /**
     * Constructor from a Scanner of MenuItem strings. Each line in the Scanner
     * corresponds to a MenuItem. You have to read the Scanner line by line and add
     * each MenuItem to the List of items.
     *
     * @param fileScanner A Scanner accessing MenuItem String data.
     */
    public Menu(Scanner fileScanner) {

	// your code here
    while (fileScanner.hasNext()) {
    	// hold the line 
    	String line = fileScanner.nextLine();
    	// split the contents into a list containing two values 
    	String parts[] = line.split("\\s+",2);
    	double price = Double.parseDouble(parts[0]);
    	String name = parts[1];
    	// create the new MenuItem 
    	MenuItem newItem = new MenuItem(name, price);
    	// add it 
    	this.items.add(newItem);
    }
    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {

	// your code here
    if (! items.isEmpty()){
    	return items.get(i-1);
    } 
	return null;
    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {

	// your code here
	return items.size();
    }

    /**
     * Returns the Menu items as a String in the format:
     *
     * <pre>
    5) poutine      $ 3.75
    6) pizza        $10.00
     * </pre>
     *
     * where n) is the index + 1 of the MenuItems in the List.
     */
    @Override
    public String toString() {

	// your code here
    String format = "";
    for (int i = 1; i < items.size(); i++){
    	format += i + ")" + " " + this.getItem(i) + "\n";
    }
	return format;
    }
}