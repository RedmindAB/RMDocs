package se.redmind.util;

/**
 * Created by victormattsson on 2015-10-23.
 */
public class Conditions {

    public static boolean isATestMethod(String[] strArr, int y) {
        for (int x = y; x > 0; x--) {
            if (strArr[x].contains("@Test")) {
                return true;
            }
        }
        return false;
    }


    public static boolean containsAnnotation(String str, String annotation) {
        if (str.contains(annotation)) {
            return true;
        }
        return false;
    }

    public static boolean containsPackageName(String str) {
        if (str.contains("package")) {
            return true;
        }
        return false;
    }

    public static boolean isAMethod(String str) {

        if (str.contains("public void"))
            return true;
        else if (str.contains("private void"))
            return true;
        else if (str.contains("public final void"))
            return true;
        else if (str.contains("private final void"))
            return true;
        else if (str.contains("public static void"))
            return true;
        else if (str.contains("private static void"))
            return true;
        else if (str.contains("public static final void"))
            return true;
        else if (str.contains("private static final void"))
            return true;
        return false;
    }

    public static boolean containsClassName(String str) {

        if (str.contains("public class"))
            return true;
        else if (str.contains("public abstract class"))
            return true;
        else if (str.contains("public final class"))
            return true;
        else if (str.contains("private class"))
            return true;

        return false;
    }
}
