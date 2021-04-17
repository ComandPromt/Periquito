package periquito;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.TooManyListenersException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.DragAndDrop;
import utils.Metodos;

@SuppressWarnings("all")

public class NoteSha extends javax.swing.JFrame implements ActionListener, ChangeListener {

	JComboBox comboBox;

	JLabel totalImages;

	LinkedList<String> lista = new LinkedList<String>();

	public NoteSha() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(NoteSha.class.getResource("/imagenes/db.png")));

		setTitle("Periquito v3 Notes Sha");

		setType(Type.UTILITY);

		comboBox = new JComboBox();

		initComponents();

		try {
			Metodos.ponerCategoriasBd(comboBox);
		}

		catch (Exception e) {

		}

		this.setVisible(true);

	}

	private void openActionPerformed(java.awt.event.ActionEvent evt, boolean carpeta) {

		JFileChooser fc = new JFileChooser();

		LinkedList<File> files = new LinkedList<File>();

		if (carpeta) {

			fc.setCurrentDirectory(new java.io.File("."));

			fc.setDialogTitle("Elige una carpeta");

			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			fc.setAcceptAllFileFilterUsed(false);

		}

		int o = fc.showOpenDialog(getParent());

		if (o == JFileChooser.APPROVE_OPTION) {

			if (!carpeta) {

				String archivo = fc.getSelectedFile().toString();

				String extension = Metodos.extraerExtension(archivo);

				if (extension.equals("jpg") || extension.equals("png") || extension.equals("gif")) {
					files.add(new File(archivo));
				}

			}

			else {

				LinkedList<String> archivos = new LinkedList<String>();

				archivos = Metodos.directorio(fc.getSelectedFile().toString() + MenuPrincipal.getSeparador(), ".", true,
						true);

				for (int i = 0; i < archivos.size(); i++) {

					files.add(new File(archivos.get(i)));

				}

			}

		}

		String sha256 = "";

		for (int i = 0; i < files.size(); i++) {

			sha256 = Metodos.getSHA256Checksum(files.get(i).toString());

			if (!lista.contains(sha256)) {

				lista.add(sha256);

			}

		}

		totalImages.setText(lista.size() + " imagen/es");

	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		setResizable(false);

		JTextArea descripcion = new JTextArea("", 0, 20);

		JTextArea draganddrop = new JTextArea();

		draganddrop.setText("Arrastra los archivos aqui");

		draganddrop.setForeground(SystemColor.desktop);

		draganddrop.setFont(new Font("Tahoma", Font.PLAIN, 26));

		draganddrop.setEditable(false);

		draganddrop.setBackground(SystemColor.windowBorder);

		JScrollPane jScrollPane1 = new JScrollPane();

		jScrollPane1.setDoubleBuffered(true);

		JButton btnNewButton = new JButton("");

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				int resp = JOptionPane.showConfirmDialog(null, "¿Quieres borrar la descripción y las imágenes?");

				if (resp == 0) {

					descripcion.setText("");

					lista.clear();

				}

			}

		});

		btnNewButton.setIcon(new ImageIcon(NoteSha.class.getResource("/imagenes/clean.png")));

		JButton btnNewButton_1 = new JButton("");

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				try {

					String nota = descripcion.getText();

					nota = Metodos.eliminarEspacios(nota, false);

					if (!lista.isEmpty()) {

						Connection conexion = Metodos.conexionBD();

						ResultSet rs;

						Statement s = conexion.createStatement();

						for (int i = 0; i < lista.size(); i++) {

							rs = s.executeQuery("SELECT COUNT(Id) AS recuento FROM note_images_sha WHERE Sha='"
									+ lista.get(i) + "'");

							rs.next();

							if (Integer.parseInt(rs.getString("recuento")) == 0) {

								s.executeQuery("INSERT INTO note_images_sha(Sha,Categoria,Descripcion) VALUES('"
										+ lista.get(i) + "','"
										+ Metodos.saberIdCategoria(comboBox.getSelectedItem().toString()) + "','" + nota
										+ "')");
							}

						}

					}

				}

				catch (Exception e) {

				}

			}

		});

		btnNewButton_1.setIcon(new ImageIcon(NoteSha.class.getResource("/imagenes/save.png")));

		JButton btnNewButton_2 = new JButton("");

		btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ev) {
				openActionPerformed(ev, false);
			}

		});

		btnNewButton_2.setIcon(new ImageIcon(NoteSha.class.getResource("/imagenes/abrir.png")));

		JButton btnNewButton_2_1 = new JButton("");

		btnNewButton_2_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ev) {
				openActionPerformed(ev, true);
			}

		});

		btnNewButton_2_1.setIcon(new ImageIcon(NoteSha.class.getResource("/imagenes/folder.png")));

		totalImages = new JLabel("");

		totalImages.setFont(new Font("Dialog", Font.PLAIN, 20));

		totalImages.setHorizontalAlignment(SwingConstants.CENTER);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(27)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(totalImages, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jScrollPane1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
						.addComponent(draganddrop, Alignment.LEADING))
				.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(29)
								.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 54,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 54,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(20)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addGap(29).addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 56,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(42, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap(32, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(btnNewButton_2_1).addGroup(layout
						.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(draganddrop, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(108)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(27)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(btnNewButton_1)
										.addComponent(btnNewButton)))
						.addGroup(layout.createSequentialGroup().addGap(27).addComponent(jScrollPane1,
								GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)))
				.addGap(18).addComponent(totalImages).addGap(33)));

		jScrollPane1.setViewportView(descripcion);

		descripcion.setLineWrap(true);

		getContentPane().setLayout(layout);

		setSize(new Dimension(585, 479));

		setLocationRelativeTo(null);

		javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder("Drop 'em");

		try {

			new DragAndDrop(draganddrop, dragBorder, rootPaneCheckingEnabled, new DragAndDrop.Listener() {

				public void filesDropped(java.io.File[] files) {

					try {

						String sha256 = "";

						for (int i = 0; i < files.length; i++) {

							sha256 = Metodos.getSHA256Checksum(files[i].toString());

							if (!lista.contains(sha256)) {

								lista.add(sha256);

							}

						}

						totalImages.setText(lista.size() + " imagen/es");

					}

					catch (Exception e) {

						totalImages.setText("");

						descripcion.setText("");

						lista.clear();

					}

				}

			});

		}

		catch (TooManyListenersException e1) {
			Metodos.mensaje("Error al mover los archivos", 1);
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
