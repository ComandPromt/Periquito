package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("serial")

public class Bd extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	JLabel jLabel1;
	static javax.swing.JTextField jTextField1;
	private static JTextField textField;
	JLabel lblThumbnails;
	JLabel lblBd;
	private JTextField base;
	String[] lectura;
	JLabel lblPrefijoDeTablas;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField direccion;
	private JLabel lblNewLabel_2;
	JRadioButton rdbtnXampp = new JRadioButton("XAMPP");
	JRadioButton rdbtnAppserv = new JRadioButton("APPServ");
	String servidorWeb = "1";
	JRadioButton rdbtnWamp = new JRadioButton("WAMP");

	@SuppressWarnings("all")
	public void buscarArchivoConf() throws IOException, SQLException {
		File af = new File("Config/Bd.txt");

		if (af.exists()) {

			try {
				lectura = Metodos.leerFicheroArray("Config/Bd.txt", 7);
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
				textField1.setText(lectura[3]);

			} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
//
			}

		}
	}

	public void guardarDatos(Boolean mensaje) throws IOException, SQLException {
		FileWriter flS = new FileWriter("Config/Bd.txt");
		BufferedWriter fS = new BufferedWriter(flS);

		try {

			fS.write(base.getText().trim());
			fS.newLine();
			fS.write(jTextField1.getText().trim());
			fS.newLine();
			fS.write(textField.getText().trim());
			fS.newLine();
			fS.write(textField1.getText().trim());
			fS.newLine();
			fS.write(textField2.getText().trim());
			fS.newLine();
			fS.write(direccion.getText().trim());
			fS.newLine();
			fS.write(servidorWeb);
			fS.close();
			flS.close();

			dispose();

			MenuPrincipal.setLecturabd(Metodos.leerFicheroArray("Config/Bd.txt", 7));

			if (mensaje) {
				Metodos.mensaje("Archivo guardado con exito!", 2);

				Metodos.ponerCategoriasBd(MenuPrincipal.comboBox);

			}

		} catch (IOException e) {
			Metodos.mensaje("Error al crear el fichero de configuracion", 1);

		} finally {
			fS.close();
			flS.close();
		}
	}

	public Bd() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Bd.class.getResource("/imagenes/db.png")));
		setTitle("Periquito v3 Config DB");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	private void servidorWebPorDefecto() {
		if (!rdbtnWamp.isSelected() && !rdbtnXampp.isSelected() && !rdbtnAppserv.isSelected()

		) {
			rdbtnAppserv.setSelected(true);
		}
	}

	@SuppressWarnings("all")
	public void initComponents() throws IOException {

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText("Usuario de la BD");
		jLabel1.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/user.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);
		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));

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
		textField1 = new JTextField();
		textField1.setToolTipText("");
		textField1.setHorizontalAlignment(SwingConstants.CENTER);
		textField1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField2 = new JTextField();
		textField2.setText("3306");
		textField2.setToolTipText("");
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		direccion = new JTextField();
		direccion.setToolTipText("");
		direccion.setHorizontalAlignment(SwingConstants.CENTER);
		direccion.setFont(new Font("Tahoma", Font.PLAIN, 24));

		try {
			buscarArchivoConf();
		} catch (SQLException e1) {
//
		}
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

		rdbtnWamp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				servidorWeb = "2";
				rdbtnAppserv.setSelected(false);
				rdbtnXampp.setSelected(false);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				servidorWebPorDefecto();

			}
		});
		rdbtnWamp.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnXampp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				servidorWeb = "3";
				rdbtnWamp.setSelected(false);
				rdbtnAppserv.setSelected(false);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				servidorWebPorDefecto();

			}
		});

		rdbtnXampp.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnAppserv.setSelected(true);

		rdbtnAppserv.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				servidorWeb = "1";
				rdbtnWamp.setSelected(false);
				rdbtnXampp.setSelected(false);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				servidorWebPorDefecto();
			}

		});
		rdbtnAppserv.setFont(new Font("Dialog", Font.BOLD, 16));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/appserv.png")));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/wamp.png")));

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/xampp.png")));

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Bd.class.getResource("/imagenes/save.png")));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("all")
			public void mousePressed(MouseEvent arg0) {

				if (jTextField1.getText() != null && jTextField1.getText().length() >= 2 && textField.getText() != null
						&& textField.getText().length() >= 2) {

					String comprobacion1 = jTextField1.getText().replace("  ", " ");
					String comprobacion2 = textField.getText().replace("  ", " ");

					comprobacion1 = jTextField1.getText().replace(" ", "");
					comprobacion1 = jTextField1.getText().trim();

					comprobacion2 = textField.getText().replace(" ", "");
					comprobacion2 = textField.getText().trim();

					int comprobacion3 = Integer.parseInt(textField2.getText().trim());

					if (comprobacion1.length() >= 2 && comprobacion2.length() >= 2 && comprobacion3 > 0) {

						if (comprobacion1.substring(0, 2).equals("\\\\")) {
							jTextField1.setText(comprobacion1.substring(2, comprobacion1.length()));
						}

						if (comprobacion2.substring(0, 2).equals("\\\\")) {
							textField.setText(comprobacion2.substring(2, comprobacion2.length()));
						}

						try {
							guardarDatos(true);
							MenuPrincipal.button.setEnabled(true);
						} catch (SQLException e) {
							Metodos.mensaje("Error al conectar a la base de datos", 1);
						} catch (Exception e) {
							Metodos.mensaje("Error al guardar la configuración", 1);
						}

					}
				} else {
					Metodos.mensaje("Algunos datos son demasiado cortos", 3);
				}
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup().addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(rdbtnAppserv)
										.addGap(30).addComponent(lblNewLabel_1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(rdbtnWamp).addGap(18)
										.addComponent(lblNewLabel_2).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(rdbtnXampp).addGap(30).addComponent(btnNewButton))
								.addGroup(layout.createSequentialGroup()
										.addComponent(lblPuerto, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
										.addGap(3).addComponent(textField2, GroupLayout.PREFERRED_SIZE, 245,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblThumbnails, GroupLayout.DEFAULT_SIZE, 363,
														Short.MAX_VALUE)
												.addComponent(lblBd).addComponent(jLabel1))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(base, 226, 226, Short.MAX_VALUE)
												.addComponent(textField1, 226, 226, Short.MAX_VALUE)
												.addComponent(direccion, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
												.addComponent(jTextField1).addComponent(textField, Alignment.TRAILING,
														GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE))))
								.addGap(242))
						.addComponent(label)).addComponent(lblPrefijoDeTablas))
				.addGap(0)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(14)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(direccion, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBd, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
						.addGap(36)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrefijoDeTablas, GroupLayout.PREFERRED_SIZE, 64,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(36)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(base, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblThumbnails)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPuerto, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
								.addComponent(textField2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addComponent(btnNewButton).addComponent(lblNewLabel_2)))
								.addGroup(layout.createSequentialGroup().addGap(18)
										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel).addComponent(lblNewLabel_1)))
								.addGroup(layout.createSequentialGroup().addGap(28).addComponent(rdbtnXampp))
								.addGroup(layout.createSequentialGroup().addGap(28).addComponent(rdbtnWamp))
								.addGroup(layout.createSequentialGroup().addGap(29).addComponent(rdbtnAppserv)))
						.addGap(27)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(651, 647));
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

	@Override
	public void stateChanged(ChangeEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//

	}
}