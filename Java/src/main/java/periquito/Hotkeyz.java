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
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")
public class Hotkeyz extends javax.swing.JFrame implements ActionListener, ChangeListener {

	public Hotkeyz() {
		getContentPane().setBackground(Color.WHITE);
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

		JTextArea txtrPeriquitoGui = new JTextArea();
		txtrPeriquitoGui.setWrapStyleWord(true);
		txtrPeriquitoGui.setText(
				"-----------------------------------------------------------------------------------------------------\r\n                                                  Periquito GUI\r\n-----------------------------------------------------------------------------------------------------\r\n\r\nEnter → Sube imágenes al CMS\r\n\r\nCTRL + N → Abre el menú de las notas\r\n\r\nSHIFT + C → Abre el menú de los comentarios de las imágenes\r\n\r\nCTRL + R → Recorta fotos\r\n\r\nCTRL + S → Abre el buscador de imágenes\r\n\r\nCTRL + B → Hace el backup de la base de datos\r\n\r\nSHIFT + S → Comprueba SHA\r\n\r\nSHIFT + G → Crea gif animados a partir de imágenes ( Gif animator)\r\n\r\nCTRL + G → Abre la carpeta del Gif Animator\r\n\r\nCTRL + I → Abre la carpeta imágenes\r\n\r\nCTRL + F → Abre la carpeta del Video Extractor\r\n\r\nSHIFT + F → Video a frames\r\n\r\nF1 → Ayuda sobre los atajos de teclado\r\n\r\nF2 → Ayuda para reparar la base de datos");
		txtrPeriquitoGui.setLineWrap(true);
		txtrPeriquitoGui.setFont(new Font("Dialog", Font.BOLD, 16));
		txtrPeriquitoGui.setEditable(false);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtrPeriquitoGui, GroupLayout.PREFERRED_SIZE, 512, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(txtrPeriquitoGui, GroupLayout.PREFERRED_SIZE, 668, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(22, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(539, 703));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
