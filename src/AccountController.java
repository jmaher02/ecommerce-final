/** Controller class to manage the user's cart
 * 
 * @author Jillian Maher
 */

import java.io.IOException;
import java.text.DecimalFormat;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.stage.Stage;

public class AccountController 
{
	//Models
	public static User user;
	public ObservableList<CartProduct> userCart;
	
	//Nodes
	@FXML private Label title;
	@FXML private Label accountTitle;
	@FXML private Label nameTitle;
	@FXML private Label emailTitle;
	@FXML private Label passwordTitle;
	@FXML private Label totalTitle;
	@FXML private Label totalAmount;
	@FXML private Label nameLabel;
	@FXML private Label emailLabel;
	@FXML private Label oldPasswordLabel;
	@FXML private Label newPasswordLabel;
	@FXML private Label passwordWarning;
	
	@FXML private TextField oldPasswordInput;
	@FXML private TextField newPasswordInput;

	@FXML private Button backButton;
	@FXML private Button accountButton;
	@FXML private Button cartButton;
	@FXML private Button signOutButton;
	@FXML private Button updatePasswordButton;
	@FXML private Button setCartButton;
	
    @FXML private ImageView userImage;
	
	@FXML private TableView<CartProduct> cartTable;
	@FXML private TableColumn<CartProduct, String> ProductName;
	@FXML private TableColumn<CartProduct, String> ProductPrice;
	@FXML private TableColumn<CartProduct, Integer> ProductQuantity;
	
	@FXML
	public void initialize()
	{
		//Update Label Text Colors
		title.setTextFill(ECommerceLaunch.MAIN_LIGHT);
		accountTitle.setTextFill(ECommerceLaunch.ACCENT_1_DARK);
		nameTitle.setTextFill(ECommerceLaunch.ACCENT_1_DARK);
		emailTitle.setTextFill(ECommerceLaunch.ACCENT_1_DARK);
		passwordTitle.setTextFill(ECommerceLaunch.ACCENT_1_DARK);
		oldPasswordLabel.setTextFill(ECommerceLaunch.ACCENT_1_LIGHT);
		newPasswordLabel.setTextFill(ECommerceLaunch.ACCENT_1_LIGHT);
		totalAmount.setTextFill(ECommerceLaunch.ACCENT_1_DARK);
		totalTitle.setTextFill(ECommerceLaunch.ACCENT_1_LIGHT);
		passwordWarning.setTextFill(ECommerceLaunch.WARNING);
		
		
		//Access the user's info
		if(user != null)
		{
			nameLabel.setText(user.getName());
			emailLabel.setText(user.getEmail());
			userCart = user.getUserCart();
			//Set image
			try {
			userImage.setImage(new Image( user.showFeaturedPicture() ));
			}
			catch (IllegalArgumentException e)
			{
				userImage.setImage(new Image( "images/userIconDefault.png"));
			}			
			
			//Update Total Label
			updateTotal();
		}
		else
		{
			userCart = FXCollections.observableArrayList();
		}
		
		//Size the columns
		cartTable.getColumns().get(0).prefWidthProperty().bind(cartTable.widthProperty().multiply(0.5));  
	    cartTable.getColumns().get(1).prefWidthProperty().bind(cartTable.widthProperty().multiply(0.25)); 
	    cartTable.getColumns().get(2).prefWidthProperty().bind(cartTable.widthProperty().multiply(0.25)); 
	    
	    //Create hover style
  		ECommerceLaunch.setButtonHover(backButton, 0);
  		ECommerceLaunch.setButtonHover(accountButton, 3);
  		ECommerceLaunch.setButtonHover(cartButton, 0);
  		ECommerceLaunch.setButtonHover(signOutButton, 1);
  		ECommerceLaunch.setButtonHover(updatePasswordButton, 1);
  		ECommerceLaunch.setButtonHover(setCartButton, 1);  		
	}
	
	//Set the user from the sign in screen
	public static void initializeAccount( User setUser )
	{
		user = setUser;
	}
	
	//Save the Cart for this User
	// returns false if there is no user
	public static boolean saveCart( ObservableList<CartProduct> saveCart)
	{
		if(user==null)
		{
			return false;
		}
		else
		{
			user.setCart(saveCart);
			ECommerceLaunch.updateUser(user);
			return true;
		}
	}
	
	//Store data into the table
	public void initializeTable( )
	{
		ProductName.setCellValueFactory(new PropertyValueFactory<CartProduct,String>("productName"));
		ProductPrice.setCellValueFactory(new PropertyValueFactory<CartProduct,String>("productPrice"));
		ProductQuantity.setCellValueFactory(new PropertyValueFactory<CartProduct,Integer>("productQty"));
		
		cartTable.setItems(userCart);
	}
	
	//Update the total in the cart
	public void updateTotal()
	{
		//Calculate total
		double total = 0.0;
		for(CartProduct product: userCart)
		{
			total += product.getSubtotal();
		}

		//Prepare for formatted output
		String pattern = "##0.00";
		DecimalFormat decFormat = new DecimalFormat(pattern);
		
		totalAmount.setText("$" + decFormat.format(total));
	}
	
	//Remove user from the account page and initiate Sign In page
	@FXML
	public void userSignOut( ActionEvent event ) throws IOException
	{
		user = null;
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("fxml_signin_page.fxml"));
		Parent signInScreen = loader.load();
		Scene signInScene = new Scene(signInScreen, ECommerceLaunch.WIDTH, ECommerceLaunch.HEIGHT);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(signInScene);
		window.show();
	}
	
	@FXML
	public void restoreCart( ActionEvent event ) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("fxml_cart_page.fxml"));
		Parent cartScreen = loader.load();
		Scene cartScene = new Scene(cartScreen, ECommerceLaunch.WIDTH, ECommerceLaunch.HEIGHT);
		
		//Pass existing cart data
		CartController.addUserCart(userCart);
		CartController control = loader.getController();
		control.initializeTable( );
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(cartScene);
		window.show();
		
		user.setCart( FXCollections.observableArrayList() );
	}
	
	@FXML
	public void backToHome( ActionEvent event ) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("fxml_homepage.fxml"));
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
	public void updatePassword(ActionEvent event)
	{
		if(!User.isValidPassword(newPasswordInput.getText()))
		{
			passwordWarning.setText("Provide a valid new password");
		}
		else if(user != null && user.setPassword(oldPasswordInput.getText(), newPasswordInput.getText()))
		{
			passwordWarning.setText("Password has been updated");
			ECommerceLaunch.updateUser(user);
		}
		else
		{
			passwordWarning.setText("Password not updated, incorrect password. ");
		}
		
		oldPasswordInput.setText("");
		newPasswordInput.setText("");
	}
	
}
