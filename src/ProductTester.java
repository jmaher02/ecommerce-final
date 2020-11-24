/**Practice reading text files and storing ArrayLists
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


public class ProductTester 
{
	public static void printCategory(ArrayList<Product> list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			System.out.println(list.get(i));
		}
	}
	
	public static void parseProduct(String fileName, ArrayList<Product> list, int category)
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

	public static void main(String[] args) 
	{
		String[] files = {"art_supplies.txt", "camping_gear.txt", "candy.txt", "electronics.txt", "toys.txt", "video_games.txt"};

		//=====  CREATE ARRAYLISTS FOR Product Categories ======
		ArrayList<Product> allVideoGames = new ArrayList<Product>();
		ArrayList<Product> allToys = new ArrayList<Product>();
		ArrayList<Product> allCandy = new ArrayList<Product>();
		ArrayList<Product> allArtSupplies = new ArrayList<Product>();
		ArrayList<Product> allElectronics = new ArrayList<Product>();
		ArrayList<Product> allCamping = new ArrayList<Product>();
		
		parseProduct(files[0], allArtSupplies, 0);
		parseProduct(files[1], allCamping, 1);
		parseProduct(files[2], allCandy, 2);
	    parseProduct(files[3], allElectronics, 3);
	    parseProduct(files[4], allToys, 4);
	    parseProduct(files[5], allVideoGames, 5);
	    
	    //Store all items by category in one array
	    ArrayList<Product>[] allProducts = new ArrayList[6]; 
		allProducts[0] = allArtSupplies;
	    allProducts[1] = allCamping;
		allProducts[2] = allCandy;
		allProducts[3] = allElectronics;
		allProducts[4] = allToys;
		allProducts[5] = allVideoGames;
		
		//Print the VideoGames Category
		System.out.println("VIDEO GAMES");
		System.out.println("===========");
		printCategory(allProducts[5]);
		System.out.println("===========");
		
		//Print the Art Supplies Category
		System.out.println("ART SUPPLIES");
		System.out.println("===========");
		printCategory(allProducts[0]);
		System.out.println("===========");
		
		//Print the Camping Category
		System.out.println("CAMPING GEAR");
		System.out.println("===========");
		printCategory(allProducts[1]);
		System.out.println("===========");
		
		//Print the Candy Category
		System.out.println("CANDY");
		System.out.println("===========");
		printCategory(allProducts[2]);
		System.out.println("===========");
		
		//Print the Electronics Category
		System.out.println("ELECTRONICS");
		System.out.println("===========");
		printCategory(allProducts[3]);
		System.out.println("===========");
		
		//Print the Toys Category
		System.out.println("TOYS");
		System.out.println("===========");
		printCategory(allProducts[4]);
		System.out.println("===========");
		
		
		
	}

}
