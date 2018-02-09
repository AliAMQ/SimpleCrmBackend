package com.practice1;

//import com.practice1.config.Practice1Context;
import com.practice1.config.Practice1Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Alireza on 1/4/2018.
 */
@SpringBootApplication
public class Practice1Application {

    public static void main(String[] args)
    {
        SpringApplication.run(Practice1Context.class, args);
    }
}
