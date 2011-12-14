package org.pentaho.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.pentaho.test.webdriver.WebDriverTestBase;

public class WebDriverTest extends WebDriverTestBase {

  @BeforeClass
  public static void setup() {
    System.setProperty("webdriver.chrome.driver", "./bin/chromedriver.exe");
    DesiredCapabilities capabilities = new DesiredCapabilities();
    // capabilities.setPlatform(Platform.WINDOWS);
    // capabilities.setVersion("8");
    driver = new InternetExplorerDriver(capabilities);
    //driver = new FirefoxDriver(capabilities);
    // driver = new ChromeDriver(capabilities);
  }

  @Test
  public void testWorkspace() throws Exception {
    driver.get("http://localhost:8080/pentaho/Logout");

    WebElement userElement = getElementById(driver, "j_username");
    userElement.clear();
    userElement.sendKeys("joe");
    assertEquals("joe", userElement.getAttribute("value"));

    WebElement passwordElement = getElementById(driver, "j_password");
    passwordElement.clear();
    passwordElement.sendKeys("password");
    assertEquals("password", passwordElement.getAttribute("value"));

    driver.findElement(By.cssSelector("input.pentaho-button")).click();

    getElementById(driver, "workspace.perspective").click();

    // workspaceElement.click();

    // Get a handle to the open alert, prompt or confirmation
    // Alert alert = driver.switchTo().alert();
    // alert.accept();
  }

}
