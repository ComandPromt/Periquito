package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;

@SuppressWarnings("all")
public class ConfiguracionSonido extends javax.swing.JFrame implements ActionListener, ChangeListener {

	JCheckBox checkBox_1;
	JComboBox comboBox;
	static String[] sonido;
	public static LinkedList<String> sonidos = new LinkedList<>();

	public ConfiguracionSonido() throws IOException {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						guardarDatos(true);
					}

					catch (IOException e1) {
						//
					}
				}
			}
		});

		setIconImage(Toolkit.getDefaultToolkit().getImage(ConfiguracionSonido.class.getResource("/imagenes/wav.png")));
		setTitle("Periquito v3 Conf.Sonido");
		setType(Type.UTILITY);

		initComponents();

		sonido = Metodos.leerArchivo("sonido.txt", 2, "gong.wav\r\n" + "1", false);

		if (sonido[1].equals("1")) {

			checkBox_1.setSelected(true);
		}

		sonidos = Metodos.directorio(MenuPrincipal.directorioActual + "sonidos" + MenuPrincipal.getSeparador(), "wav",
				1);

		sonidos.remove("advertencia.wav");

		sonidos.remove("duck-quack.wav");

		for (int i = 0; i < sonidos.size(); i++) {
			comboBox.addItem(sonidos.get(i));
		}

		this.setVisible(true);

	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JButton button = new JButton("Sonidos");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Metodos.abrirCarpeta(MenuPrincipal.getDirectorioActual() + "sonidos");
				}

				catch (IOException e1) {
					Metodos.mensaje("Error", 1);
				}
			}
		});

		button.setIcon(new ImageIcon(ConfiguracionSonido.class.getResource("/imagenes/folder.png")));
		button.setFont(new Font("Tahoma", Font.BOLD, 16));

		comboBox = new JComboBox();

		JButton button_1 = new JButton("Play");
		button_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Metodos.reproducirSonido(
						MenuPrincipal.getDirectorioActual() + "sonidos/" + comboBox.getSelectedItem().toString(),
						false);
			}

		});

		button_1.setIcon(new ImageIcon(ConfiguracionSonido.class.getResource("/imagenes/start.png")));

		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(ConfiguracionSonido.class.getResource("/imagenes/wav.png")));
		label.setText("Sonido");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 24));

		checkBox_1 = new JCheckBox("Activar sonido");
		checkBox_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton button_2 = new JButton("");

		button_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					guardarDatos(true);
				}

				catch (IOException e1) {
					//
				}

			}
		});

		button_2.setIcon(new ImageIcon(ConfiguracionSonido.class.getResource("/imagenes/save.png")));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(199).addComponent(label,
								GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(15).addGroup(layout
								.createParallelGroup(Alignment.LEADING)
								.addComponent(checkBox_1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup()
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 248,
												GroupLayout.PREFERRED_SIZE)
										.addGap(37).addComponent(button_1, GroupLayout.PREFERRED_SIZE, 76,
												GroupLayout.PREFERRED_SIZE)))))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
				.addGap(19)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 46, Short.MAX_VALUE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(26).addComponent(button_2,
										GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(36).addComponent(checkBox_1,
										GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
						.addGap(26)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(595, 297));
		setLocationRelativeTo(null);
	}

	public void guardarDatos(Boolean mensaje) throws IOException {

		FileWriter flS = new FileWriter("Config/sonido.txt");

		BufferedWriter fS = new BufferedWriter(flS);

		try {

			fS.write(comboBox.getSelectedItem().toString());
			fS.newLine();

			if (checkBox_1.isSelected()) {
				fS.write("1");
			}

			else {
				fS.write("0");
			}

			fS.close();
			flS.close();

			dispose();

			MenuPrincipal.setSonido(Metodos.leerFicheroArray("sonido.txt", 2));

		}

		catch (IOException e) {

			if (mensaje) {
				Metodos.mensaje("Error al crear el fichero de configuracion", 1);
			}

		}

		finally {
			fS.close();
			flS.close();
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
