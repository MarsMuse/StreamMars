package com.beta.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.beta.api.entity.ChargePushBackInfo;
import com.beta.api.entity.PushBatchChargeInfo;
import com.beta.api.service.WorkPlatformService;
import com.beta.prop.encrypt.entity.DecryptInformation;
import com.beta.prop.encrypt.entity.InformationSecurityEntity;
import com.beta.prop.encrypt.entity.TransInformation;
import com.beta.prop.encrypt.util.InformationSecurityUtil;

@Service(value="workPlatformServiceImp")
public class WorkPlatformServiceImp implements WorkPlatformService {

    @Override
    public String test(String meesage) {
        
        return "ceshi";
    }

    @Override
    public String getBatchData(String meesage) {
        //转化
    	TransInformation tis = JSON.parseObject(meesage,TransInformation.class);
    	
    	
    	//新建安全信息对象
        InformationSecurityEntity ise = new InformationSecurityEntity(tis.getEncryptKey(), tis.getCipherData(), tis.getPlatformSignature());
        
        String pri="E:/testKey/dev/payment_dev_pri.pfx"; 
        String password= "abc123456";
        String pub = "E:/testKey/payment/payment_pub.cer";
        
      //信息安全工具解密数据
        DecryptInformation di = InformationSecurityUtil.decryptInformation(ise, pub, pri, password);
        List<PushBatchChargeInfo>   data = JSON.parseArray(di.getData(), PushBatchChargeInfo.class);
        List<ChargePushBackInfo> re = new ArrayList<>();
    	System.out.println(data.size());
    	for( int i = 0 ; i< data.size() ; i++){
    		PushBatchChargeInfo a = data.get(i);
    		ChargePushBackInfo temp = new ChargePushBackInfo(a.getChargeNo(), i%2==0?"1":"2");
    		re.add(temp);
    	}
    	String info = JSON.toJSONString(re);
    	String pri1="E:/testKey/dev/payment_dev_pri.pfx"; 
        String password1= "abc123456";
        String pub1 = "E:/testKey/payment/payment_pub.cer";
        
        InformationSecurityEntity is = InformationSecurityUtil.encryptInformation(info, pub1, pri1, password1);
        TransInformation ti = new TransInformation("CS007", "C001",is.getEncryptKey(), is.getCipherData(), is.getPlatformSignature());
        String content = JSON.toJSONString(ti);
        return content;
    }

}
