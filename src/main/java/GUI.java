import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;



public class GUI extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7632908392074124134L;
	
	protected JButton open, parse;
	protected JTextArea console;
	private MapReader mr;
	
	private GUI(){
		
		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());
		
		JPanel buttonbar = new JPanel();
		buttonbar.setLayout(new FlowLayout());
		
		open = new JButton("Open");
		open.setVerticalTextPosition(SwingConstants.CENTER);
		open.setHorizontalTextPosition(SwingConstants.LEADING);
		open.setActionCommand("open");
		open.addActionListener(this);
		
		parse = new JButton("Parse");
		parse.setVerticalTextPosition(SwingConstants.CENTER);
		parse.setHorizontalTextPosition(SwingConstants.LEADING);
		parse.setActionCommand("parse");
		parse.setEnabled(false);
		parse.addActionListener(this);
		
		console = new JTextArea();
		console.setEditable(false);
		console.setWrapStyleWord(true);
		console.setLineWrap(true);
		console.setPreferredSize(new Dimension(450,200));
		JScrollPane cscroll = new JScrollPane(console);
		cscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		
		buttonbar.add(open);
		buttonbar.add(parse);
		
		root.add(buttonbar, BorderLayout.CENTER);
		root.add(console, BorderLayout.PAGE_END);
		
		add(root);
	}
	
	@Override
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
			in.setMultiSelectionEnabled(true);
			if (in.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				mr = new MapReader(in.getSelectedFiles());
				print("Directory selected.");
				mr.getFiles();
				print("Finished scanning. Found "+mr.getFileCount()+" files.");
				parse.setEnabled(true);
			} else {
				print("No folder selected.");
			}
			
		}else if (e.getActionCommand().equals("parse")){
			print("Parsing files. This can take some time.");
			mr.parse();
			StringSelection ss = new StringSelection(mr.output());
			Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
			cb.setContents(ss, null);
			print("Results are ready. The results have been copied to your clipboard. Paste them to your master coding sheet.");
		}
	}
	
	protected void print(String s){
		console.append("[StatParser] "+s+"\r\n");
	}

	public static void createAndShowGUI() {
		JFrame frame = new JFrame("StatParser");
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
		
		gui.print("Reader is ready. Please choose the files or directories you would like to parse.");
	}
	
	
	
}
