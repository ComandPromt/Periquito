package utils;

import java.awt.Font;
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
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
import java.nio.file.Path;
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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import periquito.Bd;
import periquito.Config;
import periquito.Config2;
import periquito.Descarga;
import periquito.MenuPrincipal;

public abstract class Metodos {

	private Metodos() {
		throw new IllegalStateException("Utility class");
	}

	public static boolean pingURL(String url) {

		int timeout = 100000;

		url = url.replaceFirst("^https", "http"); // Otherwise an exception may be thrown on invalid SSL certificates.

		try {

			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setConnectTimeout(timeout);
			connection.setReadTimeout(timeout);
			connection.setRequestMethod("HEAD");

			int responseCode = connection.getResponseCode();

			return (200 <= responseCode && responseCode <= 399);

		} catch (IOException exception) {
			return false;
		}
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

		String cadena2 = cadena.substring(0, cadena.length() - 4);

		cadena = cadena2.replace(".", "_") + "." + extraerExtension(cadena);

		return cadena;
	}

	public static void cerrarNavegador() {

		try {

			if (!MenuPrincipal.getOs().equals("Linux")) {
				crearScript("cerrar.bat", "taskkill /F /IM chromedriver.exe /T", true, MenuPrincipal.getOs());

			}

			else {
				crearScript("cerrar.sh", "kilall chrome", true, MenuPrincipal.getOs());
			}

		} catch (Exception e) {
			Metodos.mensaje("Error al cerrar el navegador", 1);
		}

	}

	public static void postFile(File binaryFile, String imageNameOnServer, String username, String pass, int catId) {

		try {

			String url = "http://" + MenuPrincipal.getLecturaurl()[0] + "/" + MenuPrincipal.getLecturaurl()[1]
					+ "/utility/index.php?username=" + username + "&pass=" + pass + "&cat_id=" + catId
					+ "&nombre_imagen=" + imageNameOnServer;

			String charset = "UTF-8";

			String limite = Long.toHexString(System.currentTimeMillis());

			String crlf = "\r\n";

			URLConnection connection = new URL(url).openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + limite);
			OutputStream output = connection.getOutputStream();
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);

			writer.append("--" + limite).append(crlf);
			writer.append("Content-Disposition: form-data; name=\"imgname\"").append(crlf);
			writer.append("Content-Type: text/plain; charset=" + charset).append(crlf);
			writer.append(crlf).append(imageNameOnServer).append(crlf).flush();

