package com.beta.app.stream;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;


public class StreamStudy {
    public static void main(String[] args) {
        Reader read = new BufferedReader(new InputStreamReader(System.in));
        
        while(true){
            System.out.println("输入");
            char[] a = new char[16];
            int flag  = 0;
            while(flag != -1){
                try {
                    flag = read.read(a);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(new String(a));
            }
            
        }
    }
}
