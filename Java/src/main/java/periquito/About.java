package periquito;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Font;

@SuppressWarnings("serial")
public class About extends javax.swing.JFrame implements ActionListener, ChangeListener {
	private javax.swing.Box.Filler filler1;

	public About() {
		setTitle("Periquito v2.1 About");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);

	}

	private void initComponents() {
		filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0),
				new java.awt.Dimension(32767, 32767));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JTextArea txtrAdvertenciaLa = new JTextArea();
		txtrAdvertenciaLa.setFont(new Font("Arial", Font.BOLD, 15));
		txtrAdvertenciaLa.setWrapStyleWord(true);
		txtrAdvertenciaLa.setEditable(false);
		txtrAdvertenciaLa.setLineWrap(true);
		txtrAdvertenciaLa.setText(
				"-------------------------------------------------------\r\n\r\n                 Configuraciones\r\n\r\n-------------------------------------------------------\r\n \r\nDebemos de tener instalado nuestro\r\n\r\nServidor web en la unidad C:\\\r\n\r\nSe puede modificar la ruta\r\n\r\nPero debemos de dejar la\r\n\r\nParte anterior y posterior (en algunos casos)\r\n\r\n(Según el orden de rutas en el programa)\r\n\r\nC:\\________\\imagenes\r\n\r\nhttp://______.php    http://______.html\r\n\r\nC:\\_________\\Hacer_gif\r\n\r\nC:\\_________\\Hacer_gif\\______.php C:\\_________\\Hacer_gif\\______.html \r\n\r\nC:\\_________\\GifFrames\r\n\r\nC:\\_________\\GifFrames\\______.php C:\\_________\\GifFrames\\______.html ");
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(txtrAdvertenciaLa, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
						.addGap(173).addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addGap(389)
						.addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(164, Short.MAX_VALUE))
				.addComponent(txtrAdvertenciaLa, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 553, Short.MAX_VALUE));
		getContentPane().setLayout(layout);
		setSize(new Dimension(326, 596));
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

	@Override
	public void stateChanged(ChangeEvent e) {
	}
}
