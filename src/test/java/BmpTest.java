import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BmpTest {

    @Test
    public void bpmExamplesTest() throws Exception {
        ProxyServer proxyServer = new ProxyServer(8888);
        proxyServer.start();
        proxyServer.autoBasicAuthorization("", "admin", "admin");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PROXY, proxyServer.seleniumProxy());

        WebDriver driver = new FirefoxDriver(caps);

        driver.get("http://the-internet.herokuapp.com/");
        driver.findElement(By.linkText("Basic Auth")).click();

        Assert.assertTrue(driver.getPageSource().contains("Congratulations! You "));

        driver.quit();
        proxyServer.stop();
    }
}
