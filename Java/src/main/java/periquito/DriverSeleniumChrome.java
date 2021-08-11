package periquito;

public class DriverSeleniumChrome extends DriverSelenium {

	public DriverSeleniumChrome() {

		String file = "C:\\Users\\Yeah\\Periquito\\Java\\geckodriver.exe";

		if (MenuPrincipal.getOs().contains("inux")) {

			file = "geckodriver";

		}
		System.out.println(file);
		System.setProperty("webdriver.gecko.driver", file);

	}

}
