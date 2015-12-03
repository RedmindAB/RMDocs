package se.redmind.util;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Victor Mattsson on 2015-11-10.
 */
public final class Configuration {

    private Configuration() {
    }

    private final static ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

    public static void setFilterPath(File path) {
        map.put("filterfile", path);
    }

    public static File getFilterPath() {
        return (File) map.get("filterfile");
    }

    public static void setFilterBoolean(boolean filter) {
        map.put("filterbool", filter);
    }

    public static boolean getFilterBoolean() {
        return (boolean) map.get("filterbool");
    }

    public static void setOutputPath(String path) {
        map.put("outputpath", path);
    }

    public static String getOutputPath() {
        return String.valueOf(map.get("outputpath"));
    }
}
