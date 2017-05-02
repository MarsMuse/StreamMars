package com.beta.prop.web.upload.file;

/**
 * 
 * @ClassName:  UploadFileSystem   
 * @Description:TODO(上传文件至文件服务器)   
 * @author: mars
 * @date:   2017年5月2日 下午5:10:44   
 *     
 * @Copyright: 2017 
 *
 */
public class UploadFileSystem extends FileAbstractInfor {

    /**
     * 可序列化
     */
    private static final long serialVersionUID = -8094539551024192895L;
    
    //文件系统访问路径
    private  String  fileSystemURL;
    
    //相对路径（在文件系统的的相对路径）
    private  String  relativePath;
    
    //临时文件名
    private String  storedFileName;
    
    //相对路径文件扩展名
    private String  storedFileExtName;

    public String getFileSystemURL() {
        return fileSystemURL;
    }

    public void setFileSystemURL(String fileSystemURL) {
        this.fileSystemURL = fileSystemURL;
    }

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
    
    
}
