package Utils;

public class DriverSeleniumChrome extends DriverSelenium {

	public DriverSeleniumChrome() {
		System.setProperty("webdriver.chrome.driver", "Utils/chromedriver.exe");
	}
}