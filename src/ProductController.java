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
		//Update Label Text Colors
		title.setTextFill(ECommerceLaunch.MAIN_LIGHT);
		productName.setTextFill(ECommerceLaunch.ACCENT_1_DARK);
		productDetails.setTextFill(ECommerceLaunch.ACCENT_1_LIGHT);
		productID.setTextFill(ECommerceLaunch.ACCENT_1_LIGHT);
		
		//Set the Sign In / Account Button
		if(AccountController.user != null)
		{
			accountButton.setText("ACCOUNT");
			accountButton.setOnAction( e -> {  
				try {
					getAccount(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}} );
		}
				
		//Create hover style
		ECommerceLaunch.setButtonHover(backButton, 0);
		ECommerceLaunch.setButtonHover(accountButton, 0);
		ECommerceLaunch.setButtonHover(addToCart, 1);
		ECommerceLaunch.setButtonHover(cartButton, 0);
	}
	
	//Initialize the product to be displayed
	public void setProduct(Product initProduct, int saveCategory)
	{
		catalog = ECommerceLaunch.catalog;
		product = initProduct;
		category = saveCategory;
		
		productName.setText(product.getName());
		productPrice.setText(product.printPrice());
		productID.setText("Item #" + product.getItemNumber());
		productDetails.setText(product.displayCharacteristics());
		
		//Set image
		try {
		productImage.setImage(new Image( product.showFeaturedPicture() ));
		}
		catch (IllegalArgumentException e)
		{
			productImage.setImage(new Image( "images/defaultProduct.png"));
		}
		productImage.setFitHeight(200);
		productImage.setFitWidth(200);
		productImage.setPreserveRatio(true);
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
	
	@FXML
	public void userSignIn( ActionEvent event ) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("fxml_signin_page.fxml"));
		Parent signInScreen = loader.load();
		Scene signInScene = new Scene(signInScreen, ECommerceLaunch.WIDTH, ECommerceLaunch.HEIGHT);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(signInScene);
		window.show();
	}
	
	@FXML
	public void getAccount(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("fxml_account_page.fxml"));
		Parent acctScreen = loader.load();
		Scene acctScene = new Scene(acctScreen, ECommerceLaunch.WIDTH, ECommerceLaunch.HEIGHT);

		//Pass existing cart data
		AccountController control = loader.getController();
		control.initializeTable( );
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(acctScene);
		window.show();
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
				
				//Add item to Cart
				if(CartController.addCartItem(product, quantity))
				{
					warning.setTextFill(ECommerceLaunch.ACCENT_2_DARK);
					warning.setText("Your quantity has been updated");
				}
				else
				{
					warning.setTextFill(ECommerceLaunch.ACCENT_2_DARK);
					warning.setText("Your selection has been added");
				}
			}
			catch (NumberFormatException e) {
				warning.setTextFill(ECommerceLaunch.WARNING);
				warning.setText("*  Please enter a number");
			}
		}
	}
	
}
