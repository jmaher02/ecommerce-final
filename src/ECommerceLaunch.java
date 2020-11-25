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
import javafx.stage.Stage;

public class ECommerceLaunch extends Application 
{

	@Override
	public void start( Stage stage ) 
	{
		try 
	    {
	      URL url = getClass( ).getResource( "fxml_product_page.fxml" );
	      BorderPane root = FXMLLoader.load( url );
	      Scene scene = new Scene( root, 800, 600 );
	      stage.setTitle( "Maher Merchandise" );
	      stage.setScene( scene ); 
	      stage.show( );
	    }
	    catch( Exception e )
	    {
	      e.printStackTrace( ); 
	    }
	}
	
	
	
	public static void main(String[] args) 
	{
		launch(args);

	}

}
