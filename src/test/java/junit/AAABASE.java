package junit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;


//copy this template before every new class
public class AAABASE {
    WebDriver driver;
    WebDriverWait wait;

    public WebElement findElement(By by){
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public List<WebElement> findElements(By by) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
    public static void takeSnapShot(WebDriver webdriver) throws IOException {
        File srcFile = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
        File destFile = new File("C:\\Users\\testinium\\Desktop\\ss\\"+timestamp()+".png");

        FileUtils.copyFile(srcFile,destFile);
    }
    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
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
        driver.get("https://www.google.com.tr");

    }
    @After
    public void afterAll () {
        driver.quit();
    }

}
