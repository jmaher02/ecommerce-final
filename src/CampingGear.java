import java.util.Scanner;

public class CampingGear extends Product 
{
	private double weight;
	private String dimensions;

	public CampingGear(int item, String name, double price, String description)
	{
		super(item, price, name);
		Scanner parse = new Scanner(description);
		parse.useDelimiter(",");
		weight = parse.nextDouble();
		dimensions = parse.next();
	}

	public CampingGear(int item, String name, double price, String description, String picture)
	{
		super(item,price,name, picture);
		Scanner parse = new Scanner(description);
		parse.useDelimiter(",");
		weight = parse.nextDouble();
		dimensions = parse.next();	
	}
	
	@Override
	public String displayCharacteristics() 
	{
		String display = "DIMENSIONS: " + dimensions + "\n";
		display += "WEIGHT: " + weight + " ounces\n";
		
		return display;
	}

}
