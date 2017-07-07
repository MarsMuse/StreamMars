package com.beta.app.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamStudy {
    public static void main(String[] args) {
        File inFile = new File("D:/bg.jpg");
        File outFile = new File("D:/bg1.jpg");
        System.out.println(inFile.length()/1024);
        
        try(InputStream is = new FileInputStream(inFile) ; OutputStream os = new FileOutputStream(outFile)){
            
            byte[]  buffer = new byte[256];
            int flag = 0;
            while(flag >-1){
                flag = is.read(buffer);
                os.write(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
