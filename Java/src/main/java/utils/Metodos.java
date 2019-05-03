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
import java.io.Reader;
import java.net.InetAddress;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
		super();
	}

	public static void descargarFoto(String enlace, int numero) throws IOException {
		FileOutputStream fos = null;
		InputStream is = null;
		try {
			// Url con la foto
			URL url = new URL(enlace);

			// establecemos conexion
			URLConnection urlCon = url.openConnection();

			// Se obtiene el inputStream de la foto web y se abre el fichero
			// local.
			is = urlCon.getInputStream();
			fos = new FileOutputStream("Downloads/Image" + numero + "." + Metodos.extraerExtension(enlace));

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
			Metodos.abrirCarpeta("Downloads");
			System.exit(0);
		} finally {
			if (is != null) {
				is.close();
			}
			if (fos != null) {
				fos.close();
			}

		}
	}

	public static void descargar(String imagen, int inicio, int fin, int salto) throws IOException {
		try {

			WebDriver chrome;

			for (int x = inicio; x <= fin; x += salto) {

				if (Descarga.getRdbtnComplex().isSelected()) {
					chrome = new ChromeDriver();
					chrome.get(imagen + x);

					if (!chrome.findElements(By.tagName("img")).isEmpty()) {
						List<WebElement> image = chrome.findElements(By.tagName("img"));

						descargarFoto(image.get(0).getAttribute("src"), x);
					}

					chrome.close();
				}

				else {
					try {
						String extension = Descarga.textField3.getText().trim();
						// Url con la foto
						URL url = new URL(imagen + x + "." + extension);

						// establecemos conexion
						URLConnection urlCon = url.openConnection();

						// Se obtiene el inputStream de la foto web y se abre el fichero
						// local.
						InputStream is = urlCon.getInputStream();
						FileOutputStream fos = new FileOutputStream("Config/Downloads/Image_" + x + "." + extension);

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
						Descarga.setError(true);
					}
				}

			}
			Metodos.abrirCarpeta("Config" + MenuPrincipal.getSeparador() + "Downloads");
		} catch (Exception e) {
			Metodos.abrirCarpeta("Config" + MenuPrincipal.getSeparador() + "Downloads");
			System.exit(0);
		}
	}

	public static String mostrarDialogo() {

		JFrame frame = new JFrame("Introduce un nombre para buscar");

		String name = JOptionPane.showInputDialog(frame, "Introduce un nombre para buscar");

		return name;
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
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
		} catch (Exception e) {
			return resultado;
		} finally {
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
		} finally {
			if (st != null)
				st.close();
		}
		s.close();
	}

	public static void moverArchivosDrop(java.io.File[] files, String separador) throws IOException {
		String imagen;
		String comprobacion;
		boolean filtro = false;
		String origen;
		String destino;
		if (files.length > 0) {
			for (int i = 0; i < files.length; i++) {

				imagen = files[i].getCanonicalPath().substring(files[i].getCanonicalPath().lastIndexOf(separador) + 1,
						files[i].getCanonicalPath().length());
				comprobacion = extraerExtension(imagen);

				if (comprobacion.equals("jpg") || comprobacion.equals("peg") || comprobacion.equals("png")
						|| comprobacion.equals("gif") || comprobacion.equals("avi") || comprobacion.equals("mp4")) {

					origen = files[i].getCanonicalPath();
					destino = new File(".").getCanonicalPath() + separador + "Config" + separador + "imagenes";

					if (origen.substring(0, origen.lastIndexOf(separador)).equals(destino)) {
						Metodos.mensaje("No puedes pegar archivos al mismo directorio", 3);
					}

					else {

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
				mensaje("Uno o varios archivos no tiene el formato correcto", 1);
			} else {
				mensaje("Los archivos se han movido correctamente", 2);
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
		default:
			break;
		}

		if (!chooser.isMultiSelectionEnabled()) {
			chooser.setMultiSelectionEnabled(true);
		}

		chooser.showOpenDialog(chooser);
		File[] files = chooser.getSelectedFiles();

		if (files.length == 0) {
			mensaje(mensaje, 1);

		}
		return files;
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

			while (texto != null) {

				salida[i] = texto;
				i++;

				texto = fE.readLine();
			}
			fE.close();
			flE.close();
		} catch (IOException e) {
//
		} finally {
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
					// }

				}

			}
		}
		return salida;

	}

	@SuppressWarnings("all")
	public static void exportarBd(int tipo) throws IOException {

		String[] lectura = Metodos.leerFicheroArray("Config/Bd.txt", 7);
		String[] backup = Metodos.leerFicheroArray("Config/Backup.txt", 1);

		if (!MenuPrincipal.getOs().equals("Linux")) {

			String ruta = "";

			switch (tipo) {

			case 1:
				ruta = "C:\\AppServ\\mysql\\bin\\";
				break;

			case 2:
				ruta = "C:\\wamp\\bin\\mysql\\";
				break;

			case 3:
				ruta = "C:\\xampp\\mysql\\bin\\";
				break;

			default:
				break;
			}

			try {

				crearScript("backupbd.bat",
						"mysqldump.exe --no-defaults -h " + lectura[5] + " -u " + lectura[1] + " -p" + lectura[2] + " "
								+ lectura[0] + " > " + backup[0] + MenuPrincipal.getSeparador() + "backupbd.sql",
						false, MenuPrincipal.getOs());

				Metodos.mensaje("Backup realizado correctamente", 2);

			} catch (Exception e) {
				Metodos.mensaje("Error", 1);
			}
		} else {

			String[] cmdarray = { "/bin/sh", "-c", "mysqldump --no-defaults -h " + lectura[5] + " -u " + lectura[1]
					+ " -p" + lectura[2] + " " + lectura[0] + " > " + backup[0] + "/backupbd.sql" };
			Process process = Runtime.getRuntime().exec(cmdarray);

			Metodos.mensaje("Backup realizado correctamente", 2);
			abrirCarpeta(backup[0]);
		}
	}

	public static void crearScript(String archivo, String contenido, boolean opcional, String os) throws IOException {
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

				fS.write("@echo off");
				fS.newLine();
				fS.write(contenido);
				fS.newLine();
				fS.write("exit");

				aplicacion = Runtime.getRuntime().exec("cmd.exe /K " + iniciar + " " + archivo);

			} finally {
				fS.close();
				flS.close();

			}
		}
		aplicacion.destroy();
	}

	public static void ponerCategoriasBd(JComboBox<String> combobox) throws SQLException, IOException {
		combobox.setFont(new Font("Tahoma", Font.BOLD, 24));
		ArrayList<String> categorias = verCategorias();
		if (!categorias.isEmpty()) {
			try {
				for (int x = 0; x < categorias.size(); x++) {
					combobox.addItem(categorias.get(x));
				}
			} catch (Exception e) {
				Metodos.mensaje("Error al cargar las categorías", 1);
			}
		}
	}

	public static ArrayList<String> verCategorias() throws SQLException, IOException {
		String[] lectura = Metodos.leerFicheroArray("Config/Bd.txt", 7);

		ArrayList<String> categorias = new ArrayList<>();

		if (!lectura[3].isEmpty() && conexionBD() != null) {
			ResultSet rs;
			Connection conexion;
			Statement s;
			try {
				conexion = conexionBD();
				s = conexion.createStatement();

				rs = s.executeQuery("select cat_name FROM " + lectura[3] + "categories ORDER BY cat_id");

				while (rs.next()) {
					categorias.add(rs.getString("cat_name"));
				}
				s.close();
				rs.close();
				conexion.close();
			} catch (Exception e) {
				new Bd().setVisible(true);
			}

		}
		return categorias;
	}

	public static void eliminarFichero(String archivo) {
		File fichero = new File(archivo);
		if (fichero.exists()) {
			fichero.delete();
		}

	}

	public static boolean comprobarConexionBd(String sql) throws SQLException {
		boolean resultado = false;
		Connection conexion = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			conexion = Metodos.conexionBD();

			s = conexion.createStatement();

			rs = s.executeQuery(sql);

			rs.next();

			if (!rs.getString("Nombre").equals("")) {
				resultado = true;
			}
			s.close();
			rs.close();
		} catch (SQLException e) {
			Metodos.mensaje("No hay registros en la base de datos", 3);
		}

		catch (Exception e) {
			try {
				new Bd().setVisible(true);
			} catch (IOException e1) {
				//
			}
		} finally {
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
			} else {

				Metodos.abrirCarpeta(ruta);
			}
		} else {
			new Config().setVisible(true);
		}
	}

	public static boolean comprobarConexion(boolean mensaje) throws IOException {

		boolean conexion = false;
		try {
			String[] lectura2 = leerFicheroArray("Config/Bd.txt", 7);

			if (!lectura2[5].isEmpty()) {

				InetAddress ping;

				ping = InetAddress.getByName(lectura2[5]);

				if (!ping.getCanonicalHostName().equals("")) {
					conexion = true;
				}

			}
		} catch (Exception e) {

			if (mensaje && MenuPrincipal.isConexion()) {
				Metodos.mensaje("Por favor, rellena la configuración de la base de datos", 2);
			}
		}

		return conexion;

	}

	public static boolean comprobarConfiguracion() throws IOException {

		boolean comprobacion = false;

		if (Metodos.comprobarConexion(true)) {
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
			} catch (SQLException e) {
				new Bd().setVisible(true);
			}

		}
		return comprobacion;

	}

	public static Connection conexionBD() throws SQLException, IOException {

		String[] lectura2 = leerFicheroArray("Config/Bd.txt", 7);

		if (comprobarConexion(false)) {

			return DriverManager.getConnection("jdbc:mysql://" + lectura2[5] + "/" + lectura2[0], lectura2[1],
					lectura2[2]);

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
		} finally {
			iterate.close();
		}
		return numLines;
	}

	@SuppressWarnings("all")
	public static void guardarConfig(int opcion, String separador) throws IOException {

		String ruta;

		if (separador.equals("/")) {
			ruta = "/home/";
		} else {
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
		} else {
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
			try {
				bw.write(texto);
			} finally {
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

		String extension = nombreArchivo.substring(nombreArchivo.length() - 3, nombreArchivo.length());

		if (extension.equals("peg")) {
			extension = "jpg";
		}

		return extension;
	}

	public static int listarFicherosPorCarpeta(final File carpeta) {
		int ocurrencias = 0;
		String extension = "";
		ArrayList<String> permitidos = new ArrayList<>();
		permitidos.add(".jpg");
		permitidos.add("jpeg");
		permitidos.add(".png");
		permitidos.add(".gif");

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
					Runtime.getRuntime().exec("cmd /c start " + ruta);
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
			if (abrir) {
				abrirCarpeta(directorio);
			}
		}
	}

	public static void cerrarNavegador(String os) {
		if (os.equals("Linux")) {
			try {
				crearScript("cerrar.sh", "kilall chrome", true, MenuPrincipal.getOs());

			} catch (Exception e) {
				Metodos.mensaje("Error al cerrar el navegador", 1);
			}
		} else {
			try {
				crearScript("cerrar.bat", "taskkill /F /IM chromedriver.exe /T", true, MenuPrincipal.getOs());

			} catch (Exception e) {
				Metodos.mensaje("Error al cerrar el navegador", 1);
			}
		}

	}

	public static LinkedList<String> directorio(String ruta, String extension) {

		LinkedList<String> lista = new LinkedList<>();
		String directorio = ruta;
		File f = new File(directorio);

		if (f.exists()) {

			File[] ficheros = f.listFiles();
			for (int x = 0; x < ficheros.length; x++) {
				String fichero = ficheros[x].getName();
				if (fichero.indexOf(extension) != -1) {
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
			} else {
				for (int x = 0; x < imagenes.size(); x++) {
					Path origenPath = FileSystems.getDefault().getPath(origen + separador + imagenes.get(x));
					Path destinoPath = FileSystems.getDefault().getPath(destino + separador + imagenes.get(x));
					Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
				}
			}
			mensaje = imagenes.size();
		} else {
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
			} while (numRead != -1);
			fis.close();
		} catch (IOException e) {
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
		} else {

			File config = new File(archivo);

			if (config.exists()) {

				if (Metodos.numeroLineas(archivo.substring(archivo.indexOf('/') + 1, archivo.length())) > 0) {
					retorno = true;
				} else {
					retorno = false;
				}
			} else {
				retorno = false;
			}
		}
		return retorno;
	}

	public static void mensaje(String mensaje, int titulo) {
		String tituloSuperior = "";
		int tipo = 0;

		switch (titulo) {

		case 1:
			tipo = JOptionPane.ERROR_MESSAGE;
			tituloSuperior = "Error";
			break;

		case 2:
			tipo = JOptionPane.INFORMATION_MESSAGE;
			tituloSuperior = "Informacion";
			break;

		case 3:
			tipo = JOptionPane.WARNING_MESSAGE;
			tituloSuperior = "Advertencia";
			break;

		default:
			break;
		}

		JLabel alerta = new JLabel(mensaje);
		alerta.setFont(new Font("Arial", Font.BOLD, 18));

		JOptionPane.showMessageDialog(null, alerta, tituloSuperior, tipo);

	}

	public static String getSHA256Checksum(String filename) throws Exception {

		byte[] b = createChecksum(filename);
		String result = "";
		StringBuilder bld = new StringBuilder();

		for (int i = 0; i < b.length; i++) {
			bld.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
		}

		result = bld.toString();

		return result;
	}

	public static void crearCarpetas() {
		File directorio = new File("Config/imagenes");
		directorio.mkdir();
		directorio = new File("Config/imagenes_para_recortar");
		directorio.mkdir();
		directorio = new File("Config/imagenes_para_recortar/recortes");
		directorio.mkdir();
		directorio = new File("Config/Downloads");
		directorio.mkdir();
		directorio = new File("Config/Image_rotate");
		directorio.mkdir();
	}

	public static void eliminarDuplicados(String ruta, String separador) throws Exception {

		try {
			LinkedList<String> imagenes = directorio(ruta, ".");
			imagenes.sort(String::compareToIgnoreCase);
			if (!imagenes.isEmpty()) {
				ArrayList<String> scanimagenes = new ArrayList<>();
				String imagen;

				for (int i = 0; i < imagenes.size(); i++) {

					imagen = getSHA256Checksum(ruta + separador + imagenes.get(i));
					scanimagenes.add(imagen);

				}

				if (imagenes.size() == scanimagenes.size() && imagenes.size() > 1) {
					String shaimagen;

					for (int i = 0; i < scanimagenes.size(); i++) {

						shaimagen = scanimagenes.get(i);

						if (i + 1 != scanimagenes.size() && shaimagen.equals(scanimagenes.get(++i))) {
							File fichero = new File(ruta + separador + imagenes.get(i));
							fichero.delete();
						}

					}

					if (scanimagenes.size() % 2 != 0
							&& scanimagenes.get(0).equals(scanimagenes.get(scanimagenes.size() - 1))) {

						File fichero = new File(ruta + separador + imagenes.get(scanimagenes.size() - 1));
						fichero.delete();
					}
				}

			}
		} catch (ArrayIndexOutOfBoundsException e) {
			Metodos.mensaje("Error", 1);
		}
	}

	public static String saberSeparador(String os) {
		if (os.equals("Linux")) {
			return "/";
		} else {
			return "\\";
		}
	}

}