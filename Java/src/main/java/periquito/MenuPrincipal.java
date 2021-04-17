package periquito;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TooManyListenersException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import utils.ComprobarSha;
import utils.DragAndDrop;
import utils.Img2Txt;
import utils.Metodos;
import utils.MyInterface;
import utils.PhotoFrame;

@SuppressWarnings("serial")

public class MenuPrincipal extends JFrame implements ActionListener, ChangeListener, MyInterface {

	public static LinkedList<String> idCategorias = new LinkedList<>();

	private static LinkedList<String> imagenesBN = new LinkedList<>();

	private static boolean subirSoloGif = false;

	static String pagina;

	static JSONObject json;

	static Progreso progreso;

	static JCheckBox chckbxNewCheckBox;

	private static int cat;

	private int tecla1, tecla2;

	private int vuelta;

	private JMenuBar menuopciones;

	private JMenu mnAcciones, menu1, menu2, menu3, menu4, mnConfig;

	static WebDriver chrome;

	static int y = 0;

	static int total = 1;

	private JMenuItem menuItem, menuItem1, menuItem4;

	private JSeparator separator, separator1;

	private JMenuItem menuItem2, menuItem3, menuItem5, menuItem6, menuItem7, menuItem8;

	private JSeparator separator2, separator3, separator4, separator5, separator6;

	private JSeparator separator7, separator8, separator9, separator10, separator11;

	private JMenuItem menuItem9, menuItem10, menuItem11, menuItem13;

	private JMenuItem menuItem14, menuItem15, menuItem16, menuItem17;

	private JSeparator separator12, separator13, separator14, separator22;

	private JMenu menu7;

	private JMenuItem menuItem18, menuItem19;

	private static JTextField textField;

	private static JCheckBox rdbtnNewRadioButton = new JCheckBox(" Subir GIF");

	private static String[] lectura;

	private static String[] user;

	static String[] sonido;

	private static String[] configuracion;

	private static String carpeta = "";

	private JMenuItem mntmNewMenuItem, mntmNewMenuItem1, mntmDownloads, mntmComentarios;

	private JMenu mnNewMenu, mnNewMenu1;

	private JMenuItem mmenu1, mmenu2, mmenu3, mmenu5;

	static LinkedList<String> listaImagenes = new LinkedList<>();

	private static JLabel lblNewLabel_2 = new JLabel("");

	private static LinkedList<String> imagenesSubidas = new LinkedList<>();

	private String carpetaBn;

	static LinkedList<String> categorias = new LinkedList<>();

	static String os = System.getProperty("os.name");

	static String separador = Metodos.saberSeparador(os);

	static String[] lecturaurl;

	static String[] permisos;

	static String[] lecturabd;

	static JButton button = new JButton("");

	static String[] lecturabackup;

	static String directorioActual;

	static String directorioImagenes;

	private static String etiqueta = "";

	static JComboBox<String> comboBox = new JComboBox<>();

	static String nombreSubida;

	static boolean gif = false;

	JSeparator miSeparator9;

	JComboBox<String> comboBox_1 = new JComboBox<String>();

	static Timer timer = new Timer();

	static TimerTask task = new CronWebp();

	public static String crontabWebp = "";

	private static LinkedList<String> claves = new LinkedList<String>();

	private static LinkedList<String> valores = new LinkedList<String>();

	private static LinkedList<String> imagenesMeta = new LinkedList<String>();

	private JMenu mnNewMenu_1;

	private JMenu mnNewMenu_3;

	private static JCheckBox soloGif;

	private JSeparator separator_2;

	private JMenuItem mntmNewMenuItem_2;

	private JSeparator separator_3;

	private JMenuItem mntmNewMenuItem_3;

	private JSeparator separator_6;

	private JMenu mnNewMenu_2;

	private JMenuItem mntmNewMenuItem_1;

	private JMenuItem mntmNewMenuItem_4;

	private JMenuItem mntmNewMenuItem_5;

	private JSeparator separator_8;

	private JMenu mnNewMenu_4;

	private JSeparator separator_9;

	private JMenuItem mntmNewMenuItem_6;

	private JSeparator separator_10;

	static String nombreImagenes = "";

	private JMenuItem mntmNewMenuItem_7;

	private JMenuItem mntmNewMenuItem_8;

	private JSeparator separator_12;

	private JMenu mnNewMenu_5;

	private JMenuItem mntmNewMenuItem_9;

	private JMenuItem mntmNewMenuItem_10;

	private JMenuItem mntmNewMenuItem_11;

	private JSeparator separator_15;

	private JSeparator separator_14;

	private JMenuItem mntmNewMenuItem_12;

	private JSeparator separator_16;

	private static boolean subir = false;

	private JMenuItem mntmNewMenuItem_13;

	private JSeparator separator_17;

	private JMenuItem mntmNewMenuItem_14;

	private JSeparator separator_18;

	private JMenuItem mntmNewMenuItem_15;

	private JSeparator separator_19;

	private JMenuItem mntmNewMenuItem_16;

	private JMenu mnNewMenu_6;

	private JMenuItem mntmNewMenuItem_17;

	private JMenuItem mntmNewMenuItem_18;

	private JSeparator separator_21;

	private JSeparator separator_22;

	private JMenuItem mntmNewMenuItem_19;

	private JMenuItem mntmNewMenuItem_20;

	private JSeparator separator_23;

	private JMenu mnNewMenu_7;

	private JMenuItem mntmNewMenuItem_21;

	private JMenuItem mntmNewMenuItem_22;

	private JMenuItem mntmNewMenuItem_23;

	private JSeparator separator_24;

	private JMenuItem mntmNewMenuItem_24;

	private JSeparator separator_25;

	private JSeparator separator_26;

	private JMenuItem mntmNewMenuItem_25;

	private JSeparator separator_27;

	static String categoria = "";

	static int idCategoria = 0;

	private JMenuItem mntmNewMenuItem_26;

	private JMenuItem mntmNewMenuItem_27;

	private JSeparator separator_29;

	private JMenu mnNewMenu_8;

	private JMenuBar menuBar;

	private JSeparator separator_31;

	private JMenuItem mntmNewMenuItem_29;

	private JSeparator separator_32;

	private JMenuItem mntmNewMenuItem_30;

	private JSeparator separator_33;
	private JMenuItem mntmNewMenuItem_32;
	private JSeparator separator_35;
	private JMenuItem mntmNewMenuItem_33;
	private JSeparator separator_36;

	public static String[] getPermisos() {
		return permisos;
	}

	public static void setPermisos(String[] permisos) {
		MenuPrincipal.permisos = permisos;
	}

	public static String getDirectorioImagenes() {
		return directorioImagenes;
	}

	public static String[] getUser() {
		return user;
	}

	public static void setUser(String[] user) {
		MenuPrincipal.user = user;
	}

	public static void setLectura(String[] lectura) {
		MenuPrincipal.lectura = lectura;
	}

	public static void setSonido(String[] sonido) {
		MenuPrincipal.sonido = sonido;
	}

	public static void setLecturaurl(String[] lecturaurl) {
		MenuPrincipal.lecturaurl = lecturaurl;
	}

	public static LinkedList<String> getCategorias() {
		return categorias;
	}

	public static String getOs() {
		return os;
	}

	public static String[] getLecturaurl() {
		return lecturaurl;
	}

	public static void setLecturabd(String[] lecturabd) {
		MenuPrincipal.lecturabd = lecturabd;
	}

	public static String[] getSonido() {
		return sonido;
	}

	public static void setLecturabackup(String[] lecturabackup) {
		MenuPrincipal.lecturabackup = lecturabackup;
	}

	public static String[] getLecturabd() {
		return lecturabd;
	}

	public static String getSeparador() {
		return separador;
	}

	public static LinkedList<String> getIdCategorias() {
		return idCategorias;
	}

	public static void setIdCategorias(String string) {
		idCategorias.add(string);
	}

	private static void moverListaImagenes(int categoria, String web, LinkedList<String> lista) {

		for (int i = 0; i < lista.size(); i++) {

			if (Metodos.pingURL("http://" + web + "data/media/" + categoria + "/" + lista.get(i))) {

				Metodos.moverArchivo(directorioImagenes + separador + lista.get(i),
						directorioImagenes + separador + "imagenes_subidas" + separador + lista.get(i));

			}

		}
	}

	private int saberTipoDrop() {

		int tipo = 0;

		switch (comboBox_1.getSelectedIndex()) {

		case 0:

		case 1:

		case 3:
			tipo = 1;
			break;

		case 2:
			tipo = 3;
			break;

		case 4:
		case 5:
			tipo = 2;
			break;

		default:
			tipo = 1;
			break;
		}

		return tipo;

	}

	private void renombrarArchivosDrop() throws IOException {

		switch (comboBox_1.getSelectedIndex()) {

		case 0:
			Metodos.renombrarArchivos(directorioImagenes + separador, ".", true);
			break;

		case 1:
			Metodos.renombrarArchivos(lectura[0] + "Hacer_gif" + separador + "frames", ".", true);
			break;

		case 2:
			Metodos.renombrarArchivos(lectura[0] + "Gif_extractor", ".", true);
			break;

		case 3:
			Metodos.renombrarArchivos(directorioActual + "Config" + separador + "imagenes_para_recortar" + separador
					+ "recortes" + separador, ".", true);

			new PhotoFrame().setVisible(true);

			break;

		case 4:
			Metodos.renombrarArchivos(lectura[0] + "FrameExtractor", ".", true);
			break;

		case 5:
			Metodos.renombrarArchivos(lectura[0] + "Video_2_Gif", ".", true);
			break;

		}

	}

	protected static void extraerNombreComun() {

		nombreImagenes = textField.getText().trim();

		if (!nombreImagenes.isEmpty()) {
			nombreSubida = nombreImagenes;
		}

		else {
			if (!configuracion[0].isEmpty()) {
				nombreSubida = Metodos.eliminarEspacios(configuracion[0], false);
			}
		}
	}

