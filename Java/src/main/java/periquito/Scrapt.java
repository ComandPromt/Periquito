package periquito;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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
	private JTextField textField_2;
	private JLabel lblDe;
	private JTextField textField_4;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmNewMenuItem_2;

	private static LinkedList<String> urls = new LinkedList<>();
	private static LinkedList<String> datos = new LinkedList<>();
	private static LinkedList<String> temporal = new LinkedList<>();
	private JButton btnNewButton_2;
	private JMenuItem mntmNewMenuItem_5;
	private JMenu mnImport;
	private JMenuItem mntmNewMenuItem_6;
	private JMenu mnNewMenu_3;
	private JMenuItem mntmNewMenuItem_7;
	private JMenuItem mntmNewMenuItem_8;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private JMenuItem mntmNewMenuItem_9;
	private JSeparator separator_5;
	private JSeparator separator_6;
	private JLabel lblNewLabel_1;
	private JTextField textField_6;
	private JLabel label;
	private JLabel label_2;
	private JTextField textField_5;
	private JTextField textField_1;
	private JCheckBox checkBox;
	private JLabel lblNewLabel_2;
	private JTextField txtHttp;

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

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnNewMenu_1 = new JMenu("Acciones");
		mnNewMenu_1.setForeground(Color.BLACK);
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu_1.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/utilities.png")));
		menuBar.add(mnNewMenu_1);

		JMenu mnNewMenu_2 = new JMenu("Exportar URLs de la BD");
		mnNewMenu_2.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/bd.png")));
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu_1.add(mnNewMenu_2);

		mntmNewMenuItem_2 = new JMenuItem("Copiar al portapapeles");
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnNewMenu_2.add(mntmNewMenuItem_2);

		JSeparator separator_1 = new JSeparator();
		mnNewMenu_2.add(separator_1);

		mntmNewMenuItem_5 = new JMenuItem("Exportar a txt");
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnNewMenu_2.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exportar a pdf");
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnNewMenu_2.add(mntmNewMenuItem_3);

		JSeparator separator_2 = new JSeparator();
		mnNewMenu_2.add(separator_2);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Exportar a excel");
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnNewMenu_2.add(mntmNewMenuItem_4);

		separator_5 = new JSeparator();
		mnNewMenu_1.add(separator_5);

		mnImport = new JMenu("Importar URL");
		mnImport.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/remote.png")));
		mnImport.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu_1.add(mnImport);

		mntmNewMenuItem_6 = new JMenuItem("Desde archivo txt");
		mntmNewMenuItem_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new ImportarUrlScrapting().setVisible(true);
			}
		});
		mnImport.add(mntmNewMenuItem_6);

		separator_3 = new JSeparator();
		mnImport.add(separator_3);

		mnNewMenu_3 = new JMenu("BD");
		mnImport.add(mnNewMenu_3);

		mntmNewMenuItem_7 = new JMenuItem("Última URL escaneada");
		mnNewMenu_3.add(mntmNewMenuItem_7);

		separator_4 = new JSeparator();
		mnNewMenu_3.add(separator_4);

		mntmNewMenuItem_8 = new JMenuItem("Seleccionar URL");
		mnNewMenu_3.add(mntmNewMenuItem_8);

		separator_6 = new JSeparator();
		mnNewMenu_1.add(separator_6);

		mntmNewMenuItem_9 = new JMenuItem("Ver URLs");
		mntmNewMenuItem_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new VerUrl().setVisible(true);
			}
		});
		mntmNewMenuItem_9.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/view.png")));
		mntmNewMenuItem_9.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu_1.add(mntmNewMenuItem_9);

		mnNewMenu = new JMenu("Configurar");
		mnNewMenu.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/config.png")));
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnNewMenu);

		mntmNewMenuItem = new JMenuItem("Configuración");

		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new ConfigScrapting().setVisible(true);
			}
		});

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
		jTextField1.setHorizontalAlignment(SwingConstants.CENTER);
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
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setToolTipText("");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));

		buscarArchivoConf();

		lblThumbnails = new JLabel("Etiqueta");
		lblThumbnails.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/tag.png")));
		lblThumbnails.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblNewLabel = new JLabel("Paso");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		JLabel label_1 = new JLabel("Paso");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));

		lblDe = new JLabel("  De");
		lblDe.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setColumns(10);

		btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/remote.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Abrir navegador con la URL de target
			}
		});

		JButton button = new JButton("Scrapt!");

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					int imagen = Integer.parseInt(textField_6.getText());

					int de = Integer.parseInt(textField_4.getText());

					int hasta = Integer.parseInt(textField_5.getText());

					int paso = Integer.parseInt(textField_1.getText());

					if (imagen > 0 && de > 0 && hasta < 0 && paso > 0) {

						for (int contador = de; contador <= hasta; contador += paso) {

							obtenerEnlaces(Metodos.eliminarEspacios(jTextField1.getText() + contador));

						}

						int maximo = Metodos.saberMaximo("comments", "comment_id");

						Connection conexion = Metodos.conexionBD();

						Statement s;

						s = conexion.createStatement();

						String etiqueta = Metodos.eliminarEspacios(textField.getText());

						int recuento = 0;

						for (int i = 0; i < urls.size(); i++) {

							ResultSet rs = s
									.executeQuery("SELECT COUNT(comment_id) FROM " + MenuPrincipal.getLecturabd()[3]
											+ "scrapting WHERE comment_text='[URL]" + urls.get(i) + "[/URL]'");

							rs.next();

							recuento = Integer.parseInt(rs.getString("count(comment_id)"));

							if (recuento == 0) {

								s.executeUpdate("INSERT INTO " + MenuPrincipal.getLecturabd()[3] + "scrapting VALUES('"
										+ maximo + "','" + imagen + "','1','Link','[URL]" + urls.get(i)
										+ "[/URL]','localhost','2019-10-04',DEFAULT,'" + etiqueta + "')");
								maximo++;
							}
						}

						s.executeUpdate("INSERT INTO " + MenuPrincipal.getLecturabd()[3]
								+ "comments (image_id,user_id,comment_headline,comment_text,comment_ip,comment_date) SELECT '"
								+ imagen + "',user_id,comment_headline,comment_text,comment_ip,comment_date FROM "
								+ MenuPrincipal.getLecturabd()[3] + "scrapting WHERE comment_text like '%"
								+ txtHttp.getText() + "%' AND tag='" + etiqueta + "' ORDER BY comment_id ASC");

						conexion.close();

						Metodos.mensaje("Se han insertado " + urls.size() + " URLs!", 2);

					}

					else {
						Metodos.mensaje("Por favor, inserta números en los campos requeridos", 3);
					}

					urls.clear();

					datos.clear();

				}

				catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		button.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/start.png")));

		lblNewLabel_1 = new JLabel("Imagen ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setIcon(new ImageIcon(Scrapt.class.getResource("/imagenes/crop.png")));

		textField_6 = new JTextField();
		textField_6.setToolTipText("");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 24));

		label = new JLabel("Paso");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));

		label_2 = new JLabel("Hasta");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_5.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_1.setColumns(10);

		checkBox = new JCheckBox(" Recorrer paginas?");
		checkBox.setFont(new Font("Tahoma", Font.BOLD, 16));

		lblNewLabel_2 = new JLabel("Filtro");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));

		txtHttp = new JTextField();
		txtHttp.setText("http");
		txtHttp.setHorizontalAlignment(SwingConstants.CENTER);
		txtHttp.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtHttp.setColumns(10);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(28)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout
								.createParallelGroup(Alignment.LEADING).addComponent(
										lblNewLabel_1)
								.addComponent(jLabel1).addComponent(lblThumbnails))
						.addComponent(checkBox, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addGap(14).addGroup(layout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGroup(layout
										.createParallelGroup(Alignment.TRAILING)
										.addComponent(button, GroupLayout.PREFERRED_SIZE, 193, Short.MAX_VALUE)
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblDe, GroupLayout.PREFERRED_SIZE, 36,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 36,
																GroupLayout.PREFERRED_SIZE))
												.addGap(36)
												.addGroup(layout.createParallelGroup(Alignment.LEADING)
														.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 53,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 46,
																GroupLayout.PREFERRED_SIZE))
												.addGap(32)
												.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(textField_1, 0, 0, Short.MAX_VALUE)
														.addComponent(label, GroupLayout.PREFERRED_SIZE, 36,
																GroupLayout.PREFERRED_SIZE))))
										.addPreferredGap(ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
												.addGroup(layout.createSequentialGroup()
														.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
																.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(txtHttp, 0, 0, Short.MAX_VALUE))
														.addGap(59))
												.addGroup(layout.createSequentialGroup().addComponent(lblNewLabel_2)
														.addGap(92))))
								.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textField_6, Alignment.LEADING)
										.addComponent(jTextField1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 364,
												Short.MAX_VALUE)
										.addComponent(textField)))
								.addPreferredGap(ComponentPlacement.RELATED, 892, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 36,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel, Alignment.TRAILING))
								.addGap(112)))
				.addGap(159)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(jTextField1,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblThumbnails).addComponent(
						textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(23)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE).addGap(45))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
										.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(lblDe, GroupLayout.PREFERRED_SIZE, 19,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(layout.createParallelGroup(Alignment.BASELINE)
																.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 19,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(label, GroupLayout.PREFERRED_SIZE, 19,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(lblNewLabel_2)))
												.addGap(11)
												.addGroup(layout.createParallelGroup(Alignment.LEADING)
														.addComponent(textField_4, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGroup(layout.createParallelGroup(Alignment.BASELINE)
																.addComponent(textField_5, GroupLayout.PREFERRED_SIZE,
																		26, GroupLayout.PREFERRED_SIZE)
																.addComponent(textField_1, GroupLayout.PREFERRED_SIZE,
																		26, GroupLayout.PREFERRED_SIZE)
																.addComponent(txtHttp, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))))
										.addComponent(checkBox, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)))
				.addComponent(lblNewLabel).addGap(9)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(83).addComponent(textField_2,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(btnNewButton_2)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))));
		getContentPane().setLayout(layout);
		setSize(new Dimension(629, 529));
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
	}

	public void stateChanged(ChangeEvent e) {
		//
	}
}