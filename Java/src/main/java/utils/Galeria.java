package utils;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import periquito.Config2;
import periquito.MenuPrincipal;

public class Galeria {

	private ArrayList<ImageIcon> fotos = new ArrayList<>();

	public Galeria() {

		try {

			int numeroImagenes = MenuPrincipal.getImagenes().size();

			String[] lecturaurl = Metodos.leerFicheroArray("Config/Config2.txt", 2);

			for (int i = 0; i < numeroImagenes; i++) {

				fotos.add(new javax.swing.ImageIcon(
						ImageIO.read(new URL("http://" + lecturaurl[0] + "/" + lecturaurl[1] + "/data/media/"
								+ MenuPrincipal.getCategorias().get(i) + "/" + MenuPrincipal.getImagenes().get(i)))));
			}

		} catch (IOException e) {
			Metodos.mensaje("No se han podido cargar las imágenes", 1);
			try {
				new Config2().setVisible(true);
			} catch (IOException e1) {
				//
			}
		}

	}

	/* devuelve una imagen de tamaño 100x100 VISTA PREVIA */
	public Icon getPreview(int num) {
		if (num >= 0 && num < fotos.size()) {
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
		int originalWidth = fotos.get(num).getIconWidth();
		int originalHeight = fotos.get(num).getIconHeight();
		int boundWidth = d.width;
		int boundHeight = d.height;
		int newWidth = originalWidth;
		int newHeight = originalHeight;

		// first check if we need to scale width
		if (originalWidth > boundWidth) {
			// scale width to fit
			newWidth = boundWidth;
			// scale height to maintain aspect ratio
			newHeight = (newWidth * originalHeight) / originalWidth;
		}

		// then check if we need to scale even with the new height
		if (newHeight > boundHeight) {
			// scale height to fit instead
			newHeight = boundHeight;
			// scale width to maintain aspect ratio
			newWidth = (newHeight * originalWidth) / originalHeight;
		}

		Image mini = fotos.get(num).getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);
		return new ImageIcon(mini);
	}

	public ArrayList<ImageIcon> getFotos() {
		return fotos;
	}

	public void setFotos(ArrayList<ImageIcon> fotos) {
		this.fotos = fotos;
	}

}
