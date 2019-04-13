package periquito;

public class DriverSeleniumChrome extends DriverSelenium {

	public DriverSeleniumChrome() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	}
}
