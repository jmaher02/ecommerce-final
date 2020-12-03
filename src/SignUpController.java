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

public class SignUpController 
{

	//Nodes
	@FXML private Label title;
	@FXML private Label signUpTitle;
	@FXML private Label userNameTitle;
	@FXML private Label firstTitle;
	@FXML private Label lastTitle;
	@FXML private Label emailTitle;
	@FXML private Label passwordTitle;
	@FXML private Label notificationLabel;
	
	@FXML private TextField userNameInput;
	@FXML private TextField firstNameInput;
	@FXML private TextField lastNameInput;
	@FXML private TextField emailInput;
	@FXML private TextField passwordInput;

	@FXML private Button backButton;
	@FXML private Button accountButton;
	@FXML private Button cartButton;
	@FXML private Button submitSignUp;

	@FXML
	public void initialize( )
	{
		title.setTextFill(ECommerceLaunch.MAIN_LIGHT);
		signUpTitle.setTextFill(ECommerceLaunch.ACCENT_1_DARK);
		userNameTitle.setTextFill(ECommerceLaunch.ACCENT_1_LIGHT);
		firstTitle.setTextFill(ECommerceLaunch.ACCENT_1_LIGHT);
		lastTitle.setTextFill(ECommerceLaunch.ACCENT_1_LIGHT);
		emailTitle.setTextFill(ECommerceLaunch.ACCENT_1_LIGHT);
		passwordTitle.setTextFill(ECommerceLaunch.ACCENT_1_LIGHT);

	
		//Create hover style
  		ECommerceLaunch.setButtonHover(backButton, 0);
  		ECommerceLaunch.setButtonHover(accountButton, 3);
  		ECommerceLaunch.setButtonHover(cartButton, 0);
  		ECommerceLaunch.setButtonHover(submitSignUp, 1);
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
	
	public void setUserAccount(ActionEvent event)
	{
		boolean found = false;
		ArrayList<User> allUsers = ECommerceLaunch.getAllUsers();
		for(User user:allUsers)
		{
			if(user.getUserName().equals(userNameInput.getText()))
			{
				notificationLabel.setTextFill(ECommerceLaunch.WARNING);
				notificationLabel.setText("Username not available");
				userNameInput.setText("");
				found = true;
				break;
			}
		}

		if(!found)
		{
			if(User.isValidPassword(passwordInput.getText()))
			{
				User acct = new User(userNameInput.getText(), firstNameInput.getText(), 
									lastNameInput.getText(), emailInput.getText(), passwordInput.getText());
				AccountController.initializeAccount(acct);
				ECommerceLaunch.saveUserAccount(acct);
				notificationLabel.setTextFill(ECommerceLaunch.ACCENT_2_DARK);
				notificationLabel.setText("Thank you! "  );	

				userNameInput.setText("");
				firstNameInput.setText("");
				lastNameInput.setText("");
				emailInput.setText("");
				passwordInput.setText("");
			}
			else
			{
				notificationLabel.setTextFill(ECommerceLaunch.WARNING);
				notificationLabel.setText("Password must contain at least one uppercase, one lowercase, and one number"  );	
				passwordInput.setText("");
			}
		}
		
		
	}
}
