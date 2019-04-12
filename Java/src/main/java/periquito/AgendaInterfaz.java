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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import Utils.Metodos;

@SuppressWarnings("serial")
public class AgendaInterfaz extends javax.swing.JFrame {
	static javax.swing.JButton agregar;
	private javax.swing.JButton buscar;
	private javax.swing.JButton contactos;
	private javax.swing.JButton editar;
	private javax.swing.JButton editarContacto;
	private javax.swing.JButton eliminarContacto;
	private javax.swing.JButton btnNewButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private static javax.swing.JList<String> jList1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JPanel panelCasa;
	private javax.swing.JPanel panelCelular;
	private javax.swing.JPanel panelNombre;
	private JScrollPane scrollPane;
	private JTextArea nota;
	private JTextPane nombre;
	private JTextPane tipo;
	static DefaultListModel<String> modelo = new DefaultListModel<String>();

	String iduser;
	ResultSet rs;
	Statement s;
	String cnombre;
	String ctipo;
	String cnota;

	public AgendaInterfaz() throws IOException {
		
		try {
			initComponents();
		} catch (Exception e) {

		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(AgendaInterfaz.class.getResource("/imagenes/name.png")));
		setResizable(false);
		setAutoRequestFocus(false);

		this.setSize(new Dimension(670, 605));

		eliminarContacto.setToolTipText("Eliminar");
		buscar.setToolTipText("Buscar");
		agregar.setToolTipText("Agregar contacto");

		contactos.setToolTipText("Mostrar todos los contactos");
		editar.setToolTipText("Editar");

		this.setLocationRelativeTo(null);
		
	}

	public void vaciarDatos() {
		nombre.setText("");
		tipo.setText("");
		nota.setText("");
	}

