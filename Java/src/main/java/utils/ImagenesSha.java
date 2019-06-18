package utils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import periquito.Config2;
import periquito.MenuPrincipal;

@SuppressWarnings("all")

public class ImagenesSha extends javax.swing.JFrame implements ActionListener, ChangeListener {

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

			} catch (ArrayIndexOutOfBoundsException e) {

			}

		}
	}

	public ImagenesSha() throws IOException {
		setAlwaysOnTop(true);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		setSize(new Dimension(496, 294));
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Config2.class.getResource("/imagenes/config.png")));
		setTitle("Periquito v3 SHA Images Checker");
		setType(Type.UTILITY);
		DefaultTableModel myTable = new DefaultTableModel();

		JTable table = new JTable(myTable);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setPreferredScrollableViewportSize(new Dimension(400, 300));
		table.setFillsViewportHeight(true);

		myTable.addColumn("Imagen");

		myTable.addColumn("¿Está en la BD?");

		Connection conexion;

		try {
			
			conexion = Metodos.conexionBD();
			Statement s = conexion.createStatement();

			String resultadosha;

			String sentenciaFinal;
			
			for (int x = 0; x < ComprobarSha.getLectura().size(); x++) {

				if(ComprobarSha.getComboBox().getSelectedIndex()==0 ) {
					sentenciaFinal="WHERE sha256='" + ComprobarSha.getShaimages().get(x) + "'";
					
				}
				else {
					sentenciaFinal="WHERE image_media_file='" + ComprobarSha.getLectura().get(x) + "'";
						
				}
				
				ResultSet rs = s.executeQuery("select COUNT(image_id) from " + MenuPrincipal.getLecturabd()[3]
						+ "images "+sentenciaFinal);
				rs.next();
				
				int recuento = Integer.parseInt(rs.getString("count(image_id)"));

				if (recuento > 0) {
					resultadosha = "SI";
				} else {
					resultadosha = "NO";
				}
				
				myTable.addRow(new Object[] { ComprobarSha.getLectura().get(x), resultadosha });

			}
			
		} catch (SQLException e) {
			//
		}

		JScrollPane js = new JScrollPane(table);
		js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		js.setVisible(true);
		getContentPane().add(js);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}