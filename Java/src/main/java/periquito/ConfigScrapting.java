package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")
public class ConfigScrapting extends javax.swing.JFrame implements ActionListener, ChangeListener {
	private JTextField txtClaseTabla;
	private JTextField txtClase;

	public ConfigScrapting() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/imagenes/about.png")));
		setTitle("Periquito v3 About");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);

	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JButton btnNewButton = new JButton("Crear imágenes o categorías por cada tipo de la tabla scrapting");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));

		JCheckBox chckbxNewCheckBox = new JCheckBox("Apagar equipo al terminar el escaneo de URL");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 14));

		JRadioButton radioButton = new JRadioButton("Filtrar todas las URLs");
		radioButton.setFont(new Font("Tahoma", Font.BOLD, 14));

		JRadioButton rdbtnFiltrarUrlsDe = new JRadioButton("Filtrar URLs de tabla");
		rdbtnFiltrarUrlsDe.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtClaseTabla = new JTextField();
		txtClaseTabla.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtClaseTabla.setHorizontalAlignment(SwingConstants.CENTER);
		txtClaseTabla.setText("<table>");
		txtClaseTabla.setColumns(10);

		txtClase = new JTextField();
		txtClase.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtClase.setText("<td>");
		txtClase.setHorizontalAlignment(SwingConstants.CENTER);
		txtClase.setColumns(10);

		JLabel lblClaseclaseId = new JLabel("Clase:  .clase    Id: #id ");
		lblClaseclaseId.setFont(new Font("Tahoma", Font.BOLD, 14));

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(ConfigScrapting.class.getResource("/imagenes/save.png")));

		JLabel lblNewLabel = new JLabel("Guarda en scrapting.txt");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
				layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(25)
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addComponent(chckbxNewCheckBox)
														.addPreferredGap(ComponentPlacement.RELATED, 54,
																Short.MAX_VALUE)
														.addComponent(lblNewLabel))
												.addGroup(layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(btnNewButton_1).addComponent(btnNewButton,
																GroupLayout.PREFERRED_SIZE, 561,
																GroupLayout.PREFERRED_SIZE))))
								.addGroup(layout.createSequentialGroup().addGap(39).addComponent(radioButton).addGap(18)
										.addComponent(rdbtnFiltrarUrlsDe, GroupLayout.PREFERRED_SIZE, 187,
												GroupLayout.PREFERRED_SIZE)))
								.addContainerGap())
						.addGroup(layout.createSequentialGroup().addContainerGap(239, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblClaseclaseId)
										.addGroup(layout.createSequentialGroup().addGap(10)
												.addGroup(layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(txtClase, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtClaseTabla, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addGap(224)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(20).addComponent(btnNewButton)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(18)
										.addGroup(layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(radioButton).addComponent(rdbtnFiltrarUrlsDe))
										.addGap(18).addComponent(lblClaseclaseId).addGap(9)
										.addComponent(txtClaseTabla, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtClase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(chckbxNewCheckBox).addComponent(lblNewLabel))
										.addGap(17))
								.addGroup(Alignment.TRAILING,
										layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnNewButton_1).addGap(42)))));
		getContentPane().setLayout(layout);
		setSize(new Dimension(625, 305));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
