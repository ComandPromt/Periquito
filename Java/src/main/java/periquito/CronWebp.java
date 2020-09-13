package periquito;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.TimerTask;

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

						String carpeta = MenuPrincipal.getLectura()[0] + MenuPrincipal.getSeparador() + "Video_2_Gif"
								+ MenuPrincipal.getSeparador();

						MenuPrincipal.listaImagenes = Metodos
								.directorio(carpeta + "output" + MenuPrincipal.getSeparador(), "gif", true, false);

						for (int x = 0; x < MenuPrincipal.listaImagenes.size(); x++) {

							Files.move(
									FileSystems.getDefault()
											.getPath(carpeta + "output" + MenuPrincipal.getSeparador()
													+ MenuPrincipal.listaImagenes.get(x)),
									FileSystems.getDefault()
											.getPath(MenuPrincipal.directorioImagenes + MenuPrincipal.getSeparador()
													+ MenuPrincipal.listaImagenes.get(x)),
									StandardCopyOption.REPLACE_EXISTING);

						}

						Metodos.mensaje("Video 2 GIF terminado correctamente", 2);

						Metodos.abrirCarpeta(MenuPrincipal.directorioImagenes);

					} catch (Exception e) {

					}

					break;

				case "gif_frames":

					Metodos.mensaje("Los GIFs se han extraido correctamente", 2);

					Metodos.abrirCarpeta(MenuPrincipal.getLectura()[0] + MenuPrincipal.getSeparador() + "Gif_extractor"
							+ MenuPrincipal.getSeparador() + "output");

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