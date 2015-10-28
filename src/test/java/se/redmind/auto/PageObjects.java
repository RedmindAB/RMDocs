package se.redmind.auto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import se.redmind.auto.DriverHandler;

public class PageObjects extends DriverHandler {

    private static WebElement element = null;

    public PageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement clickMockTestClass1() {
        driver.findElement(By.cssSelector("label[for='MockTestClass1']")).click();
        return element;
    }

    public String veriyMockTestClass1Text() {
        return driver.findElement(By.id("#MockTestClass1")).getText();
    }

    public WebElement clickNewTextDocument() {
        driver.findElement(By.cssSelector("a[href='#newTextDocument']")).click();
        return element;
    }

    public String verifyNewTextDocumentText() {
        return driver.findElement(By.xpath("//th[contains(.,'newTextDocument')]")).getText();
    }

    public WebElement clickNewClass() {
        driver.findElement(By.cssSelector("a[href='#newClass']")).click();
        return element;
    }

    public String verifyNewClassText() {
        return driver.findElement(By.xpath("//th[contains(.,'newClass')]")).getText();
    }

    public WebElement clickMockTestClass2() {
        driver.findElement(By.cssSelector("label[for='MockTestClass2']")).click();
        return element;
    }

    public String verifyMockTestClass2Text() {
        return driver.findElement(By.id("#MockTestClass2")).getText();
    }

    public WebElement clickCreateJson() {
        driver.findElement(By.cssSelector("a[href='#createJSON']")).click();
        return element;
    }

    public String verifyCreateJsonText() {
        return driver.findElement(By.xpath("//th[contains(.,'createJSON')]")).getText();
    }

    public WebElement clickCreateSwing() {
        driver.findElement(By.cssSelector("a[href='#createSwing']")).click();
        return element;
    }

    public String verifyCreateSwingText() {
        return driver.findElement(By.xpath("//th[contains(.,'createSwing')]")).getText();
    }

    public WebElement clickCreateXml() {
        driver.findElement(By.cssSelector("a[href='#createXML']")).click();
        return element;
    }

    public String verifyCreateXmlText() {
        return driver.findElement(By.xpath("//th[contains(.,'createXML')]")).getText();
    }

    public WebElement waitForCreateXmlToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='#createXML']")));
        return element;
    }

    public WebElement clickMockTestClass3() {
        driver.findElement(By.cssSelector("label[for='MockTestClass3']")).click();
        return element;
    }

    public String verifyMockTestClass3Text() {
        return driver.findElement(By.id("#MockTestClass3")).getText();
    }

    public WebElement clickGetNames() {
        driver.findElement(By.cssSelector("a[href='#getNames']")).click();
        return element;
    }

    public String verifyGetNamesText() {
        return driver.findElement(By.xpath("//th[contains(.,'getNames')]")).getText();
    }

    public WebElement clickGetValues() {
        driver.findElement(By.cssSelector("a[href='#getValues']")).click();
        return element;
    }

    public String verifyGetValuesText() {
        return driver.findElement(By.xpath("//th[contains(.,'getValues')]")).getText();
    }

    public WebElement clickAddNumbers() {
        driver.findElement(By.cssSelector("a[href='#addNumbers']")).click();
        return element;
    }

    public String verifyAddNumbersText() {
        return driver.findElement(By.xpath("//th[contains(.,'addNumbers')]")).getText();
    }

    public WebElement clickMockTestClass4() {
        driver.findElement(By.cssSelector("label[for='MockTestClass4']")).click();
        return element;
    }

    public String verifyMockTestClass4Text() {
        return driver.findElement(By.id("#MockTestClass4")).getText();
    }

    public WebElement clickAddMethods() {
        driver.findElement(By.cssSelector("a[href='#addMethods']")).click();
        return element;
    }

    public String verifyAddMethodsText() {
        return driver.findElement(By.xpath("//th[contains(.,'addMethods')]")).getText();
    }

    public WebElement clickCreateNewException() {
        driver.findElement(By.cssSelector("a[href='#createNewException']")).click();
        return element;
    }

    public String verifyCreateNewExceptionText() {
        return driver.findElement(By.xpath("//th[contains(.,'createNewException')]")).getText();
    }

    public WebElement clickBackToTop() {
        driver.findElement(By.cssSelector("a.back-to-top")).click();
        return element;
    }

    public WebElement waitForTop(){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.back-to-top")));
        return element;
    }

    public String verifyBackToTopTitle() {
        return driver.getCurrentUrl().toString();
    }

    public String verifyListHeader() {
        return driver.findElement(By.xpath("//label[contains(.,'Klasser')]")).getText();
    }

    public String verifyProjectName() {
        return driver.findElement(By.id("project-name")).getText();
    }

    public String verifyGurkaTableContent() {
        return driver.findElement(By.cssSelector("#innerTable4>tr>td:nth-child(2)")).getText();
    }

    public String verifyGurkaTableContent2() {
        return driver.findElement(By.cssSelector("#innerTable4>tr:nth-child(3)>td:nth-child(2)")).getText();
    }

}
