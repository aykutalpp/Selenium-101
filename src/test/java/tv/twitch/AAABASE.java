package tv.twitch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


//copy this template before every new class
public class AAABASE {
    WebDriver driver;
    WebDriverWait wait;

    public WebElement findElement(By by){ // bu fonk ile tüm find elementlerin öncesine presence olana kadar bekleme koymuş olduk
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public List<WebElement> findElements(By by) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
    @Before
    public void beforeAll () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(10));
    }
    @Test
    public void testOf () {
        driver.get("");

    }
    @After
    public void afterAll () {
        driver.quit();
    }

}
