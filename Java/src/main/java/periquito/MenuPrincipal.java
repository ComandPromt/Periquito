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
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")

public class MenuPrincipal extends javax.swing.JFrame implements ActionListener, ChangeListener {
	private javax.swing.Box.Filler filler1;
	private javax.swing.JComboBox<String> jComboBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JTextField jTextField1;
	private JButton btnNewButton;
	private JCheckBox check5;
	private JCheckBox mute;
	private JCheckBox gif;
	private JButton btnNewButton_2;
	private JButton boton1;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnChrome;

	public void actionPerformed(ActionEvent e) {
	}

	public void mensaje(String mensaje, String url) {
		AudioClip clip;

		clip = java.applet.Applet.newAudioClip(getClass().getResource(url));
		if (mute.isSelected() == true) {
			clip.stop();
		} else {
			clip.loop();
		}
		JOptionPane.showMessageDialog(null, mensaje);
		int option = JOptionPane.CLOSED_OPTION;
		if (option == -1) {
			clip.stop();
		}
	}

	public void mensaje(String mensaje) {
		AudioClip clip;

		clip = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/duck-quack1.wav"));
		if (mute.isSelected() == true) {
			clip.stop();
		} else {
			clip.loop();
		}
		JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
		int option = JOptionPane.CLOSED_OPTION;
		if (option == -1) {
			clip.stop();
		}
	}

	public void notificacion(int salida, String directorio, String tipo) throws MalformedURLException {
		if (salida > 0) {
			mensaje(salida + " archivo/s copiado/s", "/sonidos/gong1.wav");
		} else {
			mensaje("No hay archivos " + tipo + " en la carpeta " + directorio);
		}
	}

