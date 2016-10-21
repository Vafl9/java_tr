package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class HelperBase {
    protected final WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if(!text.equals(existingText))
            {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }

        }

    }protected void attach(By locator, File file) {
        if (file != null) {
                wd.findElement(locator).sendKeys(file.getAbsolutePath());
            System.out.println(file.getAbsolutePath());
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        }
        catch (NoSuchElementException ex)
        {
            return false;
        }

    }

    protected String GetElementText(By by)
    {
       return wd.findElement(by).getText();
    }

    public void SelectByText(By by,String text)
    {
        new Select(wd.findElement(by)).selectByVisibleText(text);
    }

    public void findElement(By by)
    {
        wd.findElement(by);
    }


}
