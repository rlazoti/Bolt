package br.com.boltframework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.com.boltframework.Bolt;

public class FileUtils {

	public static String obtainContentOfFile(String fileName) throws IOException {
		if (StringUtils.isBlank(fileName)) {
		 throw new IllegalArgumentException("The 'filename' argument can't be null.");
		}
		
	  StringBuilder content = new StringBuilder();
		InputStream is = Bolt.class.getResourceAsStream(fileName);
		BufferedReader input = new BufferedReader(new InputStreamReader(is));
		try {
			String line = null;
			while ((line = input.readLine()) != null) {
				content.append(line);
				content.append(System.getProperty(Constants.LINE_SEPARATOR_PROPERTY));
			}
		}
		finally {
			input.close();
		}

		return content.toString();
	}

}
