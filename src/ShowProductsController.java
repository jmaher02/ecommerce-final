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
		//init method not working
		if(catalog == null)
		{
			catalog = new Catalog();
			category = 5;
			items = catalog.getCategoryProducts(category);
		}
		
		//Update Label Text Colors
		title.setTextFill(Color.web("#FFFAEE"));
		categoryTitle.setTextFill(Color.web("#689892"));
		
		
		//Create hover style
		setButtonHover(moreButton, 1);
		setButtonHover(backButton, 0);
		setButtonHover(accountButton, 0);
		setButtonHover(cartButton, 0);
	}
	
	//Initialize the category in order to show items
	public void initCategory(Catalog showCatalog, int showCategory)
	{
		catalog = showCatalog;
		
		//Set the category of items to grab from the catalog
		category = showCategory;
		items = catalog.getCategoryProducts(category);


		categoryTitle.setText(catalog.getCategoryTitles()[category]);

		Button[] buttons = {itemOneButton, itemTwoButton, itemThreeButton, itemFourButton, itemFiveButton, itemSixButton};
		productButtons = buttons;
		productpage = 0;
		//Set product text on buttons
		setProductText();
	}
	
	@FXML void viewProduct( ActionEvent event ) throws IOException
	{
		int item = productpage;
		if(event.getSource() == itemOneButton)
		{
			item+=0;
		}
		else if(event.getSource() == itemTwoButton)
		{
			item+=1;
		}
		else if(event.getSource() == itemThreeButton)
		{
			item+=2;
		}
		else if(event.getSource() == itemFourButton)
		{
			item+=3;
		}
		else if(event.getSource() == itemFiveButton)
		{
			item+=4;
		}
		else if(event.getSource() == itemSixButton)
		{
			item+=5;
		}
		else
		{
			item=-1;
		}
		
		if(item >= 0 && item < items.size())
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("fxml_product_page.fxml"));
			Parent categoryScreen = loader.load();
			Scene categoryScene = new Scene(categoryScreen, 800, 600);
			
			//Pass Product data to controller
			ProductController control = loader.getController();
			control.setProduct(catalog, items.get(item), category);
			
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			window.setScene(categoryScene);
			window.show();
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
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("fxml_category_page.fxml"));
		Parent categoryScreen = loader.load();
		Scene categoryScene = new Scene(categoryScreen, 800, 600);
		
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
