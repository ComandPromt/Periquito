package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.TooManyListenersException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
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
	static LinkedList<String> lectura = new LinkedList<>();
	static LinkedList<String> shaimages = new LinkedList<>();

	public static LinkedList<String> getShaimages() {
		return shaimages;
	}

	public static void setShaimages(LinkedList<String> shaimages) {
		ComprobarSha.shaimages = shaimages;
	}

	public static LinkedList<String> getLectura() {
		return lectura;
	}

	public void setLectura(LinkedList<String> lectura) {
		ComprobarSha.lectura = lectura;
	}

	public ComprobarSha() throws IOException {
		setAlwaysOnTop(true);

		setIconImage(Toolkit.getDefaultToolkit().getImage(ComprobarSha.class.getResource("/imagenes/db.png")));

		setTitle("Periquito v3 Comprobador SHA");
		setType(Type.UTILITY);
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
		lblNewLabel.setIcon(new ImageIcon(ComprobarSha.class.getResource("/imagenes/import.png")));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(21).addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(imagenes, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(64, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(imagenes, Alignment.TRAILING).addComponent(lblNewLabel, Alignment.TRAILING,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap(16, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(500, 116));
		setLocationRelativeTo(null);

		javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder("Drop 'em");
		try {
			new DragAndDrop(imagenes, dragBorder, rootPaneCheckingEnabled, new DragAndDrop.Listener() {

				public void filesDropped(java.io.File[] files) {

					try {
						lectura.clear();
						for (int x = 0; x < files.length; x++) {
							lectura.add(files[x].getCanonicalPath().substring(
									files[x].getCanonicalPath().lastIndexOf(MenuPrincipal.getSeparador()) + 1,
									files[x].getCanonicalPath().length()));
							shaimages.add(Metodos.getSHA256Checksum(files[x].getCanonicalPath()));
						}

						if (lectura.size() > 0) {
							dispose();
							try {
								new ImagenesSha();
							} catch (Exception e) {
								//
							}
						}
					} catch (Exception e) {
						//
					}
				}

			});

		} catch (TooManyListenersException e1) {
			Metodos.mensaje("Error", 1);
		}

	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}