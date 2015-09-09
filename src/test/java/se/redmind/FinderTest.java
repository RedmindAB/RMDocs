package test.java.se.redmind;

import static org.junit.Assert.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import se.redmind.unused.FileTree;

public class FinderTest {

	FileTree tree = new FileTree();
	File file = new File("PathFinder/src/se/redmind/Test/Test.java/");
	File file2 = new File("PathFinder/src/se/redmind/PathFinder/Find2.java/");
	Map<String, Integer> map = new HashMap<String, Integer>();

	@Test
	public void whenItemAddedToListCheckSize() {

		tree.addToList(file);
		assertEquals(1, tree.getFileList().size());
		tree.addToList(file2);
		assertEquals(2, tree.getFileList().size());
	}

	@Test
	public void whenTwoItemsAddedToListCheckOccurrences() {
		String print ="";
		tree.addToList(file);
		tree.addToList(file2);
		map = tree.printFileOccurrences();
		for(String key: map.keySet()){
			print = key.toString()+ ": " + map.get(key).toString();
		}
		assertEquals(".java: 2", print);
	}

}
