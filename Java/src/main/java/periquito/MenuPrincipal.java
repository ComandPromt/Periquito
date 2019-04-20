package periquito;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
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
import org.openqa.selenium.chrome.ChromeDriver;

import utils.DragAndDrop;
import utils.Metodos;
import utils.PhotoFrame;
import utils.interfaz;

@SuppressWarnings("serial")

public class MenuPrincipal extends javax.swing.JFrame implements ActionListener, ChangeListener, interfaz {
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu menu_1;
	private JMenuItem menuItem;
	private JSeparator separator;
	private JMenuItem menuItem_1;
	private JSeparator separator_1;
	private JMenu menu_2;
	private JMenuItem menuItem_2;
	private JSeparator separator_2;
	private JMenuItem menuItem_3;
	private JSeparator separator_3;
	private JMenu menu_3;
	private JMenuItem menuItem_4;
	private JSeparator separator_4;
	private JMenuItem menuItem_5;
	private JSeparator separator_5;
	private JMenuItem menuItem_6;
	private JSeparator separator_6;
	private JMenuItem menuItem_7;
	private JMenu menu_4;
	private JMenuItem menuItem_8;
	private JSeparator separator_7;
	private JMenuItem menuItem_9;
	private JSeparator separator_8;
	private JMenuItem menuItem_10;
	private JSeparator separator_9;
	private JMenuItem menuItem_11;
	private JSeparator separator_10;
	private JMenuItem menuItem_12;
	private JSeparator separator_11;
	private JMenuItem menuItem_13;
	private JMenu mnConfig;
	private JMenuItem menuItem_14;
	private JSeparator separator_12;
	private JMenuItem menuItem_15;
	private JSeparator separator_13;
	private JMenuItem menuItem_16;
	private JSeparator separator_14;
	private JMenuItem menuItem_17;
	private JSeparator separator_15;
	private JMenu menu_6;
	private JLabel label;
	private JRadioButton radioButton;
	private JSeparator separator_16;
	private JLabel label_1;
	private JRadioButton radioButton_1;
	private JMenu menu_7;
	private JSeparator separator_17;
	private JMenuItem menuItem_18;
	private JSeparator separator_18;
	private JMenuItem menuItem_19;
	private JTextField textField;
	@SuppressWarnings("all")
	private static String[] lectura = Metodos.leerFicheroArray("Config/Config.txt", 2);
	@SuppressWarnings("all")
	String[] lecturaurl = Metodos.leerFicheroArray("Config/Config2.txt", 2);
	@SuppressWarnings("all")
	static String[] lecturaos = Metodos.leerFicheroArray("Config/OS.txt", 1);
	String[] lecturabd = Metodos.leerFicheroArray("Config/Bd.txt", 6);
	String separador;
	String[] lecturabackup = Metodos.leerFicheroArray("Config/Backup.txt", 1);
	String directorioActual;
	static JComboBox<String> comboBox = new JComboBox<String>();

	public static String[] getLecturaos() {
		return lecturaos;
	}

	transient LinkedList<String> listaImagenes = new LinkedList<>();

