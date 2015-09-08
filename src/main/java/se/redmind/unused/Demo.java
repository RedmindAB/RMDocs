package se.redmind.unused;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Demo{


	static String data = "";

	static ArrayList<String> Name = new ArrayList<>();
	static ArrayList<String> Stad = new ArrayList<>();		
	static File file = new File("Test.txt");

	
	public static void main(String[] args){	

		
		getNames(args);
		getStates(args);
		nameValue();
		stateValue();
		
		
		try{
			FileWriter fileWritter = new FileWriter(file.getName(),true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(data);
			bufferWritter.close();

		}

		catch(IOException e){
			e.printStackTrace();
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e1) {
						System.out.println("Done");
					e1.printStackTrace();
				}

			}
		}
			}

			public static String[] getNames(String[]args){

				for(int i = 0; i < args.length; i++)
					if(args[i].equals("-n")){
						Name.add(args[i+1]);
						data += args[i+1]+"\n";
					}
				return args;

			}     
			public static String[] getStates(String[]args){
				for(int i = 0; i < args.length; i++)
				if(args[i].equals("-s")){
					Stad.add(args[i+1]);
					data += args[i+1]+"\n";
					
				}
				return args;
			}
			
			public static String nameValue(){
				for (String value : Name) {  
					System.out.println("Namn : " + value);
				}
				return " ";
			}

			public static String stateValue(){
				for(String value : Stad){
					System.out.println("Stad : " + value);
				}
				return " ";
			}
	}

