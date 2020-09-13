package periquito;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import rojerusan.RSFotoCircle;
import utils.ImpedirModificarImagen;

@SuppressWarnings("all")
public class VerUser extends javax.swing.JFrame implements ActionListener, ChangeListener {

	public static RSFotoCircle avatar = new RSFotoCircle();

	public static ImageIcon getIcono() {
		return icono;
	}

	public static void setIcono(ImageIcon icono) {
		VerUser.icono = icono;
	}

	public static RSFotoCircle getAvatar() {
		return avatar;
	}

	public static ImageIcon icono;

	Timer t = new Timer();
	ImpedirModificarImagen mTask = new ImpedirModificarImagen();
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_1;

	public VerUser() throws FileNotFoundException, IOException {

		setIconImage(Toolkit.getDefaultToolkit().getImage(VerUser.class.getResource("/imagenes/about.png")));
		setTitle("Periquito v3 About");
		setType(Type.UTILITY);
		initComponents();

		icono = new ImageIcon(VerUser.class.getResource("/imagenes/abrir.png"));
		avatar.image_default = Toolkit.getDefaultToolkit()
				.getImage(VerUser.class.getResource("/imagenes/GIF_Extract.png"));

		avatar.setImagenDefault(icono);

		try {
			avatar.image = Toolkit.getDefaultToolkit().getImage(VerUser.class.getResource("/imagenes/GIF_Extract.png"));
			avatar.icono = new ImageIcon(VerUser.class.getResource("/imagenes/actualizar.png"));
			avatar.imagenIcon = icono;
		} catch (Exception e) {
			e.printStackTrace();
		}
		avatar.setColorBorde(new Color(11, 11, 122));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Acciones");
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.setIcon(new ImageIcon(VerUser.class.getResource("/imagenes/utilities.png")));
		menuBar.add(mnNewMenu);

		JMenu mnNewMenu_1 = new JMenu("Ver");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu_1.setIcon(new ImageIcon(VerUser.class.getResource("/imagenes/view.png")));
		mnNewMenu.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("Descargas");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new UserDescargas().setVisible(true);
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu_1.add(mntmNewMenuItem);

		JSeparator separator = new JSeparator();
		mnNewMenu_1.add(separator);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Tracking");
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new UserTracking().setVisible(true);

			}
		});
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JSeparator separator_1 = new JSeparator();
		mnNewMenu_1.add(separator_1);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Comentarios");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new UserComentarios().setVisible(true);

			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu_1.add(mntmNewMenuItem_1);

		t.scheduleAtFixedRate(mTask, 0, 500);

		this.setVisible(true);

	}

	public void setAvatar(RSFotoCircle avatar) {
		this.avatar = avatar;
	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JComboBox comboBox = new JComboBox();

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel label_2 = new JLabel("Numero de comentarios");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_2.setColumns(10);

		JLabel label_3 = new JLabel("Numero de descargas");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_1 = new JLabel("Nivel");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Idioma");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_3.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_1.setColumns(10);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Usuario invisible");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 16));

		JCheckBox checkBox = new JCheckBox("Permitir mensajes internos");
		checkBox.setFont(new Font("Tahoma", Font.BOLD, 16));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(40).addGroup(layout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
														174, Short.MAX_VALUE)
												.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 174,
														Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(avatar, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE).addGroup(
										layout.createSequentialGroup()
												.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 174,
														Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(Alignment.TRAILING,
								layout.createSequentialGroup().addContainerGap().addComponent(lblEmail).addGap(65)))
						.addGroup(
								Alignment.TRAILING,
								layout.createSequentialGroup().addContainerGap().addComponent(lblNewLabel_2)
										.addGap(53)))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addGroup(layout.createSequentialGroup().addGap(122).addComponent(lblNewLabel_3)
												.addContainerGap(184, Short.MAX_VALUE))
										.addGroup(layout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
												.addGroup(layout.createParallelGroup(Alignment.TRAILING)
														.addGroup(layout.createSequentialGroup()
																.addComponent(label_3, GroupLayout.DEFAULT_SIZE, 246,
																		Short.MAX_VALUE)
																.addContainerGap())
														.addGroup(layout.createSequentialGroup()
																.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 194,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(62)))))
								.addGroup(layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 142,
												GroupLayout.PREFERRED_SIZE)
										.addGap(94))
								.addGroup(layout
										.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblNewLabel_1).addGap(134))
								.addGroup(layout.createSequentialGroup().addGap(18).addGroup(layout
										.createParallelGroup(Alignment.LEADING).addComponent(chckbxNewCheckBox)
										.addComponent(checkBox, GroupLayout.PREFERRED_SIZE, 247,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 249,
												GroupLayout.PREFERRED_SIZE))
										.addGap(39)))
						.addGroup(layout.createSequentialGroup().addGap(72)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup().addGap(23).addComponent(avatar,
								GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(27).addComponent(lblNewLabel_1).addGap(11)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(chckbxNewCheckBox)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(checkBox, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
						.addGap(6).addComponent(lblNewLabel).addGap(31)
						.addGroup(layout
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(
										layout.createSequentialGroup()
												.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 20,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textField_2, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 20,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(
														textField_1, GroupLayout.PREFERRED_SIZE, 23,
														GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblEmail).addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(21).addComponent(lblNewLabel_2)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(textField_3,
												GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
						.addGap(42).addComponent(lblNewLabel_3)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(526, 477));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
