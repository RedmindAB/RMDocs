package se.redmind.auto;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverHandler {

    protected WebDriver driver;

//    @Before
//    public void setDriver(){
//        System.setProperty("webdriver.chrome.driver", "C://Users//RMUser//Desktop//Chromedriver//chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.get("http://localhost:4567/");
//    }

    @After
    public void shutDown(){
        driver.quit();
    }



}
