package se.redmind.unused;

import java.io.*;

public class Search {	

	static String s;

	static String word ="London";		

	static int count = 0;

	public static void main(String args[]) throws IOException{
    
	findText();
		
	}
	public static int findText(){
		
		FileReader file = null;
		
		try {
			
			file = new FileReader("Test.txt");
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BufferedReader b =new BufferedReader(file);
		
		try {
			while ((s=b.readLine())!=null){
				if(s.contains(word))
					count++; 		
			}
			System.out.println("Found : " + count + " " + word);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	return count;
       
	}	
}