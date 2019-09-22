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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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

		} catch (Exception ex) {
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

					try {

						String extension = Descarga.textField3.getText().trim();
						// Url con la foto
						URL url = new URL(imagen + x + "." + extension);

						// establecemos conexion
						URLConnection urlCon = url.openConnection();

						// Se obtiene el inputStream de la foto web y se abre el fichero
						// local.
						InputStream is = urlCon.getInputStream();

						FileOutputStream fos = new FileOutputStream("Config" + MenuPrincipal.getSeparador()
								+ "Downloads" + MenuPrincipal.getSeparador() + "Image_" + x + "." + extension);

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

		Metodos.conversion("webp", "png");

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

	public static void moverArchivosDrop(java.io.File[] files, String separador) throws IOException {

		String imagen;
		String comprobacion;
		boolean filtro = false;
		String origen;
		String destino;

		if (files.length > 0) {

			LinkedList<String> listaImagenes = directorio(
					MenuPrincipal.getDirectorioActual() + "Config" + separador + "imagenes", ".");

			String busqueda;

			String salida;

			for (int i = 0; i < files.length; i++) {

				imagen = files[i].getCanonicalPath().substring(files[i].getCanonicalPath().lastIndexOf(separador) + 1,
						files[i].getCanonicalPath().length());

				comprobacion = extraerExtension(imagen);

				if (comprobacion.equals("jpg") || comprobacion.equals("JPG") || comprobacion.equals("peg")
						|| comprobacion.equals("png") || comprobacion.equals("gif") || comprobacion.equals("avi")
						|| comprobacion.equals("mp4") || comprobacion.equals("webp")) {

					origen = files[i].getCanonicalPath();
					destino = new File(".").getCanonicalPath() + separador + "Config" + separador + "imagenes";

					if (origen.substring(0, origen.lastIndexOf(separador)).equals(destino)) {
						Metodos.mensaje("No puedes pegar archivos al mismo directorio", 3);
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

			int recorrido = paso;

			for (paso = recorrido; paso <= size; paso++) {

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

		try {
			out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
		} catch (java.io.UnsupportedEncodingException e) {
			//
		}
		return out;
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
				linea = linea.trim();
				linea = linea.replaceAll("  ", " ");
				contenido.add(convertToUTF8(linea));
			}

			// Creamos un objeto HashSet
			HashSet<String> hs = new HashSet<>();

			// Lo cargamos con los valores del array, esto hace quite los repetidos
			hs.addAll(contenido);

			// Limpiamos el array
			contenido.clear();

			// Agregamos los valores sin repetir
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
		} finally {

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
					//
				}

			}
		}

		return salida;

	}

	public static void exportarBd(int tipo) throws IOException {

		String[] lectura = Metodos.leerFicheroArray("Config/Bd.txt", 7);
		String[] backup = Metodos.leerFicheroArray("Config/Backup.txt", 1);

		if (!MenuPrincipal.getOs().equals("Linux")) {

			try {

				Runtime.getRuntime()
						.exec("cmd.exe /K mysqldump.exe --no-defaults -h " + lectura[5] + " -u " + lectura[1] + " -p"
								+ lectura[2] + " " + lectura[0] + " > " + backup[0] + MenuPrincipal.getSeparador()
								+ "backupbd.sql");

			} catch (Exception e) {
				Metodos.mensaje("Error", 1);
			}
		} else {

			String[] cmdarray = { "/bin/sh", "-c", "mysqldump --no-defaults -h " + lectura[5] + " -u " + lectura[1]
					+ " -p" + lectura[2] + " " + lectura[0] + " > " + backup[0] + "/backupbd.sql" };
			Runtime.getRuntime().exec(cmdarray);

		}

		Metodos.mensaje("Backup realizado correctamente", 2);
		abrirCarpeta(backup[0]);
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
				} catch (Exception e) {
//
				}

			} finally {
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

	public static void eliminarArchivos(String extension, String ruta) {

		LinkedList<String> listaImagenes = Metodos.directorio(ruta, extension);

		for (int i = 0; i < listaImagenes.size(); i++) {

			Metodos.eliminarFichero(ruta + MenuPrincipal.getSeparador() + listaImagenes.get(i));
		}
	}

	public static void eliminarArchivos(LinkedList<String> listaImagenes, String ruta) {

		for (int i = 0; i < listaImagenes.size(); i++) {

			if (!listaImagenes.get(i).isEmpty()) {
				Metodos.eliminarFichero(ruta + listaImagenes.get(i));
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

			} catch (SQLException e) {
				new Bd().setVisible(true);
			}

		}

		return comprobacion;

	}

	public static ArrayList<String> comprobarConexionBD() throws IOException {

		ArrayList<String> categorias = null;

		try {
			categorias = verCategorias();
		} catch (SQLException e1) {
			new Bd().setVisible(true);
		}

		return categorias;
	}

	public static Connection conexionBD() throws SQLException, IOException {

		String[] lectura2 = leerFicheroArray("Config/Bd.txt", 7);

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
		} finally {
			iterate.close();
		}

		return numLines;
	}

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
			extension = "jpeg";
		}

		if (extension.equals("PEG")) {
			extension = "JPEG";
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
			if (abrir) {
				abrirCarpeta(directorio);
			}
		}
	}

	public static LinkedList<String> directorio(String ruta, String extension) {

		int size = extension.length();

		boolean comprobacion;

		LinkedList<String> lista = new LinkedList<>();

		String directorio = ruta;

		File f = new File(directorio);

		if (f.exists()) {

			File[] ficheros = f.listFiles();

			String fichero = "";

			String extensionArchivo;

			File folder;

			for (int x = 0; x < ficheros.length; x++) {

				fichero = ficheros[x].getName();

				comprobacion = fichero.substring(fichero.length() - size, fichero.length()).equals(extension);

				folder = new File(ruta + MenuPrincipal.getSeparador() + fichero);

				extensionArchivo = extraerExtension(fichero);

				if (!folder.isDirectory() && !fichero.equals("recortes") || comprobacion
						|| !comprobacion && (extensionArchivo.equals("jpg") || extensionArchivo.equals("png"))) {

					if (fichero.substring(0, fichero.length() - 5).contains(".")) {

						renombrar(
								"Config" + MenuPrincipal.getSeparador() + "imagenes" + MenuPrincipal.getSeparador()
										+ fichero,
								"Config" + MenuPrincipal.getSeparador() + "imagenes" + MenuPrincipal.getSeparador()
										+ eliminarPuntos(fichero));

					}

					if (extension.equals(".") || extension.equals(extraerExtension(fichero))) {
						lista.add(fichero);
					}

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
			resto = 4;
		}

		for (int i = 0; i < listaImagenes.size(); i++) {

			File f1 = new File("Config" + MenuPrincipal.getSeparador() + "imagenes" + MenuPrincipal.getSeparador()
					+ listaImagenes.get(i));

			File f2 = new File("Config" + MenuPrincipal.getSeparador() + "imagenes" + MenuPrincipal.getSeparador()
					+ listaImagenes.get(i).substring(0, listaImagenes.get(i).length() - resto) + salida);

			f1.renameTo(f2);

		}

	}

	public static void renombrarArchivos(LinkedList<String> listaImagenes, String ruta) {

		File f1;
		File f2;

		for (int x = 0; x < listaImagenes.size(); x++) {

			f1 = new File(ruta + listaImagenes.get(x));
			f2 = new File(ruta + Metodos.eliminarPuntos(listaImagenes.get(x)));
			f1.renameTo(f2);
		}

	}

	public static void renombrar(String ruta1, String ruta2) {

		File f1 = new File(ruta1);
		File f2 = new File(ruta2);

		if (!f1.renameTo(f2)) {
			mensaje("Error al renombrar el archivo " + ruta1 + ", comprueba los permisos", 1);
		}

	}

}