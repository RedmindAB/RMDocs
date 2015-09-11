package se.redmind.main;

import se.redmind.file.FileFinder;

public class Main {


	public static void main(String[] args) {

				
		if(args.length < 1){
			System.err.println("Arguments needed: [-p, -a]");
			System.exit(0);
		}
		ArgumentParser arg = new ArgumentParser(args);
		FileFinder finder = new FileFinder(arg.getPath());

		System.out.println(arg.toString());
		finder.printList();

	}

}
