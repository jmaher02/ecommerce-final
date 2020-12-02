/** A Video Game Product for sale
 * 
 * @author Jillian Maher
 * 
 * Beyond Product data including item number, name, and price. 
 * a Video Game object will store
 *   - console system
 *   - boolean true for pre-owned games
 * 
 */

import java.util.Scanner;

public class VideoGame extends Product 
{
	private String console;
	private boolean preOwned;
	
	public VideoGame(int item, String name, double price, String description)
	{
		super(item,price,name);
		Scanner parse = new Scanner(description);
		parse.useDelimiter(",");
		console = parse.next();
		preOwned = parse.nextBoolean();		
	}
	
	public VideoGame(int item, String name, double price, String description, String picture)
	{
		super(item,price,name, picture);
		Scanner parse = new Scanner(description);
		parse.useDelimiter(",");
		console = parse.next();
		preOwned = parse.nextBoolean();		
	}

	@Override
	public String displayCharacteristics() {
		String display = "CONSOLE: " + console + "\n";
		if(preOwned)
			display += "PRE-OWNED\n";
		else
			display += "NEW\n";
		return display;
	}
}
