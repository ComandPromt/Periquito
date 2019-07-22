package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import periquito.MenuPrincipal;

@SuppressWarnings("serial")
public class InterfazGaleria extends javax.swing.JFrame {
	transient Galeria miGaleria = new Galeria();

	int size = miGaleria.getFotos().size() - 1;
	int posicion;
	int posicionactual;
	int paso;
	int descargar;
	int web;
	boolean atras = false;
	private javax.swing.JLabel fotoA;
	private javax.swing.JLabel fotoB;
	private javax.swing.JLabel fotoC;
	private javax.swing.JLabel fotoD;
	private javax.swing.JLabel fotoGrande;
	private GridBagConstraints gridBagConstraints_1;

	public InterfazGaleria() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazGaleria.class.getResource("/imagenes/lupa.png")));

		setResizable(false);
		initComponents();
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

		JMenu mnNewMenu = new JMenu("Descargar");
		mnNewMenu.setBackground(new Color(240, 240, 240));
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.setIcon(new ImageIcon(InterfazGaleria.class.getResource("/imagenes/download.png")));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Descargar");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {

					descargar = paso;

					if (descargar > 0) {
						--descargar;
					}

					if (atras) {
						++descargar;
					}

					Metodos.descargarFoto(Galeria.getUrlFotos().get(descargar));

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

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Web");
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {

					web = paso;

					if (web > 0) {
						--web;
					}

					if (atras) {
						++web;
					}

					Metodos.abrirCarpeta(Galeria.getUrlFotos().get(web));

				} catch (IOException e1) {
					//
				}
			}
		});
		mntmNewMenuItem_2.setBackground(new Color(240, 240, 240));
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_2.setIcon(new ImageIcon(InterfazGaleria.class.getResource("/imagenes/remote.png")));
		menuBar.add(mntmNewMenuItem_2);
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;
		javax.swing.JPanel jPanel1;
		javax.swing.JPanel jPanel2;
		javax.swing.JPanel jPanel3;
		javax.swing.JPanel jPanel4;
		javax.swing.JPanel jPanel5;
		javax.swing.JPanel jPanel6;
		jPanel1 = new javax.swing.JPanel();
		fotoGrande = new javax.swing.JLabel();
		fotoGrande.setHorizontalAlignment(SwingConstants.CENTER);
		jPanel3 = new javax.swing.JPanel();
		javax.swing.JButton cmdPreview = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		fotoA = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		fotoB = new javax.swing.JLabel();
		jPanel5 = new javax.swing.JPanel();
		fotoC = new javax.swing.JLabel();
		jPanel6 = new javax.swing.JPanel();
		fotoD = new javax.swing.JLabel();
		javax.swing.JButton cmdNext = new javax.swing.JButton();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new java.awt.GridBagLayout());
		jPanel1.setBackground(new java.awt.Color(240, 240, 240));
		jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
		jPanel1.setPreferredSize(new java.awt.Dimension(600, 500));
		jPanel1.setLayout(new java.awt.GridBagLayout());
		fotoGrande.setText("foto");
		fotoGrande.setPreferredSize(new java.awt.Dimension(560, 460));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		jPanel1.add(fotoGrande, gridBagConstraints);
		gridBagConstraints_1 = new java.awt.GridBagConstraints();
		gridBagConstraints_1.gridheight = 2;
		gridBagConstraints_1.gridx = 0;
		gridBagConstraints_1.gridy = 0;
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
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
		jPanel3.add(cmdPreview, gridBagConstraints);
		jPanel2.setBackground(new java.awt.Color(255, 255, 255));
		jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
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
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel3.add(jPanel2, gridBagConstraints);
		jPanel4.setBackground(new java.awt.Color(255, 255, 255));
		jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
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
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel3.add(jPanel4, gridBagConstraints);
		jPanel5.setBackground(new java.awt.Color(255, 255, 255));
		jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
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
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel3.add(jPanel5, gridBagConstraints);
		jPanel6.setBackground(new java.awt.Color(255, 255, 255));
		jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
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
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel3.add(jPanel6, gridBagConstraints);
		cmdNext.setText(">");
		cmdNext.setPreferredSize(new java.awt.Dimension(44, 120));
		cmdNext.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmdNextActionPerformed();
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 5;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
		jPanel3.add(cmdNext, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		getContentPane().add(jPanel3, gridBagConstraints);

		pack();
	}

	private void fotoAMouseClicked() {

		if (posicion == 0 && posicionactual == 0 && paso == 0) {

			inicializar();
		} else {

			if (posicion + 1 == posicionactual && paso > 0) {
				verImagen(--paso);
			} else {

				if (posicion == 0 && posicionactual == 1 && paso == 0) {
					inicializar();
				}

				else {

					if (paso == 1) {
						inicializar();
					} else {

						if (posicionactual > size) {

							posicionactual = paso;
							verImagen(posicionactual);
						}

						if (posicion == 0 && posicionactual > 0 && paso < 0) {
							posicion = posicionactual;
							paso = posicionactual;
						}

						posicionactual -= 2;
						verImagen(posicionactual);
					}
				}
			}
		}
	}

	private void fotoBMouseClicked() {
		posicionactual += 1;
		verImagen(posicionactual);
		paso = posicionactual;
	}

	private void fotoCMouseClicked() {
		posicionactual += 2;
		verImagen(posicionactual);
		paso = posicionactual;
	}

	private void fotoDMouseClicked() {
		posicionactual += 3;
		verImagen(posicionactual);
		paso = posicionactual;
	}

	protected void verImagen(int posicionimagen) {
		try {
			fotoGrande.setIcon(miGaleria.getFoto(posicionimagen, fotoGrande.getSize()));
			fotoA.setIcon(miGaleria.getPreview(posicionimagen));
			fotoB.setIcon(miGaleria.getPreview(posicionimagen + 1));
			fotoC.setIcon(miGaleria.getPreview(posicionimagen + 2));
			fotoD.setIcon(miGaleria.getPreview(posicionimagen + 3));
		} catch (IndexOutOfBoundsException e) {
			posicionactual = size - 3;
			fotoA.setIcon(miGaleria.getPreview(size - 3));
			fotoB.setIcon(miGaleria.getPreview(size - 2));
			fotoC.setIcon(miGaleria.getPreview(size - 1));
			fotoD.setIcon(miGaleria.getPreview(size));
		}
	}

	private void cmdNextActionPerformed() {
		atras = false;
		if (paso == 0 && posicionactual == 0 && size > 1) {
			++posicionactual;
			verImagen(posicionactual);
			paso = posicionactual;
		}
		if (paso == size || posicionactual == size) {
			fotoA.setIcon(miGaleria.getPreview(size - 3));
			fotoB.setIcon(miGaleria.getPreview(size - 2));
			fotoC.setIcon(miGaleria.getPreview(size - 1));
			fotoD.setIcon(miGaleria.getPreview(size));
			fotoGrande.setIcon(miGaleria.getFoto(size, fotoGrande.getSize()));
		} else {
			if (paso < 0) {
				paso = 0;

			}
			if (posicionactual == 0) {
				if (paso == 0) {
					inicializar();
				} else {

					if (paso == 2) {
						--paso;
						posicionactual = paso;
					}
					fotoGrande.setIcon(miGaleria.getFoto(posicionactual, fotoGrande.getSize()));
					fotoA.setIcon(miGaleria.getPreview(posicionactual));
					fotoB.setIcon(miGaleria.getPreview(posicionactual + 1));
					fotoC.setIcon(miGaleria.getPreview(posicionactual + 2));
					fotoD.setIcon(miGaleria.getPreview(posicionactual + 3));
					paso++;

				}
			} else {
				fotoA.setIcon(miGaleria.getPreview(paso));
				fotoB.setIcon(miGaleria.getPreview(paso + 1));
				fotoC.setIcon(miGaleria.getPreview(paso + 2));
				fotoD.setIcon(miGaleria.getPreview(paso + 3));
				fotoGrande.setIcon(miGaleria.getFoto(paso, fotoGrande.getSize()));
				++paso;

			}
		}

		/*
		 * fotoA.setIcon(miGaleria.getPreview(paso));
		 * fotoGrande.setIcon(miGaleria.getFoto(paso, fotoGrande.getSize()));
		 * fotoB.setIcon(miGaleria.getPreview(paso + 1));
		 * fotoC.setIcon(miGaleria.getPreview(paso + 2));
		 * fotoD.setIcon(miGaleria.getPreview(paso + 3));
		 */

	}

	private void cmdPreviewActionPerformed() {// GEN-FIRST:event_cmdPreviewActionPerformed
		atras = true;
		if (paso == 2) {

			if (posicionactual <= 0) {
				inicializar();
			}

			else {
				verImagen(--posicionactual);
			}

		} else {
			if (paso < 0) {
				paso = 0;
			}

			if (posicion != 0 && posicionactual != 0) {

				fotoD.setIcon(miGaleria.getPreview(posicionactual));
				fotoC.setIcon(miGaleria.getPreview(posicionactual - 1));
				fotoB.setIcon(miGaleria.getPreview(posicionactual - 2));
				fotoA.setIcon(miGaleria.getPreview(posicionactual - 3));
			} else {

				fotoD.setIcon(miGaleria.getPreview(paso));
				fotoC.setIcon(miGaleria.getPreview(paso - 1));
				fotoB.setIcon(miGaleria.getPreview(paso - 2));
				fotoA.setIcon(miGaleria.getPreview(paso - 3));
				posicionactual = paso;
				--paso;
			}

			if (posicionactual == 0 && posicion == 0 || posicionactual < 0) {
				inicializar();
			}

			fotoGrande.setIcon(miGaleria.getFoto(--posicionactual, fotoGrande.getSize()));

		}

	}

	protected void inicializar() {
		posicionactual = 1;
		fotoA.setIcon(miGaleria.getPreview(0));
		fotoB.setIcon(miGaleria.getPreview(1));
		fotoC.setIcon(miGaleria.getPreview(2));
		fotoD.setIcon(miGaleria.getPreview(3));
		fotoGrande.setIcon(miGaleria.getFoto(0, fotoGrande.getSize()));
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new InterfazGaleria().setVisible(true);
			}
		});
	}
}