package junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AppTest {
    WebDriver driver; // bunu globalde tanımladık çünkü after da görsün
    WebDriverWait wait ; // dinamik beklemeler için

    // [id^='p-']        == id si p- ile başlayanları seçen css selector
    // a[href^="https"]  == Selects every <a> element whose href attribute value begins with "https"
    // a[href$=".pdf"]   == Selects every <a> element whose href attribute value ends with ".pdf"
    // a[href*="w3schools"] == Selects every <a> element whose href attribute value contains the substring "w3schools"

    public WebElement findElement(By by){ // bu fonk ile tüm find elementlerin öncesine presence olana kadar bekleme koymuş olduk
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public  List<WebElement> findElements(By by) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
    @Before
    public void shouldAnswerWithTrue() {
        ChromeOptions options = new ChromeOptions();
        /* buraya ekleyince direkt full-screen açılır diğeri amele işi, chrome direkt pluginle açılsın
        vs. istiyorsan yine buraya ekle  */

        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);  //mor olunce globalden geliyor demektir.
        wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(10));
        driver.get("https://www.google.com/");
    }
    @Test
    public void helloWorld() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        WebElement element = findElement(By.name("q"));
        System.out.println("Element maxlength value = "+ element.getAttribute("maxlength")); //o elementin maxlength attribute'unu aldık
        element.sendKeys("Testinium" + Keys.ENTER);
    }
    @Test
    public void actionsTestto () throws InterruptedException {
        driver.navigate().to("https://www.hepsiburada.com");
        TimeUnit.SECONDS.sleep(5);
        Actions actions = new Actions(driver);
        findElement(By.id("onetrust-accept-btn-handler"))
                .click();
        actions.moveToElement(driver.findElement(By.cssSelector("span[href='javascript:;']")))// burada mouse u oraya sürükledim
                .build()
                .perform();
        actions.moveToElement(driver.findElement(By.cssSelector("span[href='javascript:;']")))
                .clickAndHold()
                .pause(2000)
                .click()
                .build()
                .perform();
        TimeUnit.SECONDS.sleep(5);
    }
    @Test
    public void switchTabs () throws InterruptedException {
        driver.navigate().to("https://demoqa.com/browser-windows");
        String currentWindow = driver.getWindowHandle();
        findElement(By.id("tabButton")).click();

        Set <String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles){
            if (!currentWindow.equals(window)){
                driver.switchTo().window(window);
            }
        }TimeUnit.SECONDS.sleep(5);

        System.out.println("New window text = " + driver.findElement(By.id("sampleHeading")).getText());
        driver.close(); // yeni açılan sekmeyi kapattım
        TimeUnit.SECONDS.sleep(5);
        driver.switchTo().window(currentWindow); //eski sekmeme geri döndüm

        driver.findElement(By.id("messageWindowButton")).click();


    }
    @Test
    public void enOnBirSelect () throws InterruptedException {
        driver.navigate().to("https://www.n11.com/uye-ol");
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.id("myLocation-close-info")).click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.cssSelector("span.btn.btnBlack.close")).click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.cssSelector
                ("[class='inputfield buyerAgreement ']>[class='checkbox']")).click(); // user agreement
        TimeUnit.SECONDS.sleep(1);

        Select gun = new Select(driver.findElement(By.id("birthDay")));
        gun.selectByVisibleText("7");
        Select ay = new Select(driver.findElement(By.id("birthMonth")));
        //ay.selectByVisibleText("Aralık"); //bu şekilde de aralık seçilebilir
        //ay.selectByValue("12"); //bu şekilde de aralık seçilebilir
        ay.selectByIndex(11); //bu şekilde kasım seçilebilir
        Select yil = new Select(driver.findElement(By.id("birthYear")));
        yil.selectByVisibleText("1996");
        TimeUnit.SECONDS.sleep(5);


    }
    @Test
    public void sendKeysOrnek (){
        driver.get("https://www.automationtesting.co.uk/contactForm.html");
        findElement(By.name("first_name")).sendKeys("Aykut");
        findElement(By.name("last_name")).sendKeys("Alp");
        findElement(By.name("email")).sendKeys("aykutalpp@gmail.com");
        findElement(By.name("message")).sendKeys("Hellöööğğğ");
        findElement(By.cssSelector("div[id=form_buttons].text-center>input[type='submit']")).click();

    }
    @Test
    public void selectorLinkTextOrnek (){
        driver.get("https://www.automationtesting.co.uk/");
        findElement(By.cssSelector(".close-cookie-warning")).click();
        String pageTitle = driver.getTitle();
        Assert.assertEquals("Homepage",pageTitle);
        findElement(By.cssSelector("a[href='#sidebar']")).click();
        findElement(By.linkText("ACTIONS")).click();
    }
    @Test
    public void bundle404errorAssert () {
        driver.get("https://www.bundle.app/gundem/goldman-sachstan-yeni-dolar/tl-tahmini-ve-mehmet-simsek-yorumu-7f34d39f-266d-4456-9538-fdda5c08e73b");
        String error = findElement(By.cssSelector("h1[class='notfoundText']")).getText();
        Assert.assertEquals("Sayfa bulunamadı",error);
    }
    @Test
    public void hiddenElement () {
        driver.get("https://www.automationtesting.co.uk/hiddenElements.html");
        System.out.println(findElement(By.cssSelector("p[hidden]")).isDisplayed());
    }
    @Test
    public void trendyolAykut (){
        driver.get("https://www.trendyol.com/koleksiyonlar/Aykut-k-33ae05fe-578f-485f-8243-ab8b808212bc?shared=true&adjust_t=mk12x3o_m4o2h83&adjust_tracker_limit=250000&link_userID=17517619&utm_term=17517619&adjust_campaign=ios_collection_free&utm_source=share&utm_medium=ios_collection_free&utm_campaign=33ae05fe-578f-485f-8243-ab8b808212bc&link_collectionID=33ae05fe-578f-485f-8243-ab8b808212bc");
        double lowest = 99999d;
        List<WebElement> priceElements = driver.findElements(By.cssSelector(".prc-box-dscntd"));
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText();
            System.out.println("Price: " + priceText);
            String priceWithoutCurrency = priceText.replace(",", ".");
            double price = Double.parseDouble(priceWithoutCurrency.replaceAll("[^\\d.]", ""));
            if (price<lowest){
                lowest = price;
            }
        }
        System.out.println("Lowest price in the list = "+ lowest + " TL"); //ilerde hashmapli yapıp ürünü de yazdır

        Assert.assertEquals("Aykut", findElement(By.cssSelector(".user-name")).getText());


    }
    @Test
    public void loaderPage () {
        driver.get("https://www.automationtesting.co.uk/loader.html");
        //driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS); // presence olana kadar zaten bekliyoz
        // ama burda extradan wait koyduk çünkü siteye 5 sn sonra açılcak şekilde koymuşlar - Implicit wait
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='loaderBtn']")));
        //explicit wait = visible olana kadar bekledik
        findElement(By.cssSelector("button[id='loaderBtn']")).click();
    }
    @Test
    public void loaderPageTwo (){
        driver.get("https://www.automationtesting.co.uk/loadertwo.html");
        String textInPage = findElement(By.id("appears")).getText();
        System.out.println(textInPage);
    }

    @Test
    public void flypgsSelectFromListWithContains () throws InterruptedException {
        driver.get("https://www.flypgs.com/");
        findElement(By.id("nxm2CookieSubmitButton")).click();
        findElement(By.cssSelector("div[id=nxm-360-departure]")).click();
        findElement(By.id("deperture-input")).sendKeys("London");
        TimeUnit.SECONDS.sleep(1);

        List<WebElement> origins = findElements(By.cssSelector("li[data-name^='Lond']>div>div[class='nxm-360-search-select-airpot-list-area-port']"));

        for (WebElement origin:origins){
            if (origin.getText().contains("Stans")){
                origin.click();
            }
        }

        findElement(By.cssSelector("div[id='nxm-360-arrival")).click();
        findElement(By.id("arrival-input")).sendKeys("Ant");
        TimeUnit.SECONDS.sleep(1);

        List<WebElement> cities = findElements(By.cssSelector("li[data-name*='Antalya']>div>div[class='nxm-360-search-select-airpot-list-area-port']"));

        for (WebElement city:cities){
            if (city.getText().contains("Ant")){
                city.click();
            }
        }
    }
    @Test
    public void iframes () {
        driver.get("https://www.automationtesting.co.uk/iframes.html");
        driver.switchTo().frame(0);

    }
    @Test
    public void isEnabledButton () {
        driver.get("https://www.automationtesting.co.uk/buttons.html");
        System.out.println(findElement(By.id("btn_four")).isDisplayed()); // hidden elementi test edebilirsin
        System.out.println(findElement(By.id("btn_four")).isEnabled());
        System.out.println(findElement(By.id("btn_four")).isSelected());
        Assert.assertEquals(false , findElement(By.id("btn_four")).isEnabled());
        //burada tuşun enable olup olmadığına göre testi fail edebildim
    }
    @After
    public void tearDown () {
       // TimeUnit.SECONDS.sleep(5);
        driver.close(); // ilgili son sekmeyi kapatır.
        driver.quit();  // chrome driver'ını kill ediyor.
    }
}
