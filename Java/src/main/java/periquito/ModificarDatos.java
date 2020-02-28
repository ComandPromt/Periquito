package periquito;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import utils.ComprobarSha;
import utils.Metodos;

public class ModificarDatos extends JFrame {
	private JButton buscar;
	private JButton contactos;
	private JButton editar;

	private JButton eliminarContacto;

	private static JList<String> jList1;
	static DefaultListModel<String> modelo = new DefaultListModel<>();
	private static LinkedList<String> notas = new LinkedList<>();
	String iduser;
	transient ResultSet rs;
	transient Statement s;
	String cnombre;
	String ctipo;
	String cnota;
	JTextArea nota = new JTextArea();
	private JTextField nombre;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	public ModificarDatos() throws IOException, SQLException {

		initComponents();

		setIconImage(Toolkit.getDefaultToolkit().getImage(AgendaInterfaz.class.getResource("/imagenes/name.png")));
		setResizable(false);
		setAutoRequestFocus(false);

		this.setSize(new Dimension(1000, 680));
		buscar.setToolTipText("Buscar");

		contactos.setToolTipText("Mostrar todos los contactos");
		editar.setToolTipText("Editar");

		this.setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Acciones");
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setBackground(Color.BLACK);
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.setIcon(new ImageIcon(ModificarDatos.class.getResource("/imagenes/utilities.png")));
		menuBar.add(mnNewMenu);

		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ver imagen en el CMS");
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new Agregar().setVisible(true);
			}
		});

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Buscar imagen por ID");
		mntmNewMenuItem_5.setIcon(new ImageIcon(ModificarDatos.class.getResource("/imagenes/lupa.png")));
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.add(mntmNewMenuItem_5);
		mntmNewMenuItem_1.setIcon(new ImageIcon(ModificarDatos.class.getResource("/imagenes/remote.png")));
		mnNewMenu.add(mntmNewMenuItem_1);

		JSeparator separator_2 = new JSeparator();
		mnNewMenu.add(separator_2);

		JMenuItem mntmNewMenuItem = new JMenuItem("Ver descargas");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new LeerArchivo().setVisible(true);
				} catch (IOException e1) {
					//
				}
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem.setIcon(new ImageIcon(ModificarDatos.class.getResource("/imagenes/download.png")));
		mnNewMenu.add(mntmNewMenuItem);

		JButton editarContacto = new JButton();
		menuBar.add(editarContacto);

		editarContacto.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/edit_1.png")));
		editarContacto.setBorderPainted(false);
		editarContacto.setContentAreaFilled(false);
		editarContacto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		editarContacto.setFocusPainted(false);
		editarContacto.setRolloverIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/edit.png")));
		editarContacto.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {

				try {
					if (jList1.getModel().getSize() == 0) {
						java.awt.EventQueue.invokeLater(() -> {
							new Agregar().setVisible(true);
						});
					} else {
						Connection conexion = Metodos.conexionBD();

						s = conexion.createStatement();

						if (jList1.getModel().getSize() == 0) {

							rs = s.executeQuery("select Nombre from notas order by Nombre");

							while (rs.next()) {

								modelo.addElement(rs.getString("Nombre"));
							}

						}

						else {

							try {

								rs = s.executeQuery("select Nombre,tipo,descripcion from notas WHERE Nombre='"
										+ jList1.getSelectedValue().toString() + "' order by Nombre");

								rs.next();

								cnombre = rs.getString("Nombre");
								ctipo = rs.getString("tipo");
								cnota = rs.getString("descripcion");

								nombre.setText(ctipo);
								nota.setText(cnota);

								rs = s.executeQuery("select id from notas WHERE Nombre='" + rs.getString("Nombre")
										+ "' order by Nombre");
								rs.next();
								iduser = rs.getString("id");

								nombre.setEditable(true);

							} catch (NullPointerException e) {
								Metodos.mensaje("Seleccione un registro para editar", 2);

								s.close();
							}
						}

					}
				} catch (Exception e) {
					//
				}
			}
		});
		JButton btnNewButton = new JButton();
		menuBar.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

