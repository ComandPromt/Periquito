package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("serial")
public class DescargaThumb extends JFrame implements ActionListener, ChangeListener, MyInterface {
	javax.swing.JLabel jLabel1;
	static javax.swing.JTextField jTextField1;
	private JTextField textField;
	JLabel lblThumbnails;
	private JTextField textField1;
	private static JRadioButton botonRadio1 = new JRadioButton("Complex");

	static boolean error = false;
	private JTextField textField_1;
	private JLabel lblNewLabel;

	public static void setError(boolean error) {
		Descarga.error = error;
	}

	public DescargaThumb() throws IOException {
		getContentPane().setFont(new Font("Dialog", Font.BOLD, 20));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Descarga.class.getResource("/imagenes/download.png")));
		setTitle("Periquito v3 Config Remoto");
		initComponents();
		this.setVisible(true);
	}

	@SuppressWarnings("all")
	public void initComponents() throws IOException {

		jTextField1 = new javax.swing.JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.LEFT);
		jTextField1.setToolTipText("");

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText("URL");
		jLabel1.setIcon(new ImageIcon(DescargaThumb.class.getResource("/imagenes/target.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 24));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setToolTipText("");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JButton btnNewButton = new JButton("");

		btnNewButton.setIcon(new ImageIcon(Descarga.class.getResource("/imagenes/start.png")));

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				LinkedList<String> urls = new LinkedList<String>();
				LinkedList<String> urlsResultado = new LinkedList<String>();
				String url = Metodos.eliminarEspacios(jTextField1.getText(), false);

				int limite = 0;

				String limiteDescarga = Metodos.eliminarEspacios(textField_1.getText(), false);

				if (!limiteDescarga.isEmpty()) {
					limite = Integer.parseInt(limiteDescarga);
				}

				String claseImagen = Metodos.eliminarEspacios(textField1.getText(), false);

				urls = Metodos.obtenerEnlaces(url, 4, Metodos.eliminarEspacios(textField.getText(), false), "", limite);

				// urls = Metodos.obtenerEnlaces(urls.get(0), 5, claseImagen, "", 1);

				if (!urls.isEmpty()) {

					/*
					 * for (int i = 0; i < urls.size(); i++) {
					 * System.out.println(Metodos.obtenerEnlaces(urls.get(i), 5, claseImagen, "",
					 * urls, 1).get(0)); urlsResultado.add(Metodos.obtenerEnlaces(urls.get(i), 5,
					 * claseImagen, "", urls, 1).get(0)); }
					 * 
					 * for (int i = 0; i < urlsResultado.size(); i++) {
					 * System.out.println(urlsResultado.get(i)); }
					 */

					for (int i = 0; i < urls.size(); i++) {
						try {
							Metodos.descargar(urls.get(i), 1, 1, 1);
						} catch (IOException e) {
							//
						}
					}
				}
			}
		});

		lblThumbnails = new JLabel("clase o id de miniatura");
		lblThumbnails.setIcon(null);
		lblThumbnails.setFont(new Font("Tahoma", Font.BOLD, 20));

		textField1 = new JTextField();
		textField1.setToolTipText("");
		textField1.setHorizontalAlignment(SwingConstants.LEFT);
		textField1.setFont(new Font("Dialog", Font.PLAIN, 24));

		JLabel lblFin = new JLabel("Clase o id de imagen");
		lblFin.setFont(new Font("Dialog", Font.BOLD, 20));

		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 24));

		lblNewLabel = new JLabel("Limite");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(26)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(layout.createSequentialGroup().addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
								.addGap(151).addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 106,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel1)
										.addComponent(lblThumbnails).addComponent(lblFin, GroupLayout.PREFERRED_SIZE,
												221, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textField1, Alignment.LEADING)
										.addComponent(textField, Alignment.LEADING).addComponent(jTextField1,
												Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(jTextField1,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblThumbnails))
				.addGap(29)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFin, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
				.addGap(654)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(613, 332));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}

	public static JRadioButton getBotonRadio1() {
		return botonRadio1;
	}

}
