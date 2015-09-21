package se.redmind.unused;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Özgür Eken
 *
 */


public class JavaFileReaderOz {

	private String path;	

	
	
	public String[] OpenFile() throws IOException{


		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);

		int numberOfLines = readLines();
		String[] textData = new String[numberOfLines];

		int i;

		for(i = 0; i < numberOfLines; i++){
			textData[i] = textReader.readLine();

		}
		textReader.close();
		return textData;

	}


	int readLines() throws IOException{

		FileReader file_to_read = new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);

		String aLine;

		int numberOfLines = 0;

		
		while (( aLine = bf.readLine()) != null ){	
			if(aLine.contains("@rm"))
			
			numberOfLines++;
		}
		bf.close();

		return numberOfLines;
	}	   
}
