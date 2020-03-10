package org.tat.gginl.api.common;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVUtils {

	private static final String DEFAULT_SEPARATOR = ",";

	public static void writeLine(Writer w, List<String> values) throws IOException {
		writeLine(w, values, DEFAULT_SEPARATOR, "");
	}

	public static void writeLine(Writer w, List<String> values, String separators) throws IOException {
		writeLine(w, values, separators, "");
	}

	// https://tools.ietf.org/html/rfc4180
	private static String followCVSformat(String value) {

		String result = value;
		if (result.contains("\"")) {
			result = result.replace("\"", "\"\"");
		}
		return result;

	}

	public static void writeLine(Writer w, List<String> values, String separators, String customQuote) throws IOException {

		boolean first = true;

		// default customQuote is empty

		if (separators == "") {
			separators = DEFAULT_SEPARATOR;
		}

		StringBuilder sb = new StringBuilder();
		for (String value : values) {
			if (!first) {
				sb.append(separators);
			}
			if (customQuote == "") {
				sb.append(followCVSformat(value));
			} else {
				sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
			}

			first = false;
		}
		sb.append("\n");
//		sb.replace(sb.length()-16, sb.length(), "[)~=_(]");
//		sb.toString().replace("[)!|;(][)~=_(]", "[)~=_(]");
		String s = sb.toString();
//		String s = sb.toString().replaceAll("[)!|;(][)~=_(]", );
		w.append(sb.toString().replace("[)!|;(][)~=_(]", "[)~=_(]"));
		

	}

}
