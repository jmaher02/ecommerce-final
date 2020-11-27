/** Catalog will house all the Products for all the Categories
 * 
 * Products are stored in an array of ArrayLists
 * The categories are: 
 *   - 0 Art Supplies
 *   - 1 Camping Gear
 *   - 2 Candy
 *   - 3 Electronics
 *   - 4 Toys
 *   - 5 Video Games
 * 
 * @author Jillian Maher
 *
 */

import java.util.Scanner;

import java.io.File;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class Catalog 
{
	private ArrayList<Product>[] allProducts;
	private String[] categoryTitles= {"Art Supplies", "Camping Gear", "Candy", "Electronics", "Toys", "Video Games"};
	private String[] files = {"art_supplies.txt", "camping_gear.txt", "candy.txt", "electronics.txt", "toys.txt", "video_games.txt"};
	private String[] images = {"art.png", "camping.png","candy.png", "wire.png","toy.png","controller.png"};
	
	public Catalog()
	{
		//Create ArrayLists for each Category		
		ArrayList<Product> allVideoGames = new ArrayList<Product>();
		ArrayList<Product> allToys = new ArrayList<Product>();
		ArrayList<Product> allCandy = new ArrayList<Product>();
		ArrayList<Product> allArtSupplies = new ArrayList<Product>();
		ArrayList<Product> allElectronics = new ArrayList<Product>();
		ArrayList<Product> allCamping = new ArrayList<Product>();
		
		//Gather data from each of the text files
		parseProduct(files[0], allArtSupplies, 0);
		parseProduct(files[1], allCamping, 1);
		parseProduct(files[2], allCandy, 2);
	    parseProduct(files[3], allElectronics, 3);
	    parseProduct(files[4], allToys, 4);
	    parseProduct(files[5], allVideoGames, 5);
		
		allProducts = new ArrayList[6];
		
		allProducts[0] = allArtSupplies;
	    allProducts[1] = allCamping;
		allProducts[2] = allCandy;
		allProducts[3] = allElectronics;
		allProducts[4] = allToys;
		allProducts[5] = allVideoGames;
	}
	
	// Method for gathering data from the specific file to store in the correct ArrayList type
	private void parseProduct(String fileName, ArrayList<Product> list, int category)
	{

	   try
	   {
		  Scanner file = new Scanner (new File (fileName));

	      while(file.hasNext())
	      {
	        String dataLine = file.nextLine();

	        Scanner parse = new Scanner(dataLine);
	        parse.useDelimiter( ";" );

	        try
	        {
		        int itemNum = parse.nextInt();
		        String name = parse.next();
		        double price = parse.nextDouble();
		        String description = parse.next();
		        switch(category) {
		        case 0: list.add(new ArtSupply(itemNum, name, price, description)); break; 
		        case 1: list.add(new CampingGear(itemNum, name, price, description)); break; 
		        case 2: list.add(new Candy(itemNum, name, price, description)); break; 
		        case 3: list.add(new Electronic(itemNum, name, price, description)); break; 
		        case 4: list.add(new Toy(itemNum, name, price, description)); break; 
		        case 5: list.add(new VideoGame(itemNum, name, price, description)); break; 
		        }
	        	
	        }
	        catch(InputMismatchException input)
	        {
	          System.out.println("Error in record :" + dataLine + ", data ignored.");
	        }
	      }
		   	  
		}     
		catch (FileNotFoundException fnf) 
		{
		  System.out.println("The file, " + fileName + ", could not be found."); 
		}
		catch (NoSuchElementException elem)
		{
		  System.out.println("End of file reached before end of parsing");
		}    
	}
	
	//Print all items in the specified category
	public void printCategory(int category)
	{

		System.out.println("===========");
		System.out.println(categoryTitles[category]);
		System.out.println("===========");
		
		for(int i = 0; i < allProducts[category].size(); i++)
		{
			System.out.println(allProducts[category].get(i));
			System.out.println(allProducts[category].get(i).displayCharacteristics());
		}
	}
	
	//Access all the different Titles for all the Categories
	public String[] getCategoryTitles()
	{
		return categoryTitles;
	}
	
	//Get the index for the given Category title. 
	// If the category does not exist, return -1
	public int getCategory(String title)
	{
		for(int i = 0; i < categoryTitles.length; i++)
		{
			if(categoryTitles[i].equals(title))
				return i;
		}
		return -1;
	}
	
	//Search the catalog for a specific Product using the itemNumber
	public Product findProduct(int category, int itemNumber)
	{
		for(Product product: allProducts[category])
		{
			if(product.getItemNumber() == itemNumber)
				return product;
		}
		
		return null;
	}
	
	//Return all the products in a given category
	public ArrayList<Product> getCategoryProducts( int category )
	{
		return allProducts[category];
	}
	
	//Return the image name for the given category
	public String getImageTitle(int category)
	{
		return images[category];
	}
}