	private void abrirCarpetaGif() {

		try {
			Metodos.abrirCarpeta(lectura[0] + "Hacer_gif" + separador + "frames");
		}

		catch (IOException e1) {
			Metodos.mensaje("Error", 1);
		}

	}

	public static void uploadImages() throws IOException {

		try {

			listaImagenes = Metodos.directorio(directorioImagenes + separador, ".", true, false);

			if (!Metodos.comprobarFirefox(os)) {

				Metodos.mensaje("Por favor, instala el navegador Firefox", 3);

			}

			else {

				String web = lecturaurl[0];

				imagenesSubidas.clear();

				Runtime.getRuntime().exec("python web_to_png.py");

				task.cancel();

				task = new CronWebp();

				timer = new Timer();

				timer.schedule(task, 0, 1000);

				if (crontabWebp != null && crontabWebp.equals("finish")) {

					task.cancel();

					timer.cancel();

					timer.purge();

					claves.clear();

					valores.clear();

					imagenesMeta.clear();

					Metodos.crearCarpetas();

					File file = new File(directorioActual + "geckodriver.exe");

					if (os.contains("Win") && !file.exists()) {

						Metodos.mensaje("Debes tener el archivo geckodriver.exe en la raíz del programa", 3);
					}

					file = new File(directorioActual + "geckodriver");

					if (os.contains("Linux") && !file.exists()) {

						Metodos.mensaje("Debes tener el archivo geckodriver en la raíz del programa", 3);
					}

					if (MenuPrincipal.textField.getText().isEmpty()) {

						Metodos.mensaje("Por favor, introduzca el nombre común de las imágenes", 3);
					}

					else {

						Metodos.borrarArchivosDuplicados(directorioImagenes + separador);

						Metodos.borrarArchivosSubidos(directorioImagenes + separador);

						Metodos.renombrarArchivos(directorioImagenes + MenuPrincipal.getSeparador(), ".", true);

						if (comboBox.getItemCount() == 0) {

							new Bd().setVisible(true);

						}

						else {

							extraerNombreComun();

							boolean subirImagen = true;

							if (Metodos.listarFicherosPorCarpeta(new File(directorioImagenes), ".") == 0) {

								subirImagen = false;
							}

							if (subirImagen) {

								actualizar();

								if (!configuracion[1].equals("0") && Integer.parseInt(configuracion[1]) > 0) {

									idCategoria = Integer.parseInt(configuracion[1]);

									categoria = Metodos.saberCategoria(idCategoria);
								}

								else {

									idCategoria = Metodos.saberIdCategoria(comboBox.getSelectedItem().toString());

									categoria = categorias.get(comboBox.getSelectedIndex());
								}

								listaImagenes.clear();

								if (!soloGif.isSelected()) {

									listaImagenes = Metodos.directorio(directorioImagenes + separador, ".", true,
											false);

								}

								else {

									MenuPrincipal.subirSoloGif = true;

									listaImagenes = Metodos.directorio(directorioImagenes + separador, "gif", true,
											false);
								}

								MenuPrincipal.lblNewLabel_2.setText(Metodos.calcularTiempo(listaImagenes.size()));

								if (subir) {

									if (!(progreso instanceof Progreso)) {

										progreso = new Progreso();

									}

									subirFotos(idCategoria, web);

									lblNewLabel_2.setText("");

								}

								else {

									int resp = JOptionPane.showConfirmDialog(null,
											"Las imágenes se subirán a la categoría : " + categoria + " ¿Está seguro?");

									if (resp == 0) {

										subir = true;

										chrome.close();

										Metodos.cerrarNavegador();

										configuracion = Metodos.leerFicheroArray("Configuracion.txt", 7);

										if (Metodos.pingURL("http://" + lecturaurl[0] + carpeta + "/index.php")) {

											if (!chckbxNewCheckBox.isSelected()) {

												comprobarBN();

											}

											if (configuracion[3].isEmpty()) {

												etiqueta = JOptionPane.showInputDialog(null, "Escribe la/s etiqueta/s");

											}

											else {

												etiqueta = configuracion[3];
											}

											// insertar en etiquetas y luego en tag

											if (!web.substring(web.length() - 1).equals("7")) {
												web += "/";
											}

											if (!lecturaurl[1].trim().isEmpty()) {
												web += lecturaurl[1] + "/";
											}

											if (!(progreso instanceof Progreso)) {

												progreso = new Progreso();

											}

											subirFotos(idCategoria, web);

											lblNewLabel_2.setText("");
										}

										else {

											Metodos.mensaje("Por favor, revisa la configuración", 3);

											new Config2().setVisible(true);

										}

									}

									else {
										subir = false;
									}

								}

							}

						}

						MenuPrincipal.lblNewLabel_2.setText("");

					}
				}

				if (subir && Metodos.listarFicherosPorCarpeta(new File(directorioImagenes), ".") > 0) {

					listaImagenes = Metodos.directorio(directorioImagenes + separador, ".", true, false);

					if (soloGif.isSelected()) {
						listaImagenes = Metodos.directorio(directorioImagenes + separador, "gif", true, false);
					}

				}

				if (progreso instanceof Progreso) {
					Metodos.cerrarNavegador();
				}

				if (subir && listaImagenes.size() > 0) {

					if (!(progreso instanceof Progreso)) {

						progreso = new Progreso();

					}

					subirFotos(idCategoria, web);

					lblNewLabel_2.setText("");

				}

				resultadoSubida();

				imagenesSubidas.clear();

				y = 0;

				total = 1;

				subir = false;

				if (chrome != null) {
					chrome.close();
				}

				Metodos.cerrarNavegador();

			}

		}

		catch (Exception e2) {

		}

	}

	private static void resultadoSubida() {

		try {

			if (y > 0 && total > 0) {

				if (total < y) {
					total = y;
				}

				progreso.setProgressBarRecorrido("Imagen " + y + " de " + total);

				progreso.setProgressBar(Metodos.calcularPorcentaje(y, total));

			}

		}

		catch (Exception e) {

		}

	}

	public static void setCategorias(String string) {
		categorias.add(string);
	}

	private String saberDondeSeMueve() {

		String destino = directorioImagenes;

		String carpeta = Metodos.formatearRuta();

		switch (comboBox_1.getSelectedIndex()) {

		case 0:
			destino = directorioImagenes;
			break;

		case 1:
			destino = carpeta + separador + "Hacer_gif" + separador + "frames";
			break;

		case 2:
			destino = carpeta + separador + "Gif_extractor";

			break;

		case 3:
			destino = directorioActual + "Config" + separador + "imagenes_para_recortar";

			break;

		case 4:
			destino = carpeta + separador + "Frame_Extractor";
			break;

		case 5:
			destino = carpeta + separador + "Video_2_Gif";
			break;

		default:
			destino = directorioImagenes;
			break;

		}

		return destino;
	}

	protected void comprobarExif() throws IOException {

		String imagen;

		listaImagenes = Metodos.directorio(directorioImagenes + separador, "jpg", true, false);

		lecturaurl = Metodos.leerFicheroArray("Config2.txt", 2);

		obtenerCarpeta();

		int total = listaImagenes.size();

		String ruta = "http://" + lecturaurl[0] + "/bn-image-check-api/index.php";

		lecturaurl[1] = lecturaurl[1].trim();

		lecturaurl[1] = lecturaurl[1].replace("  ", " ");

		if (lecturaurl[1].length() > 1) {
			ruta = "http://" + lecturaurl[0] + carpeta + "/bn-image-check-api/index.php";
		}

		Metodos.cerrarNavegador();

		chrome = new FirefoxDriver();

		for (int i = 0; i < total; i++) {

			chrome.get(ruta);

			imagen = directorioImagenes + separador + listaImagenes.get(i);

			chrome.findElement(By.name("uploadedfile")).sendKeys(imagen);

			chrome.findElement(By.name("subir")).click();

			String text = chrome.findElement(By.cssSelector("pre")).getText();

			imagen = listaImagenes.get(i);

			json = new JSONObject(text);

			boolean resultado = json.getBoolean("resultado");

			int respuesta = json.getInt("respuesta");

			if (respuesta == 200 && resultado) {

				imagenesBN.add(imagen);
			}

		}

		chrome.close();

		Metodos.cerrarNavegador();

		for (int i = 0; i < imagenesBN.size(); i++) {

			Files.move(FileSystems.getDefault().getPath(directorioImagenes + separador + imagenesBN.get(i)),
					FileSystems.getDefault().getPath(

							directorioImagenes + separador + "bn" + separador + imagenesBN.get(i)

					), StandardCopyOption.REPLACE_EXISTING);

		}

	}

	protected static void comprobarBN() throws IOException {

		Metodos.cerrarNavegador();

		String imagen;

		listaImagenes = Metodos.directorio(directorioImagenes + separador, "jpg", true, false);

		lecturaurl = Metodos.leerFicheroArray("Config2.txt", 2);

		obtenerCarpeta();

		int total = listaImagenes.size();

		String ruta = "http://" + lecturaurl[0] + "/bn-image-check-api/index.php";

		lecturaurl[1] = lecturaurl[1].trim();

		lecturaurl[1] = lecturaurl[1].replace("  ", " ");

		if (lecturaurl[1].length() > 1) {
			ruta = "http://" + lecturaurl[0] + carpeta + "/bn-image-check-api/index.php";
		}

		chrome = new FirefoxDriver();

		for (int i = 0; i < total; i++) {

			chrome.get(ruta);

			imagen = directorioImagenes + separador + listaImagenes.get(i);

			chrome.findElement(By.name("uploadedfile")).sendKeys(imagen);

			chrome.findElement(By.name("subir")).click();

			String text = chrome.findElement(By.cssSelector("pre")).getText();

			imagen = listaImagenes.get(i);

			json = new JSONObject(text);

			boolean resultado = json.getBoolean("resultado");

			int respuesta = json.getInt("respuesta");

			if (respuesta == 200 && resultado) {

				imagenesBN.add(imagen);
			}

		}

		chrome.close();

		Metodos.cerrarNavegador();

		for (int i = 0; i < imagenesBN.size(); i++) {

			Files.move(FileSystems.getDefault().getPath(directorioImagenes + separador + imagenesBN.get(i)),
					FileSystems.getDefault().getPath(

							directorioImagenes + separador + "bn" + separador + imagenesBN.get(i)

					), StandardCopyOption.REPLACE_EXISTING);

		}

	}

