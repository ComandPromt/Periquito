package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Galeria;
import utils.InterfazGaleria;
import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("all")

public class Busqueda extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	javax.swing.JLabel jLabel1;
	static javax.swing.JTextField jTextField1;

	public Busqueda() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Config2.class.getResource("/imagenes/config.png")));
		setTitle("Periquito v3 Busqueda ");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	@SuppressWarnings("all")
	public void initComponents() throws IOException {

		jTextField1 = new javax.swing.JTextField();
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

				jTextField1.setText("");

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

				jTextField1.setText("SELECT count(image_id) FROM " + MenuPrincipal.getLecturabd()[3] + "images WHERE ");

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

									int recuento;

									if (!busqueda.isEmpty()) {

										Connection conexion = Metodos.conexionBD();

										Statement s = conexion.createStatement();

										ResultSet rs = s.executeQuery(sql);

										rs.next();

										recuento = Integer.parseInt(rs.getString("count(image_id)"));

										if (recuento > 0) {

											if (recuento > 500) {

												Metodos.mensaje(
														"Has introducido un nombre que está en más de 500 imágenes", 3);
												Metodos.mensaje(
														"Por favor,introduce un nombre con menos registros para mostrarlos",
														2);

												Metodos.abrirCarpeta(
														Metodos.obtenerEnlaceCms(MenuPrincipal.getLecturaurl()[0],
																MenuPrincipal.getLecturaurl()[1])
																+ "/search.php?filtro=" + busqueda);

											} else {

												rs = s.executeQuery(sql2);

												while (rs.next()) {

													MenuPrincipal.getImagenes().add(rs.getString("image_media_file"));
													MenuPrincipal.getCategorias().add(rs.getString("cat_id"));
												}

											}

											s.close();
											rs.close();

											if (recuento == 0) {

												Metodos.mensaje("No hay resultados, intente con otro nombre", 2);

											} else {

												if (recuento < 500) {

													try {

														new Galeria();
														new InterfazGaleria().setVisible(true);
													} catch (Exception e1) {

														Metodos.mensaje("No se han podido cargar las imágenes", 1);

														new Config2().setVisible(true);

													}

												}
											}

										} else {
											Metodos.mensaje("Por favor, introduce un nombre para buscar", 3);
										}

									}

								}
							} catch (Exception e1) {
							}
						}
					} catch (SQLException e1) {
						try {
							new Bd().setVisible(true);
						} catch (IOException e11) {
							//
						}
					}
				} else {
					Metodos.mensaje("Introduzca un nombre para buscar", 2);
				}
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(146).addComponent(btnNewButton))
						.addGroup(layout.createSequentialGroup().addGap(19).addGroup(layout
								.createParallelGroup(Alignment.LEADING)
								.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup().addComponent(rdbtnNewRadioButton)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(jLabel1).addGap(34)
										.addComponent(rdbtnNewRadioButton_1).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblNombre)))))
						.addContainerGap(28, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap(48, Short.MAX_VALUE)
								.addComponent(rdbtnNewRadioButton_1).addGap(38))
						.addGroup(
								layout.createSequentialGroup().addGap(43).addComponent(rdbtnNewRadioButton).addGap(43))
						.addGroup(layout.createSequentialGroup().addGap(25)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 64,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel1))
								.addGap(18)))
						.addGap(10).addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(27).addComponent(btnNewButton).addGap(41)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(402, 315));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
