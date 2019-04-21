package utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class ImageResizer {

	static int maxWidth = 800;

	private ImageResizer() {
		super();
	}

	static int maxHeight = 630;

	public static void copyImage(String filePath, String copyPath) {
		try {

			BufferedImage bimage = loadImage(filePath);

			if (bimage.getHeight() > bimage.getWidth()) {
				int heigt = (bimage.getHeight() * maxWidth) / bimage.getWidth();
				bimage = resize(bimage, maxWidth, heigt);
				int width = (bimage.getWidth() * maxHeight) / bimage.getHeight();
				bimage = resize(bimage, width, maxHeight);
			} else {
				int width = (bimage.getWidth() * maxHeight) / bimage.getHeight();
				bimage = resize(bimage, width, maxHeight);
				int heigt = (bimage.getHeight() * maxWidth) / bimage.getWidth();
				bimage = resize(bimage, maxWidth, heigt);
			}
			saveImage(bimage, copyPath);
		} catch (Exception e) {
			Metodos.mensaje("Error al redimensionar la imagen ubicada en " + filePath, 1);
		}
	}

	public static BufferedImage loadImage(String pathName) {
		BufferedImage bimage = null;
		try {
			bimage = ImageIO.read(new File(pathName));
		} catch (Exception e) {
			Metodos.mensaje("Error al leer la imagen ubicada en " + pathName, 1);
		}
		return bimage;
	}

	public static void saveImage(BufferedImage bufferedImage, String pathName) {
		try {
			String format = (pathName.endsWith(".png")) ? "png" : "jpg";
			File file = new File(pathName);
			file.getParentFile().mkdirs();
			ImageIO.write(bufferedImage, format, file);
		} catch (IOException e) {
			Metodos.mensaje("Error al guardar la imagen ubicada en " + pathName, 1);
		}
	}

	public static BufferedImage resize(BufferedImage bufferedImage, int newW, int newH) {
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();
		BufferedImage bufim = new BufferedImage(newW, newH, bufferedImage.getType());
		Graphics2D g = bufim.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
		g.dispose();
		return bufim;
	}
}