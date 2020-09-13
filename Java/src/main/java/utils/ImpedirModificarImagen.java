package utils;

import java.util.TimerTask;

import javax.swing.ImageIcon;

import periquito.VerUser;

public class ImpedirModificarImagen extends TimerTask {



	@Override
	public void run() {		VerUser.avatar.setImagenDefault(new ImageIcon(VerUser.class.getResource("/imagenes/abrir.png")));


	}

}
