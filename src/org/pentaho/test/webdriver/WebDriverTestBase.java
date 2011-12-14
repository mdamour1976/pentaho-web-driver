package org.pentaho.test.webdriver;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverTestBase {

  protected static WebDriver driver;

  @AfterClass
  public static void tearDown() throws Exception {
    if (driver != null) {
      driver.close();
    }
  }

  public static WebDriver getDriver() {
    return driver;
  }

  public static void setDriver(WebDriver driver) {
    WebDriverTestBase.driver = driver;
  }

  public static boolean isElementPresent(final WebDriver driver, final By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public static WebElement getElementById(final WebDriver driver,
      final String id) {
    for (int iterations = 0;; iterations++) {
      if (iterations >= 240)
        fail("timeout");
      try {
        if (isElementPresent(driver, By.id(id))) {
          return driver.findElement(By.id(id));
        }
      } catch (Exception e) {
      }
      try {
        Thread.sleep(250);
      } catch (InterruptedException ie) {
      }
    }
  }

}
