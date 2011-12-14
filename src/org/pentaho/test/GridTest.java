package org.pentaho.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.pentaho.test.grid.GridRunnable;
import org.pentaho.test.grid.GridTestBase;

public class GridTest extends GridTestBase {

  @Test
  public void testLoginLogout() throws Exception {
    GridRunnable gr = new GridRunnable() {
      public void run() {
        WebDriver driver = getDriver();
        driver.get("http://localhost:8080/pentaho/Logout");
        WebElement userElement = getElementById(driver, "j_username");
        userElement.clear();
        userElement.sendKeys("joe");
        assertEquals("joe", userElement.getAttribute("value"));
        driver.findElement(By.id("j_password")).clear();
        driver.findElement(By.id("j_password")).sendKeys("password");
        assertEquals("password", driver.findElement(By.id("j_password")).getAttribute("value"));
        driver.findElement(By.cssSelector("input.pentaho-button")).click();

        getElementById(driver, "filemenu").click();
        getElementById(driver, "logoutMenuItem").click();

        // Get a handle to the open alert, prompt or confirmation
        // Alert alert = driver.switchTo().alert();
        // alert.accept();
      }
    };
    startTest(gr);
  }

  @Test
  public void testSchedulerPerspective() throws Exception {
    GridRunnable gr = new GridRunnable() {
      public void run() {
        WebDriver driver = getDriver();
        driver.get("http://localhost:8080/pentaho/Logout");
        WebElement userElement = getElementById(driver, "j_username");
        userElement.clear();
        userElement.sendKeys("joe");
        assertEquals("joe", userElement.getAttribute("value"));
        driver.findElement(By.id("j_password")).clear();
        driver.findElement(By.id("j_password")).sendKeys("password");
        assertEquals("password", driver.findElement(By.id("j_password")).getAttribute("value"));
        driver.findElement(By.cssSelector("input.pentaho-button")).click();
        getElementById(driver, "workspace.perspective");

        getElementById(driver, "filemenu").click();
        getElementById(driver, "logoutMenuItem").click();

        // Get a handle to the open alert, prompt or confirmation
        // Alert alert = driver.switchTo().alert();
        // alert.accept();
      }
    };
    startTest(gr);
  }

}
