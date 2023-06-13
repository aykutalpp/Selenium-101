package test.ng;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class DataProviders {
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
        @DataProvider
        public Object [][] getEmailPass(){
            Object [][] data = new Object[3][2];
            //dataset 1
            data[0][0] = "testmest@gmail.com";
            data[0][1] = "testmest123";
            //dataset 2
            data[1][0] = "testcest@hotmail.com";
            data[1][1] = "testcest123";
            //dataset 3
            data[2][0] = "thisMailNotValid";
            data[2][1] = "testkest123";
            return data;
        }
        @Test(dataProvider = "getEmailPass")
        public void signin (String email, String password) {
            driver.get("http://teststore.automationtesting.co.uk/");
            findElement(By.cssSelector("[title='Log in to your customer account']")).click();
            findElement(By.name("email")).sendKeys(email);
            findElement(By.name("password")).sendKeys(password);
            findElement(By.id("submit-login")).click();
            System.out.println("user logged in");
            findElement(By.cssSelector("a[class='logout hidden-sm-down']")).click();

        }
        @AfterSuite
        public void end () {
            driver.close();
            driver.quit();
        }

    }
