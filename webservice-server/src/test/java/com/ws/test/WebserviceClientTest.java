package com.ws.test;


import com.alibaba.fastjson.JSON;
import com.myron.ws.server.model.User;
import com.myron.ws.server.service.UserService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import java.util.ArrayList;

public class WebserviceClientTest {
    private static final String WSDL_URL = "http://localhost:8080/demo/user?wsdl";

    //动态调用
    public static void main(String[] args) throws Exception {
        JaxWsDynamicClientFactory dcflient=JaxWsDynamicClientFactory.newInstance();

        Client client=dcflient.createClient(WSDL_URL);

        Object[] objects=client.invoke("getUser","411001");
        System.out.println("*******"+objects[0].toString());
        System.out.println(JSON.toJSONString(objects, true));

        Object[] objectall=client.invoke("getAlLUser");
        System.out.println(JSON.toJSONString(objects, true));

        main3(args);
    }


    //调用方式二，通过接口协议获取数据类型
    public static void main2(String[] args) throws Exception {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean=new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress(WSDL_URL);
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);

        UserService userService=(UserService)jaxWsProxyFactoryBean.create();
        User userResult= userService.getUser("411001");
        System.out.println("UserName:"+userResult.getUsername());
        ArrayList<User> users=userService.getAlLUser();

    }


    //调用方式三，通过接口协议获取数据类型,设置链接超时和响应时间
    public static void main3(String[] args) throws Exception {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean=new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress(WSDL_URL);
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);

        UserService userService = (UserService) jaxWsProxyFactoryBean.create(); // 创建客户端对象
        Client proxy= ClientProxy.getClient(userService);
        HTTPConduit conduit=(HTTPConduit)proxy.getConduit();
        HTTPClientPolicy policy=new HTTPClientPolicy();
        policy.setConnectionTimeout(1000);
        policy.setReceiveTimeout(1000);
        conduit.setClient(policy);

        User userResult= userService.getUser("411001");
        System.out.println("UserName:"+userResult.getUsername());
        ArrayList<User> users=userService.getAlLUser();
        System.out.println(JSON.toJSONString(users, true));
    }
}