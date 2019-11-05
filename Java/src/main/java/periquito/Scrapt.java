package periquito;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utils.Metodos;
import utils.MyInterface;

@SuppressWarnings("all")

public class Scrapt extends javax.swing.JFrame implements ActionListener, ChangeListener, MyInterface {
	javax.swing.JLabel jLabel1;
	static javax.swing.JTextField jTextField1;
	private JTextField textField;
	JLabel lblThumbnails;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblDe;
	private JTextField textField_4;
	private JLabel lblHasta;
	private JTextField textField_5;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmNewMenuItem_2;
	private JCheckBox chckbxNewCheckBox;

	private static LinkedList<String> urls = new LinkedList<>();
	private static LinkedList<String> datos = new LinkedList<>();
	private static LinkedList<String> temporal = new LinkedList<>();

	@SuppressWarnings("all")
	public void buscarArchivoConf() throws IOException {

		File af = new File("Config/Config2.txt");

		if (af.exists()) {

			String[] lectura;

			try {

				lectura = Metodos.leerFicheroArray("Config/Config2.txt", 2);

				if (lectura[0] == null) {
					lectura[0] = "";
				}

				if (lectura[1] == null) {
					lectura[1] = "";
				}

				lectura[0] = Metodos.eliminarUltimoElemento(lectura[0]);
				lectura[1] = Metodos.eliminarUltimoElemento(lectura[1]);

				jTextField1.setText(lectura[0]);
				textField.setText(lectura[1]);
			}

			catch (ArrayIndexOutOfBoundsException e) {

			}

			guardarDatos(false);
		}
	}

	private static void extraerEnlaces(String cadena) {

		String residuo = "";

		int puntero = cadena.indexOf("http");

		int capacidad = 0;

		String cadenaEspacio = "";

		String dato = "";

		while (cadena.indexOf(" ") > 0) {

			if (puntero >= 0 && cadena.indexOf(" ") >= 0) {

				residuo = cadena.substring(0, cadena.indexOf(" "));

				if (residuo.contains("http")) {

					urls.add(cadena.substring(0, cadena.indexOf(" ")));

					if (capacidad > 0) {

						for (int i = 0; i < capacidad; i++) {
							cadenaEspacio += temporal.get(i) + " ";
						}

						datos.add(cadenaEspacio);
						capacidad = 0;
						temporal.clear();
						cadenaEspacio = "";
					}

					else {
						datos.add("Links");
					}
				}

				else {

					if (!residuo.contains("http")) {
						capacidad++;
						temporal.add(residuo);
					}

				}
			}

			cadena = cadena.substring(cadena.indexOf(" ") + 1, cadena.length());

		}

		if (!cadena.isEmpty() && cadena.indexOf("http") >= 0) {

			urls.add(cadena);
			datos.add("Links");

		}
	}

