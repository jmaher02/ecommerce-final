
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showFeaturedPicture() {
		// TODO Auto-generated method stub
		return null;
	}

}
