package periquito;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import utils.Metodos;

public class CronWebp extends TimerTask {

	@Override
	public void run() {

		try {

			MenuPrincipal.crontabWebp = Metodos.leerFicheroArray("llamada_python.txt", 1)[0];

			if (MenuPrincipal.crontabWebp != null) {

				switch (MenuPrincipal.crontabWebp) {

				case "gif":

					try {

						File af = new File("Config/Config.txt");

						if (af.exists()) {
							MenuPrincipal.subida();
						}

					}

					catch (Exception e) {
					}

					break;

				case "video_gif":

					try {

						Metodos.moverArchivos(
								Metodos.directorio(MenuPrincipal.getLectura()[0] + "Video_2_Gif"
										+ MenuPrincipal.getSeparador() + "output" + MenuPrincipal.getSeparador(), "gif",
										true, true),
								MenuPrincipal.getSeparador(), MenuPrincipal.getDirectorioImagenes(), false, 3);

						Metodos.mensaje("Video 2 GIF terminado correctamente", 2);

						Metodos.abrirCarpeta(MenuPrincipal.directorioImagenes);

					} catch (Exception e) {

					}

					break;

				case "gif_frames":

					Metodos.mensaje("Los GIFs se han extraido correctamente", 2);

					String[] options = { "<html><h2>[1] Subir al CMS</h2></html>",
							"<html><h2>[2] Mover a la carpeta imagenes</h2></html>",
							"<html><h2>[3] Abrir carpeta de salida</h2></html>",
							"<html><h2>[4] Borrar Gifs</h2></html>" };

					ImageIcon icon = new ImageIcon(MenuPrincipal.class.getResource("/imagenes/utilities.png"));

					String opcion = (String) JOptionPane.showInputDialog(null, "", "Seleccione una opcion",
							JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);

					opcion = opcion.replace("<html><h2>[", "");

					int numerOpcion = Integer.parseInt(opcion.substring(0, 1));

					LinkedList<String> listaImagenes = new LinkedList<>();

					String carpeta = "";

					carpeta = MenuPrincipal.getLectura()[0] + "Gif_extractor" + MenuPrincipal.getSeparador() + "output"
							+ MenuPrincipal.getSeparador();

					switch (numerOpcion) {

					case 1:

						listaImagenes = Metodos.directorio(carpeta, ".", true, false);

						Metodos.moverArchivos(listaImagenes, carpeta, MenuPrincipal.getDirectorioImagenes(), false, 1);

						MenuPrincipal.uploadImages();

						break;

					case 2:

						LinkedList<String> directorios = new LinkedList<>();

						directorios = Metodos.directorio(carpeta, ".", false, false);

						String folder = "";

						for (int i = 0; i < directorios.size(); i++) {

							folder = carpeta + directorios.get(i) + MenuPrincipal.getSeparador();

							listaImagenes = Metodos.directorio(folder, ".", true, true);

							String salida = MenuPrincipal.getDirectorioImagenes() + MenuPrincipal.getSeparador()
									+ "Gif_extractor" + MenuPrincipal.getSeparador() + directorios.get(i)
									+ MenuPrincipal.getSeparador();

							File directorio = new File(salida);

							directorio.mkdir();

							if (!listaImagenes.isEmpty()) {
								Metodos.moverArchivos(listaImagenes, MenuPrincipal.getSeparador(), salida, false, 1);
							}

							directorio = new File(folder);

							directorio.delete();

						}

						Metodos.mensaje("Todos los frames se han movido correctamente", 2);

						break;

					case 3:
						Metodos.abrirCarpeta(MenuPrincipal.getLectura()[0] + MenuPrincipal.getSeparador()
								+ "Gif_extractor" + MenuPrincipal.getSeparador() + "output");
						break;

					case 4:
						Metodos.eliminarArchivos(MenuPrincipal.getLectura()[0] + MenuPrincipal.getSeparador()
								+ "Gif_extractor" + MenuPrincipal.getSeparador());

						break;

					}

					int resp = JOptionPane.showConfirmDialog(null,
							"<html><h2>¿Quieres borrar el/los GIF/s?</h2></html>");

					if (resp == 0) {
						Metodos.eliminarArchivos(MenuPrincipal.getLectura()[0] + MenuPrincipal.getSeparador()
								+ "Gif_extractor" + MenuPrincipal.getSeparador());
					}

					break;

				case "video_frames":

					Metodos.mensaje("Los videos se han pasado correctamente a fotogramas", 2);

					Metodos.abrirCarpeta(MenuPrincipal.getLectura()[0] + MenuPrincipal.getSeparador()
							+ "Frame_Extractor" + MenuPrincipal.getSeparador() + "output");

					break;

				}

				MenuPrincipal.timer.cancel();

				MenuPrincipal.timer.purge();

				Metodos.crearFichero("Config/llamada_python.txt", "", false);

			}

		}

		catch (IOException e) {
			//
		}

	}

}