/** Controller class to manage the sign in form
 * 
 * @author Jillian Maher
 */

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.Stage;

public class SignInController 
{

	//Nodes
	@FXML private Label title;
	@FXML private Label signInTitle;
	@FXML private Label userNameTitle;
	@FXML private Label passwordTitle;
	@FXML private Label notificationLabel;
	
	@FXML private TextField userNameInput;
	@FXML private TextField passwordInput;

	@FXML private Button backButton;
	@FXML private Button accountButton;
	@FXML private Button cartButton;
	@FXML private Button submitSignIn;
	@FXML private Button signUpButton;

	@FXML
	public void initialize( )
	{
		title.setTextFill(ECommerceLaunch.MAIN_LIGHT);
		signInTitle.setTextFill(ECommerceLaunch.ACCENT_1_DARK);
		userNameTitle.setTextFill(ECommerceLaunch.ACCENT_1_LIGHT);
		passwordTitle.setTextFill(ECommerceLaunch.ACCENT_1_LIGHT);

	
		//Create hover style
  		ECommerceLaunch.setButtonHover(backButton, 0);
  		ECommerceLaunch.setButtonHover(accountButton, 3);
  		ECommerceLaunch.setButtonHover(cartButton, 0);
  		ECommerceLaunch.setButtonHover(submitSignIn, 1);
  		ECommerceLaunch.setButtonHover(signUpButton, 4);
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
	
	public void findUserAccount(ActionEvent event)
	{
		boolean found = false;
		ArrayList<User> allUsers = ECommerceLaunch.getAllUsers();
		for(User user:allUsers)
		{
			if(user.getUserName().equals(userNameInput.getText()))
			{
				if(user.correctPassword(passwordInput.getText()))
				{
					notificationLabel.setTextFill(ECommerceLaunch.ACCENT_2_DARK);
					notificationLabel.setText("Welcome back, "+ user.getName() + "!");
					AccountController.initializeAccount(user);
					found = true;
					break;
				}
				else
				{
					notificationLabel.setTextFill(ECommerceLaunch.WARNING);
					notificationLabel.setText("Incorrect Username and Password");
					break;
				}
			}
		}

		if(!found)
		{
			notificationLabel.setTextFill(ECommerceLaunch.WARNING);
			notificationLabel.setText("Incorrect Username and Password");
		}
		
		userNameInput.setText("");
		passwordInput.setText("");
	}
	
	@FXML 
	public void goToNewAccount( ActionEvent event ) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("fxml_signup_page.fxml"));
		Parent signUpScreen = loader.load();
		Scene signUpScene = new Scene(signUpScreen, ECommerceLaunch.WIDTH, ECommerceLaunch.HEIGHT);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(signUpScene);
		window.show();
	}
}
