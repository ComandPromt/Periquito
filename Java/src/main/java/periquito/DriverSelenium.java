package periquito;

import org.openqa.selenium.WebDriver;

public abstract class DriverSelenium {

	protected static WebDriver driver;

	public WebDriver getDriver() {
		return DriverSelenium.driver;
	}

}
