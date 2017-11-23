package me.gacl.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtils {
	
	
	  private static final int buffer = 2048;  

	  
	  public static  InputStream   getStreamByZip(String path,String fileName)throws Exception{
		ZipFile zipFile = new ZipFile(path, Charset.forName("gbk"));
		Enumeration<?> entries = zipFile.entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			if (entry.getName().equals(fileName)) {
				return zipFile.getInputStream(entry);
			}
		}

		return null;
		  
	  }
	  
	  public static void main(String[] args) {
		  try(InputStream is = getStreamByZip("C:\\Users\\Administrator\\Desktop\\xx.zip", "TEST.dat");
			  BufferedReader read = new BufferedReader(new InputStreamReader(is));) {
			  String line = null;
			  while((line=read.readLine())!=null){
				 String[] x =  line.split("\\|");
				 System.out.println(x[0]);
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
	}

}
