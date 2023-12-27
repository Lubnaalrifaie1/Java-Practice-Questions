package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Food objects.
 *
 * @author Gbone Atsagbede; 203359820; atsa9820@mylaurier.ca
 * @version 2021-09-24
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
    	int average = 0; 
    	int sum = 0; 
    	int d = 0;
    	
    	if (foods.size()== 0) {
    		average = 0;
    	}
    	else {
    		for (d=0; d <foods.size(); d++) {
    			int calories = foods.get(d).getCalories() ; 
    			sum += calories; 
    		}
    			average = sum/d ; 
    			
    		}
	return average;
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

    	int average = 0; 
    	int sum = 0; 
    	int x = 0;
    	int count = 0;
    
    	if (foods.size()== 0) {
    		average = 0;
    	}
    	else {
    		for (x=0; x <foods.size(); x++) {
    			int origin1 = foods.get(x).getOrigin() ; 
    			if (origin == origin1) {
    				count+=1;
    				sum += foods.get(x).getCalories();
    			}
    		}
    			average = sum/count ; 	
    		}
	return average;

    }

    /**
     * Creates a list of foods by origin.
     *
     * @param foods  a list of Food
     * @param origin a food origin
     * @return a list of Food from origin
     */
    public static ArrayList<Food> getByOrigin(final ArrayList<Food> foods, final int origin) {
    	int z = 0; 
    	ArrayList<Food> origin_food = new ArrayList<> (); 
    	for (z=0; z <foods.size(); z++) {
    		if (foods.get(z).getOrigin() == origin) {
    			origin_food.add(foods.get(z));
    		}
    	}
	return origin_food;
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
    	
//    	Scanner keyboard = new Scanner(System.in);
    	boolean vege; 
    	System.out.println("Enter name: ");
    	String name = keyboard.next();
    	System.out.println("Enter origin: ");
    	System.out.println(Food.originsMenu()); 
    	int origin = keyboard.nextInt();
    	System.out.println("Vegetarian (Y/N): ");
    	
    	String veg = keyboard.next();
    	if (veg == "Y") {
    		vege = true;
    	}
    	else {
    		vege = false;
    	}
    	System.out.println("Enter calories: ");
    	int calories = keyboard.nextInt();

    	
    	Food item = new Food(name, origin, vege, calories);
	return item;
    }

    /**
     * Creates a list of vegetarian foods.
     *
     * @param foods a list of Food
     * @return a list of vegetarian Food
     */
    public static ArrayList<Food> getVegetarian(final ArrayList<Food> foods) {
    	int z = 0; 
    	ArrayList<Food> veg_food = new ArrayList<> (); 
    	for (z=0; z <foods.size(); z++) {
    		if (foods.get(z).isVegetarian()) 
    			veg_food.add(foods.get(z));
    	}
    		
	return veg_food;
    }

    /**
     * Creates and returns a Food object from a line of string data.
     *
     * @param line a vertical bar-delimited line of food data in the format
     *             name|origin|isVegetarian|calories
     * @return the data from line as a Food object
     */
    public static Food readFood(final String line) {
    	String[] data = line.split("[|]");
        String name = data[0];
        int origin = Integer.valueOf(data[1]);
        boolean isVegeterian = Boolean.valueOf(data[2]);
        int calories = Integer.valueOf(data[3]);
        Food item = new Food(name, origin, isVegeterian, calories);
        return item;

    }

    /**
     * Reads a file of food strings into a list of Food objects.
     *
     * @param fileIn a Scanner of a Food data file in the format
     *               name|origin|isVegetarian|calories
     * @return a list of Food
     */
    public static ArrayList<Food> readFoods(final Scanner fileIn) {
    	ArrayList<Food> food = new ArrayList<> (); 

    	while (fileIn.hasNextLine()) {
    	    String line = fileIn.nextLine();
    	   Food items = readFood(line);
    	    food.add(items);
    	}
	return food;
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
   
    	
    public static ArrayList<Food> foodSearch(final ArrayList<Food> foods, final int origin, final int maxCalories,
	    final boolean isVegetarian) {
    
    	ArrayList<Food> cond_food = new ArrayList<> (); 
    	 
    	for (int x=0; x <foods.size(); x++) {
    		if ((foods.get(x).getOrigin()==origin)&&(foods.get(x).getCalories()< maxCalories)&&(foods.get(x).isVegetarian() == isVegetarian)){
    			cond_food.add(foods.get(x));
    		}
    	}
    	
    	
    	return cond_food;
//    	int x = 0;  
//    	for (x=0; x <foods.size(); x++) {
//    		if (foods.get(x).getCalories()< maxCalories) {
//    			cond_food.add(foods.get(x));
//    		}
//    	}
//    		
//    	cond_food = getByOrigin(foods,origin); 
//    	cond_food = getVegetarian(foods);
//    	
//    	return cond_food;
    
    	}
   

    /**
     * Writes the contents of a list of Food to a PrintStream.
     *
     * @param foods a list of Food
     * @param ps    the PrintStream to write to
     */
    public static void writeFoods(final ArrayList<Food> foods, PrintStream ps) {
//    	PrintStream ps = new PrintStream(); 
    	for (int i=0; i<foods.size(); i++) {
    		String name = foods.get(i).getName();
    		int origin = foods.get(i).getOrigin();
    		boolean isVegetarian = foods.get(i).isVegetarian();
    		int calories = foods.get(i).getCalories();
    		ps.printf(name,origin,isVegetarian,calories);
    	}

    }
}
