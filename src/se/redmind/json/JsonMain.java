package se.redmind.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonMain {

	public static void main(String[] args) {

		Project proj = new Project();
		proj.setProjectName("MockProject");
		
		Package pack = new Package();
		pack.setName("se.redmind.mockpackage1");
		
		CurrentFile file = new CurrentFile();
		file.setName("MockTestClass1");
		Method method1 = new Method();
		method1.setMethodName("newTextDocument");
		method1.setAuthor("Kalle Svensson");
		method1.setDate("2015-09-09");
		method1.setDescription("Skapa ett nytt text document");
		method1.setPriority("High");
		method1.setSummary("Skapa ett nytt text document");
		method1.setStep("lägg till data i Frukt tabellen");

		Method method3 = new Method();
		method3.setMethodName("createNewFrame");
		method3.setAuthor("Peter Sjögren");
		method3.setDate("2015-09-01");
		method3.setDescription("Skapa ett gränsnitt i Swing");
		method3.setPriority("Medium");
		method3.setSummary("skapa ett gränssnitt");
		method3.setStep("lägg till JPanel");
		//Set methods in file 1
		file.setMethods(new Method[]{method1, method3});
		//Set package 1
		pack.setFiles(new CurrentFile[] {file});
		
		Package pack2 = new Package();
		pack2.setName("se.redmind.mockpackage2");
		
		CurrentFile file2 = new CurrentFile();
		file2.setName("MockTestClass2");
		Method method2 = new Method();
		method2.setMethodName("newDatabase");
		method2.setAuthor("Andranik Manokjan");
		method2.setDate("2015-09-01 ");
		method2.setDescription("lägg till data i databas tabeller");
		method2.setPriority("High");
		method2.setSummary("lägg till data i databasen");
		method2.setStep("skapa en instans av klassen MockClass3.java");
		//Set methods in file 2
		file2.setMethods(new Method[]{method2});
		//Set package 2
		pack2.setFiles(new CurrentFile[]{file2});
		
		proj.setPackages(new Package[] {pack, pack2});

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		System.out.println(gson.toJson(proj));
	}

}
