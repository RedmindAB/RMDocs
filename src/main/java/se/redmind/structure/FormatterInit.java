package se.redmind.structure;

import java.io.File;
import java.util.List;

public class FormatterInit {

    StringBuilder sb;
    String[] strArray;
    Project proj;
    StructureFormatter formater;
    List<File> list;

    public FormatterInit(Project proj, StructureFormatter formater, List<File> list) {
        this.proj = proj;
        this.formater = formater;
        this.list = list;
    }

    public StructureFormatter format() {

        for (File file : list) {
            if (proj.getProjectName().equals("")) {
                proj.setProjectName(formater.getProjectName(file));
            }
            sb = formater.readFileToStringBuilder(file);
            strArray = formater.toArray(sb);
            formater.buildClassObjectFromArray(strArray);
        }
        return formater;
    }
}
