/** To use with ObservableList and manage each Cart Product
 * 
 * @author computer
 *
 */

import java.text.DecimalFormat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.beans.property.*;

public class CartProduct implements Serializable
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
	
	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.writeObject(getProduct());
		out.writeObject(getProductQty());
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		product = (Product)in.readObject();
		productQty = new SimpleIntegerProperty((int) in.readObject());
		
		productName = new SimpleStringProperty(product.getName());
		price = product.getPrice();

		//Prepare for formatted output
		String pattern = "##0.00";
		decFormat = new DecimalFormat(pattern);
		
		String printPrice = "$" + decFormat.format( getPrice() );
		productPrice = new SimpleStringProperty(printPrice);
	}
	
	public String toString()
	{
		return productName + " QTY: " + productQty + " $" + productPrice;
	}
	
}
