package periquito;

// Ver descargar de cada user y  botón para mostrar en galería

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")

public class DescargasScrapting extends javax.swing.JFrame implements ActionListener, ChangeListener {

	private JTextField textField;

	public DescargasScrapting() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/imagenes/about.png")));
		setTitle("Periquito v3 About");
		setType(Type.POPUP);
		initComponents();
		this.setVisible(true);

	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JLabel lblUsuario = new JLabel("Usuario");

		JComboBox comboBox = new JComboBox();

		JButton btnNewButton = new JButton("Ver descargas");

		JLabel lblSql = new JLabel("SQL");

		textField = new JTextField();
		textField.setColumns(10);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(19)
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(btnNewButton).addGroup(layout
						.createSequentialGroup()
						.addGroup(layout
								.createParallelGroup(Alignment.LEADING).addComponent(lblUsuario).addComponent(lblSql))
						.addGap(49)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(102, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(32)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblUsuario).addComponent(comboBox,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblSql).addComponent(textField,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(14).addComponent(btnNewButton).addContainerGap(153, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(227, 187));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
