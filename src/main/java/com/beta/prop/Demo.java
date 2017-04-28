package com.beta.prop;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.beta.prop.web.upload.file.Animal;
import com.beta.prop.web.upload.file.Person;
import com.beta.prop.web.upload.file.TestSer;

public class Demo {
    
    public static void main(String[] args) {
        Animal a = new Animal();
        a.setGender("222");
        Person p  = new Person();
        p.setAge("25");
        TestSer ta = new TestSer();
        ta.setName("mars");
        p.setTestSer(ta);
        p.setTestSer(ta);
        a.setPerson(p );
        TestSer t2 = new TestSer();
        t2.setName("muse");
        a.setTestSer(t2);
        
        Animal an =  a.myClone();
        
        an.setGender("555");
        
        System.out.println(a.getGender());
        System.out.println(an.getGender());
        System.out.println(an);
        System.out.println(a);
        
        try {
            FileOutputStream  fos  =  new FileOutputStream(new File("d:/fire.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(a);
            FileInputStream fis = new FileInputStream(new File("d:/fire.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            Animal anm = (Animal) ois.readObject();
            System.out.println(anm);
            System.out.println(a == anm);
            System.out.println(anm.getTestSer().getName());
        } catch ( IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        
    }
}
