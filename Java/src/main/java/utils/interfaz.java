package utils;

@SuppressWarnings("serial")
public class interfaz extends javax.swing.JFrame {
	Galeria Mi_Galeria = new Galeria();
	int size = Mi_Galeria.getFotos().size() - 1;
	int posicion;
	int posicionactual;
	int paso;

	private javax.swing.JButton CMD_NEXT;
	private javax.swing.JButton CMD_PREVIEW;
	private javax.swing.JLabel FOTO_A;
	private javax.swing.JLabel FOTO_B;
	private javax.swing.JLabel FOTO_C;
	private javax.swing.JLabel FOTO_D;
	private javax.swing.JLabel FOTO_GRANDE;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;

	public interfaz() {

		setResizable(false);
		initComponents();
		this.setTitle("Periquito v3 Image Gallery");
		this.setLocationRelativeTo(null);
		FOTO_A.setText("");
		FOTO_B.setText("");
		FOTO_C.setText("");
		FOTO_D.setText("");
		FOTO_GRANDE.setText("");
		FOTO_A.setIcon(Mi_Galeria.getPreview(0));
		FOTO_B.setIcon(Mi_Galeria.getPreview(1));
		FOTO_C.setIcon(Mi_Galeria.getPreview(2));
		FOTO_D.setIcon(Mi_Galeria.getPreview(3));
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;
		jPanel1 = new javax.swing.JPanel();
		FOTO_GRANDE = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		CMD_PREVIEW = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		FOTO_A = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		FOTO_B = new javax.swing.JLabel();
		jPanel5 = new javax.swing.JPanel();
		FOTO_C = new javax.swing.JLabel();
		jPanel6 = new javax.swing.JPanel();
		FOTO_D = new javax.swing.JLabel();
		CMD_NEXT = new javax.swing.JButton();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new java.awt.GridBagLayout());
		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
		jPanel1.setPreferredSize(new java.awt.Dimension(600, 500));
		jPanel1.setLayout(new java.awt.GridBagLayout());
		FOTO_GRANDE.setText("foto");
		FOTO_GRANDE.setPreferredSize(new java.awt.Dimension(560, 460));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		jPanel1.add(FOTO_GRANDE, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
		getContentPane().add(jPanel1, gridBagConstraints);
		jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		jPanel3.setLayout(new java.awt.GridBagLayout());
		CMD_PREVIEW.setText("<");
		CMD_PREVIEW.setPreferredSize(new java.awt.Dimension(44, 120));
		CMD_PREVIEW.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CMD_PREVIEWActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
		jPanel3.add(CMD_PREVIEW, gridBagConstraints);
		jPanel2.setBackground(new java.awt.Color(255, 255, 255));
		jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
		jPanel2.setPreferredSize(new java.awt.Dimension(120, 120));
		jPanel2.setLayout(new java.awt.GridBagLayout());

		FOTO_A.setText("foto");
		FOTO_A.setPreferredSize(new java.awt.Dimension(100, 100));
		FOTO_A.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				FOTO_AMouseClicked(evt);
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		jPanel2.add(FOTO_A, gridBagConstraints);
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

		FOTO_B.setText("foto");
		FOTO_B.setPreferredSize(new java.awt.Dimension(100, 100));
		FOTO_B.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				FOTO_BMouseClicked(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		jPanel4.add(FOTO_B, gridBagConstraints);
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

		FOTO_C.setText("foto");
		FOTO_C.setPreferredSize(new java.awt.Dimension(100, 100));
		FOTO_C.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				FOTO_CMouseClicked(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		jPanel5.add(FOTO_C, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel3.add(jPanel5, gridBagConstraints);
		jPanel6.setBackground(new java.awt.Color(255, 255, 255));
		jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
		jPanel6.setPreferredSize(new java.awt.Dimension(120, 120));
		jPanel6.setLayout(new java.awt.GridBagLayout());

		FOTO_D.setText("foto");
		FOTO_D.setPreferredSize(new java.awt.Dimension(100, 100));
		FOTO_D.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				FOTO_DMouseClicked(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		jPanel6.add(FOTO_D, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel3.add(jPanel6, gridBagConstraints);
		CMD_NEXT.setText(">");
		CMD_NEXT.setPreferredSize(new java.awt.Dimension(44, 120));
		CMD_NEXT.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CMD_NEXTActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 5;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
		jPanel3.add(CMD_NEXT, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		getContentPane().add(jPanel3, gridBagConstraints);

		pack();
	}

	private void FOTO_AMouseClicked(java.awt.event.MouseEvent evt) {

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

	private void FOTO_BMouseClicked(java.awt.event.MouseEvent evt) {
		posicionactual += 1;
		verImagen(posicionactual);
		paso = posicionactual;
	}

	private void FOTO_CMouseClicked(java.awt.event.MouseEvent evt) {
		posicionactual += 2;
		verImagen(posicionactual);
		paso = posicionactual;
	}

	private void FOTO_DMouseClicked(java.awt.event.MouseEvent evt) {
		posicionactual += 3;
		verImagen(posicionactual);
		paso = posicionactual;
	}

	protected void verImagen(int posicionimagen) {
		try {
			FOTO_GRANDE.setIcon(Mi_Galeria.getFoto(posicionimagen, FOTO_GRANDE.getSize()));
			FOTO_A.setIcon(Mi_Galeria.getPreview(posicionimagen));
			FOTO_B.setIcon(Mi_Galeria.getPreview(posicionimagen + 1));
			FOTO_C.setIcon(Mi_Galeria.getPreview(posicionimagen + 2));
			FOTO_D.setIcon(Mi_Galeria.getPreview(posicionimagen + 3));
		} catch (IndexOutOfBoundsException e) {
			posicionactual = size - 3;
			FOTO_A.setIcon(Mi_Galeria.getPreview(size - 3));
			FOTO_B.setIcon(Mi_Galeria.getPreview(size - 2));
			FOTO_C.setIcon(Mi_Galeria.getPreview(size - 1));
			FOTO_D.setIcon(Mi_Galeria.getPreview(size));
		}
	}

	private void CMD_NEXTActionPerformed(java.awt.event.ActionEvent evt) {

		if (paso == size || posicionactual == size) {
			FOTO_A.setIcon(Mi_Galeria.getPreview(size - 3));
			FOTO_B.setIcon(Mi_Galeria.getPreview(size - 2));
			FOTO_C.setIcon(Mi_Galeria.getPreview(size - 1));
			FOTO_D.setIcon(Mi_Galeria.getPreview(size));
			FOTO_GRANDE.setIcon(Mi_Galeria.getFoto(size, FOTO_GRANDE.getSize()));
		} else {
			if (paso < 0) {
				paso = 0;
			}
			if (posicionactual == 0) {
				if (paso == 0) {
					inicializar();
				} else {

					if (paso == 2 && posicionactual == 0) {
						--paso;
						posicionactual = paso;
					}
					FOTO_GRANDE.setIcon(Mi_Galeria.getFoto(posicionactual, FOTO_GRANDE.getSize()));
					FOTO_A.setIcon(Mi_Galeria.getPreview(posicionactual));
					FOTO_B.setIcon(Mi_Galeria.getPreview(posicionactual + 1));
					FOTO_C.setIcon(Mi_Galeria.getPreview(posicionactual + 2));
					FOTO_D.setIcon(Mi_Galeria.getPreview(posicionactual + 3));
					paso++;
				}
			} else {
				FOTO_A.setIcon(Mi_Galeria.getPreview(paso));
				FOTO_B.setIcon(Mi_Galeria.getPreview(paso + 1));
				FOTO_C.setIcon(Mi_Galeria.getPreview(paso + 2));
				FOTO_D.setIcon(Mi_Galeria.getPreview(paso + 3));
				FOTO_GRANDE.setIcon(Mi_Galeria.getFoto(paso, FOTO_GRANDE.getSize()));
				++paso;
			}
		}
	}

	private void CMD_PREVIEWActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CMD_PREVIEWActionPerformed

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

				FOTO_D.setIcon(Mi_Galeria.getPreview(posicionactual));
				FOTO_C.setIcon(Mi_Galeria.getPreview(posicionactual - 1));
				FOTO_B.setIcon(Mi_Galeria.getPreview(posicionactual - 2));
				FOTO_A.setIcon(Mi_Galeria.getPreview(posicionactual - 3));
			} else {

				FOTO_D.setIcon(Mi_Galeria.getPreview(paso));
				FOTO_C.setIcon(Mi_Galeria.getPreview(paso - 1));
				FOTO_B.setIcon(Mi_Galeria.getPreview(paso - 2));
				FOTO_A.setIcon(Mi_Galeria.getPreview(paso - 3));
				posicionactual = paso;
				--paso;
			}

			if (posicionactual == 0 && posicion == 0 || posicionactual < 0) {
				inicializar();
			}

			FOTO_GRANDE.setIcon(Mi_Galeria.getFoto(--posicionactual, FOTO_GRANDE.getSize()));

		}

	}

	protected void inicializar() {
		posicionactual = 1;
		FOTO_A.setIcon(Mi_Galeria.getPreview(0));
		FOTO_B.setIcon(Mi_Galeria.getPreview(1));
		FOTO_C.setIcon(Mi_Galeria.getPreview(2));
		FOTO_D.setIcon(Mi_Galeria.getPreview(3));
		FOTO_GRANDE.setIcon(Mi_Galeria.getFoto(0, FOTO_GRANDE.getSize()));
	}

	protected void reiniciarVariables() {
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new interfaz().setVisible(true);
			}
		});
	}
}