package utils;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import periquito.MenuPrincipal;

public class Galeria {

	private ArrayList<ImageIcon> fotos = new ArrayList<>();
	public static ArrayList<String> urlFotos = new ArrayList<>();

	public static ArrayList<String> getUrlFotos() {
		return urlFotos;
	}

	public Galeria() {

		try {

			int numeroImagenes = MenuPrincipal.getListaImagenes().size();

			String[] lecturaurl = Metodos.leerFicheroArray("Config/Config2.txt", 2);

			InetAddress ping;

			ping = InetAddress.getByName(new URL("http://" + lecturaurl[0] + "/" + lecturaurl[1] + "/data/media/"
					+ MenuPrincipal.getCategorias().get(0) + "/" + MenuPrincipal.getListaImagenes().get(0)).getHost());

			if (!ping.getCanonicalHostName().equals("")) {

				for (int i = 0; i < numeroImagenes; i++) {
					urlFotos.add("http://" + lecturaurl[0] + "/" + lecturaurl[1] + "/data/media/"
							+ MenuPrincipal.getCategorias().get(i) + "/" + MenuPrincipal.getListaImagenes().get(i));
					fotos.add(new javax.swing.ImageIcon(ImageIO.read(new URL("http://" + lecturaurl[0] + "/"
							+ lecturaurl[1] + "/data/media/" + MenuPrincipal.getCategorias().get(i) + "/"
							+ MenuPrincipal.getListaImagenes().get(i)))));
				}

			}

		} catch (IOException e) {
			//
		}

	}

	public Icon getPreview(int num) {
		if (num >= 0 && num < fotos.size()) {
			Image mini = fotos.get(num).getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
			return new ImageIcon(mini);
		} else {
			return null;
		}
	}

	public Icon getFoto(int num, Dimension d) {

		// int originalWidth = fotos.get(num).getIconWidth();
		// int originalHeight = fotos.get(num).getIconHeight();

		int newWidth = 545;
		int newHeight = 453;

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
