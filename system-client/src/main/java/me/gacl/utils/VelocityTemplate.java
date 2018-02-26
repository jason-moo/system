package me.gacl.utils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import java.io.StringWriter;
import java.util.Properties;

/**
 * Created by jason_moo on 2017/11/26.
 */
public class VelocityTemplate {

    public static String getVelocityTemplate(String basePath) throws Exception {

        String sysRoot = VelocityTemplate.class.getResource("").getPath();
        Properties properties = new Properties();
        //设置velocity资源加载方式为file
        properties.setProperty("resource.loader", "file");
        //设置velocity资源加载方式为file时的处理类
        properties.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.FileResourceLoader");
        properties.put("input.encoding", "UTF-8");
        properties.put("output.encoding", "UTF-8");
        //实例化一个VelocityEngine对象
        VelocityEngine velocityEngine = new VelocityEngine(properties);
        //实例化一个VelocityContext
        VelocityContext context = new VelocityContext();
        //向VelocityContext中放入键值
        context.put("username", "张三");
        context.put("password", "123456789");
        context.put("age", "20");
        //实例化一个StringWriter
        StringWriter writer=new StringWriter();
        Template template = velocityEngine.getTemplate(sysRoot+basePath, "UTF-8");
        template.merge(context, writer);
        return writer.toString();
    }

    public String getPath(){
        return this.getClass().getResource("").getPath().toString();
    }

    public static void main(String[] args) {
        VelocityTemplate velocityTemplate = new VelocityTemplate();
        System.out.println(velocityTemplate.getPath());
        System.out.println(Template.class.getClassLoader().getResource("").getPath());
    }
}
