package periquito;

import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.Scanner;
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
import java.awt.Toolkit;
import javax.swing.JSeparator;

@SuppressWarnings("serial")

public class MenuPrincipal extends javax.swing.JFrame implements ActionListener, ChangeListener {
	private static javax.swing.JComboBox<String> jComboBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JTextField jTextField1;
	private JButton btnNewButton;
	private JCheckBox check5;
	private static JCheckBox mute;
	private JButton boton1;
	private JRadioButton rdbtnNewRadioButton;
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
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_4;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JMenuItem mntmLocal;
	private JMenuItem mntmNewMenuItem_5;
	private JSeparator separator_3;
	private JMenuItem mntmNewMenuItem_6;
	private JSeparator separator_4;
	private JMenuItem mntmNewMenuItem_7;
	private JMenuItem mntmNewMenuItem_8;
	private JSeparator separator_5;
	private JSeparator separator_6;
	private JSeparator separator_7;

	static int numeroLineas(String fichero) {
		File input = new File("Categorias.txt");
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
		cadena = Config.eliminarUltimoElemento(cadena);
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
		String imagen = null;
		DriverSeleniumFirefox firefox = new DriverSeleniumFirefox();
		firefox.getDriver().get(lectura[3]);
		comprobacion = firefox.getDriver().findElement(By.name("salida")).getText();
		imagen = firefox.getDriver().findElement(By.name("imagen")).getText();
		Config.cerrarNavegador();

		if (comprobacion.compareTo("Folder empty") == 0) {
			mensaje("No hay imagenes en " + lectura[2] + "\\img", true);
			abrirCarpeta(lectura[2] + "\\img", true);
		}

		else {
			try {
				int salida = salida(lectura[2] + "\\Output", lectura[0], 9);
				notificacion(salida, lectura[0], "JPG", true);
				mensaje(salida + " imagen/es subida/s", false);
				if (salida > 0) {
					abrirCarpeta(lectura[0], true);
					DriverSeleniumFirefox firefox1 = new DriverSeleniumFirefox();
					firefox1.getDriver().get("file:///" + lectura[0] + "/" + imagen);
				}
			} catch (IOException e1) {
			}
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

	public void notificacion(int salida, String directorio, String tipo, Boolean abrir) throws MalformedURLException {
		if (salida <= 0) {
			mensaje("No hay archivos " + tipo + " en la carpeta " + directorio, true);
			if (abrir) {
				abrirCarpeta(directorio, true);
			}
		}

	}

	private void mover_imagenes(int opcion, String lectura, Boolean cerrarNavegador) throws IOException {
		if (cerrarNavegador) {
			Config.cerrarNavegador();
		}
		String[] destino;
		destino = Config.leerFicheroArray("Config2.txt", 2);
		if (opcion > 0 && !destino[0].isEmpty() && !destino[1].isEmpty()) {
			int salida;
			if (opcion == 9) {
				salida = salida(lectura + "\\gif", "\\\\" + destino[0] + "\\" + opcion, opcion);
				notificacion(salida, lectura + "gif", "GIF", true);
				salida = salida(lectura + "\\gif\\Thumb", "\\\\" + destino[1] + "\\" + opcion, opcion);
				notificacion(salida, lectura + "gif\\Thumb", "JPG", false);
				mensaje(salida + " imagen/es subida/s", false);
			} else {
				salida = salida(lectura, "\\\\" + destino[0] + "\\" + opcion, opcion);
				notificacion(salida, lectura, "JPG", true);
				salida = salida(lectura + "\\Thumb", "\\\\" + destino[1] + "\\" + opcion, opcion);
				notificacion(salida, lectura + "Thumb", "JPG", false);
				mensaje(salida + " imagen/es subida/s", false);
			}
		} else {
			new Config2().setVisible(true);
		}
	}

	public static int salida(String origen, String destino, int opcion) throws IOException {
		LinkedList<String> imagenes = new LinkedList<String>();
		if (opcion != 9 || (opcion == 9 && origen.contains("Thumb"))) {
			imagenes = Config.directorio(origen, ".jpg");
		} else {
			imagenes = Config.directorio(origen, ".gif");
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

	private int searchString(String[] findArray, String stringSearch) {
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

	public MenuPrincipal() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/maxresdefault.jpg")));
		setResizable(false);
		setTitle("Periquito 2.2");
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
				File af = new File("Config.txt");
				if (af.exists()) {
					String[] lectura;
					lectura = Config.leerFicheroArray("Config.txt", 6);
					if (!lectura[0].isEmpty() && lectura[0] != null && !lectura[2].isEmpty() && lectura[2] != null
							&& !lectura[3].isEmpty() && lectura[3] != null) {
						creargif(lectura);
					} else {
						new Config().setVisible(true);
					}
				}
			}
		});
		mnGifAnimator.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gifanim.png")));
		mnGifAnimator.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mnMenu.add(mnGifAnimator);
		mnGifExtractor = new JMenuItem("GIF Extractor");
		mnGifExtractor.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				File af = new File("Config.txt");
				if (af.exists()) {
					String[] lectura;
					lectura = Config.leerFicheroArray("Config.txt", 6);
					if (!lectura[4].isEmpty() && lectura[4] != null && !lectura[5].isEmpty() && lectura[5] != null) {
						String comprobacion = null;
						DriverSeleniumFirefox firefox = new DriverSeleniumFirefox();
						firefox.getDriver().get(lectura[5] = convertirCadena(lectura[5], "firefox"));
						comprobacion = firefox.getDriver().findElement(By.name("salida")).getText();

						Config.cerrarNavegador();

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
			}
		});

		separator_7 = new JSeparator();
		mnMenu.add(separator_7);
		mnGifExtractor.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/carpeta.png")));
		mnGifExtractor.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mnMenu.add(mnGifExtractor);
		mntmNewMenuItem_7 = new JMenuItem("Video 2 Frame");
		mntmNewMenuItem_7.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				String comprobacion = null;
				String[] lectura;
				lectura = Config.leerFicheroArray("Config.txt", 6);
				DriverSeleniumFirefox firefox = new DriverSeleniumFirefox();
				firefox.getDriver().get(
						lectura[5] = convertirCadena(lectura[1] + "/FrameExtractor/examples/index.php", "firefox"));
				comprobacion = firefox.getDriver().findElement(By.name("salida")).getText();
				Config.cerrarNavegador();

				switch (comprobacion) {
				case "Ya has convertido un video a frames!":
					mensaje("Ya has convertido un video a frames!", true);
					abrirCarpeta(lectura[0] + "\\..\\FrameExtractor\\examples\\output", true);
					break;
				case "No tienes videos":
					mensaje("No tienes videos para convertir a frames", true);
					abrirCarpeta(lectura[0] + "\\..\\FrameExtractor\\examples\\video", true);
					break;
				case "Exito!":
					abrirCarpeta(lectura[0] + "\\..\\FrameExtractor\\examples\\output", true);
					break;
				}
			}
		});

		separator_6 = new JSeparator();
		mnMenu.add(separator_6);
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmNewMenuItem_7.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video2frames.png")));
		mnMenu.add(mntmNewMenuItem_7);
		mntmNewMenuItem = new JMenu("   ");
		mntmNewMenuItem.setBackground(Color.BLACK);
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmNewMenuItem.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menuBar.add(mntmNewMenuItem);
		mntmImages = new JMenuItem("Imagenes");
		mntmImages.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmImages.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				File af = new File("Config.txt");
				if (af.exists()) {
					String[] lectura;
					lectura = Config.leerFicheroArray("Config.txt", 6);
					abrirCarpeta(lectura[0], true);
				} else {
					new Config().setVisible(true);
				}
			}
		});
		mntmImages.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmImages.setSelectedIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmNewMenuItem.add(mntmImages);
		mntmImages_1 = new JMenuItem("GIF Animator");
		mntmImages_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmImages_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				File af = new File("Config.txt");
				if (af.exists()) {
					String[] lectura;
					lectura = Config.leerFicheroArray("Config.txt", 6);
					if (!lectura[2].isEmpty()) {
						abrirCarpeta(lectura[2] + "\\img", true);
					} else {
						abrirCarpeta(lectura[2], true);
					}
				} else {
					new Config().setVisible(true);
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
				File af = new File("Config.txt");
				if (af.exists()) {
					String[] lectura;
					lectura = Config.leerFicheroArray("Config.txt", 6);
					abrirCarpeta(lectura[4], true);
				} else {
					new Config().setVisible(true);
				}
			}
		});

		separator_2 = new JSeparator();
		mntmNewMenuItem.add(separator_2);
		mntmCxvxv.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/GIF_Extract.png")));
		mntmNewMenuItem.add(mntmCxvxv);
		mntmNewMenuItem_8 = new JMenuItem("FrameExtractor");
		mntmNewMenuItem_8.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				File af = new File("Config.txt");
				if (af.exists()) {
					String[] lectura;
					lectura = Config.leerFicheroArray("Config.txt", 6);
					abrirCarpeta(lectura[0] + "\\..\\FrameExtractor\\examples\\video", true);
				} else {
					new Config().setVisible(true);
				}
			}
		});

		separator_5 = new JSeparator();
		mntmNewMenuItem.add(separator_5);
		mntmNewMenuItem_8.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmNewMenuItem_8.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmNewMenuItem.add(mntmNewMenuItem_8);
		mntmNewMenuItem_1 = new JMenu("Config");
		mntmNewMenuItem_1.setBackground(Color.BLACK);
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
		mntmNewMenuItem_5 = new JMenuItem("Remoto");
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
		mntmNewMenuItem_6 = new JMenuItem("Categorias");
		mntmNewMenuItem_6.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				verPanelCategorias();
			}
		});

		separator_4 = new JSeparator();
		mntmNewMenuItem_1.add(separator_4);
		mntmNewMenuItem_6.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/tag.png")));
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem_1.add(mntmNewMenuItem_6);
		mntmNewMenuItem_2 = new JMenu("Ayuda");
		mntmNewMenuItem_2.setBackground(Color.BLACK);
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem_2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/help.png")));
		menuBar.add(mntmNewMenuItem_2);
		mntmNewMenuItem_3 = new JMenuItem("Sobre");
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
		ponerCategorias();
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
				File af = new File("Categorias.txt");
				File config = new File("Config.txt");
				File config2 = new File("Config2.txt");
				if (af.exists() && config.exists() && config2.exists()) {
					if (numeroLineas("Categorias.txt") == 0) {
						verPanelCategorias();
					} else {
						if (numeroLineas("Config.txt") == 0) {
							new Config().setVisible(true);
						} else {
							if (numeroLineas("Config2.txt") == 0) {
								new Config2().setVisible(true);
							} else {
								String[] lectura;
								lectura = Config.leerFicheroArray("Config.txt", 6);
								String[] categorias = Config.leerFicheroArray("Categorias.txt",
										numeroLineas("Categorias.txt"));
								if (!lectura[0].isEmpty()) {
									String cat = (String) jComboBox1.getSelectedItem();
									if (cat != null) {
										int opcion = searchString(categorias, cat) + 1;
										try {
											lectura[0] = Config.eliminarUltimoElemento(lectura[0]);
											mover_imagenes(opcion, lectura[0], true);
											check5.setSelected(false);
										} catch (IOException e1) {
											mensaje("Error al copiar las imagenes", true);
										}
									}
								} else {
									new Config().setVisible(true);
								}
							}
						}
					}
				} else {
					if (!af.exists()) {
						verPanelCategorias();
					}
					if (!config.exists()) {
						new Config().setVisible(true);
					}
					if (!config2.exists()) {
						new Config2().setVisible(true);
					}
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
				button1ActionPerformed(arg0);
			}
		});
		rdbtnNewRadioButton = new JRadioButton("");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rdbtnNewRadioButton);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/Firefox.png")));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(11)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(3).addComponent(label).addGap(18)
										.addComponent(rdbtnNewRadioButton)
										.addPreferredGap(ComponentPlacement.RELATED, 335, Short.MAX_VALUE)
										.addComponent(lblNewLabel).addGap(62))
								.addGroup(layout.createSequentialGroup()
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
				.addGroup(layout.createParallelGroup(Alignment.LEADING, false).addGroup(layout.createSequentialGroup()
						.addGap(31)
						.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(rdbtnNewRadioButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)))
						.addGroup(layout.createSequentialGroup().addGap(16).addComponent(lblNewLabel,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
				.addContainerGap()));
		getContentPane().setLayout(layout);
		setSize(new Dimension(573, 478));
		setLocationRelativeTo(null);
	}

	static void ponerCategorias() {
		File af = new File("Categorias.txt");
		if (af.exists() && numeroLineas("Categorias.txt") > 0) {
			String[] categorias = Config.leerFicheroArray("Categorias.txt", numeroLineas("Categorias.txt"));
			jComboBox1.setFont(new Font("Tahoma", Font.BOLD, 24));
			if (categorias.length > 0) {
				jComboBox1.setModel(new DefaultComboBoxModel<String>(categorias));
			}
		}
	}

	@SuppressWarnings("static-access")
	private void button1ActionPerformed(java.awt.event.ActionEvent evt) {
		File categoria = new File("Categorias.txt");
		File af = new File("Config.txt");
		if (af.exists() && categoria.exists()) {
			String[] lectura;
			String[] categoriias;
			lectura = Config.leerFicheroArray("Config.txt", 6);
			if (numeroLineas("categorias.txt") > 0) {
				categoriias = Config.leerFicheroArray("categorias.txt", numeroLineas("categorias.txt"));
				if (!lectura[0].isEmpty() && lectura[0] != null && !lectura[1].isEmpty() && lectura[1] != null
						&& !categoriias[0].isEmpty() && categoriias[0] != null) {
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
						int opcion = -1;
						lectura[1] = convertirCadena(lectura[1], "boton");
						DriverSeleniumFirefox prueba = new DriverSeleniumFirefox();
						prueba.getDriver().get(lectura[1]);
						prueba.getDriver().findElement(By.name("nombre")).sendKeys(comprobacion);
						Select drpCountry = new Select(prueba.getDriver().findElement(By.name("categoria")));
						drpCountry.selectByVisibleText(cat);
						prueba.getDriver().findElement(By.name("envio")).click();
						prueba.getDriver().findElement(By.name("si")).click();
						opcion = Integer.parseInt(prueba.getDriver().findElement(By.name("salida")).getText());

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

						try {
							mover_imagenes(opcion, lectura[0], true);
						} catch (IOException e) {
							mensaje("Error al copiar las imagenes", true);
						}

					} else {
						mensaje("Introduce un texto", true);
					}
				} else {
					if ((lectura[0].isEmpty() || lectura[1].isEmpty())) {
						new Config().setVisible(true);
					} else {
						if (!af.exists() && categoria.exists()) {
							new Config().setVisible(true);
						} else {
							if (!categoria.exists() && af.exists()) {
								verPanelCategorias();
							} else {
								new Config().setVisible(true);
								verPanelCategorias();
							}
						}
					}
				}
			} else {
				verPanelCategorias();
			}
		}
	}

	private void verPanelCategorias() {
		@SuppressWarnings("unused")
		Categorias ejemplo = new Categorias();
		Categorias.frmprincipal.setLocationRelativeTo(null);
		Categorias.frmprincipal.setVisible(true);
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
