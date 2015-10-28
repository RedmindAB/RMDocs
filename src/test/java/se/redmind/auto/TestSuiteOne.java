package se.redmind.auto;

import org.junit.*;
import org.junit.runner.RunWith;
import se.redmind.main.Main;
import se.redmind.rmtest.selenium.grid.DriverNamingWrapper;
import se.redmind.rmtest.selenium.grid.Parallelized;
import se.redmind.rmtest.selenium.grid.TestBase;

import java.util.concurrent.TimeUnit;

/**
 * Created by victormattsson on 2015-10-28.
 */
@RunWith(Parallelized.class)
public class TestSuiteOne extends TestBase {

    public TestSuiteOne(DriverNamingWrapper driverWrapper, String driverDescription) {
        super(driverWrapper, driverDescription);
    }

    @BeforeClass
    public static void beforeClass(){
        String path = System.getProperty("user.dir") + "/TestProject/Mock Project/";
        Main.main(new String[] {"-p", path, "-o", ".html"});
    }

    @Before
    public void before(){
        webDriver = driverNamingWrapper.startDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://localhost:9090/");
    }

    @Test
    public void verifyMockTestClassOne(){
        PageObjects object = new PageObjects(webDriver);

        object.clickMockTestClass1();
        Assert.assertTrue(object.veriyMockTestClass1Text().equals("MockTestClass1"));
        object.clickNewTextDocument();
        Assert.assertTrue(object.verifyNewTextDocumentText().equals("newTextDocument"));
        Assert.assertTrue(object.verifyGurkaTableContent().equals("hejehejehej"));
        Assert.assertTrue(object.verifyGurkaTableContent2().equals("hejsansvejsan"));
        object.clickNewClass();
        Assert.assertTrue(object.verifyNewClassText().equals("newClass"));

    }

    @Test
    public void verifyMockTestClassTwo(){
        PageObjects object = new PageObjects(webDriver);

        object.clickMockTestClass2();
        Assert.assertTrue(object.verifyMockTestClass2Text().equals("MockTestClass2"));
        object.clickCreateJson();
        Assert.assertTrue(object.verifyCreateJsonText().equals("createJSON"));
        object.clickCreateSwing();
        Assert.assertTrue(object.verifyCreateSwingText().equals("createSwing"));
        object.clickCreateXml();
        Assert.assertTrue(object.verifyCreateXmlText().equals("createXML"));

    }

    @Test
    public void verifyMockTestClassThree(){
        PageObjects object = new PageObjects(webDriver);

        object.clickMockTestClass3();
        Assert.assertTrue(object.verifyMockTestClass3Text().equals("MockTestClass3"));
        object.clickGetNames();
        Assert.assertTrue(object.verifyGetNamesText().equals("getNames"));
        object.clickGetValues();
        Assert.assertTrue(object.verifyGetValuesText().equals("getValues"));
        object.clickAddNumbers();
        Assert.assertTrue(object.verifyAddNumbersText().equals("addNumbers"));
    }

    @Test
    public void verifyMockTestClassFour(){
        PageObjects object = new PageObjects(webDriver);

        object.clickMockTestClass4();
        Assert.assertTrue(object.verifyMockTestClass4Text().equals("MockTestClass4"));
        object.clickAddMethods();
        Assert.assertTrue(object.verifyAddMethodsText().equals("addMethods"));
        object.clickCreateNewException();
        Assert.assertTrue(object.verifyCreateNewExceptionText().equals("createNewException"));
    }

    @Test
    public void verifyTitles() throws InterruptedException{
        PageObjects object = new PageObjects(webDriver);

        object.clickMockTestClass2();
        object.waitForCreateXmlToLoad();
        object.clickCreateXml();
        object.clickBackToTop();
        Assert.assertTrue(object.verifyBackToTopTitle().equals("http://localhost:9090/#top"));
        Assert.assertTrue(object.verifyListHeader().equals("KLASSER"));
        Assert.assertTrue(object.verifyProjectName().equals("Mock Project"));
    }
}
