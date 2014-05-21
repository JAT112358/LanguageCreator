package display;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import utils.JFile;
import utils.Properties;
import components.Window;
import entities.LangDirectory;

public class Menu extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = -5336398584435950444L;

	// MENU
	private final JMenu				program;
	private final JMenu				file;
	private final JMenu				edit;
	private final JMenu				window;
	private final JMenu				help;
	
	// PROGRAM
	private final JMenuItem			about;
	private final JMenuItem			preferences;
	private final JMenuItem			exit;
	
	// FILE
	private final JMenu				create;
	private final JMenuItem			createDirectory;
	private final JMenuItem			openFile;
	private final JMenu				openRecently;
	private final JMenuItem			clearRecentlyFiles;
	private final JMenuItem			save;
	private final JMenuItem			rename;
	private final JMenuItem			print;

	// EDIT
	private final JMenu				delete;
	private final JMenuItem			deleteDirectory;
	private final JMenuItem			deleteLanguage;
	private final JMenuItem			selectAll;
	private final JMenuItem			find;
	
	// WINDOW
	private final JMenuItem			minimize;
	private final JMenuItem			maximize;
	private final JMenuItem			fullscreen;
	
	private Vector<JMenuItem>		recentlyFiles;
	
	public Menu() {
		super();
		
		Font plainFont = new Font("Arial", Font.PLAIN, 16);
		Font boldFont = new Font("Arial", Font.BOLD, 16);
		
		Insets insets = new Insets(5, 5, 5, 5);
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		
		program = new JMenu("LangCreatorPro");
		program.setFont(boldFont);
		program.setMargin(insets);
		program.setCursor(cursor);
		
		about = new JMenuItem("About");
		about.addActionListener(this);
		about.setFont(plainFont);
		about.setMargin(insets);
		about.setCursor(cursor);
		program.add(about);
		
		program.add(new JSeparator());
		
		preferences = new JMenuItem("Preferences...");
		preferences.addActionListener(this);
		preferences.setFont(plainFont);
		preferences.setMargin(insets);
		preferences.setCursor(cursor);
		program.add(preferences);
		
		program.add(new JSeparator());
		
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		exit.setFont(plainFont);
		exit.setMargin(insets);
		exit.setCursor(cursor);
		program.add(exit);
		
		file = new JMenu("File");
		file.setFont(plainFont);
		file.setMargin(insets);
		file.setCursor(cursor);
		
		create = new JMenu("New...");
		create.setFont(plainFont);
		create.setMargin(insets);
		create.setCursor(cursor);
		file.add(create);
		
		createDirectory = new JMenuItem("Directory");
		createDirectory.addActionListener(this);
		createDirectory.setFont(plainFont);
		createDirectory.setMargin(insets);
		createDirectory.setCursor(cursor);
		create.add(createDirectory);
		
		openFile = new JMenuItem("Open...");
		openFile.addActionListener(this);
		openFile.setFont(plainFont);
		openFile.setMargin(insets);
		openFile.setCursor(cursor);
		file.add(openFile);
		
		openRecently = new JMenu("Open recently");
		openRecently.addActionListener(this);
		openRecently.setFont(plainFont);
		openRecently.setMargin(insets);
		openRecently.setCursor(cursor);
		file.add(openRecently);
		
		addRecentlyFiles();
		
		clearRecentlyFiles = new JMenuItem("Clear");
		clearRecentlyFiles.addActionListener(this);
		clearRecentlyFiles.setFont(plainFont);
		clearRecentlyFiles.setMargin(insets);
		clearRecentlyFiles.setCursor(cursor);
		openRecently.add(clearRecentlyFiles);
		
		save = new JMenuItem("Save");
		save.addActionListener(this);
		save.setFont(plainFont);
		save.setMargin(insets);
		save.setCursor(cursor);
		file.add(save);
		
		file.add(new JSeparator());

		rename = new JMenuItem("Rename");
		rename.addActionListener(this);
		rename.setFont(plainFont);
		rename.setMargin(insets);
		rename.setCursor(cursor);
		file.add(rename);
		
		print = new JMenuItem("Print");
		print.addActionListener(this);
		print.setFont(plainFont);
		print.setMargin(insets);
		print.setCursor(cursor);
		file.add(print);
		
		edit = new JMenu("Edit");
		edit.setFont(plainFont);
		edit.setMargin(insets);
		edit.setCursor(cursor);
		
		delete = new JMenu("Delete");
		delete.setFont(plainFont);
		delete.setMargin(insets);
		delete.setCursor(cursor);
		edit.add(delete);
		
		deleteLanguage = new JMenuItem("Language");
		deleteLanguage.addActionListener(this);
		deleteLanguage.setFont(plainFont);
		deleteLanguage.setMargin(insets);
		deleteLanguage.setCursor(cursor);
		delete.add(deleteLanguage);
		
		deleteDirectory = new JMenuItem("Directory");
		deleteDirectory.addActionListener(this);
		deleteDirectory.setFont(plainFont);
		deleteDirectory.setMargin(insets);
		deleteDirectory.setCursor(cursor);
		delete.add(deleteDirectory);
		
		selectAll = new JMenuItem("Select all");
		selectAll.addActionListener(this);
		selectAll.setFont(plainFont);
		selectAll.setMargin(insets);
		selectAll.setCursor(cursor);
		edit.add(selectAll);
		
		edit.add(new JSeparator());

		find = new JMenuItem("Find");
		find.addActionListener(this);
		find.setFont(plainFont);
		find.setMargin(insets);
		find.setCursor(cursor);
		edit.add(find);		
		
		window = new JMenu("Window");
		window.setFont(plainFont);
		window.setMargin(insets);
		window.setCursor(cursor);
		
		minimize = new JMenuItem("Minimize");
		minimize.addActionListener(this);
		minimize.setFont(plainFont);
		minimize.setMargin(insets);
		minimize.setCursor(cursor);
		window.add(minimize);
		
		maximize = new JMenuItem("Maximize");
		maximize.addActionListener(this);
		maximize.setFont(plainFont);
		maximize.setMargin(insets);
		maximize.setCursor(cursor);
		window.add(maximize);
		
		fullscreen = new JMenuItem("Toggle Full Screen");
		fullscreen.addActionListener(this);
		fullscreen.setFont(plainFont);
		fullscreen.setMargin(insets);
		fullscreen.setCursor(cursor);
		window.add(fullscreen);
		
		help = new JMenu("Help");
		help.setFont(plainFont);
		help.setMargin(insets);
		help.setCursor(cursor);
		
		this.add(program);
		this.add(file);
		this.add(edit);
		this.add(window);
		this.add(help);
	}
	
	private void addRecentlyFiles() {
		Font plainFont = new Font("Arial", Font.PLAIN, 16);
		Insets insets = new Insets(5, 5, 5, 5);
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		
		if(Properties.getRecentlyFiles().size() != 0) {
			recentlyFiles = new Vector<JMenuItem>();
			for(final String path: Properties.getRecentlyFiles()) {
				JMenuItem aux = new JMenuItem(path);
				aux.addActionListener(new ActionListener()  {
					public void actionPerformed(ActionEvent e) {
						if(LangDirectory.getInstance().isSaved) {
							openDirectory(new File(path));
						}
					}
				});
				aux.setFont(plainFont);
				aux.setMargin(insets);
				aux.setCursor(cursor);
				recentlyFiles.add(aux);
				openRecently.add(aux);
			}
		} else {
			JMenuItem empty = new JMenuItem("Empty");
			empty.setFont(plainFont);
			empty.setMargin(insets);
			empty.setCursor(cursor);
			empty.setEnabled(false);
			openRecently.add(empty);
		}
		openRecently.add(new JSeparator());
	}
	
	private void openDirectory(File directory) {
		if(directory != null) {
			if(directory.exists()) {
				LangDirectory.getInstance().load(directory);
				Properties.addRecentlyPath(directory.getAbsolutePath());
				Start start = (Start) Window.getInstance().getContentPane();
				start.setLangEditor(new LangEditor(LangDirectory.getInstance()));
				Window.getInstance().updateMenu();
				Window.getInstance().setTitle("Language Creator PRO | " + directory.getAbsolutePath());
			} else {
				JOptionPane.showMessageDialog(Window.getInstance(), "This directory does't exist!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		//Start start = (Start) Window.getInstance().getContentPane();
		if(about == e.getSource())
		{
			
		} 
		else if(preferences == e.getSource())
		{
			
		}
		else if(exit == e.getSource())
		{
			Window.getInstance().dispose();
		}
		else if(createDirectory == e.getSource())
		{
			
		}
		else if(openFile == e.getSource())
		{
			if(LangDirectory.getInstance().isSaved) {
				File directory = JFile.openPath(JFileChooser.DIRECTORIES_ONLY);
				openDirectory(directory);
			}
		} 
		else if(clearRecentlyFiles == e.getSource())
		{
			Properties.removeRecentlyFiles();
			Window.getInstance().setJMenuBar(new JMenuBar());
		}
		else if(save == e.getSource())
		{
			
		}
		else if(rename == e.getSource())
		{
			String newname = (String) JOptionPane.showInputDialog(Window.getInstance(), "Rename directory", "Rename", 
					JOptionPane.PLAIN_MESSAGE, null, null, LangDirectory.getInstance().getDirectory().getName());
			if(newname != null && !newname.equals(LangDirectory.getInstance().getDirectory().getName())) {
				LangDirectory.getInstance().setDirectoryName(newname);
			}
			System.out.println("Changed");
		}
		else if(print == e.getSource())
		{
			
		}
		else if(deleteLanguage == e.getSource())
		{
			
		}
		else if(deleteDirectory == e.getSource())
		{
			
		}
		else if(selectAll == e.getSource())
		{
			
		}
		else if(find == e.getSource())
		{
			
		}
		else if(minimize == e.getSource())
		{
			Window.getInstance().minimize();
		}
		else if(maximize == e.getSource())
		{
			Window.getInstance().maximize();
		}
		else if(fullscreen == e.getSource())
		{
			
		}
	}
}