package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("serial")
public class Descarga extends JFrame implements ActionListener, ChangeListener, MyInterface {
	javax.swing.JLabel jLabel1;
	static javax.swing.JTextField jTextField1;
	private JTextField textField;
	JLabel lblThumbnails;
	private JTextField textField1;
	private JTextField textField2;
	private static JRadioButton botonRadio1 = new JRadioButton("Complex");
	JRadioButton rdbtnNewRadioButton = new JRadioButton("Simple");
	public static final JTextField textField3 = new JTextField();

	static boolean error = false;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenu mnNewMenu_1;
	private JRadioButtonMenuItem rdbtnmntmNewRadioItem;
	private JRadioButtonMenuItem rdbtnmntmNewRadioItem_1;
	private JMenuItem mntmNewMenuItem_1;
	private JMenu mnNewMenu_2;
	private JCheckBoxMenuItem chckbxmntmNewCheckItem;
	private JCheckBoxMenuItem chckbxmntmNewCheckItem_1;
	private JCheckBoxMenuItem chckbxmntmNewCheckItem_2;

	public static void setError(boolean error) {
		Descarga.error = error;
	}

	public Descarga() throws IOException {
		getContentPane().setFont(new Font("Dialog", Font.BOLD, 20));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Descarga.class.getResource("/imagenes/download.png")));
		setTitle("Periquito v3 Config Remoto");

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);

		mntmNewMenuItem = new JMenuItem("DescargaThumb");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new DescargaThumb().setVisible(true);
				} catch (IOException e1) {
					//
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		mnNewMenu_1 = new JMenu("Modo");
		mnNewMenu.add(mnNewMenu_1);

		rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("Simple");
		mnNewMenu_1.add(rdbtnmntmNewRadioItem);

		rdbtnmntmNewRadioItem_1 = new JRadioButtonMenuItem("Complejo");
		mnNewMenu_1.add(rdbtnmntmNewRadioItem_1);

		mntmNewMenuItem_1 = new JMenuItem("Importar desde archivo");
		mnNewMenu.add(mntmNewMenuItem_1);

		mnNewMenu_2 = new JMenu("Al terminar");
		mnNewMenu.add(mnNewMenu_2);

		chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Mover imagenes");
		mnNewMenu_2.add(chckbxmntmNewCheckItem);

		chckbxmntmNewCheckItem_1 = new JCheckBoxMenuItem("Apagar equipo");
		mnNewMenu_2.add(chckbxmntmNewCheckItem_1);

		chckbxmntmNewCheckItem_2 = new JCheckBoxMenuItem("Cerrar aplicación");
		mnNewMenu_2.add(chckbxmntmNewCheckItem_2);
		initComponents();
		this.setVisible(true);
	}

	private void modoPorDefecto() {
		if (!rdbtnNewRadioButton.isSelected() && !getBotonRadio1().isSelected()) {
			rdbtnNewRadioButton.setSelected(true);
		}
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

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Descarga.class.getResource("/imagenes/start.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!jTextField1.getText().trim().isEmpty() && !textField.getText().trim().isEmpty()
						&& !textField1.getText().trim().isEmpty() && !textField2.getText().trim().isEmpty()) {

					try {

						int inicio = -1;
						int fin = -1;
						int salto = -1;

						if (Integer.parseInt(textField.getText().trim()) >= 0) {
							inicio = Integer.parseInt(textField.getText().trim());
						}

						if (Integer.parseInt(textField1.getText().trim()) >= 0) {
							fin = Integer.parseInt(textField1.getText().trim());
						}

						if (Integer.parseInt(textField2.getText().trim()) >= 0) {
							salto = Integer.parseInt(textField2.getText().trim());
						}

						if (fin < inicio) {
							Metodos.mensaje("El fin no puede ser menor que el inicio", 1);
						}

						else {

							if (inicio >= 0 && fin >= 0 && salto >= 0) {

								String unidadtiempo = "segundos";

								int tiempoEstimado = fin - inicio;

								if (fin - inicio == 0) {
									tiempoEstimado = 5;
								}

								else {
									tiempoEstimado = ((fin - inicio) + 1) * 5;
								}

								if (tiempoEstimado == 60) {
									tiempoEstimado = 1;
									unidadtiempo = "minuto";
								} else {
									tiempoEstimado /= 60;
									unidadtiempo = "minutos";
								}

								Metodos.mensaje(
										"El tiempo estimado de descarga es de: " + tiempoEstimado + " " + unidadtiempo,
										2);

								Metodos.descargar(jTextField1.getText().trim(), inicio, fin, salto);

								if (!error) {
									Metodos.abrirCarpeta("Config" + MenuPrincipal.getSeparador() + "Downloads");
								}
							}

							else {
								Metodos.mensaje("Introduce números en inicio,fin y salto", 3);
							}
						}
					} catch (NumberFormatException e) {
						Metodos.mensaje("Por favor, inserta números en los campos inicio,fin y salto", 3);
					} catch (Exception e) {
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

		textField1 = new JTextField();
		textField1.setToolTipText("");
		textField1.setHorizontalAlignment(SwingConstants.LEFT);
		textField1.setFont(new Font("Dialog", Font.PLAIN, 24));

		JLabel lblFin = new JLabel("Fin");
		lblFin.setFont(new Font("Dialog", Font.BOLD, 20));

		textField2 = new JTextField();
		textField2.setToolTipText("");
		textField2.setHorizontalAlignment(SwingConstants.LEFT);
		textField2.setFont(new Font("Dialog", Font.PLAIN, 24));
		JLabel lblSalto;
		lblSalto = new JLabel("Salto");
		lblSalto.setFont(new Font("Dialog", Font.BOLD, 20));
		getBotonRadio1().setFont(new Font("Dialog", Font.BOLD, 20));

		getBotonRadio1().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				rdbtnNewRadioButton.setSelected(false);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				modoPorDefecto();
			}

		});

		rdbtnNewRadioButton.setSelected(true);

		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				getBotonRadio1().setSelected(false);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				modoPorDefecto();
			}
		});
		rdbtnNewRadioButton.setFont(new Font("Dialog", Font.BOLD, 20));

		JTextPane txtpnElMtodosimple = new JTextPane();
		txtpnElMtodosimple.setFont(new Font("Dialog", Font.BOLD, 14));
		txtpnElMtodosimple.setText(
				"El método \"Simple\" es cuando damos la URL de la imagen. \n\nEl método complejo se usa para cuando se da una URL\n\nque genera una imagen (elemento img de HTML)");

		textField3.setToolTipText("");
		textField3.setHorizontalAlignment(SwingConstants.LEFT);
		textField3.setFont(new Font("Dialog", Font.PLAIN, 24));
		JLabel lblExtension;
		lblExtension = new JLabel("Extensión");
		lblExtension.setFont(new Font("Dialog", Font.BOLD, 20));

		JButton btnNewButton_1 = new JButton("Limpiar campos");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(26)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addGap(12).addComponent(
														txtpnElMtodosimple, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(layout.createSequentialGroup()
														.addGroup(layout.createParallelGroup(Alignment.LEADING)
																.addComponent(lblExtension, GroupLayout.PREFERRED_SIZE,
																		129, GroupLayout.PREFERRED_SIZE)
																.addComponent(jLabel1)
																.addComponent(lblFin, GroupLayout.PREFERRED_SIZE, 61,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(lblThumbnails).addComponent(lblSalto,
																		GroupLayout.PREFERRED_SIZE, 61,
																		GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
																.addComponent(textField, 338, 338, Short.MAX_VALUE)
																.addComponent(textField3, GroupLayout.DEFAULT_SIZE, 338,
																		Short.MAX_VALUE)
																.addComponent(textField1, 338, 338, Short.MAX_VALUE)
																.addComponent(jTextField1).addComponent(textField2))))
										.addContainerGap(35, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 106,
												GroupLayout.PREFERRED_SIZE)
										.addGap(80).addComponent(rdbtnNewRadioButton)
										.addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
										.addComponent(btnNewButton_1).addGap(20)))));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(jTextField1,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblThumbnails))
				.addGap(29)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFin, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSalto, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(21)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblExtension, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
				.addGap(20)
				.addGroup(
						layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 51,
												GroupLayout.PREFERRED_SIZE)
										.addGap(5))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addComponent(btnNewButton_1).addComponent(rdbtnNewRadioButton))
										.addGap(18)))
				.addGap(18).addComponent(txtpnElMtodosimple, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(423)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(538, 541));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}

	public static JRadioButton getBotonRadio1() {
		return botonRadio1;
	}
}
