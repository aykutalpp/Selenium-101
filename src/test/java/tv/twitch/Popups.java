package tv.twitch;

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
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


//copy this template before every new class
public class Popups {
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
    public void testOfPopups () throws InterruptedException {
        driver.get("https://www.automationtesting.co.uk/popups.html");
        findElement(By.cssSelector("[onclick='popup()']")).click();

        TimeUnit.SECONDS.sleep(2);
        // burada mevcut sekmeyi kaydettim popupa zaten geçmemişti driver
        String mainWindow = driver.getWindowHandle();
        // burada tüm sekmeleri handles'a attım
        Set <String> handles = driver.getWindowHandles();
        // ıterator is an interface that used to loop for a collection ı've created an object iterate...
        // and the equals handles which is name of my sets, and it's going to iterate through each...
        // one of those values in the collection.
        Iterator <String> iterate = handles.iterator();
        // while loop itarate'in nexti olana kadar çalışcak.Collection'un sonuna kadar gidecek
        // So child will store the next tab in browser
        while (iterate.hasNext()){
            String child = iterate.next();
            if (!mainWindow.equalsIgnoreCase(child)){
                driver.switchTo().window(child);
                driver.close();
            }
        }
        TimeUnit.SECONDS.sleep(2);
        driver.switchTo().window(mainWindow);

    }
    @After
    public void afterAll () {
        driver.quit();
    }

}
