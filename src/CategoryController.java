/** Controller class to manage the different categories
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

public class CategoryController 
{
	//Models
	private Catalog catalog;

	//Nodes
	@FXML private Label title;
	@FXML private Label categoryTitle;

	@FXML private Button backButton;
	@FXML private Button accountButton;
	@FXML private Button cartButton;

	@FXML private Button artCategory;
	@FXML private Button campCategory;
	@FXML private Button candyCategory;
	@FXML private Button elecCategory;
	@FXML private Button toyCategory;
	@FXML private Button gameCategory;
	
	@FXML
	public void initialize()
	{
		catalog = new Catalog();
		
		//Update Label Text Colors
		title.setTextFill(ECommerceLaunch.MAIN_LIGHT);
		categoryTitle.setTextFill(ECommerceLaunch.ACCENT_1_DARK);
		categoryTitle.setMinWidth(400.0);
		
		Button[] categories = {artCategory, campCategory, candyCategory, elecCategory, toyCategory, gameCategory};
		//Set category images on buttons
		for(int i = 0; i < 6; i++)
		{
			setButtonImage(categories[i], i);
			ECommerceLaunch.setButtonHover(categories[i], 1);
		}
		
		//Create hover style
		ECommerceLaunch.setButtonHover(backButton, 0);
		ECommerceLaunch.setButtonHover(accountButton, 0);
		ECommerceLaunch.setButtonHover(cartButton, 0);
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

	@FXML
	public void backToHomepage( ActionEvent event)
	{
		System.out.println("GO BACK");
	}
	
	@FXML void userSignIn( ActionEvent event)
	{
		System.out.println("ACCOUNT SCREEN");
	}
	
	@FXML
	public void viewCart( ActionEvent event ) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("fxml_cart_page.fxml"));
		Parent cartScreen = loader.load();
		Scene cartScene = new Scene(cartScreen, ECommerceLaunch.WIDTH, ECommerceLaunch.HEIGHT);
		
		//Pass existing cart data
//		CartController control = loader.getController();
//		control.initializeTable( /*get cart list*/ null);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(cartScene);
		window.show();
	}
}
