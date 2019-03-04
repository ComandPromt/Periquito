package periquito;

import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Utils.Metodos;
import Utils.interfaz;

@SuppressWarnings("serial")

public class Config2 extends javax.swing.JFrame implements ActionListener, ChangeListener, interfaz {
	private javax.swing.JLabel jLabel1;
	static javax.swing.JTextField jTextField1;
	static JCheckBox mute;
	private JTextField textField;
	private JLabel lblThumbnails;

	public void mensaje(String mensaje, Boolean error) {
		dispose();
		JLabel alerta = new JLabel(mensaje);
		alerta.setFont(new Font("Arial", Font.BOLD, 18));
		AudioClip clip;
		if (error) {
			clip = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/duck-quack1.wav"));
		} else {
			clip = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/gong1.wav"));
		}

		if (mute.isSelected() == true) {
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

	public void buscarArchivoConf() {
		File af = new File("Config/Config2.txt");

		if (af.exists()) {
			String[] lectura;
			try {
				lectura = Metodos.leerFicheroArray("Config/Config2.txt", 2);

				if (lectura[0] == null) {
					lectura[0] = "";
				}
				if (lectura[1] == null) {
					lectura[1] = "";
				}

				lectura[0] = Metodos.eliminarUltimoElemento(lectura[0]);
				lectura[1] = Metodos.eliminarUltimoElemento(lectura[1]);

				jTextField1.setText(lectura[0]);
				textField.setText(lectura[1]);
			} catch (ArrayIndexOutOfBoundsException e) {

			}
			guardarDatos(false);
		}
	}

	public void guardarDatos(Boolean mensaje) {
		try {
			FileWriter flS = new FileWriter("Config/Config2.txt");
			BufferedWriter fS = new BufferedWriter(flS);

			fS.write(jTextField1.getText().trim());
			fS.newLine();
			fS.write(textField.getText().trim());
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

	public Config2() {
		setAlwaysOnTop(true);
		setTitle("Periquito v3 Config Remoto");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	public void initComponents() {

		jTextField1 = new javax.swing.JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.CENTER);
		jTextField1.setToolTipText("");

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText("Images");
		jLabel1.setIcon(new ImageIcon(Config2.class.getResource("/imagenes/folder.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 24));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));
		mute = new JCheckBox("");
		mute.setBounds(500, 205, 40, 20);
		mute.addChangeListener(this);
		mute.setFont(new java.awt.Font("Tahoma", 1, 18));
		getContentPane().add(mute);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Config2.class.getResource("/imagenes/WAV_00002.png")));

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setToolTipText("");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		buscarArchivoConf();

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Config2.class.getResource("/imagenes/save.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jTextField1.getText() != null && jTextField1.getText().length() >= 2 && textField.getText() != null
						&& textField.getText().length() >= 2) {
					String comprobacion1 = jTextField1.getText().replace("  ", " ");
					String comprobacion2 = textField.getText().replace("  ", " ");
					comprobacion1 = jTextField1.getText().replace(" ", "");
					comprobacion2 = textField.getText().replace(" ", "");
					comprobacion1 = jTextField1.getText().trim();
					comprobacion2 = textField.getText().trim();
					if (comprobacion1.length() >= 2 && comprobacion2.length() >= 2) {
						if (comprobacion1.substring(0, 2).equals("\\\\")) {
							jTextField1.setText(comprobacion1.substring(2, comprobacion1.length()));
						}
						if (comprobacion2.substring(0, 2).equals("\\\\")) {
							textField.setText(comprobacion2.substring(2, comprobacion2.length()));
						}
						guardarDatos(true);
					}
				}
			}
		});

		lblThumbnails = new JLabel("Thumbnails");
		lblThumbnails.setIcon(new ImageIcon(Config2.class.getResource("/imagenes/folder.png")));
		lblThumbnails.setFont(new Font("Tahoma", Font.BOLD, 20));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addGap(28)
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblThumbnails)
								.addComponent(jLabel1))
						.addGap(10)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE).addComponent(
										jTextField1, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addGap(63).addComponent(lblNewLabel).addGap(12)))
				.addGap(41)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addGap(24)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel1))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(35).addComponent(lblThumbnails))
						.addGroup(layout.createSequentialGroup().addGap(55).addComponent(textField,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(18).addComponent(lblNewLabel))
						.addGroup(layout.createSequentialGroup().addGap(18).addComponent(btnNewButton)))
				.addGap(42)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(560, 333));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
	}

	public void stateChanged(ChangeEvent e) {
	}
}