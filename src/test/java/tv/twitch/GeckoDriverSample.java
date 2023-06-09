package tv.twitch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GeckoDriverSample {
    WebDriver driver ;
    @Before
    public void geckoBefore(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("start-minimized");
        driver = new FirefoxDriver(options);
    }

    @Test
    public void geckoSample () {
        driver.get("https://github.com/mozilla/geckodriver/");
    }
    @After
    public void geckoAfter () {
        driver.close();
    }

}
