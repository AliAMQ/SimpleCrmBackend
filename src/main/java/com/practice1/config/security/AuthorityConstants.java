package com.practice1.config.security;
//Don't want to use it :)
/**
 * Created by Alireza on 2/4/2018.
 */
public class AuthorityConstants {

    public static final String MASTERADMIN = "ROLE_MASTERADMIN";
    public static final String ADMIN = "ROLE_ADMIN";

    public static String validatePrivilege(String privilage){

        if (privilage.equalsIgnoreCase(MASTERADMIN)){
            return MASTERADMIN;
        }
        if (privilage.equalsIgnoreCase(ADMIN)){
            return ADMIN;
        }
        return "unauthorized";

    }

}
