/** Controller class to manage scenes and user interaction
 * 
 * @author Jillian Maher
 */

import java.net.URL;
import javafx.animation.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ECommerceController 
{
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	//Models
	private Catalog catalog;
	
	//Nodes
	@FXML private Label productName;
	@FXML private Label productPrice;
	@FXML private Label productID;
	@FXML private Label productDetails;
	@FXML private TextField quantity;
	@FXML private Button addToCart;
	
	//@FXML private ImageView productImage;
	
	@FXML
	public void initialize()
	{
		catalog = new Catalog();
		
		//Update Label Text Colors
		productName.setTextFill(Color.web("#689892"));
		productDetails.setTextFill(Color.web("#8FB4A8"));

		
	}
	
	
	
}
