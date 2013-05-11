import java.io.File;
import java.io.FileNotFoundException;

public class Reader{
	
	private File file;
	
	public Reader(File file) throws FileNotFoundException{
		
		if (file.exists()){
			this.file = file;
		} else {
			throw new FileNotFoundException();
		}
		
	}
	
	public Reader readIn(){
		return this;
	}
	
	public Reader parse(){
		return this;
	}
	
	public String output(){
		return null;
	}
	
}