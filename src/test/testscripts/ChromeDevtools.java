import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.ConnectionType;
import org.openqa.selenium.devtools.v85.network.model.LoadingFailed;
import org.openqa.selenium.devtools.v91.emulation.Emulation;
import org.openqa.selenium.devtools.v91.log.Log;
import org.openqa.selenium.devtools.v91.log.model.LogEntry;
import org.openqa.selenium.devtools.v91.security.Security;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChromeDevtools {

    WebDriver driver;

    //@Test
    public void Enablenetworkoffline(){

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

       DevTools devtools = ((ChromeDriver)driver).getDevTools();

       devtools.createSession();

       devtools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

       devtools.send(Network.emulateNetworkConditions(true,100,1000,2000,Optional.of(ConnectionType.ETHERNET)));

       driver.get("https://www.google.com/");

    }

    //@Test(priority = 1)
    public void Enablenetworkonline(){

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        DevTools devtools = ((ChromeDriver)driver).getDevTools();

        devtools.createSession();

        devtools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        devtools.send(Network.emulateNetworkConditions(false,100,1000,2000,Optional.of(ConnectionType.ETHERNET)));

        driver.get("https://www.google.com/");

    }

    //@Test
    public void Consolelogs() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        DevTools devtools = ((ChromeDriver) driver).getDevTools();

        devtools.createSession();

        devtools.send(Log.enable());

        //we add a listener to capture all the console logs logged by the application. For each log captured by the application we then extract the log text with getText() and log level with getLevel() methods.
        devtools.addListener(Log.entryAdded(),logEntry -> {
            System.out.println("log:" + logEntry.getText());
            System.out.println("level:" + logEntry.getLevel());
        });

        driver.get("https://testersplayground.herokuapp.com/console-5d63b2b2-3822-4a01-8197-acd8aa7e1343.php");

    }

    //@Test
    public void loadinsecurewebsite(){

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        DevTools devtools = ((ChromeDriver) driver).getDevTools();

        devtools.createSession();

        //if true - it will ignore the certificate and launch the url
        //if false -  it will not ignore the certificate

        devtools.send(Security.setIgnoreCertificateErrors(false));

        driver.get("https://expired.badssl.com/");

    }

    //@Test
    public void SetDevicemode() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        DevTools devtools = ((ChromeDriver) driver).getDevTools();

        devtools.createSession();

        Map deviceMetrics = new HashMap()

        {{
            put("width",600);
            put("height",1000);
            put("mobile",true);
            put("deviceScaleFactor",50);
        }};

        //I call the executeCdpCommand() method and pass two parameters: the command name as “Emulation.setDeviceMetricsOverride” and the device metrics Map with the parameters.
        ((ChromeDriver) driver).executeCdpCommand("Emulation.setDeviceMetricsOverride",deviceMetrics);

        driver.get("https://www.google.com");



    }

    //@Test
    public void GeoMocking() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        DevTools devtools = ((ChromeDriver) driver).getDevTools();

        devtools.createSession();

        //send() method to invoke the Emulation.setGeolocationOverride command, sending the latitude, longitude, and accuracy.
        devtools.send(Emulation.setGeolocationOverride(
                Optional.of(35.8235),
                Optional.of(-78.8256),
                Optional.of(100)));

        driver.get("https://mycurrentlocation.net/");

    }

    @Test
    public void Capturenetworktraffic() { //to check the API request methods

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        DevTools devtools = ((ChromeDriver) driver).getDevTools();

        devtools.createSession();

        // DevTools::send() method to send the Network.enable CDP command to enable capturing network traffic.
        devtools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        //listener is added to listen to all the requests made by the application. For each request captured by the application we then extract the URL with getRequest().getUrl() and the HTTP Method with getRequest().getMethod().
        devtools.addListener(Network.requestWillBeSent(),
                entry -> {
                    System.out.println("Request URI : " + entry.getRequest().getUrl()+"\n"
                            + " With method : "+entry.getRequest().getMethod() + "\n");
                    entry.getRequest().getMethod();
                });
        driver.get("https://www.google.com");

        //Network.disable to stop capturing the network traffic
        devtools.send(Network.disable());
    }

}
