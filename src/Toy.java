

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
	
	public Toy(int item, String name, double price, String description, String picture)
	{
		super(item,price,name, picture);
		Scanner parse = new Scanner(description);
		parse.useDelimiter(",");
		category = parse.next();
		minAge = parse.nextInt();
		maxAge = parse.nextInt();
	}
	
	@Override
	public String displayCharacteristics() 
	{
		String display = "CATEGORY: " + category + "\n";
		
		display += "AGE: ";
		
		if(minAge == -1 && maxAge == -1)
			display += "All ages";
		else {
			if(minAge == -1)
				display += "up ";
			else
				display += minAge;
			
			if(maxAge == -1)
				display += " and up";
			else
				display += " to " + maxAge;
		}
		
		return display + "\n";
	}

}
