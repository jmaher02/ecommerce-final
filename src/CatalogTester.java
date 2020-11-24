// Verify that all products are stored in the Catalog

public class CatalogTester 
{

	public static void main(String[] args) 
	{
		Catalog catalog = new Catalog();
		
		for(int i = 0; i < 6; i++)
			catalog.printCategory(i);

	}

}
