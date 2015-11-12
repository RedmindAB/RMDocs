package se.redmind.util;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Victor Mattsson on 2015-11-10.
 */
public class Configuration {

    private static ConcurrentHashMap map = new ConcurrentHashMap<>();

    public static void setFilterPath(File path){
        map.put("filterfile", path);
    }

    public static File getFilterPath(){
       return (File) map.get("filterfile");
    }

    public static void setFilterBoolean(boolean filter){
        map.put("filterbool", filter);
    }

    public static boolean getFilterBoolean(){
        return (boolean) map.get("filterbool");
    }
}
