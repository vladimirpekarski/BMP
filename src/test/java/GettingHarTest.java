import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.File;

public class GettingHarTest {
    @Test
    public void gettingHar() throws Exception {

        ProxyServer proxyServer = new ProxyServer(8888);
        proxyServer.start();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PROXY, proxyServer.seleniumProxy());

        WebDriver driver = new FirefoxDriver(caps);

        proxyServer.newHar("onliner");

        driver.get("http://www.onliner.by");

        Har har = proxyServer.getHar();

        for(HarEntry entry : har.getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            HarResponse response = entry.getResponse();

            System.out.println(request.getUrl() + " : " + response.getStatus() + ", " +
                    entry.getTime() + "ms");


        }

        proxyServer.stop();
        har.writeTo(new File("D:\\some.har")); //ToDo save in target folder
    }
}
