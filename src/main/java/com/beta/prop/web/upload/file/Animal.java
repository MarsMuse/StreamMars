package com.beta.prop.web.upload.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Animal    implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 8907565848742840156L;

    private String  gender;
    
    private Person person;
    
    private TestSer testSer;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public TestSer getTestSer() {
        return testSer;
    }

    public void setTestSer(TestSer testSer) {
        this.testSer = testSer;
    }
    
    public  Animal  myClone(){
        Animal  animal  =  null;
        ByteArrayOutputStream  baos  = new  ByteArrayOutputStream();
        
        ObjectOutputStream oos =   null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            ByteArrayInputStream bais  =  new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois  =  new  ObjectInputStream(bais);
            animal = (Animal) ois.readObject();
        } catch (IOException e) {
            
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return animal;
    }
}
