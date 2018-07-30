package periquito;

import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import javax.swing.JRadioButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.ComponentOrientation;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JProgressBar;
import java.awt.Toolkit;
import javax.swing.JSeparator;

@SuppressWarnings("serial")

public class MenuPrincipal extends javax.swing.JFrame implements ActionListener, ChangeListener {
	private javax.swing.JComboBox<String> jComboBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JTextField jTextField1;
	private JButton btnNewButton;
	private JCheckBox check5;
	private static JCheckBox mute;
	private JButton boton1;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnChrome;
	private JMenuBar jmenubarra;
	private JMenuBar menuBar;
	private JMenu mnMenu;
	private JMenuItem mnGifAnimator;
	private JMenuItem mnGifExtractor;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmImages;
	private JMenuItem mntmImages_1;
	private JMenuItem mntmCxvxv;
	private JMenuItem mntmNewMenuItem_1;
	JProgressBar progressBar = new JProgressBar();
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_4;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JMenuItem mntmLocal;
	private JMenuItem mntmNewMenuItem_5;
	private JSeparator separator_3;

	public String[] leerArchivo(String nombre) {
		String[] lectura = null;
		File af = new File(nombre);
		if (af.exists()) {
			lectura = Config.leerFicheroArray(nombre);
		}
		return lectura;
	}

	public static String eliminarIndices(String cadena) {
		cadena = cadena.replace("index.php", "");
		cadena = cadena.replace("index.html", "");
		cadena = cadena.replace("index.htm", "");
		cadena = cadena.replace("/", "\\");
		cadena = cadena.replace("//", "\\");
		cadena = cadena.replace("c:", "C:");
		cadena = cadena.replace("http", "C:\\");
		return cadena;
	}

	public void abrirCarpeta(String ruta, Boolean retocar) {
		if (retocar) {
			ruta = eliminarIndices(ruta);
		}
		if (ruta != null && ruta != "" && !ruta.isEmpty()) {
			try {
				Runtime.getRuntime().exec("cmd /c start " + ruta);
			} catch (IOException e) {
				mensaje("Ruta invalida", true);
			}
		} else {
			new Config().setVisible(true);
		}
	}

	public static String convertirCadena(String cadena, String cadena2) {

		if ((int) cadena2.compareTo("boton") != 0) {
			if (cadena == null && cadena2 != null) {
				cadena = cadena2;
			}
			if (cadena.isEmpty() && !cadena2.isEmpty()) {
				cadena = cadena2;
			}
			if (cadena2.isEmpty() && !cadena.isEmpty()) {
				cadena = "";
			}
		}
		cadena = cadena.replace("\\imagenes", "");
		cadena = cadena.replace("C:\\", "http://localhost/");
		cadena = cadena.replace("AppServ\\", "");
		cadena = cadena.replace("wamp\\", "");
		cadena = cadena.replace("www\\", "");
		cadena = cadena.replace("xampp\\htdocs\\", "");
		cadena = cadena.replace("index.php", "");
		cadena = eliminarUltimoElemento(cadena);
		return cadena;
	}

	public static String eliminarUltimoElemento(String cadena) {
		if (cadena.length() > 1) {
			if (cadena.charAt(cadena.length() - 1) == 92 || cadena.charAt(cadena.length() - 1) == 47) {
				cadena = cadena.substring(0, cadena.length() - 1);
			}
			cadena = urlPredeterminada(cadena);
		}
		return cadena;
	}

	public static String urlPredeterminada(String cadena) {
		if (cadena.length() >= 5) {
			if (cadena.substring(cadena.length() - 4, cadena.length()).compareTo(".php") == 0
					|| cadena.substring(cadena.length() - 5, cadena.length()).compareTo(".html") == 0
					|| cadena.substring(cadena.length() - 4, cadena.length()).compareTo(".htm") == 0) {
			} else {
				cadena += "\\index.php";
			}

			if (cadena.substring(0, 7).compareTo("http://") != 0) {
				cadena = "http://" + cadena;
			}
			cadena = cadena.replace("\\", "/");
		}
		return cadena;
	}

