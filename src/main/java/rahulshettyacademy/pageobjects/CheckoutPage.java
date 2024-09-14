package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(css=".btnn")
	WebElement submit;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		
		waitForElementToAppear(By.cssSelector(".ta-results"));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		
	//	Thread.sleep(2000);

	
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() throws InterruptedException
	
	{
		
		submit.click();
		System.out.println("git");
		
		System.out.println("Ramzan");
		
		return new ConfirmationPage(driver);
	}
	

}
