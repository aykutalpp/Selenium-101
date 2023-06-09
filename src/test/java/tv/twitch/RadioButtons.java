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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


//copy this template before every new class
public class RadioButtons {
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
    public void testOfRadioButtons () {
        driver.get("https://www.automationtesting.co.uk/dropdown.html#");

        for (int i = 0; i < 8; i++) {
            findElement(By.cssSelector("[for='demo-priority-low']")).click();
            findElement(By.cssSelector("[for='cb_red']")).click();
            findElement(By.cssSelector("[for='demo-priority-normal']")).click();
            findElement(By.cssSelector("[for='cb_green']")).click();
            findElement(By.cssSelector("[for='demo-priority-high']")).click();
            findElement(By.cssSelector("[for='cb_blue']")).click();
            Select cars = new Select(findElement(By.id("cars")));
            cars.selectByIndex(i);
        }

    }
    @After
    public void afterAll () {
        driver.quit();
    }

}
