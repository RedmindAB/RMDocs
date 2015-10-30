package se.redmind.file;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import jxl.*;
import jxl.write.*;
import se.redmind.structure.ClassObject;
import se.redmind.structure.Method;
import se.redmind.structure.Project;
import se.redmind.util.StringCustomizer;

public class XLSWriter {

    private Project project;
    private String path;

    public XLSWriter(String path, Project project) {
        this.project = project;
        this.path = path;
    }

    public void write() {

        File xlsDirectory = new File(path + "xls");
        xlsDirectory.mkdirs();

        WritableCellFormat methodNameFormat = new WritableCellFormat(
                new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD));
        WritableCellFormat classNameFormat = new WritableCellFormat(
                new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD));
        WritableCellFormat packNameFormat = new WritableCellFormat(
                new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, true));

        WritableWorkbook workbook;
        try {
            workbook = Workbook.createWorkbook(new File(xlsDirectory, StringCustomizer.appendDateToFile(project) + ".xls"));

            WritableSheet sheet = workbook.createSheet(project.getProjectName(), 0);

            int i = 0;

            for (ClassObject co : project.getClassObjects()) {

                String packName = co.getPackageName();
                String className = co.getName();

                sheet.addCell(new Label(0, i, packName, packNameFormat));
                i++;

                sheet.addCell(new Label(0, i, className, classNameFormat));
                i++;

                int y = i + 1;

                for (Method m : co.getMethodList()) {

                    sheet.addCell(new Label(0, y, m.getMethodName(), methodNameFormat));
                    y++;
                    for (String s : m.getCommentList()) {
                        String[] arr = s.split(":");
                        sheet.addCell(new Label(0, y, arr[0]));
                        sheet.addCell(new Label(1, y, arr[1]));
                        y++;
                    }
                    for (Entry<String, List<String>> entry : m.getDuplicateMap().entrySet()) {
                        for (String dup : entry.getValue()) {
                            String[] arr = dup.split(":");
                            sheet.addCell(new Label(0, y, arr[0]));
                            sheet.addCell(new Label(1, y, arr[1]));
                            y++;
                        }
                    }
                    y += 2;
                }
                i = y;
            }

            workbook.write();
            workbook.close();

        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }
}
