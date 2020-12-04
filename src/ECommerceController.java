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
	private Catalog catalog;
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
	
	@FXML private Button artCategory;
	@FXML private Button campCategory;
	@FXML private Button candyCategory;
	@FXML private Button elecCategory;
	@FXML private Button toyCategory;
	@FXML private Button gameCategory;
	
	@FXML private ImageView productImage;
	
	@FXML
	public void initialize()
	{		
		catalog = ECommerceLaunch.catalog;
		
		//Update Label Text Colors
		title.setTextFill(ECommerceLaunch.MAIN_LIGHT);
		productName.setTextFill(ECommerceLaunch.MAIN_LIGHT);
		categoryTitle.setTextFill(ECommerceLaunch.ACCENT_1_DARK);
		
		Button[] categories = {artCategory, campCategory, candyCategory, elecCategory, toyCategory, gameCategory};
		//Set category images on buttons
		for(int i = 0; i < 6; i++)
		{
			categories[i].setPrefSize(100, 100);
			categories[i].setMaxSize(120, 120);
			setButtonImage(categories[i], i);
			ECommerceLaunch.setButtonHover(categories[i], 1);
		}
		
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
		ECommerceLaunch.setButtonHover(productButton, 1);
	}

	@FXML void viewProducts( ActionEvent event ) throws IOException
	{
		int category = -1;
		if(event.getSource() == artCategory)
		{
			category = 0;
		}
		else if(event.getSource() == campCategory)
		{
			category = 1;
		}
		else if(event.getSource() == candyCategory)
		{
			category = 2;
		}
		else if(event.getSource() == elecCategory)
		{
			category = 3;
		}
		else if(event.getSource() == toyCategory)
		{
			category = 4;
		}
		else if(event.getSource() == gameCategory)
		{
			category = 5;
		}
		else
		{
			category = -1;
		}
		
		if(category != -1)
		{
			//Jump to new screen to show products for given cateogry
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("fxml_select_product.fxml"));
			Parent categoryScreen = loader.load();
			Scene categoryScene = new Scene(categoryScreen, ECommerceLaunch.WIDTH, ECommerceLaunch.HEIGHT);
			
			//Pass data to the Products Page to find the catalog items
			ShowProductsController control = loader.getController();
			control.initCategory(catalog, category);
			
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			window.setScene(categoryScene);
			window.show();
		}
	}
	
	//Add images to the buttons
	public void setButtonImage( Button button , int index)
	{
		ImageView view = new ImageView( new Image("images/" + catalog.getImageTitle(index)));
		button.setGraphic(view);
		button.setText("");
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
