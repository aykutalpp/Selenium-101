package junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class ChromeDriverChromeOptions {

    WebDriver driver ;

    @Before
    public void chromeBefore(){
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME ,"Aykut"); // bir işe yaramadı ?

        ChromeOptions options = new ChromeOptions();
        options.merge(cap);
        options.setAcceptInsecureCerts(true); // güvenli değil sertifika vs. geçer
        options.addArguments("start-maximized"); // tam ekran açar
        options.addArguments("use-fake-device-for-media-stream"); // fake kamera açar
        options.addArguments("use-fake-ui-for-media-stream"); // siteye fake ses gönderir (mic test)
        //options.addArguments("--incognito"); // gizli sekmede açar
        //options.addArguments("--headless"); // arkada çalışır hiç açılmaz
        //options.addArguments("--remote-allow-origins=*"); //workaround for not released up to date chromedriver
        //options.setPageLoadStrategy(PageLoadStrategy.EAGER); //workaround for cookie timeouts
        //options.addArguments("--disable-infobars"); // çalışmadı

        driver = new ChromeDriver(options);


    }

    @Test
    public void chromeSample () throws InterruptedException {
        driver.get("https://www.google.com/");
        TimeUnit.SECONDS.sleep(2);
        //driver.findElement(By.cssSelector("div[onmousedown='isKeyPressed(event)']>div>div>p[id='doubClickStartText']")).click();
        WebElement elem = driver.findElement(By.cssSelector(".FPdoLc [value='Google\\'da Ara']"));
        System.out.println(elem.getAttribute("innerHTML"));
    }
    @Test
            public void cameraAndVoiceFake () throws InterruptedException {
        driver.get("https://webcamtests.com/check");
        Thread.sleep(5000);
        driver.findElement(By.id("webcam-launcher")).click();
        Thread.sleep(10000);

        driver.get("https://www.vidyard.com/mic-test/");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button[class='btn btn-white test-button']")).click();
        Thread.sleep(3000);

        driver.manage().deleteAllCookies(); // cookieleri siler
    }
    @Test
    public void desiredCapabilitiesDeneme () throws InterruptedException {
        driver.get("https://expired.badssl.com");
        //driver.get("https://www.google.com/");
        TimeUnit.SECONDS.sleep(3);
    }
    @After
    public void chromeAfter () {
        driver.quit();
    }

}
