import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Relativelocators {

    WebDriver driver;

    @Test
    public void locators(){

        //Initiating Driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://automationbookstore.dev/");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

        //Test Book5 is located leftof Book6 belowof Book1

        WebElement book5 = driver.findElement(RelativeLocator.with(By.tagName("li")).toLeftOf(By.id("pid6")).below(By.id("pid1")));

        String id = book5.getAttribute("id");

        System.out.println(id);

        Assert.assertEquals("pid5",id);

        //Test Book2 is located above Book6 rightof Book1

        WebElement book2 = driver.findElement(RelativeLocator.with(By.tagName("li")).toRightOf(By.id("pid1")).above(By.id("pid6")));

        String id1 = book2.getAttribute("id");

        System.out.println(id1);

        Assert.assertEquals("pid2",id1);

        //Test Book1 is located near by Book2

        WebElement book1 = driver.findElement(RelativeLocator.with(By.tagName("li")).near(By.id("pid5")));

        String id2 = book1.getAttribute("id");

        System.out.println(id2);

        //Assert.assertEquals("pid2",id2);

    }




    //Test Book5 is located below Book1

    WebElement BelowBook1 = driver.findElement(RelativeLocator.with(By.tagName("li")).below(By.id("pid1")));

    //Test Book2 is located above Book6

    WebElement AboveBook2 = driver.findElement(RelativeLocator.with(By.tagName("li")).above(By.id("pid6")));

    //Test Book1 is located near Book2

    WebElement NearBook1 = driver.findElement(RelativeLocator.with(By.tagName("li")).near(By.id("pid2")));



}