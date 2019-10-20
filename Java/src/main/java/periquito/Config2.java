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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("all")

public class Config2 extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	javax.swing.JLabel jLabel1;
	static javax.swing.JTextField jTextField1;
	private JTextField textField;
	JLabel lblThumbnails;

	@SuppressWarnings("all")
	public void buscarArchivoConf() throws IOException {
		File af = new File("Config/Config2.txt");

		if (af.exists()) {
			String[] lectura;
			try {
				lectura = Metodos.leerFicheroArray("Config/Config2.txt", 2);

				if (lectura[0] == null) {
					lectura[0] = "";
				}
				if (lectura[1] == null) {
					lectura[1] = "";
				}

				lectura[0] = Metodos.eliminarUltimoElemento(lectura[0]);
				lectura[1] = Metodos.eliminarUltimoElemento(lectura[1]);

				jTextField1.setText(lectura[0]);
				textField.setText(lectura[1]);
			} catch (ArrayIndexOutOfBoundsException e) {

			}
			guardarDatos(false);
		}
	}

	public void guardarDatos(Boolean mensaje) throws IOException {
		FileWriter flS = new FileWriter("Config/Config2.txt");
		BufferedWriter fS = new BufferedWriter(flS);

		try {

			fS.write(jTextField1.getText().trim());
			fS.newLine();
			fS.write(textField.getText().trim());
			fS.close();
			flS.close();
			dispose();

			MenuPrincipal.setLecturaurl(Metodos.leerFicheroArray("Config/Config2.txt", 2));

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

	public Config2() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Config2.class.getResource("/imagenes/config.png")));
		setTitle("Periquito v3 Config Remoto");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	@SuppressWarnings("all")
	public void initComponents() throws IOException {

		jTextField1 = new javax.swing.JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.LEFT);
		jTextField1.setToolTipText("");

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText("Servidor");
		jLabel1.setIcon(new ImageIcon(Config2.class.getResource("/imagenes/remote.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 24));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setToolTipText("");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));

		buscarArchivoConf();

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Config2.class.getResource("/imagenes/save.png")));

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				if (jTextField1.getText() != null && jTextField1.getText().length() >= 2 && textField.getText() != null
						&& textField.getText().length() >= 2) {

					String comprobacion1 = jTextField1.getText().trim().replaceAll("  ", " ");
					String comprobacion2 = textField.getText().trim().replaceAll("  ", " ");

					if (comprobacion1.length() >= 2 && comprobacion2.length() >= 2) {

						jTextField1.setText(comprobacion1);

						textField.setText(comprobacion2);

						dispose();

						try {
							guardarDatos(true);

						} catch (IOException e) {
							Metodos.mensaje("Error al guargar la configuración", 1);
						}

					}
				}
			}
		});

		lblThumbnails = new JLabel("Carpeta del cms");
		lblThumbnails.setIcon(new ImageIcon(Config2.class.getResource("/imagenes/folder.png")));
		lblThumbnails.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton button = new JButton("");

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				File[] files = Metodos.seleccionar(1, "Elija la carpeta del CMS", "");

				try {
					String texto = files[0].getCanonicalPath();
					textField.setText(texto);

				} catch (Exception e1) {
					Metodos.mensaje("Error al guardar la configuración", 1);
				}
			}
		});

		button.setIcon(new ImageIcon(Config2.class.getResource("/imagenes/abrir.png")));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(layout.createSequentialGroup().addGap(28)
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblThumbnails)
										.addComponent(jLabel1))
								.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addGroup(layout.createSequentialGroup().addGap(10).addComponent(textField,
												GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(jTextField1)))
								.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(
								layout.createSequentialGroup()
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 63,
												GroupLayout.PREFERRED_SIZE)
										.addGap(119)))
						.addGap(18).addComponent(button, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addGap(22)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(jTextField1,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(38)
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblThumbnails)
						.addGroup(layout.createSequentialGroup().addGap(10)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addComponent(button, GroupLayout.PREFERRED_SIZE, 43,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))))
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnNewButton).addGap(174)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(643, 303));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}