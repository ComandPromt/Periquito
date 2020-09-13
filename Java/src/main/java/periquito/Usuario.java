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
import javax.swing.LayoutStyle.ComponentPlacement;
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

		JButton btnNewButton = new JButton("ver perfil");

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					new VerUser().setVisible(true);
				}

				catch (Exception e1) {
					//
				}

			}

		});

		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_1 = new JLabel("Mostrar datatable para ver los usuarios");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_2 = new JLabel(" y buscarlos por nombre");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btnNewButton_1 = new JButton("Crear Usuarios");

		JLabel lblNewLabel = new JLabel("Tabla para mostrar todos los usuarios");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(37)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout
								.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_2)
										.addComponent(lblNewLabel_1))
								.addContainerGap(259, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup().addGap(22).addGroup(layout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(lblNewLabel).addContainerGap())
								.addGroup(layout.createSequentialGroup().addComponent(btnNewButton)
										.addPreferredGap(ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
										.addComponent(btnNewButton_1).addGap(145)))))));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(51).addComponent(lblNewLabel).addGap(42)
										.addComponent(btnNewButton).addGap(65).addComponent(lblNewLabel_1).addGap(18)
										.addComponent(lblNewLabel_2))
								.addGroup(layout.createSequentialGroup().addGap(124).addComponent(btnNewButton_1)))
						.addContainerGap(17, Short.MAX_VALUE)));
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
