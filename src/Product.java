/** Product class
 * 
 *  Abstract class to organize the name, itemnumber, and price
 */

import java.text.DecimalFormat;

public abstract class Product 
{
	private int itemNumber;
	private double price;
	private String productName;
	DecimalFormat decFormat;
	
	public Product(int num, double itemPrice, String name)
	{
		itemNumber = num;				
		price = itemPrice;
		productName = name;
		
		//Prepare for formatted output
		String pattern = "##0.00";
		decFormat = new DecimalFormat(pattern);
	}
	
	public int getItemNumber()
	{
		return itemNumber;
	}
	
	public String getName()
	{
		return productName;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public String printPrice()
	{
		return "$" + decFormat.format(price);
	}
	
	public abstract String displayCharacteristics();
	
	public abstract String showFeaturedPicture();
	
	public String toString()
	{
		return "Item " + itemNumber + " " + productName + " $" + decFormat.format(price);
	}
}
