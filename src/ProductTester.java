/**Practice reading text files and storing ArrayLists
 * 
 * @author Jillian Maher
 *
 */

import java.util.Scanner;

import chapter11_assignments.SagaData;

import java.io.File;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.ArrayList;


public class ProductTester 
{

	public static void main(String[] args) 
	{
		String[] fileName = {"art_supplies.txt", "camping_gear.txt", "candy.txt", "electronics.txt", "toys.txt", "video_games.txt"};

		//=====  CREATE ARRAYLISTS FOR Product Categories ======
		ArrayList<Product> allVideoGames = new ArrayList<Product>();
		ArrayList<Product> allToys = new ArrayList<Product>();
		ArrayList<Product> allCandy = new ArrayList<Product>();
		ArrayList<Product> allArtSupplies = new ArrayList<Product>();
		ArrayList<Product> allElectronics = new ArrayList<Product>();
		ArrayList<Product> allCamping = new ArrayList<Product>();
		
		//=== Practice Video_game file ======
	    try
	    {
	      Scanner file = new Scanner (new File (fileName[5]));

	      while(file.hasNext())
	      {
	        String dataLine = file.nextLine();

	        Scanner parse = new Scanner(dataLine);
	        parse.useDelimiter( "," );

	        try
	        {
		        int itemNum = parse.nextInt();
		        String name = parse.next();
		        double price = parse.nextDouble();
		        String console = parse.next();
		        boolean preOwn = parse.nextBoolean();
	        	allVideoGames.add(new VideoGame(itemNum, name, price, console, preOwn));
	        }
	        catch(InputMismatchException input)
	        {
	          System.out.println("Error in saga record :" + dataLine + ", data ignored.");
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
	    
	    
	    ArrayList<Product>[] allProducts = new ArrayList[6]; 
		allProducts[0] = allVideoGames;
	    allProducts[1] = allToys;
		allProducts[2] = allCandy;
		allProducts[3] = allArtSupplies;
		allProducts[4] = allElectronics;
		allProducts[5] = allCamping;
		
		System.out.println(allProducts.toString());


	}

}
