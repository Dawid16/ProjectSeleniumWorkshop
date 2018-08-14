import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dawidek on 2018-08-03.
 */
public class test1 {

    @Test
    public void checkItemsDiscounted() throws InterruptedException {

        String discountedItems = ".//span[@class='price-percent-reduction']";
        String allItems = ".//div[@class='right-block']";
        String itemsContener = "//ul[@class='product_list grid row']";
        String oldPriceField = ".//span[@class='old-price product-price']";
        String currentPriceField = ".//span[@class='price product-price']";
        String discountField = ".//span[@class='price-percent-reduction']";

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.navigate().to("http://automationpractice.com/index.php");

        WebElement dressesButton = driver.findElement(By.xpath("//ul[contains(@class, 'sf')]/*[2]"));
        dressesButton.click();


        WebElement contener = driver.findElement(By.xpath(itemsContener));

        List<WebElement> allDressess = contener.findElements(By.xpath(allItems));

        List<WebElement> allDiscounted = new ArrayList<>();
        WebElement discountOfItemField = null;

        for(WebElement element : allDressess){
            if(element.findElements(By.xpath(discountedItems)).size() != 0){

                discountOfItemField = element.findElement(By.xpath(discountedItems));
                //System.out.println(element.getText());

                double oldPrice = Double.parseDouble(element.findElement(By.xpath(oldPriceField)).getText().substring(1));
                System.out.println(oldPrice);

                double currentPrice = Double.parseDouble(element.findElement(By.xpath(currentPriceField)).getText().substring(1));
                System.out.println(currentPrice);

                String discountHeader = element.findElement(By.xpath(discountField)).getText();
                double discount = Double.parseDouble(discountHeader.substring(1,discountHeader.indexOf("%")));
                System.out.println(discount);

                System.out.println("Assertion check for " + element.getText());
                Assert.assertEquals(currentPrice, oldPrice - (0.01 * oldPrice * discount), 0.01);

            }
            allDiscounted.add(discountOfItemField);
        }


    }


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
        Thread.sleep(1000);

        addToCartButton.click();
        Thread.sleep(1000);

        proceedToCheckoutButton.click();
        Thread.sleep(1000);

        WebElement proceedToCheckoutButton2 = driver.findElement(By.xpath(
                "//p[@class='cart_navigation clearfix']//span"));

        proceedToCheckoutButton2.click();

        WebElement createAnAccountText = driver.findElement(By.xpath("//h3[contains(text(),'Create an account')]"));
        Assert.assertTrue(createAnAccountText.isDisplayed());

        driver.quit();

    }

    @Test
    public void test3(){

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("http://automationpractice.com/index.php");

        List<WebElement> links = driver.findElements(
                By.xpath("//ul[@id='homefeatured']/li"));

        Iterator<WebElement> iter = links.iterator();

        while(iter.hasNext()) {
            WebElement we = iter.next().findElement(By.xpath(".//h5/a[@class='product-name']"));

                System.out.println(we.getText());
        }

        driver.quit();

    }


    @Test
    public void test4() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.navigate().to("http://automationpractice.com/index.php");

        WebElement dress = driver.findElement(By.cssSelector(
                    "[id='homefeatured'] h5 a[href*='id_product=3']"));

        dress.click();

        WebElement addToCartButton = driver.findElement(By.xpath(
                "//span[contains(text(),'Add to cart')]"));

        addToCartButton.click();

        Thread.sleep(1000);

        WebElement check = driver.findElement(By.xpath("//a[@title='Proceed to checkout']//span"));

        wait.until(ExpectedConditions.visibilityOf(check));

        Assert.assertTrue(check.isDisplayed());













    }



    @Test
    public void test5() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.navigate().to("http://automationpractice.com/index.php");


        WebElement dresses = driver.findElement(By.xpath("//ul[contains(@class, 'sf')]/*[2]"));
        dresses.click();

        Thread.sleep(2000);

        List<WebElement> listOfDressesDiscouned = driver.findElements(By.xpath(
                "//div[@class='right-block']//span[@class='price-percent-reduction']"));
        List<WebElement> listOfDressesDiscounedNormal = driver.findElements(
                By.xpath("//div[@class='right-block']//span[@class='old-price product-price']"));
        List<WebElement> all5elements = driver.findElements(
                By.xpath("//div[@class='right-block']"));


        for(int i = 0; i<listOfDressesDiscouned.size(); i++){

            double discount = Double.parseDouble(listOfDressesDiscouned.get(i).getText().substring(1, listOfDressesDiscouned.get(i).getText().indexOf("%")));
            System.out.println(discount);

            double normalPrice = Double.parseDouble(listOfDressesDiscounedNormal.get(i).getText().substring(1, listOfDressesDiscounedNormal.get(i).getText().length()));
            System.out.println(normalPrice);


            if(i==0) {
                Assert.assertEquals(28.98, normalPrice - (0.01 * normalPrice * discount), 0.01);
            } else if (i==1){
                Assert.assertEquals(16.40, normalPrice - (0.01 * normalPrice * discount), 0.01);
            }

        }
        

        /*
        for (WebElement element : listOfDressesDiscouned){
            int discount = Integer.parseInt(element.getText().substring(0, element.getText().indexOf("%")));
            System.out.println(discount);

            int normalPrice = Integer.parseInt(element.getText().substring(element.getText().indexOf("$"), element.getText().length()));
            System.out.println(discount);
        }*/

        //div[@class='right-block']//span[@class='old-price product-reduction']














    }








}
