package periquito;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
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
import utils.MetodosPdf;

@SuppressWarnings("all")
public class VerUrl extends javax.swing.JFrame implements ActionListener, ChangeListener {
	String url;
	private JTextField textField;

	JList list;

	LinkedList<String> urls = new LinkedList();

	static DefaultListModel<String> modelo = new DefaultListModel<>();

	public VerUrl() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/imagenes/about.png")));
		setTitle("Periquito v3 About");
		setType(Type.POPUP);
		initComponents();
		this.setVisible(true);
	}

	private static Clipboard getSystemClipboard() {

		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();

		Clipboard systemClipboard = defaultToolkit.getSystemClipboard();

		return systemClipboard;
	}

	public static String get() throws Exception {

		Clipboard systemClipboard = getSystemClipboard();

		DataFlavor dataFlavor = DataFlavor.stringFlavor;

		if (systemClipboard.isDataFlavorAvailable(dataFlavor)) {

			Object text = systemClipboard.getData(dataFlavor);

			return (String) text;
		}

		return null;
	}

	public static void copy(String text) {

		Clipboard clipboard = getSystemClipboard();

		clipboard.setContents(new StringSelection(text), null);
	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		JLabel lblInsertarUrl = new JLabel("Insertar URL");
		lblInsertarUrl.setFont(new Font("Tahoma", Font.BOLD, 20));

		textField = new JTextField();
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JButton btnNewButton = new JButton("Poner en el target");

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					String urlSeleccionada = list.getSelectedValue().toString();
					Scrapt.jTextField1.setText(urlSeleccionada);
					Metodos.mensaje("La URL ha sido copiada para hacer scrapting", 2);
				}

				catch (NullPointerException e1) {
					Metodos.mensaje("Por favor, seleccione una URL", 2);
				}
			}

		});
		btnNewButton.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/insert.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel = new JLabel("Coger URL para hacer scrapting");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnInsertarUrlEn = new JButton("Meter URL en .txt");
		btnInsertarUrlEn.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/nota.png")));
		btnInsertarUrlEn.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btnLimpiarArchivotxt = new JButton("Limpiar archivo .txt");
		btnLimpiarArchivotxt.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/name.png")));
		btnLimpiarArchivotxt.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btnNewButton_1 = new JButton("");

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				url = Metodos.eliminarEspacios(textField.getText());

				urls = Metodos.obtenerEnlaces(url, 1, "", "", 0);

				if (!urls.isEmpty()) {

					for (int i = 0; i < urls.size(); i++) {

						modelo.addElement(urls.get(i));

					}

					list.setModel(modelo);
				}

				else {
					Metodos.mensaje("No se han encontrado enlaces en : " + url, 2);
				}

			}

		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/view.png")));

		JButton button = new JButton("");
		button.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/copy.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					copy(list.getSelectedValue().toString());
				} catch (Exception e1) {
					//
				}
			}

		});

		button.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton button_1 = new JButton("");

		button_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					MetodosPdf.crearPdf(urls, url, "template-verurl.html");

				} catch (FileNotFoundException e1) {
					Metodos.mensaje("Por favor, elimine el archivo pdf generado anteriormente y genere el pdf de nuevo",
							3);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_1.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/pdf.png")));
		button_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/excel.png")));
		button_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(19)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addComponent(lblInsertarUrl).addGap(18)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(layout.createSequentialGroup()
												.addComponent(button, GroupLayout.PREFERRED_SIZE, 58,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 58,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 58,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 70,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 272,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnLimpiarArchivotxt, GroupLayout.DEFAULT_SIZE, 260,
												Short.MAX_VALUE)
										.addComponent(btnInsertarUrlEn, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
										.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))))
				.addGap(68)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(23)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblInsertarUrl)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
						.addGap(18)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(btnNewButton)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnInsertarUrlEn))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton_1)
										.addComponent(btnLimpiarArchivotxt))
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGap(16)));

		list = new JList();
		scrollPane.setViewportView(list);
		getContentPane().setLayout(layout);
		setSize(new Dimension(615, 381));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}
