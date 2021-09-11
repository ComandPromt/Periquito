package utils;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.numixproject.colorextractor.image.Color;
import org.numixproject.colorextractor.image.ColorPicker;
import org.numixproject.colorextractor.image.KMeansColorPicker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.biezhi.webp.WebpIO;
import periquito.Bd;
import periquito.Config;
import periquito.Config2;
import periquito.CronWebp;
import periquito.Descarga;
import periquito.MenuPrincipal;

public abstract class Metodos {

	public static Timer timer = new Timer();

	static TimerTask task = new CronWebp();

	public static boolean filtroSize(String archivo, double filtro, int unidad) {

		File file = new File(MenuPrincipal.getDirectorioImagenes() + MenuPrincipal.getSeparador() + archivo);

		boolean resultado = false;

		double unit = 0;

		try {

			if (file.exists()) {

				switch (unidad) {

				default:

				case 1:

					unit = file.length();

					break;

				case 2:

					unit = file.length() / 1024d;

					break;

				case 3:

					unit = (file.length() / 1024d) / 1024;

					break;

				case 4:

					unit = ((file.length() / 1024d) / 1024) / 1024;

					break;

				case 5:

					unit = (((file.length() / 1024d) / 1024) / 1024) / 1024d;

					break;

				}

				if (unit <= filtro) {

					resultado = true;

				}

				else {

					resultado = false;

				}

			}

		}

		catch (Exception e) {

		}

		return resultado;

	}

	public static boolean genHtml(File file) throws IOException {

		LinkedList<String> colores = new LinkedList<String>();

		colores.add("#151313");
		colores.add("#383737");
		colores.add("#53565a");
		colores.add("#63666a");
		colores.add("#75787b");
		colores.add("#888b8d");
		colores.add("#b1b3b3");
		colores.add("#929289");
		colores.add("#494845");
		colores.add("#515150");
		colores.add("#404049");
		colores.add("#444340");
		colores.add("#d8d8d6");
		colores.add("#454040");
		colores.add("#b1ada8");
		colores.add("#d1d1d2");
		colores.add("#c5c4c4");
		colores.add("#b3afab");
		colores.add("#4b4d4b");
		colores.add("#6c6d71");
		colores.add("#dddedd");
		colores.add("#222125");
		colores.add("#706e71");
		colores.add("#2a3033");
		colores.add("#3b3c40");
		colores.add("#c2c2c1");
		colores.add("#57504c");
		colores.add("#36312e");
		colores.add("#000000");
		colores.add("#ffffff");
		colores.add("#939290");
		colores.add("#151515");
		colores.add("#1c1c1c");
		colores.add("#2e2e2e");
		colores.add("#424242");
		colores.add("#585858");
		colores.add("#6e6e6e");
		colores.add("#848484");
		colores.add("#484848");
		colores.add("#606060");
		colores.add("#a8a8a8");
		colores.add("#303030");
		colores.add("#909090");
		colores.add("#787878");
		colores.add("#c0c0c0");
		colores.add("#181818");
		colores.add("#f0f0f0");
		colores.add("#d8d8d8");
		colores.add("#a4a4a4");
		colores.add("#bdbdbd");
		colores.add("#e6e6e6");
		colores.add("#f2f2f2");
		colores.add("#fafafa");
		colores.add("#4f4e4e");
		colores.add("#a09d9d");
		colores.add("#fcfbff");
		colores.add("#aeaeae");
		colores.add("#f8f9fb");
		colores.add("#5d5e62");
		colores.add("#f3f2f7");
		colores.add("#fbfbfb");
		colores.add("#444446");
		colores.add("#fffeff");
		colores.add("#212224");
		colores.add("#f9f9f9");
		colores.add("#f0eff4");
		colores.add("#18191b");
		colores.add("#fdfdfd");
		colores.add("#fefefe");
		colores.add("#fcfcfc");
		colores.add("#d3d3d3");
		colores.add("#747474");
		colores.add("#d1d1d1");
		colores.add("#dddddd");
		colores.add("#878787");
		colores.add("#141414");
		colores.add("#1a1a1a");
		colores.add("#ebebeb");
		colores.add("#dfdfdf");
		colores.add("#e4e4e4");
		colores.add("#d4d4d4");
		colores.add("#6b6b6b");
		colores.add("#f4f4f4");
		colores.add("#dbdbdb");
		colores.add("#cccccc");
		colores.add("#b6b6b6");
		colores.add("#d7d7d7");
		colores.add("#c6c6c6");
		colores.add("#c8c8c8");
		colores.add("#353535");
		colores.add("#5d5d5d");
		colores.add("#515151");
		colores.add("#686868");
		colores.add("#838383");
		colores.add("#e0e0e0");
		colores.add("#d6d6d6");
		colores.add("#c4c4c4");
		colores.add("#646464");
		colores.add("#b8b8b8");
		colores.add("#3a3a3a");
		colores.add("#343434");
		colores.add("#252525");
		colores.add("#c3c3c3");
		colores.add("#999999");
		colores.add("#9e9e9e");
		colores.add("#cacaca");
		colores.add("#949494");
		colores.add("#161616");
		colores.add("#121212");
		colores.add("#171717");
		colores.add("#4a4a4a");
		colores.add("#d2d2d2");
		colores.add("#cdcdcd");
		colores.add("#767676");
		colores.add("#2a2a2a");
		colores.add("#2d2d2d");
		colores.add("#3f3f3f");
		colores.add("#b0b0b0");
		colores.add("#f6f6f6");
		colores.add("#bababa");
		colores.add("#c2c2c2");
		colores.add("#b7b7b7");
		colores.add("#f7f7f7");
		colores.add("#242424");
		colores.add("#acacac");
		colores.add("#ababab");
		colores.add("#aaaaaa");
		colores.add("#b4b4b4");
		colores.add("#b3b3b3");
		colores.add("#c5c5c5");
		colores.add("#494949");
		colores.add("#b9b9b9");
		colores.add("#b2b2b2");
		colores.add("#b5b5b5");
		colores.add("#adadad");
		colores.add("#a6a6a6");
		colores.add("#afafaf");
		colores.add("#bebebe");
		colores.add("#c7c7c7");
		colores.add("#c1c1c1");
		colores.add("#a3a3a3");
		colores.add("#979797");
		colores.add("#414141");
		colores.add("#7e7e7e");
		colores.add("#474747");
		colores.add("#363636");
		colores.add("#313131");
		colores.add("#444444");
		colores.add("#555555");
		colores.add("#676767");
		colores.add("#f5f5f5");
		colores.add("#272727");
		colores.add("#3b3b3b");
		colores.add("#282828");
		colores.add("#202020");
		colores.add("#5b5b5b");
		colores.add("#969696");
		colores.add("#919191");
		colores.add("#939393");
		colores.add("#bcbcbc");
		colores.add("#696969");
		colores.add("#656565");
		colores.add("#7a7a7a");
		colores.add("#545454");
		colores.add("#404040");
		colores.add("#454545");
		colores.add("#535353");
		colores.add("#4d4d4d");
		colores.add("#5f5f5f");
		colores.add("#9d9d9d");
		colores.add("#989898");
		colores.add("#787878");
		colores.add("#a5a5a5");
		colores.add("#a0a0a0");
		colores.add("#262626");
		colores.add("#333333");
		colores.add("#464646");
		colores.add("#505050");
		colores.add("#9f9f9f");
		colores.add("#a9a9a9");
		colores.add("#cfcfcf");
		colores.add("#4b4b4b");
		colores.add("#3c3c3c");
		colores.add("#1b1b1b");
		colores.add("#757575");
		colores.add("#6c6c6c");
		colores.add("#8f8f8f");
		colores.add("#9b9b9b");
		colores.add("#2f2f2f");
		colores.add("#373737");
		colores.add("#323232");
		colores.add("#6d6d6d");
		colores.add("#a1a1a1");
		colores.add("#848484");
		colores.add("#4e4e4e");
		colores.add("#4c4c4c");
		colores.add("#9c9c9c");
		colores.add("#d0d0d0");
		colores.add("#9a9a9a");
		colores.add("#bfbfbf");
		colores.add("#292929");
		colores.add("#b1b1b1");
		colores.add("#cecece");
		colores.add("#dadada");
		colores.add("#e2e2e2");
		colores.add("#e1e1e1");
		colores.add("#dedede");
		colores.add("#e7e7e7");
		colores.add("#8c8c8c");
		colores.add("#d9d9d9");
		colores.add("#dcdcdc");
		colores.add("#6a6a6a");
		colores.add("#6f6f6f");
		colores.add("#8d8d8d");
		colores.add("#959595");
		colores.add("#393939");
		colores.add("#808080");
		colores.add("#797979");
		colores.add("#434343");
		colores.add("#3d3d3d");
		colores.add("#707070");
		colores.add("#8a8a8a");
		colores.add("#909090");
		colores.add("#737373");
		colores.add("#2b2b2b");
		colores.add("#bdbdbd");
		colores.add("#232323");
		colores.add("#7c7c7c");
		colores.add("#60606");
		colores.add("#80808");
		colores.add("#222222");
		colores.add("#626262");
		colores.add("#575757");
		colores.add("#30303");
		colores.add("#b0b0b");
		colores.add("#d0d0d");
		colores.add("#191919");
		colores.add("#40404");
		colores.add("#717171");
		colores.add("#727272");
		colores.add("#50505");
		colores.add("#a0a0a");
		colores.add("#e0e0e");
		colores.add("#90909");
		colores.add("#898989");
		colores.add("#111111");
		colores.add("#101010");
		colores.add("#f0f0f");
		colores.add("#c0c0c");
		colores.add("#7d7d7d");
		colores.add("#20202");
		colores.add("#212121");
		colores.add("#70707");
		colores.add("#7f7f7f");
		colores.add("#8e8e8e");
		colores.add("#888888");
		colores.add("#525252");
		colores.add("#7b7b7b");
		colores.add("#c9c9c9");
		colores.add("#929292");
		colores.add("#858585");
		colores.add("#8b8b8b");
		colores.add("#616161");
		colores.add("#5a5a5a");
		colores.add("#595959");
		colores.add("#1d1d1d");
		colores.add("#565656");
		colores.add("#2c2c2c");
		colores.add("#f8f8f8");
		colores.add("#818181");
		colores.add("#777777");
		colores.add("#5c5c5c");
		colores.add("#636363");
		colores.add("#383838");
		colores.add("#4f4f4f");
		colores.add("#131313");
		colores.add("#1f1f1f");
		colores.add("#1e1e1e");
		colores.add("#bbbbbb");
		colores.add("#e9e9e9");
		colores.add("#e8e8e8");
		colores.add("#5e5e5e");
		colores.add("#e5e5e5");
		colores.add("#e3e3e3");
		colores.add("#a2a2a2");
		colores.add("#3e3e3e");
		colores.add("#cacaca");
		colores.add("#cbcbcb");
		colores.add("#d5d5d5");
		colores.add("#666666");
		colores.add("#f1f1f1");
		colores.add("#ededed");
		colores.add("#eaeaea");
		colores.add("#eeeeee");
		colores.add("#efefef");
		colores.add("#ececec");
		colores.add("#828282");
		colores.add("#a7a7a7");
		colores.add("#868686");
		colores.add("#f3f3f3");
		colores.add("#10101");
		colores.add("#0f0f0f");
		colores.add("#0C0C0C");
		colores.add("#090909");
		colores.add("#060606");
		colores.add("#030303");
		colores.add("#9b9a98");
		colores.add("#b6b5b3");
		colores.add("#6d6c6a");
		colores.add("#626260");
		colores.add("#222221");
		colores.add("#797977");
		colores.add("#92918f");
		colores.add("#d0d1d1");

		boolean resultado = false;

		try {

			org.numixproject.colorextractor.image.Image img = new AWTImage(file);

			ColorPicker km = new KMeansColorPicker();

			java.util.List<Color> l = km.getUsefulColors(img, 1);

			java.util.List<Color> t = new ArrayList<Color>();

			for (Color c : l) {

				float f[] = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue());

				t.add(new Color(Color.HSBtoRGB(f[0], f[1], f[2])));

			}

			String color;

			LinkedList<String> imagenColoareada = new LinkedList<String>();

			for (Color c : l) {

				color = "#" + String.format("%06x", c.getRGB() & 0x00FFFFFF);

				if (!colores.contains(color)) {

					System.out.println(color);

					imagenColoareada.add(color);
				}

			}

			if (imagenColoareada.isEmpty()) {

				resultado = true;

			}

			else {

				resultado = false;

			}

		}

