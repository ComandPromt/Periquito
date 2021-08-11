package periquito;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import utils.Metodos;
import utils.MetodosPdf;

@SuppressWarnings("all")
public class VerUrl extends javax.swing.JFrame implements ActionListener, ChangeListener {

	JComboBox comboBox = new JComboBox();

	String url;
	private JTextField textField;

	JList list;

	LinkedList<String> urls = new LinkedList();

	static DefaultListModel<String> modelo = new DefaultListModel<>();

	public VerUrl() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VerUrl.class.getResource("/imagenes/remote.png")));
		setTitle("Periquito v5 - Ver URLs ");
		setType(Type.POPUP);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Acciones");
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/utilities.png")));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Copiar lista");
		mntmNewMenuItem.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/copy.png")));
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.add(mntmNewMenuItem);

		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Meter URL en .txt");
		mntmNewMenuItem_8.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/nota.png")));
		mntmNewMenuItem_8.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.add(mntmNewMenuItem_8);

		JSeparator separator_6 = new JSeparator();
		mnNewMenu.add(separator_6);

		JMenu mnExportarLista = new JMenu("Exportar lista");
		mnExportarLista.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/config.png")));
		mnExportarLista.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.add(mnExportarLista);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("PDF");
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (urls.isEmpty()) {
					Metodos.mensaje("Por favor, inserte una URL y vea sus enlaces", 3);
				}

				else {

					try {

						MetodosPdf.crearPdf(urls, url, "template-verurl.html");

					} catch (Exception e1) {
						//
					}
				}
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/pdf.png")));
		mnExportarLista.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Excel");
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (!urls.isEmpty()) {

					LinkedList<String> leyenda = new LinkedList<String>();

					leyenda.add("Datos");

					try {

						Metodos.generarExcel("Hoja1", urls, leyenda);

						Metodos.mensaje("Lista exportada correctamente", 2);

					} catch (IOException e1) {
						Metodos.mensaje("Error al exportar la lista", 1);
					}

				}

				else {
					Metodos.mensaje("Por favor, inserte una URL y vea sus enlaces", 3);
				}
			}
		});

		JSeparator separator_1 = new JSeparator();
		mnExportarLista.add(separator_1);
		mntmNewMenuItem_2.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/excel.png")));
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnExportarLista.add(mntmNewMenuItem_2);

		JSeparator separator_2 = new JSeparator();
		mnExportarLista.add(separator_2);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Txt");
		mntmNewMenuItem_4.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/txt.png")));
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnExportarLista.add(mntmNewMenuItem_4);

		JMenu mnNewMenu_1 = new JMenu("Ver");
		mnNewMenu_1.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/view.png")));
		mnNewMenu_1.setForeground(Color.BLACK);
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("PDF");
		mntmNewMenuItem_3.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/pdf.png")));
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JSeparator separator_3 = new JSeparator();
		mnNewMenu_1.add(separator_3);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Excel");
		mntmNewMenuItem_5.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/excel.png")));
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu_1.add(mntmNewMenuItem_5);

		JSeparator separator_4 = new JSeparator();
		mnNewMenu_1.add(separator_4);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Txt");
		mntmNewMenuItem_6.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/txt.png")));
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu_1.add(mntmNewMenuItem_6);

		initComponents();

		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));

		comboBox.addItem("Todo");

		comboBox.addItem("URLs favoritas");

		comboBox.addItem("URLs analizadas");

		String urlTarget = Scrapt.jTextField1.getText();

		if (!urlTarget.isEmpty()) {

			textField.setText(Metodos.eliminarEspacios(urlTarget, false));
		}

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

		JLabel lblInsertarUrl = new JLabel("URL");
		lblInsertarUrl.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/target.png")));
		lblInsertarUrl.setFont(new Font("Tahoma", Font.BOLD, 20));

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JButton btnNewButton_1 = new JButton("");

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				modelo.clear();

				url = Metodos.eliminarEspacios(textField.getText(), false);

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
		button.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VerUrl.class.getResource("/imagenes/filtro.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Metodos.mensaje("Abrir carpeta de archivos exportados", 2);
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Buscador de archivos exportados");

		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
				layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(19)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(
												scrollPane, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(layout.createSequentialGroup().addGroup(layout
										.createParallelGroup(Alignment.TRAILING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(button, GroupLayout.PREFERRED_SIZE, 72,
														GroupLayout.PREFERRED_SIZE)
												.addGap(47))
										.addComponent(lblInsertarUrl)).addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addGroup(layout.createSequentialGroup()
														.addComponent(textField, GroupLayout.DEFAULT_SIZE, 329,
																Short.MAX_VALUE)
														.addGap(18).addComponent(btnNewButton_1,
																GroupLayout.PREFERRED_SIZE, 70,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup().addComponent(btnNewButton)
																.addPreferredGap(ComponentPlacement.RELATED, 49,
																		Short.MAX_VALUE)
																.addComponent(lblNewLabel).addGap(18)
																.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 197,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)))
										.addGap(23))))
						.addGroup(layout.createSequentialGroup().addGap(55).addComponent(lblNewLabel_1)
								.addContainerGap(481, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(14)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(btnNewButton_1)
						.addGroup(layout
								.createSequentialGroup()
								.addGroup(layout
										.createParallelGroup(Alignment.BASELINE).addComponent(lblInsertarUrl)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)))
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(button)
										.addComponent(lblNewLabel))
								.addGap(9))
						.addGroup(layout.createSequentialGroup().addGap(18)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(layout.createSequentialGroup().addGap(30).addComponent(btnNewButton)
								.addPreferredGap(ComponentPlacement.RELATED)))
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(lblNewLabel_1).addGap(38)));

		list = new JList();

		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				try {

					String urlSeleccionada = list.getSelectedValue().toString();

					if (Metodos.probarConexion(urlSeleccionada)) {

						Scrapt.setjTextField1(Metodos.eliminarEspacios(textField.getText(), false));

					}

					else {
						Metodos.mensaje(" La URL no existe", 3);
					}

				}

				catch (Exception e1) {
					//
				}

			}

		});

		list.setFont(new Font("Tahoma", Font.PLAIN, 16));

		scrollPane.setViewportView(list);

		getContentPane().setLayout(layout);

		setSize(new Dimension(588, 544));

		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public void stateChanged(ChangeEvent e) {

	}

}
