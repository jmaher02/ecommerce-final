/**
 * The User class
 * Designed to save information about a User include
 *  - name
 *  - email address
 *  - password
 *  - saved cart
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable
{
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	//private String profile_pic;  //Should be image data_type
	private ArrayList<CartProduct> savedCart;
	private String picFile;
	
	public User(String uName, String first, String last, String email, String pword)
	{
		userName = uName;
		firstName = first;
		lastName = last;
		this.email = email;
		password = pword;
		savedCart = new ArrayList<CartProduct>();
		picFile = "images/userIconDefault.png";
	}
	
	public void setPicture( String picture )
	{
		picFile = picture;
	}
	
	public void setPassword( String pword )
	{
		password = pword;
	}
	
	public void setEmail( String newEmail)
	{
		email = newEmail;
	}
	
	public void setCart( ObservableList<CartProduct> cart)
	{
		savedCart = new ArrayList<CartProduct>();
		for(CartProduct product : cart)
		{
			savedCart.add(product);
		}
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getName()
	{
		return firstName + " " + lastName;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public boolean correctPassword( String toCheck )
	{
		return password.equals(toCheck);
	}
	
	public boolean setPassword( String toCheck, String newPassword)
	{
		if(correctPassword(toCheck))
		{
			password = newPassword;
			return true;
		}
		else
			return false;
	}
	
	public String showFeaturedPicture()
	{
		return picFile;
	}
	
	public ObservableList<CartProduct> getUserCart()
	{
		ObservableList<CartProduct> cart = FXCollections.observableArrayList();
		
		for(CartProduct product : savedCart)
		{
			cart.add(product);
		}
		
		return cart;
	}
	
	//A password is checked for high security before allowing a user to assign the password.
	//  A password must contain 
	//  - one uppercase letter
	//  - one lowercase letter
	//  - one number
	//  - no additional characters
	public static boolean isValidPassword(String password)
	{
		String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowercase = uppercase.toLowerCase();
		String numeric = "0123456789";
		
		return password.length() >= 8 && onlyValidChars(password, uppercase+lowercase+numeric ) &&
				charExists(password,uppercase) && charExists(password,lowercase) && charExists(password, numeric);
	}
	
	private static boolean charExists(String toCheck, String necessaryChars)
	{
		for(char letter: necessaryChars.toCharArray())
		{
			if(toCheck.indexOf(letter) >= 0)
				return true;
		}
		
		return false;
	}
	
	private static boolean onlyValidChars(String toCheck, String necessaryChars)
	{
		for(char letter: toCheck.toCharArray())
		{
			if(necessaryChars.indexOf(letter) == -1)
				return false;
		}		
		return true;
	}
	
	public String toString()
	{
		return "\nUser Name: " + firstName + " " + lastName + "\nEmail: " + email + "\n";
	}
	
}
