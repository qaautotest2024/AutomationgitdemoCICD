package rahulshettyacademy.tests;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StandAloneTest extends BaseTest{
	String productName="ZARA COAT 3";
	
	
	
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
			
		
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		
//		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
//		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
//		driver.findElement(By.id("login")).click();
		
		
		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage =productCatalogue.goToCartpage();
	
	
		
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=	cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		JavascriptExecutor js = (JavascriptExecutor)driver;
	  	js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(2000);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		Thread.sleep(2000);
	
		//Thread.sleep(2000);
		//driver.findElement(By.cssSelector(".btnn")).click();
	//	Thread.sleep(2000);
//		Thread.sleep(3000);
//		js.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");
////		Thread.sleep(2000);
//		driver.findElement(By.cssSelector(".action_submit")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector(".action_submit")).click();
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("window.scrollBy(0,1000)");
		
//		WebElement dateofBirth = driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[1]"));
//		driver.findElement(with(By.tagName("a")).below(dateofBirth)).click();
//		
//		System.out.println("test");
//		By loadingimage = By.xpath("//div[@class='actions']/a");
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingimage));
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//div[@class='actions']/a")).click();
//		System.out.println("end");
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-500)");
		Thread.sleep(2000);
	String confirmmessage =confirmationPage.getconfirmationmessage();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("test");
			

	}
	
	// To verify that "zara coat 3" is displayed in the orders page
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue=landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage  ordersPage =productCatalogue.goToOrderspage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "anshika@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "shetty@gmail.com");
//		map.put("password", "Iamking@000");
//		map.put("product", "ADIDAS ORIGINAL");
		
		List<HashMap<String, String>>  data =getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[] []   {{data.get(0)},{data.get(1)}  };
		
		
		
	}
	

}
