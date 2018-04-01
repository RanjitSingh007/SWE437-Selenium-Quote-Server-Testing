import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class quoteServerTests {

    public static void main(String [] args){
        //path for the chrome driver
        //this current path is for my Mac Change for windows
        System.setProperty("webdriver.chrome.driver","/Users/AhmadA/Desktop/chromedriver2");
        WebDriver driver =new ChromeDriver();   //created instance of Chrome Driver

        //called the quote Server app for testing
        //pulls in the webpage to test needs full URL
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");

        //maximize the window on the testers display
        driver.manage().window().maximize();

        //begin test 1 checking against a valid quote search
        String quotes[] = new String[3];
        quotes[0] = "If you have nothing to say, you don't have to put it into words.\n" +
                "—Alex Brodsky";
        //captures the seach bar and the sendKeys allows you to input something for it to type in the box
        WebElement searchBar = driver.findElement(By.xpath("//input[@name= 'searchText']"));
        searchBar.sendKeys("Alex");

        //capture the submit button and simulate a press the copy xpath command in chrome works well for this path
        WebElement submitButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/table/tbody/tr[3]/td/input[1]"));
        submitButton.click();

        //should pull the printed results and seems to be different path for those that are longer then one line
        WebElement printedText = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/dl"));

        //pulls the output of the server and stores it in a string
        String output = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/dl")).getText();

        //prints the quote that is being tested against
        System.out.println(output);
        //uses assertequals to ensure the output is as expected, if it throws an exception ie not equal, we catch and
        //return that it failed the test
        try {
            Assert.assertEquals(quotes[0], output);
            System.out.println("Test Passed");
        }
        catch(Throwable t){
            System.out.println("Test Failed");

        }

        //begin test for random quote pulls in the button and clicks it to obtain another quote
        //we will check it against the previous quote to ensure that its not the same
        String beforeRandom = driver.findElement(By.xpath("/html/body/div")).getText();
        //print the output to console to verify by hand
        System.out.println(beforeRandom);

        //click the random quote button
        WebElement randomQuote = driver.findElement(By.xpath("/html/body/form/input"));
        randomQuote.click();

        //pulls the output of the random quote after the button is clicked
        WebElement randomQuoteOutput = driver.findElement(By.xpath("/html/body/div"));
        String output2 = randomQuoteOutput.getText();
        //print the output to verify by hand
        System.out.println(output2);

        try {
            Assert.assertNotEquals(beforeRandom, randomQuoteOutput);
            System.out.println("Test Passed");
        }
        catch (Throwable t){
            System.out.println("Test Failed");
        }


        driver.quit();


        //ranjits old code, could still be used as a template to understand things
        //Creating 5 tests using for loop
        /*for( int i=0; i<=5; i++){
            System.out.println("Test "+i+": Started");
            //capture the search bar
            WebElement element = driver.findElement(By.xpath("//input[@name='searchText']"));
            //Enter the value into the search bar
            //element.sendKeys("second automated test search by quote");
            element.sendKeys("");//give it an empty string

            //capture the radio button quote
            WebElement radioButton = driver.findElement(By.xpath("//input[@id='quote']"));
            radioButton.click(); //click the quote radio button

            //capture the submit button quote
            //uses the IDs that are in the source html page.
            WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
            submitButton.click(); //click the quote radio button


            System.out.println("Test "+i+": Passed");
        }*/
        //


    }
}
