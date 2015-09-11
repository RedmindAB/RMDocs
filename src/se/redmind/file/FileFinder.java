package se.redmind.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import se.redmind.unused.JavaFileReaderOz;

public class FileFinder {

	private File path;
	private List<File> fileList = new ArrayList<File>();
	
 
	public FileFinder(File path) {

		this.path = path;
		pathWalker(path);
	}

	public void pathWalker(File file){

		for(File f: file.listFiles()){
			if(f.isDirectory()){
				pathWalker(f);
			}else if(f.isFile()){
				addToList(f);
			}
		}
	}

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
