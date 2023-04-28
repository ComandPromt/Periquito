package utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import pdf.Url;

public abstract class MetodosPdf {

	public static void crearPdf(LinkedList<String> url, String dato, String plantilla)
			throws Exception, FileNotFoundException {

		String tampleFile = "plantillas/" + plantilla;

		Map<String, Object> variables = new HashMap<String, Object>();

		List<Url> users = createUserList(url);

		variables.put("users", users);

		variables.put("urli", dato);

		String htmlStr = HtmlGenerator.generate(tampleFile, variables);

		PdfGenerator.generate(htmlStr, new FileOutputStream(Metodos.extraerNombreArchivo("pdf")));
	}

	private static List<Url> createUserList(LinkedList<String> urls) {

		List<Url> users = new ArrayList<Url>();

		for (int i = 0; i < urls.size(); i++) {

			users.add(createUrl(urls.get(i)));

		}

		return users;
	}

	private static Url createUrl(String urli) {

		Url url = new Url();

		url.setUsername(urli);

		return url;
	}
}