	private void recortarFotos() {

		try {

			if (!listaImagenes.isEmpty()) {

				Metodos.renombrarArchivos(
						directorioActual + "Config" + separador + "imagenes_para_recortar" + separador, ".", true);
			}

			listaImagenes = Metodos.directorio(
					directorioActual + "Config" + separador + "imagenes_para_recortar" + separador, ".", true, false);

			new PhotoFrame().setVisible(true);
		}

		catch (Exception e1) {

		}
	}

	public static LinkedList<String> getListaImagenes() {
		return listaImagenes;
	}

	private void abrirNotas() {

		try {

			if (Metodos.comprobarConfiguracion()) {
				new AgendaInterfaz().setVisible(true);
			}

			else {
				new Bd().setVisible(true);
			}

		}

		catch (Exception e1) {
			//
		}

	}

	public static void hacerGIF() throws IOException {

		try {

			String carpeta = lectura[0] + "Hacer_gif" + separador + "frames";

			String rutaFrames = lectura[0] + "Hacer_gif" + separador + "frames" + separador;

			if (Metodos.listarFicherosPorCarpeta(new File(carpeta), ".") <= 1) {

				Metodos.mensaje("Tienes que tener al menos 2 imágenes para crear un GIF", 3);

				Metodos.abrirCarpeta(rutaFrames);

			}

			else {

				if (Metodos.listarFicherosPorCarpeta(new File(carpeta), ".") > 170) {

					Metodos.mensaje("Has superado el límite de imágenes para crear un GIF", 3);

					Metodos.abrirCarpeta(rutaFrames);
				}

				else {
					animGif();
				}
			}
		}

		catch (Exception e) {
			new Config().setVisible(true);
		}
	}

	static void subida() {

		try {

			Metodos.moverArchivos(Metodos.directorio(
					Metodos.formatearRuta() + separador + "Hacer_gif" + separador + "Output" + separador, "gif", true,
					true), separador, directorioImagenes, false, 3);

			Metodos.abrirCarpeta(directorioImagenes);

		}

		catch (Exception e1) {
			//
		}

	}

	private void video2Frames() {

		try {

			String carpeta = Metodos.formatearRuta();

			carpeta += separador + "Frame_Extractor";

			Metodos.comprobarConexion("Config/Config.txt", carpeta);

		}

		catch (ArrayIndexOutOfBoundsException e1) {
			Metodos.mensaje("Error en el  archivo Config.txt", 1);
		}

		catch (Exception e1) {
			//
		}

	}

	private void videoToFrame() throws Exception {

		try {

			if (lectura[1] == null || lectura[1].equals("")) {

				Config guardar = new Config();

				guardar.guardarDatos(false);
			}

			else {

				if (Metodos.listarFicherosPorCarpeta(new File(Metodos.formatearRuta() + separador + "Frame_Extractor"),
						".") != 1) {

					Metodos.mensaje("Debes tener un vídeo para poder crear los fotogramas", 3);

					Metodos.abrirCarpeta(carpeta);
				}

				else {

					Runtime.getRuntime().exec("python3 video_frames.py");

					task.cancel();

					task = new CronWebp();

					timer = new Timer();

					timer.schedule(task, 0, 1000);

				}

			}

		}

		catch (ArrayIndexOutOfBoundsException e) {
			Metodos.mensaje("Error en el archivo Config.txt", 1);
		}

	}

	private void comprobarSha() {

		ArrayList<String> listaCategorias;

		try {

			listaCategorias = Metodos.comprobarConexionBD();

			if (!listaCategorias.isEmpty()) {

				new ComprobarSha().setVisible(true);

			}
		}

		catch (Exception e2) {

			try {

				new Bd().setVisible(true);
			}

			catch (IOException e1) {
				//
			}
		}
	}

	public static void mensaje170() {

		Metodos.mensaje("Tienes más de 170 imágenes", 3);

		try {
			Metodos.abrirCarpeta(lectura[0] + "Hacer_gif" + separador + "frames");
		}

		catch (IOException e1) {

			try {
				new Config().setVisible(true);
			}

			catch (IOException e2) {
				Metodos.mensaje("Error", 1);
			}
		}
	}

	public static void animGif() {

		try {

			int recuento = Metodos.listarFicherosPorCarpeta(new File(lectura[0] + "Hacer_gif" + separador + "frames"),
					".");

			if (recuento <= 170) {

				Runtime.getRuntime().exec("python run.py");

				task.cancel();

				task = new CronWebp();
				timer = new Timer();
				timer.schedule(task, 0, 1000);

			}

			else {
				mensaje170();
			}

		}

		catch (Exception e1) {

			try {
				new Config().setVisible(true);
			}

			catch (IOException e2) {
				//
			}

		}
	}

	private void buscarFotos() {

		try {
			new Busqueda().setVisible(true);
		}

		catch (IOException e) {

			try {

				Metodos.mensaje("Comprueba la configuración", 3);
				new Bd().setVisible(true);
				new Config2().setVisible(true);
			}

			catch (IOException e1) {
				//
			}

		}
	}

	private void video2Frame() {

		try {
			videoToFrame();
		}

		catch (Exception e) {

			e.printStackTrace();

		}
	}

	private static String ponerSeparador(String cadena) {

		String busquedaSeparador = cadena.substring(cadena.length() - 1, cadena.length());

		String resultado = cadena;

		if (!busquedaSeparador.equals("\\") && !busquedaSeparador.equals("/")) {
			resultado = cadena + separador;
		}

		return resultado;
	}

