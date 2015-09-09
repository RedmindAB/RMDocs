package se.redmind.unused;

import java.io.File;



public class Find2 {
	
	
	public static void main(String[] args) {

		FileTree tree = new FileTree();
		
		File originalPath = new File("/home/victor/redmind-workspace");
		
		tree.pathWalker(originalPath);
		System.out.println("Number of files: " + tree.getFileCount());
		tree.printFileOccurrences();
	}
	
}
