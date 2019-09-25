package com.myron.ws.server.service;
 
import javax.jws.WebService;
 
@WebService(name = "DemoService", // 暴露服务名称
    targetNamespace = "http://service.server.ws.myron.com"// 命名空间,一般是接口的包名倒序
)
public interface DemoService {
 
    String sayHello(String user);
 
}
