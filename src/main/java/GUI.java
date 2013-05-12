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

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	protected JButton open, search, parse, save;
	protected JTextArea console;
	private File inFolder;
	private MapReader mr;
	
	private GUI(){
		
		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());
		
		JPanel buttonbar = new JPanel();
		buttonbar.setLayout(new FlowLayout());
		
		open = new JButton("Open");
		open.setVerticalTextPosition(AbstractButton.CENTER);
		open.setHorizontalTextPosition(AbstractButton.LEADING);
		open.setActionCommand("open");
		open.addActionListener(this);
		
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
			in.addChoosableFileFilter(new FileFilter(){

				@Override
				public boolean accept(File f) {
					return f.getName().endsWith(".sts");
				}

				@Override
				public String getDescription() {
					return "Stat file type.";
				}
				
			});
			in.setAcceptAllFileFilterUsed(true);
			
			if (in.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				inFolder = in.getSelectedFile();
				try {
					mr = new MapReader(inFolder);
					print("Directory selected.");
					search.setEnabled(true);
				} catch (FileNotFoundException e1) {
					print("An error occured opening that directory. Make sure you " +
							"have permission to open the directory.");
				}
			} else {
				print("No folder selected.");
			}
			print("Scanning \""+inFolder.getPath()+"\" for hst files.");
			mr.getFiles();
			print("Finished scanning. Found "+mr.getFileCount()+" files.");
			parse.setEnabled(true);
		}else if (e.getActionCommand().equals("parse")){
			print("Parsing files. This can take some time.");
			mr.scanFiles();
			print("Results are ready. Please save the results to a file.");
			save.setEnabled(true);
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
	
	*/
	
	
	
}
