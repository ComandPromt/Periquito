package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TooManyListenersException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import periquito.MenuPrincipal;

@SuppressWarnings("serial")

public class ComprobarSha extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	private JTextArea imagenes = new JTextArea();
	String comprobacion;
	transient Statement s;
	boolean filtro = false;
	transient ResultSet rs;
	String[] categorias;

	public ComprobarSha() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(ComprobarSha.class.getResource("/imagenes/db.png")));

		setTitle("Periquito v3 Recomponer Imágenes");
		setType(Type.UTILITY);
		initComponents();

		this.setVisible(true);

	}

	@SuppressWarnings("all")
	public void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);
		imagenes.setText("  Arrastra los archivos aqui");
		imagenes.setForeground(Color.DARK_GRAY);
		imagenes.setFont(new Font("Tahoma", Font.BOLD, 24));
		imagenes.setEditable(false);
		imagenes.setBackground(Color.WHITE);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addGap(33)
						.addComponent(imagenes, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addGap(150)
						.addComponent(imagenes, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addGap(113)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(613, 265));
		setLocationRelativeTo(null);

		javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder("Drop 'em");
		try {
			new DragAndDrop(imagenes, dragBorder, rootPaneCheckingEnabled, new DragAndDrop.Listener() {
				public void filesDropped(java.io.File[] files) {
					System.out.println(files + "\n");
					// String nombre_input = nombre.getText().trim();
					// String prefijo = prefijoTablas.getText().trim();

					if (true) {

						try {
							System.out.println("select image_media_file,cat_id" + " FROM "
									+ MenuPrincipal.getLecturabd()[3] + "images" + " WHERE sha256='"
									+ Metodos.getSHA256Checksum(files[0].toString()) + "'");
							String thumb;
							Connection conexion = Metodos.conexionBD();

							s = conexion.createStatement();
							rs = s.executeQuery("select image_media_file,cat_id" + " FROM "
									+ MenuPrincipal.getLecturabd()[3] + "images" + " WHERE sha256='"
									+ Metodos.getSHA256Checksum(files[0].toString()) + "'");
							rs.next();

							if (rs.getString("image_media_file") == null) {
								System.out.println(rs.getString("image_media_file"));
							}

							s.close();
							rs.close();

						} catch (SQLException e) {
							Metodos.mensaje("La tabla está vacía", 2);

						} catch (Exception e) {
							//
						}

					}
				}
			});
		} catch (TooManyListenersException e) {
			//
		}

	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}

}