package periquito;

public class DriverSeleniumChrome extends DriverSelenium {

	public DriverSeleniumChrome() {
		// Indicamos la ruta del driver que levantara chrome
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	}
}
