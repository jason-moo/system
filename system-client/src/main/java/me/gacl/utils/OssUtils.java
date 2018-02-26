package me.gacl.utils;


import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jason_moo on 2017/11/29.
 */
public class OssUtils {


    public static void main(String[] args) throws Exception {

        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name +".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[inputStream.available()];
                    inputStream.read(b);
                    return defineClass(fileName,b,0,b.length);
                }catch (IOException e){
                    e.printStackTrace();
                }
                return null;
            }
        };
        Object obj = classLoader.loadClass("OssUtils").newInstance();
        System.out.println(obj.getClass());  //class ClassLoaderTest
        System.out.println(obj instanceof  OssUtils);  //false

    }

}
