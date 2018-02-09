package com.practice1.config;

import com.practice1.config.security.Http401UnauthorizedEntryPoint;
import com.practice1.config.security.SecurityConfig;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;

/**
 * Created by Alireza on 1/4/2018.
 */
@Configuration
@ComponentScan(basePackages = "com.practice1")
@Import({PersistenceContext.class, SecurityConfig.class,
        SimpleCorsFilter.class,
        Http401UnauthorizedEntryPoint.class,
        ServiceConfig.class,
        ControllerConfig.class
})
public class Practice1Context {

    // Deploy environment profiles
    @Profile("rand")
    @Configuration
    @EnableAutoConfiguration
    @PropertySource("classpath:rand.properties")
    static class DeployProperties {
    }

}
