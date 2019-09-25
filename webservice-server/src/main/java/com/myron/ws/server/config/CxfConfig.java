package com.myron.ws.server.config;
 

import com.myron.ws.server.service.DemoService;
import com.myron.ws.server.service.UserService;
import com.myron.ws.server.service.impl.DemoServiceImpl;
import com.myron.ws.server.service.impl.UserServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {
 
    @Bean
    public ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(),"/demo/*");
    }
 
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }
 
    @Bean
    public DemoService demoService() {
        return new DemoServiceImpl();
    }
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
 
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), demoService());
        endpoint.publish("/api");
        return endpoint;
    }

    @Bean
    public Endpoint endpointUser() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), userService());
        endpoint.publish("/user");
        return endpoint;
    }
 
}
