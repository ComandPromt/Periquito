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
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("all")

public class Backup extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {

	javax.swing.JLabel jLabel1;
	javax.swing.JTextField jTextField1;

	@SuppressWarnings("all")
	public void buscarArchivoConf() throws IOException {
		File af = new File("Config/Backup.txt");

		if (af.exists()) {
			String[] lectura;
			try {
				lectura = Metodos.leerFicheroArray("Backup.txt", 1);

				if (lectura[0] == null) {
					lectura[0] = "1";
				}

				jTextField1.setText(lectura[0]);

			} catch (ArrayIndexOutOfBoundsException e) {
				Metodos.mensaje("Error al leer el archivo Backup.txt", 1);
			}
			guardarDatos(false);
		}
	}

	void guardarDatos(Boolean mensaje) throws IOException {

		FileWriter flS = new FileWriter("Config/Backup.txt");
		BufferedWriter fS = new BufferedWriter(flS);

		try {

			String texto = jTextField1.getText().trim();

			if (texto.isEmpty() && !MenuPrincipal.getOs().equals("Linux")) {
				texto = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
			}

			if (texto.isEmpty() && MenuPrincipal.getOs().equals("Linux")) {
				texto = "/home/" + System.getProperty("user.name");
			}

			fS.write(texto);
			fS.newLine();
			fS.close();
			flS.close();
			dispose();

			MenuPrincipal.setLecturabackup(Metodos.leerFicheroArray("Backup.txt", 1));

			if (mensaje) {
				Metodos.mensaje("Archivo guardado con exito!", 2);
			}

		} catch (IOException e) {

			if (mensaje) {
				Metodos.mensaje("Error al crear el fichero de configuracion", 1);
			}

		} finally {
			fS.close();
			flS.close();
		}
	}

	public Backup() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Backup.class.getResource("/imagenes/bd.png")));
		setTitle("Periquito v3 Backup");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	public void initComponents() throws IOException {

		jTextField1 = new javax.swing.JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.CENTER);
		jTextField1.setToolTipText("");

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText("Backup");
		jLabel1.setIcon(new ImageIcon(Backup.class.getResource("/imagenes/folder.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 24));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));
		buscarArchivoConf();

		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				File[] files = Metodos.seleccionar(1, "Elija la carpeta para hacer el backup de la bd",
						"Elija una carpeta para hacer el backup de la bd");

				try {
					String texto = files[0].getCanonicalPath();
					jTextField1.setText(texto);
					guardarDatos(true);
				} catch (Exception e1) {
					Metodos.mensaje("Error al guardar la configuración", 1);
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(Backup.class.getResource("/imagenes/abrir.png")));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(18).addComponent(jLabel1).addGap(18)
						.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(21, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)))
						.addGroup(layout.createSequentialGroup().addGap(19).addComponent(btnNewButton,
								GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		getContentPane().setLayout(layout);
		setSize(new Dimension(519, 130));
		setLocationRelativeTo(null);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}