	public MenuPrincipal() throws Exception {

		addKeyListener(new KeyAdapter() {

			@Override

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					try {
						uploadImages();
					}

					catch (IOException e1) {
						//
					}
				}
			}

		});

		getContentPane().setBackground(Color.WHITE);

		Metodos.crearCarpetas();

		Metodos.crearCarpetasConfig();

		directorioActual = new File(".").getCanonicalPath() + separador;

		directorioImagenes = directorioActual + "Config" + separador + "imagenes";

		lecturabd = Metodos.leerArchivo("Bd.txt", 6,
				"4images\r\n" + "root\r\n" + "root\r\n" + "4images_\r\n" + "3306\r\n" + "localhost", false);

		lectura = Metodos.leerArchivo("Config.txt", 2, directorioActual + "http://localhost/Periquito", false);

		lectura[0] = ponerSeparador(lectura[0]);

		carpetaBn = lectura[0] + "colorization" + separador + "imgs" + separador;

		user = Metodos.leerArchivo("User.txt", 2, "root\r\n" + "root", false);

		sonido = Metodos.leerArchivo("sonido.txt", 2, "gong.wav\r\n" + "1", false);

		configuracion = Metodos.leerArchivo("Configuracion.txt", 7,
				"\r\n" + "0\r\n" + "\r\n" + "\r\n" + "0\r\n" + "0\r\n" + "0\r\n", false);

		lecturaurl = Metodos.leerArchivo("Config2.txt", 2, "127.0.0.1\r\n" + "4images_", false);

		permisos = Metodos.leerArchivo("Permisos.txt", 4, "2\r\n" + "1\r\n" + "1\r\n" + "1", false);

		lecturabackup = Metodos.leerArchivo("Backup.txt", 1, directorioActual, false);

		setIconImage(
				Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/maxresdefault.jpg")));

		Metodos.crearCarpetas();

		Metodos.crearCarpetasConfig();

		Metodos.crearFichero("Config/llamada_python.txt", "", false);

		Metodos.crearFichero("Config" + separador + "GifFrames", "", true);

		Metodos.guardarConfig(3, separador);

		if (lectura[0] == null || lectura[0].equals("")) {
			Metodos.guardarConfig(1, separador);
		}

		if (lecturaurl[0] == null || lecturaurl[0].equals("")) {
			Metodos.guardarConfig(2, separador);
		}

		if (os == null || os.equals("")) {
			os = System.getProperty("os.name");
			separador = Metodos.saberSeparador(os);
		}

		try {
			Metodos.probarconexion("www.google.com");
		}

		catch (UnknownHostException e) {
			Metodos.mensaje("No hay conexión a internet", 3);
		}

		if (permisos[0] == null) {
			Metodos.crearFichero("Config/Permisos.txt", "2\r\n" + "1\r\n" + "1\r\n" + "1\r\n", false);
		}

		if (sonido[0] == null) {
			Metodos.crearFichero("Config/sonido.txt", "gong.wav\n1", false);
		}

		if (configuracion[0] == null) {
			Metodos.crearFichero("Config/Configuracion.txt",
					" \r\n" + "0\r\n" + " \r\n" + " \r\n" + "0\r\n" + "0\r\n" + "0", false);
		}

		if (!Metodos.comprobarConexion(false) || lecturabd[0] == null || lecturabd[0].equals("")) {

			Metodos.crearFichero("Config/Bd.txt",
					"4images\r\n" + "root\r\n" + "root\r\n" + "4images_\r\n" + "3306\r\n" + "localhost", false);

			new Bd().setVisible(true);
		}

		if (lecturabackup[0] == null || lecturabackup[0].equals("")) {

			if (!os.equals("Linux")) {
				Metodos.crearFichero("Config/Backup.txt", "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop",
						false);
			}

			else {
				Metodos.crearFichero("Config/Backup.txt", "/home/" + System.getProperty("user.name") + "/Escritorio",
						false);

			}
		}

		setTitle("Periquito v3");

		menuopciones = new JMenuBar();
		setJMenuBar(menuopciones);

		mnAcciones = new JMenu("Acciones");
		mnAcciones.setMnemonic(' ');
		mnAcciones.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/utilities.png")));
		mnAcciones.setForeground(Color.BLACK);
		mnAcciones.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnAcciones.setBackground(Color.BLACK);
		menuopciones.add(mnAcciones);

		menu1 = new JMenu("Imagen");
		menu1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gif.png")));
		menu1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnAcciones.add(menu1);

		menuItem = new JMenuItem("GIF Animator");
		menuItem.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				Metodos.crearCarpetasConfig();

				try {

					hacerGIF();

				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		});

		menuItem.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gifanim.png")));
		menuItem.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menu1.add(menuItem);

		separator = new JSeparator();
		menu1.add(separator);

		menuItem1 = new JMenuItem("GIF Extractor");

		menuItem1.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				try {

					Metodos.crearCarpetasConfig();

					String directorio = lectura[0] + "Gif_extractor";

					File carpeta = new File(directorio);

					if (!carpeta.exists()) {

						Metodos.crearFichero(directorio, "", true);

					}

					if (Metodos.listarFicherosPorCarpeta(carpeta, ".") == 0) {

						Metodos.mensaje("Tienes que tener al menos 1 gif en la carpeta", 3);

						Metodos.abrirCarpeta(lectura[0] + "Gif_extractor");

					}

					else {

						Runtime.getRuntime().exec("python gif_frames.py");

						task.cancel();

						task = new CronWebp();

						timer = new Timer();

						timer.schedule(task, 0, 1000);

					}

				}

				catch (Exception e1) {

				}

			}

		});

		mntmNewMenuItem_30 = new JMenuItem("Create GIF GUI ");

		mntmNewMenuItem_30.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				try {

					Runtime.getRuntime().exec("java -jar EASY_GIF_CREATOR.jar");

				}

				catch (Exception e1) {
					//
				}

			}

		});

		mntmNewMenuItem_30.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video_2_frame.png")));
		mntmNewMenuItem_30.setFont(new Font("Dialog", Font.PLAIN, 20));
		menu1.add(mntmNewMenuItem_30);

		separator_33 = new JSeparator();
		menu1.add(separator_33);

		menuItem1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/GIF_Extract.png")));
		menuItem1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menu1.add(menuItem1);

		separator5 = new JSeparator();
		menu1.add(separator5);

		menuItem7 = new JMenuItem("Recortar imágenes");
		menu1.add(menuItem7);

		menuItem7.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				recortarFotos();
			}

		});

		menuItem7.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/crop.png")));

		menuItem7.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JSeparator separator_1 = new JSeparator();

		menu1.add(separator_1);

		mntmNewMenuItem_25 = new JMenuItem("BN 2 Color");

		mntmNewMenuItem_25.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				try {

					Metodos.mensaje("Espere mientras se colorizan las imagenes", 2);

					Metodos.conversion("jpeg", "jpg", carpetaBn);

					Metodos.conversion("JPEG", "jpg", carpetaBn);

					Metodos.conversion("JPG", "jpg", carpetaBn);

					Metodos.conversion("webp", "png", carpetaBn);

					Metodos.conversion("png", "jpg", carpetaBn);

					Metodos.conversion("PNG", "jpg", carpetaBn);

					Runtime.getRuntime().exec("python3 colorize.py");

					task.cancel();

					task = new CronWebp();

					timer = new Timer();

					timer.schedule(task, 0, 1000);

				}

				catch (Exception e1) {

					Metodos.mensaje("Error al colorizar", 1);

				}

			}

		});

		mntmNewMenuItem_25.setFont(new Font("Dialog", Font.PLAIN, 20));
		mntmNewMenuItem_25.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/30-07-2018 1-07-31.png")));
		menu1.add(mntmNewMenuItem_25);

		separator_27 = new JSeparator();
		menu1.add(separator_27);

		mntmNewMenuItem_27 = new JMenuItem("Image 2 TXT");
		mntmNewMenuItem_27.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				new Img2Txt().setVisible(true);

			}

		});
		mntmNewMenuItem_27.setFont(new Font("Dialog", Font.PLAIN, 20));
		mntmNewMenuItem_27.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/crop.png")));
		menu1.add(mntmNewMenuItem_27);

		separator_29 = new JSeparator();
		menu1.add(separator_29);

		mntmNewMenuItem = new JMenuItem("Buscar imagen");
		menu1.add(mntmNewMenuItem);
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				buscarFotos();
			}

		});

		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		mntmNewMenuItem.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/lupa.png")));

		separator22 = new JSeparator();

		menu1.add(separator22);

		mntmNewMenuItem1 = new JMenuItem("Descargar imágenes");

		mntmNewMenuItem1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/download.png")));

		mntmNewMenuItem1.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent arg0) {

				try {
					new Descarga().setVisible(true);
				}

				catch (IOException e) {
					//
				}

			}

		});

		mntmNewMenuItem1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menu1.add(mntmNewMenuItem1);

		separator_17 = new JSeparator();
		menu1.add(separator_17);

		mmenu2 = new JMenuItem("Comprobador SHA");
		menu1.add(mmenu2);

		mmenu2.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {

				try {

					ComprobarSha.setTipo(0);

					new ComprobarSha().setVisible(true);
				}

				catch (IOException e) {
					//
				}

			}

		});

		mmenu2.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		mmenu2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/db.png")));

		separator_22 = new JSeparator();

		menu1.add(separator_22);

		JMenuItem mntmNewMenuItem_31 = new JMenuItem("Notas SHA");

		mntmNewMenuItem_31.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				new NoteSha().setVisible(true);
			}

		});

		mntmNewMenuItem_31.setFont(new Font("Dialog", Font.PLAIN, 20));

		mntmNewMenuItem_31.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/db.png")));

		menu1.add(mntmNewMenuItem_31);

		separator1 = new JSeparator();

		mnAcciones.add(separator1);

		menu2 = new JMenu("Video");

		menu2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video_2_frame.png")));

		menu2.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		mnAcciones.add(menu2);

		menuItem2 = new JMenuItem("Video 2 Frame");

		menuItem2.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				video2Frame();
			}

		});

		menuItem2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video2frames.png")));
		menuItem2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menu2.add(menuItem2);

		separator2 = new JSeparator();
		menu2.add(separator2);

		mnNewMenu_8 = new JMenu("Video 2 GIF (GUI)");
		mnNewMenu_8.setFont(new Font("Dialog", Font.PLAIN, 20));
		mnNewMenu_8.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video_2_frame.png")));
		menu2.add(mnNewMenu_8);

		menuItem3 = new JMenuItem("Windows");
		mnNewMenu_8.add(menuItem3);
		menuItem3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {

					Runtime.getRuntime().exec("java -jar Video_TO_GIF.jar");

				}

				catch (IOException e1) {
					//
				}

			}

		});

		menuItem3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video_2_frame.png")));

		menuItem3.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JSeparator separator_30 = new JSeparator();

		mnNewMenu_8.add(separator_30);

		JMenuItem mntmNewMenuItem_28 = new JMenuItem("Linux");

		mntmNewMenuItem_28.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				try {

					Runtime.getRuntime().exec("java -jar Video_GIF.jar");

				} catch (Exception e1) {
					//
				}

			}

		});

		mntmNewMenuItem_28.setFont(new Font("Dialog", Font.PLAIN, 20));
		mntmNewMenuItem_28.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video_2_frame.png")));
		mnNewMenu_8.add(mntmNewMenuItem_28);

		menuBar = new JMenuBar();
		mnNewMenu_8.add(menuBar);

		mntmNewMenuItem_23 = new JMenuItem("Video 2 GIF");
		mntmNewMenuItem_23.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {

					Metodos.conversion("webm", "mp4", lectura[0] + "Video_2_Gif" + separador);

					Metodos.mensaje("Espere mientras se hacen los gifs", 2);

					Runtime.getRuntime().exec("python video_gif.py");

					task.cancel();

					task = new CronWebp();

					timer = new Timer();

					timer.schedule(task, 0, 1000);

				}

				catch (IOException e1) {
					//
				}

			}

		});

		separator_24 = new JSeparator();
		menu2.add(separator_24);
		mntmNewMenuItem_23.setFont(new Font("Dialog", Font.PLAIN, 20));
		mntmNewMenuItem_23.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video_2_gif.gif")));
		menu2.add(mntmNewMenuItem_23);

		separator3 = new JSeparator();
		mnAcciones.add(separator3);

		menu3 = new JMenu("Base de datos   ");
		menu3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/db.png")));
		menu3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnAcciones.add(menu3);

		menuItem4 = new JMenuItem("Backup");
		menuItem4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				new BackupBd().setVisible(true);
			}
		});

		mnNewMenu_5 = new JMenu("Utilidades");
		mnNewMenu_5.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnNewMenu_5.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/utilities.png")));
		menu3.add(mnNewMenu_5);

		mntmNewMenuItem_9 = new JMenuItem("Eliminar Imágenes");
		mntmNewMenuItem_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new EliminarImagen().setVisible(true);

			}
		});

		mntmNewMenuItem_9.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/db.png")));
		mntmNewMenuItem_9.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnNewMenu_5.add(mntmNewMenuItem_9);

		separator_15 = new JSeparator();
		mnNewMenu_5.add(separator_15);

		menuItem6 = new JMenuItem("Recomponer Inserts");
		mnNewMenu_5.add(menuItem6);
		menuItem6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {

					if (Metodos.comprobarConfiguracion()) {
						new Utilidades().setVisible(true);

					}

					else {
						new Bd().setVisible(true);
					}

				}

				catch (Exception e1) {
					//
				}
			}
		});
		menuItem6.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/actualizar.png")));
		menuItem6.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JSeparator separator_13 = new JSeparator();
		mnNewMenu_5.add(separator_13);

		mntmNewMenuItem_10 = new JMenuItem("Cambiador de categoría");
		mntmNewMenuItem_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new CambiarCategoria().setVisible(true);
			}
		});
		mntmNewMenuItem_10.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/tag.png")));
		mntmNewMenuItem_10.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnNewMenu_5.add(mntmNewMenuItem_10);

		separator_18 = new JSeparator();
		mnNewMenu_5.add(separator_18);

		separator6 = new JSeparator();
		menu3.add(separator6);

		menuItem4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		menuItem4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menu3.add(menuItem4);

		JSeparator miSeparator3 = new JSeparator();
		menu3.add(miSeparator3);

		mntmNewMenuItem_13 = new JMenuItem("Ejecutar sql");
		mntmNewMenuItem_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new ImportarSql().setVisible(true);
			}
		});
		mntmNewMenuItem_13.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmNewMenuItem_13.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/db.png")));
		menu3.add(mntmNewMenuItem_13);

		JSeparator separator_34 = new JSeparator();
		menu3.add(separator_34);

		mntmNewMenuItem_14 = new JMenuItem("Modificar datos");
		menu3.add(mntmNewMenuItem_14);
		mntmNewMenuItem_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {

					ComprobarSha.setTipo(1);

					new ComprobarSha().setVisible(true);
				}

				catch (IOException e1) {
					//
				}

			}
		});
		mntmNewMenuItem_14.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/db.png")));
		mntmNewMenuItem_14.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		separator4 = new JSeparator();
		mnAcciones.add(separator4);

		mnNewMenu_3 = new JMenu("Ver");
		mnNewMenu_3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnNewMenu_3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/view.png")));
		mnAcciones.add(mnNewMenu_3);

		menuItem5 = new JMenuItem("Notas");
		mnNewMenu_3.add(menuItem5);
		menuItem5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				abrirNotas();
			}
		});

		menuItem5.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/name.png")));
		menuItem5.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		miSeparator9 = new JSeparator();
		mnNewMenu_3.add(miSeparator9);

		mntmComentarios = new JMenuItem("Comentarios");
		mnNewMenu_3.add(mntmComentarios);
		mntmComentarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					new Comentarios().setVisible(true);
				}

				catch (Exception e1) {
					//
				}
			}
		});
		mntmComentarios.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmComentarios.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/nota.png")));

		mntmNewMenuItem_11 = new JMenuItem("Estadísticas");
		mntmNewMenuItem_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				new Estadistica().setVisible(true);

			}
		});

		separator_19 = new JSeparator();
		mnNewMenu_3.add(separator_19);

		mntmNewMenuItem_29 = new JMenuItem("URLs");

		mntmNewMenuItem_29.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				try {
					new Urls().setVisible(true);
				}

				catch (Exception e1) {

				}

			}

		});

		mntmNewMenuItem_29.setFont(new Font("Dialog", Font.PLAIN, 20));
		mntmNewMenuItem_29.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/remote.png")));
		mnNewMenu_3.add(mntmNewMenuItem_29);

		separator_32 = new JSeparator();
		mnNewMenu_3.add(separator_32);
		mnNewMenu_3.add(mntmNewMenuItem_11);
		mntmNewMenuItem_11.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmNewMenuItem_11.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/db.png")));

		separator_14 = new JSeparator();
		mnNewMenu_3.add(separator_14);

		mntmNewMenuItem_8 = new JMenuItem("Descargas");
		mnNewMenu_3.add(mntmNewMenuItem_8);
		mntmNewMenuItem_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new DescargasScrapting().setVisible(true);
			}
		});

		mntmNewMenuItem_8.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		mntmNewMenuItem_8.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/download.png")));

		mntmNewMenuItem_19 = new JMenuItem("Metadatos");

		mntmNewMenuItem_19.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					new Metadato().setVisible(true);
				}

				catch (Exception e1) {
					//
				}

			}
		});

		separator_31 = new JSeparator();
		mnNewMenu_3.add(separator_31);

		mntmNewMenuItem_19.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		mnNewMenu_3.add(mntmNewMenuItem_19);

		separator_12 = new JSeparator();

		mnNewMenu_3.add(separator_12);

		mmenu5 = new JMenuItem("CMS");

		mnNewMenu_3.add(mmenu5);

		mmenu5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {

					lecturaurl = Metodos.leerFicheroArray("Config2.txt", 2);

					obtenerCarpeta();

					String url = "http://" + lecturaurl[0] + carpeta;

					if (Metodos.pingURL(url)) {
						Metodos.abrirCarpeta(url);
					}

					else {
						Metodos.mensaje("Por favor, comprueba la configuración", 3);
						new Config2().setVisible(true);
					}

				}

				catch (Exception e1) {
					//
				}

			}

		});

		mmenu5.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/remote.png")));
		mmenu5.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		separator_9 = new JSeparator();
		mnAcciones.add(separator_9);

		mntmNewMenuItem_6 = new JMenuItem("Scrapt");

		mntmNewMenuItem_6.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				try {
					new Scrapt().setVisible(true);
				}

				catch (IOException e1) {
					//
				}
			}

		});

		mnNewMenu_4 = new JMenu("Administrar");
		mnNewMenu_4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnNewMenu_4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/user_pass.png")));
		mnAcciones.add(mnNewMenu_4);

		mntmNewMenuItem_3 = new JMenuItem("Categorías");
		mnNewMenu_4.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					new Categorias().setVisible(true);
				}

				catch (Exception e1) {
					//
				}

			}
		});
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmNewMenuItem_3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/tag.png")));

		separator_16 = new JSeparator();
		mnNewMenu_4.add(separator_16);

		mntmNewMenuItem_12 = new JMenuItem("Grupos");
		mntmNewMenuItem_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new AdminGrupos().setVisible(true);
			}

		});

		mntmNewMenuItem_15 = new JMenuItem("Usuario");

		mnNewMenu_4.add(mntmNewMenuItem_15);

		mntmNewMenuItem_15.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				try {
					new VerUser().setVisible(true);
				}

				catch (Exception e1) {
					//
				}
			}
		});

		mntmNewMenuItem_15.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/user.png")));

		mntmNewMenuItem_15.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		separator_8 = new JSeparator();

		mnNewMenu_4.add(separator_8);

		mntmNewMenuItem_12.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/db.png")));

		mntmNewMenuItem_12.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		mnNewMenu_4.add(mntmNewMenuItem_12);

		separator_6 = new JSeparator();

		mnNewMenu_4.add(separator_6);

		mntmNewMenuItem_5 = new JMenuItem("Bots");

		mnNewMenu_4.add(mntmNewMenuItem_5);

		mntmNewMenuItem_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					new Bots().setVisible(true);
				}

				catch (IOException e1) {
					//
				}
			}
		});

		mntmNewMenuItem_5.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bot.png")));

		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		separator_10 = new JSeparator();

		mnAcciones.add(separator_10);

		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		mntmNewMenuItem_6.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/remote.png")));

		mnAcciones.add(mntmNewMenuItem_6);

		separator_23 = new JSeparator();

		mnAcciones.add(separator_23);

		mnNewMenu_7 = new JMenu("Utilidades");

		mnNewMenu_7.setFont(new Font("Dialog", Font.PLAIN, 20));

		mnNewMenu_7.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/fix.png")));

		mnAcciones.add(mnNewMenu_7);

		mntmNewMenuItem_20 = new JMenuItem("Crear carpetas");

		mntmNewMenuItem_20.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));

		mnNewMenu_7.add(mntmNewMenuItem_20);

		mntmNewMenuItem_20.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		separator_35 = new JSeparator();

		mnNewMenu_7.add(separator_35);

		mntmNewMenuItem_32 = new JMenuItem("Exif Tool");

		mntmNewMenuItem_32.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				try {
					Runtime.getRuntime().exec("java -jar jExifToolGUI.jar");
				}

				catch (Exception e1) {

				}

			}

		});

		mnNewMenu_7.add(mntmNewMenuItem_32);

		mntmNewMenuItem_32.setFont(new Font("Dialog", Font.PLAIN, 20));

		mntmNewMenuItem_32.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/fix.png")));

		separator_25 = new JSeparator();

		mnNewMenu_7.add(separator_25);

		mntmNewMenuItem_33 = new JMenuItem("Image Resizer");

		mntmNewMenuItem_33.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				try {
					Runtime.getRuntime().exec("java -jar Image_Resizer.jar");
				}

				catch (Exception e1) {

				}

			}

		});

		mnNewMenu_7.add(mntmNewMenuItem_33);

		separator_36 = new JSeparator();

		mnNewMenu_7.add(separator_36);

		mntmNewMenuItem_21 = new JMenuItem("Descomprimir zip");

		mntmNewMenuItem_21.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		mnNewMenu_7.add(mntmNewMenuItem_21);

		separator_26 = new JSeparator();

		mnNewMenu_7.add(separator_26);

		mntmNewMenuItem_22 = new JMenuItem("Actualizar");

		mntmNewMenuItem_22.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				try {
					actualizar();
				}

				catch (IOException e1) {

					// ;
				}

			}

		});

		mntmNewMenuItem_22.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/actualizar.png")));

		mnNewMenu_7.add(mntmNewMenuItem_22);

		mntmNewMenuItem_20.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				try {

					ArrayList<String> categorias = Metodos.verCategorias();

					for (int i = 0; i < categorias.size(); i++) {

						File directorio = new File(directorioImagenes + separador + categorias.get(i));

						directorio.mkdir();
					}

				} catch (Exception e1) {
					//
				}

			}
		});

		menu4 = new JMenu("Abrir");

		menu4.setForeground(Color.BLACK);

		menu4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));

		menu4.setFont(new Font("Segoe UI", Font.PLAIN, 24));

		menu4.setBackground(Color.BLACK);

		menuopciones.add(menu4);

		mnNewMenu = new JMenu("Imagen  ");

		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		mnNewMenu.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));

		menu4.add(mnNewMenu);

		menuItem8 = new JMenuItem("Imagenes");

		mnNewMenu.add(menuItem8);

		menuItem8.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				try {
					Metodos.abrirCarpeta(directorioImagenes);
				}

				catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}

			}
		});

		menuItem8.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));

		menuItem8.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		separator9 = new JSeparator();

		mnNewMenu.add(separator9);

		mntmNewMenuItem_26 = new JMenuItem("BN 2 Color");

		mntmNewMenuItem_26.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				try {
					Metodos.abrirCarpeta(carpetaBn);
				}

				catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}

			}

		});

		mntmNewMenuItem_26.setFont(new Font("Dialog", Font.PLAIN, 20));
		mntmNewMenuItem_26.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/30-07-2018 1-07-31.png")));
		mnNewMenu.add(mntmNewMenuItem_26);

		JSeparator separator_28 = new JSeparator();
		mnNewMenu.add(separator_28);

		menuItem9 = new JMenuItem("GIF Animator");

		mnNewMenu.add(menuItem9);

		menuItem9.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				abrirCarpetaGif();

			}

		});

		menuItem9.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gif.png")));

		menuItem9.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		separator7 = new JSeparator();

		mnNewMenu.add(separator7);

		menuItem10 = new JMenuItem("GIF Extractor");

		mnNewMenu.add(menuItem10);

		menuItem10.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				try {
					Metodos.abrirCarpeta(lectura[0] + "Gif_extractor");
				}

				catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});

		menuItem10.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/GIF_Extract.png")));
		menuItem10.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		separator11 = new JSeparator();
		mnNewMenu.add(separator11);

		menuItem13 = new JMenuItem("Crop images");
		mnNewMenu.add(menuItem13);
		menuItem13.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					Metodos.abrirCarpeta("Config" + separador + "imagenes_para_recortar");
				}

				catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});

		menuItem13.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/crop.png")));
		menuItem13.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		mntmDownloads = new JMenuItem("Descargas");
		mntmDownloads.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/download.png")));
		mntmDownloads.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				try {
					Metodos.abrirCarpeta("Config" + separador + "Downloads");
				}

				catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});

		JSeparator separador6 = new JSeparator();
		mnNewMenu.add(separador6);
		mntmDownloads.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnNewMenu.add(mntmDownloads);

		separator8 = new JSeparator();
		menu4.add(separator8);

		mnNewMenu1 = new JMenu("Video");
		mnNewMenu1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnNewMenu1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video_2_frame.png")));
		menu4.add(mnNewMenu1);

		menuItem11 = new JMenuItem("Video 2 Frame");
		mnNewMenu1.add(menuItem11);
		menuItem11.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				video2Frames();
			}

		});
		menuItem11.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video2frames.png")));
		menuItem11.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		separator10 = new JSeparator();
		mnNewMenu1.add(separator10);

		mntmNewMenuItem_24 = new JMenuItem("Video 2 GIF");

		mntmNewMenuItem_24.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				try {

					String carpeta = Metodos.formatearRuta();

					carpeta += separador + "Video_2_Gif";

					Metodos.comprobarConexion("Config/Config.txt", carpeta);

				} catch (IOException e1) {
					//
				}

			}

		});

		mntmNewMenuItem_24.setFont(new Font("Dialog", Font.PLAIN, 20));
		mntmNewMenuItem_24.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video_2_gif.gif")));
		mnNewMenu1.add(mntmNewMenuItem_24);

		mnNewMenu_6 = new JMenu("Ver");
		mnNewMenu_6.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_6.setForeground(Color.BLACK);
		mnNewMenu_6.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/view.png")));
		menuopciones.add(mnNewMenu_6);

		mntmNewMenuItem_17 = new JMenuItem("Usuarios");
		mnNewMenu_6.add(mntmNewMenuItem_17);

		separator_21 = new JSeparator();
		mnNewMenu_6.add(separator_21);

		mntmNewMenuItem_18 = new JMenuItem("Estadísticas");
		mntmNewMenuItem_18.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new Estadistica().setVisible(true);
			}
		});
		mntmNewMenuItem_18.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/view.png")));
		mnNewMenu_6.add(mntmNewMenuItem_18);

		mnConfig = new JMenu("Config  ");
		mnConfig.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/config.png")));
		mnConfig.setForeground(Color.BLACK);
		mnConfig.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnConfig.setBackground(Color.BLACK);
		menuopciones.add(mnConfig);

		mnNewMenu_1 = new JMenu("Conexiones");
		mnNewMenu_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/remote.png")));
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnConfig.add(mnNewMenu_1);

		menuItem14 = new JMenuItem("Local");
		mnNewMenu_1.add(menuItem14);
		menuItem14.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					new Config().setVisible(true);
				}

				catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});

		menuItem14.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/port.png")));
		menuItem14.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		separator13 = new JSeparator();
		mnNewMenu_1.add(separator13);

		menuItem15 = new JMenuItem("Remoto");
		mnNewMenu_1.add(menuItem15);
		menuItem15.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					new Config2().setVisible(true);
				}

				catch (IOException e1) {
					Metodos.mensaje("No se ha podidio abrir la configuración remota", 1);
				}
			}
		});

		menuItem15.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/port.png")));
		menuItem15.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		separator14 = new JSeparator();
		mnNewMenu_1.add(separator14);

		mmenu1 = new JMenuItem("Usuario - CMS");
		mnNewMenu_1.add(mmenu1);
		mmenu1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				try {
					new User().setVisible(true);
				}

				catch (IOException e) {
					//
				}
			}
		});

		mmenu1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/user.png")));
		mmenu1.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JSeparator separador2 = new JSeparator();
		mnNewMenu_1.add(separador2);

		menuItem16 = new JMenuItem("BD");
		mnNewMenu_1.add(menuItem16);
		menuItem16.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					new Bd().setVisible(true);
				}

				catch (IOException e1) {
					//
				}
			}
		});

		menuItem16.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		menuItem16.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		separator12 = new JSeparator();
		mnConfig.add(separator12);

		mnNewMenu_2 = new JMenu("Subida de imagenes");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnNewMenu_2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/utilities.png")));
		mnConfig.add(mnNewMenu_2);

		mntmNewMenuItem_1 = new JMenuItem("Valores por defecto");
		mntmNewMenuItem_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/config.png")));
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					new Configuracion().setVisible(true);
				}

				catch (IOException e1) {
					//
				}

			}
		});

		mnNewMenu_2.add(mntmNewMenuItem_1);

		JSeparator separator_7 = new JSeparator();

		mnNewMenu_2.add(separator_7);

		mntmNewMenuItem_7 = new JMenuItem("Permisos");

		mntmNewMenuItem_7.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				try {
					new Permiso().setVisible(true);
				}

				catch (IOException e1) {
					//
				}

			}

		});

		mntmNewMenuItem_7.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/key.png")));
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnNewMenu_2.add(mntmNewMenuItem_7);

		separator_2 = new JSeparator();
		mnConfig.add(separator_2);

		mntmNewMenuItem_16 = new JMenuItem("Descarga");
		mntmNewMenuItem_16.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmNewMenuItem_16.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/download.png")));
		mnConfig.add(mntmNewMenuItem_16);

		JSeparator separator_20 = new JSeparator();
		mnConfig.add(separator_20);

		menuItem17 = new JMenuItem("Backup BD");
		mnConfig.add(menuItem17);
		menuItem17.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					new Backup().setVisible(true);
				}

				catch (IOException e1) {
					Metodos.mensaje("Error al cargar la utilidad", 1);
				}
			}
		});

		menuItem17.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		menuItem17.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JSeparator separator_11 = new JSeparator();
		mnConfig.add(separator_11);

		mntmNewMenuItem_4 = new JMenuItem("Sonido");
		mnConfig.add(mntmNewMenuItem_4);
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmNewMenuItem_4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/wav.png")));
		mntmNewMenuItem_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new ConfiguracionSonido().setVisible(true);
				} catch (IOException e1) {
					//
				}
			}
		});

		menu7 = new JMenu("Ayuda");
		menu7.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/help.png")));
		menu7.setForeground(Color.BLACK);
		menu7.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menu7.setBackground(Color.BLACK);
		menuopciones.add(menu7);

		mmenu3 = new JMenuItem("DB Fix");
		mmenu3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					new DBFix().setVisible(true);
				}

				catch (IOException e1) {
					//
				}
			}
		});
		mmenu3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mmenu3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		menu7.add(mmenu3);

		JSeparator separator_4 = new JSeparator();
		menu7.add(separator_4);

		mntmNewMenuItem_2 = new JMenuItem("HotKeyz");
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new Hotkeyz().setVisible(true);
			}
		});
		menu7.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/config.png")));
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		separator_3 = new JSeparator();
		menu7.add(separator_3);

		menuItem18 = new JMenuItem("IMG 2 Color");
		menuItem18.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					Metodos.abrirCarpeta("https://colourise.sg");
				}

				catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});
		menuItem18.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/30-07-2018 1-07-31.png")));
		menuItem18.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menu7.add(menuItem18);

		menuItem19 = new JMenuItem("Sobre");
		menuItem19.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new About().setVisible(true);
			}
		});

		JSeparator separator_5 = new JSeparator();
		menu7.add(separator_5);
		menuItem19.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/about.png")));
		menuItem19.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menu7.add(menuItem19);

		Metodos.comprobarArchivo("Config", false);

		Metodos.crearCarpetas();

		obtenerCarpeta();

		initComponents();

		Metodos.crearCarpetasConfig();

		comboBox_1.setBackground(Color.WHITE);

		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		comboBox_1.addItem("Imagenes");

		comboBox_1.addItem("Hacer GIF");

		comboBox_1.addItem("GIF Extractor");

		comboBox_1.addItem("Recortar imagenes");

		comboBox_1.addItem("Video a frames");

		comboBox_1.addItem("Video A GIF");

		comboBox_1.addItem("Comprobador SHA");

		this.setVisible(true);
	}

	static void obtenerCarpeta() throws IOException {

		try {

			if (!lecturaurl[1].isEmpty()) {
				carpeta = "/" + lecturaurl[1];
			}

		}

		catch (NullPointerException e) {
			new Config2().setVisible(true);
		}

	}

	public static String getDirectorioActual() {
		return directorioActual;
	}

	private static void comprobarUsuario(String directorioImagenes) throws IOException {

		try {

			if (user[0] == null && user[1] == null || (user[0].isEmpty() || user[1].isEmpty())) {

				Metodos.mensaje("Por favor, introduce tu usuario y contraseña del CMS", 2);

				new User().setVisible(true);

			}

			else {

				if (configuracion[0].isEmpty() && nombreImagenes.isEmpty()) {
					Metodos.mensaje("Por favor, rellene el nombre", 3);
				}

				else {

					Metodos.abrirCarpeta(directorioImagenes);

				}
			}
		}

		catch (Exception e1) {

			Metodos.mensaje("Por favor, introduce tu usuario y contraseña del CMS", 2);

			new User().setVisible(true);

		}
	}

	private static int subirArchivos(Statement s, int categoria, JSONArray imagenesBD, String extension, boolean subir,
			boolean cerrarNavegador) throws SQLException, Exception {

		int retorno = 0;

		try {

			claves.clear();

			int maximo = Metodos.saberMaximo("images", "image_id");

			int maximoMeta = Metodos.saberMaximo("metadatos", "Id");

			if (!listaImagenes.isEmpty()) {

				retorno = subirImagenes(maximo, s, categoria, imagenesBD, extension, maximoMeta, subir);
			}

			ResultSet rs;

			int idImagen;

			for (int x = 0; x < claves.size(); x++) {

				rs = s.executeQuery("SELECT image_id FROM " + lecturabd[3] + "images" + " WHERE image_media_file='"
						+ imagenesMeta.get(x) + "'");

				rs.next();

				idImagen = Integer.parseInt(rs.getString("image_id"));

				s.executeUpdate("INSERT INTO " + lecturabd[3] + "metadatos (Imagen,Tipo,Valor) VALUES('" + idImagen
						+ "','" + claves.get(x) + "','" + valores.get(x) + "')");
			}

		} catch (Exception e) {

		}

		return retorno;

	}

	private static void subirFotos(int categoria, String web) {

		try {

			Metodos.cerrarNavegador();

			cat = categoria;

			pagina = web;

			Metodos.convertir(directorioImagenes + separador);

			listaImagenes.clear();

			if (subirSoloGif) {
				listaImagenes = Metodos.directorio(directorioImagenes + separador, "gif", true, false);
			}

			else {
				listaImagenes = Metodos.directorio(directorioImagenes + separador, ".", true, false);
			}

			if (comboBox.getSelectedIndex() == -1) {

				Metodos.mensaje("Comprueba que tengas al menos una categoría en la base de datos", 3);

				new Bd().setVisible(true);
			}

			else {

				if (!textField.getText().trim().isEmpty()) {

					if (listaImagenes.isEmpty() || user.length != 2) {

						if (user.length != 2) {

							new User().setVisible(true);

						}

						if (listaImagenes.isEmpty()) {

							Metodos.abrirCarpeta(directorioImagenes);

						}

					}

					else {

						String parametros = "";

						if (user[0] != null && user[1] != null && !user[0].isEmpty() && !user[1].isEmpty()) {

							parametros = Metodos.obtenerParametros(listaImagenes);

							if (!parametros.isEmpty()) {

								JSONObject json;

								try {

									ResultSet rs = null;

									Statement s;

									Connection conexion = Metodos.conexionBD();

									s = conexion.createStatement();

									json = Metodos.apiImagenes(parametros);

									JSONArray imagenesBD = json.getJSONArray("imagenes_bd");

									if (!soloGif.isSelected()) {
										subirImagenes(categoria, imagenesBD, s, true, true);
									}

									else {

										subirImagenCms(categoria, imagenesBD, s, "gif", true, true);

									}

									if (rs != null) {
										rs.close();

									}

									s.close();

									conexion.close();

									moverListaImagenes(categoria, web, imagenesSubidas);

									if (configuracion[4].equals("1")) {

										for (int i = 0; i < imagenesSubidas.size(); i++) {

											Metodos.eliminarFichero(imagenesSubidas.get(i));
										}

									}

									if (configuracion[5].equals("1")) {

										try {
											Metodos.abrirCarpeta("http://" + lecturaurl[0] + carpeta);
										}

										catch (IOException e1) {
											Metodos.mensaje("Error", 1);
										}

									}

								}

								catch (SQLException e1) {

									Metodos.mensaje(
											"La tabla de imágenes no tiene la estructura para ejecutar correctamente esta acción",
											1);
								}

								catch (Exception e1) {
									//
								}

							}
						}

						else {

							new User().setVisible(true);

						}
					}
				}

				else {

					comprobarUsuario(directorioImagenes);
				}

			}

		}

		catch (Exception e4) {

			try {

				new Config2().setVisible(true);
			}

			catch (IOException e1) {
				//
			}

		}
	}

	private static void subirImagenes(int categoria, JSONArray imagenesBD, Statement s, boolean subir,
			boolean cerrarNavegador) throws SQLException, Exception {

		Metodos.cerrarNavegador();

		subirImagenCms(categoria, imagenesBD, s, "jpg", subir, cerrarNavegador);

		subirImagenCms(categoria, imagenesBD, s, "png", subir, cerrarNavegador);

		subirImagenCms(categoria, imagenesBD, s, "gif", subir, cerrarNavegador);

	}

	private static void subirImagenCms(int categoria, JSONArray imagenesBD, Statement s, String extension,
			boolean subir, boolean cerrarNavegador) throws SQLException, Exception {

		Metodos.cerrarNavegador();

		listaImagenes = Metodos.directorio(directorioImagenes + separador, extension, true, false);

		if (listaImagenes.size() > 0) {
			subirArchivos(s, categoria, imagenesBD, extension, subir, cerrarNavegador);
		}

	}

	private static int subirImagenes(int maximo, Statement s, int categoria, JSONArray imagenesBD, String extension,
			int maximoMeta, boolean subir) throws SQLException, IOException {

		try {

			String descripcion = "";

			// Poner descripción en imágenes

			configuracion[2] = Metodos.eliminarEspacios(configuracion[2], false);

			if (!configuracion[2].isEmpty()) {
				descripcion = configuracion[2];
			}

			String imagen;

			Connection conexionSubida = Metodos.conexionBD();

			ResultSet rs = null;

			int comprobarSha = 0;

			String imagenbd = "";

			total = listaImagenes.size();

			File file;

			String rutaServer = "http://" + lecturaurl[0] + carpeta;

			if (total > 0) {

				Metodos.cerrarNavegador();

				chrome = new FirefoxDriver();

				chrome.get(rutaServer + "/index.php");

				chrome.findElement(By.id("user_name")).sendKeys(user[0]);

				chrome.findElement(By.id("user_password")).sendKeys(user[1]);

				chrome.findElement(By.id("login")).click();

				for (int i = 0; i < total; i++) {

					imagen = directorioImagenes + separador + listaImagenes.get(i);

					file = new File(imagen);

					if (file.exists()) {

						rs = s.executeQuery("SELECT COUNT(sha256) FROM " + lecturabd[3] + "images" + " WHERE sha256='"
								+ Metodos.getSHA256Checksum(imagen) + "'");

						rs.next();

						comprobarSha = Integer.parseInt(rs.getString("COUNT(sha256)"));

						if (comprobarSha == 0) {

							resultadoSubida();

							imagenbd = imagenesBD.get(i).toString().substring(0,
									imagenesBD.get(i).toString().length() - 3) + extension;

							File f1 = new File(imagen);

							File f2 = new File(directorioImagenes + separador + imagenbd);

							f1.renameTo(f2);

							imagen = directorioImagenes + separador + imagenbd;

							lecturaurl[1] = lecturaurl[1].trim();

							lecturaurl[1] = lecturaurl[1].replace("  ", " ");

							try {

								InputStream inputstream = new FileInputStream(imagen);

								Metadata metadata = ImageMetadataReader.readMetadata(inputstream);

								String etiqueta = "";

								for (Directory directory : metadata.getDirectories()) {

									for (Tag tag : directory.getTags()) {

										etiqueta = tag.toString();

										verValor(etiqueta, "[IPTC] Special Instructions - ", imagenbd);

										verValor(etiqueta, "[IPTC] By-line - ", imagenbd);

										verValor(etiqueta, "[IPTC] By-line Title - ", imagenbd);

										verValor(etiqueta, "[IPTC] Headline - ", imagenbd);

										verValor(etiqueta, "[IPTC] Credit - ", imagenbd);

										verValor(etiqueta, "[IPTC] Source - ", imagenbd);

										verValor(etiqueta, "[IPTC] Copyright Notice - ", imagenbd);

										verValor(etiqueta, "[IPTC] Caption/Abstract - ", imagenbd);

										verValor(etiqueta, "[IPTC] Caption Writer/Editor - ", imagenbd);

										verValor(etiqueta, "[JpegComment] JPEG Comment - ", imagenbd);

										verValor(etiqueta, "[PNG-tEXt] Textual Data - Comment: ", imagenbd);

									}
								}

								// Comprobar si la categoria admite gif

								if (subir) {

									chrome.get(rutaServer + "/upload_images/input.php?cat_id=" + categoria + "&nombre="
											+ nombreImagenes + "&periquito=si");

									chrome.findElement(By.id("file")).sendKeys(imagen);

									imagenesSubidas
											.add(imagen.substring(imagen.lastIndexOf(separador) + 1, imagen.length()));
								}

								maximo++;

								y++;

							}

							catch (Exception e) {

								progreso.dispose();

								i = total;

							}
						}

					}

				}

				chrome.close();

				Metodos.cerrarNavegador();

			}

			conexionSubida.close();

			imagenesSubidas.clear();

			imagenesSubidas = Metodos.directorio(directorioImagenes + separador, ".", true, false);

			moverListaImagenes(cat, pagina, imagenesSubidas);

		}

		catch (Exception e) {

			y = 0;

			total = 1;

			return 0;

		}

		return maximo;

	}

	private static void verValor(String etiqueta, String busqueda, String imagen) {

		String dato;

		String clave = "";

		if (etiqueta.contains(busqueda)) {

			dato = calcularDato(etiqueta, busqueda);

			clave = busqueda.substring(0, busqueda.indexOf(" -"));

			if (!dato.isEmpty() && !claves.contains(clave)) {

				claves.add(clave);

				valores.add(dato);
			}

			if (!imagenesMeta.contains(imagen)) {
				imagenesMeta.add(imagen);
			}

		}

	}

	private static String calcularDato(String etiqueta, String indice) {

		int longitud = indice.length();

		return etiqueta.substring(etiqueta.indexOf(indice) + longitud, etiqueta.length()).trim();

	}

	public void initComponents() throws IOException {

		Metodos.crearCarpetas();

		if (Metodos.comprobarArchivo("Config/Config.txt", true)) {

			try {

				Metodos.guardarConfig(4, separador);
			}

			catch (IOException e1) {
				Metodos.mensaje("Error al guardar la configuración", 1);
			}

		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JLabel label2 = new JLabel();
		label2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/name.png")));

		textField = new JTextField();

		textField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				try {

					if (e.getKeyCode() == KeyEvent.VK_F5) {

						actualizar();
					}

					if (e.getKeyCode() == KeyEvent.VK_ENTER) {

						uploadImages();
					}

				}

				catch (Exception e1) {
					//
				}

			}

			@Override

			public void keyReleased(KeyEvent e) {

				if (tecla1 == 0) {
					tecla1 = e.getKeyCode();
				}

				if (tecla2 == 0 && vuelta == 1) {
					tecla2 = e.getKeyCode();
				}

				if (tecla1 == 112 || tecla2 == 112) {

					new Hotkeyz().setVisible(true);
				}

				if (tecla1 == 113 || tecla2 == 113) {

					try {
						new DBFix().setVisible(true);
					}

					catch (IOException e1) {
						//
					}

				}

				if (tecla1 == 70 && tecla2 == 17 || tecla1 == 17 && tecla2 == 70) {

					video2Frames();
				}

				if (tecla1 == 70 && tecla2 == 16 || tecla1 == 16 && tecla2 == 70) {

					video2Frame();
				}

				if (tecla1 == 71 && tecla2 == 17 || tecla1 == 17 && tecla2 == 71) {
					abrirCarpetaGif();
				}

				if (tecla1 == 71 && tecla2 == 16 || tecla1 == 16 && tecla2 == 71) {

					animGif();
				}

				if (tecla1 == 67 && tecla2 == 16 || tecla1 == 16 && tecla2 == 67) {

					try {
						new Comentarios().setVisible(true);
					}

					catch (Exception e1) {
						//
					}

				}

				if (tecla1 == 83 && tecla2 == 16 || tecla1 == 16 && tecla2 == 83) {

					comprobarSha();
				}

				if (tecla1 == 83 && tecla2 == 17 || tecla1 == 17 && tecla2 == 83) {

					buscarFotos();
				}

				if (tecla1 == 78 && tecla2 == 17 || tecla1 == 17 && tecla2 == 78) {

					abrirNotas();
				}

				if (tecla1 == 66 && tecla2 == 17 || tecla1 == 17 && tecla2 == 66) {

					new BackupBd().setVisible(true);
				}

				if (tecla1 == 82 && tecla2 == 17 || tecla1 == 17 && tecla2 == 82) {

					recortarFotos();
				}

				if (tecla1 == 73 && tecla2 == 17 || tecla1 == 17 && tecla2 == 73) {

					try {
						Metodos.abrirCarpeta(directorioImagenes);
					}

					catch (IOException e1) {
						//
					}
				}

				if (vuelta == 1) {
					tecla1 = 0;
					tecla2 = 0;
					vuelta = 0;
				}

				else {
					vuelta++;
				}
			}

		});

		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JLabel label3 = new JLabel();
		label3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/tag.png")));
		label3.setHorizontalAlignment(SwingConstants.CENTER);

		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				try {

					Metodos.crearFichero("Config/llamada_python.txt", "", false);

					uploadImages();

				}

				catch (Exception e1) {
					//
				}

			}

		});

		button.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/start.png")));
		button.setFont(new Font("Tahoma", Font.PLAIN, 6));

		JTextArea imagenes = new JTextArea();
		imagenes.setText("    Arrastra los archivos aqui");
		imagenes.setForeground(SystemColor.desktop);
		imagenes.setFont(new Font("Tahoma", Font.PLAIN, 26));
		imagenes.setEditable(false);
		imagenes.setBackground(SystemColor.menu);
		JLabel label4 = new JLabel("");

		label4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				File[] files = Metodos.seleccionar(2, "Imagen & Video",
						"Elije un archivo de imagen o video para mover");

				if (files != null) {

					try {

						if (comboBox_1.getSelectedIndex() == 6) {

							ComprobarSha.comprobarSha(files);

						}

						else {

							Metodos.moverArchivosDrop(files, separador, saberDondeSeMueve(), saberTipoDrop());

							if (comboBox_1.getSelectedIndex() == 3) {
								new PhotoFrame().setVisible(true);
							}

						}

					}

					catch (IOException e1) {
						Metodos.mensaje("Error al mover archivos de imagen o video", 1);
					}

				}
			}
		});

		label4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/import.png")));
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gifanim.png")));

		chckbxNewCheckBox = new JCheckBox("B / N ?");
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 20));

		soloGif = new JCheckBox(" Sólo GIF");
		soloGif.setBackground(Color.WHITE);
		soloGif.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_1 = new JLabel("Tiempo de subida aproximado:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_3 = new JLabel("Nombre común");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblCategora = new JLabel("Categoría");
		lblCategora.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCategora.setHorizontalAlignment(SwingConstants.CENTER);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(layout
										.createSequentialGroup().addGap(19).addComponent(label2)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup().addGap(21).addGroup(layout
										.createParallelGroup(Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 64,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(layout.createParallelGroup(Alignment.LEADING)
														.addComponent(soloGif).addComponent(rdbtnNewRadioButton)))
										.addGroup(layout.createSequentialGroup()
												.addComponent(label4, GroupLayout.PREFERRED_SIZE, 70,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(comboBox_1, 0, 187, Short.MAX_VALUE))))
								.addGroup(layout.createSequentialGroup().addContainerGap()
										.addComponent(label3, GroupLayout.PREFERRED_SIZE, 85,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblCategora, GroupLayout.PREFERRED_SIZE, 193,
												GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup().addComponent(lblNewLabel_1)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
								.addComponent(comboBox, 0, 440, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
								.addComponent(imagenes, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
										.addComponent(button, GroupLayout.PREFERRED_SIZE, 266,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxNewCheckBox,
												GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
										.addGap(16)))
						.addGap(42)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(26).addComponent(label2,
										GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
								.addGroup(
										layout.createSequentialGroup().addGap(45).addComponent(textField,
												GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(53).addComponent(lblNewLabel_3)))
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout
								.createSequentialGroup().addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(label3, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout
										.createSequentialGroup().addGap(36)
										.addGroup(layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblCategora))
										.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel_1).addComponent(lblNewLabel_2,
														GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGap(13)
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addComponent(chckbxNewCheckBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(layout.createSequentialGroup().addGap(37).addComponent(soloGif)
												.addPreferredGap(ComponentPlacement.RELATED))
										.addComponent(lblNewLabel).addComponent(
												button, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 71,
												Short.MAX_VALUE)))
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addGap(18)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(imagenes, GroupLayout.PREFERRED_SIZE, 60,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(label4))
												.addGap(26))
										.addComponent(imagenes, GroupLayout.PREFERRED_SIZE, 60,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label4)))
								.addGroup(layout.createSequentialGroup().addGap(43).addComponent(comboBox_1,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addGap(26))
				.addGroup(layout.createSequentialGroup().addGap(202).addComponent(rdbtnNewRadioButton)
						.addContainerGap(177, Short.MAX_VALUE)));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 20));

		getContentPane().setLayout(layout);
		setSize(new Dimension(790, 511));
		setLocationRelativeTo(null);

		try {

			if (Metodos.comprobarConexion(true)) {
				Metodos.ponerCategoriasBd(comboBox);
			}

		}

		catch (SQLException e3) {

		}

		javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder("Drop 'em");

		try {

			new DragAndDrop(imagenes, dragBorder, rootPaneCheckingEnabled, new DragAndDrop.Listener() {

				public void filesDropped(java.io.File[] files) {

					try {

						if (comboBox_1.getSelectedIndex() == 6) {

							ComprobarSha.comprobarSha(files);

						}

						else {

							Metodos.crearCarpetas();

							String carpeta = files[0].getCanonicalPath().substring(0,
									files[0].getCanonicalPath().lastIndexOf(separador) + 1);

							Metodos.moverArchivosDrop(files, separador, saberDondeSeMueve(), saberTipoDrop());

							Metodos.convertir(carpeta);

							renombrarArchivosDrop();

						}

					} catch (Exception e) {
						Metodos.mensaje("Error", 1);
					}

				}

			});

		}

		catch (TooManyListenersException e1) {
			Metodos.mensaje("Error al mover los archivos", 1);
		}

	}

	protected static void actualizar() throws IOException {

		lectura = Metodos.leerFicheroArray("Config.txt", 2);

		lectura[0] = ponerSeparador(lectura[0]);

		lecturaurl = Metodos.leerFicheroArray("Config2.txt", 2);
		configuracion = Metodos.leerFicheroArray("Configuracion.txt", 7);
		user = Metodos.leerFicheroArray("User.txt", 2);
		sonido = Metodos.leerFicheroArray("sonido.txt", 2);
		permisos = Metodos.leerFicheroArray("Permisos.txt", 4);
		lecturabd = Metodos.leerFicheroArray("Bd.txt", 6);
		lecturabackup = Metodos.leerFicheroArray("Backup.txt", 1);

		try {

			if (Metodos.comprobarConexion(true)) {

				Metodos.ponerCategoriasBd(comboBox);
			}

		}

		catch (SQLException e3) {

		}

	}

	public static String[] getLectura() {
		return lectura;
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
