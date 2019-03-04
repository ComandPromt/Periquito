package periquito;

import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Utils.Metodos;
import Utils.interfaz;

@SuppressWarnings("serial")

public class Backup extends javax.swing.JFrame implements ActionListener, ChangeListener, interfaz {
	private javax.swing.JLabel jLabel1;
	static javax.swing.JTextField jTextField1;

	public void mensaje(String mensaje, Boolean error) {
		JLabel alerta = new JLabel(mensaje);
		alerta.setFont(new Font("Arial", Font.BOLD, 18));
		AudioClip clip;
		if (error) {
			clip = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/duck-quack1.wav"));
		} else {
			clip = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/gong1.wav"));
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

	public void buscarArchivoConf() {
		File af = new File("Config/Backup.txt");

		if (af.exists()) {
			String[] lectura;
			try {
				lectura = Metodos.leerFicheroArray("Config/Backup.txt", 1);

				if (lectura[0] == null) {
					lectura[0] = "1";
				}

				jTextField1.setText(lectura[0]);

			} catch (ArrayIndexOutOfBoundsException e) {

			}
			guardarDatos(false);
		}
	}

	void guardarDatos(Boolean mensaje) {
		try {
			FileWriter flS = new FileWriter("Config/Backup.txt");
			BufferedWriter fS = new BufferedWriter(flS);

			fS.write(jTextField1.getText().trim());
			fS.newLine();

			fS.close();
			dispose();

			if (mensaje) {
				mensaje("Archivo guardado con exito!", false);
			}

		} catch (IOException e) {
			if (mensaje) {
				mensaje("Error al crear el fichero de configuracion", true);
			}
		}
	}

	public Backup() {
		setTitle("Periquito v3 Config Backup");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	public void initComponents() {

		jTextField1 = new javax.swing.JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.CENTER);
		jTextField1.setToolTipText("");

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText("Backup");
		jLabel1.setIcon(new ImageIcon(Backup.class.getResource("/imagenes/folder.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 24));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));
		buscarArchivoConf();

		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				File[] files = Metodos.seleccionar(1, "Elija la carpeta para hacer el backup de la bd",
						"Elija una carpeta para hacer el backup de la bd");

				try {
					String texto = files[0].getCanonicalPath();
					jTextField1.setText(texto);
					guardarDatos(false);
				} catch (Exception e1) {

				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(Backup.class.getResource("/imagenes/save.png")));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1).addGap(26)
						.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
						.addGap(31)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(45, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(24)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 64, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING,
								layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
				.addGap(223)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(532, 129));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
	}

	public void stateChanged(ChangeEvent e) {
	}
}