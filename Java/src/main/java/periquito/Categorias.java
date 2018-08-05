package periquito;

import java.applet.AudioClip;
import java.awt.Rectangle;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Categorias extends javax.swing.JFrame implements ActionListener, ChangeListener {
	static JFrame frmprincipal;
	JTextArea txtarea;
	JScrollPane scroll;

	private JCheckBox mute;
	static JTextPane textPane;
	private JLabel lblNewLabel_1;

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

	void guardarDatos(Boolean mensaje) {
		try {
			FileWriter flS = new FileWriter("Categorias.txt");
			BufferedWriter fS = new BufferedWriter(flS);
			fS.write(textPane.getText());
			fS.close();

			if (mensaje) {
				mensaje2("Archivo guardado con exito!", "/sonidos/gong1.wav");
			}
			frmprincipal.setVisible(false);
		} catch (IOException e) {
			if (mensaje) {
				mensaje1("Error al crear el fichero de configuracion");
			}
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

	public void notificacion(int salida, String directorio, String tipo) throws MalformedURLException {
		if (salida > 0) {
			mensaje(salida + " archivo/s copiado/s", "/sonidos/gong1.wav");
		} else {
			mensaje1("No hay archivos " + tipo + " en la carpeta " + directorio);
		}
	}

	public void buscarArchivoConf(String fichero, int longitud) {
		File af = new File(fichero);
		if (af.exists() && longitud > 0) {
			String[] lectura;
			lectura = Config.leerFicheroArray(fichero, longitud);
			String texto = lectura[0] + "\r\n";
			for (int x = 1; x < lectura.length; x++) {
				if (x + 1 == lectura.length) {
					texto += lectura[x];
				} else {
					texto += lectura[x] + "\r\n";
				}

			}

			textPane.setText(texto);
		}
	}

	public static int salida(String origen, String destino, int opcion) throws IOException {

		LinkedList<String> imagenes = new LinkedList<String>();

		if (opcion != 9 || (opcion == 9 && origen.contains("Thumb"))) {
			imagenes = Config.directorio(origen, ".jpg");
		}

		else {
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

	Categorias() {
		frmprincipal = new JFrame();
		frmprincipal.setTitle("Periquito v2.2 Categorias");
		frmprincipal.setType(Type.UTILITY);
		frmprincipal.setResizable(false);
		frmprincipal.setSize(451, 287);

		frmprincipal.getContentPane().setLayout(null);
		textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.BOLD, 22));

		scroll = new JScrollPane(textPane);
		scroll.setBounds(new Rectangle(25, 40, 300, 200));
		frmprincipal.getContentPane().add(scroll);

		mute = new JCheckBox("");
		mute.setBounds(392, 40, 21, 23);
		frmprincipal.getContentPane().add(mute);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Categorias.class.getResource("/imagenes/WAV_00002.png")));
		lblNewLabel.setBounds(336, 40, 64, 64);
		frmprincipal.getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Introduce las categoría en una línea");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setBounds(10, 5, 417, 23);
		frmprincipal.getContentPane().add(lblNewLabel_1);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Categorias.class.getResource("/imagenes/save.png")));
		btnNewButton.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent arg0) {
				guardarDatos(true);
				MenuPrincipal.ponerCategorias();
			}
		});
		btnNewButton.setBounds(351, 123, 62, 64);
		frmprincipal.getContentPane().add(btnNewButton);

		buscarArchivoConf("Categorias.txt", MenuPrincipal.numeroLineas("Categorias.txt"));

		frmprincipal.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
}