	private void mover_imagenes(int opcion, int navegador) throws IOException {

		Runtime aplicacion = Runtime.getRuntime();
		switch (navegador) {
		case 1:
			aplicacion.exec("cmd.exe /K start cerrar.bat");
			break;
		case 2:
			aplicacion.exec("cmd.exe /K start cerrarchrome.bat");
			break;
		}

		if (opcion > 0) {
			int salida;
			if (opcion == 9) {
				salida = salida("C:\\AppServ\\www\\Periquito\\imagenes\\gif",
						"\\\\192.168.1.100\\web\\hoopfetish\\data\\media\\" + opcion, opcion);
				notificacion(salida, "C:\\AppServ\\www\\Periquito\\imagenes\\gif", "GIF");
				salida = salida("C:\\AppServ\\www\\Periquito\\imagenes\\gif\\Thumb",
						"\\\\192.168.1.100\\web\\hoopfetish\\data\\thumbnails\\" + opcion, opcion);
				notificacion(salida, "C:\\AppServ\\www\\Periquito\\imagenes\\gif\\Thumb", "JPG");
			} else {
				salida = salida("C:\\AppServ\\www\\Periquito\\imagenes",
						"\\\\192.168.1.100\\web\\hoopfetish\\data\\media\\" + opcion, opcion);
				notificacion(salida, "C:\\AppServ\\www\\Periquito\\imagenes", "JPG");
				salida = salida("C:\\AppServ\\www\\Periquito\\imagenes\\Thumb",
						"\\\\192.168.1.100\\web\\hoopfetish\\data\\thumbnails\\" + opcion, opcion);
				notificacion(salida, "C:\\AppServ\\www\\Periquito\\imagenes\\Thumb", "JPG");
			}
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

		initComponents();
		this.setVisible(true);

	}

	private void initComponents() {

		jTextField1 = new javax.swing.JTextField();

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/name.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		jComboBox1 = new javax.swing.JComboBox<>();

		jLabel2 = new javax.swing.JLabel();
		jLabel2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/tag.png")));
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0),
				new java.awt.Dimension(32767, 32767));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextField1.setFont(new Font("Tahoma", Font.BOLD, 24));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 28));

		jComboBox1.setFont(new Font("Tahoma", Font.BOLD, 24));
		jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[] { "Models", "Girls/Womans", "XXX", "Singers",
				"WebCam", "Films/Cartoon", "Dangles", "GIF", "Sado", "Earrings" }));

		jLabel2.setFont(new Font("Tahoma", Font.BOLD, 28));

		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Yeah\\Documents\\Periquito\\fix.png"));
		check5 = new JCheckBox("Fix");
		check5.setBounds(155, 150, 80, 60);
		check5.addChangeListener(this);
		check5.setFont(new Font("Tahoma", Font.BOLD, 25));
		getContentPane().add(check5);
		gif = new JCheckBox("Gif");
		gif.setBounds(155, 195, 80, 60);
		gif.addChangeListener(this);
		gif.setFont(new Font("Tahoma", Font.BOLD, 25));
		getContentPane().add(gif);
		mute = new JCheckBox("");
		mute.setBounds(490, 270, 80, 60);
		mute.addChangeListener(this);
		mute.setFont(new java.awt.Font("Tahoma", 1, 18));
		getContentPane().add(mute);
		btnNewButton.setBounds(10, 100, 100, 30);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
		btnNewButton.setEnabled(false);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
					mover_imagenes(opcion, navegador);
				} catch (IOException e1) {
					mensaje("Error al copiar las imagenes");
				}
			}
		});

		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/folder.png")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Runtime.getRuntime().exec("cmd /c start C:\\AppServ\\www\\Periquito\\imagenes");
				} catch (IOException e) {
					mensaje("Ruta invlida");
				}
			}
		});

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gif.png")));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Runtime.getRuntime().exec("cmd /c start C:\\AppServ\\www\\Periquito\\Hacer_gif\\img");
				} catch (IOException e1) {
					mensaje("Ruta invlida");
				}
			}
		});

		btnNewButton_2 = new JButton("");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/gif.gif")));

		btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String comprobacion = null;
				int navegador = 0;
				if (rdbtnNewRadioButton.isSelected()) {
					DriverSeleniumFirefox firefox = new DriverSeleniumFirefox();
					firefox.getDriver().get("http://localhost/Periquito/Hacer_gif/index.php");
					comprobacion = firefox.getDriver().findElement(By.name("salida")).getText();
					navegador = 1;
				}

				if (rdbtnChrome.isSelected()) {
					DriverSeleniumChrome chrome = new DriverSeleniumChrome();
					chrome.getDriver().get("http://localhost/Periquito/Hacer_gif/index.php");
					comprobacion = chrome.getDriver().findElement(By.name("salida")).getText();
					navegador = 2;
				}

				try {
					Runtime aplicacion = Runtime.getRuntime();
					switch (navegador) {
					case 1:
						aplicacion.exec("cmd.exe /K start cerrar.bat");
						break;
					case 2:
						aplicacion.exec("cmd.exe /K start cerrarchrome.bat");
						break;
					}

				} catch (IOException e2) {

				}

				if (comprobacion.compareTo("Folder empty") == 0) {
					mensaje("No hay imagenes en la carpeta de los gif");
				}

				else {

					try {
						int salida = salida("C:\\AppServ\\www\\Periquito\\Hacer_gif\\Output",
								"C:\\\\AppServ\\\\www\\\\Periquito\\imagenes", 9);
						notificacion(salida, "C:\\AppServ\\www\\Periquito\\imagenes", "JPG");
						try {
							Runtime.getRuntime().exec("cmd /c start C:\\AppServ\\www\\Periquito\\imagenes");
						} catch (IOException e1) {
							mensaje("Ruta invlida");
						}
					} catch (IOException e1) {

					}

				}
			}
		});

		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/sonido.png")));

		boton1 = new JButton("");
		boton1.setFont(new Font("Tahoma", Font.PLAIN, 6));
		boton1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/start.png")));
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					button1ActionPerformed(arg0);
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		});

		rdbtnNewRadioButton = new JRadioButton("Firefox");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 18));

		rdbtnChrome = new JRadioButton("Chrome");
		rdbtnChrome.setFont(new Font("Tahoma", Font.BOLD, 18));

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rdbtnNewRadioButton);
		grupo.add(rdbtnChrome);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addContainerGap()
										.addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(87))
								.addGroup(layout.createSequentialGroup().addGap(16)
										.addComponent(btnNewButton, 0, 0, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(btnNewButton_1,
										GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(jLabel1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 147,
												Short.MAX_VALUE))
								.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 71,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(boton1, GroupLayout.PREFERRED_SIZE, 239, Short.MAX_VALUE)
								.addComponent(jComboBox1, 0, 0, Short.MAX_VALUE)
								.addComponent(jTextField1, 222, 239, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, layout
										.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addGroup(layout.createSequentialGroup()
														.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 64,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, 92,
																Short.MAX_VALUE))
												.addGroup(layout.createSequentialGroup()
														.addComponent(rdbtnNewRadioButton).addGap(18)))
										.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
												.addGroup(layout.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(rdbtnChrome))
												.addGroup(Alignment.TRAILING,
														layout.createSequentialGroup()
																.addPreferredGap(ComponentPlacement.RELATED,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(lblNewLabel).addGap(22)))))
						.addGap(16)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel1)
										.addGroup(layout.createSequentialGroup().addGap(9).addComponent(jTextField1,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel2))
								.addGap(11).addComponent(boton1, 0, 0, Short.MAX_VALUE))
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING,
										layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(Alignment.LEADING)
														.addComponent(btnNewButton_1).addComponent(btnNewButton_3))
												.addGap(20)
												.addComponent(filler1, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(18))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel).addComponent(btnNewButton_2,
														GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(rdbtnChrome, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(rdbtnNewRadioButton))
										.addContainerGap()))));
		getContentPane().setLayout(layout);
		setSize(new Dimension(527, 419));
		setLocationRelativeTo(null);
	}

	@SuppressWarnings("static-access")
	private void button1ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		String comprobacion = jTextField1.getText();
		comprobacion = comprobacion.trim();

		if (comprobacion.length() != 0) {

			JOptionPane.showMessageDialog(null, "Espere...");
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
			
			if (rdbtnNewRadioButton.isSelected()) {
				DriverSeleniumFirefox prueba = new DriverSeleniumFirefox();
				prueba.getDriver().get("http://localhost/Periquito/index.php");
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
				prueba.getDriver().get("http://localhost/Periquito/index.php");
				prueba.getDriver().findElement(By.name("nombre")).sendKeys(comprobacion);
				Select drpCountry = new Select(prueba.getDriver().findElement(By.name("categoria")));
				drpCountry.selectByVisibleText(cat);
				prueba.getDriver().findElement(By.name("envio")).click();
				prueba.getDriver().findElement(By.name("si")).click();
				opcion = Integer.parseInt(prueba.getDriver().findElement(By.name("salida")).getText());
				navegador = 2;
			}

			mover_imagenes(opcion, navegador);

		} else {
			mensaje("Introduce un texto");
		}
	}

	public void stateChanged(ChangeEvent e) {

		if (gif.isSelected() == true) {
			check5.setEnabled(false);
			jComboBox1.setEnabled(false);
			btnNewButton.setEnabled(false);
			boton1.setEnabled(false);
			jTextField1.setEnabled(false);
			jLabel1.setEnabled(false);
			jLabel2.setEnabled(false);
			btnNewButton_2.setEnabled(true);
		} else {
			check5.setEnabled(true);
			jComboBox1.setEnabled(true);
			btnNewButton.setEnabled(false);
			jTextField1.setEnabled(true);
			jLabel1.setEnabled(true);
			jLabel2.setEnabled(true);
			boton1.setEnabled(true);
			btnNewButton_2.setEnabled(false);
		}
		if (check5.isSelected() == true) {
			btnNewButton.setEnabled(true);
			jLabel1.setEnabled(false);
			jLabel2.setEnabled(false);
			jTextField1.setEnabled(false);
			jComboBox1.setEnabled(false);
			gif.setEnabled(false);
			boton1.setEnabled(false);
		} else {
			btnNewButton.setEnabled(false);
			gif.setEnabled(true);
		}
	}
}
