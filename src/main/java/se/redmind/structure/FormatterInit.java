package se.redmind.structure;

import java.io.File;
import java.util.List;

public class FormatterInit {

    StringBuilder stringBuilder;
    String[] strArray;
    Project project;
    StructureFormatter formatter;
    List<File> list;

    public FormatterInit(Project project, StructureFormatter formatter, List<File> list) {
        this.project = project;
        this.formatter = formatter;
        this.list = list;
    }

    public StructureFormatter format() {

        for (File file : list) {
            if (project.getProjectName().equals("")) {
                project.setProjectName(formatter.getProjectName(file));
            }
            stringBuilder = formatter.readFileToStringBuilder(file);
            strArray = formatter.toArray(stringBuilder);
            formatter.buildClassObjectFromArray(strArray);
        }
        return formatter;
    }
}
