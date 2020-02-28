package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import periquito.MenuPrincipal;

@SuppressWarnings("serial")
public class InterfazGaleria extends JFrame {

	transient Galeria miGaleria = new Galeria();

	int size = miGaleria.getFotos().size() - 1;

	int posicionactual;
	int paso;

	private JLabel fotoA = new JLabel();
	private JLabel fotoB = new JLabel();
	private JLabel fotoC = new JLabel();
	private JLabel fotoD = new JLabel();
	private JLabel fotoGrande = new JLabel();
	private GridBagConstraints gridBagConstraints_1;
	private int contador = 1;
	static int numImagenes = 0;
	JLabel titulo = new JLabel("");
	private JButton cmdPreview = new JButton();

	private JButton cmdNext = new JButton();

	public int getNumImagenes() {
		return numImagenes;
	}

	public static void setNumImagenes(int numImagenes) {
		InterfazGaleria.numImagenes = numImagenes;
	}

	public InterfazGaleria() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazGaleria.class.getResource("/imagenes/lupa.png")));

		setResizable(false);

		initComponents();
		System.out.println("Paso: " + paso + " posicion actual: " + posicionactual + "\n");
		if (numImagenes == 1) {

			cmdPreview.setEnabled(false);
			cmdNext.setEnabled(false);
			fotoA.setVisible(false);
			fotoB.setVisible(false);
			fotoC.setVisible(false);
			fotoD.setVisible(false);
		}

		this.setTitle("Periquito v3 Image Gallery");
		this.setLocationRelativeTo(null);
		fotoA.setText("");
		fotoB.setText("");
		fotoC.setText("");
		fotoD.setText("");
		fotoGrande.setText("");
		fotoA.setIcon(miGaleria.getPreview(0));
		fotoB.setIcon(miGaleria.getPreview(1));
		fotoC.setIcon(miGaleria.getPreview(2));
		fotoD.setIcon(miGaleria.getPreview(3));
		fotoGrande.setIcon(miGaleria.getFoto(0, fotoGrande.getSize()));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu_2 = new JMenu("Acciones");
		menuBar.add(mnNewMenu_2);

		JMenu mnNewMenu = new JMenu("Descargar");
		mnNewMenu_2.add(mnNewMenu);
		mnNewMenu.setBackground(new Color(240, 240, 240));
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.setIcon(new ImageIcon(InterfazGaleria.class.getResource("/imagenes/download.png")));

		JMenuItem mntmNewMenuItem = new JMenuItem("Descargar");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {

					Metodos.descargarFoto(Galeria.getUrlFotos().get(contador));

				} catch (IOException e1) {
					//
				}
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem.setIcon(new ImageIcon(InterfazGaleria.class.getResource("/imagenes/download.png")));
		mnNewMenu.add(mntmNewMenuItem);

		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);

		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Descargar");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Metodos.abrirCarpeta("Config" + MenuPrincipal.getSeparador() + "Downloads");
				} catch (IOException e1) {
					//
				}
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(InterfazGaleria.class.getResource("/imagenes/folder.png")));
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Información sobre imagen");
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnNewMenu_2.add(mntmNewMenuItem_4);

		JMenu mnNewMenu_1 = new JMenu("Ver imagen");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu_1.setForeground(Color.BLACK);
		mnNewMenu_1.setIcon(new ImageIcon(InterfazGaleria.class.getResource("/imagenes/view.png")));
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Web");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {

					Metodos.abrirCarpeta(Galeria.getUrlFotos().get(posicionactual));

				} catch (IOException e1) {
					//
				}
			}
		});
		mntmNewMenuItem_2.setBackground(new Color(240, 240, 240));
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_2.setIcon(new ImageIcon(InterfazGaleria.class.getResource("/imagenes/remote.png")));

		JSeparator separator_2 = new JSeparator();
		mnNewMenu_1.add(separator_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("En el CMS");
		mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {

					String url = "http://" + MenuPrincipal.getLecturaurl()[0] + "/" + MenuPrincipal.getLecturaurl()[1]
							+ "/details.php?image_id=" + Galeria.getIdImagenes().get(contador);

					Metodos.abrirCarpeta(url);

				} catch (Exception e1) {
					//
				}

			}
		});
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_3.setForeground(Color.BLACK);
		mntmNewMenuItem_3.setIcon(new ImageIcon(InterfazGaleria.class.getResource("/imagenes/remote.png")));
		mnNewMenu_1.add(mntmNewMenuItem_3);
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;
		JPanel jPanel1;
		JPanel jPanel2;
		JPanel jPanel3;
		JPanel jPanel4;
		JPanel jPanel5;
		JPanel jPanel6;
		jPanel1 = new JPanel();
		fotoGrande = new JLabel();
		fotoGrande.setHorizontalAlignment(SwingConstants.CENTER);
		jPanel3 = new JPanel();

		cmdPreview.setFont(new Font("Tahoma", Font.BOLD, 15));
		jPanel2 = new JPanel();
		fotoA = new JLabel();
		jPanel4 = new JPanel();

		jPanel5 = new JPanel();

		jPanel6 = new JPanel();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new java.awt.GridBagLayout());
		titulo.setHorizontalAlignment(SwingConstants.CENTER);

		titulo.setText(contador + " / " + numImagenes);
		titulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_titulo = new GridBagConstraints();
		gbc_titulo.insets = new Insets(0, 0, 5, 0);
		gbc_titulo.gridx = 0;
		gbc_titulo.gridy = 0;
		getContentPane().add(titulo, gbc_titulo);
		jPanel1.setBackground(new java.awt.Color(240, 240, 240));
		jPanel1.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
		jPanel1.setPreferredSize(new java.awt.Dimension(600, 500));
		jPanel1.setLayout(new java.awt.GridBagLayout());
		fotoGrande.setText("foto");
		fotoGrande.setPreferredSize(new java.awt.Dimension(560, 460));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		jPanel1.add(fotoGrande, gridBagConstraints);
		gridBagConstraints_1 = new java.awt.GridBagConstraints();
		gridBagConstraints_1.gridheight = 2;
		gridBagConstraints_1.gridx = 0;
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints_1.insets = new java.awt.Insets(10, 10, 5, 10);
		getContentPane().add(jPanel1, gridBagConstraints_1);
		jPanel3.setBorder(null);
		jPanel3.setLayout(new java.awt.GridBagLayout());
		cmdPreview.setText("<");
		cmdPreview.setPreferredSize(new java.awt.Dimension(44, 120));
		cmdPreview.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmdPreviewActionPerformed();
			}
		});

		JButton btnNewButton = new JButton("<<");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				inicializar();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		jPanel3.add(btnNewButton, gbc_btnNewButton);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new Insets(5, 5, 0, 5);
		jPanel3.add(cmdPreview, gridBagConstraints);
		jPanel2.setBackground(new java.awt.Color(255, 255, 255));
		jPanel2.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
		jPanel2.setPreferredSize(new java.awt.Dimension(120, 120));
		jPanel2.setLayout(new java.awt.GridBagLayout());

		fotoA.setText("foto");
		fotoA.setPreferredSize(new java.awt.Dimension(100, 100));
		fotoA.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				fotoAMouseClicked();
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel2.add(fotoA, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new Insets(5, 5, 0, 5);
		jPanel3.add(jPanel2, gridBagConstraints);
		jPanel4.setBackground(new java.awt.Color(255, 255, 255));
		jPanel4.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
		jPanel4.setPreferredSize(new java.awt.Dimension(120, 120));
		jPanel4.setLayout(new java.awt.GridBagLayout());

		fotoB.setText("foto");
		fotoB.setPreferredSize(new java.awt.Dimension(100, 100));
		fotoB.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				fotoBMouseClicked();
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel4.add(fotoB, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new Insets(5, 5, 0, 5);
		jPanel3.add(jPanel4, gridBagConstraints);
		jPanel5.setBackground(new java.awt.Color(255, 255, 255));
		jPanel5.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
		jPanel5.setPreferredSize(new java.awt.Dimension(120, 120));
		jPanel5.setLayout(new java.awt.GridBagLayout());

		fotoC.setText("foto");
		fotoC.setPreferredSize(new java.awt.Dimension(100, 100));
		fotoC.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				fotoCMouseClicked();
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel5.add(fotoC, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(5, 5, 0, 5);
		jPanel3.add(jPanel5, gridBagConstraints);
		jPanel6.setBackground(new java.awt.Color(255, 255, 255));
		jPanel6.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
		jPanel6.setPreferredSize(new java.awt.Dimension(120, 120));
		jPanel6.setLayout(new java.awt.GridBagLayout());

		fotoD.setText("foto");
		fotoD.setPreferredSize(new java.awt.Dimension(100, 100));
		fotoD.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				fotoDMouseClicked();
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel6.add(fotoD, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 5;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new Insets(5, 5, 0, 5);
		jPanel3.add(jPanel6, gridBagConstraints);

		cmdNext.setFont(new Font("Tahoma", Font.BOLD, 15));
		cmdNext.setText(">");
		cmdNext.setPreferredSize(new java.awt.Dimension(44, 120));
		cmdNext.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmdNextActionPerformed();
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 6;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new Insets(5, 0, 0, 5);
		jPanel3.add(cmdNext, gridBagConstraints);

		JButton btnNewButton_1 = new JButton(">>");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				contador = numImagenes;
				posicionactual = size;
				paso = posicionactual;
				--paso;
				verImagen(posicionactual);
			}
		});

		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 7;
		gbc_btnNewButton_1.gridy = 0;
		jPanel3.add(btnNewButton_1, gbc_btnNewButton_1);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		getContentPane().add(jPanel3, gridBagConstraints);

		pack();
	}

	private void fotoAMouseClicked() {

		if ((posicionactual == 0 && paso == 0) || paso == 1 || (posicionactual == 1 && paso <= 0)) {

			inicializar();

		}

		else {
			if (size - posicionactual <= 3) {

				if (contador % 4 != 0) {
					System.out.println("NO ENTRES");
					while (contador % 4 != 0) {
						++contador;
					}

					if (contador > size) {
						contador = size;
					}

					int numeroMaximo = contador - 4;

					posicionactual = ++numeroMaximo;

					paso = posicionactual;

					contador = ++numeroMaximo;
				}
			}

			else {
				--posicionactual;

				contador = posicionactual;

				--posicionactual;
				paso = posicionactual;
			}

			System.out
					.println("Salida: posicion " + posicionactual + " contador: " + contador + " size: " + size + "\n");

			verImagen(posicionactual);

		}
	}

	private void fotoBMouseClicked() {
		posicionactual += 1;

		System.out.println("Contador: " + contador + " Paso: " + paso + " posicion actual: " + posicionactual + "\n");

		verImagen(posicionactual);
		paso = posicionactual;

	}

	private void fotoCMouseClicked() {
		posicionactual += 2;
		verImagen(posicionactual);
		paso = posicionactual;
		System.out.println("Paso: " + paso + " posicion actual: " + posicionactual + "\n");
	}

	private void fotoDMouseClicked() {
		posicionactual += 3;
		verImagen(posicionactual);
		paso = posicionactual;
		System.out.println("Paso: " + paso + " posicion actual: " + posicionactual + "\n");
	}

	protected void verImagen(int posicionimagen) {
		try {

			fotoGrande.setIcon(miGaleria.getFoto(posicionimagen, fotoGrande.getSize()));

			if (size - posicionimagen <= 2) {
				int fotoa, fotob, fotoc;
				fotoa = size - 3;
				fotob = size - 2;
				fotoc = size - 1;
				fotoA.setIcon(miGaleria.getPreview(fotoa));
				fotoB.setIcon(miGaleria.getPreview(fotob));
				fotoC.setIcon(miGaleria.getPreview(fotoc));
				fotoD.setIcon(miGaleria.getPreview(size));
			} else {

				fotoA.setIcon(miGaleria.getPreview(posicionimagen));
				fotoB.setIcon(miGaleria.getPreview(posicionimagen + 1));
				fotoC.setIcon(miGaleria.getPreview(posicionimagen + 2));
				fotoD.setIcon(miGaleria.getPreview(posicionimagen + 3));
			}

			titulo.setText(contador + " / " + numImagenes);
		} catch (IndexOutOfBoundsException e) {

			inicializar();

		}
	}

	private void cmdNextActionPerformed() {

		if (contador <= 0) {
			contador = 2;
		}

		else {
			++contador;
		}

		if (paso == size || posicionactual == size) {

			verImagen(size);

		}

		if (paso < 0 || posicionactual > size) {
			inicializar();
		}

		else {

			if (paso == posicionactual && contador > size) {

				contador = posicionactual;
				contador += 2;
				++posicionactual;
				++paso;

			}

			else {

				posicionactual = contador;

				--posicionactual;

				paso = posicionactual;

				--paso;
			}

			verImagen(posicionactual);

		}

	}

	private void cmdPreviewActionPerformed() {

		if (posicionactual <= 2) {

			if (posicionactual <= 0) {

				inicializar();
			}

			else {

				paso = posicionactual;

				contador = paso;

				fotoA.setIcon(miGaleria.getPreview(0));
				fotoB.setIcon(miGaleria.getPreview(1));
				fotoC.setIcon(miGaleria.getPreview(2));
				fotoD.setIcon(miGaleria.getPreview(3));

				titulo.setText(posicionactual + " / " + numImagenes);

				fotoGrande.setIcon(miGaleria.getFoto(--posicionactual, fotoGrande.getSize()));

			}
		}

		else {

			if (paso == 2) {

				verImagen(--posicionactual);

			}

			else {

				if (paso < 0) {
					paso = 0;
				}

				if (posicionactual != 0) {

					fotoD.setIcon(miGaleria.getPreview(posicionactual));
					fotoC.setIcon(miGaleria.getPreview(posicionactual - 1));
					fotoB.setIcon(miGaleria.getPreview(posicionactual - 2));
					fotoA.setIcon(miGaleria.getPreview(posicionactual - 3));

				}

				else {

					fotoD.setIcon(miGaleria.getPreview(paso));

					fotoC.setIcon(miGaleria.getPreview(paso - 1));

					fotoB.setIcon(miGaleria.getPreview(paso - 2));

					fotoA.setIcon(miGaleria.getPreview(paso - 3));

					posicionactual = paso;

					--paso;
				}

				if (posicionactual <= 0) {

					posicionactual = 1;

					paso = 0;

					contador = 1;
				}

				titulo.setText(posicionactual + " / " + numImagenes);

				fotoGrande.setIcon(miGaleria.getFoto(--posicionactual, fotoGrande.getSize()));

			}

			if (paso - posicionactual == 1) {
				--paso;
			}
		}

	}

	protected void inicializar() {

		contador = 1;

		posicionactual = 0;

		paso = 0;

		fotoA.setIcon(miGaleria.getPreview(0));

		fotoB.setIcon(miGaleria.getPreview(1));

		fotoC.setIcon(miGaleria.getPreview(2));

		fotoD.setIcon(miGaleria.getPreview(3));

		fotoGrande.setIcon(miGaleria.getFoto(0, fotoGrande.getSize()));

		titulo.setText("1 / " + numImagenes);
	}

	public static void main(String[] args) {

		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new InterfazGaleria().setVisible(true);
			}

		});
	}

}