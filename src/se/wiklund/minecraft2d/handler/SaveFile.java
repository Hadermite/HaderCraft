package se.wiklund.minecraft2d.handler;

import java.io.File;
import java.io.IOException;

public class SaveFile {
	
	private File file;
	private boolean isJustCreated;
	
	public SaveFile(String fileName) {
		File dir = new File(FileHandler.DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		file = new File(FileHandler.DIR, fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
				isJustCreated = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public File getFile() {
		return file;
	}
	
	public boolean isJustCreated() {
		return isJustCreated;
	}
}
