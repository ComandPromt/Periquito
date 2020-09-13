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

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import utils.Metodos;

@SuppressWarnings("all")

public class Categorias extends JFrame {

	private JButton buscar;
	private JButton contactos;
	private JButton editar;
	private JButton eliminarContacto;
	private static JList<String> jList1;
	private JTextPane nombre;
	JTextArea nota = new JTextArea("", 0, 50);
	static DefaultListModel<String> modelo = new DefaultListModel<>();
	JTextPane nvGif = new JTextPane();
	String iduser;
	transient ResultSet rs;
	transient Statement s;
	String cnombre;
	String ctipo;
	String cnota;
	JTextPane tipo = new JTextPane();
	JTextPane nvUpload = new JTextPane();

	public Categorias() throws IOException, SQLException {

		initComponents();

		setIconImage(Toolkit.getDefaultToolkit().getImage(AgendaInterfaz.class.getResource("/imagenes/name.png")));
		setResizable(false);
		setAutoRequestFocus(false);

		this.setSize(new Dimension(680, 640));
		buscar.setToolTipText("Buscar");

		contactos.setToolTipText("Mostrar todos los contactos");
		editar.setToolTipText("Editar");

		this.setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Insertar");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/insert.png")));
		menuBar.add(mnNewMenu);

		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Nota");
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new Agregar().setVisible(true);
			}
		});

		mntmNewMenuItem_1.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/name.png")));
		mnNewMenu.add(mntmNewMenuItem_1);

		JSeparator separator_2 = new JSeparator();
		mnNewMenu.add(separator_2);

		JMenuItem mntmNewMenuItem = new JMenuItem("Desde Archivo");

		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					new LeerArchivo().setVisible(true);
				}

				catch (IOException e1) {
					//
				}
			}
		});

		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/nota.png")));
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
					}

					else {

						Connection conexion = Metodos.conexionBD();

						s = conexion.createStatement();

						if (jList1.getModel().getSize() == 0) {

							rs = s.executeQuery("select cat_name from " + MenuPrincipal.getLecturabd()[3]
									+ "categories order by cat_name");

							while (rs.next()) {

								modelo.addElement(rs.getString("cat_name"));
							}

						}

						else {

							try {

								rs = s.executeQuery("select cat_id,cat_name,cat_parent_id,cat_description from "
										+ MenuPrincipal.getLecturabd()[3] + "categories WHERE cat_id='"
										+ jList1.getSelectedValue().substring(0, jList1.getSelectedValue().indexOf(" "))
										+ "' order by cat_name");

								rs.next();

								iduser = rs.getString("cat_id");
								cnombre = rs.getString("cat_name");
								ctipo = rs.getString("cat_parent_id");
								cnota = rs.getString("cat_description");
								nombre.setText(cnombre);
								tipo.setText(ctipo);
								nota.setText(cnota);

								nombre.setEditable(true);
								tipo.setEditable(true);
								nota.setEditable(true);
								nvGif.setEditable(true);
								nvUpload.setEditable(true);
							} catch (NullPointerException e) {
								Metodos.mensaje("Seleccione un registro para editar", 2);

								s.close();
							}
						}

					}
				}

				catch (Exception e) {
					//
				}
			}
		});

		JButton btnNewButton = new JButton();
		menuBar.add(btnNewButton);

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				String usuario = nombre.getText();
				String tipo1 = tipo.getText();
				String nota1 = nota.getText();

				if (!controlarSeleccion()) {

					nombre.setText(usuario.trim());
					tipo.setText(tipo1.trim());
					nota.setText(nota1.trim());

					if (!usuario.equals("") && !tipo1.equals("") && !nota1.equals("")
							&& (!cnombre.equals(usuario) || !ctipo.equals(tipo1) || !cnota.equals(nota1))) {

						try {

							s.executeUpdate("UPDATE " + MenuPrincipal.getLecturabd()[3] + "categories SET cat_name='"
									+ usuario + "',cat_parent_id='" + tipo1 + "',cat_description='" + nota1
									+ "' WHERE cat_id='" + iduser + "'");

							rs.close();
							s.close();
							vaciarDatos();
							verNotas();
							Metodos.mensaje("La nota " + usuario + " se ha actualizado correctamente", 2);

						}

						catch (SQLException e1) {

							Metodos.mensaje("La nota " + usuario + " ya está en la BD", 3);

							vaciarDatos();
						}

						catch (IOException e1) {
							//
						}

					}

					else {

						if (usuario.equals("") && tipo1.equals("") && nota1.equals("")) {
							Metodos.mensaje("Por favor, visualize un usuario", 2);
						}

						else {

							if (usuario.equals("") || tipo1.equals("") || nota1.equals("")) {
								Metodos.mensaje("Por favor, rellena todos los campos", 2);
							}

						}

					}
				}

				else {
					Metodos.mensaje("Por favor, seleccione un usuario y visualízelo", 2);
				}

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

		eliminarContacto.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(java.awt.event.MouseEvent evt) {

				try {
					eliminarContactoMouseClicked();
				}

				catch (IOException e) {
					Metodos.mensaje("No se ha podido eliminar la nota", 1);
				}

			}
		});

	}

	public void vaciarDatos() {

		nombre.setText("");
		tipo.setText("");
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
		JPanel panelNombre;
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
		setTitle("Periquito v3 Categorías");
		setBackground(new java.awt.Color(123, 123, 123));

		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane1.setDoubleBuffered(true);

		jList1.setBackground(new java.awt.Color(254, 254, 254));
		jList1.addMouseListener(new java.awt.event.MouseAdapter() {

		});
		jList1.addKeyListener(new java.awt.event.KeyAdapter() {

		});
		jScrollPane1.setViewportView(jList1);
		panelNombre = new JPanel();
		JLabel jLabel3;
		jLabel3 = new JLabel();

		panelNombre.setBackground(new Color(214, 217, 223));

		jLabel3.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/user.png")));

		nombre = new JTextPane();
		nombre.setBackground(new Color(255, 255, 255));
		nombre.setEditable(false);
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JCheckBox chckbxNewCheckBox = new JCheckBox("Visible");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Categorias.class.getResource("/imagenes/view.png")));

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Categorias.class.getResource("/imagenes/download.png")));

		JCheckBox chckbxDescarga = new JCheckBox("Descarga");
		chckbxDescarga.setFont(new Font("Tahoma", Font.BOLD, 16));

		GroupLayout panelNombreLayout = new GroupLayout(panelNombre);
		panelNombreLayout.setHorizontalGroup(panelNombreLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(panelNombreLayout.createSequentialGroup().addContainerGap()
						.addGroup(panelNombreLayout
								.createParallelGroup(Alignment.LEADING).addComponent(jLabel3).addComponent(lblNewLabel))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(panelNombreLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(nombre, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
								.addGroup(panelNombreLayout.createSequentialGroup().addComponent(chckbxNewCheckBox)
										.addGap(18)
										.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 64,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(chckbxDescarga)))
						.addContainerGap(20, Short.MAX_VALUE)));
		panelNombreLayout.setVerticalGroup(panelNombreLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(panelNombreLayout.createSequentialGroup()
						.addGroup(panelNombreLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(panelNombreLayout.createSequentialGroup().addGap(26).addComponent(nombre,
										GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
								.addGroup(panelNombreLayout.createSequentialGroup().addContainerGap().addComponent(
										jLabel3, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
						.addGroup(panelNombreLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(panelNombreLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(panelNombreLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
												.addGroup(panelNombreLayout.createParallelGroup(Alignment.TRAILING)
														.addGroup(panelNombreLayout.createSequentialGroup()
																.addComponent(lblNewLabel).addGap(12))
														.addGroup(panelNombreLayout.createSequentialGroup()
																.addComponent(chckbxNewCheckBox).addGap(29))))
										.addGroup(panelNombreLayout.createSequentialGroup().addGap(18)
												.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 64,
														GroupLayout.PREFERRED_SIZE)
												.addContainerGap()))
								.addGroup(Alignment.TRAILING, panelNombreLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxDescarga,
												GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
										.addGap(30)))));
		panelNombre.setLayout(panelNombreLayout);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(214, 217, 223));

		JLabel label = new JLabel();
		label.setText("Num. categoria padre");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));

		tipo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tipo.setEditable(false);
		tipo.setBackground(Color.WHITE);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(214, 217, 223));

		JLabel label_1 = new JLabel();
		label_1.setIcon(new ImageIcon(Categorias.class.getResource("/imagenes/nota.png")));

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE).addGap(28)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE).addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		nota.setWrapStyleWord(true);
		nota.setLineWrap(true);
		nota.setFont(new Font("Monospaced", Font.PLAIN, 20));
		nota.setEditable(false);
		nota.setBackground(Color.WHITE);
		scrollPane.setViewportView(nota);
		panel_1.setLayout(gl_panel_1);

		JLabel lblNivelGif = new JLabel();
		lblNivelGif.setText("Nivel GIF");
		lblNivelGif.setFont(new Font("Tahoma", Font.BOLD, 16));

		nvGif.setFont(new Font("Tahoma", Font.PLAIN, 24));
		nvGif.setEditable(false);
		nvGif.setBackground(Color.WHITE);

		JLabel lblNivelDescarga = new JLabel();
		lblNivelDescarga.setText("Nivel subida");
		lblNivelDescarga.setFont(new Font("Tahoma", Font.BOLD, 16));

		nvUpload.setFont(new Font("Tahoma", Font.PLAIN, 24));
		nvUpload.setEditable(false);
		nvUpload.setBackground(Color.WHITE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addComponent(label).addGap(18).addComponent(
										tipo, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
								.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblNivelGif).addGap(18)
								.addComponent(nvGif, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNivelDescarga).addGap(18)
								.addComponent(nvUpload, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addGap(23)))));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup().addContainerGap(22, Short.MAX_VALUE)
												.addComponent(label))
										.addGroup(gl_panel.createSequentialGroup().addGap(19)
												.addComponent(tipo, GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)))
								.addGap(40)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(nvUpload, GroupLayout.PREFERRED_SIZE, 27,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNivelGif, GroupLayout.PREFERRED_SIZE, 20,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNivelDescarga, GroupLayout.PREFERRED_SIZE, 20,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(nvGif, GroupLayout.PREFERRED_SIZE, 27,
												GroupLayout.PREFERRED_SIZE))
								.addGap(29)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		panel.setLayout(gl_panel);

		GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addGap(18)
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panel, 0, 0, Short.MAX_VALUE)
								.addComponent(panelNombre, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
						.addContainerGap(34, Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addGap(28).addGroup(jPanel3Layout
						.createParallelGroup(Alignment.TRAILING, false).addComponent(jScrollPane1, Alignment.LEADING)
						.addGroup(Alignment.LEADING, jPanel3Layout.createSequentialGroup()
								.addComponent(panelNombre, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)))
						.addGap(83)));
		jPanel3.setLayout(jPanel3Layout);

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 670, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(359, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 576, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(109, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		if (jList1.getModel().getSize() == 0) {

			try {
				verNotas();
			}

			catch (Exception e) {
//
			}

		}

		pack();
	}

	public static void verNotas() throws SQLException, IOException {

		modelo.removeAllElements();

		Connection conexion = Metodos.conexionBD();

		Statement s = conexion.createStatement();

		ResultSet rs = s.executeQuery(
				"select cat_id,cat_name from " + MenuPrincipal.getLecturabd()[3] + "categories order by cat_name");

		while (rs.next()) {
			modelo.addElement(rs.getString("cat_id") + " - " + rs.getString("cat_name"));
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

					s.executeUpdate("DELETE FROM " + MenuPrincipal.getLecturabd()[3] + "categories WHERE cat_name='"
							+ jList1.getSelectedValue() + "'");
					vaciarDatos();
					verNotas();
					s.close();
				} catch (SQLException e) {
					//
				}

			}

		}

		else {
			Metodos.mensaje("Seleccione un registro para borrar", 2);
		}
	}

	public boolean controlarSeleccion() {

		try {
			jList1.getSelectedValue().toString();
			return false;
		}

		catch (NullPointerException e1) {
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
		}

		catch (Exception e) {
			//
		}

		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {
					new AgendaInterfaz().setVisible(true);
				}

				catch (Exception e) {
					//
				}

			}

		});

	}
}