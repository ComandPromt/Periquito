package utils;

import java.awt.Dimension;
import java.awt.Image;
import java.net.InetAddress;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import periquito.MenuPrincipal;

public class Galeria {

	private ArrayList<ImageIcon> fotos = new ArrayList<>();
	public static ArrayList<String> urlFotos = new ArrayList<>();
	public static ArrayList<String> idImagenes = new ArrayList<>();

	public static ArrayList<String> getUrlFotos() {
		return urlFotos;
	}

	public Galeria() {

		try {

			String ruta;

			int numeroImagenes = MenuPrincipal.getListaImagenes().size();

			String[] lecturaurl = Metodos.leerFicheroArray("Config2.txt", 2);

			InetAddress ping;

			ping = InetAddress.getByName(new URL("http://" + lecturaurl[0] + "/" + lecturaurl[1] + "/data/media/"
					+ MenuPrincipal.getCategorias().get(0) + "/" + MenuPrincipal.getListaImagenes().get(0)).getHost());

			if (!ping.getCanonicalHostName().equals("")) {

				if (numeroImagenes > 0) {

					String imagen;

					Connection conexion = Metodos.conexionBD();

					ResultSet rs = null;

					Statement s = conexion.createStatement();

					for (int i = 0; i < numeroImagenes; i++) {

						imagen = MenuPrincipal.getListaImagenes().get(i);

						ruta = "http://" + lecturaurl[0] + "/" + lecturaurl[1] + "/data/media/"
								+ MenuPrincipal.getCategorias().get(i) + "/" + imagen;

						urlFotos.add(ruta);

						fotos.add(new javax.swing.ImageIcon(ImageIO.read(new URL(ruta))));

						rs = s.executeQuery("SELECT image_id FROM " + MenuPrincipal.getLecturabd()[3]
								+ "images WHERE image_media_file='" + imagen + "'");
						rs.next();
						idImagenes.add(rs.getString("image_id"));
					}

					if (rs != null) {
						rs.close();
					}

					s.close();
					conexion.close();
				}

			}

		} catch (Exception e) {
			//
		}

	}

	public static void setIdImagenes(ArrayList<String> idImagenes) {
		Galeria.idImagenes = idImagenes;
	}

	public static ArrayList<String> getIdImagenes() {
		return idImagenes;
	}

	public Icon getPreview(int num) {

		if (num >= 0 && num < fotos.size()) {
			Image mini = fotos.get(num).getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
			return new ImageIcon(mini);
		}

		else {
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
