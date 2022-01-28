package b100.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class StreamUtils {
	
	public static int cacheSize = 1024;
	
	public static void transferData(InputStream in, OutputStream out) throws IOException{
		byte[] temp = new byte[cacheSize];
		
		while(true) {
			int read = in.read(temp);
			if(read == -1) break;
			out.write(temp, 0, read);
		}
	}
	
}
