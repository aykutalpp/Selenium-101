package tv.twitch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class ChromeDriverChromeOptions {

    WebDriver driver ;

    @Before
    public void edgeBefore(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // tam ekran açar
        //options.addArguments("--incognito"); // gizli sekmede açar
        //options.addArguments("--headless"); // arkada çalışır hiç açılmaz
        //options.addArguments("--disable-infobars"); // çalışmadı

        driver = new ChromeDriver(options);


    }

    @Test
    public void edgeSample () throws InterruptedException {
        driver.get("https://www.google.com/");
        TimeUnit.SECONDS.sleep(2);
        //driver.findElement(By.cssSelector("div[onmousedown='isKeyPressed(event)']>div>div>p[id='doubClickStartText']")).click();
        WebElement elem = driver.findElement(By.cssSelector(".FPdoLc [value='Google\\'da Ara']"));
        System.out.println(elem.getAttribute("innerHTML"));
    }
    @After
    public void edgeAfter () {
        driver.quit();
    }

}
