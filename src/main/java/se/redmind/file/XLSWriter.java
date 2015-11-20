package se.redmind.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.jna.platform.FileUtils;
import jxl.*;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.Number;
import se.redmind.structure.Project;
import se.redmind.util.StringCustomizer;

public class XLSWriter {

    private Project project;
    private String path;

    public XLSWriter(String path) {
        this.path = path;
    }

    public XLSWriter() {
    }

    public void write(JsonObject jsonObject) {

        File xlsDirectory = new File(path + "xls");
        xlsDirectory.mkdirs();

//        WritableCellFormat methodNameFormat = new WritableCellFormat(
//                new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD));
//        WritableCellFormat classNameFormat = new WritableCellFormat(
//                new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD));
//        WritableCellFormat packNameFormat = new WritableCellFormat(
//                new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, true));
//        WritableCellFormat stepCellFormat = new WritableCellFormat();
//        WritableCellFormat commentCellFormat = new WritableCellFormat();
//        WritableCellFormat multipleKeyFormat = new WritableCellFormat(
//                new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, true));
//
//        try {
//            stepCellFormat.setWrap(true);
//            commentCellFormat.setWrap(true);
//            commentCellFormat.setVerticalAlignment(VerticalAlignment.TOP);
//            commentCellFormat.setAlignment(Alignment.LEFT);
//            stepCellFormat.setVerticalAlignment(VerticalAlignment.TOP);
//            stepCellFormat.setAlignment(Alignment.LEFT);
//        } catch (WriteException e) {
//            e.printStackTrace();
//        }
//
//        project = new Project();
//        project.setProjectName(jsonObject.get("ProjectName").getAsString());
//
//        WritableWorkbook workbook;
//        try {
//            workbook = Workbook.createWorkbook(new File(xlsDirectory, StringCustomizer.appendDateToFile(project) + ".xls"));
//
//            WritableSheet sheet = workbook.createSheet(jsonObject.get("ProjectName").getAsString(), 0);
//
//            sheet.setColumnView(0, 10);
//            sheet.setColumnView(1, 22);
//            sheet.setColumnView(2, 22);
//
//            int i = 0;
//
//            for (JsonElement element : jsonObject.get("Classes").getAsJsonArray()) {
//                String name = element.getAsJsonObject().get("ClassName").getAsString();
//                String packageName = element.getAsJsonObject().get("PackageName").getAsString();
//
//                sheet.addCell(new Label(0, i, packageName, packNameFormat));
//                i++;
//
//                sheet.addCell(new Label(0, i, name, classNameFormat));
//                i++;
//
//                int y = i + 1;
//
//                for (JsonElement methods : element.getAsJsonObject().get("Methods").getAsJsonArray()) {
//
//                    String methodName = "";
//
//                    for (Entry<String, JsonElement> methodArray : methods.getAsJsonObject().entrySet()) {
//
//                        if (methodArray.getKey().equals("MethodName")) {
//                            methodName = methodArray.getValue().getAsString();
//                            sheet.addCell(new Label(0, y, methodName, methodNameFormat));
//                            y++;
//                        }
//                        if (methodArray.getValue().isJsonArray()) {
//
//                            sheet.addCell(new Label(0, y, methodArray.getKey(), multipleKeyFormat));
//                            y++;
//                            sheet.addCell(new Label(0, y, "ID"));
//                            int ID = 1;
//                            int startIndex = y;
//                            List<String> keyList = new ArrayList<>();
//                            y++;
//
//                            for (JsonElement dupArray : methodArray.getValue().getAsJsonArray()) {
//                                for (Entry<String, JsonElement> dup : dupArray.getAsJsonObject().entrySet()) {
//                                    if (!keyList.contains(dup.getKey())) keyList.add(dup.getKey());
//                                }
//                                //get the values based by key and add new cell
//                                for (int x = 0; x <= keyList.size(); x++) {
//                                    String key;
//                                    try {
//                                        key = dupArray.getAsJsonObject().get(keyList.get(x)).getAsString();
//                                    } catch (IndexOutOfBoundsException | NullPointerException e) {
//                                        key = "";
//                                    }
//                                    sheet.addCell(new Label(x + 1, y, key, stepCellFormat));
//                                }
//
//                                sheet.addCell(new Number(0, y, ID, stepCellFormat));
//                                y++;
//                                ID++;
//                            }
//                            //Add the labels for duplicate keys
//                            for (int j = 0; j < keyList.size(); j++) {
//                                sheet.addCell(new Label(j + 1, startIndex, keyList.get(j), methodNameFormat));
//                            }
//
//                            if (jsonObject.get(methodName) != null) {
//                                String[] browsers = jsonObject.get(methodName).getAsString().split("\\s+");
//                                int index = keyList.size() + 1;
//                                for (int j = 0; j < browsers.length; j++) {
//                                    sheet.addCell(new Label(index + j, startIndex, browsers[j], methodNameFormat));
//                                }
//                            }
//
//                        } else {
//                            sheet.addCell(new Label(0, y, methodArray.getKey(), commentCellFormat));
//                            sheet.addCell(new Label(1, y, methodArray.getValue().getAsString(), commentCellFormat));
//                            y++;
//                        }
//                    }
//                    y += 2;
//                }
//                i = y;
//            }
////            workbook.write();
////            workbook.close();
//
//        } catch (IOException | WriteException e) {
//            e.printStackTrace();
//        }
    }

