// Verify that all products are stored in the Catalog

public class CatalogTester 
{

	public static void main(String[] args) 
	{
		Catalog catalog = new Catalog();
		
		for(int i = 0; i < 6; i++)
			catalog.printCategory(i);
		
		//Checking that passwords are valid
		String password = "12341234"; //not valid
		System.out.println( User.isValidPassword(password));

		password = "HelloWorld5"; //valid
		System.out.println( User.isValidPassword(password));
		
		password = "FalseTrue3"; //valid
		System.out.println( User.isValidPassword(password));
		
		password = "ThisIsALongPassword"; //not valid
		System.out.println( User.isValidPassword(password));
		
		password = "This Is A Long Password 123"; //not valid
		System.out.println( User.isValidPassword(password));
		
		password = "Hello&World5"; //not valid
		System.out.println( User.isValidPassword(password));
	}

}
