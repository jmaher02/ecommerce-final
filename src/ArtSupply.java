import java.util.Scanner;

public class ArtSupply extends Product 
{
	private String brand;
	
	public ArtSupply(int item, String name, double price, String description)
	{
		super(item, price, name);
		brand = description;
	}

	public ArtSupply(int item, String name, double price, String description, String picture)
	{
		super(item,price,name, picture);
		brand = description;	
	}
	
	@Override
	public String displayCharacteristics() {
		String display = "BRAND: " + brand;
		return display + "\n";
	}
}
