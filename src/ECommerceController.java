/** Controller class to manage the Home page
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

public class ECommerceController 
{	
	//Models
	private Product featured;
	private int featCategory;
	
	//Nodes
	@FXML private Label title;
	@FXML private Label productName;
	@FXML private Label productPrice;
	@FXML private Label categoryTitle;

	@FXML private Button homeButton;
	@FXML private Button accountButton;
	@FXML private Button cartButton;
	@FXML private Button productButton;
	@FXML private Button categoryButton;
	
	@FXML private ImageView productImage;
	
	@FXML
	public void initialize()
	{		
		//Update Label Text Colors
		title.setTextFill(ECommerceLaunch.MAIN_LIGHT);
		productName.setTextFill(ECommerceLaunch.MAIN_LIGHT);
		categoryTitle.setTextFill(ECommerceLaunch.ACCENT_1_DARK);
		
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
		
		//Get data for a random featured product
		setFeatureProduct();
		
		//Create hover style
		ECommerceLaunch.setButtonHover(homeButton, 3);
		ECommerceLaunch.setButtonHover(accountButton,0);
		ECommerceLaunch.setButtonHover(cartButton,0);
		ECommerceLaunch.setButtonHover(categoryButton, 4);
		ECommerceLaunch.setButtonHover(productButton, 1);
	}
	
	@FXML void userSignIn( ActionEvent event) throws IOException
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
	public void goToAllCategories( ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("fxml_category_page.fxml"));
		Parent categoryScreen = loader.load();
		Scene categoryScene = new Scene(categoryScreen, ECommerceLaunch.WIDTH, ECommerceLaunch.HEIGHT);
				
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(categoryScene);
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
	public void viewProduct(ActionEvent event) throws IOException
	{		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("fxml_product_page.fxml"));
		Parent productScreen = loader.load();
		Scene productScene = new Scene(productScreen,  ECommerceLaunch.WIDTH, ECommerceLaunch.HEIGHT);
		
		//Pass Product data to controller
		ProductController control = loader.getController();
		control.setProduct(featured, featCategory);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(productScene);
		window.show();
	}

	
	public void setFeatureProduct( )
	{
		featured = ECommerceLaunch.catalog.getRandomProduct();
		featCategory = ECommerceLaunch.catalog.findProductCategory(featured);
		
		//Set product text details
		productName.setText(featured.getFeaturedTitle(30));
		productPrice.setText(featured.printPrice());		

		//Set image
		productImage.setImage(new Image( featured.showFeaturedPicture() ));
		productImage.setFitHeight(100);
		productImage.setFitWidth(100);
		productImage.setPreserveRatio(true);
	}
	
}
