import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AutoItTest {

    @Test
    public void autoItTest() throws IOException, InterruptedException {
        String filePath = "d:/books/JAVA/CoursesDZ/_Automation/AutoIT/winauth.exe";

        Runtime rt = Runtime.getRuntime();
        WebDriver driver = new InternetExplorerDriver();
        driver.manage().window().maximize();

        driver.get("http://the-internet.herokuapp.com/");

        rt.exec(filePath + " admin" + " admin");
        driver.findElement(By.linkText("Basic Auth")).click();
        Assert.assertTrue(driver.getPageSource().contains("Congratulations! You "));
    }
}
