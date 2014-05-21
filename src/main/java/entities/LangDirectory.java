package entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Vector;

public class LangDirectory {

	public static LangDirectory 			instance;
	
	public File								directory;
	public Vector<File> 					filesDirectory;
	public Vector<HashMap<String, String>> 	directoryFilesHashMap;
	public boolean							isSaved;

	private LangDirectory() {
		this.isSaved = true;
	}
	
	public boolean load(File directory) {
		instance.directory = directory;
		File[] files = directory.listFiles(new FilenameFilter() {
			public boolean accept(File directory, String fileName) {
				return fileName.endsWith(".lang");
			}
		});
		instance.filesDirectory = new Vector<File>();
		instance.directoryFilesHashMap = new Vector<HashMap<String, String>>();
		try {
			for(int i=0; i<files.length; i++) {
				instance.filesDirectory.add(files[i]);
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(files[i].toString()));
				@SuppressWarnings("unchecked")
				HashMap<String, String> hashMap = (HashMap<String, String>) ois.readObject();
				ois.close();
				instance.directoryFilesHashMap.add(hashMap);
			}
			return true;
		} catch (IOException | ClassNotFoundException e1) {
			return false;
		}
	}
	
	public boolean remove() {
		return instance.directory.delete();
	}
	
	public void save() {
		try {			
			for(int i=0; i < instance.filesDirectory.size(); i++)
			{
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(instance.filesDirectory.get(i)));
				oos.writeObject(instance.directoryFilesHashMap.get(i));
				oos.close();
				System.out.println("Saved: " + instance.filesDirectory.get(i));

			}
			instance.isSaved = true;
		} catch (IOException e) {
			e.printStackTrace();
			instance.isSaved = false;
		}
	}
	
	public boolean existLabel(final String label) {
		for(int i=0; i<instance.directoryFilesHashMap.size(); i++)
		{
			if(instance.directoryFilesHashMap.get(i).containsKey(label))
			{
				return true;
			}
		}
		return false;
	}
	
	public void addLabel(final String label) {
		for(int i=0; i<instance.directoryFilesHashMap.size(); i++)
		{
			instance.directoryFilesHashMap.get(i).put(label, "");
		}
		instance.isSaved = false;
	}
	
	public void deleteLabel(final String label) {
		for(int i=0; i<instance.directoryFilesHashMap.size(); i++)
		{
			if(instance.directoryFilesHashMap.get(i).containsKey(label))
			{
				instance.directoryFilesHashMap.get(i).remove(label);
			}
		}
		instance.isSaved = false;
	}
	
	public Vector<HashMap<String, String>> getDirectoryFilesHashMap() {
		return directoryFilesHashMap;
	}

	public Vector<File> getFilesDirectory() {
		return filesDirectory;
	}

	public void setFilesDirectory(Vector<File> filesDirectory) {
		this.filesDirectory = filesDirectory;
	}

	public void setDirectoryFilesHashMap(
			Vector<HashMap<String, String>> directoryFilesHashMap) {
		this.directoryFilesHashMap = directoryFilesHashMap;
	}
	
	public File getDirectory() {
		return this.directory;
	}
	
	public void setDirectoryName(String newName) {
		
		File aux = instance.directory.getAbsoluteFile();
		String oldDirectory = aux.getName();
		String path = aux.getAbsolutePath().replace(oldDirectory, newName);
		instance.directory = new File(path);
		
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(instance.directory.getAbsolutePath()));
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0; i < instance.filesDirectory.size(); i++) {
			File aux2 = instance.filesDirectory.get(i).getAbsoluteFile();
			String path2 = aux2.getAbsolutePath().replace("/" + oldDirectory, "/" + newName);
			instance.filesDirectory.set(i, new File(path2));
		}
		save();
		aux.delete();
	}

	public static LangDirectory getInstance() {
		if(instance == null) {
			instance = new LangDirectory();
		}
		return instance;
	}
}
