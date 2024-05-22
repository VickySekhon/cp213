package cp213;

import java.awt.Font;
import java.math.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores a HashMap of MenuItem objects and the quantity of each MenuItem
 * ordered. Each MenuItem may appear only once in the HashMap.
 *
 * @author Vicky Sekhon 
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-09-06
 */
public class Order implements Printable {
	
    public static final BigDecimal TAX_RATE = new BigDecimal(0.13);
    
    private static final String itemFormat = "%-20s %s @ $ %.2f = $ %.2f %n";

    // your code here
    
    // create hash map (holds item and quantity)
    HashMap<MenuItem, Integer> items = new HashMap<>();

    /**
     * Increments the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added.
     *
     * @param item     The MenuItem to purchase - the HashMap key.
     * @param quantity The number of the MenuItem to purchase - the HashMap value.
     */
    public void add(final MenuItem item, final int quantity) {
    	
	// your code here
    	
    // check if item already exists in order
    if (items.containsKey(item)) {
    	this.update(item, quantity);
    } else {
    	
    	// quantity is greater than 0
    	if (quantity > 0) {
    		items.put(item, quantity);
    		
    	// quantity is 0 
    	} else {
    		items.remove(item);
    	}    	
    }
    }

    /**
     * Removes an item from an order entirely.  
     *  
     * @param item		The MenuItem to completely remove.
     */
    public void removeAll(final MenuItem item) {
    	
    	items.remove(item);
    }

    /**
     * Calculates the total value of all MenuItems and their quantities in the
     * HashMap.
     *
     * @return the total price for the MenuItems ordered.
     */
    public BigDecimal getSubTotal() {

	// your code here
    	
	BigDecimal subTotal = BigDecimal.ZERO;

	// iterate through the hash map 
	for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
		
		// run "get" to find the item name and based on that, do menuitem.getPrice() 
		BigDecimal itemPrice = entry.getKey().getPrice();
		
		// run "get" to find the item quantity
		int quantity = entry.getValue();
		
		// multiply price x quantity and add it the running total 
		subTotal = subTotal.add(itemPrice.multiply(BigDecimal.valueOf(quantity)));
	}
	return subTotal;
    }

    /**
     * Calculates and returns the total taxes to apply to the subtotal of all
     * MenuItems in the order. Tax rate is TAX_RATE.
     *
     * @return total taxes on all MenuItems
     */
    public BigDecimal getTaxes() {
    	
	// your code here
    BigDecimal tax = BigDecimal.ZERO;
    
    MathContext m = new MathContext(2);
    
    tax = this.getSubTotal().multiply(TAX_RATE).round(m);
    
    return tax;
    }

    /**
     * Calculates and returns the total price of all MenuItems order, including tax.
     *
     * @return total price
     */
    public BigDecimal getTotal() {
    	
	// your code here
    	
    BigDecimal subTot = BigDecimal.ZERO;
    
    subTot = this.getSubTotal().add(this.getTaxes());
    
	return subTot;
    }

    /*
     * Implements the Printable interface print method. Prints lines to a Graphics2D
     * object using the drawString method. Prints the current contents of the Order.
     */
    @Override
    public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex)
	    throws PrinterException {
    	
	int result = PAGE_EXISTS;

	if (pageIndex == 0) {
	    final Graphics2D g2d = (Graphics2D) graphics;
	    g2d.setFont(new Font("MONOSPACED", Font.PLAIN, 12));
	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	    // Now we perform our rendering
	    final String[] lines = this.toString().split("\n");
	    int y = 100;
	    final int inc = 12;

	    for (final String line : lines) {
		g2d.drawString(line, 100, y);
		y += inc;
	    }
	} else {
	    result = NO_SUCH_PAGE;
	}
	return result;
    }

    /**
     * Returns a String version of a receipt for all the MenuItems in the order.
     */
    @Override
    public String toString() {
	// your code here
	String format = "";
	for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
	    MenuItem menuItem = entry.getKey();
	    int quantity = entry.getValue();
	    String name = entry.getKey().getName();
	    BigDecimal price = entry.getKey().getPrice();
	    BigDecimal totalPrice = menuItem.getPrice().multiply(BigDecimal.valueOf(quantity));
	    // return formatted output containing details about customer order
	    format += String.format(itemFormat, name, quantity, price, totalPrice);
	}
	// create payment section
	format += String.format("Subtotal:                        $%6.2f %n", this.getSubTotal());
	format += String.format("Taxes:                           $%6.2f %n", this.getTaxes());
	format += String.format("Total:                           $%6.2f %n", this.getTotal());
	return format;
    }

    /**
     * Replaces the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added. If quantity is 0
     * or negative, the MenuItem is removed from the Order.
     *
     * @param item     The MenuItem to update
     * @param quantity The quantity to apply to item
     */
    public void update(final MenuItem item, final int quantity) {
	// your code here
    if (quantity > 0) {
    	int currentItemQuantity = items.get(item);
    	// update quantity 
    	items.put(item, currentItemQuantity+quantity);
    } else {
    	// remove item from hashmap
    	items.remove(item);
    }
    }
}