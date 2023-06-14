package burada.properties.var;


import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;

public class LoginTest extends BasePage {

    public LoginTest() throws IOException {
    }
    @BeforeSuite
    public void setup () throws IOException {
        driver = getDriver();
        driver.get(getUrl());

    }
    @Test
    public void signIn() throws IOException {

        driver.findElement(By.cssSelector("[title='Log in to your customer account']")).click();
        driver.findElement(By.name("email")).sendKeys("asd@asd.com");
        driver.findElement(By.name("password")).sendKeys("asdasd");
        driver.findElement(By.id("submit-login")).click();

    }
    @AfterMethod
    public void signOut() {
        driver.get("http://teststore.automationtesting.co.uk/");
        driver.findElement(By.cssSelector("a[class='logout hidden-sm-down']")).click();
    }
    @AfterSuite
    public void end() {
        driver.quit();
    }
}
