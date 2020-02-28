package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")
public class ConfigDescarga extends javax.swing.JFrame implements ActionListener, ChangeListener {

	public ConfigDescarga() {
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

		JLabel lblNewLabel = new JLabel("Filtro y extensión por defecto");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblSitiosConfigurados = new JLabel("Sitios configurados");
		lblSitiosConfigurados.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblSitioPorDefecto = new JLabel("Sitio por defecto");
		lblSitioPorDefecto.setFont(new Font("Tahoma", Font.BOLD, 16));

		JScrollPane scrollPane = new JScrollPane();

		JButton btnNewButton = new JButton("Poner sitio por defecto");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btnNuevoSitio = new JButton("Nuevo sitio");
		btnNuevoSitio.setFont(new Font("Tahoma", Font.BOLD, 16));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
				layout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
						layout.createSequentialGroup().addGap(29)
								.addGroup(layout
										.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNuevoSitio,
														GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(btnNewButton).addContainerGap())
										.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(
												layout.createSequentialGroup()
														.addComponent(lblSitiosConfigurados, GroupLayout.DEFAULT_SIZE,
																473, Short.MAX_VALUE)
														.addContainerGap())
												.addGroup(layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(Alignment.TRAILING)
														.addComponent(lblSitioPorDefecto, GroupLayout.DEFAULT_SIZE, 306,
																Short.MAX_VALUE)
														.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 306,
																Short.MAX_VALUE)
														.addGroup(layout.createSequentialGroup()
																.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 306,
																		Short.MAX_VALUE)
																.addPreferredGap(ComponentPlacement.RELATED)))
														.addGap(177))))));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(20).addComponent(lblNewLabel).addGap(41)
						.addComponent(lblSitioPorDefecto, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblSitiosConfigurados, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(btnNewButton)
								.addComponent(btnNuevoSitio))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGap(27)));

		JList list = new JList();
		scrollPane.setViewportView(list);
		getContentPane().setLayout(layout);
		setSize(new Dimension(518, 359));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
