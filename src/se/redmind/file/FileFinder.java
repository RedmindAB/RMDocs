package se.redmind.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * FileFinder --- Walks the given filepath, searches every sub-directory 
 * after .java-files and sets them in a list containing java.io Files
 * @author victor
 *
 */
public class FileFinder {

//	private File path;
	private List<File> fileList = new ArrayList<File>();

	public FileFinder(File path) {
//		this.path = path;
		pathWalker(path);
	}

	/**
	 * Iterates a list of files based on the given path, If it's a
	 * directory it calls itself with that directory, else if it's a file
	 * it adds it to a list
	 * @param file is the given path to walk
	 */
	public void pathWalker(File file){
		for(File f: file.listFiles()){
			if(f.isDirectory()){
				pathWalker(f);
			}else if(f.isFile()){
				addToList(f);
			}
		}
	}

	/**
	 * If the files has the extension .java add it to the list
	 * @param file the given file to add
	 */
	public void addToList(File file) {
		int i = file.getName().lastIndexOf(".java");
		if(i > 0){
			fileList.add(file);
		}
	}

	public void printList(){
		System.out.println("***.java-Files***");
		for(File list: fileList){
			System.out.println(list.getName());
		}
	}
}
