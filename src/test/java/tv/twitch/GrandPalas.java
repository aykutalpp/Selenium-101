package tv.twitch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GrandPalas {
    WebDriver driver;
    WebDriverWait wait ;





    public WebElement findElement(By by){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public List<WebElement> findElements(By by) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    @Before
    public void shouldAnswerWithTrue() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(600), Duration.ofMillis(1000));

    }

    @Test
    public void grandPalasHouseCheck () throws InterruptedException {
        Actions actions = new Actions(driver);
        driver.get("https://www.sahibinden.com/");
        findElement(By.cssSelector("button[id=onetrust-accept-btn-handler]")).click();

        TimeUnit.SECONDS.sleep(5);
        actions.moveByOffset(316,287).click().build().perform();
        findElement(By.cssSelector("a[href='/kategori/emlak']")).click();
        findElement(By.cssSelector("[type='checkbox']")).click();
        findElement(By.cssSelector("a[href='/kiralik']")).click();
        findElement(By.cssSelector("li[data-address='city']")).click();
        findElement(By.cssSelector("li[data-id='10002']")).click();
        findElement(By.cssSelector("li[data-address='town']")).click();
        findElement(By.cssSelector("li[data-id='442']")).click();
        findElement(By.cssSelector("a[class='collapse-pane']")).click();
        findElement(By.cssSelector("[name='price_max']")).sendKeys("9000");
        findElement(By.cssSelector("a[title='Stüdyo (1+0)']")).click();
        findElement(By.cssSelector("a[title='1+1']")).click();
        findElement(By.cssSelector("li>input[name='query_text']")).sendKeys("grand palas");
        findElement(By.cssSelector("button[class='btn btn-block search-submit']")).click();
        findElement(By.cssSelector("a[title='Fiyat']")).click();

        List <WebElement> prices = findElements(By.cssSelector("div[class='classified-price-container']"));
        int lowest = 9999;
        for (WebElement price : prices) {
            String strPrice = price.getText();
            Integer fiyat = Integer.parseInt(strPrice.replaceAll("[^\\d.]", ""));
            if (fiyat < lowest) {
                lowest = fiyat;
            }
        }
    }
    @Test
    public void mouseClickByCordinate () throws InterruptedException {
        Actions actions = new Actions(driver);
        driver.get("https://jspaint.app/#local:a057631f76ef");
        TimeUnit.SECONDS.sleep(5);
        actions.moveByOffset(100,100)
                .clickAndHold()
                .moveByOffset(50, 0)
                .moveByOffset(0, 50)
                .moveByOffset(-50, 0)
                .moveByOffset(0, -50)
                .release()
                .perform();
        TimeUnit.SECONDS.sleep(500);

    }


    @After
    public void tearDown () {
        driver.quit();
    }
}
