package utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import periquito.Config2;

@SuppressWarnings("all")

public class ImagenesSha extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	private JTable table;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Config2.class.getResource("/imagenes/config.png")));
		setTitle("Periquito v3 Config Remoto");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	@SuppressWarnings("all")
	public void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);
		buscarArchivoConf();

		String titulos[] = { "Codigo", "Nombre", "Edad", "Profesión", "Telefono" };

		DefaultTableModel myTable = new DefaultTableModel();

		table = new JTable(myTable);

		table.setEnabled(false);

		myTable.addColumn("");

		myTable.addColumn("");

		for (int x = 0; x < ComprobarSha.getLectura().size();) {
			myTable.addRow(new Object[] { ComprobarSha.getLectura().get(x), ComprobarSha.getLectura().get(x++) });

		}

		JLabel lblNewLabel = new JLabel("Imagen");

		JLabel lblNewLabel_1 = new JLabel("¿Está en la BD?");
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(table, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup().addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1).addGap(91)))));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(table, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE).addContainerGap()));
		getContentPane().setLayout(layout);
		setSize(new Dimension(368, 294));
		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}