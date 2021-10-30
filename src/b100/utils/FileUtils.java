package b100.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class FileUtils {
	
	public static boolean logging = true;
	
	private static void log(String s) {
		if(logging) {
			System.out.println(s);
		}
	}
	
	public static boolean createFile(File file) {
		return createFile(file, logging);
	}
	
	public static boolean createFolder(File file) {
		return file.mkdirs();
	}
	
	private static boolean createFile(File file, boolean log) {
		if(file == null)
			throw new NullPointerException();
		
		if(log && !file.exists()) System.out.println("Creating File: "+file.getAbsolutePath());
		
		try {
			file = file.getAbsoluteFile();
			file.getParentFile().mkdirs();
			return file.createNewFile();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getFileExtension(File file) {
		String path = file.getAbsoluteFile().getAbsolutePath();
		
		String ext = "";
		
		for(int i=0; i < path.length(); i++) {
			char c = path.charAt(i);
			
			if(c == '/' || c == '\\') {
				ext = "";
			}else if(c == '.') {
				ext = "";
			}else {
				ext += c;
			}
		}
		
		return ext;
	}
	
	public static String getFileExtension(String path) {
		return getFileExtension(new File(path));
	}
	
	public static void copyAll(String from, String to) {
		copyAll(new File(from), new File(to));
	}
	
	public static void copy(String from, String to) {
		copy(new File(from), new File(to));
	}
	
	public static void copyAll(File from, File to) {
		List<File> files = getAllFiles(from);
		
		String fromPath = from.getAbsolutePath();
		String toPath = to.getAbsolutePath();
		
		log("Copying "+files.size()+" files from "+from.getAbsolutePath()+" to "+to.getAbsolutePath());
		long start = System.nanoTime();
		
		for(File oldFile : files) {
			String oldPath = oldFile.getAbsolutePath();
			String newPath = oldPath;
			if(!newPath.startsWith(fromPath)) {
				throw new RuntimeException(oldFile.getAbsolutePath());
			}
			newPath = toPath + newPath.substring(fromPath.length());
			
			File newFile = new File(newPath);
			copy(oldFile, newFile);
		}
		
		long end = System.nanoTime();
		log("Copied "+files.size()+" files in "+((end-start)/1000000)+"ms");
	}
	
	public static void copy(File from, File to) {
		if(from == null)
			throw new NullPointerException();
		if(to == null)
			throw new NullPointerException();
		if(!from.exists())
			throw new RuntimeException("File doesn't exist: "+from.getAbsolutePath());
		if(!from.isFile()) {
			throw new RuntimeException("Not a file: "+from.getAbsolutePath());
		}
		
		InputStream inputStream;
		OutputStream outputStream;
		
		try {
			inputStream = new FileInputStream(from);
			
			createFile(to, false);
			outputStream = new FileOutputStream(to);
			
			while(inputStream.available() > 0) {
				outputStream.write(inputStream.read());
			}
			
			inputStream.close();
			outputStream.close();
		}catch (Exception e) {
			throw new RuntimeException("Could not copy from "+from.getAbsolutePath()+" to "+to.getAbsolutePath(), e);
		}
	}
	
	public static List<File> getAllFiles(File folder){
		return getAllFiles(new ArrayList<>(), folder);
	}
	
	public static List<File> getAllFiles(List<File> files, File folder) {
		File[] files2 = folder.listFiles();
		for(File file : files2) {
			if(file.isFile()) {
				files.add(file);
			}
			if(file.isDirectory()) {
				getAllFiles(files, file);
			}
		}
		return files;
	}
	
}
