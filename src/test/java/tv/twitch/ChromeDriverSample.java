package tv.twitch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class ChromeDriverSample {
    WebDriver driver = new ChromeDriver();
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
