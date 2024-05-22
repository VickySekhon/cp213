package cp213;

import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

// Import Printer API 
import java.awt.print.*;

/**
 * The GUI for the Order class.
 *
 * @author Vicky Sekhon 
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-09-06
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {
	
    // Attributes
    private Menu menu = null; // Menu attached to panel.
    private final Order order = new Order(); // Order to be printed by panel.
    
    // UI Elements 
    // GUI Widgets
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("Subtotal:");
    private final JLabel taxLabel = new JLabel("Tax:");
    private final JLabel totalLabel = new JLabel("Total:");
    // Header Labels
    private final JLabel itemLabel = new JLabel("Item");
    private final JLabel priceLabel = new JLabel("Price");
    private final JLabel quantityLabel = new JLabel("Quantity");
    // Temporary Price Labels 
    private final JLabel subTotal = new JLabel("0");
    private final JLabel tax = new JLabel("0");
    private final JLabel total = new JLabel("0");	
    // Spacing Labels 
    private final JLabel space1 = new JLabel("");
    private final JLabel space2 = new JLabel("");
    private final JLabel space3 = new JLabel("");
    private final JLabel space4 = new JLabel("");
    private final JLabel space5 = new JLabel("");
    // TextFields for food names. 
    private JLabel nameLabels[] = null;
    // TextFields for food prices.
    private JLabel priceLabels[] = null;
    // TextFields for menu item quantities.
    private JTextField quantityFields[] = null;

    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     */
    public OrderPanel(final Menu menu) {
    this.menu = menu;
    this.nameLabels = new JLabel[this.menu.size()];
    this.priceLabels = new JLabel[this.menu.size()];
    this.quantityFields = new JTextField[this.menu.size()];
    this.layoutView();
    this.registerListeners();
    }

    /**
     * Implements an ActionListener for the 'Print' button. Prints the current
     * contents of the Order to a system printer or PDF.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {
	    // your code here
		
		// create printer job
		PrinterJob job = PrinterJob.getPrinterJob();
		
		// order is to be printed
		job.setPrintable(order);
		
		// call the Order class print() abstract method to print contents
		try {
			job.print();
		} catch (PrinterException e1) {
			System.out.println("There was an error printing the receipt. Please try again.");
		}
	}
    }

    /**
     * Implements a FocusListener on a JTextField. Accepts only positive integers,
     * all other values are reset to 0. Adds a new MenuItem to the Order with the
     * new quantity, updates an existing MenuItem in the Order with the new
     * quantity, or removes the MenuItem from the Order if the resulting quantity is
     * 0.
     */
    private class QuantityListener implements FocusListener {
	private MenuItem item = null;
	
	QuantityListener(final MenuItem item) {
	    this.item = item;
	}
	// your code here

	/**
	 * Handles the case when user clicks off of a JTextField.
	 */
	public void focusLost(FocusEvent e) {
		
		JTextField source = (JTextField) e.getSource();
		
		// quantity entered into the JTextField
		String txt = source.getText();
		
		int quantity; 
		
		try {  
			
			// clear item from existing order  
			order.removeAll(this.item);
			
			// ensures quantity is numeric 
			quantity = Integer.parseInt(txt);
			
			// valid quantity 
			if (quantity > 0) {
				
				// add the item into the order with the quantity entered 
				order.add(this.item, quantity);		
				
			// invalid quantity 
			} else {
				order.removeAll(item);
				source.setText("0");
			}
			
		// quantity entered is a string
		} catch(NumberFormatException e1){  
			source.setText("0");
		}  
	}
	
	/**
	 * Handles the case for when the user clicks a JTextField.
	 */
	public void focusGained(FocusEvent e) {
		
	// calculate total, subtotal, and tax
	subTotal.setText("$" + String.valueOf(order.getSubTotal()));
	
	tax.setText("$" + String.valueOf(order.getTaxes()));
	
	total.setText("$" + String.valueOf(order.getTotal()));
	}
    }

    /**
     * Layout the panel.
     */
    private void layoutView() {
	// your code here
    	
	setLayout(new GridLayout(menu.size() + 4, 2));
	
	// Top Section: Item, Price, and Quantity
	add(itemLabel);
	add(priceLabel);
	add(quantityLabel);

	for (int i = 1; i < menu.size(); i++) {
		MenuItem food = menu.getItem(i);
	    add(nameLabels[i] = new JLabel(food.getName()));
	    add(priceLabels[i] = new JLabel("$" + String.valueOf(food.getPrice())));
	    QuantityListener listener = new QuantityListener(food);
	    add(quantityFields[i] = new JTextField(menu.size()));
	    quantityFields[i].addFocusListener(listener);
	}

	// Bottom Section: Subtotal, Tax, Total, and Print Button
	add(subtotalLabel);
	add(space1);
	add(subTotal);
	add(taxLabel);
	add(space2);
	add(tax);
	add(totalLabel);
	add(space3);
	add(total);
	add(space4);
	add(space5);
	add(printButton);
    }

    /**
     * Register the widget listeners.
     */
    private void registerListeners() {
    	
	// Register the PrinterListener with the print button.
	this.printButton.addActionListener(new PrintListener());
    }
	

}