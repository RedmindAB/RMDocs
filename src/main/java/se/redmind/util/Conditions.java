package se.redmind.util;

/**
 * Created by victormattsson on 2015-10-23.
 */
public class Conditions {

    public static boolean isATestMethod(String[] lineArray, int iteration) {
        for (int x = iteration; x > 0; x--) {
            if (lineArray[x].contains("@Test")) {
                return true;
            }
        }
        return false;
    }


    public static boolean containsAnnotation(String line, String annotation) {
        return line.contains(annotation);
    }

    public static boolean containsPackageName(String line) {
        return line.contains("package");
    }

    public static boolean isAMethod(String line) {

        if (line.contains("public void"))
            return true;
        else if (line.contains("private void"))
            return true;
        else if (line.contains("public final void"))
            return true;
        else if (line.contains("private final void"))
            return true;
        else if (line.contains("public static void"))
            return true;
        else if (line.contains("private static void"))
            return true;
        else if (line.contains("public static final void"))
            return true;
        else if (line.contains("private static final void"))
            return true;
        return false;
    }

    public static boolean containsClassName(String line) {

        if (line.contains("public class"))
            return true;
        else if (line.contains("public abstract class"))
            return true;
        else if (line.contains("public final class"))
            return true;
        else if (line.contains("private class"))
            return true;

        return false;
    }
}
