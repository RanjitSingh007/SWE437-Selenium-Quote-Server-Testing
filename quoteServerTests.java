import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class quoteServerTests {

    public static void main(String [] args) {
        //path for the chrome driver
        String outputRandomQuote;
        String beforeRandomQuote;

        System.setProperty("webdriver.chrome.driver", "/Users/AhmadA/Desktop/chromedriver2");
        WebDriver driver = new ChromeDriver();   //created instance of Chrome Driver
        WebDriverWait wait = new WebDriverWait(driver, 5); //you can play with the time integer  to wait for longer than 15 seconds.`


        //called the quote Server app for testing
        //pulls in the webpage to test needs full URL
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");

        //maximize the window on the testers display
        driver.manage().window().maximize();



        //Testing Basic Choice Coverage A1,B1,C1,D1
        //Searching with a valid quote, and having the "both" box selected.
        //method pulls in the SearchBar,SubmitButton,and the printed text from the search results
        //and is compared against a static string known to be in the QuoteList
        //begin test 1 checking against a valid quote search and "both" being selected
        String validQuote1 = new String();
        validQuote1 = "If you have nothing to say, you don't have to put it into words.\n" +
                "—Alex Brodsky";

        //captures the seach bar and the sendKeys allows you to input something for it to type in the box
        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"searchText\"]"));

        //capture the submit button and simulate a press the copy xpath command in chrome works well for this path
        WebElement submitButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/table/tbody/tr[3]/td/input[1]"));

        //captures the "both" "author" and "quote" buttons to filter through
        WebElement filterTypeBoth = driver.findElement(By.xpath("//*[@id=\"both\"]"));
        WebElement filterTypeAuthor = driver.findElement(By.xpath("//*[@id=\"author\"]"));//this line causes exception
        WebElement filterTypeQuote = driver.findElement((By.xpath("//*[@id=\"quote\"]")));
        WebElement randomQuoteButton = driver.findElement(By.xpath("/html/body/form/input"));


        //begin setting elements for searching valid quote "both" using string "alex"
        filterTypeBoth.click();
        searchBar.sendKeys("Alex");
        submitButton.click();
        //pulls the output of the server and stores it in a string provided its not a one liner/no results
        String outputQuote = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/dl")).getText();

        try {
            Assert.assertEquals(validQuote1, outputQuote);
            System.out.println("Test A1,B1,C1,D1 Part 1 Passed");
        }
        catch(Throwable t){
            System.out.println("Test Did not Pass");
        }//end test Part1


        //NEED TO STALL HERE****
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



        //begin setting elements for searching a valid quote using the "author" filter text "Alex"
        filterTypeQuote.click();
        searchBar.sendKeys("Alex");
        submitButton.click();
        outputQuote = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/dl")).getText();
        try {
            Assert.assertEquals(validQuote1, outputQuote);
            System.out.println("Test A1,B1,C1,D1 Part 2 Passed");
        }
        catch(Throwable t){
            System.out.println("Test Did not Pass");
        }//end test Part2

        //AND STALL HERE***

        //begin setting elements for searching a valid quote using the "quote" filter and text "world
        filterTypeQuote.click();
        searchBar.sendKeys("World");
        submitButton.click();
        outputQuote = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/dl")).getText();
        try {
            Assert.assertEquals(outputQuote,validQuote1);
            System.out.println("Test A1,B1,C1,D1 Part 3 Passed");
        }
        catch (Throwable t){
            System.out.println("Test A1,B1,C1,D1 Part 3 Failed");
        }//end test Part3

        //AND STALL HERE****

        //begin setting elements for generating a random quote with no input in the search box
        beforeRandomQuote = driver.findElement(By.xpath("/html/body/div")).getText();
        randomQuoteButton.click();
        outputRandomQuote = driver.findElement(By.xpath("/html/body/div")).getText();//pulls text from the box

        try{
            Assert.assertNotEquals(beforeRandomQuote,outputRandomQuote);
            System.out.println("Test A1,B1,C1,D1 Part 4 Passed");
        }
        catch(Throwable t){
            System.out.println("Test A1,B1,C1,D1 Part 4 did not pass");
        }



        //close browser after all test have been run
        driver.quit();




    }
}