	private void hacerGIF() {
		if (Metodos.listarFicherosPorCarpeta(new File(lectura[0] + "/Hacer_gif/img"), ".") <= 1) {
			Metodos.mensaje("Tienes que tener al menos 2 imágenes para crear un GIF", 3);
			Metodos.abrirCarpeta(lectura[0] + separador + "Hacer_gif" + separador + "img" + separador);
		} else {
			if (Metodos.listarFicherosPorCarpeta(new File(lectura[0] + "/Hacer_gif/img"), ".") > 163) {
				Metodos.mensaje("Has superado el límite de imágenes para crear un GIF", 3);
				Metodos.abrirCarpeta(lectura[0] + separador + "Hacer_gif" + separador + "img" + separador);
			} else {
				File af = new File("Config/Config.txt");

				if (af.exists()) {
					try {
						if (!lectura[0].isEmpty() && lectura[0] != null) {

							WebDriver chrome = new ChromeDriver();

							chrome.get(lectura[1] + "/Hacer_gif/crear_gif.php");

							Metodos.cerrarNavegador(Integer.parseInt(lecturaos[0]));

							chrome.close();

							LinkedList<String> frames = new LinkedList<String>();

							listaImagenes = Metodos
									.directorio(lectura[0] + separador + "Hacer_gif" + separador + "Output", "gif");
							frames = Metodos.directorio(lectura[0] + separador + "Hacer_gif" + separador + "img", ".");

							for (int x = 0; x < listaImagenes.size(); x++) {
								Files.move(
										FileSystems.getDefault()
												.getPath(lectura[0] + separador + "Hacer_gif" + separador + "Output"
														+ separador + listaImagenes.get(x)),
										FileSystems.getDefault().getPath(
												directorioActual + "imagenes" + separador + listaImagenes.get(x)),
										StandardCopyOption.REPLACE_EXISTING);
							}

							for (int x = 0; x < frames.size(); x++) {
								Metodos.eliminarFichero(lectura[0] + separador + "Hacer_gif" + separador + "img"
										+ separador + frames.get(x));
							}
							Metodos.abrirCarpeta("imagenes");
						} else {
							new Config().setVisible(true);
						}

					} catch (ArrayIndexOutOfBoundsException e1) {

						new Config().setVisible(true);
					} catch (IOException e1) {
						Metodos.mensaje("Error", 1);
					}

					catch (Exception e1) {
						Metodos.mensaje("No tienes el driver de chrome en la carpeta del programa", 1);
					}

				}
			}
		}
	}

	private void videoToFrame() {
		File directorio = new File(
				lectura[0] + separador + "FrameExtractor" + separador + "examples" + separador + "video");
		directorio.mkdir();
		directorio = new File(lectura[0] + separador + "FrameExtractor" + separador + "examples" + separador + "tmp");
		directorio.mkdir();
		directorio = new File(
				lectura[0] + separador + "FrameExtractor" + separador + "examples" + separador + "output");
		directorio.mkdir();

		try {
			if (lectura[1] == null || lectura[1].equals("")) {
				Config guardar = new Config();
				guardar.guardarDatos(false);
			} else {
				if (Metodos.listarFicherosPorCarpeta(new File(lectura[0] + "/FrameExtractor/examples/output"),
						".") > 0) {
					Metodos.mensaje("Ya has convertido un video a frames!", 3);
					Metodos.abrirCarpeta(
							lectura[0] + separador + "FrameExtractor" + separador + "examples" + separador + "output");
				}

				else {

					if (Metodos.listarFicherosPorCarpeta(new File(lectura[0] + "/FrameExtractor/examples/video"),
							".") != 1) {
						Metodos.mensaje("Debes tener un vídeo para poder crear los fotogramas", 3);
						Metodos.abrirCarpeta(lectura[0] + "/FrameExtractor/examples/video");
					}

					else {

						WebDriver chrome = new ChromeDriver();
						chrome.get(lectura[1] + "/FrameExtractor/examples/index.php");

						Metodos.cerrarNavegador(Integer.parseInt(lecturaos[0]));
						chrome.close();

						Metodos.eliminarDuplicados(lectura[0] + separador + "FrameExtractor" + separador + "examples"
								+ separador + "output", separador);
						Metodos.abrirCarpeta(lectura[0] + separador + "FrameExtractor" + separador + "examples"
								+ separador + "output");
					}

				}
			}
		}

		catch (ArrayIndexOutOfBoundsException e) {
			Metodos.mensaje("Error en el archivo Config.txt", 1);
		}
	}

