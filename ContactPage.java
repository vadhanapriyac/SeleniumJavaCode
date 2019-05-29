/*Module 2: On the contact form page:
	1.Submitting the form without entering any details should indicate an error and specify which fields are required.
	2.The “I am looking to…” field is a dropdown with 4 choices
	3.The “Select Sub Product” field is initially empty:
	     a. Selecting the Product “Loans” from the dropdown 
	     b.Select Sub Product” dropdown with 6 options
		 including “Home Loan UAE Resident”
	4.Entering a number that is less than 7 digits in the “Mobile Number” field should show an error. 
		 Entering a number that is 7 digits should not show an error Invalid Input for Mobile number
*/

package com.qa.selenium.java.bank;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ContactPage {
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
		Thread.sleep(3000);

		// Clicking this link should take from home page to the contact form page.

		Actions action = new Actions(driver);
		action.moveToElement(driver
				.findElement(By.xpath("//div[@class='col-md-3 col-xs-12 no-padding']//ul//li//a[@title='Contact us']")))
				.build().perform();
		driver.findElement(By.xpath("//div[@class='col-md-3 col-xs-12 no-padding']//ul//li//a[@title='Contact us']"))
				.click();
		Thread.sleep(3000);
		System.out.println("Contact Page Title:" + driver.getTitle());
		Thread.sleep(2000);
		driver.findElement(By.id("btnSubmit")).click();

		driver.findElements(By.xpath("//select[@id='reachoutforproduct']"));
		System.out.println("Please Select I am Looking to.. in the List");
		driver.findElements(By.xpath("//select[@id='compInqServ']"));
		System.out.println("Please Select Complaint/Enquiry/Service Type in the List");
		driver.findElements(By.xpath("//select[@id='need']"));
		System.out.println("Please Select Product in the List");
		driver.findElements(By.xpath("//select[@id='product']"));
		System.out.println("Please Select Sub Product in the List");
		driver.findElements(By.xpath("//select[@id='emirate']"));
		System.out.println("Please Select Emirate in the List");
		driver.findElements(By.xpath("//select[@id='branch']"));
		System.out.println("Please Select Branch in the List");
		driver.findElements(By.xpath("//input[@id='firstName']"));
		System.out.println("Please Enter FirstName");
		driver.findElements(By.xpath("//input[@id='email']"));
		System.out.println("Please Enter Email");
		driver.findElements(By.xpath("//input[@id='mobile']"));
		System.out.println("Please Enter Mobile Number");

		// 2.The “I am looking to…” field is a dropdown with 4 choices
		
		Select select = new Select(driver.findElement(By.xpath("//select[@id='reachoutforproduct']")));
		select.selectByVisibleText("I am looking to...");

		/*
		 * 3.The “Select Sub Product” field is initially empty: a. Selecting the Product
		 * “Loans” from the dropdown b.Select Sub Product” dropdown with 6 options
		 * including “Home Loan UAE Resident”
		 */

		Select product = new Select(driver.findElement(By.xpath("//select[@id='need']")));
		product.selectByVisibleText("Loans");
		Select subproduct = new Select(driver.findElement(By.xpath("//select[@id='product']")));
		subproduct.selectByVisibleText("Home Loan UAE Resident");

		// Page scroll down to view Mobile Field:

		WebElement mobilenum = driver.findElement(By.xpath("//input[@id='mobile']"));
		scrollIntoView(mobilenum, driver);
		Thread.sleep(2000);

		/*
		 * 4.Entering a number that is less than 7 digits in the “Mobile Number” field
		 * should show an error. Entering a number that is 7 digits should not show an
		 * error Invalid Input for Mobile number
		 */

		driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys("123456");
		WebElement mob = driver
				.findElement(By.xpath("//div[@class='col-lg-9 col-md-9 col-sm-9 col-xs-9 padding-right-0px']"));
		System.out.println("InValid Mobile Number:" + mob.getText()); // Displays Error Message
		driver.findElement(By.xpath("//input[@id='mobile']")).clear();

		// Positive Input for Mobile number

		driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys("1234567");
		driver.findElement(By.xpath("//div[@class='col-lg-9 col-md-9 col-sm-9 col-xs-9 padding-right-0px']"));
		System.out.println("Valid Mobile Number"); // Displays valid Message
		// driver.findElement(By.xpath("//input[@id='mobile']")).clear();
		Thread.sleep(2000);

		driver.close();

	}

	public static void scrollIntoView(WebElement newselement, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", newselement);
	}

	public static void generateAlert(WebDriver driver, String message) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("alert('" + message + "')");
	}

}
