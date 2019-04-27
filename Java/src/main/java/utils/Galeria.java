package utils;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import periquito.MenuPrincipal;

/**
 * @web http://www.jc-mouse.net/
 * @author Mouse
 */
public class Galeria {
	// para almacenar las imagene
	private ArrayList<ImageIcon> fotos = new ArrayList<ImageIcon>();

	public Galeria() {

		try {

			for (int i = 0; i < MenuPrincipal.getImagenes().size(); i++) {
				fotos.add(new javax.swing.ImageIcon(ImageIO.read(new URL("http://192.168.1.2/"
						+ MenuPrincipal.getLecturaurl()[1] + "/data/media/3/" + MenuPrincipal.getImagenes().get(i)))));
			}

		} catch (IOException e) {
			//
		}

	}

	/* devuelve una imagen de tamaño 100x100 VISTA PREVIA */
	public Icon getPreview(int num) {
		if (num >= 0 & num < fotos.size()) {
			Image mini = fotos.get(num).getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
			return new ImageIcon(mini);
		} else {
			return null;
		}
	}

	/*
	 * devuelve la foto original, pero si el tamaño es mayor al contenedor, lo
	 * redimensiona
	 */
	public Icon getFoto(int num, Dimension d) {
		int original_width = fotos.get(num).getIconWidth();
		int original_height = fotos.get(num).getIconHeight();
		int bound_width = d.width;
		int bound_height = d.height;
		int new_width = original_width;
		int new_height = original_height;

		// first check if we need to scale width
		if (original_width > bound_width) {
			// scale width to fit
			new_width = bound_width;
			// scale height to maintain aspect ratio
			new_height = (new_width * original_height) / original_width;
		}

		// then check if we need to scale even with the new height
		if (new_height > bound_height) {
			// scale height to fit instead
			new_height = bound_height;
			// scale width to maintain aspect ratio
			new_width = (new_height * original_width) / original_height;
		}

		Image mini = fotos.get(num).getImage().getScaledInstance(new_width, new_height, Image.SCALE_AREA_AVERAGING);
		return new ImageIcon(mini);
	}

	public ArrayList<ImageIcon> getFotos() {
		return fotos;
	}

	public void setFotos(ArrayList<ImageIcon> fotos) {
		this.fotos = fotos;
	}
}
