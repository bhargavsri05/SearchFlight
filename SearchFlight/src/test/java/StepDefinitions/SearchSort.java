package StepDefinitions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSort {

	WebDriver driver = null;

	@Given("the user is on the flight search page")
	public void the_user_is_on_the_flight_search_page() {

		String projectPath = System.getProperty("user.dir");
		System.out.println("Project path is" +projectPath);

		System.getProperty("webdriver.chromedriver", projectPath+"/src/test/resources/Drivers/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String url = "https://www.in.cheapflights.com/";
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);

	}

	@When("the user enters  Hyderabad")
	public void the_user_enters_Hyderabad() {

		driver.findElement(By.xpath("//input[@id='FromTag']")).click();
		driver.findElement(By.xpath("//input[@id='FromTag']")).clear();
		driver.findElement(By.xpath("//input[@id='FromTag']")).sendKeys("Hyderabad, IN - Rajiv Gandhi International (HYD)");
	}

	@And("the user enters  Chennai")
	public void the_user_enters_Chennai() {
		driver.findElement(By.xpath("//input[@id='ToTag']")).click();
		driver.findElement(By.xpath("//input[@id='ToTag']")).clear();
		driver.findElement(By.xpath("//input[@id='ToTag']")).sendKeys("Chennai, IN - Chennai Airport (MAA)");
	}

	@And("the user selects the travel date")
	public void the_user_selects_the_travel_date() throws AWTException, InterruptedException {
		driver.findElement(By.xpath("//input[@id='DepartDate']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='DepartDate']")).sendKeys("Mon, 22 Jul, 2020");

		Actions builder = new Actions(driver);        
		builder.sendKeys(Keys.ENTER);

		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ESCAPE);
		r.keyRelease(KeyEvent.VK_ESCAPE);

		Thread.sleep(2000);


		//Code for selecting date in date picker


		/*String date = "22-Jul-2020";
		String splitter[] = date.split("-");
		String month_year = splitter[0];
		String day = splitter[7];	
		System.out.println(month_year);
		System.out.println(day);*/

	}

	@And("clicks search")
	public void clicks_search() throws InterruptedException {
		driver.findElement(By.id("SearchBtn")).click();
		Thread.sleep(5000);
	}

	@Then("the user is able to navigate to the search result page")
	public void the_user_is_able_to_navigate_to_the_search_result_page() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@And("the user sorts Cheapest flight")
	public void the_user_sorts_Cheapest_flight() {
		driver.getPageSource().contains("Flight details");
	}

	@Then("the user finds the Cheapest flight")
	public void the_user_finds_the_Cheapest_flight() {
		// Details of Cheapest flight
		String Cheapest = driver.findElement(By.xpath("//h3[@class='fs-3 c-neutral-900 fw-500']")).getText();
		System.out.println("Cheapest available flight" + Cheapest);
	}

	@And("the user sorts for quickest flight")

	public void the_user_sorts_quickest_flight() {

		//Sorting based on duration

		String arrow_direction = driver.findElement(By.xpath("//span[contains(text(),'Duration')]")).getAttribute("class");
		if(!arrow_direction.contains("up"))
			driver.findElement(By.xpath("//span[contains(text(),'Duration')]")).click();		

	}
	@Then("the user finds the quickest flight")
	public void the_user_finds_the_quickest_flight() {

		// Details of quickest flight
		driver.findElement(By.xpath("//div[@class='col-19']//div[1]//div[1]//div[2]//div[1]//div[1]//span[1]//div[1]")).click();
		String quickest = driver.findElement(By.xpath("//h3[@class='fs-3 c-neutral-900 fw-500']")).getText();
		System.out.println("Quickest available flight" + quickest);
	}

	@And("the user sorts evening flight")

	public void the_user_sorts_evening_flight() {	

		//Evening departures
		String arrow_direction = driver.findElement(By.xpath("//span[@class='fs-inherit c-inherit mr-1 fw-500']")).getAttribute("class");
		if(!arrow_direction.contains("down"))
			driver.findElement(By.xpath("//span[@class='fs-inherit c-inherit mr-1 fw-500']")).click();
	}

	@Then("the user finds the evening flight")
	public void the_user_finds_the_evening_flight() {

		//Evening departure details
		driver.findElement(By.xpath("//div[@class='col-19']//div[1]//div[1]//div[2]//div[1]//div[1]//span[1]//div[1]")).click();
		String evening = driver.findElement(By.xpath("//h3[@class='fs-3 c-neutral-900 fw-500']")).getText();
		System.out.println("Evening available flight" + evening);
	}



}
