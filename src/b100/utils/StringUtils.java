package b100.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import b100.utils.err.MissingFileException;

public abstract class StringUtils {
	
	public static String loadFromFile(String path) {
		if(path == null)
			throw new NullPointerException();
		
		return loadFromFile(new File(path));
	}
	
	public static String loadFromFile(File file) {
		if(file == null)
			throw new NullPointerException();
		if(!file.exists() || !file.isFile()) {
			throw new MissingFileException(file);
		}
		
		try{
			return readInputStream(new FileInputStream(file));
		}catch (Exception e) {
			throw new RuntimeException("Error while reading file", e);
		}
	}
	
	public static void saveToFile(String path, String content) {
		saveToFile(new File(path), content);
	}
	
	public static void saveToFile(File file, String content) {
		if(file == null)
			throw new NullPointerException();
		if(content == null)
			throw new NullPointerException();
		
		try {
			FileWriter fw = new FileWriter(file);
			
			FileUtils.createFile(file);
			fw.write(content);
			
			fw.close();
		}catch (Exception e) {
			throw new RuntimeException(file.getAbsolutePath(), e);
		}
	}
	
	public static String readInputStream(InputStream inputStream) {
		if(inputStream == null)
			throw new NullPointerException();
		
		try {
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(reader);
			
			String string = "";
			String line = null;
			boolean first = true;
			
			while(true) {
				line = br.readLine();
				
				if(line == null)
					break;
				
				if(first) {
					first = false;
				}else {
					line = "\n" + line;
				}
				
				string += line;
			}
			
			br.close();
			reader.close();
			
			return string;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getWebsiteContent(String url) {
		URL u = null;
		InputStream is = null;
		
		try {
			u = new URL(url);
		} catch (Exception e) {
			throw new RuntimeException(url, e);
		}
		
		try {
			is = u.openStream();
		}catch (Exception e) {
			throw new RuntimeException(u.toString(), e);
		}
		
		return readInputStream(is);
	}
	
	public static void validateNotEmpty(String string) {
		if(string == null)
			throw new NullPointerException();
		if(string.length() == 0)
			throw new RuntimeException("Empty String");
	}
	
	public static String[] toArray(List<String> list) {
		String[] array = new String[list.size()];
		
		for(int i=0; i < array.length; i++) {
			array[i] = list.get(i);
		}
		
		return array;
	}
	
}
