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
import java.net.InetAddress;
import java.net.URL;
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
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.ComprobarSha;
import utils.DragAndDrop;
import utils.Galeria;
import utils.ImageResizer;
import utils.InterfazGaleria;
import utils.Metodos;
import utils.MyInterface;
import utils.PhotoFrame;

@SuppressWarnings("serial")
public class MenuPrincipal extends JFrame implements ActionListener, ChangeListener, MyInterface {
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
	private JMenu menu7;
	private JMenuItem menuItem18;
	private JMenuItem menuItem19;
	transient static LinkedList<String> listaImagenes = new LinkedList<>();
	static LinkedList<String> imagenes = new LinkedList<>();
	static LinkedList<String> categorias = new LinkedList<>();
	static boolean conexion = true;
	private JTextField textField;
	static String os = System.getProperty("os.name");
	private static String[] lectura = Metodos.leerFicheroArray("Config/Config.txt", 2);
	private static String[] user = Metodos.leerFicheroArray("Config/User.txt", 2);
	static int imagenesSubidas = 0;

	public static void setImagenesSubidas(int imagenesSubidas) {
		MenuPrincipal.imagenesSubidas = imagenesSubidas;
	}

	public static int getImagenesSubidas() {
		return imagenesSubidas;
	}

	public static String[] getUser() {
		return user;
	}

	public static void setUser(String[] user) {
		MenuPrincipal.user = user;
	}

	static String separador = Metodos.saberSeparador(os);
	static String[] lecturaurl = Metodos.leerFicheroArray("Config/Config2.txt", 2);
	static String[] lecturabd = Metodos.leerFicheroArray("Config/Bd.txt", 7);
	static JButton button = new JButton("");
	static String[] lecturabackup = Metodos.leerFicheroArray("Config/Backup.txt", 1);
	static String directorioActual;
	static JComboBox<String> comboBox = new JComboBox<>();
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem1;
	private JSeparator separator22;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu1;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;

	public static void setLectura(String[] lectura) {
		MenuPrincipal.lectura = lectura;
	}

	public static void setLecturaurl(String[] lecturaurl) {
		MenuPrincipal.lecturaurl = lecturaurl;
	}

	public static boolean isConexion() {
		return conexion;
	}

