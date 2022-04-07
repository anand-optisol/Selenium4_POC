import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Screenshot {

    public WebDriver driver;

    @Test
    public void Completepagescreenshot() throws IOException, InterruptedException {

        //Initiating Driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.google.com/");
        driver.manage().window().maximize();

        TakesScreenshot Ts = (TakesScreenshot) driver;
        File src = Ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("Home.jpg");

        FileUtils.copyFile(src,dest);

        Thread.sleep(5000);

        driver.quit();


    }

    @Test(priority = 1)

    public void Specificelementscreenshot() throws IOException, InterruptedException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //To open a new tap in the same browser
        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://devops-test.d2xm4odj59p3vm.amplifyapp.com/login");
        driver.manage().window().maximize();

        WebElement logo = driver.findElement(By.xpath("//div[@class='container-fluid']/img"));

        File src = logo.getScreenshotAs(OutputType.FILE);

        File dest = new File("logo.jpg");

        FileUtils.copyFile(src,dest);

        Thread.sleep(5000);

        driver.quit();

    }

    @Test(priority = 2)
    public void particularsectionscreenshot() throws IOException, InterruptedException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //To open a new window
        driver.switchTo().newWindow(WindowType.WINDOW);

        driver.get("https://devops-test.d2xm4odj59p3vm.amplifyapp.com/login");
        driver.manage().window().maximize();

        WebElement logo = driver.findElement(By.xpath("//div[@class='row']"));

        File src = logo.getScreenshotAs(OutputType.FILE);

        File dest = new File("Section.jpg");

        FileUtils.copyFile(src,dest);

        Thread.sleep(5000);

        driver.quit();

    }

    public void sample(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.navigate().to("https://www.google.com/");

        //To open a new tap in the same browser
        WebDriver NewTab = driver.switchTo().newWindow(WindowType.TAB);
        NewTab.get("https://www.google.com/");

        //To open a new window
        WebDriver NewWindow = driver.switchTo().newWindow(WindowType.WINDOW);
        NewWindow.get("https://www.google.com/");


    }
}
