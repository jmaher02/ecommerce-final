/** Controller class to manage the different categories
 * 
 * @author Jillian Maher
 */

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		//Update Label Text Colors
		title.setTextFill(ECommerceLaunch.MAIN_LIGHT);
		categoryTitle.setTextFill(ECommerceLaunch.ACCENT_1_DARK);
		
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
				
		//Create hover style
		ECommerceLaunch.setButtonHover(moreButton, 1);
		ECommerceLaunch.setButtonHover(backButton, 0);
		ECommerceLaunch.setButtonHover(accountButton, 0);
		ECommerceLaunch.setButtonHover(cartButton, 0);
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
			Parent productScreen = loader.load();
			Scene productScene = new Scene(productScreen,  ECommerceLaunch.WIDTH, ECommerceLaunch.HEIGHT);
			
			//Pass Product data to controller
			ProductController control = loader.getController();
			control.setProduct(catalog, items.get(item), category);
			
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			window.setScene(productScene);
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
				ECommerceLaunch.setButtonHover(productButtons[i], 1);
			}
			else
			{
				productButtons[i].setText("NULL");
				ECommerceLaunch.setButtonHover(productButtons[i], 2);
			}
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
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(acctScene);
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
//		CartController control = loader.getController();
//		control.initializeTable( /*get cart list*/ null);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(cartScene);
		window.show();
	}
}
