package org.pentaho.test.grid;

import org.openqa.selenium.WebDriver;

public abstract class GridRunnable implements Runnable {
  protected ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

  public GridRunnable() {
  }

  public WebDriver getDriver() {
    return threadLocalDriver.get();
  }

  public void setDriver(WebDriver driver) {
    this.threadLocalDriver.set(driver);
  }

}
