package com.myron.ws.server.service.impl;
 

import com.myron.ws.server.service.DemoService;

import javax.jws.WebService;
import java.util.Date;
 
@WebService(serviceName = "DemoService", // 与接口中指定的name一致
        targetNamespace = "http://service.server.ws.myron.com", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.myron.ws.server.service.DemoService"// 接口地址
)
public class DemoServiceImpl implements DemoService {
 
    @Override
    public String sayHello(String user) {
        return user+"，现在时间："+"("+new Date()+")";
    }
 
}
