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
import java.sql.SQLException;

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
	private JTextField direccion;

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
			this.dispose();
			JOptionPane.showMessageDialog(null, alerta, "Success", JOptionPane.INFORMATION_MESSAGE);

		}
		option = JOptionPane.CLOSED_OPTION;
		if (option == -1) {
			clip.stop();
		}
	}

	public void buscarArchivoConf() throws IOException, SQLException {
		File af = new File("Config/Bd.txt");

		if (af.exists()) {

			try {
				lectura = Metodos.leerFicheroArray("Config/Bd.txt", 6);
				if (lectura[0] == null) {
					lectura[0] = "";
				}
				if (lectura[1] == null) {
					lectura[1] = "";
				}
				if (lectura[2] == null) {
					lectura[2] = "";
				}
				direccion.setText(lectura[5]);
				base.setText(lectura[0]);
				jTextField1.setText(lectura[1]);
				textField.setText(lectura[2]);
				textField_1.setText(lectura[3]);

			} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {

			}

		}
	}

	public void guardarDatos(Boolean mensaje) throws SQLException {
		dispose();
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
			fS.newLine();
			fS.write(direccion.getText().trim());

			fS.close();

			if (mensaje) {
				mensaje("Archivo guardado con exito!", false);
			}
			if (Metodos.comprobarConexion()) {
				Metodos.ponerCategoriasBd(MenuPrincipal.jComboBox1);
			}
		} catch (IOException e) {
			e.printStackTrace();
			if (mensaje) {
				mensaje("Error al crear el fichero de configuracion", true);
			}
		}
	}

	public Bd() throws IOException {
		setTitle("Periquito v3 Config DB");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	public void initComponents() throws IOException {

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText("Usuario de la BD");
		jLabel1.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/user.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);
		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));
		mute = new JCheckBox("");
		mute.setBounds(610, 460, 20, 20);
		mute.addChangeListener(this);
		mute.setFont(new java.awt.Font("Tahoma", 1, 18));
		getContentPane().add(mute);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/WAV_00002.png")));

		base = new JTextField();
		base.setToolTipText("");
		base.setHorizontalAlignment(SwingConstants.CENTER);
		base.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField = new javax.swing.JPasswordField();
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
		direccion = new JTextField();
		direccion.setToolTipText("");
		direccion.setHorizontalAlignment(SwingConstants.CENTER);
		direccion.setFont(new Font("Tahoma", Font.PLAIN, 24));

		try {
			buscarArchivoConf();
		} catch (SQLException e1) {

		}
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

						try {
							guardarDatos(true);
						} catch (SQLException e) {

						}

					}
				} else {
					mensaje("Algunos datos son demasiado cortos", true);
				}
			}
		});
		lblThumbnails = new JLabel("Contraseña de la BD");
		lblThumbnails.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/user_pass.png")));
		lblThumbnails.setFont(new Font("Tahoma", Font.BOLD, 20));

		lblBd = new JLabel();
		lblBd.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/db.png")));
		lblBd.setText("IP/dominio del servidor");
		lblBd.setHorizontalAlignment(SwingConstants.CENTER);
		lblBd.setFont(new Font("Tahoma", Font.BOLD, 20));

		lblPrefijoDeTablas = new JLabel();
		lblPrefijoDeTablas.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/db.png")));
		lblPrefijoDeTablas.setText("Prefijo de las tablas");
		lblPrefijoDeTablas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrefijoDeTablas.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblPuerto = new JLabel("Puerto de la BD");
		lblPuerto.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/port.png")));
		lblPuerto.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/db.png")));
		label.setText("Nombre de la BD");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout
						.createParallelGroup(Alignment.LEADING).addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout
										.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addGroup(layout.createSequentialGroup()
														.addComponent(lblPuerto, GroupLayout.DEFAULT_SIZE, 274,
																Short.MAX_VALUE)
														.addGap(36))
												.addGroup(layout.createSequentialGroup()
														.addComponent(lblThumbnails, GroupLayout.DEFAULT_SIZE, 310,
																Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.RELATED))
												.addGroup(layout.createSequentialGroup().addComponent(lblBd)
														.addPreferredGap(ComponentPlacement.RELATED)))
										.addGap(13))
								.addGroup(layout.createSequentialGroup().addComponent(lblPrefijoDeTablas).addGap(56)))
						.addGroup(layout.createSequentialGroup()
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
								.addGap(85)))
						.addGroup(layout.createSequentialGroup().addComponent(jLabel1).addGap(88)))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(lblNewLabel))
						.addComponent(direccion, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textField_1, Alignment.LEADING).addComponent(base, Alignment.LEADING)
								.addComponent(jTextField1, Alignment.LEADING).addComponent(textField, Alignment.LEADING,
										GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)))
				.addContainerGap(14, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addGap(14)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBd, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(direccion, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(36)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrefijoDeTablas, GroupLayout.PREFERRED_SIZE, 64,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(38)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(base, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(25)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(
								jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblThumbnails)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addGap(18)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 64,
												Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblPuerto, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
												.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 35,
														GroupLayout.PREFERRED_SIZE)))
								.addGap(42))
								.addGroup(layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 88,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap()))));
		getContentPane().setLayout(layout);
		setSize(new Dimension(666, 588));
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