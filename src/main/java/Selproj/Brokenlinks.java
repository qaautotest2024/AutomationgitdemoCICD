package Selproj;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Brokenlinks {

	public static void main(String[] args) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		WebDriver driver = null;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//broken URL
		//Step 1 - is to get all urls tied up to the links using selenium
		//Java methods will call URL's and gets you the status code
		
	//
		
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
		SoftAssert a = new SoftAssert();
		
		
		for(WebElement link: links)
		{
			String url=link.getAttribute("href");
			HttpURLConnection conn= (HttpURLConnection)new URL(url).openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			int resCode =conn.getResponseCode();
			System.out.println(resCode);
			a.assertTrue(resCode>400, "The link with Text"+link.getText()+" is broken with code" +resCode);
//			if(resCode>400)
//			{
//				System.out.println("The link with Text"+link.getText()+" is broken with code" +resCode);
//				Assert.assertTrue(false);
//			}
		}
		a.assertAll();
		
		
		
		
		
		//String url=driver.findElement(By.cssSelector("a[href*='soapui']")).getAttribute("href");
		//String url=driver.findElement(By.cssSelector("a[href*='brokenlink']")).getAttribute("href");
		
		
		

	}

}
