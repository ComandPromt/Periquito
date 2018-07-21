package periquito;

import org.openqa.selenium.chrome.ChromeDriver;

import periquito.DriverSelenium;

public class DriverSeleniumChrome extends DriverSelenium{
	
	public DriverSeleniumChrome() {
		
		//Indicamos la ruta del driver que levantara chrome
		final StringBuilder path = new StringBuilder();
		
		path.append("chromedriver.exe");
		
		System.setProperty("webdriver.chrome.driver", path.toString());
	
		driver = new ChromeDriver();
		
		//maximizar la ventana
		driver.manage().window().maximize();
	}
}
