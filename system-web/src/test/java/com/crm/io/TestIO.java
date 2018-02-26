package com.crm.io;

import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * Created by jason_moo on 2018/2/7.
 */
public class TestIO {

    private static final String path1 = "/Users/jason_moo/Downloads/mysql.txt";


    public static void main(String[] args) throws Exception{

        System.out.println(System.getProperty("user.dir"));
        File file = new File(path1);
        InputStream inputStream = new FileInputStream(file);
        inputStream = new BufferedInputStream(inputStream);
        inputStream.mark(0);
        byte[] datas = new byte[inputStream.available()];
        while (inputStream.read(datas) != -1){
            System.out.println(new String(datas,"gbk"));
        }
        inputStream.reset();


        byte[] data = new byte[1024];
        while (inputStream.read(data) != -1){
            System.out.println(new String(datas,"gbk"));
        }

    }
}
