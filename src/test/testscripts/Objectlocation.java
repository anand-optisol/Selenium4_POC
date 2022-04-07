import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Objectlocation {

    public WebDriver driver;

    @Test
    public void getobjectlocation() throws IOException, InterruptedException {

        //Initiating Driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://devops-test.d2xm4odj59p3vm.amplifyapp.com/login");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        WebElement logo = driver.findElement(By.xpath("//div[@class='container-fluid']/img"));

        System.out.println("Height" + logo.getRect().getDimension().getHeight());
        System.out.println("Width" + logo.getRect().getDimension().getWidth());

        System.out.println("X-Location" + logo.getRect().getX());
        System.out.println("Y-Location" + logo.getRect().getY());
    }
}
