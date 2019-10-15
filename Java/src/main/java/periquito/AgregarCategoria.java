package periquito;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
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

public class AgregarCategoria extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {

	static javax.swing.JTextField usuario;
	JTextArea nota = new JTextArea("", 0, 50);
	JComboBox subcategoria;

	public AgregarCategoria() {
		setTitle("Periquito v3 Agregar Comentario");
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
		jLabel1.setText(" Nombre");
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

					usuariobd = usuariobd.substring(0, 1).toUpperCase() + usuariobd.substring(1);

					String notabd = nota.getText().trim();

					if (!usuariobd.equals("") && !notabd.equals("")) {

						try {

							Connection conexion = Metodos.conexionBD();

							Statement s = conexion.createStatement();

							try {

								s.executeUpdate("INSERT INTO notas (nombre,tipo,descripcion) VALUES('" + usuariobd
										+ "','" + notabd + "')");
								AgendaInterfaz.verNotas();
								dispose();
								Metodos.mensaje("La nota se ha insertado correctamente", 2);

							} catch (SQLException e1) {
								dispose();
								Metodos.mensaje("El usuario " + usuario.getText() + " ya está en la BD", 3);

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
					} else {
						Metodos.mensaje("Rellene todos los campos", 3);
					}
				} else {
					Metodos.mensaje("Rellene todos los campos", 3);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JRadioButton categoriaPadre = new JRadioButton("Categoría padre");
		JRadioButton categoriaHija = new JRadioButton("Categoría hija");

		categoriaPadre.setSelected(true);

		categoriaPadre.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				categoriaHija.setSelected(false);
				subcategoria.setEnabled(false);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				categoriaHija.setSelected(false);
				categoriaPadre.setSelected(true);
				subcategoria.setEnabled(false);

			}
		});

		categoriaPadre.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblComentario = new JLabel();
		lblComentario.setIcon(new ImageIcon(AgregarComentario.class.getResource("/imagenes/name.png")));
		lblComentario.setText("Descripción");
		lblComentario.setHorizontalAlignment(SwingConstants.CENTER);
		lblComentario.setFont(new Font("Tahoma", Font.BOLD, 20));

		subcategoria = new JComboBox();
		subcategoria.setEnabled(false);

		nota.setWrapStyleWord(true);
		nota.setLineWrap(true);
		nota.setFont(new Font("Monospaced", Font.PLAIN, 20));

		categoriaHija.setFont(new Font("Tahoma", Font.PLAIN, 18));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup().addComponent(jLabel1).addGap(50))
						.addGroup(layout.createSequentialGroup().addComponent(lblComentario).addGap(18)))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout
										.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addGroup(layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(nota, GroupLayout.PREFERRED_SIZE, 363,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(subcategoria, GroupLayout.PREFERRED_SIZE, 172,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(layout.createSequentialGroup().addGap(121).addComponent(
														btnNewButton, GroupLayout.PREFERRED_SIZE, 109,
														GroupLayout.PREFERRED_SIZE)))
										.addGap(355).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 366,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(layout.createSequentialGroup().addComponent(categoriaPadre)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(categoriaHija, GroupLayout.PREFERRED_SIZE, 133,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(usuario, 366, 366, 366)))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(24)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(usuario,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout
								.createSequentialGroup().addGap(29)
								.addGroup(layout
										.createParallelGroup(Alignment.BASELINE).addComponent(categoriaPadre)
										.addComponent(categoriaHija, GroupLayout.PREFERRED_SIZE, 31,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGap(187).addComponent(scrollPane,
												GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
										.addGroup(
												layout.createSequentialGroup().addGap(26)
														.addComponent(subcategoria, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(29)
														.addComponent(nota, GroupLayout.PREFERRED_SIZE, 165,
																GroupLayout.PREFERRED_SIZE)
														.addGap(28).addComponent(btnNewButton)))
								.addContainerGap(23, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblComentario, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addGap(192)))));
		getContentPane().setLayout(layout);
		setSize(new Dimension(623, 550));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}