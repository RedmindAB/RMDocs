package se.redmind;

import org.junit.Test;
import se.redmind.util.Conditions;

import static org.junit.Assert.*;

/**
 * Created by victormattsson on 2015-10-23.
 */
public class ConditionsTest {

    String[] strArr;

    @Test
    public void testIsATestMethod(){

        strArr = new String[]{"/*", "* EN KOMMENTAR", "@Test", "public static void enMetod(){"};
        assertTrue(Conditions.isATestMethod(strArr, 3));
        strArr = new String[]{"/*", "* EN KOMMENTAR", "@Bean", "public static void enMetod(){"};
        assertFalse(Conditions.isATestMethod(strArr, 3));
    }

    @Test
    public void testContainsAnnotationMethod(){

        assertTrue(Conditions.containsAnnotation("This is a string with @rm annotation", "@rm"));
        assertTrue(Conditions.containsAnnotation("This is a string with @RM annotation", "@RM"));

        assertFalse(Conditions.containsAnnotation("This is a string without annotation", "@rm"));
        assertFalse(Conditions.containsAnnotation("This is a string with @RM annotation", "@rm"));
    }

    @Test
    public void testContainsPackageNameMethod(){
        assertTrue(Conditions.containsPackageName("This string contains package"));
        assertTrue(Conditions.containsPackageName("!#€%&/()=package"));

        assertFalse(Conditions.containsPackageName("This string does not"));
    }

    @Test
    public void testIsAMethodMethod(){
        assertTrue(Conditions.isAMethod("public void ahuhasudh(){"));
        assertTrue(Conditions.isAMethod("public static void kekeke() {"));
        assertTrue(Conditions.isAMethod("private void"));
        assertTrue(Conditions.isAMethod("private static void"));
        assertTrue(Conditions.isAMethod("public static final void"));
        assertTrue(Conditions.isAMethod("private static final void"));
        assertTrue(Conditions.isAMethod("public final void metod"));
        assertTrue(Conditions.isAMethod("private final void metod"));

        assertFalse(Conditions.isAMethod("String does not contain a real method name"));
    }

    @Test
    public void testContainsClassNameMethod(){

        assertTrue(Conditions.containsClassName("public class aClass{"));
        assertTrue(Conditions.containsClassName("public abstract class aClass(){"));
        assertTrue(Conditions.containsClassName("public final class aClass(){"));
        assertTrue(Conditions.containsClassName("private class aClass(){"));

        assertFalse(Conditions.containsClassName("this contains final {"));
        assertFalse(Conditions.containsClassName("this contains abstract {"));
        assertFalse(Conditions.containsClassName("public static void method() {"));
    }

}