import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;


/**
 * Notes about the program:
 * 
 * <p>
 * Drawbacks: The number column value can not exceed 999 without unexpected results. No fix atm.
 * 
 * </p>
 * @author Tyler
 *
 */
public class Main{
	
	public static void print(String s){
		System.out.println("[StatParser] "+s);
	}
	
	public static void testReader(String file){
		print("This is a testing feature... It will test the reader on a single file and will not save results.");
		Reader r;
		try {
			r = new Reader(new File(file)).readIn().parse();
			print("This is the resulting output for this participant.");
			System.out.println(r.output());
		} catch (FileNotFoundException e) {
			System.out.println("The file entered cannot be found. Please try again.");
		}
		
	}
	
	
	
	public static void readDirectory(String directory, String output){
		try {
			MapReader mr = new MapReader(directory, output);
			mr.getFiles().scanFiles().save();
		} catch (FileNotFoundException e) {
			System.out.println("Sorry but the directory does not seem to exist.");
		} catch (Exception e) {
			System.out.println("An error has occured. System will exit.");
			System.exit(1);
		}
	}

	/**
	 * Creates the reader and sorter.
	 * @param args
	 */
	
	public static void main(String[] args){

		if (args.length == 1){
			Main.testReader(args[0]);
		} else if (args.length == 0){
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					GUI.createAndShowGUI();
				}
			});
		} else if (args.length>2){
			print("Too many args... Ignoring the extra ones.");
			print("Usage: hstparser [dir] [output.file]");
			print("Dir is the directory you wish to look in and output.file is the filename to print to.");
		} else {
			Main.readDirectory(args[0], args[1]);
		}

	}
	
}