/** Controller class to manage the user's cart
 * 
 * @author Jillian Maher
 */

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.*;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class AccountController 
{
	//Models
	private static User user;
	public static ObservableList<CartProduct> userCart;
	
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
	@FXML private Button updatePasswordButton;
	
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
		
		//Update Total Label
		//updateTotal();
		
		//Size the columns
		cartTable.getColumns().get(0).prefWidthProperty().bind(cartTable.widthProperty().multiply(0.5));  
	    cartTable.getColumns().get(1).prefWidthProperty().bind(cartTable.widthProperty().multiply(0.25)); 
	    cartTable.getColumns().get(2).prefWidthProperty().bind(cartTable.widthProperty().multiply(0.25)); 
	    
	    //Create hover style
  		ECommerceLaunch.setButtonHover(backButton, 0);
  		ECommerceLaunch.setButtonHover(accountButton, 0);
  		ECommerceLaunch.setButtonHover(cartButton, 0);
  		ECommerceLaunch.setButtonHover(updatePasswordButton, 1);
	}
	
	//Set the user from the sign in screen
	public static void initializeAccount( User setUser )
	{
		user = setUser;
		userCart = user.getUserCart();
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
	
	@FXML
	public void backToHome( ActionEvent event ) throws IOException
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
	public void updatePassword(ActionEvent event)
	{
		oldPasswordInput.setText("");
		newPasswordInput.setText("");
	}
	
}
