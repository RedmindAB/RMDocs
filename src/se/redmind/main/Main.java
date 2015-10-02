package se.redmind.main;

import java.io.File;
import java.util.List;

import se.redmind.file.ArgumentParser;
import se.redmind.file.FileFinder;
import se.redmind.file.RMFileReader;
import se.redmind.file.RMFileWriter;
import se.redmind.file.ReadConfigProperties;
import se.redmind.structure.Project;
import se.redmind.structure.StructureFormater;

public class Main {

	public static void main(String[] args){

		if(args.length < 1){
			System.err.println("Arguments needed: [-p, -a, -f, -o]");
			System.exit(0);
		}

		ReadConfigProperties rc = new ReadConfigProperties();
		String propValue = rc.getPropValues();
		if(propValue == null){
			rc.createConfigFile();
		}
		
		List<File> fileList;
		ArgumentParser arg = new ArgumentParser(args);
		arg.parse();
		FileFinder finder = new FileFinder(arg.getFileFormat());
		RMFileReader reader = new RMFileReader();
		RMFileWriter writer;

		finder.pathWalker(arg.getPath());
		fileList = reader.readFile(finder.getFileList(), arg.getAnnotation());

		/*
		 * Section to structure the filelist to POJO
		 */
		StructureFormater formater = new StructureFormater();
		String [] strArray;
		StringBuilder sb;
		Project proj = new Project();

		for(File file: fileList){
			if(proj.getProjectName().equals("")){
				proj.setProjectName(formater.getProjectName(file));
			}
			sb = formater.readFileToStringBuilder(file);
			strArray = formater.toArray(sb);
			formater.readArray(strArray);
		}

		proj.setClassList(formater.getClassList());

		/*
		 * Section to write the POJOs to specified format
		 */
		writer = new RMFileWriter(arg.getOutputFormat(), rc.getPath());
		writer.printAndWrite(proj);
		
	}

}