    public WritableWorkbook format(JsonObject jsonObject){

        WritableCellFormat methodNameFormat = new WritableCellFormat(
                new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD));
        WritableCellFormat classNameFormat = new WritableCellFormat(
                new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD));
        WritableCellFormat packNameFormat = new WritableCellFormat(
                new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, true));
        WritableCellFormat stepCellFormat = new WritableCellFormat();
        WritableCellFormat commentCellFormat = new WritableCellFormat();
        WritableCellFormat multipleKeyFormat = new WritableCellFormat(
                new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, true));

        try {
            stepCellFormat.setWrap(true);
            commentCellFormat.setWrap(true);
            commentCellFormat.setVerticalAlignment(VerticalAlignment.TOP);
            commentCellFormat.setAlignment(Alignment.LEFT);
            stepCellFormat.setVerticalAlignment(VerticalAlignment.TOP);
            stepCellFormat.setAlignment(Alignment.LEFT);
        } catch (WriteException e) {
            e.printStackTrace();
        }

        project = new Project();
        project.setProjectName(jsonObject.get("ProjectName").getAsString());

        WritableWorkbook workbook = null;
        File file = new File("tempfile1.xls");
        file.deleteOnExit();
        try {
            workbook = Workbook.createWorkbook(file);

            WritableSheet sheet = workbook.createSheet(jsonObject.get("ProjectName").getAsString(), 0);

            sheet.setColumnView(0, 10);
            sheet.setColumnView(1, 22);
            sheet.setColumnView(2, 22);

            int i = 0;

            for (JsonElement element : jsonObject.get("Classes").getAsJsonArray()) {
                String name = element.getAsJsonObject().get("ClassName").getAsString();
                String packageName = element.getAsJsonObject().get("PackageName").getAsString();

                sheet.addCell(new Label(0, i, packageName, packNameFormat));
                i++;

                sheet.addCell(new Label(0, i, name, classNameFormat));
                i++;

                int y = i + 1;

                for (JsonElement methods : element.getAsJsonObject().get("Methods").getAsJsonArray()) {

                    String methodName = "";

                    for (Entry<String, JsonElement> methodArray : methods.getAsJsonObject().entrySet()) {

                        if (methodArray.getKey().equals("MethodName")) {
                            methodName = methodArray.getValue().getAsString();
                            sheet.addCell(new Label(0, y, methodName, methodNameFormat));
                            y++;
                        }
                        if (methodArray.getValue().isJsonArray()) {

                            sheet.addCell(new Label(0, y, methodArray.getKey(), multipleKeyFormat));
                            y++;
                            sheet.addCell(new Label(0, y, "ID"));
                            int ID = 1;
                            int startIndex = y;
                            List<String> keyList = new ArrayList<>();
                            y++;

                            for (JsonElement dupArray : methodArray.getValue().getAsJsonArray()) {
                                for (Entry<String, JsonElement> dup : dupArray.getAsJsonObject().entrySet()) {
                                    if (!keyList.contains(dup.getKey())) keyList.add(dup.getKey());
                                }
                                //get the values based by key and add new cell
                                for (int x = 0; x <= keyList.size(); x++) {
                                    String key;
                                    try {
                                        key = dupArray.getAsJsonObject().get(keyList.get(x)).getAsString();
                                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                                        key = "";
                                    }
                                    sheet.addCell(new Label(x + 1, y, key, stepCellFormat));
                                }

                                sheet.addCell(new Number(0, y, ID, stepCellFormat));
                                y++;
                                ID++;
                            }
                            //Add the labels for duplicate keys
                            for (int j = 0; j < keyList.size(); j++) {
                                sheet.addCell(new Label(j + 1, startIndex, keyList.get(j), methodNameFormat));
                            }

                            if (jsonObject.get(methodName) != null) {
                                String[] browsers = jsonObject.get(methodName).getAsString().split("\\s+");
                                int index = keyList.size() + 1;
                                for (int j = 0; j < browsers.length; j++) {
                                    sheet.addCell(new Label(index + j, startIndex, browsers[j], methodNameFormat));
                                }
                            }

                        } else {
                            sheet.addCell(new Label(0, y, methodArray.getKey(), commentCellFormat));
                            sheet.addCell(new Label(1, y, methodArray.getValue().getAsString(), commentCellFormat));
                            y++;
                        }
                    }
                    y += 2;
                }
                i = y;
            }
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    public void write(WritableWorkbook workbook){
        File xlsDirectory = new File(path + "xls");
        xlsDirectory.mkdirs();
        try {
            workbook.setOutputFile(new File(xlsDirectory, StringCustomizer.appendDateToFile(project) + ".xls"));

            workbook.write();
            workbook.close();
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }

    public File getAsFile(WritableWorkbook workbook){
        File file = null;

        try {
            file = new File("tempfile.xls");
            file.deleteOnExit();
            workbook.setOutputFile(file);

            workbook.write();
            workbook.close();
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
        return file;
    }

//    public File getAsFile(WritableWorkbook workbook){
//
//        File file = getAsFile(workbook);
//        return file;
//    }
}
