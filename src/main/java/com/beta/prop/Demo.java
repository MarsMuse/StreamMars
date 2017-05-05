package com.beta.prop;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.io.FileUtils;

public class Demo {
    
    public static void main(String[] args) {
        
    	Executor exe = Executors.newCachedThreadPool();
    	
        StringBuilder  sb = new StringBuilder("555");
        
        sb.append("[aaaaa人a]这");
        try {
            FileUtils.writeByteArrayToFile(new File("d:/demo.txt"), sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
