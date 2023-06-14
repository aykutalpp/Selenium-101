package test.ng;

import burada.properties.var.BasePage;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ITestListenerSample extends BasePage implements ITestListener  {

    public ITestListenerSample() throws IOException {
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test succeed");
    }

    public void onTestStart(ITestResult result) {
        System.out.println("Test started");
    }

    public void onTestFailure(ITestResult result) {
        try {
            takeSnapShot(driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Test failed");
    }


}
