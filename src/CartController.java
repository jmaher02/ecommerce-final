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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.stage.Stage;

public class CartController 
{
	//Models
	private User user;
	public static ObservableList<CartProduct> userCart;
	
	//Nodes
	@FXML private Label title;
	@FXML private Label cartTitle;
	@FXML private Label totalTitle;
	@FXML private Label totalAmount;

	@FXML private Button backButton;
	@FXML private Button accountButton;
	@FXML private Button cartButton;
	
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
		
		//Update Total Label
		updateTotal();
		
		//Size the columns
		cartTable.getColumns().get(0).prefWidthProperty().bind(cartTable.widthProperty().multiply(0.5));  
	    cartTable.getColumns().get(1).prefWidthProperty().bind(cartTable.widthProperty().multiply(0.25)); 
	    cartTable.getColumns().get(2).prefWidthProperty().bind(cartTable.widthProperty().multiply(0.25)); 
	    
	    //Create hover style
  		ECommerceLaunch.setButtonHover(backButton, 0);
  		ECommerceLaunch.setButtonHover(accountButton, 0);
  		ECommerceLaunch.setButtonHover(cartButton, 0);
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
	public void userSignIn( ActionEvent event )
	{
		
	}
	
	@FXML 
	public void viewCart( ActionEvent event )
	{
		//Do nothing since we are already on cart screen
	}
	
	
	
}
