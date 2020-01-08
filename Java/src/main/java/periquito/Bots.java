package periquito;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("all")

public class Bots extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	private JTextField textField;
	JLabel lblThumbnails;
	private JButton button_1;
	private JLabel lblNewLabel;
	private JButton button_5;
	private JButton button_4;
	private JButton button_2;
	private JButton button_3;
	private JList<String> list;

	@SuppressWarnings("all")
	public void buscarArchivoConf() throws IOException {
		File af = new File("Config/Config2.txt");

		if (af.exists()) {
			String[] lectura;
			try {
				lectura = Metodos.leerFicheroArray("Config2.txt", 2);

				if (lectura[0] == null) {
					lectura[0] = "";
				}
				if (lectura[1] == null) {
					lectura[1] = "";
				}

				lectura[0] = Metodos.eliminarUltimoElemento(lectura[0]);
				lectura[1] = Metodos.eliminarUltimoElemento(lectura[1]);

				textField.setText(lectura[1]);
			} catch (ArrayIndexOutOfBoundsException e) {

			}
			guardarDatos(false);
		}
	}

	public void guardarDatos(Boolean mensaje) throws IOException {
		FileWriter flS = new FileWriter("Config/Config2.txt");
		BufferedWriter fS = new BufferedWriter(flS);

		try {

			fS.newLine();
			fS.write(textField.getText().trim());
			fS.close();
			flS.close();
			dispose();

			MenuPrincipal.setLecturaurl(Metodos.leerFicheroArray("Config2.txt", 2));

			if (mensaje) {

				Metodos.mensaje("Archivo guardado con exito!", 2);
			}

		} catch (IOException e) {
			if (mensaje) {
				Metodos.mensaje("Error al crear el fichero de configuracion", 1);
			}
		} finally {
			fS.close();
			flS.close();
		}
	}

	public Bots() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Bots.class.getResource("/imagenes/bot.png")));
		setTitle("Periquito v3 Bots");
		initComponents();
		this.setVisible(true);
	}

	@SuppressWarnings("all")
	public void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setToolTipText("");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));

		buscarArchivoConf();

		lblThumbnails = new JLabel("Carpeta de salida");
		lblThumbnails.setIcon(new ImageIcon(Config2.class.getResource("/imagenes/folder.png")));
		lblThumbnails.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton button = new JButton("");

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				File[] files = Metodos.seleccionar(1, "Elija la carpeta del CMS", "");

				try {
					String texto = files[0].getCanonicalPath();
					textField.setText(texto);

				} catch (Exception e1) {
					Metodos.mensaje("Error al guardar la configuración", 1);
				}
			}
		});

		button.setIcon(new ImageIcon(Config2.class.getResource("/imagenes/abrir.png")));

		button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(Bots.class.getResource("/imagenes/txt.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		lblNewLabel = new JLabel("Exportar bots");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setIcon(new ImageIcon(Bots.class.getResource("/imagenes/bot.png")));

		button_5 = new JButton("Copiar al portapapeles");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_5.setFont(new Font("Tahoma", Font.BOLD, 16));

		button_4 = new JButton("");
		button_4.setIcon(new ImageIcon(Bots.class.getResource("/imagenes/save.png")));

		button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_2.setIcon(new ImageIcon(Bots.class.getResource("/imagenes/pdf.png")));

		button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_3.setIcon(new ImageIcon(Bots.class.getResource("/imagenes/excel.png")));

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setIcon(new ImageIcon(Bots.class.getResource("/imagenes/insert.png")));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout
								.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE).addGap(15))
						.addComponent(button_5, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(12).addComponent(lblThumbnails))
						.addGroup(layout.createSequentialGroup().addGap(34).addGroup(layout
								.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
								.addGroup(layout.createSequentialGroup()
										.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 63,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(button_3, GroupLayout.PREFERRED_SIZE, 66,
												GroupLayout.PREFERRED_SIZE))))
						.addGroup(layout.createSequentialGroup().addGap(6).addGroup(layout
								.createParallelGroup(Alignment.TRAILING)
								.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup()
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 238,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(button,
												GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))))
						.addGroup(layout.createSequentialGroup().addGap(103).addComponent(btnNewButton,
								GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
				.addGap(35)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addComponent(lblThumbnails).addGap(10)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(button_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(button_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(button_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnNewButton))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
				.addGap(73)));

		list = new JList<String>();
		list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		list.setFixedCellHeight(40);
		list.setBackground(new Color(254, 254, 254));
		scrollPane.setViewportView(list);
		getContentPane().setLayout(layout);
		setSize(new Dimension(570, 507));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}