package se.redmind.structure;

import java.io.File;
import java.util.List;

public class FormatterInit {

    private Project project;
    private StructureFormatter formatter;
    private List<File> files;

    public FormatterInit(Project project, StructureFormatter formatter, List<File> list) {
        this.project = project;
        this.formatter = formatter;
        this.files = list;
    }

    public StructureFormatter format() {

        for (File file : files) {
            if (project.getProjectName().equals("")) {
                project.setProjectName(formatter.getProjectName(file));
            }
            StringBuilder stringBuilder = formatter.readFileToStringBuilder(file);
            String[] strArray = formatter.toArray(stringBuilder);
            formatter.buildClassObjectFromArray(strArray);
        }
        return formatter;
    }
}
