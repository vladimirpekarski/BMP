import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class UploadFileTest {

    @Test
    public void uploadFileTest() {
        String relPath = getClass().getProtectionDomain().getCodeSource()
                .getLocation().getFile();

        WebDriver driver = new FirefoxDriver();
        File file = new File(relPath + "../../src/java/resources/2.png");

        driver.get("http://the-internet.herokuapp.com");
        driver.findElement(By.linkText("File Upload")).click();
        driver.findElement(By.id("file-upload")).sendKeys(file.getPath());
        driver.findElement(By.id("file-submit")).click();

        Assert.assertTrue(driver.getPageSource().contains("2.png"));
    }
}