	public void creargif(String[] lectura) {
		lectura[3] = convertirCadena(lectura[3], "gif");
		lectura[3] = urlPredeterminada(lectura[3]);
		String comprobacion = null;
		int navegador = 0;
		progressBar.setValue(60);
		progressBar.setStringPainted(true);
		if (rdbtnNewRadioButton.isSelected()) {
			DriverSeleniumFirefox firefox = new DriverSeleniumFirefox();
			firefox.getDriver().get(lectura[3]);
			comprobacion = firefox.getDriver().findElement(By.name("salida")).getText();
			navegador = 1;
		}

		if (rdbtnChrome.isSelected()) {
			DriverSeleniumChrome chrome = new DriverSeleniumChrome();
			chrome.getDriver().get(lectura[3]);
			comprobacion = chrome.getDriver().findElement(By.name("salida")).getText();
			navegador = 2;
		}
		progressBar.setValue(80);
		progressBar.setStringPainted(true);
		cerrarNavegador(navegador);

		if (comprobacion.compareTo("Folder empty") == 0) {
			mensaje("No hay imagenes en " + lectura[2] + "\\img", true);
			abrirCarpeta(lectura[2] + "\\img", true);
			progressBar.setValue(0);
			progressBar.setStringPainted(true);
		}

		else {

			try {
				int salida = salida(lectura[2] + "\\Output", lectura[0], 9);
				notificacion(salida, lectura[0], "JPG");
				progressBar.setValue(100);
				progressBar.setStringPainted(true);
				abrirCarpeta(lectura[0], true);
				progressBar.setValue(0);
				progressBar.setStringPainted(true);
				mensaje(salida + " imagen/es subida/s", false);
				abrirCarpeta(lectura[0], true);
			} catch (IOException e1) {
			}
		}
	}

	public void cerrarNavegador(int navegador) {
		try {
			Runtime aplicacion = Runtime.getRuntime();
			switch (navegador) {
			case 1:
				aplicacion.exec("cmd.exe /K start C:\\cerrar.bat");
				break;
			case 2:
				aplicacion.exec("cmd.exe /K start C:\\cerrarchrome.bat");
				break;
			}

		} catch (IOException e2) {
		}
	}

	public void mensaje(String mensaje, Boolean error) {
		AudioClip clip;
		if (error) {
			clip = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/duck-quack1.wav"));
		} else {
			clip = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/gong1.wav"));
		}

		if (mute.isSelected() == true) {
			clip.stop();
		} else {
			clip.loop();
		}
		int option;
		if (error) {
			JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
			option = JOptionPane.CLOSED_OPTION;
		} else {
			JOptionPane.showMessageDialog(null, mensaje, "Success", JOptionPane.INFORMATION_MESSAGE);
			option = JOptionPane.CLOSED_OPTION;
		}

		if (option == -1) {
			clip.stop();
		}
	}

	public void notificacion(int salida, String directorio, String tipo) throws MalformedURLException {
		if (salida <= 0) {
			mensaje("No hay archivos " + tipo + " en la carpeta " + directorio, true);
		}
	}

	private void mover_imagenes(int opcion, int navegador, String lectura) throws IOException {
		cerrarNavegador(navegador);
		progressBar.setValue(50);
		progressBar.setStringPainted(true);
		String[] destino;
		destino = leerArchivo("Config2.txt");
		if (opcion > 0 && !destino[0].isEmpty() && !destino[1].isEmpty()) {
			int salida;
			if (opcion == 9) {
				salida = salida(lectura + "\\gif", "\\\\" + destino[0] + "\\" + opcion, opcion);
				notificacion(salida, lectura + "\\gif", "GIF");
				salida = salida(lectura + "\\gif\\Thumb", "\\\\" + destino[1] + "\\" + opcion, opcion);
				notificacion(salida, lectura + "\\gif\\Thumb", "JPG");
				mensaje(salida + " imagen/es subida/s", false);
			} else {
				salida = salida(lectura, "\\\\" + destino[0] + "\\" + opcion, opcion);
				notificacion(salida, lectura, "JPG");
				salida = salida(lectura + "\\Thumb", "\\\\" + destino[1] + "\\" + opcion, opcion);
				notificacion(salida, lectura + "\\Thumb", "JPG");
				mensaje(salida + " imagen/es subida/s", false);
			}
			progressBar.setValue(100);
			progressBar.setStringPainted(true);
		} else {
			new Config2().setVisible(true);
		}
	}

