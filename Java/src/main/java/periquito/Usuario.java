package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")
public class Usuario extends javax.swing.JFrame implements ActionListener, ChangeListener {

	public Usuario() {
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

		JLabel lblNewLabel = new JLabel("perfil del usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btnNewButton = new JButton("ver perfil");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_1 = new JLabel("Mostrar datatable para ver los usuarios");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_2 = new JLabel(" y buscarlos por nombre");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(59).addComponent(lblNewLabel))
						.addGroup(layout.createSequentialGroup().addGap(37)
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_1)
										.addComponent(btnNewButton).addComponent(lblNewLabel_2))))
						.addContainerGap(161, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(26).addComponent(lblNewLabel).addGap(87)
						.addComponent(btnNewButton).addGap(39).addComponent(lblNewLabel_1).addGap(18)
						.addComponent(lblNewLabel_2).addContainerGap(35, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(623, 305));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
