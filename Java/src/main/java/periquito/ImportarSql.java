package periquito;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")
public class ImportarSql extends javax.swing.JFrame implements ActionListener, ChangeListener {

	public ImportarSql() {
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

		JLabel lblEjecutarArchivoSql = new JLabel("Ejecutar archivo sql");
		lblEjecutarArchivoSql.setFont(new Font("Tahoma", Font.BOLD, 20));

		JTextArea textArea = new JTextArea();
		textArea.setText(" Arrastra los archivos aqui");
		textArea.setForeground(Color.DARK_GRAY);
		textArea.setFont(new Font("Tahoma", Font.BOLD, 24));
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);

		JLabel lblVermostrarElNumero = new JLabel("Mostrar el numero de sentencias ejecutadas");
		lblVermostrarElNumero.setFont(new Font("Tahoma", Font.BOLD, 20));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(37).addComponent(textArea,
								GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(69).addComponent(lblEjecutarArchivoSql))
						.addGroup(layout.createSequentialGroup().addGap(29).addComponent(lblVermostrarElNumero)))
						.addContainerGap(51, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(16)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(lblEjecutarArchivoSql).addGap(51).addComponent(lblVermostrarElNumero)
						.addContainerGap(81, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(532, 305));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
