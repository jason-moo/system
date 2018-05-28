package me.gacl.generate;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

public class FreeMakerToXML {

    private static Configuration configuration = null;

    private static Template t = null;

    public static final String xmlTempPath = "xmlTemp.ftl";

    public static final String daoTempPath = "daoTemp.ftl";

    public static final String pojoTempPath = "pojoTemp.ftl";

    public static final String dtoTempPath = "dtoTemp.ftl";

    public static final String serviceTempPath = "serviceTemp.ftl";

    public static final String serviceImplTempPath = "serviceImplTemp.ftl";

    static {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(FreeMakerToXML.class, "/");
    }


    public static void createWord(Map<String,Object> dataMap,String fileName,String templte){
        File outFile = new File("/Users/jason_moo/Downloads/helper/"+fileName);  //生成文件的路径
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            t = configuration.getTemplate(templte); //文件名
            t.process(dataMap, out, ObjectWrapper.BEANS_WRAPPER);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
