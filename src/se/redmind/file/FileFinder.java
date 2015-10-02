package se.redmind.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * FileFinder --- Walks the given filepath, searches every sub-directory 
 * after .java-files and sets them in a list containing java.io Files
 * @author Victor Mattsson
 *
 */
public class FileFinder {

	private List<File> fileList = new ArrayList<File>();
	private String fileFormat;

	public FileFinder(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	
	public String getFileFormat() {
		return fileFormat;
	}

	/**
	 * Iterates a list of files based on the given path, If it's a
	 * directory it calls itself with that directory, else if it's a file
	 * it adds it to a list
	 * @param file is the given path to walk
	 */
	public void pathWalker(File file){
		for(File f: file.listFiles()){
			try{
				if(f.isDirectory()){
					pathWalker(f);
				}else if(f.isFile()){
					addToList(f);
				}
			}catch(NullPointerException e){
				System.err.println("Bad path. Please choose absolute path to project.");
				System.exit(1);
			}
		}
	}

	/**
	 * If the files has the extension specified by the fileFormat, add it to the list
	 * @param file the given file to add
	 */
	public void addToList(File file) {
		int i = file.getName().lastIndexOf(fileFormat);
		if(i > 0){
			fileList.add(file);
		}
	}

	public List<File> getFileList() {
		if(fileList.isEmpty()){
			System.err.println("No " + fileFormat + " files in project.");
			System.exit(1);
		}
		return fileList;
	}
}
