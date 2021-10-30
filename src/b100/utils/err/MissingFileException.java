package b100.utils.err;

import java.io.File;

public class MissingFileException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private File file;

	public MissingFileException(File file) {
		this.file = file;
	}
	
	public File getFile() {
		return file;
	}

}
