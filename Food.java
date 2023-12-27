package cp213;

import java.io.PrintStream;

/**
 * Food class definition.
 *
 * @author Gbone Atsagbede; 203359820; atsa9820@mylaurier.ca
 * @version 2021-09-24
 */
public class Food implements Comparable<Food> {

    // Constants
    public static final String ORIGINS[] = { "Canadian", "Chinese", "Indian", "Ethiopian", "Mexican", "Greek",
	    "Japanese", "Italian", "Moroccan", "Scottish", "Columbian", "English" };

    /**
     * Creates a string of food origins in the format:
     *
     * <pre>
Origins
 0 Canadian
 1 Chinese
...
11 English
     * </pre>
     *
     * @return A formatted numbered string of valid food origins.
     */
    public static String originsMenu() {
    	System.out.print("Origins");
    	String food_origins = ""; 
    	for(int i=0; i < Food.ORIGINS.length; i++){
    		if (i<=9) {
    			food_origins +="\n"+" "+i+" "+Food.ORIGINS[i] ;
    		}
    		else {
    		food_origins += "\n"+i+" "+Food.ORIGINS[i];
    		}
    	}
    		
    return food_origins;
    	        
    }
    
    // Attributes
    private String name = null;
    private int origin = 0;
    private boolean isVegetarian = false;
    private int calories = 0;

    /**
     * Food constructor.
     *
     * @param name         food name
     * @param origin       food origin code
     * @param isVegetarian whether food is vegetarian
     * @param calories     caloric content of food
     */
    public Food(final String name, final int origin, final boolean isVegetarian, final int calories) {
    	super();
    	this.name = name ; 
    	this.origin = origin ; 
    	this.isVegetarian = isVegetarian; 
    	this.calories = calories; 
    	
	// your code here

	return;
    }

    /*
     * (non-Javadoc) Compares this food against another food.
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    /**
     * Foods are compared by name, then by origin if the names match.
     * Must ignore case.
     */
    @Override
    public int compareTo(final Food target) {
    	
    	if (target==null)
    		return 0;
    	Food other = (Food)target; 
    	if (name.charAt(0) > other.name.charAt(0)) { 
    			return 1; 
    	}
    	else if (name.charAt(0) < other.name.charAt(0)) { 
    		return -1; 
    	} else if (name.toLowerCase().equals(other.name.toLowerCase())) {
    		if (origin > other.origin)
    			return 1; 
    		else if (origin == other.origin)
    			return 0; 
    		else
    			return -1 ; 
    	}
    	
    	return 0;
    }
    

    /**
     * Getter for calories attribute.
     *
     * @return calories
     */
    public int getCalories() {
    	return this.calories;
    }

	// your code here

    

    /**
     * Getter for name attribute.
     *
     * @return name
     */
    public String getName() {
    	return this.name; 
	// your code here


    }

    /**
     * Getter for origin attribute.
     *
     * @return origin
     */
    public int getOrigin() {
    	return this.origin; 
	// your code here

    }

    /**
     * Getter for string version of origin attribute.
     *
     * @return string version of origin
     */
    public String getOriginString() {
    	
	// your code here

	return ORIGINS[this.origin];
    }

    /**
     * Getter for isVegetarian attribute.
     *
     * @return isVegetarian
     */
    public boolean isVegetarian() {

	// your code here

	return this.isVegetarian;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object//toString() Creates a formatted string of food data.
     */
    /**
     * Returns a string version of a Food object in the form:
<pre>
Name:       name
Origin:     origin string
Vegetarian: true/false
Calories:   calories
</pre>
     */
    @Override
    public String toString() {
    	String food_object = "";
    	food_object += String.format("Name: %-12s%n",this.name); 
    	food_object+=String.format("Origin: %-12s%n",ORIGINS[this.origin]); 
    	food_object+=String.format("Vegetarian: %-12s%n",this.isVegetarian); 
    	food_object+=String.format("Calories: %-12s%n", this.calories); 
    	
	return food_object;
    }

    /**
     * Writes a single line of food data to an open PrintStream. The contents of
     * food are written as a string in the format name|origin|isVegetarian|calories to ps.
     *
     * @param ps The PrintStream to write to.
     */
    public void write(final PrintStream ps) {
//    	PrintStream ps = new PrintStream(); 
    	String name = getName();
    	int origin = getOrigin(); 
    	int calories = getCalories();
    	boolean isVegetarian = isVegetarian();
    	ps.printf(name,"|",origin,"|",isVegetarian,"|",calories);  

	return;
    }

}
