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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CategoryController 
{
	//Models
	private Catalog catalog;

	//Nodes
	@FXML private Label title;

	@FXML private Button backButton;
	@FXML private Button accountButton;
	@FXML private Button cartButton;
	//Button styling
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: #B0C485";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: #EBEFCC;";

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
		title.setTextFill(Color.web("FFFAEE"));
		
		Button[] categories = {artCategory, campCategory, candyCategory, elecCategory, toyCategory, gameCategory};
		//Set category images on buttons
		for(int i = 0; i < 6; i++)
		{
			setButtonImage(categories[i], i);
			setButtonHover(categories[i]);
		}
		
		//Create hover style
		setButtonHover(backButton);
		setButtonHover(accountButton);
		setButtonHover(cartButton);
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
			System.out.println(category + "Title: " + catalog.getCategoryTitles()[category]);
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("fxml_select_product.fxml"));
			Parent categoryScreen = loader.load();
			Scene categoryScene = new Scene(categoryScreen, 800, 600);
			
			ShowProductsController control = loader.getController();
			System.out.println( control );
			control.initCategory(catalog, category);
			
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			window.setScene(categoryScene);
			window.show();
		}
	}
	
	//Create transitions for hovering on and off a button
	public void setButtonHover( Button button )
	{
		button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_STYLE));
        button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_STYLE));
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
	public void viewCart( ActionEvent event )
	{
		System.out.println("View Cart");
	}
}
