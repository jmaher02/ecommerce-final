import java.util.Scanner;

public class Candy extends Product 
{
	private double weight;
	
	public Candy(int item, String name, double price, String description)
	{
		super(item, price, name);
		Scanner parse = new Scanner(description);
		parse.useDelimiter(",");
		weight = parse.nextDouble();
	}

	public Candy(int item, String name, double price, String description, String picture)
	{
		super(item,price,name, picture);
		Scanner parse = new Scanner(description);
		parse.useDelimiter(",");
		weight = parse.nextDouble();
	}
	
	@Override
	public String displayCharacteristics() 
	{
		String display = "WEIGHT: " + weight + " ounces\n";
		
		return display;
	}

}
