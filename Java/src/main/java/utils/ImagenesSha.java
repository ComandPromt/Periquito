package utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import periquito.Config2;
import periquito.MenuPrincipal;

@SuppressWarnings("all")

public class ImagenesSha extends javax.swing.JFrame implements ActionListener, ChangeListener {

	static LinkedList<String> imagenesrRepetidas = new LinkedList<>();

	public static LinkedList<String> comprobacionSha;
	int x;

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

		Connection conexion;

		try {

			conexion = Metodos.conexionBD();
			Statement s = conexion.createStatement();

			String sentenciaFinal;

			comprobacionSha = new LinkedList<>();

			for (int x = 0; x < ComprobarSha.getLectura().size(); x++) {

				if (ComprobarSha.getComboBox().getSelectedIndex() == 0) {
					sentenciaFinal = "WHERE sha256='" + ComprobarSha.getShaimages().get(x) + "'";

				} else {
					sentenciaFinal = "WHERE image_media_file='" + ComprobarSha.getLectura().get(x) + "'";

				}

				ResultSet rs = s.executeQuery(
						"select COUNT(image_id) from " + MenuPrincipal.getLecturabd()[3] + "images " + sentenciaFinal);
				rs.next();

				int recuento = Integer.parseInt(rs.getString("count(image_id)"));

				if (recuento > 0) {
					comprobacionSha.add("SI");
					imagenesrRepetidas.add(ComprobarSha.getLectura().get(x));

				}

				else {
					comprobacionSha.add("NO");
				}

			}

			// MyTableModel.longitud = comprobacionSha.size();

			JFrame frmShaImages = new JFrame();
			frmShaImages.setResizable(false);
			frmShaImages.getContentPane().setEnabled(false);
			frmShaImages.setAutoRequestFocus(false);
			frmShaImages.setIconImage(
					Toolkit.getDefaultToolkit().getImage(ImagenesSha.class.getResource("/imagenes/db.png")));
			frmShaImages.setTitle("Periquito v3 SHA Images");
			frmShaImages.getContentPane().setLayout(new BorderLayout());

			MyTableModel model = new MyTableModel();

			model.addColumn("Resultado");

			model.addColumn("Imagen");

			JTable table = new JTable(model);
			table.setEnabled(false);
			table.setColumnSelectionAllowed(true);
			table.setCellSelectionEnabled(true);
			table.setFont(new Font("Tahoma", Font.BOLD, 12));
			table.setPreferredScrollableViewportSize(new Dimension(500, 800));
			table.setFillsViewportHeight(true);
			table.setRowHeight(60);
			table.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());

			JScrollPane js = new JScrollPane(table);
			js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			js.setVisible(true);
			frmShaImages.getContentPane().add(BorderLayout.CENTER, js);

			JButton btnNewButton = new JButton("Eliminar imagenes que están en la BD ");
			btnNewButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {

					Metodos.eliminarArchivos(imagenesrRepetidas,
							ComprobarSha.getRutas().get(0).substring(0,
									ComprobarSha.getRutas().get(0).lastIndexOf(MenuPrincipal.getSeparador()))
									+ MenuPrincipal.getSeparador());

				}
			});
			btnNewButton.setIcon(new ImageIcon(ImagenesSha.class.getResource("/imagenes/delete.png")));
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
			frmShaImages.getContentPane().add(btnNewButton, BorderLayout.NORTH);

			JButton btnNewButton_1 = new JButton(" Abrir carpeta");
			btnNewButton_1.setIcon(new ImageIcon(ImagenesSha.class.getResource("/imagenes/folder.png")));
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {

					try {
						Metodos.abrirCarpeta(ComprobarSha.getRutas().get(0).substring(0,
								ComprobarSha.getRutas().get(0).lastIndexOf(MenuPrincipal.getSeparador()))
								+ MenuPrincipal.getSeparador());
					} catch (IOException e1) {
						//
					}

				}
			});

			frmShaImages.getContentPane().add(btnNewButton_1, BorderLayout.SOUTH);
			frmShaImages.setSize(744, 400);

			frmShaImages.setVisible(true);

		}

		catch (Exception e) {

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