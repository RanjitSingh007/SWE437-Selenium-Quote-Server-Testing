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

        //a valid quote we know is in the list and can search for. one of 2
        String validQuote1;
        validQuote1 = "If you have nothing to say, you don't have to put it into words.\n" +
                "—Alex Brodsky";

        System.setProperty("webdriver.chrome.driver", "/Users/AhmadA/Desktop/chromedriver2");
        WebDriver driver = new ChromeDriver();   //created instance of Chrome Driver
        WebDriverWait wait = new WebDriverWait(driver, 5); //you can play with the time integer  to wait for longer than 15 seconds.`


        //called the quote Server app for testing
        //pulls in the webpage to test needs full URL
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");

        //maximize the window on the testers display
        driver.manage().window().maximize();

        //captures the seach bar and the sendKeys allows you to input something for it to type in the box
        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"searchText\"]"));

        //capture the submit button and simulate a press the copy xpath command in chrome works well for this path
        WebElement submitButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/table/tbody/tr[3]/td/input[1]"));

        //captures the "both" "author" and "quote" buttons to filter through
        WebElement filterTypeBoth = driver.findElement(By.xpath("//*[@id=\"both\"]"));
        WebElement filterTypeAuthor = driver.findElement(By.xpath("//*[@id=\"author\"]"));//this line causes exception
        WebElement filterTypeQuote = driver.findElement((By.xpath("//*[@id=\"quote\"]")));

        //Test A1,B1,C1,D1
        //call function to test A1 and print result
        System.out.println(TestA1(driver));
        //call Function to test B1 and print result
        System.out.println(TestB1(driver));
        //call function to test C1 and print result
        System.out.println(TestC1(driver));
        //call function to test D1 and print
        System.out.println(TestD1(driver));
        //End Test A1,B1,C1,D1

        //Begin Test A1,B1,C2,D1
        System.out.println(TestA1(driver));
        System.out.println(TestB1(driver));
        //Test C2 invalid quote string, and result should not be found input = "schoolcool"
        filterTypeAuthor = driver.findElement((By.xpath("//*[@id=\"quote\"]")));
        filterTypeAuthor.click();
        searchBar = driver.findElement(By.xpath("//*[@id=\"searchText\"]"));
        searchBar.sendKeys("schoolcool");
        submitButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/table/tbody/tr[3]/td/input[1]"));
        submitButton.click();
        String output = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/p")).getText();
        String correctOutput = "Your search - schoolcool - did not match any quotes.";
        try{
            Assert.assertEquals(output,correctOutput);
            System.out.println("Test C2 Passed");
        }
        catch (Throwable t){
            System.out.println("Test C2 Failed");
        }
        System.out.println(TestD1(driver));
        //End Test A1,B1,C2,D1

        //Begin Test A1,B1,C3,D1
        System.out.println(TestA1(driver));
        System.out.println(TestB1(driver));
        //Test C3 empty string being passed in with the author box selected
        filterTypeAuthor = driver.findElement((By.xpath("//*[@id=\"quote\"]")));
        filterTypeAuthor.click();
        searchBar = driver.findElement(By.xpath("//*[@id=\"searchText\"]"));
        searchBar.sendKeys("");
        submitButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/table/tbody/tr[3]/td/input[1]"));
        submitButton.click();
        output = "";
        correctOutput = "";
        try{
            Assert.assertEquals(output,correctOutput);
            System.out.println("Test C3 Passed");
        }
        catch(Throwable t){
            System.out.println("Test C3 Failed");
        }
        System.out.println(TestD1(driver));
        //End Test A1,B1,C3,D1

        //Begin Test A1,B2,C1,D1
        System.out.println(TestA1(driver));
        filterTypeQuote = driver.findElement((By.xpath("//*[@id=\"quote\"]")));
        filterTypeQuote.click();
        searchBar = driver.findElement(By.xpath("//*[@id=\"searchText\"]"));
        searchBar.sendKeys("!Pigs CAn Fly");
        submitButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/table/tbody/tr[3]/td/input[1]"));
        submitButton.click();
        output = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/p")).getText();
        correctOutput = "Your search - !Pigs CAn Fly - did not match any quotes.";
        
        try{
            Assert.assertEquals(correctOutput,output);
            System.out.println("Test B2 Passed");
        }
        catch (Throwable t){
            System.out.println("Test B2 Failed");
        }
        System.out.println(TestC1(driver));
        System.out.println(TestD1(driver));
        //End Test A1,B2,C1,D1

        //Begin Test A1,B3,C1,D1
        System.out.println(TestA1(driver));
        filterTypeQuote = driver.findElement((By.xpath("//*[@id=\"quote\"]")));
        filterTypeQuote.click();
        searchBar = driver.findElement(By.xpath("//*[@id=\"searchText\"]"));
        searchBar.sendKeys("");
        submitButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/table/tbody/tr[3]/td/input[1]"));
        submitButton.click();
        output = "";
        correctOutput = "";
        try{
            Assert.assertEquals(correctOutput,output);
            System.out.println("Test B3 Passed");
        }
        catch (Throwable t){
            System.out.println("Test B3 Failed");
        }
        System.out.println(TestC1(driver));
        System.out.println(TestD1(driver));
        //End Test A1,B3,C1,D1

        //Begin Test A2,B1,C1,D1
        filterTypeBoth = driver.findElement(By.xpath("//*[@id=\"both\"]"));
        filterTypeBoth.click();
        searchBar = driver.findElement(By.xpath("//*[@id=\"searchText\"]"));
        searchBar.sendKeys("!This Quote doesnt exist?");
        submitButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/table/tbody/tr[3]/td/input[1]"));
        submitButton.click();
        output = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/p")).getText();
        correctOutput = "Your search - !This Quote doesnt exist? - did not match any quotes.";
        try{
            Assert.assertEquals(output,correctOutput);
            System.out.println("Test A2 Passed");
        }
        catch(Throwable t){
            System.out.println("Test A2 Failed");
        }
        System.out.println(TestB1(driver));
        System.out.println(TestC1(driver));
        System.out.println(TestD1(driver));
        //end Test A2,B1,C1,D1

        //Begin Test A3
        filterTypeBoth = driver.findElement(By.xpath("//*[@id=\"both\"]"));
        filterTypeBoth.click();
        searchBar = driver.findElement(By.xpath("//*[@id=\"searchText\"]"));
        searchBar.sendKeys("");
        submitButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/table/tbody/tr[3]/td/input[1]"));
        submitButton.click();
        output = "";
        correctOutput = "";
        try{
            Assert.assertEquals(output,correctOutput);
            System.out.println("Test A3 Passed");
        }
        catch(Throwable t){
            System.out.println("Test A3 Failed");
        }
        System.out.println(TestB1(driver));
        System.out.println(TestC1(driver));
        System.out.println(TestD1(driver));
        //End Test A3,B1,C1,D1

        //close browser after all test have been run
        driver.quit();




    }
    public static String TestA1(WebDriver driver){

        String validQuote1;
        validQuote1 = "If you have nothing to say, you don't have to put it into words.\n" +
                "—Alex Brodsky";

        //called the quote Server app for testing
        //pulls in the webpage to test needs full URL
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");

        //maximize the window on the testers display
        driver.manage().window().maximize();
        //captures the seach bar and the sendKeys allows you to input something for it to type in the box
        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"searchText\"]"));

        //capture the submit button and simulate a press the copy xpath command in chrome works well for this path
        WebElement submitButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/table/tbody/tr[3]/td/input[1]"));

        //captures the "both" "author" and "quote" buttons to filter through
        WebElement filterTypeBoth = driver.findElement(By.xpath("//*[@id=\"both\"]"));
        //Begin Test A1,B1,C1,D1 Part1
        //begin setting elements for searching valid quote "both" using string "alex"
        filterTypeBoth.click();
        searchBar.sendKeys("Alex");
        submitButton.click();
        //pulls the output of the server and stores it in a string provided its not a one liner/no results
        String outputQuote = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/dl")).getText();

        try {
            Assert.assertEquals(validQuote1, outputQuote);
            String result = ("Test A1 Passed");
            return result;
        }
        catch(Throwable t){

            String result = "Test A1 Did not Pass";
            return result;

        }//end test Part1

    }//end TestA1

    public static String TestB1(WebDriver driver){
        //called the quote Server app for testing
        //pulls in the webpage to test needs full URL
        String validQuote1;
        validQuote1 = "If you have nothing to say, you don't have to put it into words.\n" +
                "—Alex Brodsky";

        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");

        //maximize the window on the testers display
        driver.manage().window().maximize();
        //captures the seach bar and the sendKeys allows you to input something for it to type in the box
        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"searchText\"]"));

        //capture the submit button and simulate a press the copy xpath command in chrome works well for this path
        WebElement submitButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/table/tbody/tr[3]/td/input[1]"));
        WebElement filterTypeQuote = driver.findElement((By.xpath("//*[@id=\"quote\"]")));

        //begin setting elements for searching a valid quote using the "author" filter text "Alex"
        filterTypeQuote.click();
        searchBar.sendKeys("World");
        submitButton.click();

        String outputQuote = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/dl")).getText();
        try {
            Assert.assertNotEquals(validQuote1, outputQuote);
            String result = "Test B1 Passed";
            return result;
        }
        catch(Throwable t){
            String result = "Test B1 Did not Pass";
            return result;
        }//end test Part2
    }//End TestB1

    public static String TestC1(WebDriver driver){
        String validQuote1 = "If you have nothing to say, you don't have to put it into words.\n" +
                "—Alex Brodsky";


        //begin setting elements for searching a valid quote using the "quote" filter and text "world
         WebElement filterTypeAuthor = driver.findElement((By.xpath("//*[@id=\"quote\"]")));
        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"searchText\"]"));
        WebElement submitButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/table/tbody/tr[3]/td/input[1]"));

        filterTypeAuthor.click();
        searchBar.sendKeys("Alex");
        submitButton.click();
        String outputQuote = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/p")).getText();
        try {
            Assert.assertNotEquals(outputQuote,validQuote1);
            String result = "Test C1 Passed";
            return result;
        }
        catch (Throwable t){
            String result = "Test C1 Failed";
            return result;
        }//end test Part3

    }//End TestC1

    public static String TestD1(WebDriver driver){
        String outputRandomQuote;
        String beforeRandomQuote;

        WebElement randomQuoteButton = driver.findElement(By.xpath("/html/body/form/input"));
        beforeRandomQuote = driver.findElement(By.xpath("/html/body/div")).getText();
        randomQuoteButton.click();
        outputRandomQuote = driver.findElement(By.xpath("/html/body/div")).getText();//pulls text from the box

        try{
            Assert.assertNotEquals(beforeRandomQuote,outputRandomQuote);
            String result = "Test D1 Passed";
            return result;
        }
        catch(Throwable t){
            String result = "Test D1 did not pass";
            return result;
        }//end Test Part4
    }
}
