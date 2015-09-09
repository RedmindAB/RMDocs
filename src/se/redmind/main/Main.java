package se.redmind.main;

public class Main {


	public static void main(String[] args) {

		if(args.length < 1){
			System.err.println("Arguments needed: [-p, -a]");
			System.exit(0);
		}
		ArgumentParser arg = new ArgumentParser(args);

		System.out.println(arg.toString());
	}

}
