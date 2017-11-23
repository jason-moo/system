package me.gacl;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

        try {
            InputStream inputStream = App.class.getClassLoader().getResourceAsStream("sss.text");
            List<String> strings = IOUtils.readLines(inputStream);
            for (String a : strings){
                System.out.println(a);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
