package org.pentaho.test.grid;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.AssertionFailedError;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.pentaho.test.webdriver.WebDriverTestBase;

public class GridTestBase extends WebDriverTestBase {

  public static final String GRID_URL = "http://localhost:4444/wd/hub";

  protected static HashMap<DesiredCapabilities, WebDriver> drivers = new HashMap<DesiredCapabilities, WebDriver>();

  protected List<Throwable> errors = new ArrayList<Throwable>();

  public static WebDriver addCapability(DesiredCapabilities capability) throws Exception {
    if (!drivers.containsKey(capability)) {
      drivers.put(capability, new RemoteWebDriver(new URL(GRID_URL), capability));
    }
    return drivers.get(capability);
  }

  @BeforeClass
  public static void setup() throws Exception {
    // add capabilities
    // DesiredCapabilities ie8 = DesiredCapabilities.internetExplorer();
    // ie8.setVersion("8");
    // addCapability(ie8);

    DesiredCapabilities ie9 = DesiredCapabilities.internetExplorer();
    ie9.setPlatform(Platform.ANY);
    // ie9.setVersion("9");
    addCapability(ie9);

//    DesiredCapabilities chrome = DesiredCapabilities.chrome();
//    addCapability(chrome);

    DesiredCapabilities ff = DesiredCapabilities.firefox();
    addCapability(ff);
  }

  @AfterClass
  public static void tearDown() throws Exception {
    for (WebDriver driver : drivers.values()) {
      driver.quit();
    }
  }

  public void startTest(GridRunnable gr) throws Exception {
    // for each driver (browser) run the tests
    List<Thread> threads = new ArrayList<Thread>();
    for (final WebDriver driver : drivers.values()) {
      GridThread thread = new GridThread(driver, gr, errors);
      threads.add(thread);
      thread.start();
    }
    // wait for the tests to finish
    for (Thread thread : threads) {
      thread.join();
    }
    // assert that there are no errors
    if (errors.size() > 0) {
      Throwable t = errors.get(0);
      AssertionFailedError exception = new AssertionFailedError(t.getMessage());
      exception.setStackTrace(t.getStackTrace());
      throw exception;
    }
  }

}
