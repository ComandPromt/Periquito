package periquito;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("all")

public class Scrapt extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	javax.swing.JLabel jLabel1;
	static javax.swing.JTextField jTextField1;
	private JTextField textField;
	JLabel lblThumbnails;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblDe;
	private JTextField textField_4;
	private JLabel lblHasta;
	private JTextField textField_5;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;

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
			}

			catch (ArrayIndexOutOfBoundsException e) {

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

	public Scrapt() throws IOException {
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Config2.class.getResource("/imagenes/config.png")));
		setTitle("Periquito v3 Config Remoto");
		setType(Type.UTILITY);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnNewMenu = new JMenu("Configurar");
		mnNewMenu.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/config.png")));
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnNewMenu);

		mntmNewMenuItem = new JMenuItem("Configuración");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/utilities.png")));
		mnNewMenu.add(mntmNewMenuItem);

		mntmNewMenuItem_1 = new JMenuItem("Crontab");
		mntmNewMenuItem_1.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/cron.png")));
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.add(mntmNewMenuItem_1);
		initComponents();
		this.setVisible(true);
	}

	@SuppressWarnings("all")
	public void initComponents() throws IOException {

		jTextField1 = new javax.swing.JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.LEFT);
		jTextField1.setToolTipText("");

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText(" URL");
		jLabel1.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/target.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 24));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						guardarDatos(true);
					} catch (IOException e1) {
						//
					}
				}
			}
		});
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setToolTipText("");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));

		buscarArchivoConf();

		JButton btnNewButton = new JButton("Scrapt!");
		btnNewButton.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/start.png")));

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

		lblThumbnails = new JLabel("Etiqueta");
		lblThumbnails.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/tag.png")));
		lblThumbnails.setFont(new Font("Tahoma", Font.BOLD, 20));

		JCheckBox chckbxNewCheckBox = new JCheckBox(" Recorrer paginas?");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblNewLabel = new JLabel("Paso");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel label = new JLabel("Paso");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		JLabel label_1 = new JLabel("Paso");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_3 = new JTextField();
		textField_3.setColumns(10);

		lblDe = new JLabel("  De");
		lblDe.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_4 = new JTextField();
		textField_4.setColumns(10);

		lblHasta = new JLabel("Hasta");
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_5 = new JTextField();
		textField_5.setColumns(10);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(28)
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addGap(54)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 36,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 36,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 36,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(textField_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
												.addComponent(lblNewLabel, Alignment.LEADING))
										.addGroup(layout.createSequentialGroup().addGap(32)
												.addGroup(layout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblDe, GroupLayout.PREFERRED_SIZE, 36,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 36,
																GroupLayout.PREFERRED_SIZE)))
										.addGap(18)
										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addGroup(Alignment.LEADING,
														layout.createSequentialGroup()
																.addComponent(textField_5, GroupLayout.PREFERRED_SIZE,
																		46, GroupLayout.PREFERRED_SIZE)
																.addGap(16))
												.addComponent(lblHasta, GroupLayout.PREFERRED_SIZE, 62,
														GroupLayout.PREFERRED_SIZE)))
								.addComponent(chckbxNewCheckBox))
						.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
						.addGap(166))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblThumbnails)
										.addComponent(jLabel1))
								.addGap(18)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
										.addComponent(jTextField1, 356, 356, 356))
								.addGap(83)))));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(jTextField1,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(38)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblThumbnails).addComponent(
						textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(btnNewButton).addGroup(layout
						.createSequentialGroup().addComponent(chckbxNewCheckBox).addGap(7)
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout
										.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel).addComponent(lblDe,
														GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(textField_1,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup().addGap(25).addComponent(textField_4,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
								.addGap(282)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(113)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addGap(6).addComponent(textField_2, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(lblHasta, GroupLayout.PREFERRED_SIZE, 19,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(textField_5,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))))));
		getContentPane().setLayout(layout);
		setSize(new Dimension(601, 344));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}