	private void initComponents() throws SQLException, IOException {
		jPanel4 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		contactos = new javax.swing.JButton();
		contactos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		editar = new javax.swing.JButton();
		editar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buscar = new javax.swing.JButton();
		buscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jPanel3 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList<>();
		jList1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jList1.setFixedCellHeight(40);

		jPanel5.setBackground(new java.awt.Color(88, 205, 170));

		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		jLabel1.setText("Nombre");

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel5Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(jLabel1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(jPanel5,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup()
						.addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));
		setTitle("Periquito v3 Agenda");
		setBackground(new java.awt.Color(123, 123, 123));

		jPanel1.setBackground(new Color(240, 240, 240));
		agregar = new javax.swing.JButton();
		agregar.setFont(new Font("Tahoma", Font.PLAIN, 16));

		agregar.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/insert.png")));
		agregar.setBorder(null);
		agregar.setBorderPainted(false);
		agregar.setContentAreaFilled(false);
		agregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		agregar.setFocusPainted(false);
		agregar.setRolloverIcon(null);
		agregar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				new Agregar().setVisible(true);
			}
		});
		editarContacto = new javax.swing.JButton();

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

						} else {

							try {

								rs = s.executeQuery("select Nombre,tipo,descripcion from notas WHERE Nombre='"
										+ jList1.getSelectedValue().toString() + "' order by Nombre");
								rs.next();
								cnombre = rs.getString("Nombre");
								ctipo = rs.getString("tipo");
								cnota = rs.getString("descripcion");
								nombre.setText(rs.getString("Nombre"));
								tipo.setText(rs.getString("tipo"));
								nota.setText(rs.getString("descripcion"));

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
				}
			}
		});

		btnNewButton = new JButton();
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!controlarSeleccion()) {
					nombre.setText(nombre.getText().trim());
					tipo.setText(tipo.getText().trim());
					nota.setText(nota.getText().trim());
					if (!nombre.getText().equals("") && !tipo.getText().equals("") && !nota.getText().equals("")
							&& (!cnombre.equals(nombre.getText()) || !ctipo.equals(tipo.getText())
									|| !cnota.equals(nota.getText()))) {

						try {
							String usuario = nombre.getText();
							s.executeUpdate("UPDATE notas SET nombre='" + nombre.getText() + "',tipo='"
									+ tipo.getText() + "',descripcion='" + nota.getText() + "' WHERE id=" + iduser);

							rs.close();
							s.close();
							vaciarDatos();
							verNotas();
							Metodos.mensaje("La nota " + usuario + " se ha actualizado correctamente", 2);

						} catch (SQLException e1) {

							Metodos.mensaje("La nota " + nombre.getText() + " ya está en la BD", 3);

							vaciarDatos();
						} catch (IOException e1) {
								}

					}

					else {
						if (nombre.getText().equals("") && tipo.getText().equals("") && nota.getText().equals("")) {
							Metodos.mensaje("Por favor, visualize un usuario", 2);
						} else {
							if (nombre.getText().equals("") || tipo.getText().equals("") || nota.getText().equals("")) {
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
		eliminarContacto = new javax.swing.JButton();

		eliminarContacto.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/delete.png")));
		eliminarContacto.setBorderPainted(false);
		eliminarContacto.setContentAreaFilled(false);
		eliminarContacto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		eliminarContacto.setFocusPainted(false);
		eliminarContacto.setRolloverIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/delete_1.png")));
		eliminarContacto.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				try {
					eliminarContactoMouseClicked(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JSeparator separator = new JSeparator();

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout
				.createSequentialGroup().addContainerGap()
				.addComponent(agregar, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(editarContacto, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE).addGap(69)
				.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE).addGap(32)
				.addComponent(eliminarContacto, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(77, Short.MAX_VALUE))
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout
				.createSequentialGroup().addGap(4)
				.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addComponent(agregar, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
						.addComponent(editarContacto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
										.addComponent(eliminarContacto, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)));
		jPanel1.setLayout(jPanel1Layout);

		jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane1.setDoubleBuffered(true);

		jList1.setBackground(new java.awt.Color(254, 254, 254));
		jList1.addMouseListener(new java.awt.event.MouseAdapter() {

		});
		jList1.addKeyListener(new java.awt.event.KeyAdapter() {

		});
		jScrollPane1.setViewportView(jList1);
		panelNombre = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();

		panelNombre.setBackground(new Color(214, 217, 223));

		jLabel3.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/user.png")));

		nombre = new JTextPane();
		nombre.setBackground(new Color(255, 255, 255));
		nombre.setEditable(false);
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 24));

		javax.swing.GroupLayout panelNombreLayout = new javax.swing.GroupLayout(panelNombre);
		panelNombreLayout.setHorizontalGroup(panelNombreLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(panelNombreLayout.createSequentialGroup().addContainerGap().addComponent(jLabel3).addGap(18)
						.addComponent(nombre, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(93, Short.MAX_VALUE)));
		panelNombreLayout.setVerticalGroup(panelNombreLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(panelNombreLayout.createSequentialGroup()
						.addGroup(panelNombreLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(panelNombreLayout.createSequentialGroup().addContainerGap()
										.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
								.addGroup(panelNombreLayout.createSequentialGroup().addGap(32).addComponent(nombre,
										GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		panelNombre.setLayout(panelNombreLayout);
		panelCasa = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();

		panelCasa.setBackground(new Color(214, 217, 223));

		jLabel5.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/name.png")));

		tipo = new JTextPane();
		tipo.setBackground(new Color(255, 255, 255));
		tipo.setEditable(false);
		tipo.setFont(new Font("Tahoma", Font.PLAIN, 24));

		javax.swing.GroupLayout panelCasaLayout = new javax.swing.GroupLayout(panelCasa);
		panelCasaLayout.setHorizontalGroup(panelCasaLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(panelCasaLayout.createSequentialGroup().addContainerGap().addComponent(jLabel5).addGap(18)
						.addComponent(tipo, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(90, Short.MAX_VALUE)));
		panelCasaLayout.setVerticalGroup(panelCasaLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(panelCasaLayout.createSequentialGroup()
						.addGroup(panelCasaLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(panelCasaLayout.createSequentialGroup().addGap(26).addComponent(jLabel5))
								.addGroup(panelCasaLayout.createSequentialGroup().addGap(34).addComponent(tipo,
										GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(22, Short.MAX_VALUE)));
		panelCasa.setLayout(panelCasaLayout);
		panelCelular = new javax.swing.JPanel();
		jLabel6 = new javax.swing.JLabel();
		jLabel6.setIcon(new ImageIcon(AgendaInterfaz.class.getResource("/imagenes/nota.png")));

		panelCelular.setBackground(new Color(214, 217, 223));

		scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		javax.swing.GroupLayout panelCelularLayout = new javax.swing.GroupLayout(panelCelular);
		panelCelularLayout.setHorizontalGroup(panelCelularLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(panelCelularLayout.createSequentialGroup().addContainerGap().addComponent(jLabel6).addGap(18)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE).addContainerGap()));
		panelCelularLayout.setVerticalGroup(panelCelularLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(panelCelularLayout.createSequentialGroup().addContainerGap().addComponent(jLabel6,
						GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
				.addGroup(panelCelularLayout.createSequentialGroup().addGap(19)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		nota = new JTextArea("", 0, 50);
		nota.setBackground(new Color(255, 255, 255));
		nota.setEditable(false);
		nota.setWrapStyleWord(true);
		nota.setFont(new Font("Monospaced", Font.PLAIN, 20));
		scrollPane.setViewportView(nota);
		nota.setLineWrap(true);
		panelCelular.setLayout(panelCelularLayout);

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel3Layout
				.createSequentialGroup().addGap(18)
				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelNombre, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelCasa, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelCelular, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(89, Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel3Layout
				.createSequentialGroup().addGap(28)
				.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jScrollPane1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
						.addGroup(jPanel3Layout.createSequentialGroup()
								.addComponent(panelNombre, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
								.addComponent(panelCasa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(panelCelular,
										GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
				.addGap(31)));
		jPanel3.setLayout(jPanel3Layout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(jPanel1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jPanel3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE))
				.addContainerGap(302, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(156, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		if (jList1.getModel().getSize() == 0) {
			try {
				verNotas();
			} catch (Exception e) {

				
			}
		}

		pack();
	}
	
	public static void verNotas() throws SQLException, IOException {
		modelo.removeAllElements();
		Connection conexion = Metodos.conexionBD();

		Statement s = conexion.createStatement();

		ResultSet rs = s.executeQuery("select Nombre from notas order by Nombre");

		while (rs.next()) {
			modelo.addElement(rs.getString("Nombre"));
		}

		jList1.setModel(modelo);
		rs.close();
		s.close();
	}

	private void eliminarContactoMouseClicked(java.awt.event.MouseEvent evt) throws IOException {

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

	public static void main(String args[])  {
	
	
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
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
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AgendaInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AgendaInterfaz().setVisible(true);
				} catch (IOException e) {
	
				}
			}
		});
		
	}

}