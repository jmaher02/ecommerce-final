

import java.util.Scanner;

public class Toy extends Product 
{
	private String category;
	private int minAge;
	private int maxAge;
	
	public Toy(int item, String name, double price, String description)
	{
		super(item, price, name);
		Scanner parse = new Scanner(description);
		parse.useDelimiter(",");
		category = parse.next();
		minAge = parse.nextInt();
		maxAge = parse.nextInt();
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
