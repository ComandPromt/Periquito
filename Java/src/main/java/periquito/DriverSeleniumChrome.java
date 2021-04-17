package periquito;

public class DriverSeleniumChrome extends DriverSelenium {

	public DriverSeleniumChrome() {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
	}
}
