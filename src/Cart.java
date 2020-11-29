/** 
 * The Cart class
 * Designed to store information about the products the 
 *   User wishes to purchase
 */


import java.util.ArrayList;
import java.text.DecimalFormat;

public class Cart 
{
	private ArrayList<Product> products;
	
	public Cart()
	{
		products = new ArrayList<Product>();
	}
	
	public void addToCart( Product product )
	{
		products.add(product);
	}
	
	public void emptyCart()
	{
		products.clear();
	}
	
	public ArrayList<Product> getProducts()
	{
		return products;
	}
	
	public double calculateTotal()
	{
		double total = 0.0;
		
		for(Product product : products)
		{
			total += product.getPrice();
		}
		
		return total;
	}
	
	public String displayCart()
	{
		String output = "ITEM\tPRICE\tDESCRIPTION\n";
		
		for(Product product: products)
		{
			output += product.getItemNumber() + "\t$" + product.printPrice()  + "\t";
			output += product.getName() + "\n";
		}
		
		//Prepare for formatted output
		String pattern = "##0.00";
		DecimalFormat decFormat = new DecimalFormat(pattern);
		
		output += "\nTOTAL: $" + decFormat.format(calculateTotal()) + "\n";
		return output;
	}
	
	public String toString()
	{
		return products.toString();
	}
}
