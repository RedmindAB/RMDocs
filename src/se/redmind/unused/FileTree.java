package se.redmind.unused;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileTree {

	private int fileCount = 0;
	private List<String> fileList = new ArrayList<String>();

	public int getFileCount() {
		return fileCount;
	}
	
	public void addToList(File file) {

        String ext = "";
        int i = file.getName().lastIndexOf(".");
        if(i > 0){
        	ext = file.getName().substring(i);
        	fileList.add(ext);
        }
	}
	
	public List<String> getFileList(){
		return fileList;
	}
	
	public void printList(){
		System.out.println("***File types***");
		for(String list: fileList){
			System.out.println(list);
		}
	}

	public void pathWalker(File file){

		printFiles(file);
		for(File f: file.listFiles()){
			if(f.isDirectory()){
				pathWalker(f);
			}else if(f.isFile()){
				addToList(f);
				fileCount++;
			}
		}
	}

	public void printFiles(File file){
		for(File f: file.listFiles()){
			if(f.isFile()){
				System.out.println("File: " + f.getPath());
			}
		}
	}
	
    public Map<String, Integer> printFileOccurrences(){
    	System.out.println("**File Occurrences**");
    	Map<String, Integer> count = new HashMap<String, Integer>();
    	for(String a: fileList){
    		Integer i = count.get(a);
    		if(i == null){
    			i = 0;
    		}
    		count.put(a, i+1);
    	}
    	for(String key: count.keySet()){
    		System.out.println(key.toString()+ ": " + count.get(key).toString());
    	}
		return count;
    }
}
