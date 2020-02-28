package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")

public class InformacionImagen extends javax.swing.JFrame implements ActionListener, ChangeListener {

	public InformacionImagen() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(InformacionImagen.class.getResource("/imagenes/InformacionImagen.png")));
		setTitle("Periquito v3 InformacionImagen");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);

	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JTextArea txtrAdvertenciaLa = new JTextArea();
		txtrAdvertenciaLa.setFont(new Font("Dialog", Font.BOLD, 16));
		txtrAdvertenciaLa.setWrapStyleWord(true);
		txtrAdvertenciaLa.setEditable(false);
		txtrAdvertenciaLa.setLineWrap(true);
		txtrAdvertenciaLa.setText(
				"Ver usuario que le subió ,\r\n la hora que se subió\r\nver número de visitas, \r\nver numero de descargas,\r\nmetadatos,\r\n etc\r\n");
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(21)
						.addComponent(txtrAdvertenciaLa, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(23, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(27)
						.addComponent(txtrAdvertenciaLa, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(31, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(309, 305));
		setLocationRelativeTo(null);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
