package test.ng;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class AssertTestNG {
    //copy this template before every new class
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
        @BeforeSuite
        public void setup () {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(10));
        }
        @Test
        public void signin () {
            driver.get("http://teststore.automationtesting.co.uk/");
            findElement(By.cssSelector("[title='Log in to your customer account']")).click();
            findElement(By.name("email")).sendKeys("asd@asd.com");
            findElement(By.name("password")).sendKeys("asdasd");
            findElement(By.id("submit-login")).click();
            String alp = findElement(By.cssSelector("a>span[class='hidden-sm-down']")).getText();
            Assert.assertEquals(alp,"Aykut Kıvrak");
        }
        @AfterSuite
        public void end () {
            driver.close();
            driver.quit();
        }
    }
