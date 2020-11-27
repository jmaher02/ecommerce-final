/** Controller class to manage the different categories
 * 
 * @author Jillian Maher
 */

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ShowProductsController 
{
	//Models
	private Catalog catalog;
	private int category;
	private int productpage;
	private ArrayList<Product> items;

	//Nodes
	@FXML private Label title;
	@FXML private Label categoryTitle;

	@FXML private Button backButton;
	@FXML private Button accountButton;
	@FXML private Button cartButton;
	//Button styling Sage
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: #B0C485";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: #EBEFCC;";
    //Button styling Teal
    private static final String IDLE_BUTTON_TEAL = "-fx-background-color: #689892";
    private static final String HOVERED_BUTTON_TEAL = "-fx-background-color: #8FB4A8;";

	@FXML private Button itemOneButton;
	@FXML private Button itemTwoButton;
	@FXML private Button itemThreeButton;
	@FXML private Button itemFourButton;
	@FXML private Button itemFiveButton;
	@FXML private Button itemSixButton;
	private Button[] productButtons;
	
	@FXML private Button moreButton;
	
	@FXML
	public void initialize()
	{
		catalog = new Catalog();
		
		//TESTING All Categories
		category = 1;
		items = catalog.getCategoryProducts(category);
		
		//Update Label Text Colors
		title.setTextFill(Color.web("#FFFAEE"));
		categoryTitle.setText(catalog.getCategoryTitles()[category]);
		categoryTitle.setTextFill(Color.web("#689892"));
		
		Button[] buttons = {itemOneButton, itemTwoButton, itemThreeButton, itemFourButton, itemFiveButton, itemSixButton};
		productButtons = buttons;
		productpage = 0;
		
		//Set product text on buttons
		setProductText();
		
		//Create hover style
		setButtonHover(moreButton, 1);
		setButtonHover(backButton, 0);
		setButtonHover(accountButton, 0);
		setButtonHover(cartButton, 0);
	}
	
	@FXML void viewProduct( ActionEvent event )
	{
		if(event.getSource() == itemOneButton)
		{
			System.out.println("Change page for product 1");
		}
		else if(event.getSource() == itemTwoButton)
		{
			System.out.println("Change page for product 2");
		}
		else if(event.getSource() == itemThreeButton)
		{
			System.out.println("Change page for product 3");
		}
		else if(event.getSource() == itemFourButton)
		{
			System.out.println("Change page for product 4");
		}
		else if(event.getSource() == itemFiveButton)
		{
			System.out.println("Change page for product 5");
		}
		else if(event.getSource() == itemSixButton)
		{
			System.out.println("Change page for product 6");
		}
		else
		{
			System.out.println("Not a category");
		}
	}
	
	@FXML
	public void showMoreProducts( ActionEvent event )
	{
		productpage += 6;
		if(productpage >= items.size())
		{
			productpage=0;
		}
		setProductText();
	}
	
	public void setProductText()
	{
		for(int i=0; i<6; i++)
		{
			if((i+productpage)<items.size() && items.get(i+productpage) != null)
			{
				productButtons[i].setText(items.get(i+productpage).getName());
				setButtonHover(productButtons[i], 1);
			}
			else
			{
				productButtons[i].setText("        NULL        ");
				setButtonHover(productButtons[i], 2);
			}
		}
	}
	
	//Create transitions for hovering on and off a button
	public void setButtonHover( Button button, int color )
	{
		if(color == 0)
		{
			button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_STYLE));
	        button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_STYLE));
		}
		else if (color == 1)
		{
			button.setStyle(IDLE_BUTTON_TEAL);
			button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_TEAL));
	        button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_TEAL));
		}
		else if (color == 2)
		{
			//show button as disabled
			button.setStyle(HOVERED_BUTTON_TEAL);
			button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_TEAL));
	        button.setOnMouseExited(e -> button.setStyle(HOVERED_BUTTON_TEAL));
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
	public void backToCategory( ActionEvent event) throws IOException
	{
		Parent categoryScreen = FXMLLoader.load(getClass().getResource("fxml_category_page.fxml"));
		Scene categoryScene = new Scene(categoryScreen);
		
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
}
