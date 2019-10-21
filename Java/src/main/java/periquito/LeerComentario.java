package periquito;

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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.MyInterface;

@SuppressWarnings("all")
public class LeerComentario extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	private JTextField textField;
	private JTextField textField_1;

	public LeerComentario() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Config.class.getResource("/imagenes/config.png")));
		setTitle("Periquito v3");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	public void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JLabel tipo = new JLabel("Categoría");
		tipo.setIcon(new ImageIcon(LeerComentario.class.getResource("/imagenes/tag.png")));
		tipo.setFont(new Font("Tahoma", Font.BOLD, 24));

		JLabel lblDescripcin = new JLabel("Imagen");
		lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 24));

		JLabel lblNewLabel = new JLabel("Preview");

		JLabel lblNewLabel_1 = new JLabel("En cada comentario habrá 10 enlaces como máximo");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));

		JComboBox comboBox = new JComboBox();

		textField = new JTextField();
		textField.setToolTipText("");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JButton button = new JButton("");
		button.setIcon(new ImageIcon(LeerComentario.class.getResource("/imagenes/abrir.png")));

		JLabel lblTextoDeLos = new JLabel("Texto");
		lblTextoDeLos.setFont(new Font("Tahoma", Font.BOLD, 24));

		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblDragAndDrop = new JLabel("Drag and drop de imagen descargada en el CMS para añadir comentario");
		lblDragAndDrop.setFont(new Font("Tahoma", Font.BOLD, 14));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addGap(20)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addGroup(layout.createSequentialGroup().addComponent(tipo)
												.addPreferredGap(ComponentPlacement.UNRELATED))
										.addGroup(
												layout.createSequentialGroup().addComponent(lblDescripcin).addGap(100)))
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 236,
														GroupLayout.PREFERRED_SIZE)
												.addGap(47).addComponent(button, GroupLayout.PREFERRED_SIZE, 48,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 236,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(textField, GroupLayout.PREFERRED_SIZE, 236,
																GroupLayout.PREFERRED_SIZE)))))
								.addComponent(lblTextoDeLos, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 197,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, Alignment.LEADING)))
						.addGroup(layout.createSequentialGroup().addGap(48).addComponent(lblNewLabel))
						.addGroup(layout.createSequentialGroup().addGap(33).addComponent(lblDragAndDrop)))
				.addContainerGap(23, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addGap(19)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(tipo).addComponent(
								comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addGap(27)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDescripcin, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addGap(31).addComponent(lblNewLabel))
						.addGroup(layout.createSequentialGroup().addGap(32)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)))
				.addGap(37)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTextoDeLos, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
				.addGap(75).addComponent(lblDragAndDrop).addGap(46).addComponent(lblNewLabel_1).addGap(24)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(573, 481));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
	}

	public void stateChanged(ChangeEvent e) {
	}
}