	public static int salida(String origen, String destino, int opcion) throws IOException {
		LinkedList<String> imagenes = new LinkedList<String>();
		if (opcion != 9 || (opcion == 9 && origen.contains("Thumb"))) {
			imagenes = directorio(origen, ".jpg");
		} else {
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

	public MenuPrincipal() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/maxresdefault.jpg")));
		setResizable(false);
		setTitle("Periquito 2.0");
		setSize(600, 600);
		getContentPane().setLayout(new BorderLayout());

		jmenubarra = new JMenuBar();

		getContentPane().add(jmenubarra, BorderLayout.NORTH);

		menuBar = new JMenuBar();
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setToolTipText("");
		menuBar.setName("");
		menuBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		menuBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		setJMenuBar(menuBar);

		mnMenu = new JMenu("          ");
		mnMenu.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/utilities.png")));
		mnMenu.setFont(new Font("Segoe UI", Font.BOLD, 10));
		mnMenu.setBackground(new Color(0, 0, 0));
		mnMenu.setForeground(new Color(0, 0, 0));
		menuBar.add(mnMenu);

		mnGifAnimator = new JMenuItem("GIF Animator");

		mnGifAnimator.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				progressBar.setValue(0);
				progressBar.setStringPainted(true);
				String[] lectura;
				lectura = leerArchivo("config.txt");
				if (!lectura[0].isEmpty() && lectura[0] != null && !lectura[2].isEmpty() && lectura[2] != null
						&& !lectura[3].isEmpty() && lectura[3] != null) {
					System.out.println("aaaaaaaa " + lectura[0]);
					System.out.println(lectura[2]);
					System.out.println(lectura[3]);
					progressBar.setValue(30);
					progressBar.setStringPainted(true);
					creargif(lectura);
				} else {
					new Config().setVisible(true);
				}
			}
		});
		mnGifAnimator.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gifanim.png")));
		mnGifAnimator.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mnMenu.add(mnGifAnimator);
		mnMenu.addSeparator();
		mnGifExtractor = new JMenuItem("GIF Extractor");
		mnGifExtractor.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				String[] lectura;
				lectura = leerArchivo("config.txt");
				if (lectura[4] != "" && lectura[4] != null && lectura[5] != null && lectura[5] != null) {
					String comprobacion = null;
					int navegador = 0;
					if (rdbtnNewRadioButton.isSelected()) {
						DriverSeleniumFirefox firefox = new DriverSeleniumFirefox();
						firefox.getDriver().get(lectura[5] = convertirCadena(lectura[5], "firefox"));
						comprobacion = firefox.getDriver().findElement(By.name("salida")).getText();
						navegador = 1;
					}

					if (rdbtnChrome.isSelected()) {
						DriverSeleniumChrome chrome = new DriverSeleniumChrome();
						chrome.getDriver().get(lectura[5] = convertirCadena(lectura[5], "chrome"));
						comprobacion = chrome.getDriver().findElement(By.name("salida")).getText();
						navegador = 2;
					}

					cerrarNavegador(navegador);

					switch (comprobacion) {
					case "Ya has convertido un gif a frames!":
						mensaje("Ya has convertido un gif a frames!", true);
						abrirCarpeta(lectura[4] + "\\frames", true);
						break;
					case "Tienes mas de 1 archivo gif":
						mensaje("Debes de tener solo un archivo en la carpeta gif", true);
						abrirCarpeta(lectura[4], true);
						break;
					case "Error!":
						mensaje("No hay 1 archivo gif para extraer", true);
						abrirCarpeta(lectura[4], true);
						break;
					case "Exito!":
						abrirCarpeta(lectura[4] + "\\frames", true);
						break;
					}
				} else {
					new Config().setVisible(true);
				}
			}
		});
		mnGifExtractor.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/carpeta.png")));
		mnGifExtractor.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mnMenu.add(mnGifExtractor);

		mntmNewMenuItem = new JMenu("   ");
		mntmNewMenuItem.setBackground(Color.BLACK);
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmNewMenuItem.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menuBar.add(mntmNewMenuItem);

		mntmImages = new JMenuItem("Images");
		mntmImages.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmImages.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String[] lectura;
				lectura = leerArchivo("config.txt");
				abrirCarpeta(lectura[0], true);
			}
		});
		mntmImages.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmImages.setSelectedIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmNewMenuItem.add(mntmImages);

		mntmImages_1 = new JMenuItem("GIF Animator");
		mntmImages_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmImages_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String[] lectura;
				lectura = leerArchivo("config.txt");
				if (!lectura[2].isEmpty()) {
					abrirCarpeta(lectura[2] + "\\img", true);
				} else {
					abrirCarpeta(lectura[2], true);
				}
			}
		});

		separator_1 = new JSeparator();
		mntmNewMenuItem.add(separator_1);
		mntmImages_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gif.png")));
		mntmImages_1.setSelectedIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmNewMenuItem.add(mntmImages_1);

		mntmImages_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gif.png")));
		mntmImages_1.setSelectedIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmNewMenuItem.add(mntmImages_1);

		mntmCxvxv = new JMenuItem("GIF Extractor");
		mntmCxvxv.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmCxvxv.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String[] lectura;
				lectura = leerArchivo("config.txt");
				abrirCarpeta(lectura[4], true);
			}
		});

		separator_2 = new JSeparator();
		mntmNewMenuItem.add(separator_2);
		mntmCxvxv.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/GIF_Extract.png")));
		mntmNewMenuItem.add(mntmCxvxv);

		mntmNewMenuItem_1 = new JMenu("Config");
		mntmNewMenuItem_1.setBackground(Color.BLACK);
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/config.png")));
		menuBar.add(mntmNewMenuItem_1);

		mntmLocal = new JMenuItem("Local");
		mntmLocal.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				new Config().setVisible(true);
			}
		});
		mntmLocal.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmLocal.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/config.png")));
		mntmNewMenuItem_1.add(mntmLocal);

		mntmNewMenuItem_5 = new JMenuItem("Remote");
		mntmNewMenuItem_5.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				new Config2().setVisible(true);
			}
		});

		separator_3 = new JSeparator();
		mntmNewMenuItem_1.add(separator_3);
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem_5.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/remote.png")));
		mntmNewMenuItem_1.add(mntmNewMenuItem_5);

		mntmNewMenuItem_2 = new JMenu("Help");
		mntmNewMenuItem_2.setBackground(Color.BLACK);
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem_2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/help.png")));
		menuBar.add(mntmNewMenuItem_2);

		mntmNewMenuItem_3 = new JMenuItem("About");
		mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				new About().setVisible(true);
			}
		});
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem_3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/about.png")));
		mntmNewMenuItem_2.add(mntmNewMenuItem_3);

		separator = new JSeparator();
		mntmNewMenuItem_2.add(separator);

		mntmNewMenuItem_4 = new JMenuItem("IMG 2 Color");
		mntmNewMenuItem_4.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				abrirCarpeta("https://demos.algorithmia.com/colorize-photos/", false);
			}
		});
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem_4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/30-07-2018 1-07-31.png")));
		mntmNewMenuItem_2.add(mntmNewMenuItem_4);

		initComponents();
		this.setVisible(true);

	}

	private void initComponents() {

		jTextField1 = new javax.swing.JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.LEFT);

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/name.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		jComboBox1 = new javax.swing.JComboBox<>();

		jLabel2 = new javax.swing.JLabel();
		jLabel2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/tag.png")));
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		jTextField1.setFont(new Font("Tahoma", Font.BOLD, 24));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 28));
		// String[]categorias=leerArchivo("");
		jComboBox1.setFont(new Font("Tahoma", Font.BOLD, 24));
		jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[] { "Models", "Girls/Womans", "XXX", "Singers",
				"WebCam", "Films/Cartoon", "Dangles", "GIF", "Sado", "Earrings" }));

		jLabel2.setFont(new Font("Tahoma", Font.BOLD, 28));

		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/fix.png")));
		check5 = new JCheckBox("Fix");
		check5.setBounds(170, 170, 80, 60);
		check5.addChangeListener(this);
		check5.setFont(new Font("Tahoma", Font.BOLD, 25));
		getContentPane().add(check5);
		mute = new JCheckBox("");
		mute.setBounds(530, 270, 80, 60);
		mute.addChangeListener(this);
		mute.setFont(new java.awt.Font("Tahoma", 1, 18));
		getContentPane().add(mute);
		btnNewButton.setBounds(10, 100, 100, 30);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
		btnNewButton.setEnabled(false);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] lectura;
				lectura = leerArchivo("config.txt");
				if (!lectura[0].isEmpty()) {
					String cat = (String) jComboBox1.getSelectedItem();
					int opcion = 0;
					switch (cat) {

					case "Models":
						opcion = 1;
						break;

					case "Girls/Womans":
						opcion = 2;
						break;

					case "XXX":
						opcion = 3;
						break;

					case "Singers":
						opcion = 4;
						break;

					case "WebCam":
						opcion = 5;
						break;

					case "Films/Cartoon":
						opcion = 6;
						break;

					case "Dangles":
						opcion = 7;
						break;

					case "GIF":
						opcion = 9;
						break;

					case "Sado":
						opcion = 10;
						break;

					case "Earrings":
						opcion = 11;
						break;

					}
					try {
						int navegador = 0;
						if (rdbtnNewRadioButton.isSelected()) {

							navegador = 1;
						}

						if (rdbtnChrome.isSelected()) {

							navegador = 2;
						}

						lectura[0] = eliminarUltimoElemento(lectura[0]);
						mover_imagenes(opcion, navegador, lectura[0]);
					} catch (IOException e1) {
						mensaje("Error al copiar las imagenes", true);
					}
				} else {
					new Config().setVisible(true);
				}
			}
		});

		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 24));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/WAV_00002.png")));
		lblNewLabel.setBounds(10, 195, 80, 60);
		boton1 = new JButton("");
		boton1.setFont(new Font("Tahoma", Font.PLAIN, 6));
		boton1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/start.png")));
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					progressBar.setValue(0);
					progressBar.setStringPainted(true);
					button1ActionPerformed(arg0);
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		});

		rdbtnNewRadioButton = new JRadioButton("");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 18));

		rdbtnChrome = new JRadioButton("");
		rdbtnChrome.setFont(new Font("Tahoma", Font.BOLD, 18));

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rdbtnNewRadioButton);
		grupo.add(rdbtnChrome);
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 40));

		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/Firefox.png")));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/chrome.gif")));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(11)
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addGap(3).addComponent(label).addGap(18).addComponent(rdbtnNewRadioButton)
						.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE).addComponent(lblNewLabel_1)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(rdbtnChrome).addGap(25)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(lblNewLabel).addGap(62)).addGroup(
								layout.createSequentialGroup()
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 105,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(layout
												.createParallelGroup(Alignment.TRAILING)
												.addGroup(layout.createSequentialGroup()
														.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
																.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 116,
																		Short.MAX_VALUE))
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(layout.createParallelGroup(Alignment.LEADING)
																.addGroup(layout.createSequentialGroup()
																		.addComponent(jTextField1, 293, 293, 293)
																		.addPreferredGap(ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
																.addComponent(jComboBox1, 0, 293, Short.MAX_VALUE))
														.addGap(39))
												.addGroup(layout.createSequentialGroup().addComponent(boton1,
														GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
														.addGap(53)))))));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout
								.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel1)
										.addGroup(layout.createSequentialGroup().addGap(8).addComponent(jTextField1,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(jLabel2))
										.addGroup(layout.createSequentialGroup().addGap(20).addComponent(jComboBox1,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
								.addGap(19)
								.addComponent(boton1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup().addGap(31)
								.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(rdbtnChrome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(rdbtnNewRadioButton, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)))
						.addGroup(layout.createSequentialGroup().addGap(16).addComponent(lblNewLabel,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
				.addContainerGap()));
		getContentPane().setLayout(layout);
		setSize(new Dimension(573, 478));
		setLocationRelativeTo(null);

	}

	@SuppressWarnings("static-access")
	private void button1ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {

		String[] lectura;
		lectura = leerArchivo("config.txt");
		if (!lectura[0].isEmpty() && !lectura[1].isEmpty()) {

			String comprobacion = jTextField1.getText();
			comprobacion = comprobacion.trim();

			if (comprobacion.length() != 0) {

				comprobacion = comprobacion.replace("  ", " ");
				comprobacion = comprobacion.replace("   ", " ");
				comprobacion = comprobacion.replace("  ", " ");
				comprobacion = comprobacion.replace("   ", " ");
				comprobacion = comprobacion.replace("  ", " ");
				int resultado = comprobacion.indexOf(" ");
				resultado = Integer.parseInt((comprobacion.valueOf(resultado + 1)));

				if (resultado > 0) {
					comprobacion = comprobacion.replace("  ", " ");
					comprobacion = comprobacion.replace("  ", " ");
					comprobacion = comprobacion.replace("  ", " ");
					comprobacion = comprobacion.replace("  ", " ");
				}

				String cat = (String) jComboBox1.getSelectedItem();
				int navegador = 0;
				int opcion = 0;

				lectura[1] = convertirCadena(lectura[1], "boton");
				if (rdbtnNewRadioButton.isSelected()) {
					DriverSeleniumFirefox prueba = new DriverSeleniumFirefox();
					prueba.getDriver().get(lectura[1]);
					prueba.getDriver().findElement(By.name("nombre")).sendKeys(comprobacion);
					Select drpCountry = new Select(prueba.getDriver().findElement(By.name("categoria")));
					drpCountry.selectByVisibleText(cat);
					prueba.getDriver().findElement(By.name("envio")).click();
					prueba.getDriver().findElement(By.name("si")).click();
					opcion = Integer.parseInt(prueba.getDriver().findElement(By.name("salida")).getText());
					navegador = 1;
				}

				if (rdbtnChrome.isSelected()) {
					DriverSeleniumChrome prueba = new DriverSeleniumChrome();
					prueba.getDriver().get(lectura[1]);
					prueba.getDriver().findElement(By.name("nombre")).sendKeys(comprobacion);
					Select drpCountry = new Select(prueba.getDriver().findElement(By.name("categoria")));
					drpCountry.selectByVisibleText(cat);
					prueba.getDriver().findElement(By.name("envio")).click();
					prueba.getDriver().findElement(By.name("si")).click();
					opcion = Integer.parseInt(prueba.getDriver().findElement(By.name("salida")).getText());
					navegador = 2;
				}

				if (lectura[0].length() > 1) {
					if (lectura[0].charAt(lectura[0].length() - 1) != 92) {
						if (lectura[0].charAt(lectura[0].length() - 1) == 47) {
							lectura[0] = lectura[0].substring(0, lectura[0].length() - 1);
						}
						lectura[0] += "\\";
					}

					Config guardar = new Config();

					Config.jTextField1.setText(lectura[0]);
					guardar.guardarDatos(false);
				}
				progressBar.setValue(25);
				progressBar.setStringPainted(true);

				if (!lectura[0].isEmpty()) {
					mover_imagenes(opcion, navegador, lectura[0]);
				}
				progressBar.setValue(0);
				progressBar.setStringPainted(true);

			} else {
				mensaje("Introduce un texto", true);
			}
		} else {
			new Config().setVisible(true);
		}
	}

	public void stateChanged(ChangeEvent e) {

		if (check5.isSelected() == true) {
			btnNewButton.setEnabled(true);
			jLabel1.setEnabled(false);
			jLabel2.setEnabled(false);
			jTextField1.setEnabled(false);
			jComboBox1.setEnabled(false);
			boton1.setEnabled(false);
		} else {
			btnNewButton.setEnabled(false);
			jLabel1.setEnabled(true);
			jLabel2.setEnabled(true);
			jTextField1.setEnabled(true);
			jComboBox1.setEnabled(true);
			boton1.setEnabled(true);
		}
	}

	public void actionPerformed(ActionEvent arg0) {

	}
}
