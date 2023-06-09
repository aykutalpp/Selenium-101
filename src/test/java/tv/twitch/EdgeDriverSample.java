package tv.twitch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class EdgeDriverSample {
    WebDriver driver ;
    @Before
    public void edgeBefore(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--disable-infobars");
        driver = new EdgeDriver(options);

    }
    @Test
    public void edgeSample () throws InterruptedException {
        driver.get("https://github.com/mozilla/geckodriver/");
        TimeUnit.SECONDS.sleep(10);
        Thread.sleep(3000);
    }
    @After
    public void edgeAfter () {
        driver.quit();
    }

}
