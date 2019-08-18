package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

@SuppressWarnings("all")
public class LeerArchivo extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	static javax.swing.JTextField jTextField1;
	private JTextField textField;

	public LeerArchivo() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Config.class.getResource("/imagenes/config.png")));
		setTitle("Periquito v3");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	public void initComponents() throws IOException {

		jTextField1 = new JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.CENTER);
		jTextField1.setToolTipText("");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (!textField.getText().equals("") && !jTextField1.getText().equals("")) {

					File[] files = Metodos.seleccionar(3, "Archivos txt", "Por favor, eliga un archivo txt");

					try {

						if (!files[0].getCanonicalPath().isEmpty()) {

							Metodos.muestraContenido(files[0].getCanonicalPath(), jTextField1.getText(),
									textField.getText());

							Metodos.mensaje("Notas insertadas correctamente", 2);

						}
					} catch (Exception e3) {
						//
					}

				} else {
					Metodos.mensaje("Por favor, rellena todos los campos", 3);
				}

			}
		});

		btnNewButton.setIcon(new ImageIcon(Config.class.getResource("/imagenes/abrir.png")));

		textField = new JTextField();
		textField.setToolTipText("");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel tipo = new JLabel("Tipo");
		tipo.setFont(new Font("Tahoma", Font.BOLD, 24));

		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 24));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(20).addComponent(lblDescripcin))
								.addGroup(layout.createSequentialGroup().addGap(54).addComponent(tipo)))
						.addGap(32)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 236,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 48,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(83)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(19)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 38,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(tipo)))
						.addGap(28)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDescripcin, GroupLayout.PREFERRED_SIZE, 29,
										GroupLayout.PREFERRED_SIZE))
						.addGap(48)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(516, 269));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
	}

	public void stateChanged(ChangeEvent e) {
	}
}