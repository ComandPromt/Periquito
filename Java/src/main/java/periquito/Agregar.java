package periquito;

import java.applet.AudioClip;
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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Utils.Metodos;
import Utils.interfaz;

@SuppressWarnings("serial")

public class Agregar extends javax.swing.JFrame implements ActionListener, ChangeListener, interfaz {
	private javax.swing.JLabel jLabel1;
	static javax.swing.JTextField usuario;
	static JCheckBox mute;
	JTextArea nota = new JTextArea("", 0, 50);
	private JTextField tipo;

	public void mensaje(String mensaje, Boolean error) {
		JLabel alerta = new JLabel(mensaje);
		alerta.setFont(new Font("Arial", Font.BOLD, 18));
		AudioClip clip;
		if (error) {
			clip = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/duck-quack1.wav"));
		} else {
			clip = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/gong1.wav"));
		}

		if (mute.isSelected() == true) {
			clip.stop();
		} else {
			clip.loop();
		}
		int option;
		if (error) {
			JOptionPane.showMessageDialog(null, alerta, "Error", JOptionPane.ERROR_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(null, alerta, "Success", JOptionPane.INFORMATION_MESSAGE);

		}
		option = JOptionPane.CLOSED_OPTION;
		if (option == -1) {
			clip.stop();
		}
	}

	public Agregar() {
		setTitle("Periquito v3 Agregar Usuario");
		setType(Type.UTILITY);
		initComponents();
	}

	public void initComponents() {

		usuario = new javax.swing.JTextField();
		usuario.setHorizontalAlignment(SwingConstants.CENTER);
		usuario.setToolTipText("");

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText("Usuario");
		jLabel1.setIcon(new ImageIcon(Agregar.class.getResource("/imagenes/user.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		usuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));

		mute = new JCheckBox("");
		mute.setBounds(480, 390, 20, 20);
		mute.addChangeListener(this);
		mute.setFont(new java.awt.Font("Tahoma", 1, 18));

		getContentPane().add(mute);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Agregar.class.getResource("/imagenes/WAV_00002.png")));
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Agregar.class.getResource("/imagenes/insert.png")));
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				String usuariobd = usuario.getText().trim();
				usuariobd = usuariobd.substring(0, 1).toUpperCase() + usuariobd.substring(1);
				String tipobd = tipo.getText().trim();
				String notabd = nota.getText().trim();

				if (!usuariobd.equals("") && !tipobd.equals("") && !notabd.equals("")) {

					try {

						Connection conexion = Metodos.conexionBD();

						Statement s = conexion.createStatement();

						try {

							s.executeUpdate("INSERT INTO notas (nombre,tipo,descripcion) VALUES('" + usuariobd + "','"
									+ tipobd + "','" + notabd + "')");
							AgendaInterfaz.verNotas();
							dispose();
							mensaje("La nota se ha insertado correctamente", false);

						} catch (SQLException e1) {
							dispose();
							mensaje("El usuario " + usuario.getText() + " ya está en la BD", true);

						} catch (NullPointerException e1) {
							dispose();

						}

					} catch (SQLException | IOException e) {
dispose();
					}
				} else {
					mensaje("Rellene todos los campos", true);
				}

			}
		});

		JLabel lblTipo = new JLabel();
		lblTipo.setIcon(new ImageIcon(Agregar.class.getResource("/imagenes/name.png")));
		lblTipo.setText("Tipo");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblDescripcion = new JLabel();
		lblDescripcion.setIcon(new ImageIcon(Agregar.class.getResource("/imagenes/nota.png")));
		lblDescripcion.setText("Nota");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 20));

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		tipo = new JTextField();
		tipo.setToolTipText("");
		tipo.setHorizontalAlignment(SwingConstants.CENTER);
		tipo.setFont(new Font("Tahoma", Font.PLAIN, 20));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblDescripcion)
										.addComponent(jLabel1).addComponent(lblTipo))
								.addGap(26)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(tipo, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 366,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(usuario, 366, 366, 366)))
								.addGroup(layout.createSequentialGroup()
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 109,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(lblNewLabel).addGap(54)))
						.addGap(155)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addGap(24)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(usuario,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(28)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblTipo).addComponent(tipo,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(47).addComponent(lblDescripcion))
						.addGroup(layout.createSequentialGroup().addGap(6).addComponent(scrollPane,
								GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)))
				.addGap(18).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
						.addComponent(btnNewButton))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		nota.setWrapStyleWord(true);
		nota.setLineWrap(true);
		nota.setFont(new Font("Monospaced", Font.PLAIN, 20));
		scrollPane.setViewportView(nota);
		getContentPane().setLayout(layout);
		setSize(new Dimension(573, 514));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
	}

	public void stateChanged(ChangeEvent e) {
	}
}