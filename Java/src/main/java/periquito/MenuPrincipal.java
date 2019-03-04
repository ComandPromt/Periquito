package periquito;

import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import Utils.DragAndDrop;
import Utils.Metodos;
import Utils.interfaz;

@SuppressWarnings("serial")

public class MenuPrincipal extends javax.swing.JFrame implements ActionListener, ChangeListener, interfaz {
	public static javax.swing.JComboBox<String> jComboBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JTextField jTextField1;
	private JButton btnNewButton;
	private JCheckBox check5;
	private static JCheckBox mute;
	private JButton boton1;
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
	private JSeparator separator_4;
	private JMenuItem mntmNewMenuItem_7;
	private JMenuItem mntmNewMenuItem_8;
	private JSeparator separator_5;
	private JMenuItem mntmNewMenuItem_9;
	private JSeparator separator_8;
	private JMenuItem mntmNewMenuItem_10;
	private Label check6;
	private JLabel importar;
	private JMenu mnVideo;
	private JMenu mnGif;
	private JSeparator separator_6;
	private JSeparator separator_7;
	private JSeparator separator_9;
	private JMenuItem mntmUploads;
	private JMenuItem mntmNewMenuItem_11;
	private JSeparator separator_11;
	private JMenuItem mntmNewMenuItem_12;
	private JSeparator separator_12;
	final static javax.swing.JTextArea imagenes = new javax.swing.JTextArea();
	static ArrayList<String> categorias;

	public static ArrayList<String> getCategorias() {
		return categorias;
	}

	String[] lectura = Metodos.leerFicheroArray("Config/Config.txt", 6);
	String[] lecturaurl = Metodos.leerFicheroArray("Config/Config2.txt", 2);
	String[] lecturaos = Metodos.leerFicheroArray("Config/OS.txt", 1);
	String[] lecturacategorias;
	private JSeparator separator_13;
	private JMenuItem mntmNewMenuItem_13;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem_14;
	private JSeparator separator_14;
	private JSeparator separator_15;
	private JMenuItem mntmNewMenuItem_15;
	private JMenu mnNewMenu_1;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JLabel lblNewLabel_1;
	private JSeparator separator_17;
	private JLabel lblNewLabel_2;
	private JSeparator separator_22;

	public void comprobarConexion(String archivo, String ruta) {
		File af = new File(archivo);
		if (af.exists()) {
			File comprobacion = new File(ruta);
			if (!comprobacion.exists()) {
				mensaje("Ruta inválida ", true);
				new Config().setVisible(true);
			} else {
				Metodos.abrirCarpeta(ruta, true);
			}
		} else {
			new Config().setVisible(true);
		}
	}

	public static javax.swing.JTextArea getImagenes() {
		return imagenes;
	}

