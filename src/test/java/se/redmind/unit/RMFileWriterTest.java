package se.redmind.unit;

import static org.junit.Assert.*;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Rule;
import org.junit.Test;

import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import se.redmind.file.RMFileWriter;
import se.redmind.structure.Project;



public class RMFileWriterTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    RMFileWriter writer;
    String path = System.getProperty("user.dir") + "/TestProject";
    Project proj = new Project();


	@Test
	public void expectSystemExitIfFormatIsIncorrect(){
        writer = new RMFileWriter(".asdf", path, proj);
        exit.expectSystemExitWithStatus(1);
        writer.printAndWrite();

	}
	
//	@Test
//	public void expectWriteMethodsToBeCalledBasedOnFormat(){
//        writer = mock(RMFileWriter.class);
//
//        when(writer.printAndWrite()).thenCallRealMethod(writer.printAndWrite());
//        writer.printAndWrite();
//        verify(writer, times(1)).writeToText();
//    }
}



