package periquito;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.Metodos;

public class Descarga {

	public static void main(String[] args) {

		descargar("http://www.example.com/showphoto.php?photo_id=", 1814, 10628, 1);

	}

	public static void descargarFoto(String enlace, int numero) {
		try {
			// Url con la foto
			URL url = new URL(enlace);

			// establecemos conexion
			URLConnection urlCon = url.openConnection();

			// Se obtiene el inputStream de la foto web y se abre el fichero
			// local.
			InputStream is = urlCon.getInputStream();
			FileOutputStream fos = new FileOutputStream(
					"Downloads/Image" + numero + "." + Metodos.extraerExtension(enlace));

			// Lectura de la foto de la web y escritura en fichero local
			byte[] array = new byte[1000]; // buffer temporal de lectura.
			int leido = is.read(array);
			while (leido > 0) {
				fos.write(array, 0, leido);
				leido = is.read(array);
			}

			// cierre de conexion y fichero.
			is.close();
			fos.close();
		} catch (Exception e) {
			//
		}
	}

	public static void descargar(String imagen, int inicio, int fin, int salto) {
		try {

			WebDriver chrome;

			for (int x = inicio; x <= fin; x++) {

				chrome = new ChromeDriver();

				chrome.get(imagen + x);

				if (!chrome.findElements(By.tagName("img")).isEmpty()) {
					List<WebElement> image = chrome.findElements(By.tagName("img"));

					descargarFoto(image.get(0).getAttribute("src"), x);
				}

				chrome.close();

			}

		} catch (Exception e) {
//
		}
	}

}
