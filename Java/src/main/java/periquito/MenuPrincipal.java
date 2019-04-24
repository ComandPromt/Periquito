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
import java.util.TooManyListenersException;

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
import utils.ImageResizer;
import utils.Metodos;
import utils.MyInterface;
import utils.PhotoFrame;

@SuppressWarnings("all")

public class MenuPrincipal extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	private JMenuBar menuopciones;
	private JMenu menu;
	private JMenu menu1;
	private JMenuItem menuItem;
	private JSeparator separator;
	private JMenuItem menuItem1;
	private JSeparator separator1;
	private JMenu menu2;
	private JMenuItem menuItem2;
	private JSeparator separator2;
	private JMenuItem menuItem3;
	private JSeparator separator3;
	private JMenu menu3;
	private JMenuItem menuItem4;
	private JSeparator separator4;
	private JMenuItem menuItem5;
	private JSeparator separator5;
	private JMenuItem menuItem6;
	private JSeparator separator6;
	private JMenuItem menuItem7;
	private JMenu menu4;
	private JMenuItem menuItem8;
	private JSeparator separator7;
	private JMenuItem menuItem9;
	private JSeparator separator8;
	private JMenuItem menuItem10;
	private JSeparator separator9;
	private JMenuItem menuItem11;
	private JSeparator separator10;
	private JMenuItem menuItem12;
	private JSeparator separator11;
	private JMenuItem menuItem13;
	private JMenu mnConfig;
	private JMenuItem menuItem14;
	private JSeparator separator12;
	private JMenuItem menuItem15;
	private JSeparator separator13;
	private JMenuItem menuItem16;
	private JSeparator separator14;
	private JMenuItem menuItem17;
	private JSeparator separator15;
	private JMenu menu6;
	private JLabel label;
	private JRadioButton radioButton;
	private JSeparator separator16;
	private JLabel label1;
	private JRadioButton radioButton1;
	private JMenu menu7;
	private JSeparator separator17;
	private JMenuItem menuItem18;
	private JSeparator separator18;
	private JMenuItem menuItem19;
	public static String getOs() {
		return os;
	}

	private JTextField textField;
