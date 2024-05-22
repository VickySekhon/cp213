package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Utilities for working with Food objects.
 *
 * @author Vicky Sekhon, 169024498, sekh4498@mylaurier.ca
 * @version 2023-05-07
 */
public class FoodUtilities {

    /**
     * Determines the average calories in a list of foods. No rounding necessary.
     * Foods list parameter may be empty.
     *
     * @param foods a list of Food
     * @return average calories in all Food objects
     */
    public static int averageCalories(final ArrayList<Food> foods) {

    	// your code here
    	// passed a list of food objects ie Food(name, origin, isvegetarian, calories)
    	// iterate through the list of food objects and add all food.calories to count, use a length tracker so that we can divide count/length 
    	// to get average
    	int totalCalories = 0; 
    	int count = 0;
    	if (!foods.isEmpty()) {
    		for (Food food: foods) {
    			totalCalories += food.getCalories();
    			count++;
    		}
    	}
    	totalCalories = totalCalories/count;

    	return totalCalories;
    }

    /**
     * Determines the average calories in a list of foods for a particular origin.
     * No rounding necessary. Foods list parameter may be empty.
     *
     * @param foods  a list of Food
     * @param origin the origin of the Food
     * @return average calories for all Foods of the specified origin
     */
    public static int averageCaloriesByOrigin(final ArrayList<Food> foods, final int origin) {

		// your code here
    	// iterate through the list of food objects
    	// use if statement to check if the origin of the food object matches the origin passed as a parameter 
    	// if it does, then add the objects calories attribute to the total calories 
    	
    	int totalCalories = 0;
    	int count = 0;
    	for(Food food: foods) {
    		if(food.getOrigin() == origin) {
    			totalCalories += food.getCalories();
    			count++;
    		}
    	}
    	totalCalories = totalCalories/count; 
	
		return totalCalories;
    }

    /**
     * Creates a list of foods by origin.
     *
     * @param foods  a list of Food
     * @param origin a food origin
     * @return a list of Food from origin
     */
    public static ArrayList<Food> getByOrigin(final ArrayList<Food> foods, final int origin) {

    	// your code here
    	/*
    	 * Create an empty ArrayList for food objects
    	 * 
    	 * Iterate through the list of food objects (parameter) and check what food objects match the origin passed to us 
    	 * 
    	 * If the food object origin matches the origin passed to us as parameter, then we append to the new list 
    	 * 
    	 * */
    	
    	ArrayList<Food> foodsByOrigin = new ArrayList<Food>();
    	
    	for(Food food: foods) {
    		if(food.getOrigin() == origin) {
    			foodsByOrigin.add(food);
    		}
    	}
    	
    	

    	return foodsByOrigin;
    }

    /**
     * Creates a Food object by requesting data from a user. Uses the format:
     *
     * <pre>
    Name: name
    Origins
     0 Canadian
     1 Chinese
    ...
    11 English
    Origin: origin number
    Vegetarian (Y/N): Y/N
    Calories: calories
     * </pre>
     *
     * @param keyboard a keyboard Scanner
     * @return a Food object
     */
    public static Food getFood(final Scanner keyboard) {

		// your code here
    	
    	// hold each attribute of the food object inside a variable
    	// create a new food object using those attributes eg Food object = new Food(name, origin, isvegetarian, calories)
    	
    	String name = "";
    	int origin = -1; 
    	boolean vegetarian = false; 
    	int calories = 0;
    	
    	// first attribute to get for the food object 
    	System.out.print("Name: ");
    	// check if the user inputs something 
    	while (true) {
    		name = keyboard.next();
    		System.out.println("Origins");
    		System.out.println(Food.originsMenu());
    		System.out.print("Origin: ");
    		origin = keyboard.nextInt();
    		System.out.print("Vegetarian (true/false): ");
    		vegetarian = keyboard.nextBoolean();
    		System.out.print("Calories: ");
    		calories = keyboard.nextInt();
    		break;
    	}
    	
    	Food newFood = new Food(name, origin, vegetarian, calories);
		
		return newFood;
    }

