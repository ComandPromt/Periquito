package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rojerusan.componentes.RSProgressBar;
import utils.MyInterface;

@SuppressWarnings("all")

public class Progreso extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {

	JLabel lblThumbnails;

	RSProgressBar progressBarRecorrido = new RSProgressBar();

	RSProgressBar progressBar = new RSProgressBar();

	public Progreso() {

		try {

			setAlwaysOnTop(true);

			setIconImage(Toolkit.getDefaultToolkit().getImage(Config2.class.getResource("/imagenes/config.png")));

			setTitle("Periquito v3 Uploading");

			setType(Type.UTILITY);

			initComponents();

			this.setVisible(true);

		}

		catch (Exception e) {

		}

	}

	@SuppressWarnings("all")

	public void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		lblThumbnails = new JLabel("Resultado de la subida");
		lblThumbnails.setIcon(new ImageIcon(Progreso.class.getResource("/imagenes/remote.png")));
		lblThumbnails.setFont(new Font("Tahoma", Font.BOLD, 20));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING,
						layout.createSequentialGroup().addGap(43)
								.addComponent(lblThumbnails, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE).addGap(56))
				.addGroup(Alignment.LEADING, layout.createSequentialGroup().addGap(31)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(progressBarRecorrido, GroupLayout.PREFERRED_SIZE, 319,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(46, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				layout.createSequentialGroup().addContainerGap().addComponent(lblThumbnails).addGap(29)
						.addComponent(progressBarRecorrido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(119, Short.MAX_VALUE)));

		progressBarRecorrido.setValue(0);
		progressBar.setValue(0);

		getContentPane().setLayout(layout);
		setSize(new Dimension(392, 245));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}

	public void setProgressBarRecorridoValor(int n) {
		progressBarRecorrido.setValue(n);
	}

	public void setProgressBarRecorrido(String s) {
		progressBarRecorrido.setString(s);
	}

	public void setProgressBar(int n) {
		progressBar.setValue(n);
	}

}