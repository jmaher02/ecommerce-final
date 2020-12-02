/** Main file to run the program 
 *  
 *  @author Jillian Maher
 *  
 */

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
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

	@Override
	public void start( Stage stage ) 
	{
		try 
	    {
		  //Some initializing
		  Catalog catalog = new Catalog();
		  CartController.initializeCart();
			
	      URL url = getClass( ).getResource( "fxml_account_page.fxml" );
	      BorderPane root = FXMLLoader.load( url );
	      Scene scene = new Scene( root, WIDTH, HEIGHT );
	      stage.setTitle( "Maher Merchandise" );
	      stage.setScene( scene ); 
	      stage.show( );
	    }
	    catch( Exception e )
	    {
	      e.printStackTrace( ); 
	    }
	}
	
	//Create transitions for hovering on and off a button
	public static void setButtonHover( Button button, int color )
	{
		if(color == 0)
		{
			button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_SAGE));
	        button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_SAGE));
		}
		else if (color == 1)
		{
			button.setStyle(IDLE_BUTTON_TEAL);
			button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_TEAL));
	        button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_TEAL));
		}
		else if (color == 2)
		{
			//show button as disabled
			button.setStyle(HOVERED_BUTTON_TEAL);
			button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_TEAL));
	        button.setOnMouseExited(e -> button.setStyle(HOVERED_BUTTON_TEAL));
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);

	}

}