		catch (Exception e) {

		}

		return resultado;

	}

	public static void borrarArchivosSubidos() {

		LinkedList<String> lista = new LinkedList<String>();

		if (!MenuPrincipal.imagenesSubidas.isEmpty()) {

			lista = MenuPrincipal.imagenesSubidas;

		}

		else {

			lista = directorio(MenuPrincipal.getDirectorioImagenes() + MenuPrincipal.getSeparador(), "images", true,
					false);

		}

		if (MenuPrincipal.configuracion[4].equals("1")) {

			moverListaImagenes(lista, true);

		}

		else {

			moverListaImagenes(lista, false);

		}

	}

	public static void moverListaImagenes(LinkedList<String> lista, boolean borrar) {

		try {

			JSONObject json;

			int respuesta;

			for (int i = 0; i < lista.size(); i++) {

				String server = MenuPrincipal.getLecturaurl()[0];

				if (!MenuPrincipal.getLecturaurl()[1].isEmpty()) {

					server += "/" + MenuPrincipal.getLecturaurl()[1];

				}

				json = Metodos.readJsonFromUrl("http://" + server + "/api/api.php?sha256='"
						+ Metodos.getSHA256Checksum(
								MenuPrincipal.getDirectorioImagenes() + MenuPrincipal.getSeparador() + lista.get(i))
						+ "'");

				respuesta = json.getInt("respuesta");

				if (respuesta == 202) {

					if (borrar) {

						Metodos.eliminarFichero(
								MenuPrincipal.getDirectorioImagenes() + MenuPrincipal.getSeparador() + lista.get(i));

					}

					else {

						Metodos.moverArchivo(
								MenuPrincipal.getDirectorioImagenes() + MenuPrincipal.getSeparador() + lista.get(i),
								MenuPrincipal.getDirectorioImagenes() + MenuPrincipal.getSeparador()
										+ "imagenes_subidas" + MenuPrincipal.getSeparador() + lista.get(i));

					}

				}

			}

		}

		catch (Exception e) {

		}

	}

	public static void verFicheros(String carpeta) {

		File dir = new File(carpeta);

		if (dir.exists()) {

			File[] ficheros = dir.listFiles();

			for (int x = 0; x < ficheros.length; x++) {

				System.out.println(ficheros[x].length());

			}

		}

	}

	public static void webp_png(boolean png, String src, String dest, boolean eliminarArchivo) throws IOException {

		if (png) {

			WebpIO.create().toNormalImage(src, dest);

		}

		else {

			WebpIO.create().toWEBP(src, dest);

		}

		if (eliminarArchivo) {

			Metodos.eliminarFichero(src);

		}

	}

	public static void resizeImage(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight)
			throws IOException {

		// reads input image
		File inputFile = new File(inputImagePath);
		BufferedImage inputImage = ImageIO.read(inputFile);

		// creates output image
		BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

		// scales the input image to the output image
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();

		// extracts extension of output file
		String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

		// writes to output file
		ImageIO.write(outputImage, formatName, new File(outputImagePath));

	}

	public static List<String> borrarArchivosDuplicados(String directorio) throws IOException {

		LinkedList<String> listaImagenes = directorio(directorio, ".", true, false);

		LinkedList<String> listaImagenesSha = new LinkedList<String>();

		for (int i = 0; i < listaImagenes.size(); i++) {
			listaImagenesSha.add(getSHA256Checksum(directorio + MenuPrincipal.getSeparador() + listaImagenes.get(i)));
		}

		List<String> duplicateList = listaImagenesSha.stream()

				.collect(Collectors.groupingBy(s -> s)).entrySet().stream()

				.filter(e -> e.getValue().size() > 1).map(e -> e.getKey()).collect(Collectors.toList());

		int indice = 0;

		for (String archivoRepetido : duplicateList) {

			for (int i = 0; i < Collections.frequency(listaImagenesSha, archivoRepetido) - 1; i++) {

				indice = listaImagenesSha.indexOf(archivoRepetido);

				if (indice < listaImagenes.size()) {

					eliminarFichero(directorio + listaImagenes.get(indice));

					listaImagenes.remove(indice);

				}
			}

		}

		return listaImagenes;
	}

	public static String extraerNombreFile(String archivo) {

		return archivo.substring(archivo.lastIndexOf(MenuPrincipal.getSeparador()) + 1, archivo.length());
	}

	public static String extraerCarpeta(String ruta) {

		return ruta.substring(0, ruta.lastIndexOf(MenuPrincipal.getSeparador()) + 1);
	}

	public static String calcularTiempo(int numImagenes) {

		int minutos = 0;

		int horas = 0;

		int segundos = 30;

		if (numImagenes > 1) {

			for (int i = 0; i < numImagenes; i++) {
				segundos += 5;
			}

			if (segundos == 60) {
				minutos = 1;
				segundos = 0;
			}

			minutos = segundos / 60;

			int calculoSegundos = 0;

			calculoSegundos = 60 * minutos;

			segundos -= calculoSegundos;

			if (minutos == 60) {
				horas = 1;
				minutos = 0;
				segundos = 0;
			}

			if (minutos > 60) {

				if (minutos % 60 == 0) {
					horas = minutos / 60;
					minutos = 0;
					segundos = 0;
				}

				else {

					int contador = 0;

					int horaProxima = 120;

					int siguienteHora = 0;

					while (contador == 0) {

						if (minutos < horaProxima) {
							contador = horaProxima;
						}

						else {

							siguienteHora = horaProxima + 60;

							if (minutos > horaProxima && minutos < siguienteHora) {
								contador = siguienteHora;
							}

							horaProxima = siguienteHora;

						}
					}

					horas = minutos / 60;

					minutos = 60 - (horaProxima - minutos);

				}

			}

		}

		String ceroHoras = "";
		String ceroMinutos = "";

		String ceroSegundos = "";

		if (horas <= 9) {
			ceroHoras = "0";
		}

		if (minutos <= 9) {
			ceroMinutos = "0";
		}

		if (segundos <= 9) {
			ceroSegundos = "0";
		}

		return ceroHoras + horas + " : " + ceroMinutos + minutos + " : " + ceroSegundos + segundos;

	}

	public static void generarExcel(String hoja, LinkedList<String> datos, LinkedList<String> lista)
			throws IOException {

		HSSFWorkbook workbook = null;

		try {

			workbook = new ExcelGenerator().generateExcel(hoja, datos, lista);

			OutputStream out = null;

			out = new FileOutputStream(extraerNombreArchivo("xls"));

			workbook.write(out);

			out.flush();

			out.close();

			mensaje("Lista exportada con éxito", 2);

		} catch (Exception ex) {
			//
		}

		finally {

			if (workbook != null) {
				workbook.close();
			}

		}
	}

	public static LinkedList<String> obtenerEnlaces(String url, int tomarUrl, String claseTabla, String claseTd,
			int limite) {

		LinkedList<String> enlaces = new LinkedList<String>();

		try {

			Document doc = Jsoup.connect(url).get();

			Elements elements = null;

			String location = null;

			switch (tomarUrl) {

			case 1:
				elements = doc.select("a[href]");
				break;

			case 2:
				elements = doc.select("." + claseTabla + " tr:has(td." + claseTd + ") + tr");
				break;

			case 3:
				elements = doc.select("img[src]");
				break;

			case 4:
				elements = doc.select("." + claseTabla + " a[href]");
				break;

			case 5:
				elements = doc.select("img[src]");
				break;

			}

			boolean darVueltas = false;

			if (limite > 0) {
				darVueltas = true;
			}

			int vueltas = 1;

			for (Element element : elements) {

				if (!darVueltas || darVueltas && vueltas <= limite) {

					switch (tomarUrl) {

					case 1:
					case 4:
						location = element.absUrl("href");
						break;

					case 2:
						location = element.previousElementSibling().select("td." + claseTd).text();
						break;

					case 3:
					case 5:
						location = element.absUrl("src");
						break;
					}

					location = eliminarEspacios(location, false);

					location = limpiarCadena(location);

					String enlace = "";

					for (int i = 0; i < extraerEnlaces(location, url).size(); i++) {

						enlace = extraerEnlaces(location, url).get(i);

						if (!enlace.equals(url + "/#") && !enlaces.contains(enlace)) {
							enlaces.add(enlace);
						}

					}

				}

				vueltas++;
			}

		}

		catch (Exception e) {
			mensaje("Por favor, revisa la URL", 2);

		}

		return enlaces;

	}

	public static LinkedList<String> extraerEnlaces(String cadena, String url) {

		LinkedList<String> urls = new LinkedList<String>();

		LinkedList<String> temporal = new LinkedList<String>();

		String residuo = "";

		int puntero = cadena.indexOf("http");

		int capacidad = 0;

		String cadenaEspacio = "";

		while (cadena.indexOf(" ") > 0) {

			if (puntero >= 0 && cadena.indexOf(" ") >= 0) {

				residuo = cadena.substring(0, cadena.indexOf(" "));

				if (residuo.contains("http") && residuo.contains("http")) {

					urls.add(cadena.substring(0, cadena.indexOf(" ")));

					if (capacidad > 0) {

						for (int i = 0; i < capacidad; i++) {
							cadenaEspacio += temporal.get(i) + " ";
						}

						if (!cadenaEspacio.equals(url + "#")) {
							urls.add(cadenaEspacio);
						}

						capacidad = 0;
						temporal.clear();
						cadenaEspacio = "";
					}

				}

				else {

					if (!residuo.contains("http")) {
						capacidad++;
						temporal.add(residuo);
					}

				}
			}

			cadena = cadena.substring(cadena.indexOf(" ") + 1, cadena.length());

		}

		if (!cadena.isEmpty() && !cadena.equals(url + "#") && cadena.indexOf("http") >= 0) {

			urls.add(cadena);

		}

		return urls;
	}

	public static String limpiarCadena(String urlObtenida) {

		if (urlObtenida.indexOf("\">") > 0 && urlObtenida.indexOf("</a>") > 0) {
			urlObtenida = urlObtenida.substring(urlObtenida.indexOf("\">") + 2, urlObtenida.indexOf("</a>"));
		}

		urlObtenida = urlObtenida.replace("</path>", "");
		urlObtenida = urlObtenida.replace("</svg>", "");
		urlObtenida = urlObtenida.replace("<span", "");
		urlObtenida = urlObtenida.replace("</span>", "");
		urlObtenida = urlObtenida.replace("<img>", "");
		urlObtenida = urlObtenida.replace("</img>", "");
		urlObtenida = urlObtenida.replace("itemscope", "");
		urlObtenida = urlObtenida.replace("</button>", "");
		urlObtenida = urlObtenida.replace("&nbsp;", "");
		urlObtenida = urlObtenida.replace("data-v-4992eadc", "");
		urlObtenida = urlObtenida.replace("data-v-4992eadcBlog", "");

		urlObtenida = urlObtenida.replaceAll("class=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("onmouseover=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("onmouseout=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("height=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("rel=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("width=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("itemprop=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("itemtype=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("itemprop=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("<figure>(.*)</figure>", "");
		urlObtenida = urlObtenida.replaceAll("target=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("style=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("<p(.*)</p>", "");
		urlObtenida = urlObtenida.replaceAll("<svg(.*)>", "");
		urlObtenida = urlObtenida.replaceAll("<path(.*)>", "");

		urlObtenida = urlObtenida.replace("a href=\"", "");
		urlObtenida = urlObtenida.replace("\"  >", "");
		urlObtenida = urlObtenida.replace("<i ></i>", "");
		urlObtenida = urlObtenida.replace("<img >", "");
		urlObtenida = urlObtenida.replace(">", "");
		urlObtenida = urlObtenida.replace("<img", "");
		urlObtenida = urlObtenida.replace("data-v-23efff06", "");
		urlObtenida = urlObtenida.replace("<button", "");
		urlObtenida = urlObtenida.replace("»", "");
		urlObtenida = urlObtenida.replace("…", "");
		urlObtenida = urlObtenida.replace("???? ??????	", "");
		urlObtenida = urlObtenida.replace("src=//", "");
		urlObtenida = urlObtenida.replace("\"", "");
		urlObtenida = urlObtenida.replace("src=", "");
		urlObtenida = urlObtenida.replace("'", "");
		return urlObtenida;

	}

	public static int calcularPorcentaje(int valor, int total) {

		float resultado = (valor * 100) / total;

		int salida;

		NumberFormat numberFormat = NumberFormat.getInstance();

		numberFormat.setMaximumFractionDigits(0);

		numberFormat.setRoundingMode(RoundingMode.DOWN);

		salida = Integer.parseInt(numberFormat.format(resultado));

		return salida;

	}

	public static String obtenerEnlaceCms(String servidor, String carpeta) {

		String separador = "";

		if (!carpeta.equals("")) {
			separador = "/";
		}
		return "http://" + servidor + separador + carpeta;
	}

	public static String eliminarPuntos(String cadena) {

		String cadena2 = cadena;

		try {

			cadena2 = cadena.substring(0, cadena.length() - 4);

			cadena = cadena2.replace(".", "_") + "." + extraerExtension(cadena);

		}

		catch (Exception e) {

		}

		return cadena;

	}

	public static void cerrarNavegador() {

		try {

			if (MenuPrincipal.chrome != null || MenuPrincipal.chrome instanceof WebDriver) {

				MenuPrincipal.chrome.quit();

			}

		}

		catch (Exception e) {

//

		}

	}

	public static void descargarFoto(String enlace) throws IOException {

		String folder = MenuPrincipal.getDirectorioActual() + "Config" + MenuPrincipal.getSeparador() + "Downloads"
				+ MenuPrincipal.getSeparador();

		try {

			File dir = new File(folder);

			if (!dir.exists()) {
				dir.mkdir();
			}

			File file = new File(folder + "Image_"
					+ (listarFicherosPorCarpeta(new File("Config" + MenuPrincipal.getSeparador() + "Downloads"), ".")
							+ 1)
					+ "." + extraerExtension(enlace));

			URLConnection conn = new URL(enlace).openConnection();

			conn.connect();

			InputStream in = conn.getInputStream();

			OutputStream out = new FileOutputStream(file);

			int b = 0;

			while (b != -1) {

				b = in.read();

				if (b != -1) {
					out.write(b);
				}

			}

			out.close();

			in.close();
		}

		catch (MalformedURLException e) {

			mensaje("la URL: " + enlace + " no es valida!", 1);

		}

		catch (Exception e) {

			abrirCarpeta(folder);

		}

	}

	public static void descargar(String imagen, int inicio, int fin, int salto) throws IOException {

		try {

			WebDriver chrome;

			// for (int x = inicio; x <= fin; x += salto) {

			// if (Descarga.getBotonRadio1().isSelected()) {

			chrome = new ChromeDriver();

			chrome.get(imagen);

			if (!chrome.findElements(By.tagName("img")).isEmpty()) {

				List<WebElement> image = chrome.findElements(By.className("fotorama__img"));

				descargarFoto(image.get(0).getAttribute("src"));
			}

			chrome.close();
			// cerrarNavegador();
			// }

//				else {
//					descargar(imagen, x);
//				}

			// }

//			abrirCarpeta(
//					MenuPrincipal.getDirectorioActual() + "Config" + MenuPrincipal.getSeparador() + "Downloads");

		} catch (Exception e) {
			//
			abrirCarpeta(MenuPrincipal.getDirectorioActual() + "Config" + MenuPrincipal.getSeparador() + "Downloads");
			System.exit(0);
		}
	}

	public static String[] leerArchivo(String archivo, int longitud, String contenido, boolean carpeta)
			throws IOException {

		String[] sonido = null;

		try {

			sonido = leerFicheroArray(archivo, longitud);
		}

		catch (Exception e1) {

			try {

				crearFichero(MenuPrincipal.getDirectorioActual() + "Config" + MenuPrincipal.getSeparador() + archivo,
						contenido, carpeta);

				sonido = leerFicheroArray(archivo, longitud);
			}

			catch (Exception e2) {
				//
			}

		}

		return sonido;
	}

	private static void descargar(String imagen, int x) {

		try {

			String extension = Descarga.textField3.getText().trim();

			URL url = new URL(imagen + x + "." + extension);

			URLConnection urlCon = url.openConnection();

			InputStream is = urlCon.getInputStream();

			FileOutputStream fos = new FileOutputStream("Config" + MenuPrincipal.getSeparador() + "Downloads"
					+ MenuPrincipal.getSeparador() + "Image_" + x + "." + extension);

			byte[] array = new byte[1000];
			int leido = is.read(array);

			while (leido > 0) {
				fos.write(array, 0, leido);
				leido = is.read(array);
			}

			is.close();
			fos.close();

		} catch (Exception e) {

			Descarga.setError(true);
		}
	}

	private static String readAll(Reader rd) throws IOException {

		StringBuilder sb = new StringBuilder();

		int cp;

		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}

		return sb.toString();
	}

	public static void convertir(String carpeta) {

		conversion("jpeg", "jpg", carpeta);

		conversion("JPEG", "jpg", carpeta);

		conversion("JPG", "jpg", carpeta);

		conversion("PNG", "png", carpeta);

		conversion("webp", "png", carpeta);

		conversion("GIF", "gif", carpeta);

		conversion("GIF", "gif", carpeta);

	}

	public static JSONObject readJsonFromUrl(String url) throws IOException {

		InputStream is = new URL(url).openStream();

		BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

		String jsonText = readAll(rd);

		is.close();

		return new JSONObject(jsonText);

	}

	public static boolean pingURL(String url) {

		int timeout = 400000;

		url = url.replaceFirst("^https", "http"); // Otherwise an exception may be thrown on invalid SSL certificates.

		try {

			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setConnectTimeout(timeout);
			connection.setReadTimeout(timeout);
			connection.setRequestMethod("HEAD");

			int responseCode = connection.getResponseCode();

			if (responseCode == 404) {
				return false;
			}

			else {
				return (200 <= responseCode && responseCode <= 399);
			}

		} catch (IOException exception) {
			return false;
		}
	}

	public static boolean probarConexion(String web) throws IOException {

		int puerto = 80;

		Socket s = new Socket(web, puerto);

		boolean resultado = false;

		try {

			if (s.isConnected()) {
				resultado = true;
			}

			else {
				resultado = false;
			}

			return resultado;

		}

		catch (Exception e) {
			return resultado;
		}

		finally {
			s.close();
		}

	}

	public static void executeScript(Connection conn, InputStream in) throws SQLException {

		Scanner s = new Scanner(in);
		s.useDelimiter("/\\*[\\s\\S]*?\\*/|--[^\\r\\n]*|;");

		Statement st = null;

		try {

			st = conn.createStatement();

			while (s.hasNext()) {
				String line = s.next().trim();

				if (!line.isEmpty())
					st.execute(line);
			}

		}

		finally {
			if (st != null)
				st.close();
		}
		s.close();
	}

	public static void moverArchivos(LinkedList<String> files, String separador, String destino, boolean mensaje,

			int tipo) throws IOException {

		try {

			String imagen;

			String comprobacion;

			boolean filtro = false;

			String origen;

			if (!files.isEmpty()) {

				LinkedList<String> listaImagenes = directorio(
						MenuPrincipal.getDirectorioImagenes() + MenuPrincipal.getSeparador(), ".", true, false);

				String busqueda;

				String salida;

				for (int i = 0; i < files.size(); i++) {

					imagen = files.get(i).substring(files.get(i).lastIndexOf(separador) + 1, files.get(i).length());

					comprobacion = extraerExtension(imagen);

					ArrayList<String> lista = new ArrayList<String>();

					switch (tipo) {

					case 1:

						lista.add("jpg");

						lista.add("peg");

						lista.add("png");

						lista.add("webp");

						lista.add("gif");

						break;

					case 2:

						lista.add(".ts");

						lista.add("mp4");

						lista.add("avi");

						lista.add("3gp");

						lista.add("flv");

						lista.add("m3u8");

						lista.add("mkv");

						lista.add("mov");

						lista.add("wmv");

						break;

					default:

						lista.add("jpg");

						lista.add("peg");

						lista.add("png");

						lista.add("gif");

						lista.add("webp");

						break;

					}

					if (lista.contains(comprobacion)) {

						origen = files.get(i);

						if (origen.substring(0, origen.lastIndexOf(separador)).equals(destino)) {

							mensaje("No puedes pegar archivos al mismo directorio", 3);

							i = files.size();

						}

						else {

							busqueda = origen.substring(origen.lastIndexOf(separador) + 1, origen.length());

							busqueda = eliminarPuntos(busqueda);

							salida = origen.substring(0, origen.lastIndexOf(separador)) + separador + busqueda;

							renombrar(origen, salida);

							origen = salida;

							busqueda = origen.substring(origen.lastIndexOf(separador) + 1, origen.length());

							if (listaImagenes.indexOf(busqueda) != -1) {

								salida = origen.substring(0, origen.lastIndexOf(separador) + 1);

								salida += busqueda.substring(0, busqueda.length() - 4) + "_" + i + "." + comprobacion;

								renombrar(origen, salida);

								origen = salida;
							}

							Files.move(FileSystems.getDefault().getPath(origen), FileSystems.getDefault().getPath(

									destino + separador
											+ origen.substring(origen.lastIndexOf(separador) + 1, origen.length())

							), StandardCopyOption.REPLACE_EXISTING);

							convertir(MenuPrincipal.getDirectorioActual() + "Config" + MenuPrincipal.getSeparador()
									+ "imagenes" + MenuPrincipal.getSeparador());

						}

					}

					else {
						filtro = true;
					}

				}

				if (filtro) {

					mensaje("Uno o varios archivos no tiene el formato correcto", 3);

				}

			}
		}

		catch (Exception e) {
			//
		}

	}

	public static void moverArchivosDrop(java.io.File[] files, String separador, String destino, int tipo)
			throws IOException {

		String imagen;

		String extension;

		boolean filtro = false;

		String origen;

		if (files.length > 0) {

			LinkedList<String> listaImagenes = directorio(
					MenuPrincipal.getDirectorioImagenes() + MenuPrincipal.getSeparador(), ".", true, false);

			String busqueda;

			String salida;

			boolean error = false;

			int indice = 0;

			for (int i = 0; i < files.length; i++) {

				imagen = files[i].getCanonicalPath().substring(files[i].getCanonicalPath().lastIndexOf(separador) + 1,
						files[i].getCanonicalPath().length());

				extension = extraerExtension(imagen);

				switch (extension) {

				case "jfif":

					indice = files[i].getCanonicalPath().indexOf("." + extension);

					renombrar(files[i].getCanonicalPath().substring(0, indice) + "." + extension,
							files[i].getCanonicalPath().substring(0, indice) + "_.jpg");

					files[i] = new File(files[i].getCanonicalPath().substring(0, indice) + "_.jpg");

					extension = "jpg";

					break;

				case "webm":

					indice = files[i].getCanonicalPath().indexOf("." + extension);

					renombrar(files[i].getCanonicalPath().substring(0, indice) + "." + extension,
							files[i].getCanonicalPath().substring(0, indice) + "_.mp4");

					files[i] = new File(files[i].getCanonicalPath().substring(0, indice) + "_.mp4");

					extension = "mp4";

					break;

				case "webp":

					indice = files[i].getCanonicalPath().indexOf("." + extension);

					renombrar(files[i].getCanonicalPath().substring(0, indice) + "." + extension,
							files[i].getCanonicalPath().substring(0, indice) + "_.png");

					files[i] = new File(files[i].getCanonicalPath().substring(0, indice) + "_.png");

					extension = "png";

					break;

				default:
					break;

				}

				ArrayList<String> lista = new ArrayList<String>();

				switch (tipo) {

				case 1:

					lista.add("jpg");
					lista.add("peg");
					lista.add("png");
					lista.add("gif");
					lista.add("webp");
					break;

				case 2:

					lista.add(".ts");
					lista.add("mp4");
					lista.add("avi");
					lista.add("3gp");
					lista.add("flv");
					lista.add("m3u8");
					lista.add("mkv");
					lista.add("mov");
					lista.add("wmv");

					break;

				default:

					lista.add("jpg");
					lista.add("peg");
					lista.add("png");
					lista.add("gif");
					lista.add("webp");

					break;
				}

				if (lista.contains(extension)) {

					origen = files[i].getCanonicalPath();

					if (origen.substring(0, origen.lastIndexOf(separador)).equals(destino)) {

						mensaje("No puedes pegar archivos al mismo directorio", 3);

						i = files.length;

						error = true;
					}

					else {

						busqueda = origen.substring(origen.lastIndexOf(separador) + 1, origen.length());
						busqueda = eliminarPuntos(busqueda);

						salida = origen.substring(0, origen.lastIndexOf(separador)) + separador + busqueda;

						renombrar(origen, salida);

						origen = salida;

						busqueda = origen.substring(origen.lastIndexOf(separador) + 1, origen.length());

						if (listaImagenes.indexOf(busqueda) != -1) {

							salida = origen.substring(0, origen.lastIndexOf(separador) + 1);

							salida += busqueda.substring(0, busqueda.length() - 4) + "_" + i + "." + extension;

							renombrar(origen, salida);

							origen = salida;
						}

						Files.move(FileSystems.getDefault().getPath(origen), FileSystems.getDefault().getPath(

								destino + separador
										+ origen.substring(origen.lastIndexOf(separador) + 1, origen.length())

						), StandardCopyOption.REPLACE_EXISTING);

					}

				} else {
					filtro = true;
				}
			}

			if (filtro) {
				mensaje("Uno o varios archivos no tiene el formato correcto", 3);
			}

			else {

				if (!error) {
					mensaje("Los archivos se han movido correctamente", 2);
				}

			}

		}

	}

	public static java.io.File[] seleccionar(int tipo, String rotulo, String mensaje) {

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = null;

		switch (tipo) {

		case 1:
			filter = new FileNameExtensionFilter(rotulo, "jpg");
			chooser.setFileFilter(filter);
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			break;

		case 2:
			filter = new FileNameExtensionFilter(rotulo, "jpg", "gif", "jpeg", "png", "avi", "mp4");
			chooser.setFileFilter(filter);
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			break;

		case 3:
			filter = new FileNameExtensionFilter(rotulo, "txt");
			chooser.setFileFilter(filter);
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			break;

		default:
			break;
		}

		if (!chooser.isMultiSelectionEnabled()) {
			chooser.setMultiSelectionEnabled(true);
		}

		chooser.showOpenDialog(chooser);
		File[] files = chooser.getSelectedFiles();

		if (files.length == 0) {
			mensaje(mensaje, 3);
		}

		return files;
	}

	public static void ejecutar(LinkedList<String> contenido, String tipo, String descripcion, int paso, int size)
			throws SQLException, IOException {

		try {

			Connection conexion = conexionBD();

			Statement s = conexion.createStatement();

			for (; paso <= size; paso++) {

				if (!contenido.get(paso).isEmpty()) {

					s.executeUpdate("INSERT INTO notas (nombre,tipo,descripcion) VALUES('" + contenido.get(paso) + "','"
							+ tipo + "','" + descripcion + "')");
				}

				s.close();
			}

			conexion.close();

		} catch (Exception e) {

			int y = ++paso;

			if (y <= size) {
				ejecutar(contenido, tipo, descripcion, y, size);
			}
		}
	}

	public static String convertToUTF8(String s) {

		String out = "";

		out = new String(s.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);

		return out;
	}

	public static void reproducirSonido(String nombreSonido, boolean repetir) {

		try {

			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());

			Clip clip = AudioSystem.getClip();

			clip.open(audioInputStream);

			clip.start();

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			//
		}

	}

	public static String eliminarEspacios(String cadena, boolean filtro) {

		cadena = cadena.trim();

		cadena = cadena.replace("  ", " ");

		cadena = cadena.trim();

		if (filtro) {
			cadena = cadena.replace(" ", "");
		}

		return cadena;

	}

	public static void muestraContenido(String archivo, String tipo, String descripcion) throws IOException {

		FileReader fr = null;

		BufferedReader br = null;

		try {

			fr = new FileReader(archivo);

			br = new BufferedReader(fr);

			String linea;

			LinkedList<String> contenido = new LinkedList<>();

			while ((linea = br.readLine()) != null) {
				linea = eliminarEspacios(linea, false);
				contenido.add(convertToUTF8(linea));
			}

			HashSet<String> hs = new HashSet<>();

			hs.addAll(contenido);

			contenido.clear();

			contenido.addAll(hs);

			Connection conexion = conexionBD();

			int size = contenido.size();

			int i = 0;

			try {

				Statement s = conexion.createStatement();

				for (i = 0; i < size; i++) {

					if (!contenido.get(i).isEmpty()) {
						s.executeUpdate("INSERT INTO notas (nombre,tipo,descripcion) VALUES('" + contenido.get(i)
								+ "','" + tipo + "','" + descripcion + "')");
					}

				}

				s.close();
			}

			catch (Exception e) {
				conexion.close();
				int y = ++i;
				ejecutar(contenido, tipo, descripcion, y, size);
			}

			conexion.close();
		}

		catch (Exception e) {
//
		}

		finally {

			try {

				if (null != fr) {

					fr.close();

				}

			} catch (Exception e2) {
//

			}

		}

	}

	public static String[] leerFicheroArray(String fichero, int longitud) throws IOException {

		String[] salida = new String[longitud];

		fichero = MenuPrincipal.getDirectorioActual() + "Config" + MenuPrincipal.getSeparador() + fichero;

		File archivo = new File(fichero);

		if (archivo.exists()) {

			String texto = "";

			int i = 0;

			FileReader flE = null;

			BufferedReader fE = null;

			try {

				flE = new FileReader(fichero);

				fE = new BufferedReader(flE);

				texto = fE.readLine();

				while (texto != null && i < longitud) {

					salida[i] = texto;

					i++;

					texto = fE.readLine();

				}

				fE.close();

				flE.close();

			}

			catch (Exception e) {
				//
			}

			finally {

				if (fE != null) {

					try {
						fE.close();
					}

					catch (IOException e) {
						//
					}

				}

				if (flE != null) {

					try {

						flE.close();

					}

					catch (IOException e) {
						//
					}

				}
			}
		}

		else {

			throw new IOException();

		}

		return salida;

	}

	public static void verConfig(int tipo, boolean mensaje) {

		try {

			if (mensaje) {

				Metodos.mensaje("Por favor, revisa la configuración", 3);

			}

			switch (tipo) {

			case 1:

				new Config().setVisible(true);

				break;

			case 2:

				new Config2().setVisible(true);

				break;

			case 3:

				new Bd().setVisible(true);

				break;

			}

		}

		catch (Exception e) {

		}

	}

	public static void exportarBd(String archivo, List<String> tablas) throws IOException {

		String[] backup = leerFicheroArray("Backup.txt", 1);

		LinkedList<String> datos = new LinkedList<>();

		try {

			String ruta = backup[0] + MenuPrincipal.getSeparador() + archivo + ".sql";

			String contenido = "CREATE TABLE bots (\r\n" + "id int(11) AUTO_INCREMENT PRIMARY KEY,\r\n"
					+ "IP varchar(64) NOT NULL UNIQUE\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n"
					+ "CREATE TABLE tbl_tracking (\r\n" + "id_tracking int(11) PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ "tx_pagina varchar(70) NOT NULL DEFAULT '',\r\n"
					+ "tx_paginaOrigen varchar(200) NOT NULL DEFAULT '',\r\n"
					+ "tx_ipRemota varchar(15) NOT NULL DEFAULT '',\r\n"
					+ "tx_navegador varchar(255) NOT NULL DEFAULT '',\r\n" + "dt_fechaVisita date NOT NULL,\r\n"
					+ "pais varchar(25) NOT NULL,\r\n" + "ciudad varchar(30) NOT NULL\r\n"
					+ ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE polaco(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40) NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE coreano(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40) NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n"
					+ "CREATE TABLE vietnamita (\r\n" + "id int(11)PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ "accion varchar(40) NOT NULL UNIQUE,\r\n" + "texto text NOT NULL\r\n"
					+ ")DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE aleman(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40) NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE ingles(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40 )NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE spanish(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40) NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE frances(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40) NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE portuges(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40) NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE italiano(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40) NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE hindu(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40) NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE chino(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40) NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE japones(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40) NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE arabe(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40) NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE bengali(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40) NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE catalan(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40) NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE euskera(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40) NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE ruso(\r\n"
					+ "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "accion varchar(40) NOT NULL UNIQUE,\r\n"
					+ "texto text NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE "
					+ MenuPrincipal.getLecturabd()[3] + "users (\r\n"
					+ "user_id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "user_level int(11) NOT NULL DEFAULT '1',\r\n"
					+ "user_name varchar(255) NOT NULL UNIQUE,\r\n"
					+ "user_password varchar(255) NOT NULL DEFAULT '',\r\n" + "user_email varchar(255) NOT NULL,\r\n"
					+ "user_allowemails tinyint(1) NOT NULL DEFAULT '1',\r\n"
					+ "user_invisible tinyint(1) NOT NULL DEFAULT '0',\r\n"
					+ "user_comments int(11) NOT NULL DEFAULT '0',\r\n"
					+ "nacionalidad varchar(15) DEFAULT 'spanish' NOT NULL,\r\n"
					+ "avatar varchar(255) DEFAULT 'nofoto.jpg' NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n"
					+ "CREATE TABLE mensajes (\r\n" + "id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ "remitente int(11),\r\n" + "destinatario  int(11),\r\n" + "asunto varchar(30),\r\n"
					+ "mensaje text,\r\n" + "leido tinyint(1) DEFAULT '0' NOT NULL,\r\n"
					+ "oculto tinyint(1) DEFAULT '0' NOT NULL,\r\n" + "FOREIGN KEY (remitente) REFERENCES "
					+ MenuPrincipal.getLecturabd()[3] + "users (user_id),\r\n"
					+ "FOREIGN KEY (destinatario) REFERENCES " + MenuPrincipal.getLecturabd()[3] + "users (user_id)\r\n"
					+ ");\r\n" + "\r\n" + "CREATE TABLE " + MenuPrincipal.getLecturabd()[3] + "sessions (\r\n"
					+ "session_id int(11),\r\n" + "session_user_id int(11),\r\n"
					+ "session_lastaction int(11) NOT NULL DEFAULT '0',\r\n"
					+ "session_location varchar(255) NOT NULL DEFAULT '',\r\n"
					+ "session_ip varchar(15) NOT NULL DEFAULT '',\r\n" + "session_date date,\r\n"
					+ "FOREIGN KEY (session_user_id) REFERENCES " + MenuPrincipal.getLecturabd()[3]
					+ "users (user_id),\r\n" + "PRIMARY KEY (session_id , session_user_id)\r\n"
					+ ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE " + MenuPrincipal.getLecturabd()[3]
					+ "lightboxes (\r\n" + "user_id int(11),\r\n" + "lightbox_image_id int(11),\r\n"
					+ "orden int(11),\r\n" + "PRIMARY KEY (user_id ,lightbox_image_id),\r\n"
					+ "FOREIGN KEY (user_id) REFERENCES " + MenuPrincipal.getLecturabd()[3] + "users (user_id),\r\n"
					+ "FOREIGN KEY (user_id) REFERENCES " + MenuPrincipal.getLecturabd()[3] + "users (user_id)\r\n"
					+ ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE " + MenuPrincipal.getLecturabd()[3]
					+ "categories (\r\n" + "cat_id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ "cat_name varchar(255) NOT NULL UNIQUE,\r\n" + "cat_description text NOT NULL,\r\n"
					+ "cat_parent_id int(11) NOT NULL DEFAULT '0',\r\n" + "cat_hits int(11) NOT NULL DEFAULT '0',\r\n"
					+ "auth_download tinyint(1) NOT NULL DEFAULT '1',\r\n"
					+ "auth_upload tinyint(1) NOT NULL DEFAULT '1',\r\n"
					+ "auth_vote tinyint(1) NOT NULL DEFAULT '1',\r\n"
					+ "auth_sendpostcard tinyint(1) NOT NULL DEFAULT '1',\r\n"
					+ "visibilidad TINYINT NOT NULL DEFAULT '1',\r\n" + "permitir_gif TINYINT NOT NULL DEFAULT '1'\r\n"
					+ ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE " + MenuPrincipal.getLecturabd()[3]
					+ "images (\r\n" + "image_id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ "cat_id int(11) NOT NULL DEFAULT '0',\r\n" + "user_id int(11) NOT NULL DEFAULT '0',\r\n"
					+ "image_name varchar(255) NOT NULL,\r\n" + "image_description text,\r\n"
					+ "image_keywords text,\r\n" + "image_date date NOT NULL,\r\n"
					+ "image_active tinyint(1) NOT NULL DEFAULT '1',\r\n"
					+ "image_media_file varchar(255) NOT NULL UNIQUE,\r\n"
					+ "image_allow_comments tinyint(1) NOT NULL DEFAULT '1',\r\n"
					+ "image_comments int(11) NOT NULL DEFAULT '0',\r\n"
					+ "image_downloads int(11) NOT NULL DEFAULT '0',\r\n"
					+ "image_votes int(11) NOT NULL DEFAULT '0',\r\n"
					+ "image_rating decimal(4,2) NOT NULL DEFAULT '0.00',\r\n"
					+ "image_hits int(11) NOT NULL DEFAULT '0',\r\n" + "sha256 varchar(64) NOT NULL,\r\n"
					+ "descargable tinyint(1) NOT NULL DEFAULT '1',\r\n"
					+ "nivel_descarga tinyint(1) NOT NULL DEFAULT '2',\r\n"
					+ "nivel_comentario tinyint(1) NOT NULL DEFAULT '2',\r\n" + "FOREIGN KEY (cat_id) REFERENCES "
					+ MenuPrincipal.getLecturabd()[3] + "categories (cat_id),\r\n" + "FOREIGN KEY (user_id) REFERENCES "
					+ MenuPrincipal.getLecturabd()[3] + "users (user_id)\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n"
					+ "CREATE TABLE " + MenuPrincipal.getLecturabd()[3] + "comments (\r\n"
					+ "comment_id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ "image_id int(11) NOT NULL DEFAULT '0',\r\n" + "user_id int(11) NOT NULL DEFAULT '0',\r\n"
					+ "comment_headline varchar(255) NOT NULL DEFAULT '',\r\n" + "comment_text text NOT NULL,\r\n"
					+ "comment_ip varchar(20) NOT NULL DEFAULT '',\r\n" + "comment_date date NOT NULL,\r\n"
					+ "visible tinyint(1) NOT NULL DEFAULT '0',\r\n" + "FOREIGN KEY(image_id) REFERENCES "
					+ MenuPrincipal.getLecturabd()[3] + "images (image_id) ON DELETE CASCADE,\r\n"
					+ "FOREIGN KEY(user_id) REFERENCES  " + MenuPrincipal.getLecturabd()[3] + "images (user_id)\r\n"
					+ ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE " + MenuPrincipal.getLecturabd()[3]
					+ "scrapting (\r\n" + "comment_id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ "image_id int(11) NOT NULL DEFAULT '0',\r\n" + "user_id int(11) NOT NULL DEFAULT '0',\r\n"
					+ "comment_headline varchar(255) NOT NULL DEFAULT '',\r\n" + "comment_text text NOT NULL,\r\n"
					+ "comment_ip varchar(20) NOT NULL DEFAULT '',\r\n" + "comment_date date NOT NULL,\r\n"
					+ "visible tinyint(1) NOT NULL DEFAULT '0',\r\n" + "tag varchar(30) NOT NULL DEFAULT 'scrapt',\r\n"
					+ "FOREIGN KEY (image_id) REFERENCES " + MenuPrincipal.getLecturabd()[3]
					+ "images (image_id) ON DELETE CASCADE,\r\n" + "FOREIGN KEY (user_id) REFERENCES "
					+ MenuPrincipal.getLecturabd()[3] + "users (user_id)\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n"
					+ "CREATE TABLE " + MenuPrincipal.getLecturabd()[3] + "etiquetas (\r\n"
					+ "id int(11) PRIMARY KEY,\r\n" + "nombre varchar(20) UNIQUE\r\n" + ") DEFAULT CHARSET=utf8;\r\n"
					+ "\r\n" + "CREATE TABLE " + MenuPrincipal.getLecturabd()[3] + "tags (\r\n"
					+ "id_imagen int(11),\r\n" + "id_tag int(11),\r\n" + "FOREIGN KEY (id_imagen) REFERENCES "
					+ MenuPrincipal.getLecturabd()[3] + "images (image_id),\r\n" + "FOREIGN KEY (id_tag) REFERENCES "
					+ MenuPrincipal.getLecturabd()[3] + "etiquetas (id),\r\n" + "PRIMARY KEY (id_imagen,id_tag)\r\n"
					+ ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE " + MenuPrincipal.getLecturabd()[3]
					+ "groups (\r\n" + "group_id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ "group_name varchar(25) NOT NULL UNIQUE\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n"
					+ "CREATE TABLE " + MenuPrincipal.getLecturabd()[3] + "imgroups (\r\n"
					+ "id_group int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "user_id int(11),\r\n"
					+ "nombre varchar(30),\r\n" + "parent_group int(11),\r\n" + "description varchar(50),\r\n"
					+ "nivel_visible tinyint(1) NOT NULL,\r\n" + "nivel_descarga tinyint(1) NOT NULL,\r\n"
					+ "nivel_comentario tinyint(1) NOT NULL,\r\n" + "FOREIGN KEY(user_id) REFERENCES "
					+ MenuPrincipal.getLecturabd()[3] + "users(user_id)\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n"
					+ "CREATE TABLE " + MenuPrincipal.getLecturabd()[3] + "msgroups (\r\n"
					+ "id_group int(11) PRIMARY KEY AUTO_INCREMENT,\r\n" + "nombre varchar(30),\r\n"
					+ "parent_group int(11) NOT NULL DEFAULT 0,\r\n" + "description varchar(50)\r\n"
					+ ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE " + MenuPrincipal.getLecturabd()[3]
					+ "musugroup (\r\n" + "usuario int(11),\r\n" + "grupo int(11),\r\n"
					+ "FOREIGN KEY(usuario) REFERENCES " + MenuPrincipal.getLecturabd()[3] + "users (user_id),\r\n"
					+ "FOREIGN KEY(grupo) REFERENCES " + MenuPrincipal.getLecturabd()[3] + "msgroups (id_group),\r\n"
					+ "PRIMARY KEY(usuario,grupo)\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE "
					+ MenuPrincipal.getLecturabd()[3] + "video (\r\n" + "video_id int(11) PRIMARY KEY,\r\n"
					+ "nombre varchar(30) NOT NULL,\r\n" + "ruta varchar(50) NOT NULL,\r\n"
					+ "nivel_visible tinyint(1) NOT NULL\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE "
					+ MenuPrincipal.getLecturabd()[3] + "imv (\r\n" + "imagen int(11),\r\n" + "video int(11),\r\n"
					+ "FOREIGN KEY (imagen) REFERENCES " + MenuPrincipal.getLecturabd()[3] + "images (image_id),\r\n"
					+ "FOREIGN KEY (video) REFERENCES " + MenuPrincipal.getLecturabd()[3] + "video (video_id),\r\n"
					+ "PRIMARY KEY(imagen)\r\n" + ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE "
					+ MenuPrincipal.getLecturabd()[3] + "videocomments (\r\n"
					+ "comment_id int(11) PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ "video_id int(11) NOT NULL DEFAULT '0',\r\n" + "user_id int(11) NOT NULL DEFAULT '0',\r\n"
					+ "comment_headline varchar(255) NOT NULL DEFAULT '',\r\n" + "comment_text text NOT NULL,\r\n"
					+ "comment_ip varchar(20) NOT NULL DEFAULT '',\r\n" + "comment_date date NOT NULL,\r\n"
					+ "visible tinyint(1) NOT NULL DEFAULT '0',\r\n" + "FOREIGN KEY (video_id) REFERENCES "
					+ MenuPrincipal.getLecturabd()[3] + "video (video_id) ON DELETE CASCADE,\r\n"
					+ "FOREIGN KEY (user_id) REFERENCES " + MenuPrincipal.getLecturabd()[3] + "users (user_id)\r\n"
					+ ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE notas (\r\n"
					+ "id int(11) AUTO_INCREMENT PRIMARY KEY,\r\n" + "Nombre varchar(50) NOT NULL UNIQUE,\r\n"
					+ "tipo varchar(50) NOT NULL,\r\n" + "descripcion varchar(255) NOT NULL\r\n"
					+ ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE antispam (\r\n"
					+ "id int(11) AUTO_INCREMENT PRIMARY KEY,\r\n" + "Nombre varchar(25) NOT NULL UNIQUE\r\n"
					+ ") DEFAULT CHARSET=utf8;\r\n" + "\r\n" + "CREATE TABLE descargas (\r\n" + "usuario int(11),\r\n"
					+ "imagen int(11),\r\n" + "FOREIGN KEY (usuario) REFERENCES " + MenuPrincipal.getLecturabd()[3]
					+ "users(user_id),\r\n" + "FOREIGN KEY (imagen) REFERENCES " + MenuPrincipal.getLecturabd()[3]
					+ "images(image_id),\r\n" + "PRIMARY KEY (usuario,imagen)\r\n" + ") DEFAULT CHARSET=utf8;\r\n"
					+ "\r\n"
					+ "CREATE OR REPLACE VIEW ver_bots AS SELECT distinct(tx_ipRemota) FROM tbl_tracking where tx_navegador like '%crawler%' OR tx_navegador like '%Bot%' AND tx_ipRemota!='127.0.0.1';\r\n\r\n"
					+ "CREATE OR REPLACE VIEW pais_desconocido AS SELECT distinct(tx_ipRemota) from tbl_tracking where pais='unknow';\r\n";

			File file = new File(ruta);

			if (!file.exists()) {

				file.createNewFile();

			}

			String prefix = MenuPrincipal.getLecturabd()[3];

			FileWriter fw = new FileWriter(file);

			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(contenido);

			bw.newLine();

			int columnas = 0;

			String tabla = "";

			for (int i = 0; i < tablas.size(); i++) {

				switch (tablas.get(i)) {

				case "users":

					tabla = prefix + "users";

					columnas = 10;

					break;

				case "comments":

					tabla = prefix + "comments";

					columnas = 8;

					break;

				case "categories":

					tabla = prefix + "categories";

					columnas = 15;

					break;

				case "etiquetas":

					tabla = prefix + "etiquetas";

					columnas = 2;

					break;

				case "groups":

					tabla = prefix + "groups";

					columnas = 2;

					break;

				case "images":

					tabla = prefix + "images";

					columnas = 19;

					break;

				case "imgroups":

					tabla = prefix + "imgroups";

					columnas = 8;

					break;

				case "imv":

					tabla = prefix + "imv";

					columnas = 2;

					break;

				case "lightboxes":

					tabla = prefix + "lightboxes";

					columnas = 3;

					break;

				case "msgroups":

					tabla = prefix + "msgroups";

					columnas = 4;

					break;

				case "musugroup":

					tabla = prefix + "musugroup";

					columnas = 2;

					break;

				case "scrapting":

					tabla = prefix + "scrapting";

					columnas = 9;

					break;

				case "tags":

					tabla = prefix + "tags";

					columnas = 2;

					break;

				case "video":

					tabla = prefix + "video";

					columnas = 4;

					break;

				case "videocomments":

					tabla = prefix + "videocomments";

					columnas = 8;

					break;

				case "antispam":

					tabla = prefix + "antispam";

					columnas = 2;

					break;

				case "bots":

					tabla = "bots";

					columnas = 2;

					break;

				case "descargas":

					tabla = "descargas";

					columnas = 2;

					break;

				case "grupos":

					tabla = "grupos";

					columnas = 2;

					break;

				case "mensajes":

					tabla = "mensajes";

					columnas = 7;

					break;

				case "notas":

					tabla = "notas";

					columnas = 4;

					break;

				case "tbl_tracking":

					tabla = "tbl_tracking";

					columnas = 8;

					break;

				case "aleman":

					tabla = "aleman";

					columnas = 3;

					break;

				case "arabe":

					tabla = "arabe";

					columnas = 3;

					break;

				case "bengali":

					tabla = "bengali";

					columnas = 3;

					break;

				case "catalan":

					tabla = "catalan";

					columnas = 3;

					break;

				case "chino":

					tabla = "chino";

					columnas = 3;

					break;

				case "coreano":

					tabla = "coreano";

					columnas = 3;

					break;

				case "euskera":

					tabla = "euskera";

					columnas = 3;

					break;

				case "frances":

					tabla = "frances";

					columnas = 3;

					break;

				case "hindu":

					tabla = "hindu";

					columnas = 3;

					break;

				case "ingles":

					tabla = "ingles";

					columnas = 3;

					break;

				case "italiano":

					tabla = "italiano";

					columnas = 3;

					break;

				case "japones":

					tabla = "japones";

					columnas = 3;

					break;

				case "polaco":

					tabla = "polaco";

					columnas = 3;

					break;

				case "portuges":

					tabla = "portuges";

					columnas = 3;

					break;

				case "ruso":

					tabla = "ruso";

					columnas = 3;

					break;

				case "spanish":

					tabla = "spanish";

					columnas = 3;

					break;

				case "vietnamita":

					tabla = "vietnamita";

					columnas = 3;

					break;

				default:
					break;

				}

				datos = mostrarDatosTabla(tabla, columnas);

				bw.write(escribirInserts(tabla, datos, columnas));

				bw.newLine();
			}

			bw.close();

		}

		catch (Exception e) {
			//
		}

		mensaje("Backup realizado correctamente", 2);

		abrirCarpeta(backup[0]);

	}

	private static String escribirInserts(String tabla, LinkedList<String> datos, int filas) {

		String espaciador;

		String sentencia = "INSERT INTO " + tabla + " VALUES ";

		int y = 1;

		for (int i = 0; i < datos.size(); i++) {

			espaciador = "),(";

			if (i == 0) {
				sentencia += "(";
			}

			sentencia += "'" + datos.get(i) + "'";

			i++;

			if (y == filas && i < datos.size()) {

				sentencia += espaciador;

				y = 1;

			}

			else {

				if (i < datos.size()) {

					sentencia += ",";

				}

				y++;
			}

			i--;

		}

		sentencia += ");";

		return sentencia;

	}

	public static Integer mostrarGaleriaSql(String busqueda, String sql, String sql2) throws SQLException, IOException {

		int recuento = 0;

		Connection conexion = conexionBD();

		Statement s = conexion.createStatement();

		ResultSet rs = s.executeQuery(sql);

		rs.next();

		recuento = Integer.parseInt(rs.getString("count(image_id)"));

		if (recuento > 0) {

			if (recuento > 500) {

				mensaje("Has introducido un nombre que está en más de 500 imágenes", 3);

				mensaje("Por favor,introduce un nombre con menos registros para mostrarlos", 2);

				abrirCarpeta(obtenerEnlaceCms(MenuPrincipal.getLecturaurl()[0], MenuPrincipal.getLecturaurl()[1])
						+ "/search.php?filtro=" + busqueda);

			}

			else {

				rs = s.executeQuery(sql2);

				MenuPrincipal.getListaImagenes().clear();

				MenuPrincipal.getCategorias().clear();

				while (rs.next()) {

					MenuPrincipal.getListaImagenes().add(rs.getString("image_media_file"));

					MenuPrincipal.getCategorias().add(rs.getString("cat_id"));

				}

			}

			s.close();

			rs.close();

			conexion.close();

			if (recuento == 0) {

				mensaje("No hay resultados, intente con otro nombre", 2);

			}

			else {

				if (recuento <= 500) {

					try {

						new Galeria();

						InterfazGaleria.setNumImagenes(recuento);

						new InterfazGaleria().setVisible(true);
					}

					catch (Exception e1) {

						mensaje("No se han podido cargar las imágenes", 1);

						mensaje("Por favor, revisa la configuración", 3);

						new Config2().setVisible(true);

					}

				}

			}

		}

		else {

			mensaje("La búsqueda no tiene resultados", 3);

		}

		return recuento;
	}

	public static void mostrarGaleriaConWhere(String busqueda) {

		try {

			if (comprobarConexionBd("SELECT COUNT(image_id) FROM " + MenuPrincipal.getLecturabd()[3] + "images",
					"COUNT(image_id)")) {

				try {

					if (comprobarConfiguracion()) {

						String sql = "";

						String sql2 = "";

						sql = "select count(image_id) from " + MenuPrincipal.getLecturabd()[3] + "images WHERE "
								+ busqueda;

						sql2 = "select image_media_file,cat_id from " + MenuPrincipal.getLecturabd()[3]
								+ "images WHERE " + busqueda;

						int recuento;

						if (!busqueda.isEmpty()) {

							Connection conexion = conexionBD();

							Statement s = conexion.createStatement();

							ResultSet rs = s.executeQuery(sql);

							rs.next();

							recuento = Integer.parseInt(rs.getString("count(image_id)"));

							if (recuento > 0) {

								if (recuento > 500) {

									mensaje("Has introducido un nombre que está en más de 500 imágenes", 3);

									mensaje("Por favor,introduce un nombre con menos registros para mostrarlos", 2);

									abrirCarpeta(obtenerEnlaceCms(MenuPrincipal.getLecturaurl()[0],
											MenuPrincipal.getLecturaurl()[1]) + "/search.php?filtro=" + busqueda);

								}

								else {

									rs = s.executeQuery(sql2);

									MenuPrincipal.getListaImagenes().clear();

									MenuPrincipal.getCategorias().clear();

									while (rs.next()) {

										MenuPrincipal.getListaImagenes().add(rs.getString("image_media_file"));

										MenuPrincipal.getCategorias().add(rs.getString("cat_id"));

									}

									s.close();

									rs.close();

									conexion.close();

									if (recuento == 0) {

										mensaje("No hay resultados, intente con otro nombre", 2);

									}

									else {

										if (recuento < 500) {

											try {

												new Galeria();
												new InterfazGaleria().setVisible(true);
											}

											catch (Exception e1) {

												mensaje("No se han podido cargar las imágenes", 1);

												new Config2().setVisible(true);

											}

										}
									}

								}

							}

							else {
								mensaje("La búsqueda no tiene resultados", 3);
							}

						}

					}

				}

				catch (Exception e1) {
					mensaje("Revise la búsqueda", 3);
				}
			}
		}

		catch (SQLException e1) {

			try {
				new Bd().setVisible(true);
			}

			catch (IOException e11) {
				//
			}

		}
	}

	public static void crearScript(String archivo, String contenido, boolean opcional, String os) throws IOException {

		try {

			Process aplicacion = null;

			if (os.equals("Linux")) {
				aplicacion = Runtime.getRuntime().exec("bash " + contenido);

			}

			else {

				String iniciar = "";

				if (opcional) {
					iniciar = "start";
				}

				FileWriter flS = new FileWriter(archivo);

				BufferedWriter fS = new BufferedWriter(flS);

				try {

					Runtime aplicacion2 = Runtime.getRuntime();

					fS.write("@echo off");

					fS.newLine();

					fS.write(contenido);

					fS.newLine();

					fS.write("exit");

					aplicacion2 = Runtime.getRuntime();

					aplicacion2.exec("cmd.exe /K " + iniciar + " " + System.getProperty("user.dir") + "\\" + archivo);

				}

				finally {

					fS.close();

					flS.close();

				}

			}

			aplicacion.destroy();

		}

		catch (Exception e) {
		}
	}

	public static void python(String script) throws IOException {

		Runtime.getRuntime().exec(script);

		task.cancel();

		task = new CronWebp();

		timer = new Timer();

		timer.schedule(task, 0, 1000);

	}

	public static void ponerCategoriasBd(JComboBox<String> combobox) throws SQLException, IOException {

		combobox.setFont(new Font("Tahoma", Font.BOLD, 24));

		ArrayList<String> categorias = verCategorias();

		if (!categorias.isEmpty()) {

			try {

				for (int x = 0; x < categorias.size(); x++) {
					combobox.addItem(categorias.get(x));
				}

			}

			catch (Exception e) {
				mensaje("Error al cargar las categorías", 1);
			}

		}

	}

	public static ArrayList<String> verCategorias() throws SQLException, IOException {

		String[] lectura = leerFicheroArray("Bd.txt", 6);

		ArrayList<String> categorias = new ArrayList<>();

		if (!lectura[3].isEmpty() && conexionBD() != null) {

			ResultSet rs;
			Connection conexion;
			Statement s;

			try {

				conexion = conexionBD();
				s = conexion.createStatement();
				rs = s.executeQuery("SELECT cat_name,cat_id FROM " + MenuPrincipal.getLecturabd()[3]
						+ "categories WHERE cat_parent_id>0 UNION SELECT cat_name,cat_id FROM 4images_categories WHERE cat_parent_id=0 AND cat_id NOT IN (SELECT DISTINCT(cat_parent_id) FROM 4images_categories WHERE	cat_parent_id!=0) order by cat_name");

				while (rs.next()) {

					categorias.add(rs.getString("cat_name"));
					MenuPrincipal.setCategorias(rs.getString("cat_name"));
					MenuPrincipal.setIdCategorias(rs.getString("cat_id"));
				}

				s.close();
				rs.close();
				conexion.close();
			}

			catch (Exception e) {
				new Bd().setVisible(true);
			}

		}

		return categorias;
	}

	public static void eliminarArchivos(String ruta, String extension) throws IOException {

		LinkedList<String> frames = directorio(ruta, extension, true, false);

		for (int i = 0; i < frames.size(); i++) {

			if (!frames.get(i).isEmpty()) {

				eliminarFichero(ruta + MenuPrincipal.getSeparador() + frames.get(i));
			}

		}

	}

	public static void eliminarArchivos(LinkedList<String> lista, String ruta) throws IOException {

		File carpeta = new File(ruta);

		if (carpeta.isDirectory()) {

			for (int i = 0; i < lista.size(); i++) {

				if (!lista.get(i).isEmpty()) {

					eliminarFichero(lista.get(i));

				}

			}

			String separador = MenuPrincipal.getSeparador();

			carpeta = new File(ruta);

			LinkedList<String> archivos = new LinkedList<String>();

			LinkedList<String> carpetas = new LinkedList<String>();

			archivos = directorio(ruta, ".", true, false);

			carpetas = directorio(ruta, ".", false, false);

			boolean numeroArchivos = false;

			if (carpetas.size() > 0) {

				for (int i = 0; i < carpetas.size(); i++) {

					if (!numeroArchivos
							&& directorio(ruta + carpetas.get(i) + separador, ".", true, false).size() > 0) {
						numeroArchivos = true;
						i = carpetas.size();
					}

				}

			}

			boolean comprobacion = false;

			if (ruta.equals(MenuPrincipal.getDirectorioImagenes())
					|| ruta.equals(MenuPrincipal.getDirectorioImagenes() + MenuPrincipal.getSeparador()
							+ "imagenes_subidas" + MenuPrincipal.getSeparador())
					|| ruta.equals(MenuPrincipal.getDirectorioImagenes() + MenuPrincipal.getSeparador() + "bn"
							+ MenuPrincipal.getSeparador())) {
				comprobacion = true;
			}

			if (!comprobacion && archivos.size() == 0 && !numeroArchivos) {

				int resp = JOptionPane.showConfirmDialog(null,
						"<html><h2>La carpeta se ha quedado vacía, ¿quiere borrar la carpeta?</h2></html>");

				if (resp == 0) {

					if (carpetas.size() > 0) {

						for (int i = 0; i < carpetas.size(); i++) {
							carpeta = new File(ruta + carpetas.get(i) + separador);
							carpeta.delete();
						}
					}

					carpeta = new File(ruta);

					carpeta.delete();

				}

			}

		}
	}

	public static void eliminarFichero(String archivo) throws IOException {

		File fichero = new File(archivo);

		if (fichero.exists()) {

			if (!fichero.isDirectory()) {

				fichero.delete();

			} else {

				FileUtils.deleteDirectory(new File(archivo));

			}
		}

	}

	public static boolean comprobarConexionBd(String sql, String fila) throws SQLException {

		boolean resultado = false;

		Connection conexion = null;

		Statement s = null;

		ResultSet rs = null;

		try {

			conexion = conexionBD();

			s = conexion.createStatement();

			rs = s.executeQuery(sql);

			rs.next();

			if (!rs.getString(fila).equals("")) {
				resultado = true;
			}

			s.close();

			rs.close();

		}

		catch (SQLException e) {
			mensaje("No hay registros en la base de datos", 3);
		}

		catch (Exception e) {

			try {
				new Bd().setVisible(true);
			}

			catch (IOException e1) {
				//
			}

		}

		finally {

			if (s != null) {
				s.close();
			}

			if (rs != null) {
				rs.close();
			}

		}

		return resultado;
	}

	public static String saberFecha() {

		Calendar c = Calendar.getInstance();

		String mes = Integer.toString(c.get(Calendar.MONTH) + 1);

		if (Integer.parseInt(mes) <= 9) {
			mes = "0" + mes;
		}

		return Integer.toString(c.get(Calendar.YEAR)) + "-" + mes + "-" + Integer.toString(c.get(Calendar.DATE));
	}

	public static void comprobarConexion(String archivo, String ruta) throws IOException {

		File af = new File(archivo);

		if (af.exists()) {

			File comprobacion = new File(ruta);

			if (!comprobacion.exists()) {

				mensaje("Ruta inválida ", 1);

				new Config().setVisible(true);
			}

			else {

				abrirCarpeta(ruta);
			}

		}

		else {
			new Config().setVisible(true);
		}
	}

	public static boolean comprobarConexion(boolean mensaje) throws IOException {

		boolean conexion = false;

		try {

			String[] lectura2 = leerFicheroArray("Bd.txt", 6);

			if (!lectura2[5].isEmpty()) {

				InetAddress ping;

				ping = InetAddress.getByName(lectura2[5]);

				if (!ping.getCanonicalHostName().equals("")) {
					conexion = true;
				}

			}

		}

		catch (Exception e) {

			if (mensaje) {
				mensaje("Por favor, rellena la configuración de la base de datos", 3);
			}
		}

		return conexion;

	}

	public static boolean comprobarConfiguracion() throws IOException {

		boolean comprobacion = false;

		ArrayList<String> categorias = comprobarConexionBD();

		if (comprobarConexion(true) && categorias.size() > 0) {

			comprobacion = true;
		}

		else {

			if (!probarConexion("www.google.com")) {
				mensaje("No tienes Internet", 1);
			}

			try {

				if (conexionBD() != null) {
					comprobacion = true;
				}

			}

			catch (SQLException e) {
				new Bd().setVisible(true);
			}

		}

		return comprobacion;

	}

	public static ArrayList<String> comprobarConexionBD() throws IOException {

		ArrayList<String> categorias = null;

		try {
			categorias = verCategorias();
		}

		catch (Exception e1) {
			new Bd().setVisible(true);
		}

		return categorias;
	}

	public static Connection conexionBD() throws SQLException, IOException {

		String[] lectura2 = leerFicheroArray("Bd.txt", 6);

		if (comprobarConexion(false)) {

			return DriverManager.getConnection(
					"jdbc:mysql://" + lectura2[5] + "/" + lectura2[0] + "?useUnicode=true&amp;characterEncoding=utf8",
					lectura2[1], lectura2[2]);

		}

		else {
			return null;

		}

	}

	public static String eliminarUltimoElemento(String cadena) {

		if (cadena.length() > 1
				&& (cadena.charAt(cadena.length() - 1) == 92 || cadena.charAt(cadena.length() - 1) == 47)) {

			cadena = cadena.substring(0, cadena.length() - 1);

		}

		return cadena;
	}

	public static int numeroLineas(String fichero) throws FileNotFoundException {

		File input = new File("Config/" + fichero);

		Scanner iterate;

		int numLines = 0;

		iterate = new Scanner(input);

		try {

			while (iterate.hasNextLine()) {
				iterate.nextLine();
				numLines++;
			}

		}

		finally {
			iterate.close();
		}

		return numLines;
	}

	public static void guardarConfig(int opcion, String separador) throws IOException {

		String ruta;

		if (separador.equals("/")) {
			ruta = "/home/";
		}

		else {
			ruta = "C:\\Users\\";
		}

		switch (opcion) {

		case 1:

			crearFichero("Config/Config.txt", ruta + System.getProperty("user.name") + separador + "Downloads", false);

			Config guardar = new Config();
			guardar.guardarDatos(false);

			break;

		case 2:

			crearFichero("Config/Config2.txt", "127.0.0.1\r" + "4images_", false);

			Config2 guardar2 = new Config2();

			guardar2.guardarDatos(false);

			break;

		case 3:

			crearFichero("Config", "", true);

			break;

		case 4:

			crearCarpetasConfig();

			break;

		default:
			break;

		}

	}

	public static String formatearRuta() {

		String carpeta = MenuPrincipal.getLectura()[0];

		if (carpeta.substring(carpeta.length() - 1, carpeta.length()).equals(MenuPrincipal.getSeparador())) {

			carpeta = carpeta.substring(0, carpeta.length() - 1);

		}

		return carpeta;

	}

	public static void crearCarpetasConfig() {

		try {

			if (!MenuPrincipal.getLectura()[0].isEmpty()) {

				String carpeta = formatearRuta();

				String ruta = carpeta + MenuPrincipal.getSeparador() + "Hacer_gif";

				File directorio = new File(ruta);

				directorio.mkdir();

				ruta = carpeta + MenuPrincipal.getSeparador() + "Hacer_gif" + MenuPrincipal.getSeparador() + "frames";

				directorio = new File(ruta);

				directorio.mkdir();

				ruta = carpeta + MenuPrincipal.getSeparador() + "Hacer_gif" + MenuPrincipal.getSeparador() + "Output";

				directorio = new File(ruta);

				directorio.mkdir();

				ruta = carpeta + MenuPrincipal.getSeparador() + "Gif_extractor";

				directorio = new File(ruta);

				directorio.mkdir();

				ruta = carpeta + MenuPrincipal.getSeparador() + "Gif_extractor" + MenuPrincipal.getSeparador()
						+ "output";

				directorio = new File(ruta);

				directorio.mkdir();

				ruta = carpeta + MenuPrincipal.getSeparador() + "Frame_Extractor";

				directorio = new File(ruta);

				directorio.mkdir();

				ruta = carpeta + MenuPrincipal.getSeparador() + "Frame_Extractor" + MenuPrincipal.getSeparador()
						+ "output";

				directorio = new File(ruta);

				directorio.mkdir();

				ruta = carpeta + MenuPrincipal.getSeparador() + "Video_2_Gif";

				directorio = new File(ruta);

				directorio.mkdir();

				ruta = carpeta + MenuPrincipal.getSeparador() + "Video_2_Gif" + MenuPrincipal.getSeparador() + "output";

				directorio = new File(ruta);

				directorio.mkdir();

				ruta = carpeta + MenuPrincipal.getSeparador() + "colorization";

				directorio = new File(ruta);

				directorio.mkdir();

				ruta = carpeta + MenuPrincipal.getSeparador() + "colorization" + MenuPrincipal.getSeparador() + "imgs";

				directorio = new File(ruta);

				directorio.mkdir();

				ruta = carpeta + MenuPrincipal.getSeparador() + "colorization" + MenuPrincipal.getSeparador()
						+ "imgs_out";

				directorio = new File(ruta);

				directorio.mkdir();

			}

		}

		catch (Exception e) {
		}

	}

	public static void crearFichero(String ruta, String texto, boolean carpeta) throws IOException {

		File archivo = new File(ruta);

		try {

			if (carpeta) {

				if (!archivo.exists()) {
					archivo.mkdir();
				}

			}

			else {

				BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

				try {

					bw.write(texto);

				}

				finally {
					bw.close();
				}
			}
		} catch (Exception e) {

			Metodos.crearCarpetas();

			Metodos.crearCarpetasConfig();

		}
	}

	public static int listarFicherosPorCarpeta(final File carpeta, String filtro) {

		int ocurrencias = 0;

		String extension;

		String nombreArchivo;

		File folder;

		for (final File ficheroEntrada : carpeta.listFiles()) {

			nombreArchivo = ficheroEntrada.getName();

			extension = extraerExtension(nombreArchivo);

			folder = new File(carpeta + MenuPrincipal.getSeparador() + nombreArchivo);

			if (!folder.isDirectory() && (extension.equals(filtro) || filtro.equals("."))) {
				ocurrencias++;

			}

		}

		return ocurrencias;

	}

	public static String extraerExtension(String nombreArchivo) {

		String extension = "";

		if (nombreArchivo.length() >= 3) {

			extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1, nombreArchivo.length());

			extension = extension.toLowerCase();

			if (extension.endsWith(".ts")) {
				extension = "ts";
			}

		}

		return extension;
	}

	public static int listarFicherosPorCarpeta(final File carpeta) {

		int ocurrencias = 0;

		String extension = "";

		ArrayList<String> permitidos = new ArrayList<>();

		permitidos.add(".jpg");
		permitidos.add(".JPG");
		permitidos.add("jpeg");
		permitidos.add(".png");
		permitidos.add(".PNG");
		permitidos.add(".gif");
		permitidos.add(".GIF");

		for (final File ficheroEntrada : carpeta.listFiles()) {

			if (ficheroEntrada.getName().indexOf('.') > -1) {
				extension = ficheroEntrada.getName().substring(ficheroEntrada.getName().length() - 4,
						ficheroEntrada.getName().length());

				if (permitidos.contains(extension)) {
					ocurrencias++;
				}
			}
		}

		return ocurrencias;
	}

	public static void abrirCarpeta(String ruta) throws IOException {

		if (ruta != null && !ruta.equals("") && !ruta.isEmpty()) {

			try {

				if (MenuPrincipal.getOs().contentEquals("Linux")) {

					Runtime.getRuntime().exec("xdg-open " + ruta);

				}

				else {

					Runtime.getRuntime().exec("cmd /c C:\\Windows\\explorer.exe " + "\"" + ruta + "\"");

				}

			}

			catch (IOException e) {

				mensaje("Ruta inválida", 1);

			}
		}

		else {

			new Config().setVisible(true);

		}

	}

	public static void notificacion(String directorio, String tipo, boolean abrir) throws IOException {

		mensaje("No hay archivos " + tipo + " en la carpeta", 1);

		if (abrir) {

			abrirCarpeta(directorio);

		}

	}

	public static LinkedList<String> directorio(String ruta, String extension, boolean filtro, boolean carpeta) {

		LinkedList<String> lista = new LinkedList<String>();

		try {

			File f = new File(ruta);

			if (f.exists()) {

				File[] ficheros = f.listFiles();

				String fichero = "";

				String extensionArchivo;

				File folder;

				for (int x = 0; x < ficheros.length; x++) {

					fichero = ficheros[x].getName();

					folder = new File(ruta + fichero);

					extensionArchivo = extraerExtension(fichero);

					if (filtro && extensionArchivo.equals("webp")) {

						webp_png(true, ruta + fichero, ruta + fichero.substring(0, fichero.lastIndexOf(".")) + ".png",
								true);

					}

					if (filtro) {

						if (folder.isFile() && (extension.equals(".") || extension.equals(extensionArchivo)

								||

								(extension.equals("images")
										&& (extensionArchivo.equals("jpg") || extensionArchivo.equals("png")))

								|| (extension.equals("allimages") && (extensionArchivo.equals("jpg")

										|| extensionArchivo.equals("png") || extensionArchivo.equals("gif")))

						)) {

							if (carpeta) {

								lista.add(ruta + fichero);

							}

							else {

								lista.add(fichero);

							}

						}

					}

					else {

						if (folder.isDirectory() && carpeta) {

							lista.add(ruta + fichero);
						}

						else {

							if (folder.isDirectory() && !fichero.isEmpty()) {

								lista.add(fichero);

							}

						}

					}

					if (!lista.isEmpty() && !filtroSize(lista.getLast(), 2d, 3)) {

						Metodos.moverArchivo(
								MenuPrincipal.getDirectorioImagenes() + MenuPrincipal.getSeparador() + lista.getLast(),

								MenuPrincipal.getDirectorioImagenes() + MenuPrincipal.getSeparador() + "filtroImages"
										+ MenuPrincipal.getSeparador() + lista.getLast());

						lista.removeLast();
					}

				}

			}
		}

		catch (Exception e) {
			//
		}

		Collections.sort(lista);

		return lista;

	}

	public static byte[] createChecksum(String filename) throws NoSuchAlgorithmException, IOException {

		InputStream fis = null;

		MessageDigest complete = MessageDigest.getInstance("SHA-256");

		try {

			fis = new FileInputStream(filename);

			byte[] buffer = new byte[1024];

			int numRead;

			do {

				numRead = fis.read(buffer);

				if (numRead > 0) {
					complete.update(buffer, 0, numRead);
				}

			}

			while (numRead != -1);

			fis.close();

		}

		catch (IOException e) {

			if (fis != null) {
				fis.close();
			}

		}

		return complete.digest();
	}

	public static boolean comprobarArchivo(String archivo, boolean tipo) throws FileNotFoundException {

		File carpeta = new File(archivo);

		boolean retorno;

		if (!tipo) {

			if (!carpeta.exists()) {
				carpeta.mkdir();
			}

			retorno = false;

		}

		else {

			File config = new File(archivo);

			if (config.exists()) {

				if (numeroLineas(archivo.substring(archivo.indexOf('/') + 1, archivo.length())) > 0) {
					retorno = true;
				}

				else {
					retorno = false;
				}

			}

			else {
				retorno = false;
			}

		}

		return retorno;
	}

	public static int saberMaximo(String tabla, String campo) throws SQLException, IOException {

		int maximo = 1;

		ResultSet rs = null;

		Statement s = null;

		try {

			Connection conexion = conexionBD();

			s = conexion.createStatement();

			rs = s.executeQuery(
					"SELECT MAX(" + campo + ")+1 as maximo FROM " + MenuPrincipal.getLecturabd()[3] + tabla);

			rs.next();

			if (rs.getString("maximo") != null) {
				maximo = Integer.parseInt(rs.getString("maximo"));
			}

		}

		catch (Exception e) {
			//
		}

		finally {

			if (rs != null) {
				rs.close();
			}

			if (s != null) {
				s.close();
			}

		}

		return maximo;
	}

	public static void mensaje(String mensaje, int titulo) {

		try {

			String tituloSuperior = "";

			String sonido = "";

			int tipo = 0;

			switch (titulo) {

			case 1:

				tipo = JOptionPane.ERROR_MESSAGE;

				tituloSuperior = "Error";

				sonido = "duck-quack.wav";

				break;

			case 2:

				tipo = JOptionPane.INFORMATION_MESSAGE;

				tituloSuperior = "Informacion";

				sonido = "gong.wav";

				break;

			case 3:

				tipo = JOptionPane.WARNING_MESSAGE;

				tituloSuperior = "Advertencia";

				sonido = "advertencia.wav";

				break;

			default:

				break;

			}

			if (MenuPrincipal.getSonido()[1].equals("1")) {

				reproducirSonido(
						MenuPrincipal.getDirectorioActual() + "sonidos" + MenuPrincipal.getSeparador() + sonido, true);

			}

			JLabel alerta = new JLabel(mensaje);

			alerta.setFont(new Font("Arial", Font.BOLD, 18));

			JOptionPane.showMessageDialog(null, alerta, tituloSuperior, tipo);

		}

		catch (Exception e) {
			//
		}

	}

	public static String getSHA256Checksum(String filename) {

		String result = "";

		try {

			byte[] b;

			b = createChecksum(filename);

			StringBuilder bld = new StringBuilder();

			for (int i = 0; i < b.length; i++) {

				bld.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));

			}

			result = bld.toString();

		}

		catch (Exception e) {
			//
		}

		return result;
	}

	public static void crearCarpetas() {

		File directorio = new File("Config");
		directorio.mkdir();

		directorio = new File("Config/imagenes");
		directorio.mkdir();

		directorio = new File("Config/imagenes/bn");
		directorio.mkdir();

		directorio = new File("Config/imagenes/filtroImages");
		directorio.mkdir();

		directorio = new File("Config/Gif_extractor");
		directorio.mkdir();

		directorio = new File("Config/imagenes/imagenes_subidas");
		directorio.mkdir();

		directorio = new File("Config/imagenes_para_recortar");
		directorio.mkdir();

		directorio = new File("Config/imagenes_para_recortar/recortes");
		directorio.mkdir();

		directorio = new File("Config/Downloads");
		directorio.mkdir();

		directorio = new File("Config/imagenes_para_recortar/recortes/Image_rotate");
		directorio.mkdir();

		directorio = new File("sonidos");

		directorio.mkdir();

		directorio = new File("Scrapting");

		directorio.mkdir();

		directorio = new File("Notas");

		directorio.mkdir();

		directorio = new File("Usuarios");

		directorio.mkdir();

		directorio = new File("Comentarios");

		directorio.mkdir();

		directorio = new File("Bots");

		directorio.mkdir();

		directorio = new File("Descargas");

		directorio.mkdir();

		directorio = new File("Estadisticas");

		directorio.mkdir();

		directorio = new File("Grupos");

		directorio.mkdir();

		directorio = new File("Categorias");

		directorio.mkdir();

	}

	public static String saberSeparador(String os) {

		if (os.equals("Linux")) {

			return "/";

		}

		else {

			return "\\";

		}

	}

	public static void conversion(String extension, String salida, String carpeta) {

		LinkedList<String> listaImagenes = directorio(carpeta, extension, true, false);

		int resto = 3;

		if (extension.length() == 4) {
			resto = 5;
		}

		for (int i = 0; i < listaImagenes.size(); i++) {

			File f1 = new File(carpeta + MenuPrincipal.getSeparador() + listaImagenes.get(i));

			File f2 = new File(carpeta + MenuPrincipal.getSeparador()
					+ listaImagenes.get(i).substring(0, listaImagenes.get(i).length() - resto) + "." + salida);

			f1.renameTo(f2);

		}

		listaImagenes.clear();
	}

	public static void renombrarArchivos(String ruta, String filtro, boolean api) throws IOException {

		List<String> list = directorio(ruta, filtro, true, false);

		if (list.size() > 0) {

			File f1;

			File f2;

			File f3;

			JSONArray imagenesBD = null;

			if (api) {

				JSONObject json;

				String parametros = obtenerParametros(list);

				json = apiImagenes(parametros);

				imagenesBD = json.getJSONArray("imagenes_bd");
			}

			for (int x = 0; x < list.size(); x++) {

				f1 = new File(ruta + list.get(x));

				f2 = new File(ruta + eliminarPuntos(list.get(x)));

				if (!f1.isDirectory() && f1.renameTo(f2)) {

					if (api) {

						f3 = new File(ruta + imagenesBD.get(x));

						if (!f2.renameTo(f3)) {
							x = list.size();
						}

					}

				}

				else {

					x = list.size();

				}

			}
		}
	}

	public static void renombrar(String ruta1, String ruta2) {

		File f1 = new File(ruta1);

		File f2 = new File(ruta2);

		f1.renameTo(f2);

	}

	public static JSONObject apiImagenes(String parametros) throws IOException {

		JSONObject json = readJsonFromUrl("https://apiperiquito.herokuapp.com/recibo-json.php?imagenes=" + parametros);

		return json;
	}

	public static String extraerNombreArchivo(String extension) throws IOException {

		JSONObject json = apiImagenes("archivo." + extension);

		JSONArray imagenesBD = json.getJSONArray("imagenes_bd");

		String outputFilePath = MenuPrincipal.getDirectorioActual() + "Scrapting" + MenuPrincipal.getSeparador()
				+ imagenesBD.get(0).toString();

		return outputFilePath;
	}

	public static String obtenerParametros(List<String> list) {

		StringBuilder bld = new StringBuilder();

		String extension;

		for (int i = 0; i < list.size(); i++) {

			extension = list.get(i).substring(list.get(i).length() - 3, list.get(i).length());

			if (list.size() == 1 || i + 1 == list.size()) {

				bld.append(i + "." + extension);

			}

			else {

				bld.append(i + "." + extension + ",");

			}

		}

		return bld.toString();
	}

	public static String separarPorComas(List<String> shaimages) {

		String resultado = "";

		int y = 1;

		for (int i = 0; i < shaimages.size(); i++) {

			resultado += "sha256='" + shaimages.get(i) + "'";

			if (y < shaimages.size()) {

				resultado += ", AND ";
			}

			y++;
		}

		return resultado;
	}

	public static void backupBd(String archivo, List<String> tablas) {

		ArrayList<String> categoriasSeleccion;

		try {

			categoriasSeleccion = comprobarConexionBD();

			if (categoriasSeleccion != null && !categoriasSeleccion.isEmpty()) {

				if (comprobarConfiguracion() && comprobarConexion(true)) {

					exportarBd(archivo, tablas);

				}

				else {
					new Bd().setVisible(true);

				}

			}
		}

		catch (IOException e2) {

			try {
				new Bd().setVisible(true);
			}

			catch (IOException e1) {
				//
			}

		}
	}

	public static LinkedList<String> mostrarDatosTabla(String tabla, int filas) {

		LinkedList<String> datos = new LinkedList<>();

		ResultSet rs;

		Connection conexion;

		Statement s;

		try {

			conexion = conexionBD();

			s = conexion.createStatement();

			rs = s.executeQuery("SELECT * FROM " + tabla + " ORDER BY 1 ASC");

			while (rs.next()) {

				for (int i = 1; i <= filas; i++) {

					datos.add(rs.getString(i));
				}

			}

			s.close();

			rs.close();

			conexion.close();

		}

		catch (Exception e) {
			//
		}

		return datos;

	}

	public static int saberIdCategoria(String categoria) {

		ResultSet rs;
		Connection conexion;
		Statement s;

		int resultado = 0;

		try {

			conexion = conexionBD();

			s = conexion.createStatement();

			rs = s.executeQuery("SELECT cat_id FROM " + MenuPrincipal.getLecturabd()[3] + "categories WHERE cat_name='"
					+ categoria + "'");

			rs.next();

			resultado = Integer.parseInt(rs.getString("cat_id"));

			s.close();
			rs.close();
			conexion.close();
		}

		catch (Exception e) {
			//
		}

		return resultado;

	}

	public static String saberCategoria(int categoria) {

		ResultSet rs;

		Connection conexion;

		Statement s;

		String resultado = "";

		try {

			conexion = conexionBD();

			s = conexion.createStatement();

			rs = s.executeQuery("SELECT cat_name FROM " + MenuPrincipal.getLecturabd()[3] + "categories WHERE cat_id='"
					+ categoria + "'");

			rs.next();

			resultado = rs.getString("cat_name");

			s.close();
			rs.close();
			conexion.close();
		}

		catch (Exception e) {
			//
		}

		return resultado;

	}

	public static int saberIdUsuario(String user) {

		ResultSet rs;
		Connection conexion;
		Statement s;

		int resultado = 0;

		try {

			conexion = conexionBD();

			s = conexion.createStatement();

			rs = s.executeQuery(
					"SELECT user_id FROM " + MenuPrincipal.getLecturabd()[3] + "users WHERE user_name='" + user + "'");

			rs.next();

			resultado = Integer.parseInt(rs.getString("user_id"));

			s.close();
			rs.close();
			conexion.close();
		}

		catch (Exception e) {
			//
		}

		return resultado;

	}

	private static Clipboard getSystemClipboard() {

		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();

		Clipboard systemClipboard = defaultToolkit.getSystemClipboard();

		return systemClipboard;
	}

	public static String saberExtension(String text) {

		String resultado = "";

		try {
			resultado = text.substring(text.length() - 4, text.length());

		}

		catch (Exception e) {
		}

		return resultado;

	}

	public static void copy(String text) {

		Clipboard clipboard = getSystemClipboard();

		clipboard.setContents(new StringSelection(text), null);
	}

	public static void moverArchivo(String origen, String destino) {

		try {

			Files.move(FileSystems.getDefault().getPath(origen), FileSystems.getDefault().getPath(destino),
					StandardCopyOption.REPLACE_EXISTING);
		}

		catch (IOException e) {
			//
		}

	}

	public static void renombrar(String ruta, int numFrames) {

		int frame = Integer.parseInt(ruta.substring(ruta.indexOf("frame-") + 6, ruta.indexOf(".")));

		String resultado = calcularCeros(frame, numFrames);

		renombrar(ruta,
				ruta.substring(0, ruta.lastIndexOf(MenuPrincipal.getSeparador()) + 1) + "Imagen_" + resultado + ".png");

	}

	public static String pintarCeros(int frames) {

		String ceros = "";

		for (int i = 0; i < frames; i++) {

			ceros += "0";

		}

		return ceros;

	}

	public static String calcularCeros(int frame, int numFrames) {

		String numeroDigitos = "";

		int totalFrames = ("" + numFrames).length();

		int digitos = ("" + frame).length();

		numeroDigitos = pintarCeros(totalFrames);

		if (totalFrames > 0) {

			if (frame == 1) {

				numeroDigitos = pintarCeros(totalFrames);
			}

			else {

				numeroDigitos = pintarCeros(totalFrames -= (--digitos));

			}

		}

		return numeroDigitos + frame;

	}

	public static boolean comprobarFirefox(String os) {

		Process aplicacion = null;

		boolean resultado = false;

		if (os.equals("Linux")) {

			try {
				aplicacion = Runtime.getRuntime().exec("firefox");
				resultado = true;
			}

			catch (IOException e) {
			}

		}

		else {

			try {
				aplicacion = Runtime.getRuntime().exec("cmd.exe start firefox");
				resultado = true;
			}

			catch (IOException e) {
				//
			}

		}

		if (aplicacion != null) {

			aplicacion.destroy();
		}

		return resultado;

	}

}
