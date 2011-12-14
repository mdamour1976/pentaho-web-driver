package org.pentaho.test.grid;

import java.util.List;

import org.openqa.selenium.WebDriver;

public class GridThread extends Thread {
  protected WebDriver driver;
  protected GridRunnable runnable;
  protected List<Throwable> errors;
  
  public GridThread(WebDriver driver, GridRunnable runnable, List<Throwable> errors) {
    this.driver = driver;
    this.runnable = runnable;
    this.errors = errors;
  }

  public void run() {
    try {
      runnable.setDriver(driver);
      runnable.run();
    } catch (Throwable t) {
      errors.add(t);
    }
  }

}
