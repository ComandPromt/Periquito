package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")
public class ConfigScrapting extends javax.swing.JFrame implements ActionListener, ChangeListener {

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
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(25)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(chckbxNewCheckBox)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 561, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(33, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(20).addComponent(btnNewButton)
						.addPreferredGap(ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
						.addComponent(chckbxNewCheckBox).addGap(20)));
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
