/** Main file to run the program 
 *  
 *  @author Jillian Maher
 *  
 */

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ECommerceLaunch extends Application 
{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	//Set color scheme
	public static final String ORCHID = "#BA55D3";
	public static final String OFF_WHITE = "#FFFAEE";
	public static final String TEAL_DARK = "#689892";
	public static final String TEAL_LIGHT = "#8FB4A8";
	public static final String SAGE_DARK = "#B0C485";
	public static final String SAGE_LIGHT = "#EBEFCC";
	public static final String MAROON = "#AA0037";
	
	public static final Color MAIN_DARK = Color.web(ORCHID);
	public static final Color MAIN_LIGHT = Color.web(OFF_WHITE);
	public static final Color ACCENT_1_DARK= Color.web(TEAL_DARK);
	public static final Color ACCENT_1_LIGHT = Color.web(TEAL_LIGHT);
	public static final Color ACCENT_2_DARK = Color.web(SAGE_DARK);
	public static final Color ACCENT_2_LIGHT = Color.web(SAGE_LIGHT);
	public static final Color WARNING = Color.web(MAROON);
	
	//Button styling Sage Accent 2
    public static final String IDLE_BUTTON_SAGE = "-fx-background-color: "+ SAGE_DARK;
    public static final String HOVERED_BUTTON_SAGE = "-fx-background-color: " + SAGE_LIGHT;
    //Button styling Teal Accent 1
    public static final String IDLE_BUTTON_TEAL = "-fx-background-color: " + TEAL_DARK;
    public static final String HOVERED_BUTTON_TEAL = "-fx-background-color: " + TEAL_LIGHT;
    
    //List of all Users
    private static ArrayList<User> allUsers = new ArrayList<User>( );
    
    //Create the catalog
    public static Catalog catalog = new Catalog();

	@Override
	public void start( Stage stage ) 
	{
		try 
	    {
		  CartController.initializeCart();
		  
		  setUserAccounts();
			
	      URL url = getClass( ).getResource( "fxml_homepage.fxml" );
	      FXMLLoader loader = new FXMLLoader();
	      loader.setLocation(url);
	      Parent homeScreen = loader.load( );
	      Scene scene = new Scene( homeScreen, WIDTH, HEIGHT );
	      	      
	      stage.setTitle( "Maher Merchandise" );
	      stage.setScene( scene ); 
	      stage.show( );
	    }
		catch(IOException e)
		{
			e.printStackTrace();
		}
	    catch( Exception e )
	    {
	      e.printStackTrace( ); 
	    }
	}
	
	//Read in user Accounts
	private static void setUserAccounts( )
	{
		try
	    {
	      FileInputStream fis = new FileInputStream( "userObjects" );
	      ObjectInputStream ois = new ObjectInputStream( fis );

	      try
	      {
	        while ( true )
	        {
	          // read object, type cast returned object to FlightRecord2
	          User temp = ( User ) ois.readObject( );

	          // add the User object read to allUsers
	          allUsers.add( temp );
	        }
	      } // end inner try block

	      catch ( EOFException eofe )
	      {
	        System.out.println( "End of the file reached" );
	      }

	      catch ( ClassNotFoundException cnfe )
	      {
	        System.out.println( cnfe.getMessage( ) );
	      }

	      finally
	      {
	        System.out.println( "Closing file" );
	        System.out.println( "" + allUsers.size() + " users.");
	        ois.close( );
	      }
	    } // end outer try block

	    catch ( FileNotFoundException fnfe )
	    {
	      System.out.println( "Unable to find objects" );
	    }

	    catch ( IOException ioe )
	    {
	      ioe.printStackTrace( );
	    }
	}
	
	//Save User Account to file
	public static void saveUserAccount( User acct )
	{
		allUsers.add(acct);
		try
		   {
		    FileOutputStream fos = new FileOutputStream
		                                  ( "userObjects", false );
		             // false means we will write to objects

		    ObjectOutputStream oos = new ObjectOutputStream( fos );

		    // write the objects to the file
		    for(User user: allUsers)
		    {
			    oos.writeObject( user );
		    }
		    System.out.println( acct );

		    // release resources associated with the objects file
		    oos.close( );
		   }

		   catch ( FileNotFoundException fnfe )
		   {
		     System.out.println( "Unable to write to objects" );
		   }

		   catch ( IOException ioe )
		   {
		      ioe.printStackTrace( );
		   }
	}
	
	//Update user's saved Cart
	public static void updateUserCart( User acct )
	{
		//Locate old user account and replace cart (maybe redundant due to pass by reference ??
		for(User user:allUsers)
		{
			if(user.getUserName().equals(acct.getUserName()))
			{
				user.setCart(acct.getUserCart());
			}
		}
			
		try
		   {
		    FileOutputStream fos = new FileOutputStream
		                                  ( "userObjects", false );
		             // false means we will write to objects

		    ObjectOutputStream oos = new ObjectOutputStream( fos );

		    // write the objects to the file
		    for(User user: allUsers)
		    {
			    oos.writeObject( user );
		    }
		    System.out.println( acct );

		    // release resources associated with the objects file
		    oos.close( );
		   }

		   catch ( FileNotFoundException fnfe )
		   {
		     System.out.println( "Unable to write to objects" );
		   }

		   catch ( IOException ioe )
		   {
		      ioe.printStackTrace( );
		   }
	}
	
	//Access the users in other classes
	public static ArrayList<User> getAllUsers()
	{
		return (ArrayList<User>)allUsers.clone();
	}
	
	//Create transitions for hovering on and off a button
	public static void setButtonHover( Button button, int color )
	{
		if(color == 0)  //Title Bar Active Button
		{
			button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_SAGE));
	        button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_SAGE));
		}
		else if (color == 1)  //Action Button Active
		{
			button.setStyle(IDLE_BUTTON_TEAL);
			button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_TEAL));
	        button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_TEAL));
		}
		else if (color == 2)   //Action Button Disabled
		{
			//show button as disabled
			button.setStyle(HOVERED_BUTTON_TEAL);
			button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_TEAL));
	        button.setOnMouseExited(e -> button.setStyle(HOVERED_BUTTON_TEAL));
		}
		else if (color == 3)   //Title Bar Button Disabled
		{
			//show button as disabled
			button.setStyle(HOVERED_BUTTON_SAGE);
			button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_SAGE));
	        button.setOnMouseExited(e -> button.setStyle(HOVERED_BUTTON_SAGE));
		}
		else if ( color == 4)    //Transparent Button, Text Button
		{
			button.setTextFill(ACCENT_1_DARK);
			button.setOnMouseEntered(e -> button.setTextFill(ACCENT_1_LIGHT));
	        button.setOnMouseExited(e -> button.setTextFill(ACCENT_1_DARK));
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);

	}

}
