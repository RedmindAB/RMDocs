package se.redmind.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;



/**
 * JavaFileReader --- This class reads and writes the given files and comments to text files.
 * @author Victor Mattsson , Özgür Eken
 *
 */


public class JavaFileReader {

	private List<File> annotatedFiles = new ArrayList<>();
	private BufferedReader br = null;
	
	public void printList(){
		System.out.println("***List of java files containing @rm***");
		for(File f: annotatedFiles){
			System.out.println(f.getName());
		}
		System.out.println();
	}

	/**
	 * This method creates a textfile based on the given filename
	 * and searches for the annotations in the java file and writes them to a textfile 
	 * @param file The java file to be read. 
	 */
	private void printAndWrite(File file){

		System.out.println("\n**Class**\n" + file.getName().replace(".java", ""));
		try {
			PrintWriter writer = new PrintWriter("./Resources/lib/"+file.getName().replace(".java", "") + ".txt", "UTF-8");

			try {
				String currLine;
				br = new BufferedReader(new FileReader(file));

				while((currLine = br.readLine()) != null){
					if(currLine.contains("@rm")){
						System.out.println(currLine.replaceAll("[\\*\\/]", "").trim());
						writer.println(currLine.replaceAll("[\\*\\/]", "").trim());
					}
					if(currLine.contains("@Test")){
						String newLine = br.readLine();
						System.out.println(newLine.replaceAll("[\\{]", "").trim() + "\n");
						writer.println(newLine.replaceAll("[\\{]", "").trim() + "\n");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try{
					if(br != null)br.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		

	}
	/**
	 * This method searches for the annotation @rm in the file
	 * and adds it to a list.
	 * @param fileList List of files to be read.
	 */
	public void readFile(List<File> fileList){

		for(File f: fileList){

			try {
				String currLine;
				br = new BufferedReader(new FileReader(f));

				while((currLine = br.readLine()) != null){
					if(currLine.contains("@rm")){
						annotatedFiles.add(f);
						printAndWrite(f);
						break;
					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try{
					if(br != null)br.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		printList();
	}
}
