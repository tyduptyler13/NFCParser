import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;



public class GUI extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7632908392074124134L;
	protected JButton open, search, parse, save;
	protected JTextArea console;
	private File inFolder;
	private RecursiveReader rr;
	
	private GUI(){
		
		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());
		
		JPanel buttonbar = new JPanel();
		buttonbar.setLayout(new FlowLayout());
		
		open = new JButton("Open Folder");
		open.setVerticalTextPosition(AbstractButton.CENTER);
		open.setHorizontalTextPosition(AbstractButton.LEADING);
		open.setActionCommand("open");
		open.addActionListener(this);
		
		search = new JButton("Search");
		search.setVerticalTextPosition(AbstractButton.CENTER);
		search.setHorizontalTextPosition(AbstractButton.LEADING);
		search.setActionCommand("search");
		search.setEnabled(false);
		search.addActionListener(this);
		
		parse = new JButton("Parse");
		parse.setVerticalTextPosition(AbstractButton.CENTER);
		parse.setHorizontalTextPosition(AbstractButton.LEADING);
		parse.setActionCommand("parse");
		parse.setEnabled(false);
		parse.addActionListener(this);
		
		save = new JButton("Save");
		save.setVerticalTextPosition(AbstractButton.CENTER);
		save.setHorizontalTextPosition(AbstractButton.LEADING);
		save.setActionCommand("save");
		save.setEnabled(false);
		save.addActionListener(this);
		
		console = new JTextArea();
		console.setEditable(false);
		console.setWrapStyleWord(true);
		console.setPreferredSize(new Dimension(450,200));
		JScrollPane cscroll = new JScrollPane(console);
		cscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		
		buttonbar.add(open);
		buttonbar.add(search);
		buttonbar.add(parse);
		buttonbar.add(save);
		
		root.add(buttonbar, BorderLayout.CENTER);
		root.add(console, BorderLayout.PAGE_END);
		
		add(root);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("open")){
			JFileChooser in = new JFileChooser("Open");
			in.setCurrentDirectory(new File("."));
			in.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			in.setAcceptAllFileFilterUsed(false);
			
			if (in.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				inFolder = in.getSelectedFile();
				try {
					rr = new RecursiveReader(inFolder);
					print("Directory selected.");
					search.setEnabled(true);
				} catch (FileNotFoundException e1) {
					print("An error occured opening that directory. Make sure you " +
							"have permission to open the directory.");
				}
			} else {
				print("No folder selected.");
			}
		}else if (e.getActionCommand().equals("search")){
			print("Scanning \""+inFolder.getPath()+"\" for hst files.");
			rr.getFiles();
			print("Finished scanning. Found "+rr.getFileCount()+" files.");
			parse.setEnabled(true);
		}else if (e.getActionCommand().equals("parse")){
			print("Parsing files. This can take some time.");
			rr.scanFiles();
			print("Results are ready. Please save the results to a file.");
			save.setEnabled(true);
		}else if (e.getActionCommand().equals("save")){
			print("This file will print out as plain text and is copyable to excel.");
			JFileChooser out = new JFileChooser("Save");
			out.setCurrentDirectory(new File("."));
			FileFilter filter = new FileFilter(){

				@Override
				public boolean accept(File f) {
					return f.getName().endsWith(".txt");
				}

				@Override
				public String getDescription() {
					return "Text files";
				}
				
			};
			out.setFileFilter(filter);
			if (out.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
				rr.setOut(out.getSelectedFile());
				print("File selected. Saving...");
				try {
					rr.save();
					print("Results saved to the file.");
				} catch (Exception e1) {
					print("An error occured when writing to the file. Try a different one. "+
							"It may be write protected or you may not have permission in this "+
							"directory.");
				}
			} else {
				print("No file selected.");
			}
		}
	}
	
	protected void print(String s){
		console.append("[HST Reader] "+s+"\r\n");
	}

	public static void createAndShowGUI() {
		JFrame frame = new JFrame("HST Reader");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			Main.print("The UI could not find the default system look and feel!");
		} catch (InstantiationException e) {
			Main.print("The UI could not create the look and feel.");
		} catch (IllegalAccessException e) {
			Main.print("The UI could not access the look and feel.");
		} catch (UnsupportedLookAndFeelException e) {
			Main.print("The UI look and feel is unsupported.");
		}finally{
			Main.print("The UI will use the default look and feel.");
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GUI gui = new GUI();
		gui.setOpaque(true);
		frame.setContentPane(gui);
		frame.setSize(500, 300);
		
		frame.pack();
		frame.setVisible(true);
		
		gui.print("Reader is ready. Please choose a folder.");
	}
	
	
	
}