//				String tipo1 = tipo.getText();
//				String nota1 = nota.getText();
//
//				if (!controlarSeleccion()) {
//
//					
//					tipo.setText(tipo1.trim());
//					nota.setText(nota1.trim());
//
//					if (!usuario.equals("") && !tipo1.equals("") && !nota1.equals("")
//							&& (!cnombre.equals(usuario) || !ctipo.equals(tipo1) || !cnota.equals(nota1))) {
//
//						try {
//
//							s.executeUpdate("UPDATE notas SET nombre='" + usuario + "',tipo='" + tipo1
//									+ "',descripcion='" + nota1 + "' WHERE id='" + iduser + "'");
//
//							rs.close();
//							s.close();
//							vaciarDatos();
//							verNotas();
//							Metodos.mensaje("La nota " + usuario + " se ha actualizado correctamente", 2);
//
//						} catch (SQLException e1) {
//
//							Metodos.mensaje("La nota " + usuario + " ya está en la BD", 3);
//
//							vaciarDatos();
//						} catch (IOException e1) {
//							//
//						}
//
//					}
//
//					else {
//						if (usuario.equals("") && tipo1.equals("") && nota1.equals("")) {
//							Metodos.mensaje("Por favor, visualize un usuario", 2);
//						} else {
//							if (usuario.equals("") || tipo1.equals("") || nota1.equals("")) {
//								Metodos.mensaje("Por favor, rellena todos los campos", 2);
//							}
//
//						}
//
//					}
//				}
//
//				else {
//					Metodos.mensaje("Por favor, seleccione un usuario y visualízelo", 2);
//				}

			}

		});
		btnNewButton.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/actualizar.png")));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);

		eliminarContacto = new JButton();
		menuBar.add(eliminarContacto);
		eliminarContacto.setToolTipText("Eliminar");
		eliminarContacto.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/delete.png")));
		eliminarContacto.setBorderPainted(false);
		eliminarContacto.setContentAreaFilled(false);
		eliminarContacto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		eliminarContacto.setFocusPainted(false);
		eliminarContacto.setRolloverIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/delete_1.png")));

		JMenu mnNewMenu_1 = new JMenu("Exportar");
		mnNewMenu_1.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/config.png")));
		mnNewMenu_1.setForeground(Color.BLACK);
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("PDF");
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (notas.isEmpty()) {
					Metodos.mensaje("Por favor, inserte una URL y vea sus enlaces", 3);
				}

				else {

					try {

						// MetodosPdf.crearPdf(notas, url, "template-verurl.html");

					} catch (Exception e1) {
						//
					}
				}
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_2.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/pdf.png")));
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JSeparator separator = new JSeparator();
		mnNewMenu_1.add(separator);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Excel");
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_3.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/excel.png")));
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JSeparator separator_3 = new JSeparator();
		mnNewMenu_1.add(separator_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Txt");
		mntmNewMenuItem_4.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/txt.png")));
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu_1.add(mntmNewMenuItem_4);
		eliminarContacto.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				try {
					eliminarContactoMouseClicked();
				} catch (IOException e) {
					Metodos.mensaje("No se ha podido eliminar la nota", 1);
				}
			}
		});

	}

	public void vaciarDatos() {
		// nombre.setText("");
		nombre.setText("");
		nota.setText("");
	}

	private void initComponents() throws SQLException, IOException {
		JLabel jLabel1;
		JPanel jPanel3;
		JPanel jPanel4;
		JPanel jPanel5;
		jList1 = new JList<>();
		jPanel4 = new JPanel();
		jPanel5 = new JPanel();
		jLabel1 = new JLabel();
		contactos = new JButton();
		contactos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		editar = new JButton();
		editar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buscar = new JButton();
		buscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jPanel3 = new JPanel();
		JScrollPane jScrollPane1;
		JPanel panelCasa;
		jScrollPane1 = new JScrollPane();
		jList1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jList1.setFixedCellHeight(40);

		jPanel5.setBackground(new java.awt.Color(88, 205, 170));

		jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
		jLabel1.setText("Nombre");

		GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
						.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 481, GroupLayout.PREFERRED_SIZE)));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE));

		GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup().addContainerGap()
						.addComponent(jPanel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(jPanel4Layout
								.createSequentialGroup().addComponent(jPanel5, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)));
		setTitle("Periquito v3 Notas");
		setBackground(new java.awt.Color(123, 123, 123));

		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane1.setDoubleBuffered(true);

		jList1.setBackground(new java.awt.Color(254, 254, 254));

		jScrollPane1.setViewportView(jList1);
		JLabel jLabel5;
		panelCasa = new JPanel();
		jLabel5 = new JLabel();
		jLabel5.setFont(new Font("Tahoma", Font.BOLD, 16));
		jLabel5.setText("Nombre");

		panelCasa.setBackground(new Color(214, 217, 223));

		jLabel5.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/name.png")));

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JLabel lblSubidoPor = new JLabel();
		lblSubidoPor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubidoPor.setIcon(new ImageIcon(ModificarDatos.class.getResource("/imagenes/user.png")));
		lblSubidoPor.setText("Subido por");
		lblSubidoPor.setFont(new Font("Tahoma", Font.BOLD, 16));

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNewLabel_1 = new JLabel("Descripción");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JLabel lblCategora = new JLabel();
		lblCategora.setIcon(new ImageIcon(ModificarDatos.class.getResource("/imagenes/tag.png")));
		lblCategora.setText("Categoría");
		lblCategora.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategora.setFont(new Font("Tahoma", Font.BOLD, 16));

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		nombre = new JTextField();
		nombre.setEditable(false);
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		nombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		nombre.setColumns(10);

		JLabel label_2 = new JLabel("Palabras clave");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNivelVisibilidad = new JLabel("Nivel visibilidad");
		lblNivelVisibilidad.setIcon(new ImageIcon(ModificarDatos.class.getResource("/imagenes/view.png")));
		lblNivelVisibilidad.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel label = new JLabel("Nivel comentario");
		label.setIcon(new ImageIcon(ModificarDatos.class.getResource("/imagenes/nota.png")));
		label.setFont(new Font("Tahoma", Font.BOLD, 14));

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setFont(new Font("Tahoma", Font.BOLD, 14));

		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setFont(new Font("Tahoma", Font.BOLD, 14));

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNivelDescarga = new JLabel("Nivel descarga");
		lblNivelDescarga.setIcon(new ImageIcon(ModificarDatos.class.getResource("/imagenes/download.png")));
		lblNivelDescarga.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblId = new JLabel("ID");
		lblId.setIcon(new ImageIcon(ModificarDatos.class.getResource("/imagenes/tag.png")));
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Descargas");
		lblNewLabel.setIcon(new ImageIcon(ModificarDatos.class.getResource("/imagenes/download.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_1.setColumns(10);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Permitir comentarios");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 16));

		JCheckBox chckbxImagenActiva = new JCheckBox("Imagen activa");
		chckbxImagenActiva.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxImagenActiva.setFont(new Font("Tahoma", Font.BOLD, 16));

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nº votos");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);

		JLabel lblCalificacin = new JLabel("Calificación");
		lblCalificacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalificacin.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNVistas = new JLabel("Nº vistas");
		lblNVistas.setHorizontalAlignment(SwingConstants.CENTER);
		lblNVistas.setFont(new Font("Tahoma", Font.BOLD, 16));

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);

		GroupLayout panelCasaLayout = new GroupLayout(panelCasa);
		panelCasaLayout.setHorizontalGroup(panelCasaLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(panelCasaLayout.createSequentialGroup().addContainerGap().addGroup(panelCasaLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(panelCasaLayout.createSequentialGroup().addGroup(panelCasaLayout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(panelCasaLayout.createSequentialGroup()
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
										.addGap(18)
										.addComponent(
												label_2, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
										.addGap(18))
								.addGroup(Alignment.TRAILING, panelCasaLayout.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 272,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addGroup(panelCasaLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(panelCasaLayout.createSequentialGroup().addGap(18)
												.addGroup(panelCasaLayout.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(textField_4, Alignment.LEADING)
														.addComponent(lblCalificacin, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(textField_3, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
														.addComponent(textField_5, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
														.addComponent(lblNVistas, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)))
										.addGroup(panelCasaLayout.createSequentialGroup().addGap(17).addComponent(
												lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)))
								.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE))
						.addGroup(panelCasaLayout.createSequentialGroup().addGroup(panelCasaLayout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(panelCasaLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblSubidoPor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblNewLabel))
								.addComponent(lblCategora)).addGap(48)
								.addGroup(panelCasaLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(panelCasaLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(comboBox, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(textField_1, Alignment.TRAILING).addComponent(
														chckbxNewCheckBox, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(panelCasaLayout.createSequentialGroup()
												.addGroup(panelCasaLayout.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(comboBox_1, Alignment.LEADING, 0,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(nombre, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
												.addPreferredGap(ComponentPlacement.RELATED)))
								.addGap(18)
								.addGroup(panelCasaLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING, panelCasaLayout.createSequentialGroup()
												.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 88,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(panelCasaLayout.createParallelGroup(Alignment.TRAILING)
														.addGroup(panelCasaLayout.createSequentialGroup()
																.addComponent(chckbxImagenActiva,
																		GroupLayout.PREFERRED_SIZE, 160,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(8))
														.addComponent(textField, GroupLayout.PREFERRED_SIZE, 143,
																GroupLayout.PREFERRED_SIZE)))
										.addGroup(Alignment.TRAILING, panelCasaLayout
												.createParallelGroup(Alignment.LEADING, false)
												.addGroup(panelCasaLayout.createSequentialGroup()
														.addComponent(lblNivelVisibilidad, GroupLayout.PREFERRED_SIZE,
																181, GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(comboBox_3, 0,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGroup(panelCasaLayout.createSequentialGroup()
														.addComponent(label, GroupLayout.PREFERRED_SIZE, 196,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(comboBox_4, 0, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
												.addGroup(panelCasaLayout.createSequentialGroup()
														.addComponent(lblNivelDescarga, GroupLayout.PREFERRED_SIZE, 184,
																GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE,
																54, GroupLayout.PREFERRED_SIZE))))
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGap(105))
				.addGroup(panelCasaLayout.createSequentialGroup()
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(639, Short.MAX_VALUE)));
		panelCasaLayout.setVerticalGroup(panelCasaLayout.createParallelGroup(Alignment.LEADING).addGroup(panelCasaLayout
				.createSequentialGroup()
				.addGroup(panelCasaLayout.createParallelGroup(Alignment.LEADING).addGroup(panelCasaLayout
						.createSequentialGroup()
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(panelCasaLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)))
						.addGroup(panelCasaLayout.createSequentialGroup().addGap(7)
								.addGroup(panelCasaLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(chckbxNewCheckBox).addComponent(chckbxImagenActiva,
												GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
				.addGap(11)
				.addGroup(panelCasaLayout.createParallelGroup(Alignment.LEADING).addGroup(panelCasaLayout
						.createSequentialGroup()
						.addGroup(panelCasaLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNivelVisibilidad, GroupLayout.PREFERRED_SIZE, 58,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(panelCasaLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(24)
						.addGroup(panelCasaLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNivelDescarga)
								.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(18).addComponent(lblNewLabel_2)).addGroup(
								panelCasaLayout.createSequentialGroup()
										.addGroup(panelCasaLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblSubidoPor, GroupLayout.PREFERRED_SIZE, 64,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(panelCasaLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 73,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(nombre, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(panelCasaLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblCategora, GroupLayout.PREFERRED_SIZE, 64,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 25,
														GroupLayout.PREFERRED_SIZE))
										.addGap(26)
										.addGroup(panelCasaLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 17,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_1)
												.addComponent(textField_3, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				.addGap(9)
				.addGroup(
						panelCasaLayout
								.createParallelGroup(
										Alignment.BASELINE)
								.addGroup(panelCasaLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblCalificacin, GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(19)
										.addComponent(lblNVistas, GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(3))
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(199, Short.MAX_VALUE)));

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane_1.setViewportView(textArea);
		nota.setEditable(false);

		scrollPane.setViewportView(nota);
		panelCasa.setLayout(panelCasaLayout);

		GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addGap(18)
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panelCasa, GroupLayout.PREFERRED_SIZE, 836, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel3Layout
				.setVerticalGroup(
						jPanel3Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
										.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
												.addGroup(jPanel3Layout.createSequentialGroup()
														.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 518,
																GroupLayout.PREFERRED_SIZE)
														.addContainerGap())
												.addGroup(jPanel3Layout.createSequentialGroup()
														.addComponent(panelCasa, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGap(112)))));
		jPanel3.setLayout(jPanel3Layout);

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 1041, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout
						.createSequentialGroup().addComponent(jPanel3, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(209, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		if (jList1.getModel().getSize() == 0) {
			try {
				verNotas();
			} catch (Exception e) {
//
			}
		}

		pack();
	}

	public static void verNotas() throws SQLException, IOException {

		String nota;

		String finalSentencia = "";

		int longitudSha = ComprobarSha.getShaimages().size();

		if (longitudSha > 0) {

			for (int i = 0; i < longitudSha; i++) {

				if (i > 0 && i++ < longitudSha) {
					i--;
					finalSentencia = " AND sha256='" + ComprobarSha.getShaimages().get(i) + "'";
				}

				else {
					if (i > 0) {
						i--;
					}

					finalSentencia = "sha256='" + ComprobarSha.getShaimages().get(i) + "'";
				}

			}
		}

		modelo.removeAllElements();

		Connection conexion = Metodos.conexionBD();

		Statement s = conexion.createStatement();

		ResultSet rs = s.executeQuery("SELECT image_id FROM " + MenuPrincipal.getLecturabd()[3] + "images WHERE "
				+ finalSentencia + " ORDER BY image_id DESC");

		while (rs.next()) {
			nota = rs.getString("Nombre");
			modelo.addElement(nota);
			notas.add(nota);
		}

		jList1.setModel(modelo);

		rs.close();

		s.close();
	}

	private void eliminarContactoMouseClicked() throws IOException {

		if (!controlarSeleccion()) {
			JLabel mensaje = new JLabel("Seguro que quieres borrar a " + jList1.getSelectedValue().toString() + "?");
			mensaje.setFont(new Font("Arial", Font.BOLD, 18));
			int confirmado = JOptionPane.showConfirmDialog(null, mensaje, "WARNING", 0);
			if (JOptionPane.OK_OPTION == confirmado) {

				try {
					Connection conexion = Metodos.conexionBD();

					s = conexion.createStatement();

					s.executeUpdate("DELETE FROM notas WHERE Nombre='" + jList1.getSelectedValue() + "'");
					vaciarDatos();
					verNotas();
					s.close();
				} catch (SQLException e) {
					//
				}

			}
		} else {
			Metodos.mensaje("Seleccione un registro para borrar", 2);
		}
	}

	public boolean controlarSeleccion() {
		try {
			jList1.getSelectedValue().toString();
			return false;
		} catch (NullPointerException e1) {
			return true;
		}
	}

	public static void main(String[] args) {

		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AgendaInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AgendaInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AgendaInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AgendaInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					new AgendaInterfaz().setVisible(true);
				} catch (IOException | SQLException e) {
					//
				}

			}
		});

	}
}