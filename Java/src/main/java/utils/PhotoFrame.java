package utils;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import periquito.MenuPrincipal;

@SuppressWarnings("serial")
public class PhotoFrame extends javax.swing.JFrame {

	static PhotoPanel photoPanel = new PhotoPanel();
	javax.swing.JMenu jMenu1;
	javax.swing.JMenuBar jMenuBar1;
	javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JPanel jPanel1;
	javax.swing.JScrollPane jScrollPane1;

	public PhotoFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PhotoFrame.class.getResource("/imagenes/maxresdefault.jpg")));
		initComponents();
		PhotoFrame.this.setTitle("Periquito v3");
		PhotoFrame.this.setLocationRelativeTo(null);
		this.jPanel1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.jPanel1.add(photoPanel);
	}

	@SuppressWarnings("all")
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jPanel1 = new javax.swing.JPanel();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenuBar1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		jMenu1 = new javax.swing.JMenu();
		jMenu1.setIcon(new ImageIcon(PhotoFrame.class.getResource("/imagenes/insert.png")));
		jMenu1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		jMenuItem1 = new javax.swing.JMenuItem();
		jMenuItem1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		jMenuItem1.setIcon(new ImageIcon(PhotoFrame.class.getResource("/imagenes/abrir.png")));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new java.awt.CardLayout());

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 487, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 499, Short.MAX_VALUE));

		jScrollPane1.setViewportView(jPanel1);

		getContentPane().add(jScrollPane1, "card2");

		jMenu1.setText("File");

		jMenuItem1.setText("Open image...");
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jMenuItem1ActionPerformed();
				} catch (IOException e) {
					Metodos.mensaje("Error", 1);
				}
			}
		});
		jMenu1.add(jMenuItem1);

		jMenuBar1.add(jMenu1);

		setJMenuBar(jMenuBar1);

		pack();
	}

	private void jMenuItem1ActionPerformed() throws IOException {// GEN-FIRST:event_jMenuItem1ActionPerformed
		JFileChooser fileChooser = new JFileChooser();
		File miDir = new File(".");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo de Imagen", "jpg", "png"));

		fileChooser.setCurrentDirectory(new File(
				miDir.getCanonicalPath() + Metodos.saberseparador(Integer.parseInt(MenuPrincipal.getOs()))
						+ "imagenes_para_recortar"));
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			try {

				photoPanel.setPhoto(ImageIO.read(fileChooser.getSelectedFile()));

			} catch (IOException ex) {
				//
			}
		}
	}

	public static void main(String[] args) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(PhotoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new PhotoFrame().setVisible(true);
			}
		});
	}

}