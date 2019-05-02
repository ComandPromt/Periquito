package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

@SuppressWarnings("serial")

public class Descarga extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	javax.swing.JLabel jLabel1;
	static javax.swing.JTextField jTextField1;
	private JTextField textField;
	JLabel lblThumbnails;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblSalto;

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

				textField.setText("1");
			} catch (ArrayIndexOutOfBoundsException e) {

			}

		}
	}

	public Descarga() throws IOException {
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
		jLabel1.setText("URL");
		jLabel1.setIcon(new ImageIcon(Descarga.class.getResource("/imagenes/remote.png")));
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
		btnNewButton.setIcon(new ImageIcon(Descarga.class.getResource("/imagenes/start.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!jTextField1.getText().trim().isEmpty() && !textField.getText().trim().isEmpty()
						&& !textField_1.getText().trim().isEmpty() && !textField_2.getText().trim().isEmpty()) {

					try {

						int inicio = -1;
						int fin = -1;
						int salto = -1;

						if (Integer.parseInt(textField.getText().trim()) >= 0) {
							inicio = Integer.parseInt(textField.getText().trim());
						}

						if (Integer.parseInt(textField_1.getText().trim()) >= 0) {
							fin = Integer.parseInt(textField_1.getText().trim());
						}

						if (Integer.parseInt(textField_2.getText().trim()) >= 0) {
							salto = Integer.parseInt(textField_2.getText().trim());
						}

						if (fin < inicio) {
							Metodos.mensaje("El fin no puede ser menor que el inicio", 1);
						}

						else {

							if (inicio >= 0 && fin >= 0 && salto >= 0) {
								Metodos.descargar(jTextField1.getText().trim(), inicio, fin, salto);
								Metodos.abrirCarpeta("Downloads");
							}

							else {
								Metodos.mensaje("Introduce números en inicio,fin y salto", 3);
							}
						}
					} catch (NumberFormatException e) {
						Metodos.mensaje("Por favor, inserta números en los campos inicio,fin y salto", 3);
					} catch (IOException e) {
						//
					}
				} else {
					Metodos.mensaje("Por favor, rellena todos los campos", 2);
				}
			}
		});

		lblThumbnails = new JLabel("Inicio");
		lblThumbnails.setIcon(null);
		lblThumbnails.setFont(new Font("Tahoma", Font.BOLD, 20));

		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 24));

		JLabel lblFin = new JLabel("Fin");
		lblFin.setFont(new Font("Dialog", Font.BOLD, 20));

		textField_2 = new JTextField();
		textField_2.setToolTipText("");
		textField_2.setHorizontalAlignment(SwingConstants.LEFT);
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 24));

		lblSalto = new JLabel("Salto");
		lblSalto.setFont(new Font("Dialog", Font.BOLD, 20));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup().addGroup(layout
										.createParallelGroup(Alignment.LEADING).addComponent(jLabel1)
										.addGroup(layout.createSequentialGroup().addGap(11)
												.addGroup(layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(lblFin, GroupLayout.PREFERRED_SIZE, 61,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblThumbnails).addComponent(lblSalto,
																GroupLayout.PREFERRED_SIZE, 61,
																GroupLayout.PREFERRED_SIZE))))
										.addGap(83)
										.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(textField_1).addComponent(textField)
												.addComponent(jTextField1, 338, 338, Short.MAX_VALUE)))))
						.addGroup(layout.createSequentialGroup().addGap(304).addComponent(btnNewButton,
								GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(31, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(jTextField1,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(38)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblThumbnails))
				.addGap(45)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFin, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(33)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSalto, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
				.addGap(428)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(578, 433));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
