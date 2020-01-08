package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.TooManyListenersException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import periquito.MenuPrincipal;
import periquito.ModificarDatos;

@SuppressWarnings("serial")

public class ComprobarSha extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {

	private JTextArea imagenes = new JTextArea();
	transient Statement s;
	boolean filtro = false;
	transient ResultSet rs;
	String[] categorias;
	static List<String> lectura = new LinkedList<>();
	static List<String> rutas = new LinkedList<>();
	static List<String> shaimages = new LinkedList<>();
	static JComboBox<?> comboBox;
	static int tipo = 0;

	public static int getTipo() {
		return tipo;
	}

	public static void setTipo(int tipo) {
		ComprobarSha.tipo = tipo;
	}

	public static List<String> getShaimages() {
		return shaimages;
	}

	public static void setShaimages(List<String> shaimages) {
		ComprobarSha.shaimages = shaimages;
	}

	public static List<String> getRutas() {
		return rutas;
	}

	public static List<String> getLectura() {
		return lectura;
	}

	private void comprobarSha(java.io.File[] files) {

		try {

			rutas.clear();

			lectura.clear();

			shaimages.clear();

			String nombreArchivo;
			String ruta;
			String extension;

			for (int x = 0; x < files.length; x++) {

				ruta = files[x].getCanonicalPath();

				extension = Metodos.extraerExtension(ruta);

				if (extension.equals("jpg") || extension.equals("gif") || extension.equals("png")) {

					nombreArchivo = ruta.substring(ruta.lastIndexOf(MenuPrincipal.getSeparador()) + 1, ruta.length());

					rutas.add(ruta);

					lectura.add(nombreArchivo);

					shaimages.add(Metodos.getSHA256Checksum(files[x].getCanonicalPath()));

				}

			}

			Metodos.renombrarArchivos(ComprobarSha.getLectura(), ComprobarSha.getRutas().get(0).substring(0,
					ComprobarSha.getRutas().get(0).lastIndexOf(MenuPrincipal.getSeparador()) + 1));

			if (!lectura.isEmpty()) {

				dispose();

				switch (tipo) {

				case 0:
					new ImagenesSha();

					break;

				case 1:

					new ModificarDatos().setVisible(true);

					break;

				default:
					break;

				}

			}

		}

		catch (Exception e) {
			//
		}

	}

	public ComprobarSha() throws IOException {

		rutas.clear();

		lectura.clear();

		shaimages.clear();

		setIconImage(Toolkit.getDefaultToolkit().getImage(ComprobarSha.class.getResource("/imagenes/db.png")));

		setTitle("Periquito v3 Comprobador SHA");

		initComponents();

		this.setVisible(true);

	}

	@SuppressWarnings("all")

	public void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);
		imagenes.setText("  Arrastra los archivos aqui");
		imagenes.setForeground(Color.DARK_GRAY);
		imagenes.setFont(new Font("Tahoma", Font.BOLD, 24));
		imagenes.setEditable(false);
		imagenes.setBackground(Color.WHITE);

		JLabel lblNewLabel = new JLabel("");

		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				File[] files = Metodos.seleccionar(2, "Imagen & Video",
						"Elije un archivo de imagen o video para mover");

				if (files != null) {
					comprobarSha(files);
				}

			}
		});

		lblNewLabel.setIcon(new ImageIcon(ComprobarSha.class.getResource("/imagenes/import.png")));

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 22));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "SHA", "Nombre" }));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(21).addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(imagenes, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(25, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup().addGap(22)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addComponent(imagenes, GroupLayout.PREFERRED_SIZE, 41,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(16, Short.MAX_VALUE)));

		getContentPane().setLayout(layout);
		setSize(new Dimension(641, 134));
		setLocationRelativeTo(null);

		javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder("Drop 'em");

		try {

			new DragAndDrop(imagenes, dragBorder, rootPaneCheckingEnabled, new DragAndDrop.Listener() {

				public void filesDropped(java.io.File[] files) {

					comprobarSha(files);
				}

			});

		}

		catch (TooManyListenersException e1) {
			//
		}

	}

	public static JComboBox<?> getComboBox() {
		return comboBox;
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}

}