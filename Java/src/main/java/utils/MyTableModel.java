package utils;

import javax.swing.table.DefaultTableModel;

class MyTableModel extends DefaultTableModel {

	String resultado;

	public Object getValueAt(int row, int column) {

		return ComprobarSha.getLectura().get(row);
	}

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return ComprobarSha.getLectura().size();
	}

}
