package periquito;

import java.awt.Color;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rojerusan.componentes.RSProgressCircle;

@SuppressWarnings("all")
public class Estadistica extends javax.swing.JFrame implements ActionListener, ChangeListener {
	RSProgressCircle button = new RSProgressCircle();

	public Estadistica() {
		getContentPane().setForeground(Color.PINK);

		Color color = new Color(255, 0, 0);
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/imagenes/about.png")));
		setTitle("Periquito v4 Estadisticas");
		setType(Type.UTILITY);
		initComponents();
		button.setForeground(color);
		button.setColorText(color);
		button.setBackground(color);
		this.setVisible(true);

	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JLabel lblNewLabel = new JLabel(" Top 5 imágenes por descargas,visitas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblDescargarEstadisticas = new JLabel("Descargar estadisticas");
		lblDescargarEstadisticas.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btnNewButton = new JButton("Galería");

		JLabel lblNewLabel_1 = new JLabel("Estadísticas de bots");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_2 = new JLabel("Grafico de queso");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_3 = new JLabel("Estadísticas por cidudad,pais,os y buscador");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btnNewButton_1 = new JButton("estadisticas de imagenes y se usuarios");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(30)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDescargarEstadisticas, GroupLayout.PREFERRED_SIZE, 308,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(layout.createSequentialGroup().addComponent(lblNewLabel).addGap(53)
												.addComponent(btnNewButton))
										.addComponent(lblNewLabel_3)))
						.addGroup(layout.createSequentialGroup().addGap(42)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel_2)
										.addComponent(lblNewLabel_1))))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE).addGap(37))
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(btnNewButton_1)
						.addContainerGap(483, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(4).addComponent(btnNewButton_1).addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
						.addComponent(btnNewButton))
				.addGap(18)
				.addComponent(lblDescargarEstadisticas, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
				.addGap(38).addComponent(lblNewLabel_1).addGap(18).addComponent(lblNewLabel_2)
				.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE).addComponent(lblNewLabel_3)
				.addGap(30))
				.addGroup(layout.createSequentialGroup().addGap(163)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(42, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(584, 305));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
