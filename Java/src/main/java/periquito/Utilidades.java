package periquito;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.TooManyListenersException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.DragAndDrop;
import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("serial")

public class Utilidades extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	private JTextArea imagenes = new JTextArea();
	String comprobacion;
	transient Statement s;
	boolean filtro = false;
	transient ResultSet rs;
	String[] categorias;
	static JComboBox<String> comboBox = new JComboBox<>();

	private JLabel lblNombreDeImgenes = new JLabel("Nombre");
	private JTextField nombre;
	private final JLabel lblComprobarGifAnimado = new JLabel("Comprobar gif animado");

	public Utilidades() {
		lblComprobarGifAnimado.setFont(new Font("Tahoma", Font.BOLD, 16));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Utilidades.class.getResource("/imagenes/db.png")));

		try {

			if (Metodos.comprobarConexion(true)) {

				Metodos.ponerCategoriasBd(comboBox);
			}

			setTitle("Periquito v3 Recomponer Imágenes");
			initComponents();

			this.setVisible(true);

		}

		catch (SQLException | IOException e3) {
			this.dispose();
		}

	}

	@SuppressWarnings("all")
	public void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);
		imagenes.setText("Arrastra los archivos aqui");
		imagenes.setForeground(Color.DARK_GRAY);
		imagenes.setFont(new Font("Tahoma", Font.BOLD, 24));
		imagenes.setEditable(false);
		imagenes.setBackground(Color.WHITE);
		JLabel lblCategoraAInsertar = new JLabel("");
		lblCategoraAInsertar.setIcon(new ImageIcon(Utilidades.class.getResource("/imagenes/tag.png")));
		lblCategoraAInsertar.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNombreDeImgenes.setIcon(new ImageIcon(Utilidades.class.getResource("/imagenes/name.png")));

		lblNombreDeImgenes.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeImgenes.setFont(new Font("Tahoma", Font.BOLD, 20));
		nombre = new JTextField();
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nombre.setColumns(10);

		JLabel lblNewLabel = new JLabel("insertar en metadatos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombreDeImgenes, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addGap(29).addComponent(lblCategoraAInsertar,
								GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(lblComprobarGifAnimado,
								GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(nombre).addComponent(imagenes, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(40, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(23)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblNombreDeImgenes)
						.addComponent(nombre, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(18).addComponent(lblCategoraAInsertar))
						.addGroup(layout.createSequentialGroup().addGap(33).addComponent(comboBox,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGap(18).addGroup(
						layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(imagenes, GroupLayout.PREFERRED_SIZE, 50,
												GroupLayout.PREFERRED_SIZE)
										.addGap(26))
								.addGroup(layout.createSequentialGroup().addComponent(lblNewLabel).addGap(18)
										.addComponent(lblComprobarGifAnimado, GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap()))));
		getContentPane().setLayout(layout);
		setSize(new Dimension(578, 289));
		setLocationRelativeTo(null);

		javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder("Drop 'em");

		try {

			new DragAndDrop(imagenes, dragBorder, rootPaneCheckingEnabled, new DragAndDrop.Listener() {

				public void filesDropped(java.io.File[] files) {

					String nombre_input = nombre.getText().trim();

					if (!MenuPrincipal.getLecturabd()[1].equals("") && !nombre_input.equals("")) {

						try {

							String imagen;
							Date fecha = new Date();
							String strDateFormat = "y-MM-dd";
							SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);

							String tabla = MenuPrincipal.getLecturabd()[3] + "images";

							int categoria = Integer
									.parseInt(MenuPrincipal.getIdCategorias().get(comboBox.getSelectedIndex()));

							int id;

							Connection conexion = Metodos.conexionBD();

							s = conexion.createStatement();

							rs = s.executeQuery("select MAX(image_id)+1 FROM " + tabla);
							rs.next();

							if (rs.getString("MAX(image_id)+1") == null) {
								id = 1;
							}

							else {
								id = Integer.parseInt(rs.getString("MAX(image_id)+1"));
							}

							s.close();
							rs.close();

							conexion = Metodos.conexionBD();

							s = conexion.createStatement();

							FileWriter flS = new FileWriter("Config/SQL.sql");
							BufferedWriter fS = new BufferedWriter(flS);

							String separador = MenuPrincipal.getSeparador();

							int i;

							LinkedList<String> imagenes = new LinkedList();

							String carpeta = files[0].toString().substring(0,
									files[0].toString().lastIndexOf(MenuPrincipal.getSeparador()) + 1);

							for (i = 0; i < files.length; i++) {

								imagenes.add(files[i].toString().substring(
										files[i].toString().lastIndexOf(MenuPrincipal.getSeparador()),
										files[i].toString().length()));
							}

//							String parametros = Metodos.obtenerParametros(imagenes);
//
//							JSONObject json;
//
//							json = Metodos.apiImagenes(parametros);
//
//							JSONArray imagenesBD = json.getJSONArray("imagenes_bd");

//							int longitud = imagenesBD.length();
							int longitud = 29063;
							if (longitud > 0) {

								/////////////////////////////////////////////////

								// Recomponer metadatos
								String archivo = "";
								for (i = 0; i < longitud; i++) {

									// Metodos.renombrar(files[i].toString(), carpeta + imagenesBD.get(i));

									// Comprobar si la imagen ya está subida y en la tabla metadatos

//									fS.write("INSERT INTO " + tabla + " VALUES('" + id + "','" + categoria + "','1','"
//											+ nombre_input + "',DEFAULT,DEFAULT,'" + objSDF.format(fecha)
//											+ "',DEFAULT,'" + imagenesBD.get(i)
//											+ "',DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,'"
//											+ Metodos.getSHA256Checksum(files[i].toString()) + "',DEFAULT,DEFAULT);");
//									fS.newLine();

									archivo = Metodos.extraerNombreFile(files[i].toString());

									fS.write("UPDATE " + tabla + " SET sha256='"
											+ Metodos.getSHA256Checksum(files[i].toString())
											+ "' WHERE image_media_file='" + archivo + "';");
									fS.newLine();

									id++;

								}

							}

							fS.close();

							flS.close();

							conexion = Metodos.conexionBD();

							s = conexion.createStatement();

							InputStream archivo = new FileInputStream("Config/SQL.sql");

							// Metodos.executeScript(conexion, archivo);

							// Metodos.eliminarFichero("Config/SQL.sql");

							if (i == 1) {
								Metodos.mensaje("Se ha recuperado 1 registro", 2);
							}

							else {
								Metodos.mensaje("Se han recuperado " + i + " registros", 2);
							}

						}

						catch (Exception e) {
							e.printStackTrace();
							// Metodos.mensaje("Error al recuperar la BD", 1);
						}
					}

					else {
						Metodos.mensaje("Por favor, rellene el nombre", 3);
					}

				}

			});

		}

		catch (TooManyListenersException e) {
			Metodos.mensaje("Error al recuperar la BD", 1);
		}

	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}