package utils;

import java.awt.BorderLayout;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import periquito.Config2;
import periquito.MenuPrincipal;

@SuppressWarnings("all")

public class ImagenesSha extends javax.swing.JFrame implements ActionListener, ChangeListener {

	static LinkedList<String> imagenesRepetidas = new LinkedList<>();

	JMenuItem mntmNewMenuItem_1;

	private LinkedList<String> imagenes;

	public static LinkedList<String> comprobacionSha;
	int x;

	private JFrame getFrame() {
		return this;
	}

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

		imagenes = new LinkedList<>();

		Connection conexion;

		try {

			JFrame frmShaImages = new JFrame();
			frmShaImages.setResizable(false);
			frmShaImages.getContentPane().setEnabled(false);
			frmShaImages.setAutoRequestFocus(false);
			frmShaImages.setIconImage(
					Toolkit.getDefaultToolkit().getImage(ImagenesSha.class.getResource("/imagenes/db.png")));
			frmShaImages.setTitle("Periquito v3 SHA Images");
			frmShaImages.getContentPane().setLayout(new BorderLayout());

			DefaultTableModel model = new DefaultTableModel();

			model.addColumn("Imagen");

			model.addColumn("¿Está en la base de datos?");

			JTable table = new JTable(model);
			table.setEnabled(false);
			table.setColumnSelectionAllowed(true);
			table.setCellSelectionEnabled(true);
			table.setFont(new Font("Tahoma", Font.BOLD, 12));
			table.setPreferredScrollableViewportSize(new Dimension(500, 800));
			table.setFillsViewportHeight(true);
			table.setRowHeight(60);

			conexion = Metodos.conexionBD();
			Statement s = conexion.createStatement();

			String sentenciaFinal, comprobacionSha;

			String separador = "                                          ";

			String extension;

			for (int x = 0; x < ComprobarSha.getLectura().size(); x++) {

				extension = Metodos.extraerExtension(ComprobarSha.getLectura().get(x));

				if (extension.equals("jpg") || extension.equals("png") || extension.equals("gif")) {

					if (ComprobarSha.getComboBox().getSelectedIndex() == 0) {
						sentenciaFinal = "WHERE sha256='" + ComprobarSha.getShaimages().get(x) + "'";

					} else {
						sentenciaFinal = "WHERE image_media_file='" + ComprobarSha.getLectura().get(x) + "'";

					}

					ResultSet rs = s.executeQuery("select COUNT(image_id),image_id from "
							+ MenuPrincipal.getLecturabd()[3] + "images " + sentenciaFinal);
					rs.next();

					int recuento = Integer.parseInt(rs.getString("count(image_id)"));

					if (recuento > 0) {

						comprobacionSha = separador + "SI , su image_id es: " + rs.getString("image_id");

						imagenesRepetidas.add(ComprobarSha.getLectura().get(x));
					}

					else {

						comprobacionSha = separador + "NO";

						imagenes.add(ComprobarSha.getRutas().get(0).substring(0,
								ComprobarSha.getRutas().get(0).lastIndexOf(MenuPrincipal.getSeparador()))
								+ MenuPrincipal.getSeparador() + ComprobarSha.getLectura().get(x));
					}

					model.addRow(new Object[] { " " + ComprobarSha.getLectura().get(x), comprobacionSha });
				}
			}

			JScrollPane js = new JScrollPane(table);
			js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			js.setVisible(true);

			frmShaImages.getContentPane().add(BorderLayout.CENTER, js);
			frmShaImages.setSize(744, 400);

			JMenuBar menuBar = new JMenuBar();
			frmShaImages.setJMenuBar(menuBar);

			JMenu mnNewMenu = new JMenu("Acciones  ");
			mnNewMenu.setForeground(Color.BLACK);
			mnNewMenu.setBackground(Color.WHITE);
			mnNewMenu.setIcon(new ImageIcon(ImagenesSha.class.getResource("/imagenes/config.png")));
			mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 18));
			menuBar.add(mnNewMenu);

			JMenuItem mntmNewMenuItem = new JMenuItem("Eliminar imágenes duplicadas");
			mntmNewMenuItem.setIcon(new ImageIcon(ImagenesSha.class.getResource("/imagenes/delete.png")));
			mntmNewMenuItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {

					Metodos.eliminarArchivos(imagenesRepetidas,
							ComprobarSha.getRutas().get(0).substring(0,
									ComprobarSha.getRutas().get(0).lastIndexOf(MenuPrincipal.getSeparador()))
									+ MenuPrincipal.getSeparador());

					if (imagenes.size() == 0) {
						mntmNewMenuItem_1.setEnabled(false);
					}

					Metodos.mensaje("Se han borrado correctamente todas las imágenes que ya están en el CMS", 2);
				}
			});
			mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 20));
			mnNewMenu.add(mntmNewMenuItem);

			JSeparator separator_6 = new JSeparator();
			mnNewMenu.add(separator_6);

			mntmNewMenuItem_1 = new JMenuItem("Mover para subir al CMS");
			mntmNewMenuItem_1.setIcon(new ImageIcon(ImagenesSha.class.getResource("/imagenes/actualizar.png")));
			mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
			mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {

					try {

						Metodos.moverArchivos(imagenes, MenuPrincipal.getSeparador(),
								MenuPrincipal.getDirectorioImagenes(), true);
						frmShaImages.dispose();

						Metodos.mensaje("Las imágenes se han movido correctamente", 2);
					}

					catch (IOException e1) {
						//
					}

				}
			});
			mnNewMenu.add(mntmNewMenuItem_1);

			JSeparator separator = new JSeparator();
			mnNewMenu.add(separator);

			JMenuItem mntmNewMenuItem_2 = new JMenuItem("Ver imágenes subidas");
			mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {

					String busqueda = "";

					busqueda = Metodos.separarPorComas(ComprobarSha.getShaimages());

					Metodos.mostrarGaleriaConWhere(busqueda);
				}

			});
			mntmNewMenuItem_2.setIcon(new ImageIcon(ImagenesSha.class.getResource("/imagenes/view.png")));
			mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
			mnNewMenu.add(mntmNewMenuItem_2);

			JButton btnNewButton_1 = new JButton("Abrir carpeta");
			menuBar.add(btnNewButton_1);
			btnNewButton_1.setIcon(new ImageIcon(ImagenesSha.class.getResource("/imagenes/folder.png")));
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));

			JButton btnNewButton_3 = new JButton("Comprobar de nuevo");
			btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnNewButton_3.setIcon(new ImageIcon(ImagenesSha.class.getResource("/imagenes/actualizar.png")));

			btnNewButton_3.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {

						imagenes.clear();

						new ComprobarSha().setVisible(true);

						frmShaImages.dispose();

					}

					catch (IOException e1) {
						//
					}
				}

			});

			JSeparator separator_1 = new JSeparator();
			menuBar.add(separator_1);
			menuBar.add(btnNewButton_3);

			JSeparator separator_2 = new JSeparator();
			menuBar.add(separator_2);

			btnNewButton_1.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {

					try {
						Metodos.abrirCarpeta(ComprobarSha.getRutas().get(0).substring(0,
								ComprobarSha.getRutas().get(0).lastIndexOf(MenuPrincipal.getSeparador()))
								+ MenuPrincipal.getSeparador());
					}

					catch (IOException e1) {
						//
					}

				}
			});

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