			writer.append("--" + limite).append(crlf);
			writer.append("Content-Disposition: form-data; name=\"archivo\"; filename=\"" + binaryFile.getName() + "\"")
					.append(crlf);
			writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(binaryFile.getName())).append(crlf);
			writer.append("Content-Transfer-Encoding: binary").append(crlf);
			writer.append(crlf).flush();
			Files.copy(binaryFile.toPath(), output);
			output.flush();
			writer.append(crlf).flush();

			writer.append("--" + limite + "--").append(crlf).flush();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

		}

		catch (Exception ex) {
			//
		}

	}

	public static void descargarFoto(String enlace) throws IOException {

		String folder = "Config" + MenuPrincipal.getSeparador() + "Downloads" + MenuPrincipal.getSeparador();

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
			Metodos.abrirCarpeta(folder);

		}
	}

	public static void descargar(String imagen, int inicio, int fin, int salto) throws IOException {

		try {

			WebDriver chrome;

			for (int x = inicio; x <= fin; x += salto) {

				if (Descarga.getBotonRadio1().isSelected()) {

					chrome = new ChromeDriver();

					chrome.get(imagen + x);

					if (!chrome.findElements(By.tagName("img")).isEmpty()) {

						List<WebElement> image = chrome.findElements(By.tagName("img"));

						descargarFoto(image.get(0).getAttribute("src"));
					}

					chrome.close();
					Metodos.cerrarNavegador();
				}

				else {
					descargar(imagen, x);
				}

			}

			Metodos.abrirCarpeta("Config" + MenuPrincipal.getSeparador() + "Downloads");

		} catch (Exception e) {

			Metodos.abrirCarpeta("Config" + MenuPrincipal.getSeparador() + "Downloads");
			System.exit(0);
		}
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

	public static void convertir() {

		Metodos.conversion("jpeg", "jpg");

		Metodos.conversion("JPEG", "jpg");

		Metodos.conversion("JPG", "jpg");

		Metodos.conversion("PNG", "png");

		Metodos.conversion("GIF", "gif");

	}

	public static JSONObject readJsonFromUrl(String url) throws IOException {

		InputStream is = new URL(url).openStream();

		BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

		String jsonText = readAll(rd);
		is.close();

		return new JSONObject(jsonText);

	}

	public static boolean probarconexion(String web) throws IOException {

		String dirWeb = web;
		int puerto = 80;
		Socket s = new Socket(dirWeb, puerto);
		boolean resultado = false;

		try {

			if (s.isConnected()) {
				resultado = true;
			} else {
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

	public static void moverArchivos(LinkedList<String> files, String separador, String destino, boolean mensaje)
			throws IOException {

		String imagen;
		String comprobacion;
		boolean filtro = false;
		String origen;

		if (!files.isEmpty()) {

			LinkedList<String> listaImagenes = directorio(MenuPrincipal.getDirectorioImagenes(), ".");

			String busqueda;

			String salida;
			boolean error = false;

			for (int i = 0; i < files.size(); i++) {

				imagen = files.get(i).substring(files.get(i).lastIndexOf(separador) + 1, files.get(i).length());

				comprobacion = extraerExtension(imagen);

				if (comprobacion.equals("jpg") || comprobacion.equals("JPG") || comprobacion.equals("peg")
						|| comprobacion.equals("png") || comprobacion.equals("gif") || comprobacion.equals("avi")
						|| comprobacion.equals("mp4") || comprobacion.equals("webp")) {

					origen = files.get(i);

					if (origen.substring(0, origen.lastIndexOf(separador)).equals(destino)) {
						Metodos.mensaje("No puedes pegar archivos al mismo directorio", 3);
						i = files.size();
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

							salida += busqueda.substring(0, busqueda.length() - 4) + "_" + i + "." + comprobacion;
							renombrar(origen, salida);

							origen = salida;
						}

						Files.move(FileSystems.getDefault().getPath(origen), FileSystems.getDefault().getPath(

								destino + separador
										+ origen.substring(origen.lastIndexOf(separador) + 1, origen.length())

						), StandardCopyOption.REPLACE_EXISTING);

						convertir();

					}

				} else {
					filtro = true;
				}
			}

			if (filtro) {
				mensaje("Uno o varios archivos no tiene el formato correcto", 3);
			} else {

				if (!error && mensaje) {
					mensaje("Los archivos se han movido correctamente", 2);
				}

			}

		}

	}

	public static void moverArchivosDrop(java.io.File[] files, String separador) throws IOException {

		String imagen;
		String comprobacion;
		boolean filtro = false;
		String origen;
		String destino;

		if (files.length > 0) {

			LinkedList<String> listaImagenes = directorio(MenuPrincipal.getDirectorioImagenes(), ".");

			String busqueda;

			String salida;

			boolean error = false;

			for (int i = 0; i < files.length; i++) {

				imagen = files[i].getCanonicalPath().substring(files[i].getCanonicalPath().lastIndexOf(separador) + 1,
						files[i].getCanonicalPath().length());

				comprobacion = extraerExtension(imagen);

				if (comprobacion.equals("jpg") || comprobacion.equals("JPG") || comprobacion.equals("peg")
						|| comprobacion.equals("png") || comprobacion.equals("gif") || comprobacion.equals("avi")
						|| comprobacion.equals("mp4") || comprobacion.equals("webp")) {

					origen = files[i].getCanonicalPath();

					destino = MenuPrincipal.getDirectorioImagenes();

					if (origen.substring(0, origen.lastIndexOf(separador)).equals(destino)) {
						Metodos.mensaje("No puedes pegar archivos al mismo directorio", 3);
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

							salida += busqueda.substring(0, busqueda.length() - 4) + "_" + i + "." + comprobacion;

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

			Connection conexion = Metodos.conexionBD();

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

	public static String eliminarEspacios(String cadena) {
		cadena = cadena.trim();
		cadena = cadena.replace("  ", " ");
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
				linea = eliminarEspacios(linea);
				contenido.add(convertToUTF8(linea));
			}

			HashSet<String> hs = new HashSet<>();

			hs.addAll(contenido);

			contenido.clear();

			contenido.addAll(hs);

			Connection conexion = Metodos.conexionBD();

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

	public static String[] leerFicheroArray(String fichero, int longitud) {

		String[] salida = new String[longitud];

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
				} catch (IOException e) {
					//
				}
			}

			if (flE != null) {

				try {
					flE.close();
				} catch (IOException e) {
					//
				}

			}
		}

		return salida;

	}

	public static void exportarBd() throws IOException {

		String[] backup = leerFicheroArray("Config/Backup.txt", 1);

		try {

			String ruta = backup[0] + MenuPrincipal.getSeparador() + "backupbd.txt";

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

			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(contenido);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Metodos.mensaje("Backup realizado correctamente", 2);

		abrirCarpeta(backup[0]);
	}

	public static void mostrarGaleriaSql(String busqueda, String sql, String sql2) throws SQLException, IOException {

		int recuento;

		Connection conexion = Metodos.conexionBD();

		Statement s = conexion.createStatement();

		ResultSet rs = s.executeQuery(sql);

		rs.next();

		recuento = Integer.parseInt(rs.getString("count(image_id)"));

		if (recuento > 0) {

			if (recuento > 500) {

				Metodos.mensaje("Has introducido un nombre que está en más de 500 imágenes", 3);
				Metodos.mensaje("Por favor,introduce un nombre con menos registros para mostrarlos", 2);

				Metodos.abrirCarpeta(
						Metodos.obtenerEnlaceCms(MenuPrincipal.getLecturaurl()[0], MenuPrincipal.getLecturaurl()[1])
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

				Metodos.mensaje("No hay resultados, intente con otro nombre", 2);

			} else {

				if (recuento < 500) {

					try {

						new Galeria();
						new InterfazGaleria().setVisible(true);
					}

					catch (Exception e1) {

						Metodos.mensaje("No se han podido cargar las imágenes", 1);

						new Config2().setVisible(true);

					}

				}
			}

		}

		else {
			Metodos.mensaje("La búsqueda no tiene resultados", 3);
		}
	}

	public static void mostrarGaleriaConWhere(String busqueda) {

		try {

			if (Metodos.comprobarConexionBd("SELECT COUNT(image_id) FROM " + MenuPrincipal.getLecturabd()[3] + "images",
					"COUNT(image_id)")) {

				try {

					if (Metodos.comprobarConfiguracion()) {

						String sql = "";

						String sql2 = "";

						sql = "select count(image_id) from " + MenuPrincipal.getLecturabd()[3] + "images WHERE "
								+ busqueda;

						sql2 = "select image_media_file,cat_id from " + MenuPrincipal.getLecturabd()[3]
								+ "images WHERE " + busqueda;

						int recuento;

						if (!busqueda.isEmpty()) {

							Connection conexion = Metodos.conexionBD();

							Statement s = conexion.createStatement();

							ResultSet rs = s.executeQuery(sql);

							rs.next();

							recuento = Integer.parseInt(rs.getString("count(image_id)"));

							if (recuento > 0) {

								if (recuento > 500) {

									Metodos.mensaje("Has introducido un nombre que está en más de 500 imágenes", 3);
									Metodos.mensaje("Por favor,introduce un nombre con menos registros para mostrarlos",
											2);

									Metodos.abrirCarpeta(Metodos.obtenerEnlaceCms(MenuPrincipal.getLecturaurl()[0],
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

								}

								s.close();

								rs.close();

								conexion.close();

								if (recuento == 0) {

									Metodos.mensaje("No hay resultados, intente con otro nombre", 2);

								} else {

									if (recuento < 500) {

										try {

											new Galeria();
											new InterfazGaleria().setVisible(true);
										}

										catch (Exception e1) {

											Metodos.mensaje("No se han podido cargar las imágenes", 1);

											new Config2().setVisible(true);

										}

									}
								}

							}

							else {
								Metodos.mensaje("La búsqueda no tiene resultados", 3);
							}

						}

					}
				}

				catch (Exception e1) {
					Metodos.mensaje("Revise la búsqueda", 3);
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

		Process aplicacion = null;

		if (os.equals("Linux")) {
			aplicacion = Runtime.getRuntime().exec("bash " + contenido);
			aplicacion.destroy();
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

				try {
					aplicacion2.exec("cmd.exe /K " + iniciar + " " + System.getProperty("user.dir") + "\\" + archivo);
				}

				catch (Exception e) {
//
				}

			}

			finally {
				fS.close();
				flS.close();

			}
		}

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
				Metodos.mensaje("Error al cargar las categorías", 1);
			}
		}
	}

	public static ArrayList<String> verCategorias() throws SQLException, IOException {

		String[] lectura = Metodos.leerFicheroArray("Config/Bd.txt", 6);

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

	public static void eliminarArchivos(List<String> listaImagenes, String ruta) {

		for (int i = 0; i < listaImagenes.size(); i++) {

			if (!listaImagenes.get(i).isEmpty()) {
				eliminarFichero(ruta + listaImagenes.get(i));
			}

		}
	}

	public static void eliminarFichero(String archivo) {

		File fichero = new File(archivo);

		if (fichero.exists()) {
			fichero.delete();
		}

	}

	public static boolean comprobarConexionBd(String sql, String fila) throws SQLException {

		boolean resultado = false;
		Connection conexion = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			conexion = Metodos.conexionBD();

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
			Metodos.mensaje("No hay registros en la base de datos", 3);
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
				Metodos.mensaje("Ruta inválida ", 1);
				new Config().setVisible(true);
			}

			else {

				Metodos.abrirCarpeta(ruta);
			}

		}

		else {
			new Config().setVisible(true);
		}
	}

	public static boolean comprobarConexion(boolean mensaje) throws IOException {

		boolean conexion = false;

		try {

			String[] lectura2 = leerFicheroArray("Config/Bd.txt", 6);

			if (!lectura2[5].isEmpty()) {

				InetAddress ping;

				ping = InetAddress.getByName(lectura2[5]);

				if (!ping.getCanonicalHostName().equals("")) {
					conexion = true;
				}

			}

		}

		catch (Exception e) {

			if (mensaje && MenuPrincipal.isConexion()) {
				Metodos.mensaje("Por favor, rellena la configuración de la base de datos", 3);
			}
		}

		return conexion;

	}

	public static boolean comprobarConfiguracion() throws IOException {

		boolean comprobacion = false;

		ArrayList<String> categorias = comprobarConexionBD();

		if (Metodos.comprobarConexion(true) && categorias.size() > 0) {

			comprobacion = true;
		}

		else {

			if (!Metodos.probarconexion("www.google.com")) {
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

		String[] lectura2 = leerFicheroArray("Config/Bd.txt", 6);

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
			Metodos.crearFichero("Config/Config.txt", ruta + System.getProperty("user.name") + separador + "Downloads",
					false);

			Config guardar = new Config();
			guardar.guardarDatos(false);

			break;

		case 2:

			Metodos.crearFichero("Config/Config2.txt", "127.0.0.1\r" + "4images_", false);

			Config2 guardar2 = new Config2();

			guardar2.guardarDatos(false);

			break;

		case 3:

			Metodos.crearFichero("Config", "", true);

			break;

		case 4:

			Metodos.crearFichero(MenuPrincipal.getLectura()[0] + separador + "FrameExtractor" + separador + "examples"
					+ separador + "output", "", true);

			Metodos.crearFichero(MenuPrincipal.getLectura()[0] + separador + "FrameExtractor" + separador + "examples"
					+ separador + "tmp", "", true);

			Metodos.crearFichero(MenuPrincipal.getLectura()[0] + separador + "FrameExtractor" + separador + "examples"
					+ separador + "video", "", true);

			Metodos.crearFichero(MenuPrincipal.getLectura()[0] + separador + "Hacer_gif" + separador + "img", "", true);

			Metodos.crearFichero(MenuPrincipal.getLectura()[0] + separador + "Hacer_gif" + separador + "Output", "",
					true);

			break;

		default:
			break;

		}

	}

	public static void crearFichero(String ruta, String texto, boolean carpeta) throws IOException {

		File archivo = new File(ruta);

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
	}

	public static int listarFicherosPorCarpeta(final File carpeta, String filtro) {

		int ocurrencias = 0;

		String extension;
		String nombreArchivo;

		for (final File ficheroEntrada : carpeta.listFiles()) {

			nombreArchivo = ficheroEntrada.getName();
			extension = extraerExtension(nombreArchivo);

			if (extension.equals(filtro) || filtro.equals(".")) {

				ocurrencias++;
			}

		}

		return ocurrencias;
	}

	public static String extraerExtension(String nombreArchivo) {

		String extension = "";

		if (nombreArchivo.length() >= 3) {

			extension = nombreArchivo.substring(nombreArchivo.length() - 3, nombreArchivo.length());

			extension = extension.toLowerCase();

			if (extension.equals("peg")) {
				extension = "jpeg";
			}

			if (extension.equals("ebp")) {
				extension = "webp";
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
					Runtime.getRuntime().exec("cmd /c explorer " + "\"" + ruta + "\"");
				}
			} catch (IOException e) {
				mensaje("Ruta inválida", 1);
			}
		} else {
			new Config().setVisible(true);
		}
	}

	public static void notificacion(int salida, String directorio, String tipo, Boolean abrir) throws IOException {

		if (salida <= 0) {
			mensaje("No hay archivos " + tipo + " en la carpeta " + directorio, 1);

			if (Boolean.TRUE.equals(abrir)) {
				abrirCarpeta(directorio);
			}
		}
	}

	public static LinkedList<String> directorio(String ruta, String extension) {

		LinkedList<String> lista = new LinkedList<>();

		File f = new File(ruta);

		if (f.exists()) {

			lista.clear();

			File[] ficheros = f.listFiles();

			String fichero = "";

			String extensionArchivo;

			File folder;

			boolean comprobacion;

			for (int x = 0; x < ficheros.length; x++) {

				fichero = ficheros[x].getName();

				folder = new File(ruta + "/" + fichero);

				extensionArchivo = extraerExtension(fichero);

				comprobacion = !folder.isDirectory();

				if (comprobacion && fichero.length() > 5 && fichero.substring(0, fichero.length() - 5).contains(".")) {

					renombrar(ruta + "/" + fichero, ruta + "/" + eliminarPuntos(fichero));

				}

				if (comprobacion && extension.equals("webp") && extensionArchivo.equals("webp")
						|| comprobacion && extension.equals("jpeg") && extensionArchivo.equals("jpeg")
						|| comprobacion && extension.equals(".")
						|| comprobacion && extension.equals(extensionArchivo)) {

					lista.add(fichero);
				}

			}
		}

		return lista;

	}

	public static int salida(String origen, String destino, int opcion, String separador) throws IOException {

		LinkedList<String> imagenes;

		if (opcion != 9 || (opcion == 9 && origen.contains("Thumb"))) {
			imagenes = directorio(origen, ".jpg");
		}

		else {
			imagenes = directorio(origen, ".gif");
		}

		int mensaje;

		if (!imagenes.isEmpty()) {

			if (imagenes.size() == 1) {
				destino += separador + imagenes.get(0);
				Path origenPath = FileSystems.getDefault().getPath(origen + separador + imagenes.get(0));
				Path destinoPath = FileSystems.getDefault().getPath(destino);
				Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
			}

			else {

				for (int x = 0; x < imagenes.size(); x++) {
					Path origenPath = FileSystems.getDefault().getPath(origen + separador + imagenes.get(x));
					Path destinoPath = FileSystems.getDefault().getPath(destino + separador + imagenes.get(x));
					Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
				}

			}

			mensaje = imagenes.size();

		}

		else {
			mensaje = 0;
		}

		return mensaje;
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

				if (Metodos.numeroLineas(archivo.substring(archivo.indexOf('/') + 1, archivo.length())) > 0) {
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

		int maximo = 0;

		ResultSet rs = null;

		Statement s = null;

		try {

			Connection conexion = Metodos.conexionBD();

			s = conexion.createStatement();

			rs = s.executeQuery("SELECT MAX(" + campo + ")+1 as maximo FROM " + MenuPrincipal.getLecturabd()[3] + tabla
					+ " order by image_id");

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

		String tituloSuperior = "", sonido = "";

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

		try {

			if (MenuPrincipal.getSonido()[1].equals("1")) {
				reproducirSonido(
						MenuPrincipal.getDirectorioActual() + "sonidos" + MenuPrincipal.getSeparador() + sonido, true);
			}

		} catch (Exception e) {
			//
		}

		JLabel alerta = new JLabel(mensaje);

		alerta.setFont(new Font("Arial", Font.BOLD, 18));

		JOptionPane.showMessageDialog(null, alerta, tituloSuperior, tipo);

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

		} catch (Exception e) {
			//
		}

		return result;
	}

	public static void cambiarPermisos() {

		try {
			crearScript("change_permisos.sh", "sudo chmod 777 -R /var/www", true, MenuPrincipal.getOs());
		}

		catch (Exception e1) {
			//
		}
	}

	public static void crearCarpetas() {

		File directorio = new File("Config");
		directorio.mkdir();

		directorio = new File("Config/imagenes");
		directorio.mkdir();

		directorio = new File("Config/imagenes/bn");
		directorio.mkdir();

		directorio = new File("Config/imagenes/imagenes_duplicadas");
		directorio.mkdir();

		directorio = new File("Config/imagenes/imagenes_subidas");
		directorio.mkdir();

		directorio = new File("Config/imagenes_para_recortar");
		directorio.mkdir();

		directorio = new File("Config/imagenes_para_recortar/recortes");
		directorio.mkdir();

		directorio = new File("Config/Downloads");
		directorio.mkdir();

		directorio = new File("Config/Image_rotate");
		directorio.mkdir();

		directorio = new File("sonidos");

		directorio.mkdir();
	}

	public static String saberSeparador(String os) {
		if (os.equals("Linux")) {
			return "/";
		} else {
			return "\\";
		}
	}

	public static void conversion(String extension, String salida) {

		LinkedList<String> listaImagenes = Metodos.directorio("Config" + MenuPrincipal.getSeparador() + "imagenes",
				extension);

		int resto = 3;

		if (extension.length() == 4) {
			resto = 5;
		}

		for (int i = 0; i < listaImagenes.size(); i++) {

			File f1 = new File("Config" + MenuPrincipal.getSeparador() + "imagenes" + MenuPrincipal.getSeparador()
					+ listaImagenes.get(i));

			File f2 = new File("Config" + MenuPrincipal.getSeparador() + "imagenes" + MenuPrincipal.getSeparador()
					+ listaImagenes.get(i).substring(0, listaImagenes.get(i).length() - resto) + "." + salida);

			f1.renameTo(f2);

		}

		listaImagenes.clear();
	}

	public static void renombrarArchivos(List<String> list, String ruta) {

		File f1;
		File f2;

		for (int x = 0; x < list.size(); x++) {

			f1 = new File(ruta + list.get(x));
			f2 = new File(ruta + Metodos.eliminarPuntos(list.get(x)));
			f1.renameTo(f2);
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

	public static String obtenerParametros(LinkedList<String> listaImagenes) {

		StringBuilder bld = new StringBuilder();
		String extension;

		for (int i = 0; i < listaImagenes.size(); i++) {

			extension = listaImagenes.get(i).substring(listaImagenes.get(i).length() - 3,
					listaImagenes.get(i).length());

			if (listaImagenes.size() == 1 || i + 1 == listaImagenes.size()) {
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

	public static void backupBd() {

		ArrayList<String> categoriasSeleccion;

		try {

			categoriasSeleccion = Metodos.comprobarConexionBD();

			if (categoriasSeleccion != null && categoriasSeleccion.size() > 0) {

				if (comprobarConfiguracion() && Metodos.comprobarConexion(true)) {

					exportarBd();

				} else {
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

}