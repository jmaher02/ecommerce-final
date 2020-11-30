/** To use with ObservableList and manage each Cart Product
 * 
 * @author computer
 *
 */

import java.text.DecimalFormat;

import javafx.beans.property.*;

public class CartProduct 
{
	private Product product;
	private StringProperty productName;
	private double price;
	private StringProperty productPrice;
	private IntegerProperty productQty;
	DecimalFormat decFormat;

	public CartProduct(Product product, int quantity)
	{
		this.product = product;
		productName = new SimpleStringProperty(product.getName());
		price = product.getPrice();
		productQty = new SimpleIntegerProperty(quantity);

		//Prepare for formatted output
		String pattern = "##0.00";
		decFormat = new DecimalFormat(pattern);
		
		String printPrice = "$" + decFormat.format( getPrice() );
		productPrice = new SimpleStringProperty(printPrice);
	}
	
	public void setQuantity(int quantity)
	{
		productQty.set(quantity);
	}

	public String getProductName()
	{
		return productName.get();
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public int getProductQty()
	{
		return productQty.get();
	}
	
	public double getSubtotal()
	{
		return getPrice() * getProductQty();
	}
	
	public Product getProduct()
	{
		return product;
	}
	
	public String getProductPrice()
	{
		return productPrice.get();
	}
	
	public String printSubtotal()
	{
		return "$" + decFormat.format( getSubtotal() );
	}
	
	public String toString()
	{
		return productName + " QTY: " + productQty + " $" + productPrice;
	}
	
}
