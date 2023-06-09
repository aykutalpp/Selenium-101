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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


//copy this template before every new class
public class BrowserTabs {
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
    public void testOfBrowserTabs () throws InterruptedException {
        driver.get("https://www.automationtesting.co.uk/browserTabs.html");
        //3 yeni sekme açtım
        for (int i = 0; i < 3; i++) {
            findElement(By.cssSelector("[value='Open Tab']")).click();
        }
        String mainPage = driver.getWindowHandle();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        System.out.println("There are " + tabs.size() + " tabs opened");

        for (String tab : tabs) {
            driver.switchTo().window(tab);
            TimeUnit.SECONDS.sleep(1);
            if (driver.getWindowHandle().equalsIgnoreCase(tabs.get(1))) {
                findElement(By.name("q")).click();
                findElement(By.name("q")).sendKeys("is it third tab ?");
                TimeUnit.SECONDS.sleep(2);
            }
        }
        for (String tab : tabs) {
            driver.switchTo().window(tab);
            driver.close();
            TimeUnit.SECONDS.sleep(1);
        }
    }
    @After
    public void afterAll () {
        driver.quit();
    }

}
