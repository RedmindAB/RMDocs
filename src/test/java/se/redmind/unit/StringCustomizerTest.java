package se.redmind.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import se.redmind.structure.Project;
import se.redmind.util.StringCustomizer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringCustomizerTest {

	@Test
	public void assertThatReturnedClassNameIsCorrect() {
		
		assertEquals("HEJhejPÅDig", StringCustomizer.extractClassName("public abstract class HEJhejPÅDig {"));
		assertEquals("HEJhejPÅDig", StringCustomizer.extractClassName("public abstract class HEJhejPÅDig  {"));
		assertEquals("HEJhejPÅDig", StringCustomizer.extractClassName("public abstract class HEJhejPÅDig{"));
		assertEquals("HEJhejPÅDig", StringCustomizer.extractClassName("public class HEJhejPÅDig {"));
		assertEquals("HEJhejPÅDig", StringCustomizer.extractClassName("public abstract class HEJhejPÅDig extends asdjgj{"));
		assertEquals("HEJhejPÅDig", StringCustomizer.extractClassName("public abstract class HEJhejPÅDig implements huhu {"));
	}
	
	@Test
	public void assertThatReturnedPackageNameIsCorrect(){
		assertEquals("se.redmind.file", StringCustomizer.extractPackageName("package se.redmind.file;"));
	}
	
	@Test
	public void assertThatReturnedMethodLineIsCorrect(){
		assertEquals("hahaMethodName", StringCustomizer.extractMethodName("public void hahaMethodName(){/*"));
	}
	
	@Test
	public void assertThatReturnedAnnotationDataIsCorrect(){
		assertEquals("Author: Yokomito Pak-Sun", StringCustomizer.extractAnnotationData("/*@rmAuthor Yokomito Pak-Sun", "@rm"));
	}

    @Test
    public void returnedFileNameIsCorrect(){
        Project proj = new Project();
        proj.setProjectName("hej");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String date = sd.format(new Date());
		String expected = "hej" +"-"+ date;
		assertEquals(expected, StringCustomizer.appendDateToFile(proj));
    }

    @Test
    public void returnedArrayIsCorrect(){
        String str = "Step: [step] ett steg [expected] förväntan";
        String[] strArr = StringCustomizer.splitStringToArray(str);
        assertEquals(5, strArr.length);
        assertEquals("Step: ", strArr[0]);
        assertEquals(" ett steg ", strArr[2]);

        String str2 = "Step: ett steg utan brackets";
        String[] strArr2 = StringCustomizer.splitStringToArray(str2);
        assertEquals(1, strArr2.length);
    }
}
