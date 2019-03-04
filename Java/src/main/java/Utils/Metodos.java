package Utils;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.Socket;
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

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import periquito.Config;
import periquito.Config2;
import periquito.MenuPrincipal;

public abstract class Metodos {

	public static boolean probarconexion() {
		String dirWeb = "www.google.com";
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
		String[] lectura = Metodos.leerFicheroArray("Config/Config.txt", 6);
		for (int i = 0; i < files.length; i++) {
			imagen = files[i].getCanonicalPath().substring(files[i].getCanonicalPath().lastIndexOf("\\") + 1,
					files[i].getCanonicalPath().length());
			comprobacion = imagen.substring(imagen.length() - 3, imagen.length());
			if (comprobacion.equals("jpg") || comprobacion.equals("peg") || comprobacion.equals("png")
					|| comprobacion.equals("gif") || comprobacion.equals("avi") || comprobacion.equals("mp4")) {
				Files.move(FileSystems.getDefault().getPath(files[i].getCanonicalPath()),
						FileSystems.getDefault().getPath(lectura[0] + "\\" + imagen),
						StandardCopyOption.REPLACE_EXISTING);
				mensaje("Los archivos se han movido correctamente", 2);
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

	public static Statement conectarbd() throws SQLException {
		String[] lectura = Metodos.leerFicheroArray("Config/Bd.txt", 5);
		Connection conexion = conexionBD(lectura[0]);

		Statement s = conexion.createStatement();
		return s;
	}

	public static void exportarBd(int tipo) {

		String[] lectura = Metodos.leerFicheroArray("Config/Bd.txt", 5);
		String[] backup = Metodos.leerFicheroArray("Config/Backup.txt", 1);
		String[] config = Metodos.leerFicheroArray("Config/Config2.txt", 2);
		config[0] = config[0].substring(0, config[0].indexOf("\\"));

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
			crearScript("backupbd.bat", ruta + "mysqldump.exe --no-defaults -h " + config[0] + " -u " + lectura[1]
					+ " -p" + lectura[2] + " " + lectura[0] + " > " + backup[0] + "\\backupbd.sql", false);

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

	@SuppressWarnings("unchecked")
	public static void ponerCategoriasBd(@SuppressWarnings("rawtypes") JComboBox combobox) {
		combobox.setFont(new Font("Tahoma", Font.BOLD, 24));
		for (int x = 0; x < MenuPrincipal.getCategorias().size(); x++) {
			combobox.addItem(MenuPrincipal.getCategorias().get(x));
		}
	}

	public static ArrayList<String> verCategorias() throws SQLException {
		ArrayList<String> categorias = new ArrayList<String>();
		String[] lectura = Metodos.leerFicheroArray("Config/Bd.txt", 5);
		ResultSet rs = null;
		Statement s;
		s = Metodos.conectarbd();
		rs = s.executeQuery("select cat_name FROM " + lectura[3] + "categories ORDER BY cat_id");

		while (rs.next()) {
			categorias.add(rs.getString("cat_name"));
		}
		s.close();
		rs.close();
		return categorias;
	}

	public static void eliminarFichero(String archivo) {
		File fichero = new File(archivo);
		if (fichero.exists()) {
			fichero.delete();
		}

	}

	public static Connection conexionBD(String bd) throws SQLException {
		String[] lectura = leerFicheroArray("Config/Config2.txt", 2);

		String[] lectura2 = leerFicheroArray("Config/Bd.txt", 5);

		if (!bd.equals("") || lectura2[0].equals("") || lectura2[0] == null) {
			lectura2[0] = bd;
		}

		lectura[0] = lectura[0].replace("https", "");
		lectura[0] = lectura[0].replace("http", "");
		lectura[0] = lectura[0].replace("://", "");
		lectura[0] = lectura[0].substring(0, lectura[0].indexOf("\\")).trim();

		Connection conexion = DriverManager.getConnection("jdbc:mysql://" + lectura[0] + "/" + lectura2[0], lectura2[1],
				lectura2[2]);
		return conexion;
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
			Metodos.crearFichero("Config/Config.txt",
					"C:\\AppServ\\www\\Periquito\\imagenes\r\n" + "http://localhost/Periquito\r\n"
							+ "C:\\AppServ\\www\\Periquito\\Hacer_gif\r\n" + "http://localhost/Periquito\\Hacer_gif\r\n"
							+ "C:\\AppServ\\www\\Periquito\\GifFrames\r\n" + "http://localhost/Periquito\\GifFrames");
			Config guardar = new Config();
			guardar.guardarDatos(false);
			break;

		case 2:
			Metodos.crearFichero("Config/Config2.txt", "localhost\\media\\images\r\n" + "localhost\\media\\thumbnails");
			Config2 guardar2 = new Config2();
			guardar2.guardarDatos(false);
			break;

		case 4:
			Metodos.crearFichero("Config/OS.txt", "1");
			break;

		}

	}

	public static void crearFichero(String ruta, String texto) throws IOException {
		File archivo = new File(ruta);
		BufferedWriter bw;
		bw = new BufferedWriter(new FileWriter(archivo));
		bw.write(texto);
		bw.close();
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

	public static int listarFicherosPorCarpeta(final File carpeta) {
		int ocurrencias = 0;
		for (final File ficheroEntrada : carpeta.listFiles()) {
			if (ficheroEntrada.getName().indexOf(".") > 0) {
				ocurrencias++;
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

	public static void comprobarArchivo(String archivo) {
		File carpeta = new File(archivo);

		if (!carpeta.exists()) {
			carpeta.mkdir();
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

	public static void eliminarDuplicados(String ruta) {

		String[] lectura;
		try {
			lectura = Metodos.leerFicheroArray("Config/Config.txt", 6);

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