static String os=System.getProperty("os.name");
	private static String[] lectura = Metodos.leerFicheroArray("Config/Config.txt", 2);

	static String separador = Metodos.saberSeparador(os);

	static String[] lecturaurl = Metodos.leerFicheroArray("Config/Config2.txt", 2);

	static String[] lecturabd = Metodos.leerFicheroArray("Config/Bd.txt", 6);

	public static String[] getLecturabd() {
		return lecturabd;
	}


	@SuppressWarnings("all")
	String[] lecturabackup = Metodos.leerFicheroArray("Config/Backup.txt", 1);
	String directorioActual;
	static JComboBox<String> comboBox = new JComboBox<>();

	public static String[] getLecturaos() {
		return lecturaos;
	}

	transient LinkedList<String> listaImagenes = new LinkedList<>();

	private void hacerGIF() throws IOException {
		try {
			if (Metodos.listarFicherosPorCarpeta(new File(lectura[0] + "/Hacer_gif/img"), ".") <= 1) {
				Metodos.mensaje("Tienes que tener al menos 2 imágenes para crear un GIF", 3);
				Metodos.abrirCarpeta(lectura[0] + separador + "Hacer_gif" + separador + "img" + separador,lecturaos[0]);
			} else {
				if (Metodos.listarFicherosPorCarpeta(new File(lectura[0] + "/Hacer_gif/img"), ".") > 163) {
					Metodos.mensaje("Has superado el límite de imágenes para crear un GIF", 3);
					Metodos.abrirCarpeta(lectura[0] + separador + "Hacer_gif" + separador + "img" + separador,lecturaos[0]);
				} else {
					File af = new File("Config/Config.txt");

					if (af.exists()) {
						try {
							if (!lectura[0].isEmpty() && lectura[0] != null) {

								WebDriver chrome = new ChromeDriver();

								chrome.get(lectura[1] + "/Hacer_gif/crear_gif.php");

								Metodos.cerrarNavegador(Integer.parseInt(lecturaos[0]));

								chrome.close();

								listaImagenes = Metodos
										.directorio(lectura[0] + separador + "Hacer_gif" + separador + "Output", "gif");
								LinkedList<String> frames = Metodos
										.directorio(lectura[0] + separador + "Hacer_gif" + separador + "img", ".");

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
								Metodos.abrirCarpeta("imagenes",lecturaos[0]);
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
		} catch (Exception e) {
			new Config().setVisible(true);
		}
	}

	private void video2Frames() {
		try {
			Metodos.comprobarConexion("Config/Config.txt",
					lectura[0] + separador + "FrameExtractor" + separador + "examples" + separador + "video");
		} catch (ArrayIndexOutOfBoundsException e1) {
			Metodos.mensaje("Error en el  archivo Config.txt", 1);
		} catch (Exception e1) {
			Metodos.mensaje("Error", 1);
		}
	}
	
	private void videoToFrame() throws Exception {
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
							lectura[0] + separador + "FrameExtractor" + separador + "examples" + separador + "output",lecturaos[0]);
				}

				else {

					if (Metodos.listarFicherosPorCarpeta(new File(lectura[0] + "/FrameExtractor/examples/video"),
							".") != 1) {
						Metodos.mensaje("Debes tener un vídeo para poder crear los fotogramas", 3);
						Metodos.abrirCarpeta(lectura[0] + "/FrameExtractor/examples/video",lecturaos[0]);
					}

					else {

						WebDriver chrome = new ChromeDriver();
						chrome.get(lectura[1] + "/FrameExtractor/examples/index.php");

						Metodos.cerrarNavegador(Integer.parseInt(lecturaos[0]));
						chrome.close();

						Metodos.eliminarDuplicados(lectura[0] + separador + "FrameExtractor" + separador + "examples"
								+ separador + "output", separador);
						Metodos.abrirCarpeta(lectura[0] + separador + "FrameExtractor" + separador + "examples"
								+ separador + "output",lecturaos[0]);
					}

				}
			}
		}

		catch (ArrayIndexOutOfBoundsException e) {
			Metodos.mensaje("Error en el archivo Config.txt", 1);
		}
	}

	public MenuPrincipal() throws Exception {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/maxresdefault.jpg")));
		Metodos.crearFichero("GifFrames", "", true);



			separador = Metodos.saberseparador(Integer.parseInt(lecturaos[0]));
		}

		Metodos.guardarConfig(3, separador);

		if (lectura[0] == null || lectura[0].equals("")) {
			Metodos.guardarConfig(1, separador);
		}

		if (lecturaurl[0] == null || lecturaurl[0].equals("")) {
			Metodos.guardarConfig(2, separador);
		}

		if (lecturaos[0] == null || lecturaos[0].equals("")) {
			Metodos.guardarConfig(4, separador);
		}

		if (lecturaos[0] == null || lecturaos[0].equals("")) {
			lecturaos[0] = "1";
			separador = Metodos.saberseparador(Integer.parseInt(lecturaos[0]));
		}

		directorioActual = new File(".").getCanonicalPath() + separador;

		if (lecturabd[0] == null || lecturabd[0].equals("")) {
			Metodos.crearFichero("Config/Bd.txt", "", false);
			new Bd().setVisible(true);
		}

		if (lecturabackup[0] == null || lecturabackup[0].equals("")) {
			if (Integer.parseInt(lecturaos[0]) == 1) {
				Metodos.crearFichero("Config/Backup.txt", "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop",
						false);
			} else {
				Metodos.crearFichero("Config/Backup.txt", "1", false);

			}
		}
		setTitle("Periquito v3");

		menuopciones = new JMenuBar();
		setJMenuBar(menuopciones);

		menu = new JMenu("       ");
		menu.setMnemonic(' ');
		menu.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/utilities.png")));
		menu.setForeground(Color.BLACK);
		menu.setFont(new Font("Segoe UI", Font.BOLD, 10));
		menu.setBackground(Color.BLACK);
		menuopciones.add(menu);

		menu1 = new JMenu("Imagen");
		menu1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gif.png")));
		menu1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu.add(menu1);

		menuItem = new JMenuItem("GIF Animator");
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					hacerGIF();
				} catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});
		menuItem.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gifanim.png")));
		menuItem.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu1.add(menuItem);

		separator = new JSeparator();
		menu1.add(separator);

		menuItem1 = new JMenuItem("GIF Extractor");
		menuItem1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
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

							Metodos.abrirCarpeta("GifFrames",lecturaos[0]);
						}
					}
				} catch (Exception e1) {
					Metodos.mensaje("Comprueba tu conexión", 1);
				}
			}
		});
		menuItem1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/GIF_Extract.png")));
		menuItem1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu1.add(menuItem1);

		separator1 = new JSeparator();
		menu.add(separator1);

		menu2 = new JMenu("Video");
		menu2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video_2_frame.png")));
		menu2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu.add(menu2);

		menuItem2 = new JMenuItem("Video 2 Frame");
		menuItem2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					videoToFrame();
				} catch (Exception e) {
					try {
						new Config().setVisible(true);
					} catch (IOException e1) {
						//
					}
				}
			}
		});
		menuItem2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video2frames.png")));
		menuItem2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu2.add(menuItem2);

		separator2 = new JSeparator();
		menu2.add(separator2);

		menuItem3 = new JMenuItem("Video 2 GIF");
		menuItem3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {

					try {
						videoToFrame();
					} catch (Exception e1) {

					}

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
					try {
						new Config().setVisible(true);
					} catch (IOException e2) {
						Metodos.mensaje("Error", 1);
					}
				}
			}
		});
		menuItem3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video_2_gif.gif")));
		menuItem3.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu2.add(menuItem3);

		separator3 = new JSeparator();
		menu.add(separator3);

		menu3 = new JMenu("Base de datos");
		menu3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/db.png")));
		menu3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu.add(menu3);

		menuItem4 = new JMenuItem("Backup");
		menuItem4.addMouseListener(new MouseAdapter() {
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
						}

					}
				} catch (IOException e1) {
				}

			}
		});
		menuItem4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		menuItem4.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu3.add(menuItem4);

		separator4 = new JSeparator();
		menu.add(separator4);

		menuItem5 = new JMenuItem("Notas");
		menuItem5.addMouseListener(new MouseAdapter() {
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
		menuItem5.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/name.png")));
		menuItem5.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu.add(menuItem5);

		separator5 = new JSeparator();
		menu.add(separator5);

		menuItem6 = new JMenuItem("Recomponer Inserts");
		menuItem6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if (Metodos.comprobarConfiguracion()) {

						new Utilidades().setVisible(true);

					}
				} catch (Exception e1) {
					//
				}
			}
		});
		menuItem6.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/actualizar.png")));
		menuItem6.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu.add(menuItem6);

		separator6 = new JSeparator();
		menu.add(separator6);

		menuItem7 = new JMenuItem("Recortar imagenes");
		menuItem7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {

					listaImagenes = Metodos.directorio(directorioActual + "imagenes_para_recortar", ".");
				} catch (NumberFormatException e1) {
//
				}

				if (!listaImagenes.isEmpty()) {

					try {
						Metodos.eliminarDuplicados(directorioActual + "imagenes_para_recortar", separador);

						for (int x = 0; x < listaImagenes.size(); x++) {
							ImageResizer.copyImage(
									directorioActual + "imagenes_para_recortar" + separador + listaImagenes.get(x),
									directorioActual + "imagenes_para_recortar" + separador + listaImagenes.get(x));
						}
					} catch (Exception e1) {
						Metodos.mensaje("Error", 1);
					}

				}
				new PhotoFrame().setVisible(true);
			}
		});
		menuItem7.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/crop.png")));
		menuItem7.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu.add(menuItem7);

		menu4 = new JMenu("    ");
		menu4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menu4.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu4.setBackground(Color.BLACK);
		menuopciones.add(menu4);

		menuItem8 = new JMenuItem("Imagenes");
		menuItem8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				File directorio = new File(lectura[0] + separador + "Hacer_gif" + separador + "img");
				directorio.mkdir();
				directorio = new File(lectura[0] + separador + "Hacer_gif" + separador + "Output");
				directorio.mkdir();

				try {
					Metodos.abrirCarpeta("imagenes",lecturaos[0]);
				} catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});
		menuItem8.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menuItem8.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu4.add(menuItem8);

		separator7 = new JSeparator();
		menu4.add(separator7);

		menuItem9 = new JMenuItem("GIF Animator");
		menuItem9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					setLectura(Metodos.leerFicheroArray("Config/Config.txt", 2));
					Metodos.comprobarConexion("Config/Config.txt",
							lectura[0] + separador + "Hacer_gif" + separador + "img");
				} catch (Exception e1) {
					Metodos.mensaje("Error", 1);
				}

			}
		});
		menuItem9.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gif.png")));
		menuItem9.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu4.add(menuItem9);

		separator8 = new JSeparator();
		menu4.add(separator8);

		menuItem10 = new JMenuItem("GIF Extractor");
		menuItem10.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Metodos.abrirCarpeta("GifFrames",lecturaos[0]);
				} catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});
		menuItem10.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/GIF_Extract.png")));
		menuItem10.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu4.add(menuItem10);

		separator9 = new JSeparator();
		menu4.add(separator9);

		menuItem11 = new JMenuItem("Video 2 Frame");
		menuItem11.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				video2Frames();
			}


		});
		menuItem11.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menuItem11.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu4.add(menuItem11);

		separator10 = new JSeparator();
		menu4.add(separator10);

		menuItem12 = new JMenuItem("Video 2 GIF");
		menuItem12.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				video2Frames();
			}
		});
		menuItem12.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menuItem12.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu4.add(menuItem12);

		separator11 = new JSeparator();
		menu4.add(separator11);

		menuItem13 = new JMenuItem("Crop images");
		menuItem13.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Metodos.abrirCarpeta("imagenes_para_recortar",lecturaos[0]);
				} catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});
		menuItem13.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menuItem13.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu4.add(menuItem13);

		mnConfig = new JMenu("Config  ");
		mnConfig.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/fix.png")));
		mnConfig.setForeground(Color.DARK_GRAY);
		mnConfig.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mnConfig.setBackground(Color.BLACK);
		menuopciones.add(mnConfig);

		menuItem14 = new JMenuItem("Local");
		menuItem14.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new Config().setVisible(true);
				} catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});
		menuItem14.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/config.png")));
		menuItem14.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mnConfig.add(menuItem14);

		separator12 = new JSeparator();
		mnConfig.add(separator12);

		menuItem15 = new JMenuItem("Remoto");
		menuItem15.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new Config2().setVisible(true);
				} catch (IOException e1) {
					Metodos.mensaje("No se ha podidio abrir la configuración remota", 1);
				}
			}
		});
		menuItem15.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/remote.png")));
		menuItem15.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mnConfig.add(menuItem15);

		separator13 = new JSeparator();
		mnConfig.add(separator13);

		menuItem16 = new JMenuItem("Conexion BD");
		menuItem16.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new Bd().setVisible(true);
				} catch (IOException e1) {
					//
				}
			}
		});
		menuItem16.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		menuItem16.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mnConfig.add(menuItem16);

		separator14 = new JSeparator();
		mnConfig.add(separator14);

		menuItem17 = new JMenuItem("Backup DB");
		menuItem17.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new Backup().setVisible(true);
				} catch (IOException e1) {
					Metodos.mensaje("Error al cargar la utilidad", 1);
				}
			}
		});
		menuItem17.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		menuItem17.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mnConfig.add(menuItem17);

		separator15 = new JSeparator();
		mnConfig.add(separator15);

		menu6 = new JMenu("OS");
		menu6.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/os.png")));
		menu6.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mnConfig.add(menu6);

		label = new JLabel("");
		label.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/Windows.png")));
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		menu6.add(label);

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
		menu6.add(radioButton);

		separator16 = new JSeparator();
		menu6.add(separator16);

		label1 = new JLabel("");
		label1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/linux.png")));
		menu6.add(label1);

		radioButton1 = new JRadioButton("Linux");
		radioButton1.addMouseListener(new MouseAdapter() {
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
		radioButton1.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton1.setFont(new Font("Tahoma", Font.BOLD, 14));
		menu6.add(radioButton1);

		menu7 = new JMenu("Ayuda");
		menu7.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/help.png")));
		menu7.setForeground(Color.DARK_GRAY);
		menu7.setFont(new Font("Segoe UI", Font.BOLD, 24));
		menu7.setBackground(Color.BLACK);
		menuopciones.add(menu7);

		separator17 = new JSeparator();
		menu7.add(separator17);

		menuItem18 = new JMenuItem("IMG 2 Color");
		menuItem18.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Metodos.abrirCarpeta("https://demos.algorithmia.com/colorize-photos/",lecturaos[0]);
				} catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});
		menuItem18.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/30-07-2018 1-07-31.png")));
		menuItem18.setFont(new Font("Segoe UI", Font.BOLD, 24));
		menu7.add(menuItem18);

		separator18 = new JSeparator();
		menu7.add(separator18);

		menuItem19 = new JMenuItem("Sobre");
		menuItem19.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new About().setVisible(true);
			}
		});
		menuItem19.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/about.png")));
		menuItem19.setFont(new Font("Segoe UI", Font.BOLD, 24));
		menu7.add(menuItem19);

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

			try {
				Metodos.guardarConfig(5, separador);
			} catch (IOException e1) {
				Metodos.mensaje("Error al guardar la configuración", 1);
			}
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JLabel label2 = new JLabel();
		label2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/name.png")));

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(new Font("Tahoma", Font.BOLD, 24));

		JLabel label3 = new JLabel();
		label3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/tag.png")));
		label3.setHorizontalAlignment(SwingConstants.CENTER);

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
							try {
								Metodos.abrirCarpeta(directorioActual + "imagenes",lecturaos[0]);
							} catch (IOException e1) {
								Metodos.mensaje("Error", 1);
							}
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

		JLabel label4 = new JLabel("");
		label4.addMouseListener(new MouseAdapter() {
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
		label4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/import.png")));
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setFont(new Font("Tahoma", Font.BOLD, 18));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(51).addComponent(label2).addGap(46)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 293,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 293,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup().addGap(40).addComponent(label3,
								GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(100, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(label4, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(imagenes, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(31, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING,
						layout.createSequentialGroup().addContainerGap(174, Short.MAX_VALUE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
								.addGap(113)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(29).addComponent(textField,
										GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(label2,
										GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(21).addComponent(comboBox,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
								.addComponent(label3, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
						.addGap(17)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(button, GroupLayout.PREFERRED_SIZE, 71,
												GroupLayout.PREFERRED_SIZE)
										.addGap(28).addComponent(imagenes, GroupLayout.PREFERRED_SIZE, 60,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(label4))
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
		try {
			new DragAndDrop(imagenes, dragBorder, rootPaneCheckingEnabled, new DragAndDrop.Listener() {

				public void filesDropped(java.io.File[] files) {
					try {
						Metodos.moverArchivosDrop(files, separador);
					} catch (IOException e) {
						Metodos.mensaje("Error al mover los archivos", 1);
					}
				}

			});
		} catch (TooManyListenersException e1) {
			Metodos.mensaje("Error al mover los archivos", 1);
		}
	}

	public static String[] getLectura() {
		return lectura;
	}

	public static void setLectura(String[] lectura) {
		MenuPrincipal.lectura = lectura;
	}

	public static void setLecturaurl(String[] lecturaurl) {
		MenuPrincipal.lecturaurl = lecturaurl;
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
