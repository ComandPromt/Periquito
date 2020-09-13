package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Data provider for excel
 *
 * @author angel
 */
public final class FakeDataProvider {

	/**
	 * Return the columns name for the table
	 */
	public static List<String> getTableHeaders(LinkedList<String> lista) {

		List<String> tableHeader = new ArrayList<String>();

		for (int i = 0; i < lista.size(); i++) {
			tableHeader.add(lista.get(i));
		}

		return tableHeader;
	}

	public static List<List<String>> getTableContent(int numberOfRows, boolean sql, String consulta,
			LinkedList<String> datos) {
		List<String> row = null;

		if (numberOfRows <= 0) {
			throw new IllegalArgumentException("The number of rows must be a positive integer");
		}

		List<List<String>> tableContent = new ArrayList<List<String>>();

		if (sql) {
			try {
				String SQL = "SELECT * FROM 4images_users";

				Connection conexion = Metodos.conexionBD();

				Statement s = conexion.createStatement();

				ResultSet rs = s.executeQuery(SQL);

				while (rs.next()) {

					tableContent.add(row = new ArrayList<String>());

					row.add(rs.getString("user_name"));
					row.add(rs.getString("nacionalidad"));
					row.add(rs.getString("user_email"));
					row.add(rs.getString("avatar"));

				}
			} catch (Exception e) {
				//
			}
		}

		else {
			for (int i = 0; i < datos.size(); i++) {
				tableContent.add(row = new ArrayList<String>());
				row.add(datos.get(i));
			}
		}

		return tableContent;
	}

}
