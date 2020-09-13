package periquito;

public class DriverSeleniumChrome extends DriverSelenium {

	public DriverSeleniumChrome() {

		if (MenuPrincipal.getOs().equals("Linux")) {
			System.setProperty("webdriver.gecko.driver", "geckodriver");
		}

		else {
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		}

	}
}
