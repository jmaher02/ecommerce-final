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
	
	@FXML private HBox titlePane;
	
	//Nodes
	@FXML private Label title;
	@FXML private Label productName;
	@FXML private Label productPrice;
	@FXML private Label productID;
	@FXML private Label productDetails;
	@FXML private TextField quantity;
	
	@FXML private Button addToCart;
	@FXML private Button backButton;
	@FXML private Button accountButton;
	@FXML private Button cartButton;
	//Button styling
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: #B0C485";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: #EBEFCC;";

    //----FOR TESTING PRODUCTS  ---//
    private int index = 0;
	
	@FXML private ImageView productImage;
	
	@FXML
	public void initialize()
	{
		catalog = new Catalog();
		
		//Update Label Text Colors
		title.setTextFill(Color.web("FFFAEE"));
		productName.setTextFill(Color.web("#689892"));
		productDetails.setTextFill(Color.web("#8FB4A8"));
		productID.setTextFill(Color.web("#8FB4A8"));
				
		//Set default image
		productImage.setImage(new Image("images/defaultProduct.png"));
		
		//Create hover style
		setButtonHover(backButton);
		setButtonHover(accountButton);
		setButtonHover(addToCart);
		setButtonHover(cartButton);
	}
	
	@FXML
	public void backToCategory( ActionEvent event)
	{
		System.out.println("GO BACK");
	}
	
	@FXML void userSignIn( ActionEvent event)
	{
		System.out.println("ACCOUNT SCREEN");
	}
	
	@FXML
	public void updateCart(ActionEvent event)
	{		
		System.out.println("Pressed Button");
	}
	
	@FXML
	public void viewCart( ActionEvent event )
	{
		System.out.println("View Cart");
	}
	
	public void setButtonHover( Button button )
	{
		button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_STYLE));
        button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_STYLE));
	}
	
	public void setProductScreen(Product product)
	{
		//Testing adding product details
		productName.setText(product.getName());
		productPrice.setText(product.printPrice());
		productID.setText(product.getItemNumber() + "");
		productDetails.setText(product.displayCharacteristics());
		index++;
		
	}
	
}
