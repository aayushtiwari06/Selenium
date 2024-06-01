package Demo.De;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;


public class TestShoppingDemo {
    WebDriver driver;
    String url = "https://demowebshop.tricentis.com/";

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
      
        
    }

    @Test
    public void test() throws InterruptedException {
        // Click on Jewelry option
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[1]/div[1]/div[2]/ul/li[6]/a")).click();

        // Filter (500-700)
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div[2]/div[2]/div[2]/div/div[2]/ul/li[2]/a")).click();

        // Remove Filter
        driver.findElement(By.className("remove-price-range-filter")).click();

        // Filter (0-500)
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div[2]/div[2]/div[2]/div/div[2]/ul/li[1]/a")).click();

        // Open Product
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div[2]/div[2]/div[3]/div[2]/div/div[2]/h2/a")).click();

        // Add to cart
        driver.findElement(By.id("add-to-cart-button-14")).click();
        Thread.sleep(9000);

        // Shopping cart
        driver.findElement(By.xpath("//*[@id=\"topcartlink\"]/a/span[1]")).click();

        // Agree button
        driver.findElement(By.id("termsofservice")).click();

        // Read TC
        driver.findElement(By.className("read")).click();

        // Handling and closing new opened window
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String win : windowHandles) {
            if (!win.equals(mainWindowHandle)) {
                driver.switchTo().window(win);
                break;
            }
        }
        driver.close();
        // Switching back to main window
        driver.switchTo().window(mainWindowHandle);

        // Complete checkout
        Select country = new Select(driver.findElement(By.id("CountryId")));
        country.selectByVisibleText("India");
        driver.findElement(By.id("checkout")).click();

        // Checkout as guest
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[1]/div[3]/input[1]")).click();

        // Billing details
        driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("John");
        driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("Pa");
        driver.findElement(By.id("BillingNewAddress_Email")).sendKeys("john@gmail.com");
        Select country1 = new Select(driver.findElement(By.id("BillingNewAddress_CountryId")));
        country1.selectByVisibleText("India");
        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Chandigarh");
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("MDC Sector 5");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("138156");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("9044520329");
        driver.findElement(By.xpath("//*[@id='billing-buttons-container']/input")).click();

        Thread.sleep(3000);
        // Shipping address
        driver.findElement(By.xpath("//*[@id=\"shipping-buttons-container\"]/input")).click();
        Thread.sleep(3000);
        
        // Shipping method
        driver.findElement(By.xpath("//*[@id='shipping-method-buttons-container']/input")).click();
        Thread.sleep(3000);

        // Payment method
        driver.findElement(By.xpath("//*[@id='payment-method-buttons-container']/input")).click();
        Thread.sleep(3000);

        // Payment info
        driver.findElement(By.xpath("//*[@id='payment-info-buttons-container']/input")).click();
        Thread.sleep(3000);

        // Confirm order
        driver.findElement(By.xpath("//*[@id='confirm-order-buttons-container']/input")).click();
        Thread.sleep(3000);

        // Validating Success message
        String message = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div/div/div[2]/div/div[1]/strong")).getText();
        String orderID = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div/div/div[2]/div/ul/li[1]")).getText();
        if (message.contains("successfully")) {
            System.out.println(message);
            System.out.println(orderID);
        } else {
            System.out.println("Error placing order");
        }
    }
}
