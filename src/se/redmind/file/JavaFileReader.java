package se.redmind.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	
	private void printAnnotations(File file){
		
		System.out.println("\n**Class**\n" + file.getName());
		try {
			String currLine;
			br = new BufferedReader(new FileReader(file));

			while((currLine = br.readLine()) != null){
				if(currLine.contains("@rm")){
					System.out.println(currLine.replaceAll("\\*", "").trim());
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

	public void readFile(List<File> file){

		for(File f: file){

			try {
				String currLine;
				br = new BufferedReader(new FileReader(f));

				while((currLine = br.readLine()) != null){
					if(currLine.contains("@rm")){
						annotatedFiles.add(f);
						printAnnotations(f);
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
