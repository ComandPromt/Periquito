package periquito;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Periquito {

	public static void main(String[] args) throws Exception {

//		Metodos.crearCarpetas();
//
//		new MenuPrincipal();
		try {
			WebDriver chrome = new ChromeDriver();

			chrome.get("http://192.168.1.100/hoopfetish/categories.php?cat_id=9");

			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
			// chrome.findElement(By.id("user_name")).sendKeys("ComandPromt");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
