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
	
	@Override
	public String displayCharacteristics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showFeaturedPicture() {
		// TODO Auto-generated method stub
		return null;
	}

}
