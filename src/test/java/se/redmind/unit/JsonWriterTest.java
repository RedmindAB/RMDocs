package se.redmind.unit;

import static org.junit.Assert.*;

import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import se.redmind.json.JsonWriter;
import se.redmind.structure.ClassObject;
import se.redmind.structure.Method;
import se.redmind.structure.Project;

import java.io.File;
import java.util.*;

public class JsonWriterTest {

	JsonWriter jw;
	Project proj = new Project();
	
	@Before
	public void before(){
		jw = new JsonWriter();
	}
	
	@Test
	public void ifprojectIsNullReturnCorrectString() {
        proj = null;
        assertEquals("Project is null", jw.convertToJson(proj));
	}
	
	@Test
	public void ifClassListIsNullReturnEmptyString(){
		jw = new JsonWriter();
		assertEquals("", jw.convertToJson(proj));
	}

    @Test
    public void returnedValueIsAString(){
        List<ClassObject> list = new ArrayList<>();
        list.add(new ClassObject());

        jw = new JsonWriter();
        proj.setProjectName("Project");
        proj.setClassObjects(list);
        assertEquals(String.class, jw.convertToJson(proj).getClass());
    }

    @Test
    public void testFilter(){
        List<ClassObject> classList = new ArrayList<>();
        List<Method> methodList1 = new ArrayList<>();
        List<Method> methodList2 = new ArrayList<>();
        List<Method> methodList3 = new ArrayList<>();

        Method m1 = getMethodObject("nyttBesokFormular", new ArrayList<>(), new LinkedHashMap<>());
        Method m2 = getMethodObject("nyttObjektTextruta", new ArrayList<>(), new LinkedHashMap<>());
        Method m3 = getMethodObject("nyttObjektUndergrupp", new ArrayList<>(), new LinkedHashMap<>());
        Method m4 = getMethodObject("skapaNyEnhet", new ArrayList<>(), new LinkedHashMap<>());

        methodList1.add(m1);
        methodList1.add(m2);
        methodList1.add(m3);
        methodList1.add(m4);

        Method m5 = getMethodObject("adresserAddEditFilterRemove", new ArrayList<>(), new LinkedHashMap<>());

        methodList2.add(m5);

        Method m6 = getMethodObject("skapaNyAvvikelserapport", new ArrayList<>(), new LinkedHashMap<>());
        Method m7 = getMethodObject("vidarerapporteraAvvikelse", new ArrayList<>(), new LinkedHashMap<>());

        methodList3.add(m6);
        methodList3.add(m7);

        ClassObject class1 = getClassObject("Installningar", "se.atlan.webdoc.tests.installningar", methodList1);
        ClassObject class2 = getClassObject("Adresser", "se.atlan.webdoc.tests.adresser", methodList2);
        ClassObject class3 = getClassObject("Avvikelserapporter", "se.atlan.webdoc.tests.journaler", methodList3);

        classList.add(class1);
        classList.add(class2);
        classList.add(class3);

        jw = new JsonWriter();
        proj.setProjectName("RMCustomerExample");
        proj.setClassObjects(classList);
        JsonObject obj = jw.convertToJsonObject(proj);
        obj = jw.filter(obj, new File("/Users/victormattsson/Documents/fileWithTestMethods.txt"));

    }

    private ClassObject getClassObject(String name, String packName, List<Method> methods){
        ClassObject co = new ClassObject();
        co.setName(name);
        co.setPackageName(packName);
        co.setMethodList(methods);
        return co;
    }

    private Method getMethodObject(String name, List<String> comments, LinkedHashMap<String, List<String>> duplicates){
        Method method = new Method();
        method.setMethodName(name);
        method.setCommentList(comments);
        method.setDuplicateMap(duplicates);
        return method;
    }


}