    /**
     * Creates a list of vegetarian foods.
     *
     * @param foods a list of Food
     * @return a list of vegetarian Food
     */
    public static ArrayList<Food> getVegetarian(final ArrayList<Food> foods) {

		// your code here
    	
    	// create new list of vegetarian food objects
    	// iterate through the food objects, check if the food object is vegetarian
    	// if it is then add it to the new vegetarian objects list
    	
    	ArrayList<Food> vegetarianFoods = new ArrayList<Food>();
    	
    	for(Food food: foods) {
    		if(food.isVegetarian() == true) {
    			vegetarianFoods.add(food);
    		}
    	}
    	
	
		return vegetarianFoods;
    }

    /**
     * Creates and returns a Food object from a line of string data.
     *
     * @param line a vertical bar-delimited line of food data in the format
     *             name|origin|isVegetarian|calories
     * @return the data from line as a Food object
     */
    public static Food readFood(final String line) {

		// your code here
    	
    	/*
    	 * we are passed a 'line' of food data: name|origin|isVegetarian|calories
    	 * 
    	 * we want to create a food object from that line of food data: eg Food object = new Food(food, origin, etc..)
    	 * 
    	 * break the line at each "|" using split and create variables for each attribute of the food object 
    	 * 
    	 * create new food object using those attributes
    	 * 
    	 * */
    	
    	
    	// initialize the variables to hold the attribute data
    	String name;
    	int origin;
    	boolean vegetarian; 
    	int calories;
    	
    	// create a list that will hold each attribute in an index
    	ArrayList<String> components = new ArrayList<String>();
    	// add to the list by splitting the string at each "|" 
    	components.addAll(Arrays.asList(line.split("\\|")));
    	
    	// fill out the variables with attribute data
    	name = components.get(0);
    	origin = Integer.parseInt(components.get(1));
    	vegetarian = Boolean.parseBoolean(components.get(2));
    	calories = Integer.parseInt(components.get(3));
    	
    	// create the food object 
    	Food object = new Food(name, origin, vegetarian, calories);
    	
    	
		return object;
    }

    /**
     * Reads a file of food strings into a list of Food objects.
     *
     * @param fileIn a Scanner of a Food data file in the format
     *               name|origin|isVegetarian|calories
     * @return a list of Food
     */
    public static ArrayList<Food> readFoods(final Scanner fileIn) {

    	// your code here
    	
    	// create new food list for all food objects to be passed into
    	ArrayList<Food> allFoods = new ArrayList<Food>();
    	
    	// check if there are contents to read in the file
    	while(fileIn.hasNextLine()) {
    		// convert the line to a variable to call using our read food method 
    		String line = fileIn.nextLine();
    		// call the method to create food objects for each line and add the line to the allFoods array
    		allFoods.add(FoodUtilities.readFood(line));
    		
    	}
    	
    	return allFoods;
    }

    /**
     * Searches for foods that fit certain conditions.
     *
     * @param foods        a list of Food
     * @param origin       the origin of the food; if -1, accept any origin
     * @param maxCalories  the maximum calories for the food; if 0, accept any
     * @param isVegetarian whether the food is vegetarian or not; if false accept
     *                     any
     * @return a list of foods that fit the conditions specified
     */
    public static ArrayList<Food> foodSearch(final ArrayList<Food> foods, final int origin, final int maxCalories, final boolean isVegetarian) {

		// your code here
    	// given an array list of foods 
    	// check if there are food objects that fit the given attributes
    	// if they are then add them to a new list array that we will return to the user
    	
    	// new array of food objects that meet the criteria
    	ArrayList<Food> specialFoods = new ArrayList<Food>();
    	
    	// iterate through the food objects
    	for(Food food: foods) {
    		// if the origin matches 
    		if(food.getOrigin() == origin || food.getOrigin() == -1) {
    			// check if the maxcalories is valid
    			if(food.getCalories() < maxCalories || food.getCalories() == 0) {
    				// check if the food is vegetarian or not
    				if(food.isVegetarian() == isVegetarian || food.isVegetarian() == false) {
    					specialFoods.add(food); // the food object meets the criteria
    				}
    			}
    		} 
    	}
		return specialFoods;
    }

    /**
     * Writes the contents of a list of Food to a PrintStream.
     *
     * @param foods a list of Food
     * @param ps    the PrintStream to write to
     */
    public static void writeFoods(final ArrayList<Food> foods, PrintStream ps) {

    	// your code here
    	
    	// given a list of food objects
    	// write each one to the PrintStream
    	// iterate through the list and println to the ps 
    	if(!foods.isEmpty()) {
    		for(Food food: foods) {
    			ps.println(food);
    		}
    	}

    }
}
