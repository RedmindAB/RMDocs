package se.redmind.main;

import java.io.File;
import java.util.List;

import se.redmind.file.ArgumentParser;
import se.redmind.file.FileFinder;
import se.redmind.file.RMFileReader;
import se.redmind.file.RMFileWriter;
import se.redmind.file.ConfigProperties;
import se.redmind.structure.Project;
import se.redmind.structure.StructureFormater;

public class Main {

	public static void main(String[] args){

		if(args.length < 1){
			System.err.println("Arguments needed: [-p, -o]");
			System.exit(0);
		}

		ConfigProperties conf = new ConfigProperties();
		String propValue = conf.getPropValues();
		if(propValue == null){
			conf.createConfigFile();
		}
		
		List<File> fileList;
		ArgumentParser arg = new ArgumentParser(args);
		arg.parse();
		FileFinder finder = new FileFinder(arg.getReadFormat());
		RMFileReader reader = new RMFileReader();

		finder.pathWalker(arg.getPath());
		fileList = reader.readFile(finder.getFileList(), arg.getAnnotation());

		/*
		 * Section to structure the filelist to POJO
		 */
		StructureFormater formater = new StructureFormater(arg.getAnnotation());
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
		proj.setUnCommentedMethods(formater.getUnCommentedMethods());

		/*
		 * Section to write the POJOs to specified format
		 */
		for (String format : arg.getOutputFormats()) {
			new Thread(new RMFileWriter(format, conf.getPath(), proj)).start();
		}
		
		new RMFileWriter(conf.getPath(), proj).writeReport(proj.getUnCommentedMethods());
	}

}
