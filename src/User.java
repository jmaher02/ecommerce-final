/**
 * The User class
 * Designed to save information about a 
 */


public class User 
{
	private String firstName;
	private String lastName;
	private String email;
	//private String profile_pic;  //Should be image data_type
	private Cart cart;
	
	public User(String first, String last, String email)
	{
		firstName = first;
		lastName = last;
		this.email = email;
		cart = new Cart();
	}
	
	public String showFeaturedPicture()
	{
		return firstName + " " + lastName;
	}
	
	public Cart getUserCart()
	{
		return cart;
	}
	
	public String toString()
	{
		return "\nUser Name: " + firstName + " " + lastName + "\nEmail: " + email + "\n";
	}
	
}
