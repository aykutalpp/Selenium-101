package tv.twitch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GeckoDriverSample {
    WebDriver driver = new FirefoxDriver();
    @Before
    public void geckoBefore(){

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
