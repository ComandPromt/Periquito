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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Utils.Metodos;
import Utils.interfaz;

@SuppressWarnings("serial")

public class Config extends javax.swing.JFrame implements ActionListener, ChangeListener, interfaz {
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	static javax.swing.JTextField jTextField1;
	static JCheckBox mute;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public void mensaje(String mensaje, Boolean error) {
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

	public void buscarArchivoConf(String fichero) {
		File af = new File(fichero);

		if (af.exists()) {
			String[] lectura;
			try {
				lectura = Metodos.leerFicheroArray(fichero, 6);

				// Comprobar si el texto tiene un archivo php
				// o html o htm, si lo tiene no se cambia
				lectura[0] = Metodos.eliminarIndices(lectura[0]);
				lectura[2] = Metodos.eliminarIndices(lectura[2]);
				lectura[4] = Metodos.eliminarIndices(lectura[4]);
				if (lectura[0] == null) {
					lectura[0] = "";
				}
				if (lectura[2] == null) {
					lectura[2] = "";
				}
				if (lectura[4] == null) {
					lectura[4] = "";
				}
				lectura[0] = Metodos.eliminarUltimoElemento(lectura[0]);
				lectura[0] = Metodos.comprobarImagenes(lectura[0], "\\imagenes");
				lectura[0] = lectura[0].replace("\\\\", "\\");

				lectura[2] = Metodos.eliminarUltimoElemento(lectura[2]);
				lectura[2] = Metodos.comprobarImagenes(lectura[2], "Hacer_gif");
				lectura[2] = lectura[2].replace("\\\\", "\\");

				lectura[4] = Metodos.eliminarUltimoElemento(lectura[4]);
				lectura[4] = Metodos.comprobarImagenes(lectura[4], "GifFrames");
				lectura[4] = lectura[4].replace("\\\\", "\\");

				jTextField1.setText(lectura[0]);
				textField_2.setText(MenuPrincipal.convertirCadena(lectura[1], lectura[0]));
				textField.setText(lectura[2]);
				textField_3.setText(MenuPrincipal.convertirCadena(lectura[3], lectura[2]));
				textField_1.setText(lectura[4]);
				textField_4.setText(MenuPrincipal.convertirCadena(lectura[5], lectura[4]));
			} catch (ArrayIndexOutOfBoundsException e) {

			}
			guardarDatos(false);
		}
	}

	public void guardarDatos(Boolean mensaje) {
		try {
			FileWriter flS = new FileWriter("Config/Config.txt");
			BufferedWriter fS = new BufferedWriter(flS);

			fS.write(jTextField1.getText().trim());
			fS.newLine();
			fS.write(textField_2.getText().trim());
			fS.newLine();
			fS.write(textField.getText().trim());
			fS.newLine();
			fS.write(textField_3.getText().trim());
			fS.newLine();
			fS.write(textField_1.getText().trim());
			fS.newLine();
			fS.write(textField_4.getText().trim());
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

	public Config() {
		setAlwaysOnTop(true);
		setTitle("Periquito v3 Config Local");
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
		jLabel1.setIcon(new ImageIcon(Config.class.getResource("/imagenes/folder.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		jLabel2 = new javax.swing.JLabel();
		jLabel2.setText("GIF Animator");
		jLabel2.setIcon(new ImageIcon(Config.class.getResource("/imagenes/gif.png")));
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 24));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));

		jLabel2.setFont(new Font("Tahoma", Font.BOLD, 20));
		mute = new JCheckBox("");
		mute.setBounds(580, 410, 40, 20);
		mute.addChangeListener(this);
		mute.setFont(new java.awt.Font("Tahoma", 1, 18));
		getContentPane().add(mute);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Config.class.getResource("/imagenes/WAV_00002.png")));

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setToolTipText("");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JLabel lblFolderOfGif = new JLabel();
		lblFolderOfGif.setIcon(new ImageIcon(Config.class.getResource("/imagenes/GIF_Extract.png")));
		lblFolderOfGif.setText("GIF Extractor");
		lblFolderOfGif.setHorizontalAlignment(SwingConstants.CENTER);
		lblFolderOfGif.setFont(new Font("Tahoma", Font.BOLD, 20));

		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 24));

		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 24));

		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 24));

		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 24));
		buscarArchivoConf("Config/Config.txt");

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
								.addGap(68).addComponent(lblNewLabel))
						.addGroup(
								layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addGap(18)
														.addGroup(layout.createParallelGroup(Alignment.LEADING)
																.addComponent(jLabel1).addComponent(jLabel2)))
												.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(
														lblFolderOfGif, GroupLayout.PREFERRED_SIZE, 231,
														GroupLayout.PREFERRED_SIZE)))
										.addGap(10)
										.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(textField_4).addComponent(jTextField1)
												.addComponent(textField_2).addComponent(textField)
												.addComponent(textField_1).addComponent(textField_3,
														GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))))
						.addContainerGap(45, Short.MAX_VALUE)));
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
				.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton, 0, 0, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap(34, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(692, 516));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public void stateChanged(ChangeEvent e) {
	}
}