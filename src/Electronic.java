import java.util.Scanner;

public class Electronic extends Product 
{
	private String dimensions;
	private double weight;
	
	public Electronic(int item, String name, double price, String description)
	{
		super(item, price, name);
		Scanner parse = new Scanner(description);
		parse.useDelimiter(",");
		dimensions = parse.next();
		weight = parse.nextDouble();
	}
	
	@Override
	public String displayCharacteristics() 
	{
		String display = "DIMENSIONS: " + dimensions + "\n";
		display += "WEIGHT: " + weight + " ounces\n";
		
		return display;
	}

	@Override
	public String showFeaturedPicture() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
