package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class About extends javax.swing.JFrame implements ActionListener, ChangeListener {

	public About() {
		setTitle("Periquito v3 About");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);

	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JTextArea txtrAdvertenciaLa = new JTextArea();
		txtrAdvertenciaLa.setFont(new Font("Arial", Font.BOLD, 18));
		txtrAdvertenciaLa.setWrapStyleWord(true);
		txtrAdvertenciaLa.setEditable(false);
		txtrAdvertenciaLa.setLineWrap(true);
		txtrAdvertenciaLa.setText(
				"---------------------------------------------\r\n                Periquito GUI\r\n---------------------------------------------\r\n\r\n Creado por: ComandPromt\r\n\r\n Email: smr2gocar@gmail.com");
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(25)
						.addComponent(txtrAdvertenciaLa, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
						.addGap(201)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(txtrAdvertenciaLa, GroupLayout.PREFERRED_SIZE, 525, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(326, 580));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
