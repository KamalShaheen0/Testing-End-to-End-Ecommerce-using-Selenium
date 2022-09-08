package KamalProject.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;

import KamalProject.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
WebDriver driver ;
	
	public  CartPage(WebDriver driver){
		super(driver);
		//initialization
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> CartList ;
	
	@FindBy(css=".totalRow button")
	WebElement ChockoutEle ;
	
	public List<WebElement> getChartList() {
		
		return CartList ;
		
	}
	
     public boolean VerifyProductDisplay(String productName) throws InterruptedException {
      Boolean match = getChartList().stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match ;
		
	}
     
     public CheckOutPage goToCheckoutPage() {
    	 ChockoutEle.click();
         CheckOutPage checkout = new CheckOutPage(driver);
         return  checkout ;
     }
		
	
	

}
