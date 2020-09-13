package periquito;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")

public class CrontabScrapting extends javax.swing.JFrame implements ActionListener, ChangeListener {
	private JTextField txtDia;
	private JTextField txtHora;
	private JTextField txtSegundos;
	private JTextField txtMes;
	private JTextField txtMinutos;

	public CrontabScrapting() {
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

		txtDia = new JTextField();
		txtDia.setText("dia");
		txtDia.setColumns(10);

		txtHora = new JTextField();
		txtHora.setText("hora");
		txtHora.setColumns(10);

		txtSegundos = new JTextField();
		txtSegundos.setText("segundos");
		txtSegundos.setColumns(10);

		txtMes = new JTextField();
		txtMes.setText("mes");
		txtMes.setColumns(10);

		txtMinutos = new JTextField();
		txtMinutos.setText("minutos");
		txtMinutos.setColumns(10);

		JLabel lblActualizarCada = new JLabel("Actualizar cada");
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(28)
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblActualizarCada)
								.addComponent(txtSegundos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup()
										.addComponent(txtHora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(txtMinutos, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(txtDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(txtMes, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(65, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(12).addComponent(lblActualizarCada).addGap(18)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(33)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtHora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMinutos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(34).addComponent(txtSegundos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(105, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(309, 305));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
