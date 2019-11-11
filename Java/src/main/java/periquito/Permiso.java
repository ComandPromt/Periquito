package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.Metodos;

@SuppressWarnings("all")

public class Permiso extends javax.swing.JFrame implements ActionListener, ChangeListener {

	private JTextField nvDescarga;
	private JTextField nvComentario;
	private JTextField visibilidad;
	private JCheckBox chckbxNewCheckBox;

	static String[] permisos;

	public Permiso() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(Permiso.class.getResource("/imagenes/key.png")));

		setTitle("Periquito v3 Permisos");

		initComponents();

		permisos = Metodos.leerFicheroArray("Config/Permisos.txt", 4);

		try {

			if (permisos.length == 4) {

				nvDescarga.setText(permisos[0]);
				nvComentario.setText(permisos[1]);
				visibilidad.setText(permisos[2]);

				if (permisos[3].equals("1")) {
					chckbxNewCheckBox.setSelected(true);

				} else {
					chckbxNewCheckBox.setSelected(false);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setVisible(true);

	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JLabel lblNewLabel = new JLabel("Nivel descarga");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setIcon(new ImageIcon(Permiso.class.getResource("/imagenes/download.png")));

		JLabel lblNewLabel_1 = new JLabel("Nivel comentario");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setIcon(new ImageIcon(Permiso.class.getResource("/imagenes/name.png")));

		JLabel lblNewLabel_2 = new JLabel("Visibilidad");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setIcon(new ImageIcon(Permiso.class.getResource("/imagenes/view.png")));

		nvDescarga = new JTextField();
		nvDescarga.setFont(new Font("Tahoma", Font.BOLD, 16));
		nvDescarga.setHorizontalAlignment(SwingConstants.CENTER);
		nvDescarga.setColumns(10);

		nvComentario = new JTextField();
		nvComentario.setFont(new Font("Tahoma", Font.BOLD, 16));
		nvComentario.setHorizontalAlignment(SwingConstants.CENTER);
		nvComentario.setColumns(10);

		visibilidad = new JTextField();
		visibilidad.setFont(new Font("Tahoma", Font.BOLD, 16));
		visibilidad.setHorizontalAlignment(SwingConstants.CENTER);
		visibilidad.setColumns(10);

		JButton button = new JButton("");

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					String nivelDescarga = Metodos.eliminarEspacios(nvDescarga.getText());

					String nivelComentario = Metodos.eliminarEspacios(nvComentario.getText());

					String nivelVisibilidad = Metodos.eliminarEspacios(visibilidad.getText());

					int numDescarga = Integer.parseInt(nivelDescarga);

					int numComentario = Integer.parseInt(nivelComentario);

					int numVisibilidad = Integer.parseInt(nivelVisibilidad);

					if (nivelDescarga.length() == 1 && numDescarga >= 1 && nivelComentario.length() == 1

							&& numComentario >= 1 && nivelVisibilidad.length() == 1 && numVisibilidad >= 1) {

						if (numDescarga > 2) {

							numDescarga = 2;

							nvDescarga.setText("" + numDescarga);
						}

						if (numDescarga <= 0) {

							numDescarga = 1;

							nvDescarga.setText("" + numDescarga);
						}

						if (numComentario > 3) {

							numComentario = 3;

							nvComentario.setText("" + numComentario);
						}

						if (numComentario <= 0) {

							numComentario = 1;

							nvComentario.setText("" + numComentario);
						}

						if (numVisibilidad > 3) {

							numVisibilidad = 3;

							visibilidad.setText("" + numVisibilidad);
						}

						if (numVisibilidad <= 0) {

							numVisibilidad = 1;

							visibilidad.setText("" + numVisibilidad);
						}

						dispose();

						guardarDatos(true);

					}

				} catch (Exception e1) {

					nvDescarga.setText("2");

					nvComentario.setText("1");

					visibilidad.setText("1");

					dispose();

					try {
						guardarDatos(true);
					} catch (IOException e2) {
						//
					}
				}
			}

		});

		button.setIcon(new ImageIcon(Permiso.class.getResource("/imagenes/save.png")));

		chckbxNewCheckBox = new JCheckBox("Permitir comentario");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 18));

		JTextPane txtpnNivel = new JTextPane();
		txtpnNivel.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnNivel.setText(
				"- Nivel 1: Admite Todo\r\n\r\n- Visibilidad Nivel 1: Público\r\n\r\n- Nivel 2: Usuario registrados\r\n\r\n- Visibilidad Nivel 3: Sólo para el usuario que ha subido la imagen");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(10)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(chckbxNewCheckBox)
						.addComponent(txtpnNivel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(layout.createSequentialGroup().addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(nvDescarga, GroupLayout.PREFERRED_SIZE, 103,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(
										layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel_1).addComponent(lblNewLabel_2))
												.addGap(58)
												.addGroup(layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(nvComentario, GroupLayout.PREFERRED_SIZE, 102,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(visibilidad, GroupLayout.PREFERRED_SIZE, 99,
																GroupLayout.PREFERRED_SIZE)))))
				.addGap(18).addComponent(button, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(34, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nvDescarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
				.addGap(13).addComponent(chckbxNewCheckBox).addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1).addComponent(
						nvComentario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(29)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_2).addComponent(
						visibilidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(txtpnNivel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addContainerGap(45, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup().addContainerGap(342, Short.MAX_VALUE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE).addGap(65)));

		getContentPane().setLayout(layout);
		setSize(new Dimension(591, 482));
		setLocationRelativeTo(null);
	}

	public void guardarDatos(Boolean mensaje) throws IOException {

		FileWriter flS = new FileWriter("Config/Permisos.txt");

		BufferedWriter fS = new BufferedWriter(flS);

		try {

			fS.write(nvDescarga.getText().trim());

			fS.newLine();

			fS.write(nvComentario.getText().trim());

			fS.newLine();

			fS.write(visibilidad.getText().trim());

			fS.newLine();

			if (chckbxNewCheckBox.isSelected()) {
				fS.write("1");
			}

			else {
				fS.write("0");
			}

			fS.close();

			flS.close();

			dispose();

			MenuPrincipal.setPermisos(Metodos.leerFicheroArray("Config/Permisos.txt", 4));

			if (mensaje) {

				Metodos.mensaje("Archivo guardado con exito!", 2);
			}

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

	@Override
	public void stateChanged(ChangeEvent e) {
		//
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//
	}
}
