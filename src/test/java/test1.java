import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Dawidek on 2018-08-03.
 */
public class test1 {


    /*
    @Test
    public void openTestPage(){

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("http://automationpractice.com/index.php");
        Assert.assertEquals("Wrong page title", "My Store", driver.getTitle());
        driver.quit();

    }*/

    @Test
    public void searchForProduct(){

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("http://automationpractice.com/index.php");
        Assert.assertEquals("Wrong page title", "My Store", driver.getTitle());

        WebElement searchField = driver.findElement(By.id("search_query_top"));

        searchField.clear();
        searchField.click();

        searchField.sendKeys("Faded Short");

        WebElement lupa = driver.findElement(By.name("submit_search"));
        lupa.click();

        driver.quit();

    }


    @Test
    public void checkRedirectionDuringOrder() throws Exception{

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Actions actions = new Actions(driver);

        driver.navigate().to("http://automationpractice.com/index.php");
        Assert.assertEquals("Wrong page title", "My Store", driver.getTitle());

        WebElement dress = driver.findElement(By.xpath(
                "//ul[@id='homefeatured']//li[contains(@class, 'first-in-line first')]"));
        WebElement addToCartButton = driver.findElement(By.xpath(
                "//ul[@id='homefeatured']/li[contains(@class,'first-in-line first')]//span[text()='Add to cart']"));
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath(
                "//a[@title='Proceed to checkout']"));


        actions.moveToElement(dress).build().perform();
        Thread.sleep(2000);

        addToCartButton.click();
        Thread.sleep(2000);

        proceedToCheckoutButton.click();
        Thread.sleep(2000);

        WebElement proceedToCheckoutButton2 = driver.findElement(By.xpath(
                "//p[@class='cart_navigation clearfix']//span"));

        proceedToCheckoutButton2.click();

        WebElement createAnAccountText = driver.findElement(By.xpath("//h3[contains(text(),'Create an account')]"));
        Assert.assertTrue(createAnAccountText.isDisplayed());

        driver.quit();

    }

}
