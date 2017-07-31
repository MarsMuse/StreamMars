package com.beta.app.register;

public class CenterInfo {
    @Override
    public String toString() {
        return "CenterInfo [name=" + name + ", ord=" + ord + ", shortName=" + shortName + ", moduleNo=" + moduleNo
                + "]";
    }
    private String name;
    private String ord;
    private String shortName;
    private String moduleNo;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOrd() {
        return ord;
    }
    public void setOrd(String ord) {
        this.ord = ord;
    }
    public String getShortName() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public String getModuleNo() {
        return moduleNo;
    }
    public void setModuleNo(String moduleNo) {
        this.moduleNo = moduleNo;
    }
    
}
