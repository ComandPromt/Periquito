package periquito;

import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTextField;
import javax.swing.JFrame;

@SuppressWarnings("serial")

public class Config2 extends javax.swing.JFrame implements ActionListener, ChangeListener {
	private javax.swing.Box.Filler filler1;
	private javax.swing.JLabel jLabel1;
	static javax.swing.JTextField jTextField1;
	static JCheckBox mute;
	private JTextField textField;
	private JLabel lblThumbnails;

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

	public static String[] leerFicheroArray() {
		String[] salida = new String[2];
		String texto = "";
		int i = 0;
		try {
			FileReader flE = new FileReader("Config2.txt");
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

	public static String eliminarUltimoElemento(String cadena) {
		if (cadena.length() > 1) {
			if (cadena.charAt(cadena.length() - 1) == 92 || cadena.charAt(cadena.length() - 1) == 47) {
				cadena = cadena.substring(0, cadena.length() - 1);
			}
		
		}
		return cadena;
	}
	
	public void buscarArchivoConf() {
		File af = new File("config2.txt");

		if (af.exists()) {
			String[] lectura;
			lectura = leerFicheroArray();

			// Comprobar si el texto tiene un archivo php
			// o html o htm, si lo tiene no se cambia
			if (lectura[0] == null) {
				lectura[0] = "";
			}
			if (lectura[0] == null) {
				lectura[1] = "";
			}
			
			lectura[0]=eliminarUltimoElemento(lectura[0]);
			lectura[1]=eliminarUltimoElemento(lectura[1]);
			
			
			jTextField1.setText(lectura[0]);
			textField.setText(lectura[1]);
			
			guardarDatos(false);
		}
	}

	void guardarDatos(Boolean mensaje) {
		try {
			FileWriter flS = new FileWriter("Config2.txt");
			BufferedWriter fS = new BufferedWriter(flS);

			fS.write(jTextField1.getText());
			fS.newLine();
			fS.write(textField.getText());
			fS.close();
			if(mensaje) {
			mensaje2("Archivo guardado con exito!", "/sonidos/gong1.wav");}
			this.setVisible(false);
		} catch (IOException e) {
			if(mensaje) {
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

	public void notificacion(int salida, String directorio, String tipo) throws MalformedURLException {
		if (salida > 0) {
			mensaje(salida + " archivo/s copiado/s", "/sonidos/gong1.wav");
		} else {
			mensaje1("No hay archivos " + tipo + " en la carpeta " + directorio);
		}
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

	public Config2() {
		setTitle("Periquito v2.0 Config Remote");
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
		jLabel1.setIcon(new ImageIcon(Config2.class.getResource("/imagenes/folder.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0),
				new java.awt.Dimension(32767, 32767));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextField1.setFont(new Font("Tahoma", Font.BOLD, 24));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));
		mute = new JCheckBox("");
		mute.setBounds(575, 200, 80, 60);
		mute.addChangeListener(this);
		mute.setFont(new java.awt.Font("Tahoma", 1, 18));
		getContentPane().add(mute);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Config2.class.getResource("/imagenes/WAV_00002.png")));

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setToolTipText("");
		textField.setFont(new Font("Tahoma", Font.BOLD, 24));
		buscarArchivoConf();

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Config2.class.getResource("/imagenes/save.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				if(jTextField1.getText().substring(0, 2).equals("\\\\")) {
					jTextField1.setText(jTextField1.getText().substring(2,jTextField1.getText().length()));
				}
				if(textField.getText().substring(0, 2).equals("\\\\")) {
					textField.setText(textField.getText().substring(2,textField.getText().length()));
				}
								
				guardarDatos(true);

			}
		});
		
		lblThumbnails = new JLabel("Thumbnails");
		lblThumbnails.setIcon(new ImageIcon(Config2.class.getResource("/imagenes/folder.png")));
		lblThumbnails.setFont(new Font("Tahoma", Font.BOLD, 20));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(28)
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblThumbnails)
								.addComponent(jLabel1))
							.addGap(10)
							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(jTextField1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
								.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)))
						.addGroup(layout.createSequentialGroup()
							.addGap(498)
							.addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(97)))
					.addGap(57))
				.addGroup(layout.createSequentialGroup()
					.addContainerGap(368, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addGap(76)
					.addComponent(lblNewLabel)
					.addGap(81))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addGap(24)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel1))
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(35)
							.addComponent(lblThumbnails))
						.addGroup(layout.createSequentialGroup()
							.addGap(55)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(38)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(208)
							.addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel))
					.addContainerGap())
		);
		getContentPane().setLayout(layout);
		setSize(new Dimension(613, 340));
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

	@Override
	public void stateChanged(ChangeEvent e) {
	}
}