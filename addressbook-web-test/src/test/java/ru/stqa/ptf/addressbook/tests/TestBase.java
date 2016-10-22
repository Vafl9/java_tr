package ru.stqa.ptf.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.ptf.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {


    Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() {
        app.stop();
    }


    @BeforeMethod
    public void logTestStart(Method method, Object[] objects)
    {
        logger.info("Start test " + method.getName()+ Arrays.asList(objects));
    }

    @AfterMethod (alwaysRun = true)
    public void logTestStop(Method method)
    {
        logger.info("Stop test " + method.getName());
    }

}
