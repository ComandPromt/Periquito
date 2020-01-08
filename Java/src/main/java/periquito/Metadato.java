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
import javax.swing.UnsupportedLookAndFeelException;

import utils.Metodos;

@SuppressWarnings("all")
public class Metadato extends JFrame {
	private JButton buscar;
	private JButton contactos;
	private JButton editar;

	private JButton eliminarContacto;

	private static JList<String> jList1;

	private JTextArea nota;
	private JTextPane nombre;
	private JTextPane tipo;
	static DefaultListModel<String> modelo = new DefaultListModel<>();
	private static LinkedList<String> notas = new LinkedList<>();
	String iduser;
	transient ResultSet rs;
	transient Statement s;
	String cnombre;
	String ctipo;
	String cnota;

	public Metadato() throws IOException, SQLException {

		initComponents();

		setIconImage(Toolkit.getDefaultToolkit().getImage(AgendaInterfaz.class.getResource("/imagenes/name.png")));
		setResizable(false);
		setAutoRequestFocus(false);

		this.setSize(new Dimension(670, 605));
		buscar.setToolTipText("Buscar");

		contactos.setToolTipText("Mostrar todos los contactos");
		editar.setToolTipText("Editar");

		this.setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Insertar");
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setBackground(Color.BLACK);
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
				} catch (IOException e1) {
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
								nombre.setText(cnombre);
								tipo.setText(ctipo);
								nota.setText(cnota);

								rs = s.executeQuery("select id from notas WHERE Nombre='" + rs.getString("Nombre")
										+ "' order by Nombre");
								rs.next();
								iduser = rs.getString("id");

								nombre.setEditable(true);
								tipo.setEditable(true);
								nota.setEditable(true);

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

							s.executeUpdate("UPDATE notas SET nombre='" + usuario + "',tipo='" + tipo1
									+ "',descripcion='" + nota1 + "' WHERE id='" + iduser + "'");

							rs.close();
							s.close();
							vaciarDatos();
							verNotas();
							Metodos.mensaje("La nota " + usuario + " se ha actualizado correctamente", 2);

						} catch (SQLException e1) {

							Metodos.mensaje("La nota " + usuario + " ya está en la BD", 3);

							vaciarDatos();
						} catch (IOException e1) {
							//
						}

					}

					else {
						if (usuario.equals("") && tipo1.equals("") && nota1.equals("")) {
							Metodos.mensaje("Por favor, visualize un usuario", 2);
						} else {
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
		JPanel panelCasa;
		JPanel panelCelular;
		JPanel panelNombre;
		JScrollPane scrollPane;
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
		setTitle("Periquito v3 Metadatos");
		setBackground(new java.awt.Color(123, 123, 123));

		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane1.setDoubleBuffered(true);

		jList1.setBackground(new java.awt.Color(254, 254, 254));

		jScrollPane1.setViewportView(jList1);
		panelNombre = new JPanel();
		JLabel jLabel3;
		JLabel jLabel5;
		JLabel jLabel6;
		jLabel3 = new JLabel();

		panelNombre.setBackground(new Color(214, 217, 223));

		jLabel3.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/user.png")));

		nombre = new JTextPane();
		nombre.setBackground(new Color(255, 255, 255));
		nombre.setEditable(false);
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 24));

		GroupLayout panelNombreLayout = new GroupLayout(panelNombre);
		panelNombreLayout.setHorizontalGroup(panelNombreLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(panelNombreLayout.createSequentialGroup().addContainerGap().addComponent(jLabel3).addGap(18)
						.addComponent(nombre, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(14, Short.MAX_VALUE)));
		panelNombreLayout.setVerticalGroup(panelNombreLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(panelNombreLayout.createSequentialGroup()
						.addGroup(panelNombreLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(panelNombreLayout.createSequentialGroup().addContainerGap()
										.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
								.addGroup(panelNombreLayout.createSequentialGroup().addGap(32).addComponent(nombre,
										GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		panelNombre.setLayout(panelNombreLayout);
		panelCasa = new JPanel();
		jLabel5 = new JLabel();

		panelCasa.setBackground(new Color(214, 217, 223));

		jLabel5.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/name.png")));

		tipo = new JTextPane();
		tipo.setBackground(new Color(255, 255, 255));
		tipo.setEditable(false);
		tipo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panelCelular = new JPanel();
		jLabel6 = new JLabel();
		jLabel6.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/nota.png")));

		panelCelular.setBackground(new Color(214, 217, 223));

		scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		GroupLayout panelCelularLayout = new GroupLayout(panelCelular);
		panelCelularLayout.setHorizontalGroup(panelCelularLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(panelCelularLayout.createSequentialGroup()
						.addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(573, Short.MAX_VALUE)));
		panelCelularLayout
				.setVerticalGroup(panelCelularLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING,
								panelCelularLayout.createSequentialGroup().addGap(50)
										.addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(60, Short.MAX_VALUE))
						.addGroup(panelCelularLayout.createSequentialGroup().addContainerGap(13, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));

		nota = new JTextArea("", 0, 50);
		scrollPane.setViewportView(nota);
		nota.setBackground(new Color(255, 255, 255));
		nota.setEditable(false);
		nota.setWrapStyleWord(true);
		nota.setFont(new Font("Monospaced", Font.PLAIN, 20));
		nota.setLineWrap(true);
		panelCelular.setLayout(panelCelularLayout);

		GroupLayout panelCasaLayout = new GroupLayout(panelCasa);
		panelCasaLayout.setHorizontalGroup(panelCasaLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING,
						panelCasaLayout.createSequentialGroup().addContainerGap().addComponent(jLabel5).addGap(18)
								.addComponent(tipo, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(573, Short.MAX_VALUE))
				.addGroup(panelCasaLayout.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(panelCelular,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
		panelCasaLayout.setVerticalGroup(panelCasaLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(panelCasaLayout.createSequentialGroup()
						.addGroup(panelCasaLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(panelCasaLayout.createSequentialGroup().addGap(26).addComponent(jLabel5))
								.addGroup(panelCasaLayout.createSequentialGroup().addGap(34).addComponent(tipo,
										GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addComponent(panelCelular, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelCasa.setLayout(panelCasaLayout);

		JLabel lblNewLabel = new JLabel("Buscar por dispositivo, comentario, etc");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel3Layout
				.createSequentialGroup()
				.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel3Layout.createSequentialGroup().addGap(18)
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(panelCasa, 0, 0, Short.MAX_VALUE)
										.addComponent(panelNombre, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)))
						.addGroup(jPanel3Layout.createSequentialGroup().addGap(74).addComponent(lblNewLabel)))
				.addContainerGap(89, Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel3Layout
				.createSequentialGroup().addGap(8).addComponent(lblNewLabel).addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jScrollPane1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
						.addGroup(jPanel3Layout.createSequentialGroup()
								.addComponent(panelNombre, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panelCasa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		jPanel3.setLayout(jPanel3Layout);

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 727, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(302, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout
						.createSequentialGroup().addComponent(jPanel3, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(217, Short.MAX_VALUE)));
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

		modelo.removeAllElements();

		Connection conexion = Metodos.conexionBD();

		Statement s = conexion.createStatement();

		ResultSet rs = s.executeQuery("select Nombre from notas order by Nombre");

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