	public MenuPrincipal() throws IOException {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/maxresdefault.jpg")));
		Metodos.crearFichero("GifFrames", "", true);
		if (separador == null && lecturaos[0] == null) {
			separador = "\\";
		} else {
			separador = Metodos.saberseparador(Integer.parseInt(lecturaos[0]));
		}

		Metodos.guardarConfig(3, separador);

		if (lectura[0] == null) {
			Metodos.guardarConfig(1, separador);
		}

		if (lecturaurl[0] == null) {
			Metodos.guardarConfig(2, separador);
		}

		if (lecturaos[0] == null) {
			Metodos.guardarConfig(4, separador);
		}

		if (lecturaos[0] != null) {
			separador = Metodos.saberseparador(Integer.parseInt(lecturaos[0]));
		}

		directorioActual = new File(".").getCanonicalPath() + separador;

		if (lecturabd[0] == null) {
			Metodos.crearFichero("Config/Bd.txt", "", false);
			new Bd().setVisible(true);
		}

		if (lecturabackup[0] == null && lecturaos[0] != null) {

			if (Integer.parseInt(lecturaos[0]) == 1) {
				Metodos.crearFichero("Config/Backup.txt", "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop",
						false);
			} else {
				Metodos.crearFichero("Config/Backup.txt", "1", false);

			}
		}
		setTitle("Periquito v3 Config Remoto");

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menu = new JMenu("             ");
		menu.setMnemonic(' ');
		menu.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/utilities.png")));
		menu.setForeground(Color.BLACK);
		menu.setFont(new Font("Segoe UI", Font.BOLD, 10));
		menu.setBackground(Color.BLACK);
		menuBar.add(menu);

		menu_1 = new JMenu("Imagen");
		menu_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gif.png")));
		menu_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu.add(menu_1);

		menuItem = new JMenuItem("GIF Animator");
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				hacerGIF();
			}
		});
		menuItem.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gifanim.png")));
		menuItem.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu_1.add(menuItem);

		separator = new JSeparator();
		menu_1.add(separator);

		menuItem_1 = new JMenuItem("GIF Extractor");
		menuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Metodos.probarconexion("www.google.com")) {
					if (Metodos.listarFicherosPorCarpeta(new File("GifFrames"), "gif") == 1) {

						WebDriver chrome = new ChromeDriver();
						chrome.get("https://gifframes.herokuapp.com");
						chrome.findElement(By.id("imagen"))
								.sendKeys(directorioActual + "GifFrames" + separador + "picture.gif");
						chrome.findElement(By.name("enviar")).click();
						Metodos.cerrarNavegador(Integer.parseInt(lecturaos[0]));

						Metodos.eliminarFichero(directorioActual + "GifFrames" + separador + "picture.gif");

					}

					else {

						Metodos.mensaje("Copia o mueve un archivo GIF", 3);

						Metodos.abrirCarpeta("GifFrames");
					}
				}
			}
		});
		menuItem_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/GIF_Extract.png")));
		menuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu_1.add(menuItem_1);

		separator_1 = new JSeparator();
		menu.add(separator_1);

		menu_2 = new JMenu("Video");
		menu_2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video_2_frame.png")));
		menu_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu.add(menu_2);

		menuItem_2 = new JMenuItem("Video 2 Frame");
		menuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				videoToFrame();
			}
		});
		menuItem_2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video2frames.png")));
		menuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu_2.add(menuItem_2);

		separator_2 = new JSeparator();
		menu_2.add(separator_2);

		menuItem_3 = new JMenuItem("Video 2 GIF");
		menuItem_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {

					videoToFrame();

					LinkedList<String> imagenes = new LinkedList<String>();
					imagenes = Metodos.directorio(
							lectura[0] + separador + "FrameExtractor" + separador + "examples" + separador + "output",
							".");
					File miDir = new File(".");
					for (int x = 0; x < imagenes.size(); x++) {

						Files.move(
								FileSystems.getDefault()
										.getPath(lectura[0] + separador + "FrameExtractor" + separador + "examples"
												+ separador + "output" + separador + imagenes.get(x)),
								FileSystems.getDefault().getPath(lectura[0] + separador + "Hacer_gif" + separador
										+ "img" + separador + imagenes.get(x)),
								StandardCopyOption.REPLACE_EXISTING);

					}

					hacerGIF();

				} catch (IOException e1) {
					new Config().setVisible(true);
				}
			}
		});
		menuItem_3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video_2_gif.gif")));
		menuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu_2.add(menuItem_3);

		separator_3 = new JSeparator();
		menu.add(separator_3);

		menu_3 = new JMenu("Base de datos");
		menu_3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/db.png")));
		menu_3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu.add(menu_3);

		menuItem_4 = new JMenuItem("Backup");
		menuItem_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					File archivo = new File("Config/Backup.txt");

					if (!archivo.exists()) {
						if (Integer.parseInt(lecturaos[0]) == 1) {
							Metodos.crearFichero("Config/Backup.txt",
									"C:\\Users\\" + System.getProperty("user.name") + "\\Desktop", false);
						} else {
							Metodos.crearFichero("Config/Backup.txt",
									"/home/" + System.getProperty("user.name") + "/Desktop", false);

						}
					} else {

						if (Metodos.comprobarConfiguracion()) {
							Metodos.exportarBd(1);
							Metodos.eliminarFichero("backupbd.bat");
							Metodos.eliminarFichero("reiniciar_explorer.bat");
						}

					}
				} catch (IOException e1) {
				}

			}
		});
		menuItem_4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		menuItem_4.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu_3.add(menuItem_4);

		separator_4 = new JSeparator();
		menu.add(separator_4);

		menuItem_5 = new JMenuItem("Notas");
		menuItem_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!Metodos.comprobarConexionBd()) {
					try {
						Metodos.mensaje("Compuebe que exista una tabla llamada notas en la base de datos", 3);
						new Bd().setVisible(true);
					} catch (IOException e1) {

					}

				} else {
					try {
						if (Metodos.comprobarConfiguracion()) {

							new AgendaInterfaz().setVisible(true);
						}
					} catch (IOException | SQLException e1) {
					}
				}
			}
		});
		menuItem_5.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/name.png")));
		menuItem_5.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu.add(menuItem_5);

		separator_5 = new JSeparator();
		menu.add(separator_5);

		menuItem_6 = new JMenuItem("Recomponer Inserts");
		menuItem_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if (Metodos.comprobarConfiguracion()) {
						try {
							new Utilidades().setVisible(true);
						} catch (IOException e1) {
						}
					}
				} catch (IOException e1) {
				}
			}
		});
		menuItem_6.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/actualizar.png")));
		menuItem_6.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu.add(menuItem_6);

		separator_6 = new JSeparator();
		menu.add(separator_6);

		menuItem_7 = new JMenuItem("Recortar imagenes");
		menuItem_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {

					listaImagenes = Metodos.directorio(directorioActual + "imagenes_para_recortar", ".");
				} catch (NumberFormatException e1) {
//
				}

				if (listaImagenes.size() > 0) {

					Metodos.eliminarDuplicados(directorioActual + "imagenes_para_recortar", separador);

					for (int x = 0; x < listaImagenes.size(); x++) {
						ImageResizer.copyImage(
								directorioActual + "imagenes_para_recortar" + separador + listaImagenes.get(x),
								directorioActual + "imagenes_para_recortar" + separador + listaImagenes.get(x));
					}

				}
				new PhotoFrame().setVisible(true);
			}
		});
		menuItem_7.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/crop.png")));
		menuItem_7.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu.add(menuItem_7);

		menu_4 = new JMenu("      ");
		menu_4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menu_4.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu_4.setBackground(Color.BLACK);
		menuBar.add(menu_4);

		menuItem_8 = new JMenuItem("Imagenes");
		menuItem_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				File directorio = new File(lectura[0] + separador + "Hacer_gif" + separador + "img");
				directorio.mkdir();
				directorio = new File(lectura[0] + separador + "Hacer_gif" + separador + "Output");
				directorio.mkdir();

				Metodos.abrirCarpeta("imagenes");
			}
		});
		menuItem_8.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menuItem_8.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu_4.add(menuItem_8);

		separator_7 = new JSeparator();
		menu_4.add(separator_7);

		menuItem_9 = new JMenuItem("GIF Animator");
		menuItem_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setLectura(Metodos.leerFicheroArray("Config/Config.txt", 2));
				Metodos.comprobarConexion("Config/Config.txt",
						lectura[0] + separador + "Hacer_gif" + separador + "img");

			}
		});
		menuItem_9.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gif.png")));
		menuItem_9.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu_4.add(menuItem_9);

		separator_8 = new JSeparator();
		menu_4.add(separator_8);

		menuItem_10 = new JMenuItem("GIF Extractor");
		menuItem_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Metodos.abrirCarpeta("GifFrames");
			}
		});
		menuItem_10.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/GIF_Extract.png")));
		menuItem_10.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu_4.add(menuItem_10);

		separator_9 = new JSeparator();
		menu_4.add(separator_9);

		menuItem_11 = new JMenuItem("Video 2 Frame");
		menuItem_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Metodos.comprobarConexion("Config/Config.txt",
							lectura[0] + separador + "FrameExtractor" + separador + "examples" + separador + "video");
				} catch (ArrayIndexOutOfBoundsException e1) {
					Metodos.mensaje("Error en el  archivo Config.txt", 1);
				}
			}
		});
		menuItem_11.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menuItem_11.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu_4.add(menuItem_11);

		separator_10 = new JSeparator();
		menu_4.add(separator_10);

		menuItem_12 = new JMenuItem("Video 2 GIF");
		menuItem_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Metodos.comprobarConexion("Config/Config.txt", lectura[0] + separador + "VID-2-GIF");

				} catch (ArrayIndexOutOfBoundsException e1) {
					Metodos.mensaje("Error en el  archivo Config.txt", 1);
				}
			}
		});
		menuItem_12.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menuItem_12.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu_4.add(menuItem_12);

		separator_11 = new JSeparator();
		menu_4.add(separator_11);

		menuItem_13 = new JMenuItem("Crop images");
		menuItem_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Metodos.abrirCarpeta("imagenes_para_recortar");
			}
		});
		menuItem_13.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menuItem_13.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu_4.add(menuItem_13);

		mnConfig = new JMenu("Config    ");
		mnConfig.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/fix.png")));
		mnConfig.setForeground(Color.DARK_GRAY);
		mnConfig.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mnConfig.setBackground(Color.BLACK);
		menuBar.add(mnConfig);

		menuItem_14 = new JMenuItem("Local");
		menuItem_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new Config().setVisible(true);
			}
		});
		menuItem_14.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/config.png")));
		menuItem_14.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mnConfig.add(menuItem_14);

		separator_12 = new JSeparator();
		mnConfig.add(separator_12);

		menuItem_15 = new JMenuItem("Remoto");
		menuItem_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new Config2().setVisible(true);
			}
		});
		menuItem_15.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/remote.png")));
		menuItem_15.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mnConfig.add(menuItem_15);

		separator_13 = new JSeparator();
		mnConfig.add(separator_13);

		menuItem_16 = new JMenuItem("Conexion BD");
		menuItem_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new Bd().setVisible(true);
				} catch (IOException e1) {
				}
			}
		});
		menuItem_16.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		menuItem_16.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mnConfig.add(menuItem_16);

		separator_14 = new JSeparator();
		mnConfig.add(separator_14);

		menuItem_17 = new JMenuItem("Backup DB");
		menuItem_17.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new Backup().setVisible(true);
			}
		});
		menuItem_17.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		menuItem_17.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mnConfig.add(menuItem_17);

		separator_15 = new JSeparator();
		mnConfig.add(separator_15);

		menu_6 = new JMenu("OS");
		menu_6.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/os.png")));
		menu_6.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mnConfig.add(menu_6);

		label = new JLabel("");
		label.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/Windows.png")));
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		menu_6.add(label);

		radioButton = new JRadioButton("WIN");
		radioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Metodos.crearFichero("Config/OS.txt", "1", false);
					separador = Metodos.saberseparador(Integer.parseInt(lecturaos[0]));
					directorioActual = new File(".").getCanonicalPath() + separador;
				} catch (IOException e1) {
					Metodos.mensaje("No se ha podido guardar el archivo OS.txt", 1);
				}
			}
		});
		radioButton.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		menu_6.add(radioButton);

		separator_16 = new JSeparator();
		menu_6.add(separator_16);

		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/linux.png")));
		menu_6.add(label_1);

		radioButton_1 = new JRadioButton("Linux");
		radioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Metodos.crearFichero("Config/OS.txt", "2", false);
					separador = Metodos.saberseparador(Integer.parseInt(lecturaos[0]));
					directorioActual = new File(".").getCanonicalPath() + separador;
				} catch (IOException e1) {
					Metodos.mensaje("No se ha podido guardar el archivo OS.txt", 1);
				}
			}
		});
		radioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		menu_6.add(radioButton_1);

		menu_7 = new JMenu("Ayuda");
		menu_7.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/help.png")));
		menu_7.setForeground(Color.DARK_GRAY);
		menu_7.setFont(new Font("Segoe UI", Font.BOLD, 24));
		menu_7.setBackground(Color.BLACK);
		menuBar.add(menu_7);

		separator_17 = new JSeparator();
		menu_7.add(separator_17);

		menuItem_18 = new JMenuItem("IMG 2 Color");
		menuItem_18.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Metodos.abrirCarpeta("https://demos.algorithmia.com/colorize-photos/");
			}
		});
		menuItem_18.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/30-07-2018 1-07-31.png")));
		menuItem_18.setFont(new Font("Segoe UI", Font.BOLD, 24));
		menu_7.add(menuItem_18);

		separator_18 = new JSeparator();
		menu_7.add(separator_18);

		menuItem_19 = new JMenuItem("Sobre");
		menuItem_19.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new About().setVisible(true);
			}
		});
		menuItem_19.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/about.png")));
		menuItem_19.setFont(new Font("Segoe UI", Font.BOLD, 24));
		menu_7.add(menuItem_19);

		if (!Metodos.probarconexion("www.google.com")) {
			Metodos.mensaje("No hay conexión a internet", 3);
		}
		Metodos.comprobarArchivo("Config", false);

		initComponents();

		this.setVisible(true);
	}

	public void initComponents() throws IOException {

		Metodos.crearCarpetas();

		if (Metodos.comprobarArchivo("Config/Config.txt", true)) {

			Metodos.guardarConfig(5, separador);
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JLabel label_2 = new JLabel();
		label_2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/name.png")));

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(new Font("Tahoma", Font.BOLD, 24));

		JLabel label_3 = new JLabel();
		label_3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/tag.png")));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);

		JButton button = new JButton("");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (comboBox.getSelectedIndex() == -1) {
					try {
						new Bd().setVisible(true);
					} catch (IOException e1) {
						Metodos.mensaje("Error", 1);
					}
				} else {
					if (!textField.getText().trim().isEmpty()) {

						listaImagenes = Metodos.directorio("imagenes", ".");
						String parametros = "";
						StringBuilder bld = new StringBuilder();
						if (!listaImagenes.isEmpty()) {

							for (int i = 0; i < listaImagenes.size(); i++) {
								if (listaImagenes.size() == 1 || i + 1 == listaImagenes.size()) {
									bld.append(i + ".jpg");

								} else {

									bld.append(i + ".jpg,");
								}
							}
						}
						parametros = bld.toString();

						if (!parametros.isEmpty()) {

							JSONObject json;
							try {

								Metodos.eliminarDuplicados(directorioActual + "imagenes", separador);

								Connection conexion = Metodos.conexionBD();
								Statement s = conexion.createStatement();
								ResultSet rs;
								rs = s.executeQuery("SELECT MAX(image_id)+1 as maximo FROM " + lecturabd[3]
										+ "images order by image_id");
								rs.next();

								int maximo = Integer.parseInt(rs.getString("maximo"));
								int categoria = comboBox.getSelectedIndex() + 1;

								rs = s.executeQuery("SELECT cat_parent_id FROM  " + lecturabd[3]
										+ "categories WHERE cat_id=" + categoria);
								rs.next();

								String catParent = rs.getString("cat_parent_id");

								json = Metodos.readJsonFromUrl(
										"https://apiperiquito.herokuapp.com/recibo-json.php?imagenes=" + parametros);

								JSONArray imagenesBD = json.getJSONArray("imagenes_bd");

								listaImagenes = Metodos.directorio(directorioActual + "imagenes", ".");

								for (int i = 0; i < imagenesBD.length(); i++) {

									s.executeUpdate("INSERT INTO " + lecturabd[3] + "images VALUES(" + maximo + ","
											+ categoria + "," + catParent + ",1,'" + textField.getText().trim()
											+ "','','','" + Metodos.saberFecha() + "',1,'"
											+ imagenesBD.get(i).toString() + "',1,0,0,0,DEFAULT,0,'"
											+ Metodos.getSHA256Checksum(
													directorioActual + "imagenes" + separador + listaImagenes.get(i))
											+ "')");
									maximo++;

									Files.move(
											FileSystems.getDefault().getPath(
													directorioActual + "imagenes" + separador + listaImagenes.get(i)),
											FileSystems.getDefault()
													.getPath("\\\\" + lecturaurl[0] + separador + lecturaurl[1]
															+ separador + "data" + separador + "media" + separador
															+ comboBox.getSelectedIndex() + 1 + separador
															+ imagenesBD.get(i).toString()),
											StandardCopyOption.REPLACE_EXISTING);

								}

								s.close();
								rs.close();

							} catch (Exception e1) {
								Metodos.mensaje("Error", 1);
							}

						}
					} else {
						if (textField.getText().trim().isEmpty()) {
							Metodos.mensaje("Introduce un nombre común para las imágenes", 3);
						} else {
							Metodos.abrirCarpeta(directorioActual + "imagenes");
						}
					}
				}
			}
		});
		button.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/start.png")));
		button.setFont(new Font("Tahoma", Font.PLAIN, 6));

		JTextArea imagenes = new JTextArea();
		imagenes.setText("         Arrastra los archivos aqui");
		imagenes.setForeground(Color.DARK_GRAY);
		imagenes.setFont(new Font("Tahoma", Font.BOLD, 24));
		imagenes.setEditable(false);
		imagenes.setBackground(Color.WHITE);

		JLabel label_4 = new JLabel("");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				File[] files = Metodos.seleccionar(2, "Imagen & Video",
						"Elije un archivo de imagen o video para mover");
				if (files != null) {

					try {
						Metodos.moverArchivosDrop(files, separador);
					} catch (IOException e1) {
						Metodos.mensaje("Error al mover archivos de imagen o video", 1);
					}
				}
			}
		});
		label_4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/import.png")));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 18));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(51).addComponent(label_2).addGap(46)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 293,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 293,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup().addGap(40).addComponent(label_3,
								GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(100, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, layout.createSequentialGroup().addContainerGap()
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(imagenes, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(31, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup().addContainerGap(174, Short.MAX_VALUE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
						.addGap(113)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(29).addComponent(textField,
										GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(label_2,
										GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(21).addComponent(comboBox,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup().addGap(9)
										.addComponent(button, GroupLayout.PREFERRED_SIZE, 71,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE).addComponent(
												imagenes, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(label_4)))
						.addGap(22)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(560, 438));
		setLocationRelativeTo(null);
		try {
			if (Metodos.comprobarConexion()) {

				Metodos.ponerCategoriasBd(comboBox);
			}
		} catch (SQLException e3) {
			button.setEnabled(false);
		}

		javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder("Drop 'em");
		new DragAndDrop(imagenes, dragBorder, rootPaneCheckingEnabled, new DragAndDrop.Listener() {

			public void filesDropped(java.io.File[] files) {
				try {
					Metodos.moverArchivosDrop(files, separador);
				} catch (IOException e) {
					Metodos.mensaje("Error al mover los archivos", 1);
				}
			}

		});
	}

	public static String[] getLectura() {
		return lectura;
	}

	public static void setLectura(String[] lectura) {
		MenuPrincipal.lectura = lectura;
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
