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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")
public class ConfiguracionSonido extends javax.swing.JFrame implements ActionListener, ChangeListener {

	public ConfiguracionSonido() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConfiguracionSonido.class.getResource("/imagenes/wav.png")));
		setTitle("Periquito v3 Conf.Sonido");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);

	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JButton button = new JButton("Sonidos");
		button.setIcon(new ImageIcon(ConfiguracionSonido.class.getResource("/imagenes/folder.png")));
		button.setFont(new Font("Tahoma", Font.BOLD, 16));

		JComboBox comboBox = new JComboBox();

		JButton button_1 = new JButton("Play");
		button_1.setIcon(new ImageIcon(ConfiguracionSonido.class.getResource("/imagenes/start.png")));

		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(ConfiguracionSonido.class.getResource("/imagenes/wav.png")));
		label.setText("Sonido");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 24));

		JCheckBox checkBox = new JCheckBox("Repetir");
		checkBox.setFont(new Font("Tahoma", Font.BOLD, 16));

		JCheckBox checkBox_1 = new JCheckBox("Activar sonido");
		checkBox_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(ConfiguracionSonido.class.getResource("/imagenes/save.png")));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup().addGap(199).addComponent(label,
								GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(15).addGroup(layout
								.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(checkBox_1, GroupLayout.PREFERRED_SIZE, 141,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(checkBox, GroupLayout.PREFERRED_SIZE, 87,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
				.addGap(26)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
				.addGap(19)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 46, Short.MAX_VALUE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addGap(30)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(checkBox, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(checkBox_1, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup().addGap(18).addComponent(button_2,
										GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)))
						.addGap(34)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(595, 297));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
