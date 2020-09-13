package periquito;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("all")

public class Busqueda extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {

	javax.swing.JLabel jLabel1;
	static javax.swing.JTextField jTextField1;
	private JTextField textField;

	public Busqueda() throws IOException {

		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa.png")));
		setTitle("Periquito v3 Busqueda ");
		initComponents();
		this.setVisible(true);

	}

	@SuppressWarnings("all")
	public void initComponents() throws IOException {

		jTextField1 = new javax.swing.JTextField();
		jTextField1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					//
				}
			}
		});
		jTextField1.setHorizontalAlignment(SwingConstants.CENTER);
		jTextField1.setToolTipText("");

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText("SQL");
		jLabel1.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/db.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("");

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("");

		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				rdbtnNewRadioButton.setSelected(false);

				textField.setVisible(false);

				textField.setText("");

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (!rdbtnNewRadioButton_1.isSelected() && !rdbtnNewRadioButton.isSelected()) {
					rdbtnNewRadioButton_1.setSelected(true);
				}
			}
		});

		rdbtnNewRadioButton_1.setSelected(true);

		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				rdbtnNewRadioButton_1.setSelected(false);
				textField.setVisible(true);
				textField.setText("SELECT count(image_id) FROM " + MenuPrincipal.getLecturabd()[3] + "images WHERE ");

			}

			@Override
			public void mouseReleased(MouseEvent e) {

				if (!rdbtnNewRadioButton_1.isSelected() && !rdbtnNewRadioButton.isSelected()) {
					rdbtnNewRadioButton_1.setSelected(true);
				}

			}
		});

		JLabel lblNombre = new JLabel();
		lblNombre.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/name.png")));
		lblNombre.setText("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 20));

		jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 24));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/lupa.png")));

		btnNewButton.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				if (!jTextField1.getText().trim().equals("")) {

					try {

						if (Metodos.comprobarConexionBd(
								"SELECT COUNT(image_id) FROM " + MenuPrincipal.getLecturabd()[3] + "images",
								"COUNT(image_id)")) {

							try {

								if (Metodos.comprobarConfiguracion()) {

									String busqueda = jTextField1.getText().trim();

									String sql = "";

									String sql2 = "";

									if (rdbtnNewRadioButton_1.isSelected()) {

										sql = "select count(image_id) from " + MenuPrincipal.getLecturabd()[3]
												+ "images WHERE image_name like '%" + busqueda + "%'";

										sql2 = "select image_media_file,cat_id from " + MenuPrincipal.getLecturabd()[3]
												+ "images WHERE image_name like '%" + busqueda + "%'";
									}

									else {

										sql = "select count(image_id) from " + MenuPrincipal.getLecturabd()[3]
												+ "images WHERE " + busqueda;

										sql2 = "select image_media_file,cat_id from " + MenuPrincipal.getLecturabd()[3]
												+ "images WHERE " + busqueda;
									}

									if (!busqueda.isEmpty()) {

										Metodos.mostrarGaleriaSql(busqueda, sql, sql2);

									}

								}
							}

							catch (Exception e1) {
								Metodos.mensaje("Revise la búsqueda", 3);
							}
						}
					}

					catch (SQLException e1) {

						try {
							new Bd().setVisible(true);
						}

						catch (IOException e11) {
							//
						}

					}
				}

				else {
					Metodos.mensaje("Introduzca un nombre para buscar", 2);
				}
			}

		});

		textField = new JTextField();
		textField.setEditable(false);

		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setColumns(10);

		JTextArea textArea = new JTextArea();
		textArea.setText(" Arrastra los archivos aqui");
		textArea.setForeground(Color.DARK_GRAY);
		textArea.setFont(new Font("Tahoma", Font.BOLD, 26));
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);

		JLabel lblDragMode = new JLabel();
		lblDragMode.setText("Drag Mode");
		lblDragMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblDragMode.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblNewLabel_3 = new JLabel("Buscar imágenes por");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(35).addComponent(rdbtnNewRadioButton)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(jLabel1).addGap(26)
								.addComponent(rdbtnNewRadioButton_1).addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblNombre).addGap(18).addComponent(rdbtnNewRadioButton_2)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblDragMode, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(31).addComponent(lblNewLabel_3,
								GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)))
						.addGap(189))
				.addGroup(layout.createSequentialGroup().addGap(118)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(jTextField1, Alignment.LEADING)
										.addComponent(textField, Alignment.LEADING, 360, 360, Short.MAX_VALUE)))
						.addGap(277))
				.addGroup(Alignment.LEADING, layout.createSequentialGroup().addGap(254).addComponent(btnNewButton)
						.addContainerGap(407, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(32).addComponent(lblNewLabel_3)
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addGap(11)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(21).addComponent(rdbtnNewRadioButton))
								.addGroup(layout.createSequentialGroup().addGap(24).addComponent(rdbtnNewRadioButton_1))
								.addComponent(jLabel1)
								.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDragMode, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup().addGap(34).addComponent(rdbtnNewRadioButton_2)))
				.addGap(28).addComponent(textArea, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
				.addGap(35).addComponent(textField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnNewButton)
				.addContainerGap(49, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(588, 499));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
