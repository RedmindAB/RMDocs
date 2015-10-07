package test.java.se.redmind;

import static org.junit.Assert.*;

import org.junit.Test;

import se.redmind.util.StringCustomizer;

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
		assertEquals("public void hahaMethodName", StringCustomizer.extractMethodName("public void hahaMethodName(){/*"));
	}
	
	@Test
	public void assertThatReturnedAnnotationDataIsCorrect(){
		assertEquals("Author: Yokomito Pak-Sun", StringCustomizer.extractAnnotationData("/*@rmAuthor Yokomito Pak-Sun"));
	}
}
