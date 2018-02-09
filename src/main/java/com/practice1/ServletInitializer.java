package com.practice1;

import com.practice1.config.Practice1Context;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Alireza on 1/7/2018.
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
       return springApplicationBuilder.sources(Practice1Context.class);
    }
}
