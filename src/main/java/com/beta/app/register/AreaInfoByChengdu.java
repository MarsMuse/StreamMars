package com.beta.app.register;
/**
 * 
 * @ClassName:  AreaInfoByChengdu   
 * @Description:TODO(区域信息成都)   
 * @author: zouyao
 * @date:   2017年7月31日 上午9:34:12   
 *     
 * @Copyright: 2017 
 *
 */
public class AreaInfoByChengdu {
    
    private String name;
    
    private String moduleNo;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getModuleNo() {
        return moduleNo;
    }
    public void setModuleNo(String moduleNo) {
        this.moduleNo = moduleNo;
    }
    @Override
    public String toString() {
        return "成都区域划分与对应ID [区域名=" + name + ", ID=" + moduleNo + "]";
    }
    
    
    
}
