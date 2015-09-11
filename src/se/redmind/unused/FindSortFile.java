package se.redmind.unused;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FindSortFile {


	public static void main(String[] args){

		//Skriv in önskad filväg 	
		File fileDir = new File("/Users/Oz/Documents/workspace/Test/src");
		if(fileDir.isDirectory()){

			List<String> listFile = Arrays.asList(fileDir.list());
		
			System.out.println("Listar filerna som är osorterade \n");

			for(String s:listFile){
				System.out.println(s + "     --->      <Senaste ändring> : " +new Date(fileDir.lastModified()));
			}

			Collections.sort(listFile);
			System.out.println("---------------------------------------");
			System.out.println("Sorterar filenamnet i stigande ordning \n");

			for(String s:listFile){
				System.out.println(s+ "      --->   <Senaste ändring> :" + new Date(fileDir.lastModified()));
			}
		}
		else{
			System.out.println(fileDir.getAbsolutePath() + " is not a directory");
		}

	}	
}