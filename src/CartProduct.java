/** To use with ObservableList and manage each Cart Product
 * 
 * @author computer
 *
 */

import java.text.DecimalFormat;

public class CartProduct 
{
	private Product product;
	private String productName;
	private double productPrice;
	private int productQty;
	DecimalFormat decFormat;

	public CartProduct(Product product, int quantity)
	{
		this.product = product;
		productName = product.getName();
		productPrice = product.getPrice();
		productQty = quantity;
	}
	
	public void setQuantity(int quantity)
	{
		productQty = quantity;
	}

	public String getName()
	{
		return productName;
	}
	
	public double getPrice()
	{
		return productPrice;
	}
	
	public int getQuantity()
	{
		return productQty;
	}
	
	public double getSubtotal()
	{
		return product.getPrice() * productQty;
	}
	
	public Product getProduct()
	{
		return product;
	}
	
	public String printPrice()
	{
		return "$" + decFormat.format( product.getPrice() );
	}
	
	public String printSubtotal()
	{
		return "$" + decFormat.format( getSubtotal() );
	}
	
}
