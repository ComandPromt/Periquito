package Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.json.JsonException;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONObject;

import periquito.Bd;
import periquito.Config;
import periquito.Config2;
import periquito.MenuPrincipal;

public abstract class Metodos {

	public static void png_a_jpg(String imagen) {
		BufferedImage bufferedImage;
		try {

			// read image file
			bufferedImage = ImageIO.read(new File(imagen));

			// create a blank, RGB, same width and height, and a white background
			BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

			// write to jpeg file
			ImageIO.write(newBufferedImage, "jpg", new File(imagen.substring(0, imagen.length() - 3) + "jpg"));

			Metodos.eliminarFichero(imagen);

		} catch (IOException e) {

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

	public static JSONObject readJsonFromUrl(String url) throws IOException, JsonException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static void obtenerJSON() throws IOException {

		/*
		 * JSONObject json = readJsonFromUrl(
		 * "https://apiperiquito.herokuapp.com/recibo-json.php?imagenes=b.jpg,a.jpg");
		 * JSONArray imagenes = json.getJSONArray("imagenes"); JSONArray imagenes_bd =
		 * json.getJSONArray("imagenes_bd");
		 * 
		 * for (int i = 0; i < imagenes_bd.length(); i++) {
		 * System.out.println(imagenes.get(i).toString() + " - " +
		 * imagenes_bd.get(i).toString()); }
		 */
	}

	public static boolean probarconexion(String web) {
		String dirWeb = web;
		int puerto = 80;
		try {
			@SuppressWarnings("resource")
			Socket s = new Socket(dirWeb, puerto);
			if (s.isConnected()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
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

	public static void moverArchivosDrop(java.io.File[] files) throws IOException {
		String imagen;
		String comprobacion;
		boolean filtro = false;
		String origen;
		String destino;

		for (int i = 0; i < files.length; i++) {

			imagen = files[i].getCanonicalPath().substring(files[i].getCanonicalPath().lastIndexOf("\\") + 1,
					files[i].getCanonicalPath().length());
			comprobacion = imagen.substring(imagen.length() - 3, imagen.length());

			if (comprobacion.equals("jpg") || comprobacion.equals("peg") || comprobacion.equals("png")
					|| comprobacion.equals("gif") || comprobacion.equals("avi") || comprobacion.equals("mp4")) {

				origen = files[i].getCanonicalPath();
				destino = new File(".").getCanonicalPath() + "\\imagenes";

				if (origen.substring(0, origen.lastIndexOf("\\")).equals(destino)) {
					Metodos.mensaje("No puedes pegar archivos al mismo directorio", 3);
				}

				else {

					Files.move(FileSystems.getDefault().getPath(origen), FileSystems.getDefault().getPath(

							destino + "\\" + origen.substring(origen.lastIndexOf("\\") + 1, origen.length())

					), StandardCopyOption.REPLACE_EXISTING);

					mensaje("Los archivos se han movido correctamente", 2);

				}

			} else {
				filtro = true;
			}
		}
		if (filtro) {
			mensaje("Uno o varios archivos no tiene el formato correcto", 1);
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
		}

		if (!chooser.isMultiSelectionEnabled()) {
			chooser.setMultiSelectionEnabled(true);
		}
		@SuppressWarnings("unused")
		int returnVal = chooser.showOpenDialog(chooser);
		File[] files = chooser.getSelectedFiles();

		if (files.length == 0) {
			mensaje(mensaje, 1);
			return null;
		} else {
			return files;
		}
	}

	public static String[] leerFicheroArray(String fichero, int longitud) {
		String[] salida = new String[longitud];
		String texto = "";
		int i = 0;
		try {
			FileReader flE = new FileReader(fichero);
			BufferedReader fE = new BufferedReader(flE);
			while (texto != null) {
				texto = fE.readLine();
				if (texto != null) {
					salida[i] = texto;
					i++;
				}
			}
			fE.close();
		} catch (IOException e) {

		}
		return salida;
	}

	public static void exportarBd(int tipo) {

		String[] lectura = Metodos.leerFicheroArray("Config/Bd.txt", 6);
		String[] backup = Metodos.leerFicheroArray("Config/Backup.txt", 1);

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

		}

		try {

			crearScript("backupbd.bat", ruta + "mysqldump.exe --no-defaults -h " + lectura[5] + " -u " + lectura[1]
					+ " -p" + lectura[2] + " " + lectura[0] + " > " + backup[0] + "\\backupbd.sql", false);
			crearScript("reiniciar_explorer.bat", "TASKKILL /F /IM explorer.exe\r\n" + "start explorer.exe", true);
			Metodos.mensaje("Backup realizado correctamente", 2);

			abrirCarpeta(backup[0], false);
		} catch (Exception e) {
			Metodos.mensaje("Error", 1);
		}
	}

	protected static void crearScript(String archivo, String contenido, boolean opcional) throws IOException {
		String iniciar = "";
		if (opcional) {
			iniciar = "start";
		}
		FileWriter flS = new FileWriter(archivo);
		BufferedWriter fS = new BufferedWriter(flS);
		fS.write("@echo off");
		fS.newLine();
		fS.write(contenido);
		fS.newLine();
		fS.write("exit");
		fS.close();

		Runtime aplicacion = Runtime.getRuntime();

		try {
			aplicacion.exec("cmd.exe /K " + iniciar + " " + archivo);

		} catch (Exception e) {

		}

		Process p = Runtime.getRuntime().exec("cmd.exe");
		p.destroy();

	}

	public static void ponerCategoriasBd(JComboBox<String> combobox) throws SQLException, IOException {
		combobox.setFont(new Font("Tahoma", Font.BOLD, 24));
		ArrayList<String> categorias = verCategorias();
		if (categorias != null) {
			try {
				for (int x = 0; x < categorias.size(); x++) {
					combobox.addItem(categorias.get(x));
				}
			} catch (Exception e) {

			}
		}
	}

	public static ArrayList<String> verCategorias() throws SQLException, IOException {
		String[] lectura = Metodos.leerFicheroArray("Config/Bd.txt", 6);

		if (!lectura[3].isEmpty() && conexionBD() != null) {
			ArrayList<String> categorias = new ArrayList<String>();

			ResultSet rs = null;
			Connection conexion = conexionBD();

			Statement s = conexion.createStatement();

			rs = s.executeQuery("select cat_name FROM " + lectura[3] + "categories ORDER BY cat_id");

			while (rs.next()) {
				categorias.add(rs.getString("cat_name"));
			}
			s.close();
			rs.close();

			return categorias;
		} else {
			return null;
		}
	}

	public static void eliminarFichero(String archivo) {
		File fichero = new File(archivo);
		if (fichero.exists()) {
			fichero.delete();
		}

	}

	public static boolean comprobarConexion() throws IOException {

		boolean error = false;
		String[] lectura2 = leerFicheroArray("Config/Bd.txt", 6);

		if (lectura2[5] == null || lectura2[5].isEmpty()) {
			error = true;
		} else {
			try {
				InetAddress ping;

				ping = InetAddress.getByName(lectura2[5]);
				if (!ping.isReachable(140)) {
					error = true;
				}
			} catch (Exception e) {
				return false;
			}
		}
		if (!error) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean comprobarConfiguracion() throws IOException {

		boolean error = false;

		if (!Metodos.comprobarConexion()) {
			error = true;
		}

		if (!Metodos.probarconexion("www.google.com")) {
			error = true;
			mensaje("No tienes Internet", 1);
		}

		if (!error) {
			try {
				if (conexionBD() == null) {
					error = true;
				}
			} catch (SQLException e) {

			}
		}

		if (error) {
			return false;
		} else {
			return true;
		}

	}

	public static void mensaje_error() throws IOException {
		mensaje("Configuración errónea", 1);
		new Bd().setVisible(true);
		new Config2().setVisible(true);

	}

	public static Connection conexionBD() throws SQLException, IOException {

		String[] lectura2 = leerFicheroArray("Config/Bd.txt", 6);

		if (comprobarConexion()) {

			Connection conexion = DriverManager.getConnection("jdbc:mysql://" + lectura2[5] + "/" + lectura2[0],
					lectura2[1], lectura2[2]);

			return conexion;
		}

		else {
			return null;

		}
	}

	public static String saberServidor(String cadena) {
		cadena = cadena.replace("https", "");
		cadena = cadena.replace("http", "");
		cadena = cadena.replace("://", "");
		cadena = cadena.substring(0, cadena.indexOf("\\")).trim();
		return cadena;
	}

	public static String eliminarUltimoElemento(String cadena) {
		if (cadena.length() > 1) {
			if (cadena.charAt(cadena.length() - 1) == 92 || cadena.charAt(cadena.length() - 1) == 47) {
				cadena = cadena.substring(0, cadena.length() - 1);
			}

		}
		return cadena;
	}

	public static String eliminarIndices(String cadena) {
		cadena = cadena.trim();
		cadena = cadena.replace("index.php", "");
		cadena = cadena.replace("index.html", "");
		cadena = cadena.replace("index.htm", "");
		cadena = cadena.replace("/", "\\");
		cadena = cadena.replace("//", "\\");
		cadena = cadena.replace("c:", "C:");
		cadena = cadena.replace("http", "C:\\");
		return cadena;
	}

	public static int numeroLineas(String fichero) {
		File input = new File("Config/" + fichero);
		Scanner iterate;
		int numLines = 0;
		try {
			iterate = new Scanner(input);
			while (iterate.hasNextLine()) {
				iterate.nextLine();
				numLines++;
			}
		} catch (FileNotFoundException e) {
			numLines = 0;
		}
		return numLines;
	}

	public static void guardarConfig(int opcion) throws IOException {
		switch (opcion) {
		case 1:
			Metodos.crearFichero("Config/Config.txt", "C:\\Users\\" + System.getProperty("user.name") + "\\Downloads",
					false);
			Config guardar = new Config();
			guardar.guardarDatos(false);
			break;

		case 2:

			Metodos.crearFichero("Config/Config2.txt", "localhost\\media\\images\r\n" + "localhost\\media\\thumbnails",
					false);
			Config2 guardar2 = new Config2();

			guardar2.guardarDatos(false);

			break;

		case 3:
			Metodos.crearFichero("Config", "", true);
			break;

		case 4:
			Metodos.crearFichero("Config/OS.txt", "1", false);
			break;

		case 5:
			Metodos.crearFichero(MenuPrincipal.lectura[0] + "\\FrameExtractor\\examples\\output", "", true);
			Metodos.crearFichero(MenuPrincipal.lectura[0] + "\\FrameExtractor\\examples\\tmp", "", true);
			Metodos.crearFichero(MenuPrincipal.lectura[0] + "\\FrameExtractor\\examples\\video", "", true);
			Metodos.crearFichero(MenuPrincipal.lectura[0] + "\\Hacer_gif\\img", "", true);
			Metodos.crearFichero(MenuPrincipal.lectura[0] + "\\Hacer_gif\\Output", "", true);
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
			BufferedWriter bw;
			bw = new BufferedWriter(new FileWriter(archivo));
			bw.write(texto);
			bw.close();
		}
	}

	public static String comprobarImagenes(String cadena, String comprobacion) {

		if (cadena.length() >= 10
				&& cadena.substring(cadena.length() - 9, cadena.length()).compareTo(comprobacion) != 0) {
			if (comprobacion.substring(0, 1).compareTo("\\") != 0) {
				cadena += "\\" + comprobacion;
			} else {

				cadena += comprobacion;

			}
			cadena = cadena.replace("\\\\", "\\");
		}
		return cadena;
	}

	public static int listarFicherosPorCarpeta(final File carpeta, String filtro) {
		int ocurrencias = 0;

		String extension;
		String nombre_archivo;
		for (final File ficheroEntrada : carpeta.listFiles()) {
			nombre_archivo = ficheroEntrada.getName();
			extension = nombre_archivo.substring(nombre_archivo.length() - 3, nombre_archivo.length());

			if (extension.equals(filtro)) {
				if (ocurrencias == 0) {
					File f1 = new File(MenuPrincipal.lectura[0] + "\\GifFrames\\" + nombre_archivo);
					File f2 = new File(MenuPrincipal.lectura[0] + "\\GifFrames\\picture.gif");
					f1.renameTo(f2);
				}

				ocurrencias++;
			}

		}
		return ocurrencias;
	}

	public static int listarFicherosPorCarpeta(final File carpeta) {
		int ocurrencias = 0;
		String extension = "";
		ArrayList<String> permitidos = new ArrayList<String>();
		permitidos.add(".jpg");
		permitidos.add("jpeg");
		permitidos.add(".png");
		permitidos.add(".gif");

		for (final File ficheroEntrada : carpeta.listFiles()) {

			if (ficheroEntrada.getName().indexOf(".") > 0) {
				extension = ficheroEntrada.getName().substring(ficheroEntrada.getName().length() - 4,
						ficheroEntrada.getName().length());

				if (permitidos.contains(extension)) {
					ocurrencias++;
				}
			}
		}
		return ocurrencias;
	}

	public static void abrirCarpeta(String ruta, Boolean retocar) {
		if (retocar) {
			ruta = Metodos.eliminarIndices(ruta);
		}
		if (ruta != null && ruta != "" && !ruta.isEmpty()) {
			try {
				Runtime.getRuntime().exec("cmd /c start " + ruta);
			} catch (IOException e) {
				mensaje("Ruta inválida", 1);
			}
		} else {
			new Config().setVisible(true);
		}
	}

	public static void notificacion(int salida, String directorio, String tipo, Boolean abrir)
			throws MalformedURLException {
		if (salida <= 0) {
			mensaje("No hay archivos " + tipo + " en la carpeta " + directorio, 1);
			if (abrir) {
				abrirCarpeta(directorio, true);
			}
		}
	}

	public static void cerrarNavegador(int opcion) {

		switch (opcion) {

		case 1:
			try {
				crearScript("cerrar.bat", "taskkill /F /IM chromedriver.exe /T", true);

			} catch (Exception e) {
			}

			break;
		}

	}

	public static LinkedList<String> directorio(String ruta, String extension) {

		LinkedList<String> lista = new LinkedList<String>();
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

	public static int salida(String origen, String destino, int opcion) throws IOException {

		LinkedList<String> imagenes = new LinkedList<String>();

		if (opcion != 9 || (opcion == 9 && origen.contains("Thumb"))) {
			imagenes = directorio(origen, ".jpg");
		}

		else {
			imagenes = directorio(origen, ".gif");
		}

		int mensaje;

		if (imagenes.size() > 0) {

			if (imagenes.size() == 1) {
				destino += "\\" + imagenes.get(0);
				Path origenPath = FileSystems.getDefault().getPath(origen + "\\" + imagenes.get(0));
				Path destinoPath = FileSystems.getDefault().getPath(destino);
				Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
			} else {
				for (int x = 0; x < imagenes.size(); x++) {
					Path origenPath = FileSystems.getDefault().getPath(origen + "\\" + imagenes.get(x));
					Path destinoPath = FileSystems.getDefault().getPath(destino + "\\" + imagenes.get(x));
					Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
				}
			}
			mensaje = imagenes.size();
		} else {
			mensaje = 0;
		}
		return mensaje;
	}

	public static byte[] createChecksum(String filename) throws Exception {
		InputStream fis = new FileInputStream(filename);

		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("SHA-256");
		int numRead;

		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);

		fis.close();
		return complete.digest();
	}

	public static boolean comprobarArchivo(String archivo, boolean tipo) {
		File carpeta = new File(archivo);
		if (!tipo) {
			if (!carpeta.exists()) {
				carpeta.mkdir();
			}
			return false;
		} else {

			File config = new File(archivo);

			if (config.exists()) {

				if (Metodos.numeroLineas(archivo.substring(archivo.indexOf("/") + 1, archivo.length())) > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
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
		}

		JLabel alerta = new JLabel(mensaje);
		alerta.setFont(new Font("Arial", Font.BOLD, 18));

		JOptionPane.showMessageDialog(null, alerta, tituloSuperior, tipo);

	}

	public static String getSHA256Checksum(String filename) throws Exception {
		byte[] b = createChecksum(filename);
		String result = "";

		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	public static void crearCarpetas() {
		File directorio = new File("imagenes");
		directorio.mkdir();
		directorio = new File("imagenes/Thumb");
		directorio.mkdir();
		directorio = new File("imagenes/gif");
		directorio.mkdir();
		directorio = new File("imagenes/gif/Thumb");
		directorio.mkdir();
	}

	public static void eliminarDuplicados(String ruta) {

		String[] lectura;
		try {
			lectura = Metodos.leerFicheroArray("Config/Config.txt", 1);

			LinkedList<String> imagenes = new LinkedList<String>();
			imagenes = directorio(lectura[0].substring(0, lectura[0].length() - 8) + ruta, "jpg");

			if (imagenes.size() > 0) {
				Iterator<String> it = imagenes.iterator();
				ArrayList<String> scanimagenes = new ArrayList<String>();
				String imagen;

				while (it.hasNext()) {
					try {

						imagen = getSHA256Checksum(
								lectura[0].substring(0, lectura[0].length() - 8) + ruta + "\\" + it.next());

						scanimagenes.add(imagen);

					} catch (Exception e) {
					}

				}

				if (imagenes.size() == scanimagenes.size()) {

					for (int i = 0; i < scanimagenes.size(); i++) {

						if (scanimagenes.get(i) == scanimagenes.get(scanimagenes.size() - 1)) {
							i--;
						}

						if (scanimagenes.get(i) == scanimagenes.get(i++)) {
							File fichero = new File(
									lectura[0].substring(0, lectura[0].length() - 8) + ruta + "\\" + imagenes.get(i++));
							fichero.delete();
						}

					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
	}

	public static int searchString(String[] findArray, String stringSearch) {
		int result = -1;
		int cant = 0;
		for (@SuppressWarnings("unused")
		String stringFounded : findArray) {
			if (findArray[cant].equals(stringSearch)) {
				result = cant;
			}
			cant++;
		}
		return result;
	}

}