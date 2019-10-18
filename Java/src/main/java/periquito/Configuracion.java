package periquito;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.MyInterface;

@SuppressWarnings("all")

public class Configuracion extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	static javax.swing.JTextField jTextField1;
	private JTextField textField;

	public Configuracion() throws IOException {
		setResizable(false);
		setAutoRequestFocus(false);
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 16));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Config2.class.getResource("/imagenes/config.png")));
		setTitle("Periquito v3 Config Remoto");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	@SuppressWarnings("all")
	public void initComponents() throws IOException {

		jTextField1 = new javax.swing.JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.LEFT);
		jTextField1.setToolTipText("");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/nota.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_2 = new JLabel("Valores por defecto");
		lblNewLabel_2.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/config.png")));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 24));

		JLabel label = new JLabel("Descripción");
		label.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/name.png")));
		label.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel label_1 = new JLabel("Categoría");
		label_1.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/tag.png")));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel label_2 = new JLabel("Etiquetas");
		label_2.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/tag.png")));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		JComboBox comboBox = new JComboBox();

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		textField = new JTextField();
		textField.setToolTipText("");
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JButton button = new JButton("");
		button.setIcon(new ImageIcon(Configuracion.class.getResource("/imagenes/save.png")));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addGap(130).addComponent(lblNewLabel_2).addContainerGap(167,
						Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(button,
										GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addGap(31)
														.addGroup(layout.createParallelGroup(Alignment.LEADING)
																.addComponent(label, GroupLayout.PREFERRED_SIZE, 169,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 169,
																		GroupLayout.PREFERRED_SIZE)))
												.addGroup(layout.createSequentialGroup().addGap(29)
														.addGroup(layout.createParallelGroup(Alignment.LEADING)
																.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 148,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(lblNewLabel))))
										.addGap(27)
										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addComponent(comboBox, Alignment.LEADING, 0, 269, Short.MAX_VALUE)
												.addComponent(textField, Alignment.LEADING, 269, 269, 269)
												.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 269,
														Short.MAX_VALUE)
												.addComponent(jTextField1, Alignment.LEADING,
														GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE))))
						.addGap(101)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(27).addComponent(lblNewLabel_2).addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(18).addComponent(lblNewLabel))
						.addGroup(layout.createSequentialGroup().addGap(37).addComponent(jTextField1,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGap(24)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(14).addComponent(scrollPane,
								GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(56)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
				.addGap(25)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(button, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE).addGap(30)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(532, 671));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}