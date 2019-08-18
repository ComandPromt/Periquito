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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("all")

public class AgregarComentario extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {

	static javax.swing.JTextField usuario;
	JTextArea nota = new JTextArea("", 0, 50);
	private JTextField tipo;
	private JTextField textField;

	public AgregarComentario() {
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
		jLabel1.setText(" Usuario");
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
					String tipobd = tipo.getText().trim();
					String notabd = nota.getText().trim();

					if (!usuariobd.equals("") && !tipobd.equals("") && !notabd.equals("")) {

						try {

							Connection conexion = Metodos.conexionBD();

							Statement s = conexion.createStatement();

							try {

								s.executeUpdate("INSERT INTO notas (nombre,tipo,descripcion) VALUES('" + usuariobd
										+ "','" + tipobd + "','" + notabd + "')");
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

		JLabel lblTipo = new JLabel();
		lblTipo.setIcon(new ImageIcon(Agregar.class.getResource("/imagenes/name.png")));
		lblTipo.setText(" Image id");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 20));

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		tipo = new JTextField();
		tipo.setToolTipText("");
		tipo.setHorizontalAlignment(SwingConstants.CENTER);
		tipo.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblCommentid = new JLabel();
		lblCommentid.setIcon(new ImageIcon(AgregarComentario.class.getResource("/imagenes/name.png")));
		lblCommentid.setText(" Asunto");
		lblCommentid.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommentid.setFont(new Font("Tahoma", Font.BOLD, 20));

		textField = new JTextField();
		textField.setToolTipText("");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Publico");

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Solo los usuarios");
		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				rdbtnNewRadioButton_1.setSelected(true);
				rdbtnNewRadioButton.setSelected(false);
			}

		});

		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				rdbtnNewRadioButton.setSelected(true);
				rdbtnNewRadioButton_1.setSelected(false);
			}

		});
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblComentario = new JLabel();
		lblComentario.setIcon(new ImageIcon(AgregarComentario.class.getResource("/imagenes/name.png")));
		lblComentario.setText(" Comentario");
		lblComentario.setHorizontalAlignment(SwingConstants.CENTER);
		lblComentario.setFont(new Font("Tahoma", Font.BOLD, 20));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(layout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblTipo).addComponent(jLabel1))
												.addGap(62))
										.addGroup(layout.createSequentialGroup().addContainerGap()
												.addComponent(lblCommentid, GroupLayout.PREFERRED_SIZE, 155,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)))
								.addGroup(layout
										.createSequentialGroup().addContainerGap().addComponent(lblComentario)
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(tipo, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
								.addComponent(usuario, 366, 366, 366)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(rdbtnNewRadioButton).addGap(18)
										.addComponent(rdbtnNewRadioButton_1))
								.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 109,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 366,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(42)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(24)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(usuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1))
						.addGap(28)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(tipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTipo))
						.addGap(48)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCommentid, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(26)
										.addGroup(layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(rdbtnNewRadioButton).addComponent(rdbtnNewRadioButton_1))
										.addGap(18)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 167,
												GroupLayout.PREFERRED_SIZE)
										.addGap(26).addComponent(btnNewButton))
								.addGroup(layout.createSequentialGroup().addGap(129).addComponent(lblComentario,
										GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(63, Short.MAX_VALUE)));

		nota.setWrapStyleWord(true);
		nota.setLineWrap(true);
		nota.setFont(new Font("Monospaced", Font.PLAIN, 20));
		scrollPane.setViewportView(nota);
		getContentPane().setLayout(layout);
		setSize(new Dimension(636, 687));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}