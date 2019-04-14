package periquito;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.DragAndDrop;
import utils.Metodos;
import utils.interfaz;

@SuppressWarnings("serial")

public class Utilidades extends javax.swing.JFrame implements ActionListener, ChangeListener, interfaz {
	static JCheckBox mute = new JCheckBox("");
	private JTextArea imagenes = new JTextArea();
	String comprobacion;
	transient Statement s;
	boolean filtro = false;
	transient ResultSet rs;
	private JTextField prefijoTablas;
	String[] categorias;
	static JComboBox<String> comboBox = new JComboBox<>();

	private JLabel lblNombreDeImgenes = new JLabel("Nombre");
	private JTextField nombre;

	public void mensaje(String mensaje, Boolean error) {
		JLabel alerta = new JLabel(mensaje);
		alerta.setFont(new Font("Arial", Font.BOLD, 18));
		AudioClip clip;
		if (error) {
			clip = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/duck-quack1.wav"));
		} else {
			clip = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/gong1.wav"));
		}
		if (mute.isSelected()) {
			clip.stop();
		} else {
			clip.loop();
		}
		int option;
		if (error) {
			JOptionPane.showMessageDialog(null, alerta, "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, alerta, "Success", JOptionPane.INFORMATION_MESSAGE);
		}
		option = JOptionPane.CLOSED_OPTION;
		if (option == -1) {
			clip.stop();
		}
	}

	public Utilidades() throws IOException {
		setTitle("Periquito v3 Recomponer Imágenes");
		setType(Type.UTILITY);
		initComponents();

		this.setVisible(true);
	}

	@SuppressWarnings("all")
	public void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		mute.setBounds(553, 210, 20, 20);
		mute.addChangeListener(this);

		mute.setFont(new java.awt.Font("Tahoma", 1, 18));
		getContentPane().add(mute);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Utilidades.class.getResource("/imagenes/WAV_00002.png")));
		imagenes.setText("  Arrastra los archivos aqui");
		imagenes.setForeground(Color.DARK_GRAY);
		imagenes.setFont(new Font("Tahoma", Font.BOLD, 24));
		imagenes.setEditable(false);
		imagenes.setBackground(Color.WHITE);
		prefijoTablas = new JTextField();
		prefijoTablas.setHorizontalAlignment(SwingConstants.CENTER);
		prefijoTablas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		prefijoTablas.setText("4images_");
		prefijoTablas.setColumns(10);
		JLabel lblPrefijoDeLas = new JLabel("Prefijo de las tablas");
		lblPrefijoDeLas.setFont(new Font("Tahoma", Font.BOLD, 20));
		JLabel lblCategoraAInsertar = new JLabel("Categoría a insertar");
		lblCategoraAInsertar.setFont(new Font("Tahoma", Font.BOLD, 20));

		lblNombreDeImgenes.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeImgenes.setFont(new Font("Tahoma", Font.BOLD, 20));
		nombre = new JTextField();
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nombre.setColumns(10);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(20)
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addGap(13)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(layout.createSequentialGroup().addComponent(lblPrefijoDeLas).addGap(18)
										.addComponent(prefijoTablas))
								.addComponent(imagenes, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup().addComponent(lblCategoraAInsertar)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(nombre)
												.addComponent(comboBox, 0, 328, Short.MAX_VALUE)))))
						.addComponent(lblNombreDeImgenes, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(29, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(505, Short.MAX_VALUE)
						.addComponent(lblNewLabel).addGap(38)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addGap(23)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(42)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNombreDeImgenes, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(nombre, GroupLayout.PREFERRED_SIZE, 31,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblPrefijoDeLas)
								.addComponent(prefijoTablas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(
						layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCategoraAInsertar, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(imagenes, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel).addGap(50)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(613, 323));
		setLocationRelativeTo(null);
		prefijoTablas.setText(prefijoTablas.getText().trim());

		try {
			if (Metodos.comprobarConexion()) {

				Metodos.ponerCategoriasBd(comboBox);
			}
		} catch (SQLException e3) {
			comboBox.setEnabled(false);
		}

		javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder("Drop 'em");
		new DragAndDrop(imagenes, dragBorder, rootPaneCheckingEnabled, new DragAndDrop.Listener() {
			public void filesDropped(java.io.File[] files) {
				String nombre_input = nombre.getText().trim();
				String prefijo = prefijoTablas.getText().trim();
				if (!prefijo.equals("") && !nombre_input.equals("")) {
					try {
						String imagen;
						int fecha = (int) new Date().getTime();
						if (fecha < 0) {
							fecha *= -1;
						}

						String tabla = prefijo + "images";
						int categoria = comboBox.getSelectedIndex() + 1;
						int id;

						Connection conexion = Metodos.conexionBD();

						s = conexion.createStatement();

						rs = s.executeQuery("select MAX(image_id)+1 FROM " + tabla);
						rs.next();
						id = Integer.parseInt(rs.getString("MAX(image_id)+1"));

						s.close();
						rs.close();

						String thumb;
						conexion = Metodos.conexionBD();

						s = conexion.createStatement();

						FileWriter flS = new FileWriter("Config/SQL.sql");
						BufferedWriter fS = new BufferedWriter(flS);

						String separador = Metodos.saberseparador(Integer.parseInt(MenuPrincipal.getLecturaos()[0]));

						for (int i = 0; i < files.length; i++) {
							imagen = files[i].toString();
							imagen = imagen.substring(imagen.lastIndexOf(separador) + 1, imagen.length());

							thumb = imagen.substring(0, imagen.length() - 4) + "_Thumb.jpg";

							fS.write("INSERT INTO " + tabla + " VALUES(" + id + "," + categoria + ",1,'" + nombre_input
									+ "','',''," + fecha + ",DEFAULT,'" + imagen + "','" + thumb
									+ "', '',DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,0);");
							fS.newLine();

							id++;
						}
						fS.close();
						flS.close();
						conexion = Metodos.conexionBD();

						s = conexion.createStatement();
						InputStream archivo = new FileInputStream("SQL.sql");
						Metodos.executeScript(conexion, archivo);
						Metodos.eliminarFichero("SQL.sql");
						mensaje("Insert recuperados correctamente!", false);
					} catch (Exception e) {
						mensaje("Error al recuperar la BD", true);
					}
				}
			}
		});

	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}

}