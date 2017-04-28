package com.beta.prop.web.upload.file;

import java.io.Serializable;

public class Person  implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8267780207386708780L;

    private String age;

    private TestSer testSer;
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public TestSer getTestSer() {
        return testSer;
    }

    public void setTestSer(TestSer testSer) {
        this.testSer = testSer;
    }
    
}
