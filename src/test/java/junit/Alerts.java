package junit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Alerts {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.automationtesting.co.uk/popups.html");
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.cssSelector("button[onclick='alertTrigger()']")).click();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(driver.switchTo().alert().getText());
        TimeUnit.SECONDS.sleep(1);
        driver.switchTo().alert().accept();
        //driver.switchTo().alert().dismiss(); // bu napıyo dene başka alertte
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.cssSelector("a[href='#sidebar']")).click(); //tekrar switch yapmadan sayfada işlem yapabildin
        TimeUnit.SECONDS.sleep(1);


        driver.quit();
    }
}
