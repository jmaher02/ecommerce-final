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
