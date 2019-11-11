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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;

@SuppressWarnings("all")
public class BackupBd extends javax.swing.JFrame implements ActionListener, ChangeListener {
	private JTextField textField;

	public BackupBd() {
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

		JButton btnNewButton = new JButton("Backup de la BD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Metodos.backupBd();
			}
		});

		JLabel lblNewLabel = new JLabel("");

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Backup Completo");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 16));

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Backup de tablas específicas");
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JTextPane txtpnMostrartablasDeBd = new JTextPane();
		txtpnMostrartablasDeBd.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpnMostrartablasDeBd.setText("Mostrar todas las tablas de Bd");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblEscribeLasTablas = new JLabel("Escribe las tablas separadas por coma para hacer el backup");
		lblEscribeLasTablas.setFont(new Font("Tahoma", Font.BOLD, 16));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGroup(layout
						.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(23)
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
										.addComponent(rdbtnNewRadioButton_1).addComponent(rdbtnNewRadioButton)
										.addComponent(lblEscribeLasTablas)))
						.addGroup(layout.createSequentialGroup().addGap(61)
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(btnNewButton)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup().addGap(60).addComponent(txtpnMostrartablasDeBd,
								GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(114, Short.MAX_VALUE)));
		layout.setVerticalGroup(
				layout.createParallelGroup(Alignment.LEADING)
						.addGroup(
								layout.createSequentialGroup().addGap(21).addComponent(rdbtnNewRadioButton).addGap(28)
										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel).addComponent(rdbtnNewRadioButton_1))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(txtpnMostrartablasDeBd, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(lblEscribeLasTablas)
										.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(btnNewButton).addGap(28)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(627, 305));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
