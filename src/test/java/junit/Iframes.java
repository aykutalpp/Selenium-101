package junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


//copy this template before every new class
public class Iframes {
    WebDriver driver;
    WebDriverWait wait;

    public WebElement findElement(By by){
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
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
    public void testOfIframes () throws InterruptedException {
        driver.get("https://www.automationtesting.co.uk/iframes.html");
        // ilk framedeye geçip işlem yaptım
        driver.switchTo().frame(0);
        findElement(By.cssSelector("a[href='#sidebar'")).click();
        TimeUnit.SECONDS.sleep(2);

        //ana frame'e döndüm
        driver.switchTo().parentFrame();
        findElement(By.cssSelector("a[href='#sidebar'")).click();
        findElement(By.cssSelector("a[href='#sidebar'")).click();

        //ikinci frame e geçip işlem yaptım
        driver.switchTo().frame(1);
        findElement(By.cssSelector("[class='ytp-large-play-button ytp-button ytp-large-play-button-red-bg']")).click();
        TimeUnit.SECONDS.sleep(2);

    }
    @After
    public void afterAll () {
        driver.quit();
    }

}
