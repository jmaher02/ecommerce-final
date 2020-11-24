
public class ArtSupply extends Product 
{
	private String brand;
	
	public ArtSupply(int item, String name, double price, String description)
	{
		super(item, price, name);
		brand = description;
	}
	
	@Override
	public String displayCharacteristics() {
		String display = "BRAND: " + brand;
		return display + "\n";
	}

	@Override
	public String showFeaturedPicture() {
		// TODO Auto-generated method stub
		return null;
	}

}
