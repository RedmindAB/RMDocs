package se.redmind.main;

import se.redmind.file.ArgumentParser;
import se.redmind.file.FileFinder;
import se.redmind.file.RMFileReader;
import se.redmind.file.RMFileWriter;

public class Main {

	public static void main(String[] args) {
		
		if(args.length < 1){
			System.err.println("Arguments needed: [-p, -a]");
			System.exit(0);
		}
		
		ArgumentParser arg = new ArgumentParser(args);
		FileFinder finder = new FileFinder();
		RMFileReader reader = new RMFileReader();
		RMFileWriter writer;
		
		arg.parse();
		finder.pathWalker(arg.getPath());
		reader.readFile(finder.getFileList());
		
		for(int i = 0; i < reader.getAnnotatedFiles().size(); i++){
			writer = new RMFileWriter(RMFileWriter.TEXT);
			writer.printAndWrite(reader.getAnnotatedFiles().get(i));
		}
		
		System.out.println(arg.toString());
		finder.printList();
	}

}
