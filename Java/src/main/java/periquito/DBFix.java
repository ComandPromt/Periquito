package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.MyInterface;

@SuppressWarnings("all")
public class DBFix extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {

	public DBFix() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DBFix.class.getResource("/imagenes/db.png")));
		setTitle("Periquito v3 DB Fix ");
		setType(Type.UTILITY);
		initComponents();
		this.setVisible(true);
	}

	public void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JTextPane txtpnBindaddress = new JTextPane();
		txtpnBindaddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnBindaddress.setText(
				" Linux\r\n\r\n Abrir el terminal y ejecutar\r\n sudo nano /etc/mysql/mysql.conf.d/mysqld.cnf\r\n\r\n Cambiar el parámetro bind-address\r\n bind-address\t= 0.0.0.0\r\n\r\n Guardar el archivo\r\n\r\n/////////////////////////\r\n\r\nConfigurar la cuenta de root\r\n\r\nmysql -u root -p\r\n\r\nCREATE USER 'root'@'%' WITH mysql.native_password BY 'mi contraseña'\r\n\r\nReiniciar el servidor con\r\n\r\nsudo systemctl restart mysql");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(txtpnBindaddress, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(15, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout
						.createSequentialGroup().addContainerGap().addComponent(txtpnBindaddress,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(134, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(336, 404));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
	}

	public void stateChanged(ChangeEvent e) {
	}
}