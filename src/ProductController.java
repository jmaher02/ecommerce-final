/** Controller class to manage the Product page
 * 
 * @author Jillian Maher
 */

import java.io.IOException;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.Stage;

public class ProductController 
{
	//Models
	private Catalog catalog;
	private Product product;
	private int category;
	
	//Nodes
	@FXML private Label title;
	@FXML private Label productName;
	@FXML private Label productPrice;
	@FXML private Label productID;
	@FXML private Label productDetails;
	@FXML private TextField quantityField;
	@FXML private Label warning;
	
	@FXML private Button addToCart;
	@FXML private Button backButton;
	@FXML private Button accountButton;
	@FXML private Button cartButton;
	
    @FXML private ImageView productImage;
	
	@FXML
	public void initialize()
	{
		//catalog = new Catalog();
		
		//Update Label Text Colors
		title.setTextFill(ECommerceLaunch.MAIN_LIGHT);
		productName.setTextFill(ECommerceLaunch.ACCENT_1_DARK);
		productDetails.setTextFill(ECommerceLaunch.ACCENT_1_LIGHT);
		productID.setTextFill(ECommerceLaunch.ACCENT_1_LIGHT);
				
		//Create hover style
		ECommerceLaunch.setButtonHover(backButton, 0);
		ECommerceLaunch.setButtonHover(accountButton, 0);
		ECommerceLaunch.setButtonHover(addToCart, 1);
		ECommerceLaunch.setButtonHover(cartButton, 0);
	}
	
	//Initialize the product to be displayed
	public void setProduct(Catalog saveCatalog, Product initProduct, int saveCategory)
	{
		catalog = saveCatalog;
		product = initProduct;
		category = saveCategory;
		
		productName.setText(product.getName());
		productPrice.setText(product.printPrice());
		productID.setText("Item #" + product.getItemNumber());
		productDetails.setText(product.displayCharacteristics());
		
		//Set default image
		productImage.setImage(new Image("images/defaultProduct.png"));
	}
	
	@FXML
	public void backToAllProducts( ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("fxml_select_product.fxml"));
		Parent productsScreen = loader.load();
		Scene productsScene = new Scene(productsScreen, ECommerceLaunch.WIDTH, ECommerceLaunch.HEIGHT);
		
		//Pass Catalog data back to controller
		ShowProductsController control = loader.getController();
		control.initCategory(catalog, category);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(productsScene);
		window.show();
	}
	
	@FXML void userSignIn( ActionEvent event)
	{
		System.out.println("ACCOUNT SCREEN");
	}
	
	@FXML
	public void viewCart( ActionEvent event ) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("fxml_cart_page.fxml"));
		Parent cartScreen = loader.load();
		Scene cartScene = new Scene(cartScreen, ECommerceLaunch.WIDTH, ECommerceLaunch.HEIGHT);
		
		//Pass existing cart data
		CartController control = loader.getController();
		control.initializeTable( );
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(cartScene);
		window.show();
	}

	@FXML
	public void updateCart(ActionEvent event)
	{		
		if(quantityField.getText().equals(""))
		{
			warning.setTextFill(ECommerceLaunch.WARNING);
			warning.setText("*  Please give a quantity");
		}
		else if(warning.getText().indexOf("selection") == -1)
		{
			try {
				int quantity = Integer.parseInt(quantityField.getText());
				warning.setTextFill(ECommerceLaunch.ACCENT_2_DARK);
				warning.setText("Your selection has been added");
				
				//Add item to Cart
				CartController.addCartItem(product, quantity);
			}
			catch (NumberFormatException e) {
				warning.setTextFill(ECommerceLaunch.WARNING);
				warning.setText("Please enter a number");
			}
		}
	}
	
}
