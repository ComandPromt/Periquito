package utils;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class ImageRenderer extends DefaultTableCellRenderer {

	JLabel lbl = new JLabel();

	ImageIcon icon = new ImageIcon(getClass().getResource("../imagenes/check.png"));

	ImageIcon error = new ImageIcon(getClass().getResource("../imagenes/error.png"));

	String resultado;

	static int size = ImagenesSha.comprobacionSha.size();

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if (ImagenesSha.comprobacionSha.get(row).equals("SI")) {
			lbl.setIcon(icon);

			resultado = "Esta en la base de datos";
		}

		else {
			lbl.setIcon(error);
			resultado = "Se puede insertar en la base de datos";
		}

		lbl.setText(resultado);

		return lbl;
	}

}