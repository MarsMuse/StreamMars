package com.beta.prop.web.upload.file;

public class UploadLocalServerFile  extends FileAbstractInfor   implements Cloneable{

    /**
     * 
     */
    private static final long serialVersionUID = 4742516366831741884L;
    //相对路径
    private  String  relativePath;
    
    //临时文件名
    private String storedFileName;
    
    //相对路径文件扩展名
    private String storedFileExtName;

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getStoredFileName() {
        return storedFileName;
    }

    public void setStoredFileName(String storedFileName) {
        this.storedFileName = storedFileName;
    }

    public String getStoredFileExtName() {
        return storedFileExtName;
    }

    public void setStoredFileExtName(String storedFileExtName) {
        this.storedFileExtName = storedFileExtName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    public Object clone() {
        
        UploadLocalServerFile localServerFile  =  null;
        
        localServerFile = (UploadLocalServerFile) super.clone();
        return localServerFile;
    }
    
    

}
