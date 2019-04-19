package periquito;

import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
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
import java.sql.SQLException;
import java.util.LinkedList;

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

import utils.DragAndDrop;
import utils.Metodos;
import utils.PhotoFrame;
import utils.interfaz;

@SuppressWarnings("serial")

public class MenuPrincipal extends javax.swing.JFrame implements ActionListener, ChangeListener, interfaz {
	static javax.swing.JComboBox<String> jComboBox1 = new javax.swing.JComboBox<>();
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JTextField jTextField1;
	private JButton btnNewButton;
	private JCheckBox check5;
	private JCheckBox mute;
	private JButton boton1;
	private JMenuBar jmenubarra;
	private JMenuBar menuOpciones;
	private JMenu mnMenu;
	private JMenuItem mnGifAnimator;
	private JMenuItem mnGifExtractor;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmImages;
	private JMenuItem mntmImages1;
	private JMenuItem mntmCxvxv;
	private JMenuItem mntmNewMenuItem1;
	private JMenuItem mntmNewMenuItem2;
	private JMenuItem mntmNewMenuItem3;
	private JMenuItem mntmNewMenuItem4;
	private JSeparator separator1;
	private JSeparator separator2;
	private JMenuItem mntmLocal;
	private JMenuItem mntmNewMenuItem5;
	private JSeparator separator3;
	private JSeparator separator4;
	private JMenuItem mntmNewMenuItem7;
	private JMenuItem mntmNewMenuItem8;
	private JSeparator separator5;
	private JMenuItem mntmNewMenuItem9;
	private JSeparator separator8;
	private JMenuItem mntmNewMenuItem10;
	private JMenu mnVideo;
	private JMenu mnGif;
	private JSeparator separator6;
	private JSeparator separator7;
	private JSeparator separator9;
	private JMenuItem mntmUploads;
	private JMenuItem mntmNewMenuItem11;
	private JSeparator separator11;
	private JMenuItem mntmNewMenuItem12;
	private JSeparator separator12;
	static javax.swing.JTextArea imagenes = new javax.swing.JTextArea();

	@SuppressWarnings("all")
	private static String[] lectura = Metodos.leerFicheroArray("Config/Config.txt", 2);
	@SuppressWarnings("all")
	String[] lecturaurl = Metodos.leerFicheroArray("Config/Config2.txt", 2);
	@SuppressWarnings("all")
	static String[] lecturaos = Metodos.leerFicheroArray("Config/OS.txt", 1);

	public String separador;

	String directorioActual;

	transient LinkedList<String> listaImagenes = new LinkedList<>();

