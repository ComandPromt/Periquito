package periquito;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("all")

public class AgregarUrl extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {

	static javax.swing.JTextField usuario;

	JTextArea nota = new JTextArea("", 0, 50);

	public AgregarUrl() {

		setTitle("Periquito v3 Agregar Nota");

		setType(Type.UTILITY);

		initComponents();

	}

	@SuppressWarnings("all")

	public void initComponents() {

		usuario = new javax.swing.JTextField();

		usuario.setHorizontalAlignment(SwingConstants.CENTER);

		usuario.setToolTipText("");

		javax.swing.JLabel jLabel1;
		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText("Nombre");
		jLabel1.setIcon(new ImageIcon(Agregar.class.getResource("/imagenes/user.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		usuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Agregar.class.getResource("/imagenes/insert.png")));
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				String usuariobd = usuario.getText().trim();

				if (!usuariobd.isEmpty()) {

					String notabd = nota.getText().trim();

					if (!usuariobd.equals("") && !notabd.equals("")) {

						try {

							Connection conexion = Metodos.conexionBD();

							Statement s = conexion.createStatement();

							try {

								s.executeUpdate("INSERT INTO notasurl (URL,Descripcion) VALUES('" + usuariobd + "','"
										+ notabd + "')");

								Urls.verNotas();

								dispose();

								Metodos.mensaje("La nota se ha insertado correctamente", 2);

							} catch (SQLException e1) {

								dispose();

								Metodos.mensaje("La URL " + usuario.getText() + " ya está en la BD", 3);

							} catch (NullPointerException e1) {
								dispose();

							}

						} catch (Exception e) {

							dispose();

							try {
								new Bd().setVisible(true);
							} catch (IOException e1) {
								//
							}

						}

					}

					else {
						Metodos.mensaje("Rellene todos los campos", 3);
					}

				}

				else {
					Metodos.mensaje("Rellene todos los campos", 3);
				}

			}

		});

		JLabel lblDescripcion = new JLabel();
		lblDescripcion.setIcon(new ImageIcon(Agregar.class.getResource("/imagenes/nota.png")));
		lblDescripcion.setText("Obs");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 20));

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap().addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup().addComponent(lblDescripcion).addGap(68))
								.addGroup(layout.createSequentialGroup().addComponent(jLabel1)
										.addPreferredGap(ComponentPlacement.UNRELATED)))
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 366,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(usuario, 366, 366, 366)))
						.addGroup(layout.createSequentialGroup()
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addGap(136)))
				.addGap(155)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addGap(24)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(usuario,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(57)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(47).addComponent(lblDescripcion))
						.addGroup(layout.createSequentialGroup().addGap(6).addComponent(scrollPane,
								GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)))
				.addGap(18).addComponent(btnNewButton).addContainerGap(30, Short.MAX_VALUE)));

		nota.setWrapStyleWord(true);
		nota.setLineWrap(true);
		nota.setFont(new Font("Monospaced", Font.PLAIN, 20));
		scrollPane.setViewportView(nota);
		getContentPane().setLayout(layout);
		setSize(new Dimension(586, 521));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}