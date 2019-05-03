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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
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
	public static JRadioButton rdbtnComplex = new JRadioButton("Complex");
	JRadioButton rdbtnNewRadioButton = new JRadioButton("Simple");
	public static JTextField textField_3 = new JTextField();;
	private JLabel lblExtensin;
	static boolean error = false;

	public static void setError(boolean error) {
		Descarga.error = error;
	}

	public Descarga() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Descarga.class.getResource("/imagenes/download.png")));
		setTitle("Periquito v3 Config Remoto");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	private void modoPorDefecto() {
		if (!rdbtnNewRadioButton.isSelected() && !rdbtnComplex.isSelected()) {
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
		rdbtnComplex.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				rdbtnNewRadioButton.setSelected(false);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				modoPorDefecto();
			}

		});

		rdbtnComplex.setFont(new Font("Dialog", Font.BOLD, 20));
		rdbtnNewRadioButton.setSelected(true);

		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				rdbtnComplex.setSelected(false);
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

		textField_3.setToolTipText("");
		textField_3.setHorizontalAlignment(SwingConstants.LEFT);
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 24));

		lblExtensin = new JLabel("Extensión");
		lblExtensin.setFont(new Font("Dialog", Font.BOLD, 20));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(26)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(12).addComponent(txtpnElMtodosimple,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblExtensin, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1)
								.addComponent(lblFin, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblThumbnails)
								.addComponent(lblSalto, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(textField, 338, 338, 338)
										.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(textField_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														338, Short.MAX_VALUE)
												.addComponent(textField_1, Alignment.LEADING, 338, 338, Short.MAX_VALUE)
												.addComponent(jTextField1, Alignment.LEADING))
										.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)))
						.addGroup(layout.createSequentialGroup()
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
								.addGap(80).addComponent(rdbtnNewRadioButton).addGap(28).addComponent(rdbtnComplex)))
				.addContainerGap(31, Short.MAX_VALUE)));
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
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFin, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSalto, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(21)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblExtensin, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
				.addGap(20)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(rdbtnNewRadioButton).addComponent(rdbtnComplex))
								.addGap(18))
						.addGroup(
								layout.createSequentialGroup()
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 51,
												GroupLayout.PREFERRED_SIZE)
										.addGap(5)))
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
}