	@SuppressWarnings("all")
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
					mensaje("Ya has convertido un video a frames!", true);
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
			mensaje("Error en el archivo Config.txt", true);
		}
	}

	public static String[] getLecturaos() {
		return lecturaos;
	}

	String[] lecturabd = Metodos.leerFicheroArray("Config/Bd.txt", 6);
	@SuppressWarnings("all")
	String[] lecturabackup = Metodos.leerFicheroArray("Config/Backup.txt", 1);

	private JSeparator separator13;
	private JMenuItem mntmNewMenuItem13;
	private JMenu mnNewMenu;
	private JSeparator separator15;
	private JMenuItem mntmNewMenuItem15;
	private JMenu mnNewMenu1;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton1;
	private JLabel lblNewLabel1;
	private JSeparator separator17;
	private JLabel lblNewLabel2;
	private JSeparator separator;
	private JSeparator separator10;
	private JMenuItem mntmNewMenuItem111;
	private JSeparator separator111;
	private JMenuItem mntmNewMenuItem222;
	private JSeparator separator222;

	@SuppressWarnings("all")
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
						mensaje("No tienes el driver de chrome en la carpeta del programa", true);
					}

				}
			}
		}
	}

	public void comprobarConexion(String archivo, String ruta) {
		File af = new File(archivo);
		if (af.exists()) {
			File comprobacion = new File(ruta);
			if (!comprobacion.exists()) {
				mensaje("Ruta inválida ", true);
				new Config().setVisible(true);
			} else {
				Metodos.abrirCarpeta(ruta);
			}
		} else {
			new Config().setVisible(true);
		}
	}

	public static javax.swing.JTextArea getImagenes() {
		return imagenes;
	}

	public void verConfiguraciones() {
		new Config().setVisible(true);
		new Config2().setVisible(true);
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
		if (mute.isSelected()) {
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
		}
		try {
			if (opcion > 0 && !lecturaurl[0].isEmpty() && !lecturaurl[1].isEmpty()) {
				int salida;
				if (opcion == 9) {
					salida = Metodos.salida(lectura + "\\gif", "\\\\" + lecturaurl[0] + "\\" + opcion, opcion,
							separador);
					Metodos.notificacion(salida, lectura + "gif", "GIF", true);
					salida = Metodos.salida(lectura + "\\gif\\Thumb", "\\\\" + lecturaurl[1] + "\\" + opcion, opcion,
							separador);
					Metodos.notificacion(salida, lectura + "gif\\Thumb", "JPG", false);
					mensaje(salida + " imagen/es subida/s", false);
				} else {
					salida = Metodos.salida(lectura, "\\\\" + lecturaurl[0] + "\\" + opcion, opcion, separador);
					Metodos.notificacion(salida, lectura, "JPG", true);
					salida = Metodos.salida(lectura + "\\Thumb", "\\\\" + lecturaurl[1] + "\\" + opcion, opcion,
							separador);
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

	@SuppressWarnings("all")
	public MenuPrincipal() throws IOException {

		Metodos.crearFichero("GifFrames", "", true);
		separador = Metodos.saberseparador(Integer.parseInt(lecturaos[0]));
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

		separador = Metodos.saberseparador(Integer.parseInt(lecturaos[0]));
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
		menuOpciones = new JMenuBar();
		menuOpciones.setForeground(new Color(255, 255, 255));
		menuOpciones.setToolTipText("");
		menuOpciones.setName("");
		menuOpciones.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		menuOpciones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		menuOpciones.setBackground(new Color(255, 255, 255));
		menuOpciones.setAlignmentY(Component.CENTER_ALIGNMENT);
		setJMenuBar(menuOpciones);
		mnMenu = new JMenu("          ");
		mnMenu.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/utilities.png")));
		mnMenu.setFont(new Font("Segoe UI", Font.BOLD, 10));
		mnMenu.setBackground(new Color(0, 0, 0));
		mnMenu.setForeground(new Color(0, 0, 0));
		menuOpciones.add(mnMenu);
		mnGif = new JMenu("Imagen");
		mnGif.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnGif.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gif.png")));
		mnMenu.add(mnGif);
		mnGifAnimator = new JMenuItem("GIF Animator");
		mnGif.add(mnGifAnimator);
		mnGifAnimator.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				hacerGIF();
			}

		});
		mnGifAnimator.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gifanim.png")));
		mnGifAnimator.setFont(new Font("Segoe UI", Font.BOLD, 18));
		separator7 = new JSeparator();
		mnGif.add(separator7);
		mnGifExtractor = new JMenuItem("GIF Extractor");
		mnGif.add(mnGifExtractor);
		mnGifExtractor.addMouseListener(new MouseAdapter() {
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
		mnGifExtractor.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/carpeta.png")));
		mnGifExtractor.setFont(new Font("Segoe UI", Font.BOLD, 18));
		separator6 = new JSeparator();
		mnMenu.add(separator6);
		mnVideo = new JMenu("Video");
		mnVideo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnVideo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video_2_frame.png")));
		mnMenu.add(mnVideo);
		mntmNewMenuItem7 = new JMenuItem("Video 2 Frame");
		mnVideo.add(mntmNewMenuItem7);
		mntmNewMenuItem7.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent arg0) {

				videoToFrame();
			}

		});
		mntmNewMenuItem7.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmNewMenuItem7.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video2frames.png")));
		separator9 = new JSeparator();
		mnVideo.add(separator9);
		mntmNewMenuItem10 = new JMenuItem("Video 2 GIF");
		mnVideo.add(mntmNewMenuItem10);
		mntmNewMenuItem10.addMouseListener(new MouseAdapter() {
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
		mntmNewMenuItem10.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/video_2_gif.gif")));
		mntmNewMenuItem10.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmUploads = new JMenuItem("Notas");
		mntmUploads.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmUploads.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/name.png")));
		mntmUploads.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (!Metodos.comprobarConexionBd()) {
					try {
						Metodos.mensaje("Compuebe que exista una tabla llamada notas en la base de datos", 3);
						new Bd().setVisible(true);
					} catch (IOException e) {

					}

				} else {
					try {
						if (Metodos.comprobarConfiguracion()) {

							new AgendaInterfaz().setVisible(true);
						}
					} catch (IOException | SQLException e) {
					}
				}
			}
		});
		separator11 = new JSeparator();
		mnMenu.add(separator11);

		mnNewMenu = new JMenu("Base de datos");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/db.png")));
		mnMenu.add(mnNewMenu);

		mntmNewMenuItem13 = new JMenuItem("Backup");
		mntmNewMenuItem13.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

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
				} catch (IOException e) {
				}

			}
		});
		mnNewMenu.add(mntmNewMenuItem13);
		mntmNewMenuItem13.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem13.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));

		separator111 = new JSeparator();
		mnMenu.add(separator111);
		mnMenu.add(mntmUploads);
		mntmNewMenuItem12 = new JMenuItem("Recomponer Inserts");
		mntmNewMenuItem12.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {

				try {
					if (Metodos.comprobarConfiguracion()) {
						try {
							new Utilidades().setVisible(true);
						} catch (IOException e) {
						}
					}
				} catch (IOException e) {
				}

			}

		});
		separator12 = new JSeparator();
		mnMenu.add(separator12);
		mntmNewMenuItem12.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/actualizar.png")));
		mntmNewMenuItem12.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnMenu.add(mntmNewMenuItem12);

		separator13 = new JSeparator();
		mnMenu.add(separator13);

		mntmNewMenuItem111 = new JMenuItem("Recortar imagenes");
		mntmNewMenuItem111.addMouseListener(new MouseAdapter() {
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
		mntmNewMenuItem111.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem111.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/crop.png")));
		mnMenu.add(mntmNewMenuItem111);
		mntmNewMenuItem = new JMenu("   ");
		mntmNewMenuItem.setBackground(Color.BLACK);
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmNewMenuItem.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		menuOpciones.add(mntmNewMenuItem);
		mntmImages = new JMenuItem("Imagenes");
		mntmImages.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmImages.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				File directorio = new File(lectura[0] + separador + "Hacer_gif" + separador + "img");
				directorio.mkdir();
				directorio = new File(lectura[0] + separador + "Hacer_gif" + separador + "Output");
				directorio.mkdir();

				Metodos.abrirCarpeta("imagenes");
			}
		});
		mntmImages.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmImages.setSelectedIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmNewMenuItem.add(mntmImages);
		mntmImages1 = new JMenuItem("GIF Animator");
		mntmImages1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmImages1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				setLectura(Metodos.leerFicheroArray("Config/Config.txt", 2));
				comprobarConexion("Config/Config.txt", lectura[0] + separador + "Hacer_gif" + separador + "img");

			}
		});
		separator1 = new JSeparator();
		mntmNewMenuItem.add(separator1);
		mntmImages1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gif.png")));
		mntmImages1.setSelectedIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmNewMenuItem.add(mntmImages1);
		mntmImages1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gif.png")));
		mntmImages1.setSelectedIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmNewMenuItem.add(mntmImages1);
		mntmCxvxv = new JMenuItem("GIF Extractor");
		mntmCxvxv.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmCxvxv.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				Metodos.abrirCarpeta("GifFrames");
			}
		});
		separator2 = new JSeparator();
		mntmNewMenuItem.add(separator2);
		mntmCxvxv.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/GIF_Extract.png")));
		mntmNewMenuItem.add(mntmCxvxv);
		mntmNewMenuItem8 = new JMenuItem("Video 2 Frame");
		mntmNewMenuItem8.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				try {
					comprobarConexion("Config/Config.txt",
							lectura[0] + separador + "FrameExtractor" + separador + "examples" + separador + "video");
				} catch (ArrayIndexOutOfBoundsException e) {
					mensaje("Error en el  archivo Config.txt", true);
				}
			}
		});
		separator5 = new JSeparator();
		mntmNewMenuItem.add(separator5);
		mntmNewMenuItem8.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmNewMenuItem8.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmNewMenuItem.add(mntmNewMenuItem8);
		separator8 = new JSeparator();
		mntmNewMenuItem.add(separator8);
		mntmNewMenuItem9 = new JMenuItem("Video 2 GIF");
		mntmNewMenuItem9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					comprobarConexion("Config/Config.txt", lectura[0] + separador + "VID-2-GIF");

				} catch (ArrayIndexOutOfBoundsException e) {
					mensaje("Error en el  archivo Config.txt", true);
				}
			}
		});
		mntmNewMenuItem9.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmNewMenuItem9.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmNewMenuItem.add(mntmNewMenuItem9);

		mntmNewMenuItem222 = new JMenuItem("Crop images");
		mntmNewMenuItem222.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Metodos.abrirCarpeta("imagenes_para_recortar");
			}
		});

		separator222 = new JSeparator();
		mntmNewMenuItem.add(separator222);
		mntmNewMenuItem222.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mntmNewMenuItem222.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		mntmNewMenuItem.add(mntmNewMenuItem222);
		mntmNewMenuItem1 = new JMenu("Config");
		mntmNewMenuItem1.setForeground(Color.DARK_GRAY);
		mntmNewMenuItem1.setBackground(Color.BLACK);
		mntmNewMenuItem1.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/config.png")));
		menuOpciones.add(mntmNewMenuItem1);
		mntmLocal = new JMenuItem("Local");
		mntmLocal.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				new Config().setVisible(true);
			}
		});
		mntmLocal.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmLocal.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/config.png")));
		mntmNewMenuItem1.add(mntmLocal);
		mntmNewMenuItem5 = new JMenuItem("Remoto");
		mntmNewMenuItem5.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				new Config2().setVisible(true);
			}
		});
		separator3 = new JSeparator();
		mntmNewMenuItem1.add(separator3);
		mntmNewMenuItem5.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem5.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/remote.png")));
		mntmNewMenuItem1.add(mntmNewMenuItem5);
		separator4 = new JSeparator();
		mntmNewMenuItem1.add(separator4);
		mntmNewMenuItem11 = new JMenuItem("Conexion BD");
		mntmNewMenuItem11.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					new Bd().setVisible(true);
				} catch (IOException e) {
				}
			}
		});
		mntmNewMenuItem11.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem11.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		mntmNewMenuItem1.add(mntmNewMenuItem11);

		separator15 = new JSeparator();
		mntmNewMenuItem1.add(separator15);

		mntmNewMenuItem15 = new JMenuItem("Backup DB");
		mntmNewMenuItem15.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem15.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/bd.png")));
		mntmNewMenuItem15.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				new Backup().setVisible(true);
			}
		});
		mntmNewMenuItem1.add(mntmNewMenuItem15);

		separator2 = new JSeparator();
		mntmNewMenuItem1.add(separator2);

		mnNewMenu1 = new JMenu("OS");
		mnNewMenu1.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mnNewMenu1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/os.png")));
		mntmNewMenuItem1.add(mnNewMenu1);

		lblNewLabel1 = new JLabel("");
		lblNewLabel1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/Windows.png")));
		mnNewMenu1.add(lblNewLabel1);

		rdbtnNewRadioButton1 = new JRadioButton("WIN");
		rdbtnNewRadioButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					Metodos.crearFichero("Config/OS.txt", "1", false);
					separador = Metodos.saberseparador(Integer.parseInt(lecturaos[0]));
					directorioActual = new File(".").getCanonicalPath() + separador;
				} catch (IOException e) {
					mensaje("No se ha podido guardar el archivo OS.txt", true);
				}
			}
		});
		rdbtnNewRadioButton1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton1.setFont(new Font("Tahoma", Font.BOLD, 14));
		mnNewMenu1.add(rdbtnNewRadioButton1);

		separator17 = new JSeparator();
		mnNewMenu1.add(separator17);

		lblNewLabel2 = new JLabel("");
		lblNewLabel2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/linux.png")));
		mnNewMenu1.add(lblNewLabel2);

		rdbtnNewRadioButton = new JRadioButton("Linux");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Metodos.crearFichero("Config/OS.txt", "2", false);
					separador = Metodos.saberseparador(Integer.parseInt(lecturaos[0]));
					directorioActual = new File(".").getCanonicalPath() + separador;
				} catch (IOException e1) {
					mensaje("No se ha podido guardar el archivo OS.txt", true);
				}
			}
		});
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		mnNewMenu1.add(rdbtnNewRadioButton);
		mntmNewMenuItem2 = new JMenu("Ayuda");
		mntmNewMenuItem2.setForeground(Color.DARK_GRAY);
		mntmNewMenuItem2.setBackground(Color.BLACK);
		mntmNewMenuItem2.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/help.png")));
		menuOpciones.add(mntmNewMenuItem2);
		mntmNewMenuItem3 = new JMenuItem("Sobre");
		mntmNewMenuItem3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				new About().setVisible(true);
			}
		});
		mntmNewMenuItem4 = new JMenuItem("IMG 2 Color");
		mntmNewMenuItem4.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				Metodos.abrirCarpeta("https://demos.algorithmia.com/colorize-photos/");
			}
		});

		separator = new JSeparator();
		mntmNewMenuItem2.add(separator);
		mntmNewMenuItem4.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/30-07-2018 1-07-31.png")));
		mntmNewMenuItem2.add(mntmNewMenuItem4);

		separator10 = new JSeparator();
		mntmNewMenuItem2.add(separator10);
		mntmNewMenuItem3.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mntmNewMenuItem3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/about.png")));
		mntmNewMenuItem2.add(mntmNewMenuItem3);
		initComponents();
		this.setVisible(true);
	}

	public void initComponents() throws IOException {

		Metodos.crearCarpetas();

		if (Metodos.comprobarArchivo("Config/Config.txt", true)) {

			Metodos.guardarConfig(5, separador);
		}

		jTextField1 = new javax.swing.JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.LEFT);
		jLabel1 = new javax.swing.JLabel();
		jLabel1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/name.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		try {
			if (Metodos.comprobarConexion()) {

				Metodos.ponerCategoriasBd(jComboBox1);
			}
		} catch (SQLException e3) {
			boton1.setEnabled(false);
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
		imagenes.setForeground(Color.DARK_GRAY);
		imagenes.setBackground(Color.WHITE);
		imagenes.setEditable(false);
		imagenes.setText("  Arrastra los archivos aqui");
		imagenes.setBounds(115, 295, 340, 60);
		imagenes.setFont(new Font("Tahoma", Font.BOLD, 24));
		getContentPane().add(imagenes);
		JLabel importar;
		importar = new JLabel("");
		importar.setHorizontalAlignment(SwingConstants.CENTER);
		importar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				File[] files = Metodos.seleccionar(2, "Imagen & Video",
						"Elije un archivo de imagen o video para mover");
				if (files != null) {

					try {
						Metodos.moverArchivosDrop(files, separador);
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

		if (!Metodos.probarconexion("www.google.com")) {
			mensaje("No hay conexión a internet", true);
		}
		Metodos.comprobarArchivo("Config", false);

		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/WAV_00002.png")));
		boton1 = new JButton("");
		boton1.setFont(new Font("Tahoma", Font.PLAIN, 6));
		boton1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/start.png")));
		boton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (jComboBox1.getSelectedIndex() == -1) {
					try {
						new Bd().setVisible(true);
					} catch (IOException e1) {
						mensaje("Error", true);
					}
				} else {
					Metodos.mensaje("actualizar esta parte", 2);
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
		new DragAndDrop(imagenes, dragBorder, rootPaneCheckingEnabled, new DragAndDrop.Listener() {

			public void filesDropped(java.io.File[] files) {
				try {
					Metodos.moverArchivosDrop(files, separador);
				} catch (IOException e) {
					mensaje("Error al mover los archivos", true);
				}
			}

		});
	}

	public void stateChanged(ChangeEvent e) {
		if (check5.isSelected()) {
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

	public static String[] getLectura() {
		return lectura;
	}

	public static void setLectura(String[] lectura) {
		MenuPrincipal.lectura = lectura;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
//
	}
}