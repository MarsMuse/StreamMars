package com.beta.api.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * 
 * @ClassName:  PaymentInterface   
 * @Description:TODO(测试RESTful 接口)   
 * @author: FireMonkey
 * @date:   2017年7月7日 上午10:37:17   
 *     
 * @Copyright: 2017 
 *
 */
@Path(value="/pay/v1.0")
public interface PaymentInterface {

    @POST
    @Path(value="/meet")
    String  getHello(String message);
}
