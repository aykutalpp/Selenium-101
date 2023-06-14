package burada.properties.var;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public static WebDriver driver;

    public static WebDriverWait wait;

    private String url;
    private Properties prop;

    public BasePage () throws IOException {
        prop = new Properties();
        FileInputStream data = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/java/burada/properties/var/config.properties");
        prop.load(data);
    }

    public WebDriver getDriver () {
        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") +"/src/test/java/drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
        } else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    System.getProperty("user.dir") +"/src/test/java/drivers/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            //options.addArguments("start-maximized");
            driver = new FirefoxDriver(options);
        } else if (prop.getProperty("browser").equalsIgnoreCase("edge")){
            System.setProperty("webdriver.edge.driver",
                    System.getProperty("user.dir") +"/src/test/java/drivers/msedgedriver.exe");
            EdgeOptions options = new EdgeOptions();
            options.addArguments("start-maximized");
            driver = new EdgeDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(10));

        return driver;
    }
    public String getUrl() throws IOException {
        url = prop.getProperty("url");
        return url;
    }
    public void takeSnapShot(WebDriver webdriver) throws IOException {
        File srcFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
        File destFile = new File("C:\\Users\\testinium\\Desktop\\ss\\" + timestamp() + ".png");
        FileUtils.copyFile(srcFile, destFile);
    }
    public String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }
}