	public static LinkedList<String> getImagenes() {
		return imagenes;
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

	public static void setLecturabackup(String[] lecturabackup) {
		MenuPrincipal.lecturabackup = lecturabackup;
	}

	public static String[] getLecturabd() {
		return lecturabd;
	}

	public static String getSeparador() {
		return separador;
	}

	public static void cambiarPermisos() {
		try {
			Metodos.crearScript("change_permisos.sh", "sudo chmod 777 -R /var/www", true, os);
		} catch (Exception e1) {
			Metodos.mensaje("Error", 1);
		}
	}

	public static void hacerGIF() throws IOException {
		try {
			if (Metodos.listarFicherosPorCarpeta(new File(lectura[0] + "/Hacer_gif/img"), ".") <= 1) {
				Metodos.mensaje("Tienes que tener al menos 2 imágenes para crear un GIF", 3);
				Metodos.abrirCarpeta(lectura[0] + separador + "Hacer_gif" + separador + "img" + separador);
			} else {
				if (Metodos.listarFicherosPorCarpeta(new File(lectura[0] + "/Hacer_gif/img"), ".") > 170) {
					Metodos.mensaje("Has superado el límite de imágenes para crear un GIF", 3);
					Metodos.abrirCarpeta(lectura[0] + separador + "Hacer_gif" + separador + "img" + separador);
				} else {
					File af = new File("Config/Config.txt");

					if (af.exists()) {
						try {
							if (!lectura[0].isEmpty() && lectura[0] != null) {

								WebDriver chrome = new ChromeDriver();

								chrome.get(lectura[1] + "/Hacer_gif/crear_gif.php");

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
											FileSystems.getDefault()
													.getPath(directorioActual + "Config" + separador + "imagenes"
															+ separador + listaImagenes.get(x)),
											StandardCopyOption.REPLACE_EXISTING);
								}

								for (int x = 0; x < frames.size(); x++) {
									Metodos.eliminarFichero(lectura[0] + separador + "Hacer_gif" + separador + "img"
											+ separador + frames.get(x));
								}
								Metodos.abrirCarpeta("Config" + separador + "imagenes");
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

	public static void mensaje170() {
		Metodos.mensaje("Tienes más de 170 imágenes", 3);
		try {
			Metodos.abrirCarpeta(lectura[0] + separador + "Hacer_gif" + separador + "img");
		} catch (IOException e1) {
			try {
				new Config().setVisible(true);
			} catch (IOException e2) {
				Metodos.mensaje("Error", 1);
			}
		}
	}

	public MenuPrincipal() throws Exception {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/maxresdefault.jpg")));
		Metodos.crearFichero("Config" + separador + "GifFrames", "", true);

		Metodos.guardarConfig(3, separador);

		if (lectura[0] == null || lectura[0].equals("")) {
			Metodos.guardarConfig(1, separador);
		}

		if (lecturaurl[0] == null || lecturaurl[0].equals("")) {
			Metodos.guardarConfig(2, separador);
		}

		if (os == null || os.equals("")) {
			os = "1";
			separador = Metodos.saberSeparador(os);
		}

		directorioActual = new File(".").getCanonicalPath() + separador;

		try {
			Metodos.probarconexion("www.google.com");
		} catch (UnknownHostException e) {
			conexion = false;
			Metodos.mensaje("No hay conexión a internet", 3);
		}

		if (conexion && (lecturabd[0] == null || lecturabd[0].equals(""))) {
			Metodos.crearFichero("Config/Bd.txt", "", false);
			new Bd().setVisible(true);
		}

		if (lecturabackup[0] == null || lecturabackup[0].equals("")) {
			if (!os.equals("Linux")) {
				Metodos.crearFichero("Config/Backup.txt", "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop",
						false);
			} else {
				Metodos.crearFichero("Config/Backup.txt", "/home/" + System.getProperty("user.name") + "/Escritorio",
						false);

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
					int recuento = Metodos.listarFicherosPorCarpeta(new File(lectura[0] + "/Hacer_gif/img"), ".");
					if (recuento <= 170) {
						if (os.equals("Linux")) {

							cambiarPermisos();

						}

						hacerGIF();

					} else {
						mensaje170();
					}
				} catch (Exception e1) {
					try {
						new Config().setVisible(true);
					} catch (IOException e2) {
//
					}

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

						imagenes = Metodos.directorio("Config" + separador + "GifFrames", "gif");

						if (Metodos.listarFicherosPorCarpeta(new File("Config" + separador + "GifFrames"),
								"gif") == 1) {

							WebDriver chrome = new ChromeDriver();
							chrome.get("https://gifframes.herokuapp.com");
							chrome.findElement(By.id("imagen")).sendKeys(directorioActual + "Config" + separador
									+ "GifFrames" + separador + imagenes.getFirst());
							chrome.findElement(By.name("enviar")).click();

							chrome.close();

							Metodos.eliminarFichero(directorioActual + "Config" + separador + "GifFrames" + separador
									+ imagenes.getFirst());
						}

						else {

							Metodos.mensaje("Copia o mueve un archivo GIF", 3);

							Metodos.abrirCarpeta("Config" + separador + "GifFrames");
						}
					}
				} catch (Exception e1) {
					Metodos.mensaje(
							"Comprueba tu conexión y que tengas el archivo chromedriver.exe en la raíz del programa",
							1);
				}
			}
		});
		menuItem1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/GIF_Extract.png")));
		menuItem1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu1.add(menuItem1);

		separator5 = new JSeparator();
		menu1.add(separator5);

		menuItem7 = new JMenuItem("Recortar imágenes");
		menu1.add(menuItem7);
		menuItem7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {

					listaImagenes = Metodos
							.directorio(directorioActual + "Config" + separador + "imagenes_para_recortar", ".");

					if (!listaImagenes.isEmpty()) {

						Metodos.eliminarDuplicados(directorioActual + "Config" + separador + "imagenes_para_recortar",
								separador);

						for (int x = 0; x < listaImagenes.size(); x++) {
							ImageResizer.copyImage(
									directorioActual + "Config" + separador + "imagenes_para_recortar" + separador
											+ listaImagenes.get(x),
									directorioActual + "Config" + separador + "imagenes_para_recortar" + separador
											+ listaImagenes.get(x));
						}

					}
					new PhotoFrame().setVisible(true);

				} catch (Exception e1) {
					Metodos.mensaje("Error", 1);
				}

			}
		});
		menuItem7.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/crop.png")));
		menuItem7.setFont(new Font("Segoe UI", Font.BOLD, 20));

		JSeparator separator_1 = new JSeparator();
		menu1.add(separator_1);

		mntmNewMenuItem = new JMenuItem("Buscar imagen");
		menu1.add(mntmNewMenuItem);
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {

					if (Metodos.comprobarConexionBd("SELECT COUNT(image_id) FROM " + lecturabd[3] + "images",
							"COUNT(image_id)")) {
						try {

							if (Metodos.comprobarConfiguracion()) {

								String busqueda = Metodos.mostrarDialogo();
								busqueda = busqueda.trim();
								int recuento;

								if (!busqueda.isEmpty()) {

									Connection conexion = Metodos.conexionBD();

									Statement s = conexion.createStatement();

									ResultSet rs = s.executeQuery("select count(image_id) from " + lecturabd[3]
											+ "images WHERE image_name LIKE '%" + busqueda + "%'");

									rs.next();

									recuento = Integer.parseInt(rs.getString("count(image_id)"));

									if (recuento > 0) {

										if (recuento > 500) {

											Metodos.mensaje("Has introducido un nombre que está en más de 500 imágenes",
													3);
											Metodos.mensaje(
													"Por favor,introduce un nombre con menos registros para mostrarlos",
													2);

											Metodos.abrirCarpeta(Metodos.obtenerEnlaceCms(
													MenuPrincipal.getLecturaurl()[0], MenuPrincipal.getLecturaurl()[1])
													+ "/search.php?filtro=" + busqueda);

										} else {

											rs = s.executeQuery("select image_media_file,cat_id from " + lecturabd[3]
													+ "images WHERE image_name LIKE '%" + busqueda + "%'");

											while (rs.next()) {

												imagenes.add(rs.getString("image_media_file"));
												categorias.add(rs.getString("cat_id"));
											}

										}

										s.close();
										rs.close();

										if (recuento == 0) {

											Metodos.mensaje("No hay resultados, intente con otro nombre", 2);

										} else {

											if (recuento < 500) {

												try {

													new Galeria();
													new InterfazGaleria().setVisible(true);
												} catch (Exception e) {

													Metodos.mensaje("No se han podido cargar las imágenes", 1);

													new Config2().setVisible(true);

												}

											}
										}

									} else {
										Metodos.mensaje("Por favor, introduce un nombre para buscar", 3);
									}

								}
							}
						} catch (Exception e1) {
						}
					}
				} catch (SQLException e) {
					try {
						new Bd().setVisible(true);
					} catch (IOException e1) {
						//
					}
				}

			}

		});
		mntmNewMenuItem.setFont(new Font("Dialog", Font.BOLD, 20));
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
				} catch (IOException e) {
					//
				}
			}
		});
		mntmNewMenuItem1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu1.add(mntmNewMenuItem1);

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
				if (os.equals("Linux")) {
					cambiarPermisos();
				}
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

					if (Metodos.listarFicherosPorCarpeta(new File(
							lectura[0] + separador + "FrameExtractor" + separador + "examples" + separador + "output"),
							".") <= 170) {
						listaImagenes = Metodos.directorio(lectura[0] + separador + "FrameExtractor" + separador
								+ "examples" + separador + "output", ".");

						for (int x = 0; x < listaImagenes.size(); x++) {

							Files.move(
									FileSystems.getDefault()
											.getPath(lectura[0] + separador + "FrameExtractor" + separador + "examples"
													+ separador + "output" + separador + listaImagenes.get(x)),
									FileSystems.getDefault()
											.getPath(lectura[0] + separador + "Hacer_gif" + separador + "img"
													+ separador + listaImagenes.get(x)),
									StandardCopyOption.REPLACE_EXISTING);
						}

						hacerGIF();
					} else {
						mensaje170();
					}

				} catch (Exception e1) {
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

		menu3 = new JMenu("Base de datos   ");
		menu3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/db.png")));
		menu3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu.add(menu3);

		menuItem4 = new JMenuItem("Backup");
		menuItem4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				ArrayList<String> categorias;
				try {
					categorias = Metodos.comprobarConexionBD();
					if (categorias != null && categorias.size() > 0) {
						try {

							if (Metodos.comprobarConfiguracion() && Metodos.comprobarConexion(true)) {

								lecturabd = Metodos.leerFicheroArray("Config/Bd.txt", 7);
								Metodos.exportarBd(Integer.parseInt(lecturabd[6]));

								if (!os.equals("Linux")) {
									Metodos.eliminarFichero("backupbd.bat");

								}

							} else {
								new Bd().setVisible(true);

							}

						} catch (IOException e1) {
							//
						}
					}
				} catch (IOException e2) {
					try {
						new Bd().setVisible(true);
					} catch (IOException e1) {
						//
					}
				}

			}
		});
		menuItem4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		menuItem4.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu3.add(menuItem4);

		separator6 = new JSeparator();
		menu3.add(separator6);

		menuItem6 = new JMenuItem("Recomponer Inserts");
		menu3.add(menuItem6);
		menuItem6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if (Metodos.comprobarConfiguracion()) {
						new Utilidades().setVisible(true);

					} else {
						new Bd().setVisible(true);
					}
				} catch (Exception e1) {
					//
				}
			}
		});
		menuItem6.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/actualizar.png")));
		menuItem6.setFont(new Font("Segoe UI", Font.BOLD, 20));

		mntmNewMenuItem_2 = new JMenuItem("Comprobador SHA");
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				ArrayList<String> categorias;

				try {

					categorias = Metodos.comprobarConexionBD();

					if (categorias != null && categorias.size() > 0) {

						try {
							new ComprobarSha().setVisible(true);
						} catch (IOException e) {
							//
						}
					}
				} catch (IOException e2) {
					try {
						new Bd().setVisible(true);
					} catch (IOException e1) {
						//
					}
				}

				/////////////////

			}
		});

		JSeparator separator_3 = new JSeparator();
		menu3.add(separator_3);
		mntmNewMenuItem_2.setFont(new Font("Dialog", Font.BOLD, 20));
		mntmNewMenuItem_2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/db.png")));
		menu3.add(mntmNewMenuItem_2);

		separator4 = new JSeparator();
		menu.add(separator4);

		menuItem5 = new JMenuItem("Notas");
		menuItem5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					if (Metodos.comprobarConfiguracion()) {
						new AgendaInterfaz().setVisible(true);
					} else {
						new Bd().setVisible(true);
					}
				} catch (Exception e1) {
				}

			}
		});
		menuItem5.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/name.png")));
		menuItem5.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu.add(menuItem5);

		menu4 = new JMenu("    ");
		menu4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menu4.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menu4.setBackground(Color.BLACK);
		menuopciones.add(menu4);

		mnNewMenu = new JMenu("Image  ");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menu4.add(mnNewMenu);

		menuItem8 = new JMenuItem("Imagenes");
		mnNewMenu.add(menuItem8);
		menuItem8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					Metodos.abrirCarpeta("Config" + separador + "imagenes");
				} catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});
		menuItem8.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menuItem8.setFont(new Font("Segoe UI", Font.BOLD, 18));

		separator9 = new JSeparator();
		mnNewMenu.add(separator9);

		menuItem9 = new JMenuItem("GIF Animator");
		mnNewMenu.add(menuItem9);
		menuItem9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				File directorio = new File(lectura[0] + separador + "Hacer_gif" + separador + "img");
				directorio.mkdir();
				directorio = new File(lectura[0] + separador + "Hacer_gif" + separador + "Output");
				directorio.mkdir();
				try {
					lectura = (Metodos.leerFicheroArray("Config/Config.txt", 2));
					Metodos.comprobarConexion("Config/Config.txt",
							lectura[0] + separador + "Hacer_gif" + separador + "img");
				} catch (Exception e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});
		menuItem9.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gif.png")));
		menuItem9.setFont(new Font("Segoe UI", Font.BOLD, 18));

		separator7 = new JSeparator();
		mnNewMenu.add(separator7);

		menuItem10 = new JMenuItem("GIF Extractor");
		mnNewMenu.add(menuItem10);
		menuItem10.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Metodos.abrirCarpeta("Config" + separador + "GifFrames");
				} catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});
		menuItem10.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/GIF_Extract.png")));
		menuItem10.setFont(new Font("Segoe UI", Font.BOLD, 18));

		separator11 = new JSeparator();
		mnNewMenu.add(separator11);

		menuItem13 = new JMenuItem("Crop images");
		mnNewMenu.add(menuItem13);
		menuItem13.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Metodos.abrirCarpeta("Config" + separador + "imagenes_para_recortar");
				} catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});
		menuItem13.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/crop.png")));
		menuItem13.setFont(new Font("Segoe UI", Font.BOLD, 18));

		separator8 = new JSeparator();
		menu4.add(separator8);

		mnNewMenu1 = new JMenu("Video");
		mnNewMenu1.setFont(new Font("Segoe UI", Font.BOLD, 20));
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
		menuItem11.setFont(new Font("Segoe UI", Font.BOLD, 18));

		separator10 = new JSeparator();
		mnNewMenu1.add(separator10);

		menuItem12 = new JMenuItem("Video 2 GIF");
		mnNewMenu1.add(menuItem12);
		menuItem12.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				video2Frames();
			}
		});
		menuItem12.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video_2_gif.gif")));
		menuItem12.setFont(new Font("Segoe UI", Font.BOLD, 18));

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

		menuItem16 = new JMenuItem("Conexión BD");
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

		JSeparator separator_2 = new JSeparator();
		mnConfig.add(separator_2);

		mntmNewMenuItem_1 = new JMenuItem("Usuario");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					new User().setVisible(true);
				} catch (IOException e) {
					//
				}
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/user.png")));
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mnConfig.add(mntmNewMenuItem_1);

		menu7 = new JMenu("Ayuda");
		menu7.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/help.png")));
		menu7.setForeground(Color.DARK_GRAY);
		menu7.setFont(new Font("Segoe UI", Font.BOLD, 24));
		menu7.setBackground(Color.BLACK);
		menuopciones.add(menu7);

		menuItem19 = new JMenuItem("Sobre");
		menuItem19.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new About().setVisible(true);
			}
		});

		mntmNewMenuItem_3 = new JMenuItem("DB Fix");
		mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new DBFix().setVisible(true);
				} catch (IOException e1) {
					//
				}
			}
		});
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem_3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		menu7.add(mntmNewMenuItem_3);

		menuItem18 = new JMenuItem("IMG 2 Color");
		menuItem18.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Metodos.abrirCarpeta("https://colourise.sg");
				} catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});

		JSeparator separator_4 = new JSeparator();
		menu7.add(separator_4);
		menuItem18.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/30-07-2018 1-07-31.png")));
		menuItem18.setFont(new Font("Segoe UI", Font.BOLD, 24));
		menu7.add(menuItem18);

		JSeparator separator_5 = new JSeparator();
		menu7.add(separator_5);
		menuItem19.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/about.png")));
		menuItem19.setFont(new Font("Segoe UI", Font.BOLD, 24));
		menu7.add(menuItem19);

		Metodos.comprobarArchivo("Config", false);

		initComponents();
		this.setVisible(true);
	}

	public void initComponents() throws IOException {

		Metodos.crearCarpetas();

		if (Metodos.comprobarArchivo("Config/Config.txt", true)) {

			try {
				Metodos.guardarConfig(4, separador);
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

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (comboBox.getSelectedIndex() == -1) {
					try {
						Metodos.mensaje("Comprueba que tengas al menos una categoría en la base de datos", 3);
						new Bd().setVisible(true);
					} catch (IOException e1) {
						Metodos.mensaje("Error", 1);
					}
				} else {
					if (!textField.getText().trim().isEmpty()) {

						listaImagenes = Metodos.directorio("Config" + separador + "imagenes", ".");

						if (listaImagenes.isEmpty() || user.length != 2) {

							if (user.length != 2) {
								try {
									new User().setVisible(true);
								} catch (IOException e1) {
									//
								}
							}

							if (listaImagenes.isEmpty()) {
								try {
									Metodos.abrirCarpeta("Config" + separador + "imagenes");
								} catch (IOException e1) {
									//
								}
							}

						} else {
							String parametros = "";
							String extension = "";
							StringBuilder bld = new StringBuilder();
							InetAddress ping;
							if (user[0] != null && user[1] != null && !user[0].isEmpty() && !user[1].isEmpty()) {

								for (int i = 0; i < listaImagenes.size(); i++) {

									extension = listaImagenes.get(i).substring(listaImagenes.get(i).length() - 3,
											listaImagenes.get(i).length());

									if (extension.equals("peg")) {
										extension = "jpg";
									}

									if (listaImagenes.size() == 1 || i + 1 == listaImagenes.size()) {
										bld.append(i + "." + extension);

									} else {

										bld.append(i + "." + extension + ",");
									}

								}

								parametros = bld.toString();

								if (!parametros.isEmpty()) {

									JSONObject json;

									try {

										int maximo = 1;

										Metodos.eliminarDuplicados(directorioActual + "Config" + separador + "imagenes",
												separador);

										Connection conexion = Metodos.conexionBD();
										Statement s = conexion.createStatement();
										ResultSet rs;

										rs = s.executeQuery("SELECT MAX(image_id)+1 as maximo FROM " + lecturabd[3]
												+ "images order by image_id");
										rs.next();

										if (rs.getString("maximo") != null) {
											maximo = Integer.parseInt(rs.getString("maximo"));
										}

										int categoria = comboBox.getSelectedIndex() + 1;

										json = Metodos.readJsonFromUrl(
												"https://apiperiquito.herokuapp.com/recibo-json.php?imagenes="
														+ parametros);

										JSONArray imagenesBD = json.getJSONArray("imagenes_bd");

										listaImagenes = Metodos
												.directorio(directorioActual + "Config" + separador + "imagenes", ".");

										String imagen = "";

										ping = InetAddress
												.getByName(new URL("http://" + MenuPrincipal.getLecturaurl()[0] + "/"
														+ MenuPrincipal.getLecturaurl()[1] + "/index.php").getHost());

										conexion = Metodos.conexionBD();

										s = conexion.createStatement();

										int comprobarSha = 0;

										for (int i = 0; i < listaImagenes.size(); i++) {

											rs = s.executeQuery("SELECT COUNT(sha256) FROM " + lecturabd[3] + "images"
													+ " WHERE sha256='" + Metodos.getSHA256Checksum(imagen) + "'");

											rs.next();

											comprobarSha = Integer.parseInt(rs.getString("COUNT(sha256)"));

											if (comprobarSha == 0) {

												imagen = directorioActual + "Config" + separador + "imagenes"
														+ separador + imagenesBD.get(i).toString();

												if (!Metodos.extraerExtension(imagen).equals("gif")
														&& !Metodos.extraerExtension(imagenesBD.get(i).toString())
																.equals("ini")
														&& !ping.getCanonicalHostName().equals("")) {

													File f1 = new File(
															directorioActual + "Config" + separador + "imagenes",
															separador + listaImagenes.get(i));

													File f2 = new File(
															directorioActual + "Config" + separador + "imagenes",
															separador + imagenesBD.get(i).toString());
													f1.renameTo(f2);
													s.executeUpdate("INSERT INTO " + lecturabd[3] + "images VALUES('"
															+ maximo + "','" + categoria + "','1','"
															+ textField.getText().trim() + "','','','"
															+ Metodos.saberFecha() + "','1','"
															+ imagenesBD.get(i).toString()
															+ "','1','0','0','0',DEFAULT,'0','"
															+ Metodos.getSHA256Checksum(imagen) + "')");

													maximo++;
													Metodos.postFile(new File(imagen), imagenesBD.get(i).toString(),
															user[0], user[1], categoria);

												} else {

													listaImagenes = Metodos.directorio(
															directorioActual + "Config" + separador + "imagenes", ".");

													if (!Metodos.extraerExtension(imagen).equals("ini")) {

														imagen = directorioActual + "Config" + separador + "imagenes"
																+ separador + listaImagenes.get(i).toString();
														String carpeta = "";

														if (!lecturaurl[1].isEmpty()) {
															carpeta = "/" + lecturaurl[1];
														}

														WebDriver chrome = new ChromeDriver();

														String userId = "";
														chrome.get("http://" + lecturaurl[0] + carpeta + "/index.php");

														rs = s.executeQuery("SELECT userId FROM " + lecturabd[3]
																+ "users" + " WHERE user_name='" + user[0] + "'");
														rs.next();
														userId = rs.getString("userId");
														chrome.manage().addCookie(new Cookie("4images_userid", userId));

														rs = s.executeQuery("SELECT user_password FROM " + lecturabd[3]
																+ "users WHERE userId='" + userId + "'");
														rs.next();

														chrome.manage().addCookie(
																new Cookie("pass", rs.getString("user_password")));

														chrome.get("http://" + lecturaurl[0] + carpeta
																+ "/upload_images/input.php?cat_id=1&nombre=java");

														chrome.findElement(By.id("file")).sendKeys(imagen);

														chrome.close();

													}

												}
											}
										}

										rs.close();
										s.close();
										conexion.close();
										if (imagenesSubidas > 0) {

											listaImagenes = Metodos.directorio(
													directorioActual + "Config" + separador + "imagenes", ".");

											if (comprobarSha > 0) {
												for (int i = 0; i < listaImagenes.size(); i++) {
													Metodos.eliminarFichero(directorioActual + "Config" + separador
															+ "imagenes" + separador + listaImagenes.get(i));
												}
											}

										} else {
											Metodos.mensaje("No se ha subido ningún archivo al CMS", 2);
										}

										imagenesSubidas = 0;

									} catch (SQLException e1) {
										Metodos.mensaje(
												"La tabla de imágenes no tiene la estructura para ejecutar correctamente esta acción",
												1);
									}

									catch (Exception e1) {
										//
									}
								}
							} else {
								try {
									new User().setVisible(true);
								} catch (IOException e1) {
									//
								}
							}
						}
					} else {
						try {
							if (user[0] == null && user[1] == null || (user[0].isEmpty() || user[1].isEmpty())) {

								Metodos.mensaje("Por favor, introduce tu usuario y contraseña del CMS", 2);
								new User().setVisible(true);

							} else {
								if (textField.getText().trim().isEmpty()) {
									Metodos.mensaje("Por favor, rellene el nombre", 3);
								} else {
									try {
										Metodos.abrirCarpeta(directorioActual + "Config" + separador + "imagenes");
									} catch (IOException e1) {
										Metodos.mensaje("Error", 1);
									}
								}
							}
						} catch (Exception e1) {
							Metodos.mensaje("Por favor, introduce tu usuario y contraseña del CMS", 2);
							try {
								new User().setVisible(true);
							} catch (IOException e2) {
								//
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
		layout.setHorizontalGroup(
				layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(51).addComponent(label2).addGap(46)
										.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(comboBox, 0, 293, Short.MAX_VALUE)
												.addComponent(textField, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
												.addComponent(button, 0, 0, Short.MAX_VALUE)))
								.addGroup(layout.createSequentialGroup().addGap(40).addComponent(label3,
										GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(104, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup().addContainerGap()
								.addComponent(label4, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(imagenes, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(33, Short.MAX_VALUE)));
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
						.addGap(23).addComponent(button, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addGap(22)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(imagenes, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addComponent(label4))
						.addGap(22)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(560, 438));
		setLocationRelativeTo(null);

		try {
			if (Metodos.comprobarConexion(true)) {
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

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
