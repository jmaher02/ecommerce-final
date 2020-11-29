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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ProductController 
{
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
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
	@FXML private TextField quantity;
	
	@FXML private Button addToCart;
	@FXML private Button backButton;
	@FXML private Button accountButton;
	@FXML private Button cartButton;
	//Button styling
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: #B0C485";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: #EBEFCC;";

    @FXML private ImageView productImage;
	
	@FXML
	public void initialize()
	{
		//catalog = new Catalog();
		
		//Update Label Text Colors
		title.setTextFill(Color.web("FFFAEE"));
		productName.setTextFill(Color.web("#689892"));
		productDetails.setTextFill(Color.web("#8FB4A8"));
		productID.setTextFill(Color.web("#8FB4A8"));
				
		//Create hover style
		setButtonHover(backButton);
		setButtonHover(accountButton);
		setButtonHover(addToCart);
		setButtonHover(cartButton);
	}
	
	@FXML
	public void backToAllProducts( ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("fxml_select_product.fxml"));
		Parent categoryScreen = loader.load();
		Scene categoryScene = new Scene(categoryScreen, 800, 600);
		
		//Pass Catalog data back to controller
		ShowProductsController control = loader.getController();
		control.initCategory(catalog, category);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(categoryScene);
		window.show();
	}
	
	@FXML void userSignIn( ActionEvent event)
	{
		System.out.println("ACCOUNT SCREEN");
	}
	
	@FXML
	public void viewCart( ActionEvent event )
	{
		System.out.println("View Cart");
	}

	@FXML
	public void updateCart(ActionEvent event)
	{		
		System.out.println("Pressed Button");
	}

	//Create transitions for hovering on and off a button
	public void setButtonHover( Button button )
	{
		button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_STYLE));
        button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_STYLE));
	}
	
	//Initialize the product to be displayed
	public void setProduct(Catalog saveCatalog, Product initProduct, int saveCategory)
	{
		catalog = saveCatalog;
		product = initProduct;
		category = saveCategory;
		
		productName.setText(product.getName());
		productPrice.setText(product.printPrice());
		productID.setText(product.getItemNumber() + "");
		productDetails.setText(product.displayCharacteristics());
		
		//Set default image
		productImage.setImage(new Image("images/defaultProduct.png"));
	}
	
}
