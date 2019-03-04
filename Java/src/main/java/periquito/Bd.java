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

public class Bd extends javax.swing.JFrame implements ActionListener, ChangeListener, interfaz {
	private javax.swing.JLabel jLabel1;
	static javax.swing.JTextField jTextField1;
	static JCheckBox mute;
	private static JTextField textField;
	private JLabel lblThumbnails;
	private JLabel lblBd;
	private JTextField base;
	String[] lectura;
	private JLabel lblPrefijoDeTablas;
	private JTextField textField_1;
	private JTextField textField_2;

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

	public void buscarArchivoConf() throws IOException {
		File af = new File("Config/Bd.txt");

		if (af.exists()) {

			try {
				lectura = Metodos.leerFicheroArray("Config/Bd.txt", 5);
				if (lectura[0] == null) {
					lectura[0] = "";
				}
				if (lectura[1] == null) {
					lectura[1] = "";
				}
				if (lectura[2] == null) {
					lectura[2] = "";
				}
				base.setText(lectura[0]);
				jTextField1.setText(lectura[1]);
				textField.setText(lectura[2]);
				textField_1.setText(lectura[3]);

			} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {

			}

			guardarDatos(false);
		}
	}

	void guardarDatos(Boolean mensaje) {
		try {
			FileWriter flS = new FileWriter("Config/Bd.txt");
			BufferedWriter fS = new BufferedWriter(flS);
			fS.write(base.getText().trim());
			fS.newLine();
			fS.write(jTextField1.getText().trim());
			fS.newLine();
			fS.write(textField.getText().trim());
			fS.newLine();
			fS.write(textField_1.getText().trim());
			fS.newLine();
			fS.write(textField_2.getText().trim());
			fS.close();

			if (mensaje) {
				mensaje("Archivo guardado con exito!", false);
			}

		} catch (IOException e) {
			if (mensaje) {
				mensaje("Error al crear el fichero de configuracion", true);
			}
		}
	}

	public Bd() throws IOException {
		setAlwaysOnTop(true);
		setTitle("Periquito v3 Config DB");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	public void initComponents() throws IOException {
		File bd = new File("Config/Bd.txt");
		if (!bd.exists()) {
			lectura = Metodos.leerFicheroArray("Config/Bd.txt", 5);
			Metodos.crearFichero("Config/Bd.txt", "4images\r\n" + "root\r\n" + "rootroot\r\n" + "4images_");
		}
		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText("Usuario");
		jLabel1.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/user.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);
		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));
		mute = new JCheckBox("");
		mute.setBounds(610, 305, 20, 20);
		mute.addChangeListener(this);
		mute.setFont(new java.awt.Font("Tahoma", 1, 18));
		getContentPane().add(mute);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/WAV_00002.png")));

		base = new JTextField();
		base.setToolTipText("");
		base.setHorizontalAlignment(SwingConstants.CENTER);
		base.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField = new javax.swing.JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setToolTipText("");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		jTextField1 = new javax.swing.JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.CENTER);
		jTextField1.setToolTipText("");
		jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField_2 = new JTextField();
		textField_2.setText("3306");
		textField_2.setToolTipText("");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		buscarArchivoConf();
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/save.png")));
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (jTextField1.getText() != null && jTextField1.getText().length() >= 2 && textField.getText() != null
						&& textField.getText().length() >= 2) {

					String comprobacion1 = jTextField1.getText().replace("  ", " ");
					String comprobacion2 = textField.getText().replace("  ", " ");

					comprobacion1 = jTextField1.getText().replace(" ", "");
					comprobacion1 = jTextField1.getText().trim();

					comprobacion2 = textField.getText().replace(" ", "");
					comprobacion2 = textField.getText().trim();

					int comprobacion3 = Integer.parseInt(textField_2.getText().trim());

					if (comprobacion1.length() >= 2 && comprobacion2.length() >= 2 && comprobacion3 > 0) {

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
		lblThumbnails = new JLabel("Contraseña");
		lblThumbnails.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/user_pass.png")));
		lblThumbnails.setFont(new Font("Tahoma", Font.BOLD, 20));

		lblBd = new JLabel();
		lblBd.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/db.png")));
		lblBd.setText("BD");
		lblBd.setHorizontalAlignment(SwingConstants.CENTER);
		lblBd.setFont(new Font("Tahoma", Font.BOLD, 20));

		lblPrefijoDeTablas = new JLabel();
		lblPrefijoDeTablas.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/db.png")));
		lblPrefijoDeTablas.setText("Prefijo tablas");
		lblPrefijoDeTablas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrefijoDeTablas.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/port.png")));
		lblPuerto.setFont(new Font("Tahoma", Font.BOLD, 20));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel1)
								.addComponent(lblThumbnails).addComponent(lblPrefijoDeTablas).addComponent(lblBd)
								.addComponent(lblPuerto, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
						.addGap(36)
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addGap(27)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addGap(26).addComponent(lblNewLabel).addContainerGap())
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														382, Short.MAX_VALUE)
												.addComponent(base, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 382,
														Short.MAX_VALUE)
												.addComponent(textField_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														382, Short.MAX_VALUE)
												.addComponent(jTextField1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														382, Short.MAX_VALUE))
										.addGap(30)))));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(base, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBd, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrefijoDeTablas, GroupLayout.PREFERRED_SIZE, 64,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(11)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(
								jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblThumbnails)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPuerto, GroupLayout.PREFERRED_SIZE, 64,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(btnNewButton).addComponent(lblNewLabel))
						.addGap(143)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(666, 417));
		setLocationRelativeTo(null);
	}

	public static javax.swing.JTextField getjTextField1() {
		return jTextField1;
	}

	public static void setjTextField1(javax.swing.JTextField jTextField1) {
		Bd.jTextField1 = jTextField1;
	}

	public static JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		Bd.textField = textField;
	}

	public void actionPerformed(ActionEvent arg0) {
	}

	public void stateChanged(ChangeEvent e) {
	}
}