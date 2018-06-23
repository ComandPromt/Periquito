package periquito;

import java.io.File;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SwingConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import java.awt.Dimension;

@SuppressWarnings("serial")

public class MenuPrincipal extends javax.swing.JFrame implements ActionListener, ChangeListener {

	private java.awt.Button button1;
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

	public void actionPerformed(ActionEvent e) {
	}

	public void mensaje(String mensaje, String url) {
		AudioClip clip;
		try {
			clip = Applet.newAudioClip(new URL(url));
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
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null, "ERROR EN LA URL");
		}
	}

	public void notificacion(String mensaje) throws MalformedURLException {
		mensaje(mensaje, "http://www.wavlist.com/soundfx/011/duck-quack1.wav");
	}

	public void notificacion(int salida, String directorio, String tipo) throws MalformedURLException {
		if (salida > 0) {
			mensaje(salida + " archivo/s copiado/s", "http://www.wavlist.com/soundfx/026/gong1.wav");
		} else {
			mensaje(" No hay archivos " + tipo + " en la carpeta " + directorio,
					"http://www.wavlist.com/soundfx/011/duck-quack1.wav");
		}
	}

	private void mover_imagenes(int opcion) throws IOException {

		Runtime aplicacion = Runtime.getRuntime();
		aplicacion.exec("cmd.exe /K start C:/cerrar.bat");

		if (opcion > 0) {
			int salida;
			if (opcion == 9) {
				salida = salida("C:\\AppServ\\www\\Periquito\\imagenes\\gif",
						"\\\\192.168.1.2\\web\\folder\\data\\media\\" + opcion, opcion);
				notificacion(salida, "C:\\AppServ\\www\\Periquito\\imagenes\\gif", "GIF");
				salida = salida("C:\\AppServ\\www\\Periquito\\imagenes\\gif\\Thumb",
						"\\\\192.168.1.2\\web\\folder\\data\\thumbnails\\" + opcion, opcion);
				notificacion(salida, "C:\\AppServ\\www\\Periquito\\imagenes\\gif\\Thumb", "JPG");
			} else {
				salida = salida("C:\\AppServ\\www\\Periquito\\imagenes",
						"\\\\192.168.1.2\\web\\folder\\data\\media\\" + opcion, opcion);
				notificacion(salida, "C:\\AppServ\\www\\Periquito\\imagenes", "JPG");
				salida = salida("C:\\AppServ\\www\\Periquito\\imagenes\\Thumb",
						"\\\\192.168.1.2\\web\\folder\\data\\thumbnails\\" + opcion, opcion);
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
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		jComboBox1 = new javax.swing.JComboBox<>();

		jLabel2 = new javax.swing.JLabel();
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);

		button1 = new java.awt.Button();
		filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0),
				new java.awt.Dimension(32767, 32767));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextField1.setFont(new Font("Tahoma", Font.BOLD, 24));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 28));
		jLabel1.setText("Nombre");

		jComboBox1.setFont(new Font("Tahoma", Font.BOLD, 24));
		jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[] { "Models", "Girls/Womans", "XXX", "Singers",
				"WebCam", "Films/Cartoon", "Dangles", "GIF", "Sado", "Earrings" }));

		jLabel2.setFont(new Font("Tahoma", Font.BOLD, 28));
		jLabel2.setText("Categoria");

		button1.setActionCommand("Enviar");
		button1.setFont(new java.awt.Font("Dialog", 1, 24));
		button1.setLabel("Enviar");
		button1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				try {
					button1ActionPerformed(evt);
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		});

		btnNewButton = new JButton("Arreglar");
		check5 = new JCheckBox("Fix");
		check5.setBounds(160, 93, 80, 60);
		check5.addChangeListener(this);
		check5.setFont(new java.awt.Font("Tahoma", 1, 18));
		getContentPane().add(check5);
		gif = new JCheckBox("Gif");
		gif.setBounds(250, 130, 80, 60);
		gif.addChangeListener(this);
		gif.setFont(new java.awt.Font("Tahoma", 1, 18));
		getContentPane().add(gif);
		mute = new JCheckBox("Mute");
		mute.setBounds(160, 130, 80, 60);
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

				case "Option 1":
					opcion = 1;
					break;

				case "Option 2":
					opcion = 2;
					break;

				case "Option 3":
					opcion = 3;
					break;

				case "Option 4":
					opcion = 4;
					break;

				case "Option 5":
					opcion = 5;
					break;

				case "Option 6":
					opcion = 6;
					break;

				case "Option 7":
					opcion = 7;
					break;

				case "Option 9":
					opcion = 9;
					break;

				case "Option 10":
					opcion = 10;
					break;

				case "Option 11":
					opcion = 11;
					break;

				}
				try {
					mover_imagenes(opcion);
				} catch (IOException e1) {
					try {
						notificacion("Error al copiar las imagenes");
					} catch (MalformedURLException e2) {

					}
				}
			}
		});

		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		JButton btnNewButton_1 = new JButton("Imagenes");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Runtime.getRuntime().exec("cmd /c start C:\\AppServ\\www\\Periquito\\imagenes");
				} catch (IOException e) {
					try {
						notificacion("Ruta invlida");
					} catch (MalformedURLException e1) {

					}
				}
			}
		});

		JButton btnNewButton_3 = new JButton("Carpeta GIF");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Runtime.getRuntime().exec("cmd /c start C:\\AppServ\\www\\Periquito\\Hacer_gif\\img");
				} catch (IOException e1) {
					try {
						notificacion("Ruta invlida");
					} catch (MalformedURLException e11) {
					}
				}
			}
		});

		btnNewButton_2 = new JButton("Hacer GIF");

		btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				DriverSeleniumFirefox prueba = new DriverSeleniumFirefox();
				prueba.getDriver().get("http://localhost/Periquito/Hacer_gif/index.php");

				try {
					Runtime aplicacion = Runtime.getRuntime();
					aplicacion.exec("cmd.exe /K start C:/cerrar.bat");

				} catch (IOException e2) {

				}

				try {
					int salida = salida("C:\\AppServ\\www\\Periquito\\Hacer_gif\\Output",
							"C:\\\\AppServ\\\\www\\\\Periquito\\imagenes", 9);
					notificacion(salida, "C:\\AppServ\\www\\Periquito\\imagenes", "JPG");
					try {
						Runtime.getRuntime().exec("cmd /c start C:\\AppServ\\www\\Periquito\\imagenes");
					} catch (IOException e1) {
						try {
							notificacion("Ruta invlida");
						} catch (MalformedURLException e11) {

						}
					}
				} catch (IOException e1) {

				}

			}
		});

		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup().addContainerGap()
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 139,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(filler1, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(btnNewButton_1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton_3)))
						.addGap(66)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
								.addComponent(button1, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
								.addComponent(jComboBox1, 0, 223, Short.MAX_VALUE)
								.addComponent(jTextField1, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel1))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel2))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(button1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
				.addGap(14)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(39))
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton_1)
								.addComponent(btnNewButton_3).addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE,
										39, GroupLayout.PREFERRED_SIZE)))));
		getContentPane().setLayout(layout);
		setSize(new Dimension(585, 266));
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
			DriverSeleniumFirefox prueba = new DriverSeleniumFirefox();
			prueba.getDriver().get("http://localhost/Periquito/index.php");
			prueba.getDriver().findElement(By.name("nombre")).sendKeys(comprobacion);
			Select drpCountry = new Select(prueba.getDriver().findElement(By.name("categoria")));
			drpCountry.selectByVisibleText(cat);
			prueba.getDriver().findElement(By.name("envio")).click();
			prueba.getDriver().findElement(By.name("si")).click();
			int opcion = Integer.parseInt(prueba.getDriver().findElement(By.name("salida")).getText());
			mover_imagenes(opcion);

		} else {
			notificacion("Introduce un texto");
		}
	}

	public void stateChanged(ChangeEvent e) {

		if (gif.isSelected() == true) {
			check5.setEnabled(false);
			jComboBox1.setVisible(false);
			btnNewButton.setEnabled(false);
			button1.setEnabled(false);
			jTextField1.setEnabled(false);
		} else {
			check5.setEnabled(true);
			jComboBox1.setVisible(true);
			btnNewButton.setEnabled(false);
			button1.setEnabled(true);
			jTextField1.setEnabled(true);
		}
		if (check5.isSelected() == true) {
			btnNewButton.setEnabled(true);
			btnNewButton_2.setEnabled(false);
			jTextField1.setEnabled(false);
			gif.setEnabled(false);
		} else {
			btnNewButton_2.setEnabled(true);
			btnNewButton.setEnabled(false);
			gif.setEnabled(true);
		}
	}
}
