package se.redmind.main;

import se.redmind.file.ArgumentParser;
import se.redmind.file.FileFinder;
import se.redmind.file.JavaFileReader;

public class Main {

	public static void main(String[] args) {
		
		if(args.length < 1){
			System.err.println("Arguments needed: [-p, -a]");
			System.exit(0);
		}
		
		ArgumentParser arg = new ArgumentParser(args);
		FileFinder finder = new FileFinder();
		JavaFileReader reader = new JavaFileReader();
		
		arg.parse();
		finder.pathWalker(arg.getPath());
		reader.readFile(finder.getFileList());

		
		System.out.println(arg.toString());
		finder.printList();
	}

}
