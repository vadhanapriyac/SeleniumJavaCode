# SeleniumJavaCode
/*Module1 : Home page 
	1.The navigation bar should be visible on desktop devices and display 9 items: Corporate….
	2.The Mashreq News should be displayed on the homepage.
	3.A link for “Contact Us” should be displayed on the homepage. Clicking this link should take to the contact form pg. 
*/

package com.qa.selenium.java.bank;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class HomePage {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\dell\\Desktop\\vadhana\\Mashreq_Bank_Assignment\\Setup\\Firefox\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver(); // launch

		driver.manage().window().maximize(); // maximize window
		driver.manage().deleteAllCookies(); // delete all the cookies

		// dynamic wait
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.mashreqbank.com/uae/en/personal/home"); // enter URL

		// 1.To Check Visibility:

		List<WebElement> links = driver.findElements(By.xpath("//div[@class='leftLinks']//ul//li"));
		System.out.println("No of Links in Home Page:" + links.size());

		for (int i = 0; i < links.size(); i++) {
			System.out.println(links.get(i).getText()); /* 1.Corporate, 2.Business, 3.International, 4. Private Banking,
														5. Al Islami, } //6.Gold, 7.Mashreq Securities,8.Mashreq Capital,
														9.Mashreq Global */
		} 
		
		// Page scroll down to view Mashreq News:

		WebElement news = driver.findElement(By.xpath("//div[@class='col-md-9']"));
		scrollIntoView(news, driver);

		// 2.The Mashreq News should be displayed on the homepage.

		List<WebElement> newsele = driver.findElements(By.xpath("//div[@class='newsBox']"));
		System.out.println("Title of Home Page:" + driver.getTitle());
		for (int j = 0; j < newsele.size(); j++) {
			String newselement = newsele.get(j).getText();
			System.out.println("Mashreq News :" + newselement); // Displays all the news.
		}

		// ScrollUp
		WebElement up = driver.findElement(By.xpath("//div[@class='col-md-3 col-xs-12 no-padding']"));
		scrollIntoView(up, driver);
		Thread.sleep(3000);

		/*
		 * 3.A link for “Contact Us” should be displayed on the homepage. Clicking this
		 * link should take to the contact form pg. Check Contact is displayed:
		 */

		driver.findElement(By.xpath("//div[@class='col-md-3 col-xs-12 no-padding']//ul//li//a[@title='Contact us']"))
				.isDisplayed();
		System.out.println("Title of Home Page:" + driver.getTitle());

		// Clicking this link should take to the contact form page.

		Actions action = new Actions(driver);
		action.moveToElement(driver
				.findElement(By.xpath("//div[@class='col-md-3 col-xs-12 no-padding']//ul//li//a[@title='Contact us']")))
				.build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='col-md-3 col-xs-12 no-padding']//ul//li//a[@title='Contact us']"))
				.click();
		Thread.sleep(3000);
		System.out.println("Contact Page Title:" + driver.getTitle());
		Thread.sleep(2000);
		driver.close();

	}

	public static void scrollIntoView(WebElement newselement, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", newselement);
	}
}
