package com.beta.prop.web.upload.file;

public class Test  implements Cloneable{
    private  UploadLocalServerFile localServerFile  ;
    
    private String name;

    public UploadLocalServerFile getLocalServerFile() {
        return localServerFile;
    }

    public void setLocalServerFile(UploadLocalServerFile localServerFile) {
        this.localServerFile = localServerFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Object  clone(){
        Test test = null;
        
        try {
            test = (Test) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        test.localServerFile = (UploadLocalServerFile) test.localServerFile.clone();
        return test;
    }
}
