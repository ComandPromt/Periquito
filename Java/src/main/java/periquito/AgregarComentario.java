package periquito;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
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

import rojeru_san.componentes.RSDateChooser;
import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("all")

public class AgregarComentario extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {

	static javax.swing.JTextField usuario;
	JTextArea nota = new JTextArea("", 0, 50);
	private JTextField tipo;
	private RSDateChooser textField;

	public AgregarComentario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarComentario.class.getResource("/imagenes/nota.png")));
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
		lblCommentid.setText(" Día");
		lblCommentid.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommentid.setFont(new Font("Tahoma", Font.BOLD, 20));

		textField = new RSDateChooser();
		textField.setToolTipText("");

		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Publico");

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Solo los usuarios");

		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				rdbtnNewRadioButton.setSelected(false);
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				if (!rdbtnNewRadioButton_1.isSelected() && !rdbtnNewRadioButton.isSelected()) {
					rdbtnNewRadioButton_1.setSelected(true);
				}
			}
		});

		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				rdbtnNewRadioButton_1.setSelected(false);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (!rdbtnNewRadioButton_1.isSelected() && !rdbtnNewRadioButton.isSelected()) {
					rdbtnNewRadioButton.setSelected(true);
				}
			}
		});
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblComentario = new JLabel();
		lblComentario.setIcon(new ImageIcon(AgregarComentario.class.getResource("/imagenes/name.png")));
		lblComentario.setText(" Comentario");
		lblComentario.setHorizontalAlignment(SwingConstants.CENTER);
		lblComentario.setFont(new Font("Tahoma", Font.BOLD, 20));

		JTextArea textArea = new JTextArea();
		textArea.setText(" Arrastra los archivos aqui");
		textArea.setForeground(Color.DARK_GRAY);
		textArea.setFont(new Font("Tahoma", Font.BOLD, 24));
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addGap(22)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout
								.createSequentialGroup()
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 162,
										Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 109,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblComentario)
										.addComponent(lblCommentid).addComponent(lblTipo).addComponent(jLabel1))
								.addGap(36)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 372,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
										.addComponent(tipo, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
										.addGroup(
												layout.createSequentialGroup().addComponent(rdbtnNewRadioButton)
														.addPreferredGap(ComponentPlacement.RELATED, 132,
																Short.MAX_VALUE)
														.addComponent(rdbtnNewRadioButton_1))
										.addComponent(usuario, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))))
				.addGap(161)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING)
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
						.addGap(34)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCommentid, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(76).addComponent(lblComentario,
										GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(18).addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(rdbtnNewRadioButton)
								.addComponent(rdbtnNewRadioButton_1))
						.addGap(30)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton))
						.addGap(33)));

		nota.setWrapStyleWord(true);
		nota.setLineWrap(true);
		nota.setFont(new Font("Monospaced", Font.PLAIN, 20));
		scrollPane.setViewportView(nota);
		getContentPane().setLayout(layout);
		setSize(new Dimension(670, 687));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}