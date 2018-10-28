package periquito;

import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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
import javax.swing.JTextField;
import javax.swing.JFrame;

@SuppressWarnings("serial")

public class Config extends javax.swing.JFrame implements ActionListener, ChangeListener {
	private javax.swing.Box.Filler filler1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	static javax.swing.JTextField jTextField1;
	static JCheckBox mute;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public void mensaje1(String mensaje) {
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

	public static String[] leerFicheroArray(String fichero, int longitud) {
		String[] salida = new String[longitud];
		String texto = "";
		int i = 0;
		try {
			FileReader flE = new FileReader(fichero);
			BufferedReader fE = new BufferedReader(flE);
			while (texto != null) {
				texto = fE.readLine();
				if (texto != null) {
					salida[i] = texto;
					i++;
				}
			}
			fE.close();
		} catch (IOException e) {

		}
		return salida;
	}

	String comprobarImagenes(String cadena, String comprobacion) {

		if (cadena.length() >= 10
				&& cadena.substring(cadena.length() - 9, cadena.length()).compareTo(comprobacion) != 0) {
			if (comprobacion.substring(0, 1).compareTo("\\") != 0) {
				cadena += "\\" + comprobacion;
			} else {

				cadena += comprobacion;

			}
			cadena = cadena.replace("\\\\", "\\");
		}
		return cadena;
	}

	public static String eliminarUltimoElemento(String cadena) {
		if (cadena.length() > 1) {
			if (cadena.charAt(cadena.length() - 1) == 92 || cadena.charAt(cadena.length() - 1) == 47) {
				cadena = cadena.substring(0, cadena.length() - 1);
			}

		}
		return cadena;
	}

	public void buscarArchivoConf(String fichero) {
		File af = new File(fichero);

		if (af.exists()) {
			String[] lectura;
			lectura = leerFicheroArray(fichero, 6);

			// Comprobar si el texto tiene un archivo php
			// o html o htm, si lo tiene no se cambia
			lectura[0] = MenuPrincipal.eliminarIndices(lectura[0]);
			lectura[2] = MenuPrincipal.eliminarIndices(lectura[2]);
			lectura[4] = MenuPrincipal.eliminarIndices(lectura[4]);
			if (lectura[0] == null) {
				lectura[0] = "";
			}
			if (lectura[0] == null) {
				lectura[1] = "";
			}
			lectura[0] = eliminarUltimoElemento(lectura[0]);
			lectura[0] = comprobarImagenes(lectura[0], "\\imagenes");
			lectura[0] = lectura[0].replace("\\\\", "\\");

			lectura[2] = eliminarUltimoElemento(lectura[2]);
			lectura[2] = comprobarImagenes(lectura[2], "Hacer_gif");
			lectura[2] = lectura[2].replace("\\\\", "\\");

			lectura[4] = eliminarUltimoElemento(lectura[4]);
			lectura[4] = comprobarImagenes(lectura[4], "GifFrames");
			lectura[4] = lectura[4].replace("\\\\", "\\");

			jTextField1.setText(lectura[0]);
			textField_2.setText(MenuPrincipal.convertirCadena(lectura[1], lectura[0]));
			textField.setText(lectura[2]);
			textField_3.setText(MenuPrincipal.convertirCadena(lectura[3], lectura[2]));
			textField_1.setText(lectura[4]);
			textField_4.setText(MenuPrincipal.convertirCadena(lectura[5], lectura[4]));
			guardarDatos(false);
		}
	}

	void guardarDatos(Boolean mensaje) {
		try {
			FileWriter flS = new FileWriter("Config.txt");
			BufferedWriter fS = new BufferedWriter(flS);

			fS.write(jTextField1.getText());
			fS.newLine();
			fS.write(textField_2.getText());
			fS.newLine();
			fS.write(textField.getText());
			fS.newLine();
			fS.write(textField_3.getText());
			fS.newLine();
			fS.write(textField_1.getText());
			fS.newLine();
			fS.write(textField_4.getText());
			fS.close();
			if (mensaje) {
				mensaje2("Archivo guardado con exito!", "/sonidos/gong1.wav");
			}
			this.setVisible(false);
		} catch (IOException e) {
			if (mensaje) {
				mensaje1("Error al crear el fichero de configuracion");
			}
		}
	}

	public void abrirCarpeta(String ruta) {
		try {

			Runtime.getRuntime().exec("cmd /c start " + ruta);
		} catch (IOException e) {
			mensaje1("Ruta invalida");
		}
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

	public static void cerrarNavegador() {
		try {
			Runtime aplicacion = Runtime.getRuntime();
			aplicacion.exec("cmd.exe /K start C:\\cerrar.bat");
		} catch (IOException e2) {
		}
	}

	public void notificacion(int salida, String directorio, String tipo) throws MalformedURLException {
		if (salida > 0) {
			mensaje(salida + " archivo/s copiado/s", "/sonidos/gong1.wav");
		} else {
			mensaje1("No hay archivos " + tipo + " en la carpeta " + directorio);
		}
	}

	public static byte[] createChecksum(String filename) throws Exception {
		InputStream fis = new FileInputStream(filename);

		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("SHA-256");
		int numRead;

		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);

		fis.close();
		return complete.digest();
	}

	public static int salida(String origen, String destino, int opcion) throws IOException {

		LinkedList<String> imagenes = new LinkedList<String>();

		if (opcion != 9 || (opcion == 9 && origen.contains("Thumb"))) {
			imagenes = directorio(origen, ".jpg");
		}

		else {
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

	public static String getSHA256Checksum(String filename) throws Exception {
		byte[] b = createChecksum(filename);
		String result = "";

		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	public static void eliminarDuplicados(String ruta) {

		String[] lectura;
		lectura = Config.leerFicheroArray("Config.txt", 6);

		LinkedList<String> imagenes = new LinkedList<String>();
		imagenes = Config.directorio(lectura[0].substring(0, lectura[0].length() - 8) + ruta, "jpg");

		if (imagenes.size() > 0) {
			Iterator<String> it = imagenes.iterator();
			ArrayList<String> scanimagenes = new ArrayList<String>();
			String imagen;

			while (it.hasNext()) {
				try {

					imagen = getSHA256Checksum(
							lectura[0].substring(0, lectura[0].length() - 8) + ruta + "\\" + it.next());

					scanimagenes.add(imagen);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			if (imagenes.size() == scanimagenes.size()) {

				for (int i = 0; i < scanimagenes.size(); i++) {

					if (scanimagenes.get(i) == scanimagenes.get(scanimagenes.size() - 1)) {
						i--;
					}

					if (scanimagenes.get(i) == scanimagenes.get(i++)) {
						System.out.println(i + "\n");
						File fichero = new File(
								lectura[0].substring(0, lectura[0].length() - 8) + ruta + "\\" + imagenes.get(i++));
						fichero.delete();
					}

				}
			}
		}

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

	public Config() {
		setTitle("Periquito v2.4 Config Local");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);

	}

	public void mensaje2(String mensaje, String url) {
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

	private void initComponents() {

		jTextField1 = new javax.swing.JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.LEFT);
		jTextField1.setToolTipText("");

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText("Images");
		jLabel1.setIcon(new ImageIcon(Config.class.getResource("/imagenes/folder.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel2 = new javax.swing.JLabel();
		jLabel2.setText("GIF Animator");
		jLabel2.setIcon(new ImageIcon(Config.class.getResource("/imagenes/gif.png")));
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0),
				new java.awt.Dimension(32767, 32767));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextField1.setFont(new Font("Tahoma", Font.BOLD, 24));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));

		jLabel2.setFont(new Font("Tahoma", Font.BOLD, 20));
		mute = new JCheckBox("");
		mute.setBounds(595, 402, 80, 60);
		mute.addChangeListener(this);
		mute.setFont(new java.awt.Font("Tahoma", 1, 18));
		getContentPane().add(mute);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Config.class.getResource("/imagenes/WAV_00002.png")));

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setToolTipText("");
		textField.setFont(new Font("Tahoma", Font.BOLD, 24));

		JLabel lblFolderOfGif = new JLabel();
		lblFolderOfGif.setIcon(new ImageIcon(Config.class.getResource("/imagenes/GIF_Extract.png")));
		lblFolderOfGif.setText("GIF Extractor");
		lblFolderOfGif.setHorizontalAlignment(SwingConstants.CENTER);
		lblFolderOfGif.setFont(new Font("Tahoma", Font.BOLD, 20));

		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 24));

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 24));

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 24));

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 24));
		buscarArchivoConf("Config.txt");

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Config.class.getResource("/imagenes/save.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				guardarDatos(true);

			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(400)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addGap(35)
								.addComponent(
										filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(33).addComponent(lblNewLabel))
						.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(18)
										.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel1)
												.addComponent(jLabel2)))
								.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(lblFolderOfGif,
										GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)))
								.addGap(10)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(jTextField1, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
										.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
										.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
										.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))))
						.addGap(13)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addGap(24)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(jTextField1,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE).addGap(22)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel2).addComponent(textField,
						GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(
						textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(32)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFolderOfGif, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(69).addComponent(filler1,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(18)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblNewLabel, Alignment.LEADING))))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(658, 519));
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

	@Override
	public void stateChanged(ChangeEvent e) {
	}
}
