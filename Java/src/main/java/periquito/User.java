package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("all")
public class User extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	private javax.swing.JLabel jLabel1;
	static javax.swing.JTextField jTextField1;
	private JLabel lblContrasea;
	private JTextField txtHttplocalhost;

	@SuppressWarnings("all")
	public void buscarArchivoConf() throws IOException {
		File af = new File("Config/User.txt");

		if (af.exists()) {
			String[] lectura;
			try {

				lectura = Metodos.leerFicheroArray("Config/User.txt", 2);

				if (lectura[0] == null || lectura[0].isEmpty()) {
					lectura[0] = "admin";
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

			FileWriter flS = new FileWriter("Config/User.txt");
			BufferedWriter fS = new BufferedWriter(flS);

			fS.write(texto);
			fS.newLine();
			fS.write(servidor);
			fS.close();
			flS.close();
			dispose();

			MenuPrincipal.setUser(Metodos.leerFicheroArray("Config/User.txt", 2));

			if (mensaje) {
				Metodos.mensaje("Archivo guardado con exito!", 2);
			}

		} catch (IOException e) {

			if (mensaje) {
				Metodos.mensaje("Error al crear el fichero de configuracion", 1);
			}

		}
	}

	public User() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(User.class.getResource("/imagenes/user.png")));
		setTitle("Periquito v3 User");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	public void initComponents() throws IOException {

		jTextField1 = new JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.CENTER);
		jTextField1.setToolTipText("");

		txtHttplocalhost = new JTextField();
		txtHttplocalhost.setToolTipText("");
		txtHttplocalhost.setHorizontalAlignment(SwingConstants.CENTER);
		txtHttplocalhost.setFont(new Font("Tahoma", Font.PLAIN, 20));

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText("Usuario");
		jLabel1.setIcon(new ImageIcon(User.class.getResource("/imagenes/user.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));
		buscarArchivoConf();

		lblContrasea = new JLabel();
		lblContrasea.setIcon(new ImageIcon(User.class.getResource("/imagenes/user_pass.png")));
		lblContrasea.setText("Clave");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
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
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel1)
						.addComponent(lblContrasea, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
				.addGap(26)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addComponent(txtHttplocalhost, 236, 236, 236)
								.addGap(18).addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 46,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(37, Short.MAX_VALUE)));
		layout.setVerticalGroup(
				layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(24)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 47,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtHttplocalhost, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblContrasea, GroupLayout.PREFERRED_SIZE, 64,
														GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(34, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(519, 218));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
	}

	public void stateChanged(ChangeEvent e) {
	}
}