	public static String convertirCadena(String cadena, String cadena2) {
		if (!cadena2.equals("boton")) {
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
		cadena = Metodos.eliminarUltimoElemento(cadena);
		return cadena;
	}

	public void verConfiguraciones() throws IOException {
		new Config().setVisible(true);
		new Config2().setVisible(true);
	}

	public static String urlPredeterminada(String cadena) {
		if (cadena.length() >= 5) {
			if (cadena.substring(cadena.length() - 4, cadena.length()).equals(".php")
					|| cadena.substring(cadena.length() - 5, cadena.length()).equals(".html")
					|| cadena.substring(cadena.length() - 4, cadena.length()).equals(".htm")) {
			} else {
				cadena += "\\index.php";
			}
			if (!cadena.substring(0, 7).equals("http://")) {
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
		String imagen;
		WebDriver firefox = new ChromeDriver();
		firefox.get(lectura[3]);
		comprobacion = firefox.findElement(By.name("salida")).getText();
		imagen = firefox.findElement(By.name("imagen")).getText();
		if (comprobacion.equals("Folder empty")) {
			mensaje("No hay imagenes en " + lectura[2] + "\\img", true);
			Metodos.abrirCarpeta(lectura[2] + "\\img", true);
		} else {
			try {
				int salida = Metodos.salida(lectura[2] + "\\Output", lectura[0], 9);
				Metodos.notificacion(salida, lectura[0], "JPG", true);
				mensaje(salida + " GIF creado correctamente", false);
				if (salida > 0) {
					Metodos.abrirCarpeta(lectura[0], true);
					firefox.get("file:///" + lectura[0] + "/" + imagen);
				}
			} catch (IOException e1) {
				mensaje("Error en la creación de archivos GIF", true);
			}
		}
	}

	public void mensaje(String mensaje, Boolean error) {
		JLabel alerta = new JLabel(mensaje);
		alerta.setFont(new Font("Arial", Font.BOLD, 18));
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
			JOptionPane.showMessageDialog(null, alerta, "Error", JOptionPane.ERROR_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(null, alerta, "Success", JOptionPane.INFORMATION_MESSAGE);
		}
		option = JOptionPane.CLOSED_OPTION;
		if (option == -1) {
			clip.stop();
		}
	}

	private void mover_imagenes(int opcion, String lectura, Boolean cerrarNavegador) throws IOException {
		if (cerrarNavegador) {
			Metodos.cerrarNavegador(Integer.parseInt(lecturaos[0]));
			Metodos.eliminarFichero("cerrar.bat");
		}
		try {
			if (opcion > 0 && !lecturaurl[0].isEmpty() && !lecturaurl[1].isEmpty()) {
				int salida;
				if (opcion == 9) {
					salida = Metodos.salida(lectura + "\\gif", "\\\\" + lecturaurl[0] + "\\" + opcion, opcion);
					Metodos.notificacion(salida, lectura + "gif", "GIF", true);
					salida = Metodos.salida(lectura + "\\gif\\Thumb", "\\\\" + lecturaurl[1] + "\\" + opcion, opcion);
					Metodos.notificacion(salida, lectura + "gif\\Thumb", "JPG", false);
					mensaje(salida + " imagen/es subida/s", false);
				} else {
					salida = Metodos.salida(lectura, "\\\\" + lecturaurl[0] + "\\" + opcion, opcion);
					Metodos.notificacion(salida, lectura, "JPG", true);
					salida = Metodos.salida(lectura + "\\Thumb", "\\\\" + lecturaurl[1] + "\\" + opcion, opcion);
					Metodos.notificacion(salida, lectura + "\\Thumb", "JPG", false);
					if (salida > 0) {
						mensaje(salida + " imagen/es subida/s", false);
					}
				}
			} else {
				new Config2().setVisible(true);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			new Config2().setVisible(true);
		}
	}

	public MenuPrincipal() throws IOException {

		if (lectura[0] == null) {
			Metodos.guardarConfig(1);
		}

		if (lecturaurl[0] == null) {
			Metodos.guardarConfig(2);
		}

		if (lecturaos[0] == null) {
			Metodos.guardarConfig(4);
		}

		try {
			lecturacategorias = Metodos.leerFicheroArray("Config/Categorias.txt", 1);
			if (lecturacategorias[0] == null) {
				Metodos.guardarConfig(3);
			}
		}

		catch (ArrayIndexOutOfBoundsException e) {
		}

		getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		File config = new File("Config/");
		if (!config.exists()) {
			config.mkdir();
		}
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/maxresdefault.jpg")));
		setResizable(false);
		setTitle("Periquito v3");
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
		mnGif = new JMenu("Imagen");
		mnGif.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnGif.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gif.png")));
		mnMenu.add(mnGif);
		mnGifAnimator = new JMenuItem("GIF Animator");
		mnGif.add(mnGifAnimator);
		mnGifAnimator.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				File af = new File("Config/Config.txt");
				if (af.exists()) {
					try {
						if (!lectura[0].isEmpty() && lectura[0] != null && !lectura[2].isEmpty() && lectura[2] != null
								&& !lectura[3].isEmpty() && lectura[3] != null) {
							creargif(lectura);
						} else {
							new Config().setVisible(true);
						}
					} catch (ArrayIndexOutOfBoundsException e1) {
						mensaje("Error en el archivo Config.txt", true);
					}
				}
			}
		});
		mnGifAnimator.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gifanim.png")));
		mnGifAnimator.setFont(new Font("Segoe UI", Font.BOLD, 18));
		separator_7 = new JSeparator();
		mnGif.add(separator_7);
		mnGifExtractor = new JMenuItem("GIF Extractor");
		mnGif.add(mnGifExtractor);
		mnGifExtractor.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				File af = new File("Config/Config.txt");
				if (af.exists()) {
					try {
						if (!lectura[4].isEmpty() && lectura[4] != null && !lectura[5].isEmpty()
								&& lectura[5] != null) {
							int comprobacion = 0;
							WebDriver firefox = new ChromeDriver();
							firefox.get(lectura[5] = convertirCadena(lectura[5], "firefox"));
							comprobacion = Integer.parseInt(firefox.findElement(By.name("salida")).getText());
							Metodos.cerrarNavegador(Integer.parseInt(lecturaos[0]));

							switch (comprobacion) {
							case 1:
								mensaje("Ya has convertido un gif a frames!", true);
								Metodos.abrirCarpeta(lectura[4] + "\\frames", true);
								break;
							case 2:
								mensaje("Debes de tener solo un archivo en la carpeta gif", true);
								Metodos.abrirCarpeta(lectura[4], true);
								break;
							case 3:
								mensaje("No hay 1 archivo gif para extraer", true);
								Metodos.abrirCarpeta(lectura[4], true);
								break;
							case 4:
								Metodos.eliminarDuplicados(lectura[0] + "\\..\\GifFrames\\frames");
								Metodos.abrirCarpeta(lectura[4] + "\\frames", true);
								break;
							}
						} else {
							new Config().setVisible(true);
						}
						Metodos.eliminarFichero("cerrar.bat");
					} catch (ArrayIndexOutOfBoundsException e1) {
						mensaje("Error en el archivo Config.txt", true);
					}
				}
			}
		});
		mnGifExtractor.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/carpeta.png")));
		mnGifExtractor.setFont(new Font("Segoe UI", Font.BOLD, 18));
		separator_6 = new JSeparator();
		mnMenu.add(separator_6);
		mnVideo = new JMenu("Video");
		mnVideo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnVideo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video2frames.png")));
		mnMenu.add(mnVideo);
		mntmNewMenuItem_7 = new JMenuItem("Video 2 Frame");
		mnVideo.add(mntmNewMenuItem_7);
		mntmNewMenuItem_7.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent arg0) {
				int comprobacion;
				try {
					if (lectura[1] == null || lectura[1].equals("")) {
						Config guardar = new Config();
						guardar.guardarDatos(false);
					}
					WebDriver firefox = new ChromeDriver();
					firefox.get(
							lectura[5] = convertirCadena(lectura[1] + "/FrameExtractor/examples/index.php", "firefox"));
					comprobacion = Integer.parseInt(firefox.findElement(By.name("salida")).getText());
					Metodos.cerrarNavegador(Integer.parseInt(lecturaos[0]));

					switch (comprobacion) {

					case 1:
						mensaje("Ya has convertido un video a frames!", true);
						Metodos.abrirCarpeta(lectura[0] + "\\..\\FrameExtractor\\examples\\output", true);
						break;

					case 2:
						mensaje("No tienes videos para convertir a frames", true);
						Metodos.abrirCarpeta(lectura[0] + "\\..\\FrameExtractor\\examples\\video", true);
						break;

					case 3:
						Metodos.eliminarDuplicados(lectura[0] + "\\..\\FrameExtractor\\examples\\output");
						Metodos.abrirCarpeta(lectura[0] + "\\..\\FrameExtractor\\examples\\output", true);
						break;
					}
					Metodos.eliminarFichero("cerrar.bat");
				}

				catch (ArrayIndexOutOfBoundsException e) {
					mensaje("Error en el archivo Config.txt", true);
				}
			}
		});
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmNewMenuItem_7.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video2frames.png")));
		separator_9 = new JSeparator();
		mnVideo.add(separator_9);
		mntmNewMenuItem_10 = new JMenuItem("Video 2 GIF");
		mnVideo.add(mntmNewMenuItem_10);
		mntmNewMenuItem_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String comprobacion = null;
				if (lectura[1] == null || lectura[1].equals("")) {
					Config guardar = new Config();
					guardar.guardarDatos(false);
				}
				WebDriver firefox = new ChromeDriver();
				firefox.get(lectura[5] = convertirCadena(lectura[1] + "/VID-2-GIF/index.php", "firefox"));
				comprobacion = firefox.findElement(By.name("salida")).getText();
				if (comprobacion.equals("No tienes videos")) {
					Metodos.cerrarNavegador(Integer.parseInt(lecturaos[0]));

					mensaje("Debes tener un video en la carpeta de conversion", true);
					Metodos.abrirCarpeta(lectura[0] + "\\..\\VID-2-GIF", true);
				} else {
					String imagen = firefox.findElement(By.name("imagen")).getText();
					Metodos.abrirCarpeta(lectura[0] + "\\..\\imagenes", true);
					if (!lectura[0].isEmpty() && !lectura[0].equals("") && lectura[0] != null) {
						firefox.get("file:///" + lectura[0] + "/" + imagen);
					}
				}
				Metodos.eliminarFichero("cerrar.bat");
			}
		});
		mntmNewMenuItem_10.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video2frames.png")));
		mntmNewMenuItem_10.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmUploads = new JMenuItem("Usuarios");
		mntmUploads.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmUploads.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/name.png")));
		mntmUploads.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				new AgendaInterfaz().setVisible(true);
			}
		});
		separator_11 = new JSeparator();
		mnMenu.add(separator_11);
		mnMenu.add(mntmUploads);
		mntmNewMenuItem_12 = new JMenuItem("Recomponer Inserts");
		mntmNewMenuItem_12.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				categorias = new ArrayList<String>();
				try {
					categorias = Metodos.verCategorias();

					new Utilidades().setVisible(true);
				} catch (SQLException | IOException e) {

					try {
						new Bd().setVisible(true);
					} catch (IOException e1) {
					}

				}
			}

		});
		separator_12 = new JSeparator();
		mnMenu.add(separator_12);
		mntmNewMenuItem_12.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/actualizar.png")));
		mntmNewMenuItem_12.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnMenu.add(mntmNewMenuItem_12);

		separator_13 = new JSeparator();
		mnMenu.add(separator_13);

		mnNewMenu = new JMenu("Base de datos");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/db.png")));
		mnMenu.add(mnNewMenu);

		mntmNewMenuItem_13 = new JMenuItem("Backup");
		mntmNewMenuItem_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Metodos.exportarBd(1);
				Metodos.eliminarFichero("backupbd.bat");
			}
		});
		mnNewMenu.add(mntmNewMenuItem_13);
		mntmNewMenuItem_13.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_13.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));

		separator_14 = new JSeparator();
		mnNewMenu.add(separator_14);

		mntmNewMenuItem_14 = new JMenuItem("Restaurar BD");
		mnNewMenu.add(mntmNewMenuItem_14);
		mntmNewMenuItem = new JMenu("   ");
		mntmNewMenuItem.setBackground(Color.BLACK);
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmNewMenuItem.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menuBar.add(mntmNewMenuItem);
		mntmImages = new JMenuItem("Imagenes");
		mntmImages.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmImages.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					comprobarConexion("Config/Config.txt", lectura[0]);
				} catch (ArrayIndexOutOfBoundsException e1) {
					mensaje("Error en el archivo Config.txt", true);
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
				try {
					comprobarConexion("Config/Config.txt", lectura[2]);
				} catch (ArrayIndexOutOfBoundsException e1) {
					mensaje("Error en el  archivo Config.txt", true);
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
				try {
					comprobarConexion("Config/Config.txt", lectura[4]);
				} catch (ArrayIndexOutOfBoundsException e1) {
					mensaje("Error en el  archivo Config.txt", true);
				}
			}
		});
		separator_2 = new JSeparator();
		mntmNewMenuItem.add(separator_2);
		mntmCxvxv.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/GIF_Extract.png")));
		mntmNewMenuItem.add(mntmCxvxv);
		mntmNewMenuItem_8 = new JMenuItem("Video 2 Frame");
		mntmNewMenuItem_8.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				try {
					comprobarConexion("Config/Config.txt", lectura[0] + "\\..\\FrameExtractor\\examples\\video");
				} catch (ArrayIndexOutOfBoundsException e) {
					mensaje("Error en el  archivo Config.txt", true);
				}
			}
		});
		separator_5 = new JSeparator();
		mntmNewMenuItem.add(separator_5);
		mntmNewMenuItem_8.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmNewMenuItem_8.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmNewMenuItem.add(mntmNewMenuItem_8);
		separator_8 = new JSeparator();
		mntmNewMenuItem.add(separator_8);
		mntmNewMenuItem_9 = new JMenuItem("Video 2 GIF");
		mntmNewMenuItem_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					comprobarConexion("Config/Config.txt", lectura[0] + "\\..\\VID-2-GIF\\");
				} catch (ArrayIndexOutOfBoundsException e) {
					mensaje("Error en el  archivo Config.txt", true);
				}
			}
		});
		mntmNewMenuItem_9.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmNewMenuItem_9.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmNewMenuItem.add(mntmNewMenuItem_9);
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
		separator_4 = new JSeparator();
		mntmNewMenuItem_1.add(separator_4);
		mntmNewMenuItem_11 = new JMenuItem("Conexion BD");
		mntmNewMenuItem_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					new Bd().setVisible(true);
				} catch (IOException e) {
				}
			}
		});
		mntmNewMenuItem_11.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem_11.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		mntmNewMenuItem_1.add(mntmNewMenuItem_11);

		separator_15 = new JSeparator();
		mntmNewMenuItem_1.add(separator_15);

		mntmNewMenuItem_15 = new JMenuItem("Backup DB");
		mntmNewMenuItem_15.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem_15.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		mntmNewMenuItem_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				new Backup().setVisible(true);
			}
		});
		mntmNewMenuItem_1.add(mntmNewMenuItem_15);

		separator_22 = new JSeparator();
		mntmNewMenuItem_1.add(separator_22);

		mnNewMenu_1 = new JMenu("OS");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mnNewMenu_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/os.png")));
		mntmNewMenuItem_1.add(mnNewMenu_1);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/Windows.png")));
		mnNewMenu_1.add(lblNewLabel_1);

		rdbtnNewRadioButton_1 = new JRadioButton("WIN");
		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					Metodos.crearFichero("Config/OS.txt", "1");
				} catch (IOException e) {
					mensaje("No se ha podido guardar el archivo OS.txt", true);
				}
			}
		});
		rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		mnNewMenu_1.add(rdbtnNewRadioButton_1);

		separator_17 = new JSeparator();
		mnNewMenu_1.add(separator_17);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/linux.png")));
		mnNewMenu_1.add(lblNewLabel_2);

		rdbtnNewRadioButton = new JRadioButton("Linux");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Metodos.crearFichero("Config/OS.txt", "2");
				} catch (IOException e1) {
					mensaje("No se ha podido guardar el archivo OS.txt", true);
				}
			}
		});
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		mnNewMenu_1.add(rdbtnNewRadioButton);
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
				Metodos.abrirCarpeta("https://demos.algorithmia.com/colorize-photos/", false);
			}
		});
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem_4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/30-07-2018 1-07-31.png")));
		mntmNewMenuItem_2.add(mntmNewMenuItem_4);
		initComponents();
		this.setVisible(true);
	}

	public void initComponents() throws IOException {
		jTextField1 = new javax.swing.JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.LEFT);
		jLabel1 = new javax.swing.JLabel();
		jLabel1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/name.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jComboBox1 = new javax.swing.JComboBox<>();
		// ponerCategorias();
		try {
			categorias = Metodos.verCategorias();
			Metodos.ponerCategoriasBd(jComboBox1);
		} catch (SQLException e3) {
			new Bd().setVisible(true);
		}

		jLabel2 = new javax.swing.JLabel();
		jLabel2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/tag.png")));
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		jTextField1.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/fix.png")));
		check5 = new JCheckBox("Fix");
		check5.setBounds(170, 170, 80, 60);
		check5.addChangeListener(this);
		check5.setFont(new Font("Tahoma", Font.BOLD, 25));
		getContentPane().add(check5);
		check6 = new Label("");
		check6.setEnabled(false);
		check6.setBounds(340, 240, 200, 40);
		check6.setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(check6);
		imagenes.setForeground(Color.DARK_GRAY);
		imagenes.setBackground(Color.WHITE);
		imagenes.setEditable(false);
		imagenes.setText("  Arrastra los archivos aqui");
		imagenes.setBounds(115, 295, 340, 60);
		imagenes.setFont(new Font("Tahoma", Font.BOLD, 24));
		getContentPane().add(imagenes);
		importar = new JLabel("");
		importar.setHorizontalAlignment(SwingConstants.CENTER);
		importar.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent arg0) {
				File[] files = Metodos.seleccionar(2, "Imagen & Video",
						"Elije un archivo de imagen o video para mover");
				if (files != null) {

					try {
						Metodos.moverArchivosDrop(files);
					} catch (IOException e) {
						mensaje("Error al mover archivos de imagen o video", true);
					}
				}

			}

		});
		importar.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/import.png")));
		importar.setBounds(30, 285, 70, 82);
		importar.setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(importar);
		mute = new JCheckBox("");
		mute.setVerticalAlignment(SwingConstants.BOTTOM);
		mute.setBounds(520, 295, 20, 20);
		mute.addChangeListener(this);
		mute.setFont(new java.awt.Font("Tahoma", 1, 18));
		getContentPane().add(mute);
		btnNewButton.setBounds(10, 100, 100, 30);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
		btnNewButton.setEnabled(false);

		if (!Metodos.probarconexion()) {
			mensaje("No hay conexión a internet", true);
		}
		Metodos.comprobarArchivo("Config");

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!validarURL(2)) {
					mensaje("La URL proporcionada no existe", true);
					new Config2().setVisible(true);
				} else {

					File config = new File("Config/Config.txt");
					File config2 = new File("Config/Config2.txt");
					if (config.exists() && config2.exists()) {

						if (Metodos.numeroLineas("Config.txt") == 0) {
							new Config().setVisible(true);
						} else {
							if (Metodos.numeroLineas("Config2.txt") == 0) {
								new Config2().setVisible(true);
							} else {
								try {
									String[] categorias = Metodos.leerFicheroArray("Config/Categorias.txt",
											Metodos.numeroLineas("Categorias.txt"));
									if (!lectura[0].isEmpty() && !lectura[1].isEmpty() && lectura[0] != null
											&& lectura[1] != null) {
										String cat = (String) jComboBox1.getSelectedItem();
										if (cat != null) {
											int opcion = Metodos.searchString(categorias, cat) + 1;
											try {
												lectura[0] = Metodos.eliminarUltimoElemento(lectura[0]);
												mover_imagenes(opcion, lectura[0], true);
											} catch (IOException e1) {
												mensaje("Error al copiar las imagenes", true);
												try {
													verConfiguraciones();
												} catch (IOException e2) {
												}
											}
										}
										check5.setSelected(false);
									} else {
										new Config().setVisible(true);
									}
								} catch (ArrayIndexOutOfBoundsException e1) {
									mensaje("Error en el  archivo Categorias.txt", true);
								}
							}
						}

					} else {

						if (!config.exists()) {
							new Config().setVisible(true);
						}
						if (!config2.exists()) {
							new Config2().setVisible(true);
						}
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/WAV_00002.png")));
		boton1 = new JButton("");
		boton1.setFont(new Font("Tahoma", Font.PLAIN, 6));
		boton1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/start.png")));
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Metodos.probarconexion()) {
					try {
						button1ActionPerformed(arg0);
					} catch (IOException e) {
					}
				}
			}
		});
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(11)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup().addComponent(lblNewLabel).addGap(62))
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
				.addGap(16).addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
				.addContainerGap()));
		getContentPane().setLayout(layout);
		setSize(new Dimension(573, 478));
		setLocationRelativeTo(null);
		javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder("Drop 'em");
		new DragAndDrop(System.out, imagenes, dragBorder, rootPaneCheckingEnabled, new DragAndDrop.Listener() {

			public void filesDropped(java.io.File[] files) {
				try {
					Metodos.moverArchivosDrop(files);
				} catch (IOException e) {
					mensaje("Error al mover los archivos", true);
				}
			}

		});
	}

	public Boolean validarURL(int tipo) {
		File url;
		boolean comprobacion = false;
		switch (tipo) {
		case 1:
			url = new File("Config/Config2.txt");
			if (url.exists()) {
				try {
					File archivo = new File("\\\\" + lecturaurl[0]);
					File archivo2 = new File("\\\\" + lecturaurl[1]);
					if (archivo.exists() && archivo2.exists()) {
						comprobacion = true;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					mensaje("Error en el  archivo Config2.txt", true);
				}
			}
			break;
		case 2:
			url = new File("Config/Config.txt");
			if (url.exists()) {
				try {
					File archivo = new File("\\\\" + lectura[0]);
					if (archivo.exists()) {
						comprobacion = true;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					mensaje("Error en el  archivo Config.txt", true);
				}
			}
			break;
		}
		return comprobacion;
	}

	@SuppressWarnings("static-access")
	private void button1ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {

		File af = new File("Config/Config.txt");
		if (!validarURL(2)) {
			mensaje("Error en la URL proporcionada o en la carpeta de imágenes", true);
			new Config2().setVisible(true);
			new Config().setVisible(true);
		} else {
			if (!validarURL(1)) {
				mensaje("Configuración errónea", true);
				new Config().setVisible(true);
			}
			if (af.exists()) {
				String[] categoriias;
				if (Metodos.numeroLineas("Categorias.txt") > 0) {
					categoriias = Metodos.leerFicheroArray("Config/Categorias.txt",
							Metodos.numeroLineas("Categorias.txt"));
					if (!lectura[0].isEmpty() && lectura[0] != null && !lectura[1].isEmpty() && lectura[1] != null
							&& !categoriias[0].isEmpty() && categoriias[0] != null) {
						String comprobacion = jTextField1.getText().trim();
						final File carpeta = new File(lectura[0]);

						if (Metodos.listarFicherosPorCarpeta(carpeta) <= 0) {
							mensaje("No hay imágenes en " + lectura[0], true);
							Metodos.abrirCarpeta(lectura[0], false);
						} else {
							if (comprobacion.length() != 0 && Metodos.listarFicherosPorCarpeta(carpeta) >= 1) {

								comprobacion = comprobacion.replace("  ", " ");
								comprobacion = comprobacion.replace("   ", " ");
								comprobacion = comprobacion.replace("  ", " ");
								comprobacion = comprobacion.replace("   ", " ");
								comprobacion = comprobacion.replace("  ", " ");
								int resultado = comprobacion.indexOf(" ");
								resultado = Integer.parseInt((comprobacion.valueOf(resultado + 1)));
								if (resultado > 0) {
									comprobacion = comprobacion.replaceAll("  ", " ");
								}
								String cat = (String) jComboBox1.getSelectedItem();
								int opcion = -1;
								lectura[1] = convertirCadena(lectura[1], "boton");
								WebDriver prueba = new ChromeDriver();
								prueba.get(lectura[1]);
								prueba.findElement(By.name("nombre")).sendKeys(comprobacion);
								Select drpCountry = new Select(prueba.findElement(By.name("categoria")));
								drpCountry.selectByVisibleText(cat);
								prueba.findElement(By.name("envio")).click();

								int vueltas = Integer.parseInt(prueba.findElement(By.name("vueltas")).getText());
								if (vueltas > 1) {
									check6.setEnabled(true);
									check6.setText("Nº de veces al play: " + vueltas--);
								}
								prueba.findElement(By.name("si")).click();
								opcion = Integer.parseInt(prueba.findElement(By.name("salida")).getText());
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
									mensaje("Error al mover las imagenes", true);
									verConfiguraciones();
								}

							} else {
								mensaje("Introduce un texto", true);
							}
						}
					} else {
						if ((lectura[0].isEmpty() || lectura[1].isEmpty())) {
							new Config().setVisible(true);
						} else {
							if (!af.exists()) {
								new Config().setVisible(true);
							} else {
								if (!af.exists()) {

									new Config().setVisible(true);

								}
							}
						}
					}
				}
			}
			if (check6.getText() != "") {
				int veces = Integer
						.parseInt(check6.getText().substring(check6.getText().length() - 1, check6.getText().length()));
				veces--;
				if (veces == 0) {
					check6.setText("");
				} else {
					check6.setText(check6.getText().substring(0, check6.getText().length() - 1) + veces);
				}
			}
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

	public interface Listener {
		public abstract void filesDropped(java.io.File[] files);
	}

	public void actionPerformed(ActionEvent arg0) {
	}
}