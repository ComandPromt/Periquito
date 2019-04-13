package periquito;

import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;
import utils.interfaz;

@SuppressWarnings("all")
public class Config extends javax.swing.JFrame implements ActionListener, ChangeListener, interfaz {
	private javax.swing.JLabel jLabel1;
	static javax.swing.JTextField jTextField1;
	private JLabel label;
	private JTextField txtHttplocalhost;

	public void mensaje(String mensaje, Boolean error) {
		JLabel alerta = new JLabel(mensaje);
		alerta.setFont(new Font("Arial", Font.BOLD, 18));
		AudioClip clip;
		if (error) {
			clip = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/duck-quack1.wav"));
		} else {
			clip = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/gong1.wav"));
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

	@SuppressWarnings("all")
	public void buscarArchivoConf() {
		File af = new File("Config/Config.txt");

		if (af.exists()) {
			String[] lectura;
			try {
				lectura = Metodos.leerFicheroArray("Config/Config.txt", 2);

				if (lectura[0] == null) {
					lectura[0] = "1";
				}

				if (lectura[1] == null) {
					lectura[1] = "http://localhost/Periquito";
				}

				jTextField1.setText(lectura[0]);
				txtHttplocalhost.setText(lectura[1]);

			} catch (ArrayIndexOutOfBoundsException e) {

			}
			guardarDatos(false);
		}
	}

	public void guardarDatos(Boolean mensaje) {
		try {
			String texto = jTextField1.getText().trim();
			String servidor = txtHttplocalhost.getText().trim();

			if (texto.isEmpty() || texto == null) {
				texto = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
			}

			if (servidor.isEmpty() || servidor == null) {
				servidor = "http://localhost/Periquito";
			}

			FileWriter flS = new FileWriter("Config/Config.txt");
			BufferedWriter fS = new BufferedWriter(flS);

			fS.write(texto);
			fS.newLine();
			fS.write(servidor);
			fS.close();

			dispose();

			if (mensaje) {
				mensaje("Archivo guardado con exito!", false);
			}

		} catch (IOException e) {

			if (mensaje) {
				mensaje("Error al crear el fichero de configuracion", true);
			}

		}
	}

	public Config() {
		setTitle("Periquito v3 Config ");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	public void initComponents() {

		jTextField1 = new JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.CENTER);
		jTextField1.setToolTipText("");

		txtHttplocalhost = new JTextField();
		txtHttplocalhost.setText("http://localhost/Periquito");
		txtHttplocalhost.setToolTipText("");
		txtHttplocalhost.setHorizontalAlignment(SwingConstants.CENTER);
		txtHttplocalhost.setFont(new Font("Tahoma", Font.PLAIN, 20));

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText("Web");
		jLabel1.setIcon(new ImageIcon(Backup.class.getResource("/imagenes/folder.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));
		buscarArchivoConf();

		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				File[] files = Metodos.seleccionar(1, "Elija la carpeta para hacer el backup de la bd",
						"Elija una carpeta para hacer el backup de la bd");

				try {
					jTextField1.setText(files[0].getCanonicalPath());
				} catch (Exception e1) {

				}

			}
		});
		btnNewButton.setIcon(new ImageIcon(Config.class.getResource("/imagenes/abrir.png")));

		label = new JLabel();
		label.setIcon(new ImageIcon(Config.class.getResource("/imagenes/remote.png")));
		label.setText("Web");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(Config.class.getResource("/imagenes/save.png")));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				guardarDatos(true);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup().addComponent(jLabel1).addGap(26)
								.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
								.addGap(26).addComponent(txtHttplocalhost, 0, 0, Short.MAX_VALUE)))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(18).addComponent(btnNewButton_1, 0, 0,
								Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup().addGap(45).addComponent(btnNewButton,
								GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(24)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18))
						.addGroup(
								Alignment.TRAILING,
								layout.createSequentialGroup().addContainerGap()
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 47,
												GroupLayout.PREFERRED_SIZE)
										.addGap(26)))
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup().addGap(13)
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 51,
														Short.MAX_VALUE)
												.addComponent(txtHttplocalhost, GroupLayout.PREFERRED_SIZE, 35,
														GroupLayout.PREFERRED_SIZE))))
						.addGap(32)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(532, 231));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
	}

	public void stateChanged(ChangeEvent e) {
	}
}