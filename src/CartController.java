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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class CartController 
{
	//Models
	public static ObservableList<CartProduct> userCart;
	
	//Nodes
	@FXML private Label title;
	@FXML private Label cartTitle;
	@FXML private Label totalTitle;
	@FXML private Label totalAmount;
	@FXML private Label notificationLabel;

	@FXML private Button backButton;
	@FXML private Button accountButton;
	@FXML private Button cartButton;
	@FXML private Button setCartButton;
	
	@FXML private TableView<CartProduct> cartTable;
	@FXML private TableColumn<CartProduct, String> ProductName;
	@FXML private TableColumn<CartProduct, String> ProductPrice;
	@FXML private TableColumn<CartProduct, Integer> ProductQuantity;
	
	@FXML
	public void initialize()
	{
		//Update Label Text Colors
		title.setTextFill(ECommerceLaunch.MAIN_LIGHT);
		cartTitle.setTextFill(ECommerceLaunch.ACCENT_1_DARK);
		totalAmount.setTextFill(ECommerceLaunch.ACCENT_1_DARK);
		totalTitle.setTextFill(ECommerceLaunch.ACCENT_1_LIGHT);
		
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
		
		//Update Total Label
		updateTotal();
		
		//Size the columns
		cartTable.getColumns().get(0).prefWidthProperty().bind(cartTable.widthProperty().multiply(0.5));  
	    cartTable.getColumns().get(1).prefWidthProperty().bind(cartTable.widthProperty().multiply(0.25)); 
	    cartTable.getColumns().get(2).prefWidthProperty().bind(cartTable.widthProperty().multiply(0.25)); 
	    
	    //Create hover style
  		ECommerceLaunch.setButtonHover(backButton, 0);
  		ECommerceLaunch.setButtonHover(accountButton, 0);
  		ECommerceLaunch.setButtonHover(cartButton, 3);
  		ECommerceLaunch.setButtonHover(setCartButton, 1);
	}
	
	// Instantiate Cart object from Main Launch file
	public static void initializeCart( )
	{
		userCart = FXCollections.observableArrayList();
	}
	
	// Add a new Product to the Cart
	public static void addCartItem(Product product, int quantity)
	{
		CartProduct newItem = new CartProduct(product, quantity);
		
		userCart.add(newItem);
		System.out.println(newItem);
	}
	
	// Add User's Cart to current cart
	public static void addUserCart(ObservableList<CartProduct> cart)
	{
		userCart = cart;
	}
	
	//Store data into the table
	public void initializeTable( )
	{
		ProductName.setCellValueFactory(new PropertyValueFactory<CartProduct,String>("productName"));
		ProductPrice.setCellValueFactory(new PropertyValueFactory<CartProduct,String>("productPrice"));
		ProductQuantity.setCellValueFactory(new PropertyValueFactory<CartProduct,Integer>("productQty"));
		
		cartTable.setItems(userCart);
		
		cartTable.setEditable(true);
		ProductQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter() ));
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
	
	//Allow the user to edit quantities of items in Cart
	public void editQuantity(CellEditEvent<CartProduct, Integer> editedCell)
	{
		CartProduct itemSelected = cartTable.getSelectionModel().getSelectedItem();
		itemSelected.setQuantity(Integer.parseInt(editedCell.getNewValue().toString()));
		
		System.out.println(itemSelected);
		
		updateTotal();
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
	public void saveCart( ActionEvent event ) throws IOException
	{
		if(AccountController.saveCart(userCart))
		{
			notificationLabel.setTextFill(ECommerceLaunch.ACCENT_2_DARK);
			notificationLabel.setText("Cart saved in Account");
		
		}
		else
		{
			notificationLabel.setTextFill(ECommerceLaunch.WARNING);
			notificationLabel.setText("Sign In to save your cart!");
		}
	}
	
	@FXML 
	public void viewCart( ActionEvent event )
	{
		//Do nothing since we are already on cart screen
	}
	
	
	
}
