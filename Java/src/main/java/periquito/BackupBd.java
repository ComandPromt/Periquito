package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;

@SuppressWarnings("all")
public class BackupBd extends javax.swing.JFrame implements ActionListener, ChangeListener {

	private JTextField textField;

	private JRadioButton rdbtnNewRadioButton;

	private JRadioButton rdbtnNewRadioButton_1;
	private JTextField textField_1;

	private LinkedList<String> tablas = new LinkedList();

	public BackupBd() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/imagenes/about.png")));
		setTitle("Periquito v3 BackupBD");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);

	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JButton btnNewButton = new JButton("Backup de la BD");
		btnNewButton.setIcon(new ImageIcon(BackupBd.class.getResource("/imagenes/db.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				tablas.clear();

				String nombreArchivo = "";

				nombreArchivo = Metodos.eliminarEspacios(textField_1.getText());

				if (nombreArchivo.isBlank() || nombreArchivo.isEmpty()) {
					nombreArchivo = "backup-BD";
				}

				if (rdbtnNewRadioButton.isSelected()) {

					tablas.add("users");
					tablas.add("comments");
					tablas.add("categories");
					tablas.add("etiquetas");
					tablas.add("groups");
					tablas.add("images");
					tablas.add("imgroups");
					tablas.add("imv");
					tablas.add("lightboxes");
					tablas.add("msgroups");
					tablas.add("musugroup");
					tablas.add("scrapting");
					tablas.add("tags");
					tablas.add("video");
					tablas.add("videocomments");

					tablas.add("antispam");
					tablas.add("bots");
					tablas.add("descargas");
					tablas.add("grupos");
					tablas.add("mensajes");
					tablas.add("groups");
					tablas.add("images");
					tablas.add("imgroups");
					tablas.add("imv");
					tablas.add("notas");
					tablas.add("tbl_tracking");

				}

				else {

					String tablasBackup = Metodos.eliminarEspacios(textField.getText());

					if (tablasBackup.indexOf(",") > 0) {

						tablasBackup.split(",");

						String[] elementos = tablasBackup.split(",");

						for (int i = 0; i < elementos.length; i++) {
							tablas.add(Metodos.eliminarEspacios(elementos[i]));
						}
					}
				}

				if (!tablas.isEmpty()) {
					Metodos.backupBd(nombreArchivo, tablas);
				}

			}
		});

		JLabel lblNewLabel = new JLabel("");

		rdbtnNewRadioButton = new JRadioButton("Backup Completo");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				rdbtnNewRadioButton_1.setSelected(false);
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				if (!rdbtnNewRadioButton_1.isSelected() && !rdbtnNewRadioButton.isSelected()) {
					rdbtnNewRadioButton.setSelected(true);
				}
			}
		});
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 16));

		rdbtnNewRadioButton_1 = new JRadioButton("Backup de tablas específicas");

		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				rdbtnNewRadioButton.setSelected(false);
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				if (!rdbtnNewRadioButton_1.isSelected() && !rdbtnNewRadioButton.isSelected()) {
					rdbtnNewRadioButton_1.setSelected(true);
				}
			}
		});
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JTextPane txtpnMostrartablasDeBd = new JTextPane();
		txtpnMostrartablasDeBd.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpnMostrartablasDeBd.setText("Mostrar todas las tablas de Bd");

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);

		JLabel lblEscribeLasTablas = new JLabel("Escribe las tablas (separadas por coma)");
		lblEscribeLasTablas.setFont(new Font("Tahoma", Font.BOLD, 16));

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombre del archivo (sin extension)");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Usar mysqldump");
		rdbtnNewRadioButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Usar Select");
		rdbtnNewRadioButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addGap(23)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel).addGroup(layout
						.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnNewRadioButton).addComponent(rdbtnNewRadioButton_2)
										.addComponent(rdbtnNewRadioButton_3))
								.addPreferredGap(ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnNewRadioButton_1).addComponent(txtpnMostrartablasDeBd,
												GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblEscribeLasTablas).addComponent(lblNewLabel_1))
										.addGap(26)
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 233,
														Short.MAX_VALUE)
												.addComponent(textField, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
												.addComponent(btnNewButton))))
						.addGap(96)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(21)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup().addComponent(rdbtnNewRadioButton).addGap(8))
								.addGroup(layout.createSequentialGroup().addComponent(rdbtnNewRadioButton_1)
										.addPreferredGap(ComponentPlacement.UNRELATED)))
						.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(layout.createSequentialGroup()
										.addComponent(txtpnMostrartablasDeBd, GroupLayout.PREFERRED_SIZE, 138,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18))
								.addGroup(layout.createSequentialGroup().addGap(30).addComponent(rdbtnNewRadioButton_2)
										.addGap(18).addComponent(rdbtnNewRadioButton_3)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblNewLabel).addGap(8)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblEscribeLasTablas)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(22)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18).addComponent(btnNewButton).addGap(62)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(636, 428));
		setLocationRelativeTo(null);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
