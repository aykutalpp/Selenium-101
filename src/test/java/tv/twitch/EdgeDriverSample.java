package tv.twitch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EdgeDriverSample {
    WebDriver driver = new EdgeDriver();
    @Before
    public void edgeBefore(){
    }
    @Test
    public void edgeSample () {
        driver.get("https://github.com/mozilla/geckodriver/");
    }
    @After
    public void edgeAfter () {
        driver.quit();
    }

}
