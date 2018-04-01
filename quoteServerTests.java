import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class quoteServerTests {

    public static void main(String [] args){
        //path for the chrome driver
        System.setProperty("webdriver.chrome.driver","C:\\SeleniumDrivers\\chromedriver.exe");
        WebDriver driver =new ChromeDriver();   //created instance of Chrome Driver

        //called the quote Server app for testing
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");

        //maximize the window
        driver.manage().window().maximize();

        //Creating 5 tests using for loop
        for( int i=0; i<=5; i++){
            System.out.println("Test "+i+": Started");
            //capture the search bar
            WebElement element = driver.findElement(By.xpath("//input[@name='searchText']"));
            //Enter the value into the search bar
            element.sendKeys("second automated test search by quote");

            //capture the radio button quote
            WebElement radioButton = driver.findElement(By.xpath("//input[@id='quote']"));
            radioButton.click(); //click the quote radio button

            //capture the submit button quote
            WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
            submitButton.click(); //click the quote radio button

            System.out.println("Test "+i+": Passed");
        }


    }
}
