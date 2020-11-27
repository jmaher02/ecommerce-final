/** Controller class to manage the different categories
 * 
 * @author Jillian Maher
 */

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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
	
	@FXML void viewProducts( ActionEvent event )
	{
		if(event.getSource() == artCategory)
		{
			System.out.println("Retrieve art products");
		}
		else if(event.getSource() == campCategory)
		{
			System.out.println("Retrieve camping products");
		}
		else if(event.getSource() == candyCategory)
		{
			System.out.println("Retrieve candy products");
		}
		else if(event.getSource() == elecCategory)
		{
			System.out.println("Retrieve electronic products");
		}
		else if(event.getSource() == toyCategory)
		{
			System.out.println("Retrieve toy products");
		}
		else if(event.getSource() == gameCategory)
		{
			System.out.println("Retrieve game products");
		}
		else
		{
			System.out.println("Not a category");
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