	public void guardarDatos(Boolean mensaje) throws IOException {
		FileWriter flS = new FileWriter("Config/Config2.txt");
		BufferedWriter fS = new BufferedWriter(flS);

		try {

			fS.write(jTextField1.getText().trim());
			fS.newLine();
			fS.write(textField.getText().trim());
			fS.close();
			flS.close();
			dispose();

			MenuPrincipal.setLecturaurl(Metodos.leerFicheroArray("Config/Config2.txt", 2));

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

	private void enlacePorDefecto(String location) {
		if (!location.isEmpty()) {
			datos.add("Link");
			urls.add(location);
		}
	}

	private String limpiarCadena(String urlObtenida) {

		if (urlObtenida.indexOf("\">") > 0 && urlObtenida.indexOf("</a>") > 0) {
			urlObtenida = urlObtenida.substring(urlObtenida.indexOf("\">") + 2, urlObtenida.indexOf("</a>"));
		}

		urlObtenida = urlObtenida.replace("</path>", "");
		urlObtenida = urlObtenida.replace("</svg>", "");
		urlObtenida = urlObtenida.replace("<span", "");
		urlObtenida = urlObtenida.replace("</span>", "");
		urlObtenida = urlObtenida.replace("<img>", "");
		urlObtenida = urlObtenida.replace("</img>", "");
		urlObtenida = urlObtenida.replace("itemscope", "");
		urlObtenida = urlObtenida.replace("</button>", "");
		urlObtenida = urlObtenida.replace("&nbsp;", "");
		urlObtenida = urlObtenida.replace("data-v-4992eadc", "");
		urlObtenida = urlObtenida.replace("data-v-4992eadcBlog", "");

		urlObtenida = urlObtenida.replaceAll("class=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("onmouseover=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("onmouseout=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("height=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("rel=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("width=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("itemprop=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("itemtype=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("itemprop=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("<figure>(.*)</figure>", "");
		urlObtenida = urlObtenida.replaceAll("target=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("style=(.*)\"", "");
		urlObtenida = urlObtenida.replaceAll("<p(.*)</p>", "");
		urlObtenida = urlObtenida.replaceAll("<svg(.*)>", "");
		urlObtenida = urlObtenida.replaceAll("<path(.*)>", "");

		urlObtenida = urlObtenida.replace("a href=\"", "");
		urlObtenida = urlObtenida.replace("\"  >", "");
		urlObtenida = urlObtenida.replace("<i ></i>", "");
		urlObtenida = urlObtenida.replace("<img >", "");
		urlObtenida = urlObtenida.replace(">", "");
		urlObtenida = urlObtenida.replace("<img", "");
		urlObtenida = urlObtenida.replace("data-v-23efff06", "");
		urlObtenida = urlObtenida.replace("<button", "");
		urlObtenida = urlObtenida.replace("»", "");
		urlObtenida = urlObtenida.replace("…", "");
		urlObtenida = urlObtenida.replace("???? ??????	", "");
		urlObtenida = urlObtenida.replace("src=//", "");
		urlObtenida = urlObtenida.replace("\"", "");
		urlObtenida = urlObtenida.replace("src=", "");
		urlObtenida = urlObtenida.replace("'", "");
		return urlObtenida;

	}

	public void obtenerEnlaces(String url) {

		try {

			Document doc = Jsoup.connect(url).ignoreHttpErrors(false).get();

			Elements elements = doc.select(".tbl-border tr:has(td.tbl1) + tr");

			String cadena = "";

			int y = 0;

			for (Element element : elements) {

				String location = element.previousElementSibling().select("td.tbl1").text();

				location = Metodos.eliminarEspacios(location);

				location = limpiarCadena(location);

				extraerEnlaces(location);

				y++;
			}

		}

		catch (Exception e) {
			//
		}

	}

	public Scrapt() throws IOException {
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Config2.class.getResource("/imagenes/config.png")));
		setTitle("Periquito v3 Config Remoto");
		setType(Type.UTILITY);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnNewMenu_1 = new JMenu("Acciones");
		mnNewMenu_1.setForeground(Color.BLACK);
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu_1.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/utilities.png")));
		menuBar.add(mnNewMenu_1);

		mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_2);

		mnNewMenu = new JMenu("Configurar");
		mnNewMenu.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/config.png")));
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnNewMenu);

		mntmNewMenuItem = new JMenuItem("Configuración");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/utilities.png")));
		mnNewMenu.add(mntmNewMenuItem);

		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);

		mntmNewMenuItem_1 = new JMenuItem("Crontab");
		mntmNewMenuItem_1.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/cron.png")));
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.add(mntmNewMenuItem_1);
		initComponents();
		this.setVisible(true);
	}

	@SuppressWarnings("all")
	public void initComponents() throws IOException {

		jTextField1 = new javax.swing.JTextField();
		jTextField1.setHorizontalAlignment(SwingConstants.LEFT);
		jTextField1.setToolTipText("");

		jLabel1 = new javax.swing.JLabel();
		jLabel1.setText(" URL");
		jLabel1.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/target.png")));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 24));

		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 20));

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						guardarDatos(true);
					} catch (IOException e1) {
						//
					}
				}
			}
		});
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setToolTipText("");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));

		buscarArchivoConf();

		JButton btnNewButton = new JButton("Scrapt!");
		btnNewButton.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/start.png")));

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				String url = jTextField1.getText();

				if (chckbxNewCheckBox.isSelected()) {

					for (int contador = 0; contador <= 860; contador += Integer.parseInt(textField_1.getText())) {

						url = url + contador;

						obtenerEnlaces(url);

					}
				}

				else {
					obtenerEnlaces(url);
				}

				try {

					int maximo = Metodos.saberMaximo("comments", "comment_id");

					Connection conexion = Metodos.conexionBD();

					Statement s;

					s = conexion.createStatement();

					for (int i = 0; i < urls.size(); i++) {

						s.executeUpdate("INSERT INTO " + MenuPrincipal.getLecturabd()[3] + "scrapting VALUES('" + maximo
								+ "','1329','1','Link','[URL]" + urls.get(i)
								+ "[/URL]','localhost','2019-10-04',DEFAULT)");
						maximo++;
					}

					s.executeUpdate("INSERT INTO " + MenuPrincipal.getLecturabd()[3]
							+ "comments (image_id,user_id,comment_headline,comment_text,comment_ip,comment_date)"
							+ " SELECT 29210,user_id,comment_headline,comment_text,comment_ip,comment_date" + " FROM "
							+ MenuPrincipal.getLecturabd()[3]
							+ "scrapting WHERE comment_text like '%http%' order by comment_id desc;");

					conexion.close();
				}

				catch (Exception e) {

					try {
						new Bd().setVisible(true);
					}

					catch (IOException e1) {
						//
					}
				}
			}
		});

		lblThumbnails = new JLabel("Etiqueta");
		lblThumbnails.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/tag.png")));
		lblThumbnails.setFont(new Font("Tahoma", Font.BOLD, 20));

		chckbxNewCheckBox = new JCheckBox(" Recorrer paginas?");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblNewLabel = new JLabel("Paso");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel label = new JLabel("Paso");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		JLabel label_1 = new JLabel("Paso");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_3 = new JTextField();
		textField_3.setColumns(10);

		lblDe = new JLabel("  De");
		lblDe.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_4 = new JTextField();
		textField_4.setColumns(10);

		lblHasta = new JLabel("Hasta");
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_5 = new JTextField();
		textField_5.setColumns(10);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(28)
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addGap(54)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 36,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 36,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 36,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addComponent(chckbxNewCheckBox)
												.addGroup(layout.createSequentialGroup()
														.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
																.addComponent(textField_1, Alignment.LEADING, 0, 0,
																		Short.MAX_VALUE)
																.addComponent(lblNewLabel, Alignment.LEADING))
														.addGap(32)
														.addGroup(layout.createParallelGroup(Alignment.LEADING)
																.addComponent(lblDe, GroupLayout.PREFERRED_SIZE, 36,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(textField_4, GroupLayout.PREFERRED_SIZE,
																		36, GroupLayout.PREFERRED_SIZE))
														.addGap(18)
														.addGroup(layout.createParallelGroup(Alignment.LEADING)
																.addComponent(textField_5, GroupLayout.PREFERRED_SIZE,
																		46, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblHasta, GroupLayout.PREFERRED_SIZE, 62,
																		GroupLayout.PREFERRED_SIZE))))
										.addGap(82).addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 177,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblThumbnails)
										.addComponent(jLabel1))
								.addGap(18)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
										.addComponent(jTextField1, 356, 356, 356))
								.addGap(83)))));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(jTextField1,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(38)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblThumbnails).addComponent(
						textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.LEADING, false).addGroup(layout.createSequentialGroup()
						.addComponent(chckbxNewCheckBox).addGap(18)
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout
								.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel).addComponent(lblDe,
														GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(textField_1,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(25).addComponent(textField_4,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup()
										.addComponent(lblHasta, GroupLayout.PREFERRED_SIZE, 19,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(textField_5,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))))
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addGap(271).addComponent(label_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE).addGap(6)
				.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(113).addComponent(label, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE).addGap(6)
				.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)));
		getContentPane().setLayout(layout);
		setSize(new Dimension(601, 404));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}