package periquito;

public class DriverSeleniumChrome extends DriverSelenium {

	public DriverSeleniumChrome() {

		if (MenuPrincipal.getOs().equals("Linux")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver/Linux/chromedriver");
		}

		else {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}

	}
}
