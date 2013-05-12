import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MapReader{
	
	private ArrayList<File> files = new ArrayList<File>();
	private ArrayList<Reader> readers;
	
	public MapReader(File directory){
		
		this.files.add(directory);
		
	}
	
	public MapReader(File[] files){
		
		for (File file : files){
			this.files.add(file);
		}
		
	}
	
	public MapReader getFiles(){
		
		ArrayList<File> files = new ArrayList<File>();
		
		for (File file : this.files){
			
			if (file.isDirectory()){
				files.addAll(getFiles(file));
			} else if (file.isFile()){
				files.add(file);
			}
			
		}
		
		this.files = files;
		
		return this;
	}
	
	/**
	 * Recursively finds all sts files.
	 * @param directory Top Directory.
	 * @return All sub files with .sts extension.
	 */
	private ArrayList<File> getFiles(File directory){
		
		ArrayList<File> files = new ArrayList<File>();
		
		for (File file : directory.listFiles()){
			
			if (file.isDirectory()){
				files.addAll(getFiles(file));
			} else if (file.getName().endsWith(".sts")){
				files.add(file);
			}
			
		}
		
		return files;
		
	}
	
	public MapReader parse(){
		
		readers = new ArrayList<Reader>(files.size());
		
		for (File file : files){
			
			if (file.isFile()){
				try {
					readers.add(new Reader(file).readIn());
				} catch (FileNotFoundException e) {
					Main.print("File ("+file.getName()+") was not found. Skipping.");
				}
				
			}
			
		}
		
		return this;
		
	}
	
	public String output(){
		
		String result = "";
		
		for (Reader reader : readers){
			result += reader.output();
		}
		
		return result;
		
	}
	
	public int getFileCount(){
		return files.size();
	}
	
	
}
