package periquito;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("all")

public class Configuracion extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	TextArea textArea = new TextArea();
	static javax.swing.JTextField jTextField1;
	private JTextField textField;
	private JCheckBox chckbxNewCheckBox_1;
	JComboBox comboBox;
	JCheckBox chckbxEliminarImagenesLocales;
	JCheckBox chckbxNewCheckBox;
	String[] configuracion;
	JCheckBox chckbxNewCheckBox_2;

	public Configuracion() throws IOException {

		setResizable(false);
		setAutoRequestFocus(false);
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 16));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Configuracion.class.getResource("/imagenes/config.png")));
		setTitle("Periquito v3 Configuración de subida de imágenes");
		initComponents();

		configuracion = Metodos.leerFicheroArray("Configuracion.txt", 7);

		if (!configuracion[0].isEmpty()) {
			jTextField1.setText(configuracion[0]);
		}

		if (!configuracion[2].isEmpty()) {
			textArea.setText(configuracion[2]);
		}

		if (!configuracion[3].isEmpty()) {
			textField.setText(configuracion[3]);
		}

		if (configuracion[4].equals("1")) {

			chckbxEliminarImagenesLocales.setSelected(true);
		}

		if (configuracion[5].equals("1")) {
			chckbxNewCheckBox_1.setSelected(true);
		}

		if (configuracion[6].equals("1")) {
			chckbxNewCheckBox.setSelected(true);
		}

		if (!configuracion[1].equals("0")) {

			if (Metodos.comprobarConexion(true)) {

				try {
					Metodos.ponerCategoriasBd(comboBox);
				}

				catch (Exception e) {
					//
				}

			}
		}

		else {
			chckbxNewCheckBox_2.setSelected(true);
		}

		this.setVisible(true);
	}

	@SuppressWarnings("all")

	public void guardarDatos(Boolean mensaje) throws IOException {

		FileWriter flS = new FileWriter("Config/Configuracion.txt");

		BufferedWriter fS = new BufferedWriter(flS);

		int idCategoria = 0;

		try {

			fS.write(Metodos.eliminarEspacios(jTextField1.getText(), false));

			fS.newLine();

			if (!chckbxNewCheckBox_2.isSelected()) {

				idCategoria = Integer.parseInt(MenuPrincipal.getIdCategorias().get(comboBox.getSelectedIndex()));

			}

			fS.write(idCategoria);

			fS.newLine();

			fS.write(Metodos.eliminarEspacios(textArea.getText(), false));

			fS.newLine();

			fS.write(Metodos.eliminarEspacios(textField.getText(), false));

			fS.newLine();

			if (chckbxEliminarImagenesLocales.isSelected()) {
				fS.write("1");
			}

			else {
				fS.write("0");
			}

			fS.newLine();

			if (chckbxNewCheckBox_1.isSelected()) {
				fS.write("1");
			}

			else {
				fS.write("0");
			}

			fS.newLine();

			if (chckbxNewCheckBox.isSelected()) {
				fS.write("1");
			}

			else {
				fS.write("0");
			}

			fS.close();

			flS.close();

			dispose();

			MenuPrincipal.setSonido(Metodos.leerFicheroArray("sonido.txt", 2));

		}

		catch (IOException e) {

			if (mensaje) {
				Metodos.mensaje("Error al crear el fichero de configuracion", 1);
			}

		}

		finally {
			fS.close();
			flS.close();
		}

	}

	public void initComponents() throws IOException {

		jTextField1 = new javax.swing.JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.LEFT);
		jTextField1.setToolTipText("");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/nota.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel label = new JLabel("Descripción");
		label.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/name.png")));
		label.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel label_1 = new JLabel("Categoría");
		label_1.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/tag.png")));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel label_2 = new JLabel("Etiquetas");
		label_2.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/tag.png")));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		comboBox = new JComboBox();

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
		textField.setToolTipText("");
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JButton button = new JButton("");

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					guardarDatos(true);

				} catch (IOException e1) {
					//
				}
			}
		});

		button.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/save.png")));

		chckbxEliminarImagenesLocales = new JCheckBox("Eliminar imagenes locales al subir al CMS");
		chckbxEliminarImagenesLocales.setFont(new Font("Tahoma", Font.BOLD, 16));

		chckbxNewCheckBox = new JCheckBox("Backup al terminar la subida");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 16));

		chckbxNewCheckBox_1 = new JCheckBox("Abrir CMS al terminar la subida de imágenes");

		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		chckbxNewCheckBox_2 = new JCheckBox("No usar categoría por defecto");
		chckbxNewCheckBox_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (chckbxNewCheckBox_2.isSelected()) {

					comboBox.setVisible(true);

					try {
						Metodos.ponerCategoriasBd(comboBox);
					} catch (Exception e1) {
						//
					}

				}

				else {
					comboBox.setVisible(false);

				}

			}
		});

		chckbxNewCheckBox_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Eliminar imágenes duplicadas");
		chckbxNewCheckBox_3.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_1 = new JLabel("Poner exif por defecto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_3 = new JLabel(
				"Limitar subida de imágenes si el exif coincide con algún registro en la tabla notas");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNewLabel_4 = new JLabel("Elegir donde se mueven las imágenes por defecto");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JLabel lblNewLabel_2 = new JLabel("Administrador de archivos por defecto para linux");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Apagar PC al terminar");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(23)
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addGap(21)
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout
										.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel).addComponent(label_1,
														GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
										.addGap(70)
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 269,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(chckbxNewCheckBox_4).addComponent(comboBox,
																GroupLayout.PREFERRED_SIZE, 265,
																GroupLayout.PREFERRED_SIZE))))
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(Alignment.LEADING)
														.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 169,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(label, GroupLayout.PREFERRED_SIZE, 169,
																GroupLayout.PREFERRED_SIZE))
												.addGap(36)
												.addGroup(layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(textField, 269, 269, 269).addComponent(scrollPane,
																GroupLayout.PREFERRED_SIZE, 268,
																GroupLayout.PREFERRED_SIZE)))))
								.addComponent(chckbxNewCheckBox_3)
								.addGroup(layout.createSequentialGroup().addGap(54).addComponent(lblNewLabel_4))
								.addGroup(layout.createSequentialGroup().addGap(21)
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_2)
												.addGroup(layout.createSequentialGroup().addComponent(lblNewLabel_1)
														.addGap(25).addComponent(lblNewLabel_3)))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(224).addComponent(button,
										GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(16).addComponent(chckbxNewCheckBox_2))))
						.addComponent(chckbxNewCheckBox).addComponent(chckbxEliminarImagenesLocales)
						.addComponent(chckbxNewCheckBox_1))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(41)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(
						jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(chckbxNewCheckBox_2)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addGap(35).addComponent(label, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(label_2, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(chckbxEliminarImagenesLocales)
						.addComponent(chckbxNewCheckBox_4))
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(chckbxNewCheckBox_1).addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup().addComponent(chckbxNewCheckBox)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(chckbxNewCheckBox_3)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
										.addComponent(lblNewLabel_3))
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_4)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblNewLabel_2).addGap(26))
						.addGroup(layout.createSequentialGroup()
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
								.addGap(61)))));

		scrollPane.setViewportView(textArea);
		getContentPane().setLayout(layout);
		setSize(new Dimension(777, 674));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}